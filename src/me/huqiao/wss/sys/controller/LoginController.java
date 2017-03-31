package me.huqiao.wss.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.servlet.VerifyImageServlet;
import me.huqiao.wss.sys.entity.AccessHistory;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.sys.entity.enumtype.UserStatus;
import me.huqiao.wss.sys.service.IFunctionPointService;
import me.huqiao.wss.sys.service.IUserService;
import me.huqiao.wss.util.Constants;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.LoginInfo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 登陆Controller
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
public class LoginController extends BaseController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
    /**用户服务*/
    @Resource
    private IUserService userService;
    /**功能点服务*/
    @Resource
    private IFunctionPointService functionPointService;
    
    /**
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param session HttpSession对象
     * @return String 重定向的路径
     */
    @RequestMapping(value = "/auth")
    public String ntlmAuth(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	Principal princ = request.getUserPrincipal();
    	if (isLogined()) {
			return "redirect:index.do";
		}
    	if(princ!=null){
    		session.setAttribute("princpalNameInSession", princ.getName());
    	}
    	return "redirect:/loginUI.do";
    }
    
    @RequestMapping("/changeLocale")
    @ResponseBody
    public String changeLocale(HttpServletRequest request){
    	/*if(!isLogined()){
    		return "redirect:/loginUI.do";
    	}*/
    	return "ok";
    }
    
    /**
     * 系统主页
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param session HttpSession对象
     * @return String jsp显示路径
     */
    @RequestMapping("/index")
    public String adminIndex(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	if(!response.isCommitted()){
			if (isLogined()) {
				return "index";
			}
			return "redirect:/loginUI.do";
    	}
    	return null;
	}
    
  
    /**
     * 登录
     * @param loginName 登录名
     * @param password 密码
     * @param checkcode 检查code
     * @param response HttpServletResponse 对象
     * @param session HttpSession对象
     * @param request  HttpServletRequest对象
     * @return ModelAndView jsp显示路径和显示内容
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "loginName")String loginName,@RequestParam(value = "password")String password,@RequestParam(value = "checkcode")String checkcode,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
    	if(!response.isCommitted()){
    		ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
    		User user = null;
    		
    		String checkCodeInSession = (String)request.getSession().getAttribute(VerifyImageServlet.SIMPLE_CAPCHA_SESSION_KEY);
    		checkCodeInSession = checkCodeInSession==null ? null : checkCodeInSession.toLowerCase();

     	if(!(checkcode!=null && checkcode.toLowerCase().equals(checkCodeInSession))){
    			mav.addObject("passwordError", "验证码输入错误!");
    			return mav;
    		} 
    		
    		//Config config = Config.getInstance(true);
    		if(notEmpty(loginName) && notEmpty(password)){//如果用户名和密码都存在,普通登录
    			user = userService.getEntityByProperty(User.class, "username", loginName);
    			if(user==null || user.getStatus()!=UserStatus.Active ){
    				log.info("用户:"+loginName+"尝试登录，失败：数据库没有这个用户");
    				mav.addObject("passwordError", "Incorrect username or password");
    			}else{
					if(localLoginValidate(user,password)){
						log.info("用户:"+loginName+" 是普通用户，通过本地密码验证登录成功。");
						user.recordLoginTime(new Date());
						userService.update(user);
						mav.setViewName("redirect:index.do");//本地登录成功
						request.getSession().setAttribute(Constants.FORM_URL_INSESSION, request.getParameter("from"));
					}else{
						mav.addObject("passwordError", "用户名或密码错误！");
						mav.addObject("hide",null);
					}
    			}
    		}/*else if(domainLoginValidateByNtlm(request)){//存在域Ntlm变量，尝试域登录
    			
    			//则根据域变量获取到用户名
    			String princpalName = getPrincpalUserName(request);
    			user = userService.getEntityByProperty(User.class, "userName", princpalName);
    			
    			if(user!=null){
    				log.info("用户:"+loginName+"，通过获取到本机域信息直接登录成功!");
    			}else{
    				mav.addObject("passwordError","Incorrect username or password");
    			}
    		}*/else{
    			//用户名和密码以及域变量都不存在，重定向到登录页面
    			mav.setViewName("redirect:loginUI.do");
    		}
    		//然后获取到user信息，并加载权限信息，设置“已登录”标志
    		if(user!=null){
    			prepareFunctionPoint(session,user);
    			//prepareList(session);
    			
    		}
    		return mav;
    	}
    	return null;
    }
    /**
     * 判断字符串是否为空
     * @param str 要判断的字符串
     * @return boolean 是否为空的结果
     */
    private boolean notEmpty(String str){
    	return str!=null && !str.trim().equals("");
    }
    
    
    /**
     * 进行数据库密码验证
     * @param user 用户对象
     * @param password 密码
     * @return boolean 验证结果
     */
    private boolean localLoginValidate(User user,String password){
    	return Md5Util.checkPassword(password,user.getPassword());
    }
    
    
    /**
     * 准备LoginInfo信息放到Session中
     * @param session HttpSession对象 
     * @param user 用户对象
     */
    private void prepareFunctionPoint(HttpSession session,User user){
    	 clearRandomCode(session);
    	 LoginInfo loginInfo = new LoginInfo(user,functionPointService.getFunctionPointsByUser(user));
    	 session.setAttribute(Constants.LOGIN_INFO_IN_SESSION, loginInfo);
    	 session.setAttribute("___currentUser", user);
    	 //loginInfo.setTaskChecker(TaskFactory.getInstance().getChecker(session));
    	// Hibernate.initialize(user.getServiceTypeGroups());
    	
    }
    
    
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
    	 JsonResult jsonResult = new JsonResult();
    	 jsonResult.setForwardUrl("../loginUI.do");
    	 jsonResult.setStatusCode("1000");
    	 session.removeAttribute(Constants.LOGIN_INFO_IN_SESSION);
    	 session.removeAttribute("NtlmHttpAuth");
    	 session.removeAttribute("princpalNameInSession");
    	 session.invalidate();
    	return "redirect:loginUI.do";
    }
    
    /**
     * 登录超时重新登录
     * @param request HttpServletRequest对象 
     * @return String 登陆页面路径
     */
    @RequestMapping("/loginUI")
    public String loginUI(HttpServletRequest request){
    	request.setAttribute("from", request.getParameter("from"));
    	prepareRandomCode(request.getSession());
        return "login";
    }
    
    /**
     * 准备随机码
     * @param session HttpSession对象
     */
    private void prepareRandomCode(HttpSession session){
    	session.setAttribute(Constants.RANOM_CODE_FOR_LOGIN,Md5Util.getRandomCode());
    }
    /**
     * 清除随机码
     * @param session HttpSession对象
     */
    private void clearRandomCode(HttpSession session){
    	session.setAttribute(Constants.RANOM_CODE_FOR_LOGIN,null);
    }
    
    
    /**
     * 我的主页
     * @param request HttpServletRequest对象
     */
    @RequestMapping(value="/admin/home")
    public void home(HttpServletRequest request){
	   	 LoginInfo loginInfo = (LoginInfo)request.getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION);
	   	// request.setAttribute("tasks", loginInfo.getTaskChecker().doCheck(loginInfo).getTasks());
	     request.setAttribute("accessHistorys",getAccessHistory(request));
	     
	     
    }
    /**
     * 获得访问历史
     * @param request HttpServletRequest对象
     * @return List<AccessHistory>访问路径列表
     */
    private List<AccessHistory> getAccessHistory(HttpServletRequest request){
    	Map<String,String> cookieMap = getCookieMap(request);
    	List<AccessHistory> result = new ArrayList<AccessHistory>();
    	AccessHistory history = null;
    	try {
	    	for(Map.Entry<String, String> entry : cookieMap.entrySet()){
	    		if(entry.getKey().startsWith("ACCESS_HISTORY")){
	    			history = new AccessHistory();
	    			history.setName(URLDecoder.decode(entry.getKey().substring(14),"UTF-8"));
	    			if(!history.getName().startsWith("funcs.")){
	    				continue;
	    			}
					history.setUrl(entry.getValue());
	    			history.setNavTabId(history.getUrl().substring(history.getUrl().indexOf("nav_tab")));
	    			result.add(0,history);
	    		}
	    	}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	};
    	return result;
    }
    
    
    @RequestMapping(value = "/frag")
    public String dwzFrag(){
    	return "dwz.frag";
    }

    
}
