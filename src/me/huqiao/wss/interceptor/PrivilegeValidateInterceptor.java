package me.huqiao.wss.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.util.Constants;
import me.huqiao.wss.util.threadlocal.ThreadLocalUtil;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.LoginInfo;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限验证拦截器
 * @author NOVOTS
 * @version Version 1.0
 */
public class PrivilegeValidateInterceptor extends HandlerInterceptorAdapter {
    private static final String[] urlsWithOutLogin = new String[] {// 不需要权限控制的action
    	"loginUI.do",// 登录界面
    	"login.do",// 登录 
    	"loginOut.do",// 退出
    	"auth.do",
    	"admin/index.do",
    	"index.do",
    	"frontend/",
    	"user/userUploadPhoto.do",
    	"user/updatePhoto.do",
    	"user/waitForUpload.do",
    	"changeLocale.do",
    	"filee/viewPic.do",
    	"filee/downloadFile.do",
    	"f/",
	};
	private static final String[] urlsWithLogin = new String[] {// 只需要登录就能拥有的权限
		"home.do",
		"filee/ckeditorupload",
		"filee/downloadFile.do",
		"filee/viewPic.do",
		"frag.do",
		"user/myfile.do",
		"notice/detail.do",
		"user/selectAllList.do",
		".{1,}/(select2Query.do|queryById.do)",//select2查询接口
		".{1,}/selectList.do",
		".{1,}/tabAddForm.do",
		".{1,}/tabDetailForm.do",
		".{1,}/selectTree.do",
		".{1,}/templateExport.do",
		//".{1,}/import.do", 
		"filee/dialogToAdd.do",
		"area/getProvincesByArea.do",
		"agent/tradeRecord.do",
		"user/updatePassword.do",
		"gatherResult/markAsRead.do",
		"gatherResult/mark.do",
	};
	
	//继承关系权限
	public static final Map<String,List<String>> extendsFromParents = new HashMap<String,List<String>>();
	
	static{
		//我的合同申请
		List<String> myContractApplyList = new ArrayList<String>();
		myContractApplyList.add("contractApply/add.do");
		//myContractApplyList.add("contractApply/update.do");
		//myContractApplyList.add("contractApply/delete.do");
		myContractApplyList.add("contractApply/detail.do");
		extendsFromParents.put("contractApply/myList.do",myContractApplyList);
	}
	
	private static final String publicUrlPrefix = "ajax";
	private static boolean flag = false;
	private static int SECONDS_OF_WEEK = 60*60*24*7;
    /**
     * controller前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        LoginInfo loginInfo = (LoginInfo)request.getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION);
        
        request.setAttribute("file_format_picture", ".jpg,.jpeg,.png,.bmp,.gif");
        
        /*if(1==1){
        	 return super.preHandle(request, response, handler);
        }*/
        
        ThreadLocalUtil.loginInfoThreadLocal.set(loginInfo);
    	String url = request.getRequestURI();
		url = url.substring(url.indexOf("/") + (request.getContextPath().length() + 1));
		request.setAttribute("_request_url", url);
        try {
        	if(accessUrlWithOutLogin(url) ){//访问开放地址，通过
        		prepareFormUrl(request);
        		return super.preHandle(request, response, handler);
        	}else if(isLogined(loginInfo)){
            	if(hasPrivilege(loginInfo,url,true,response,request)!=null){//登录，访问受控地址，且有权限，通过
	                return super.preHandle(request, response, handler);
            	}else{
            		returnNoPrivilege(response);//登录，但没权限，拒绝
            		return false;
            	}
            }else{//登录或超时，拒绝
            	returnNoLogin(response);
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnException(response,e.getMessage());
            return false;
        }
    }
    /**
     * 准备提交表单路径
     * @param request HttpServletRequest对象
     */
    private void prepareFormUrl(HttpServletRequest request) {
    	int count = 0;
    	
    	String basePath = request.getScheme() + "://"
    			+ request.getServerName() + ":" + request.getServerPort();
    			
    	StringBuffer url = new StringBuffer( basePath + request.getRequestURI());
    	for(Map.Entry<String,String[]> entry : request.getParameterMap().entrySet()){
    		for(String param : entry.getValue()){
    			url.append( (count >0 ? "&" :"?") ).append( entry.getKey() ).append( "=" ).append(param);
    			count++;
    		}
    	}
    	request.setAttribute("from",url.toString());
	}
   /**
    * 访问登录之后的公开地址是否匹配
    * @param url 功能路径
    * @return boolean 是否匹配
    */
    private boolean accessPublicUrl(String url){
    	return url.startsWith(publicUrlPrefix);
    }
    /**
     * 访问不需要登录即可访问的地址
     * @param url 功能路径
     * @return boolean 是否需要
     */
    private boolean accessUrlWithOutLogin(String url){
    	for (String str : urlsWithOutLogin) {
			if (url.startsWith(str) || Pattern.matches(str, url)) {
				return true;
			}
		}
    	return false;
    }
    /**
     * 访问只需要登录就能访问的地址
     * @param url 功能路径
     * @return boolean 是否需要
     */
    private boolean accessUrlWithLogin(String url){
    	for (String str : urlsWithLogin) {
    		if (url.startsWith(str) || Pattern.matches(str, url)) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * 判断权限，如果匹配成功返回对应的FunctionPoint对象，否则返回null
     * @param loginInfo 登陆信息对象
     * @param url 功能路径
     * @param recordHistory 历史记录
     * @param response HttpServletResponse对象
     * @param req HttpServletRequest对象
     * @return FunctionPoint 权限对象
     */
	public FunctionPoint hasPrivilege(LoginInfo loginInfo,String url,boolean recordHistory,HttpServletResponse response,HttpServletRequest req) {
		
		if(accessUrlWithLogin(url) || accessPublicUrl(url)){
			return new FunctionPoint();
		}
		
		List<String> parentUrls = getExtendsFrom(url);
		FunctionPoint p = null;
		if(!parentUrls.isEmpty()){
			for(String purl : parentUrls){
				p = urlMatch(purl,loginInfo.getTopFunctionPoints(),1,recordHistory,response,req);
				if(p!=null){
					return p;
				}
			}
		}
		
		return urlMatch(url,loginInfo.getTopFunctionPoints(),1,recordHistory,response,req);
	}
	
	
	/**
	 * 权限判断，如果匹配成功返回对应的FunctionPoint对象，否则返回null
	 * @param url 功能路径
	 * @param points 权限集合
	 * @param level 级别
	 * @param recordHistory 历史记录
	 * @param response HttpServletResponse对象
	 * @param req HttpServletRequest对象
	 * @return FunctionPoint 权限对象
	 */
	private FunctionPoint urlMatch(String url,Collection<FunctionPoint> points,int level,boolean recordHistory,HttpServletResponse response,HttpServletRequest req){
		if(points==null || points.size()==0){
			return null;
		}
		String tempUrl = null;
		FunctionPoint tempP = null;
		FunctionPoint result = null;
		for(FunctionPoint p  : points){
			tempUrl = p.getUrl();
			if(tempUrl.indexOf("?")>0){
				tempUrl = tempUrl.substring(0,tempUrl.indexOf("?"));
			}
			if(p.getUrl()!=null && !p.getUrl().trim().equals("")  && url.startsWith(tempUrl)){
				result =  p;
				if(result!=null && level==2 && recordHistory){
					recordHistoryInCookie(result,response,req);
				}
				break;
			}
			tempP = urlMatch(url,p.getChildren(),level+1,recordHistory,response,req);
			if(tempP!=null){
				result = tempP;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 记录访问历史到Cookie中
	 * @param p 权限
	 * @param response HttpServletResponse对象
	 * @param req HttpServletRequest对象
	 */
	private void recordHistoryInCookie(FunctionPoint p,HttpServletResponse response,HttpServletRequest req) {
		removeAttributeInCookie(req,response,p.getName());//+p.getId()
		setAttributeInCookie(response,"ACCESS_HISTORY"+p.getI18nCode(),p.getUrl()+p.getRelName()+"nav_tab",SECONDS_OF_WEEK);
	}
	/**
	 * 判断是否登陆
	 * @param loginInfo 登陆信息
	 * @return 判断结果
	 */
	private boolean isLogined(LoginInfo loginInfo){
		return loginInfo!=null;
	}
	
	/**
	 * 返回未登录或者登录超时信息
	 * @param response HttpServletResponse对象
	 * @throws Exception 异常
	 */
	private void returnNoLogin(HttpServletResponse response) throws Exception{
         outputJsonResult(response,JsonResult.STATUS_CODE_TIMEOUT,JsonResult.LOGIN_UI_URL,"您未登录，或者会话已超时，请重新登录！");
	}
	
	/**
	 * 描述此方法什么的
	 * @param response HttpServletResponse对象
	 * @param message 异常信息
	 * @throws Exception 异常
	 */
	private void returnException(HttpServletResponse response,String message) throws Exception{
		outputJsonResult(response,JsonResult.STATUS_CODE_ERROR,JsonResult.LOGIN_UI_URL,"服务器出现异常:"+message);
	}
	
	/**
	 * 返回未登录或者登录超时信息
	 * @param response HttpServletResponse对象
	 * @throws Exception抛出异常
	 */
	private void returnNoPrivilege(HttpServletResponse response) throws Exception{
		outputJsonResult(response,JsonResult.STATUS_CODE_NO_PERMISSION,JsonResult.LOGIN_UI_URL,"您无权限进行该操作！");
	}
	/**
	 * 输出json格式字符串
	 * @param response HttpServletResponse
	 * @param statusCode 状态码
	 * @param forwardUrl 要跳转的路径
	 * @param message 显示信息
	 * @throws Exception 异常
	 */
	private void outputJsonResult(HttpServletResponse response,String statusCode,String forwardUrl,String message) throws Exception{
		 ObjectMapper objectMapper = new ObjectMapper();
		 JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		 JsonResult jsonResult = new JsonResult();
		 jsonResult.setStatusCode(statusCode);
         jsonResult.setForwardUrl(forwardUrl);
         jsonResult.setMessage(message);
         jsonGenerator.writeObject(jsonResult);
	}
	
 
	/**
	 * 向cookie中存放变量
	 * @param response HttpServletResponse对象
	 * @param name 变量名称
	 * @param value 变量值
	 * @param expiry 存活时间
	 */
    public void setAttributeInCookie(HttpServletResponse response,String name,String value,int expiry){
		Cookie cookie = new Cookie(name,value);
		cookie.setMaxAge(expiry); 
		cookie.setPath("/");
		response.addCookie(cookie);
    }
    /**
     * 清除cookie中存放的变量
     * @param req HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param name 要清除属性名
     */
    public void removeAttributeInCookie(HttpServletRequest req,HttpServletResponse response,String name){
    	for(Cookie cookie : req.getCookies()){
			if(cookie.getName().startsWith("ACCESS_HISTORY")){
				if(cookie.getName().startsWith(name)){
					cookie.setMaxAge(0);
					cookie.setValue(null);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
    	}
    }
    
    
	private List<String> getExtendsFrom(String url){
		List<String> results = new ArrayList<String>();
		for(Map.Entry<String, List<String>> entry : extendsFromParents.entrySet()){
			for(String str : entry.getValue()){
				if(url.startsWith(str)){
					results.add(entry.getKey());
				}
			}
		}
		return results;
	}
   
}
