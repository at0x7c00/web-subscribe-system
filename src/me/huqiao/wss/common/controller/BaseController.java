package me.huqiao.wss.common.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Init;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.listener.ApplicationContextAccessUtilListener;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.common.service.ICommonFileService;
import me.huqiao.wss.datamodel.ModelProp;
import me.huqiao.wss.datamodel.ModelPropType;
import me.huqiao.wss.datamodel.service.IDataModelService;
import me.huqiao.wss.expandimp.PropParser;
import me.huqiao.wss.expandimp.ScanResult;
import me.huqiao.wss.expandimp.Scaner;
import me.huqiao.wss.expandimp.ValidateResult;
import me.huqiao.wss.expandimp.ValidateResultPackage;
import me.huqiao.wss.expandimp.ValidateType;
import me.huqiao.wss.export.Exportable;
import me.huqiao.wss.i18n.MySessionLocaleRsolver;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.util.Constants;
import me.huqiao.wss.util.DateUtil;
import me.huqiao.wss.util.ExportPOIUtil;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.StringUtil;
import me.huqiao.wss.util.bean.BeanPropUtil;
import me.huqiao.wss.util.web.CommonDateEditor;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.LoginInfo;
import me.huqiao.wss.util.web.Page;
import me.huqiao.wss.util.web.SheetInfo;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 基础控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
public abstract class BaseController {
	
	static Logger log = Logger.getLogger(BaseController.class);
	
	/**资源绑定消息来源对象*/
	@Resource(name = "messageSource")
	protected ResourceBundleMessageSource messageSource;
	/**session解析器*/
	@Resource(name = "localeResolver")
	protected MySessionLocaleRsolver localeResolver;
	
	@Resource
    private IDataModelService dataModelService;
	
	@Resource
	private ICommonFileService fileService;
	
    @InitBinder
    protected void ininBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CommonDateEditor());
    }
    
    /**
     * 设置变量到request中
     * @param name 属性名
     * @param value 属性值
     */
    protected void setAttribute(String name,Object value){
    	getRequset().setAttribute(name, value);
    }
    /**
     * 设置变量到session中
     * @param name 属性名
     * @param value 属性值
     */
    protected void setSessionAttribute(String name,Object value){
    	getRequset().getSession().setAttribute(name, value);
    }
    
    /**
     * 获取request
     * @return HttpServletRequest HttpServletRequest对象
     */
    protected HttpServletRequest getRequset(){
    	ServletRequestAttributes att = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return att.getRequest();
    }
    /**
     * 获取Session 
     * @return HttpSession HttpSession对象
     */
    protected HttpSession getSession(){
    	return this.getRequset().getSession();
    }
    
    /**
     * 获取当前登录用户
     * @return User 当前用户对象
     */
    protected User getCurrentUser(){
    	LoginInfo loginInfo = (LoginInfo)getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION);
    	return loginInfo==null?null:loginInfo.getUser();
    }
    
    
    @Init
    public void init(HttpSession session){
        //System.out.println("初始化输出："+session.getServletContext().getRealPath("")+"\\WEB-INF\\config\\system.properties");
    }
    /**
     * 验证
     * @param jsonResult 操作结果对象
     * @param bindResult BindingResult对象
     * @return boolean 验证是否成功结果
     */
    public boolean validate(JsonResult jsonResult,BindingResult bindResult){
    	if(bindResult.hasErrors()){
    		jsonResult.setStatusCode("300");
    		StringBuffer validateResult = new StringBuffer();
    		for(FieldError fe : bindResult.getFieldErrors()){
    			validateResult.append("<li>").append(fe.getDefaultMessage()).append("</li>");
    		}
    		jsonResult.setMessage(validateResult.toString());
    		return false;
    	}
    	return true;
    }
    
    /**
     * 从请求中查找rel参数，并放入到request中，以便jsp中使用
     * @param request HttpServletRequest对象
     */
    @ModelAttribute
    protected void findAndSaveParams(HttpServletRequest request){
    	request.setAttribute("rel",request.getParameter("rel"));
    }
    
    /**
     * 判断是否登录
     * @return boolean 是否登陆
     */
    protected boolean isLogined(){
    	return getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION)!=null;
    }
    
  
    /**
     * 向cookie中存放变量
     * @param response HttpServletResponse对象 
     * @param name 属性名
     * @param value 属性对应的值
     * @param expiry 有效时间
     */
      public void setAttributeInCookie(HttpServletResponse response,String name,String value,int expiry){
  		Cookie cookie = new Cookie(name,value);
  		cookie.setMaxAge(expiry); 
  		response.addCookie(cookie);
      }
      
      /**
       * 将cookie数组转换成map键值对
       * @param request HttpServletRequest对象
       * @return Map<String,String> cookie数组的map键值对
       */
      public Map<String,String> getCookieMap(HttpServletRequest request){
      	Map<String,String> cookiemap = new LinkedHashMap<String, String>();
      	if(request.getCookies()==null){
      		return cookiemap;
      	}
      	for(Cookie cookie : request.getCookies()){
      		cookiemap.put(cookie.getName(), cookie.getValue());
      	}
      	return cookiemap;
      }
      
  	/**
  	 * 获取国际化信息
  	 * @param request HttpServletRequest对象
  	 * @param code 查询关键字
  	 * @return String 国际化信息
  	 */
  	protected String getI18NMessage(HttpServletRequest request,String code){
  		return messageSource.getMessage(code,null,localeResolver.resolveLocale(request));
  	}
  	
  	
  	
  	/**
  	 * 为导出准备查询实体
  	 * @param request HttpServletRequest对象
  	 * @param dataModel 数据类型
  	 * @param model Model 对象
  	 * @return Exportable 导出实体
  	 */
  	@ModelAttribute(value = "paramModel")
	public Exportable initParamModel(HttpServletRequest request,@RequestParam(value = "dataModel", required = false) String dataModel, Model model) {
	     try {
	    	 Class c = Class.forName(dataModel);
	    	 return (Exportable)c.newInstance();
		} catch (Exception e) {
		}
	    return null;
	}
  
  @RequestMapping("export")
  @ResponseBody
  protected String export(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("paramModel")Exportable exportable,Page pageInfo) {
	    Class c = null;
		try {
			 String dataModel = request.getParameter("dataModel");
			 c = Class.forName(dataModel);
			 
			 IBaseService service = (IBaseService)getSerivceByName(dataModel);
			 pageInfo.setNumPerPage(100000000);
			 Page page = service.getListPage(exportable, pageInfo);
			 
			 doExcport(request,dataModel,page.getList(),response,getI18NMessage(request, "funcs." + c.getSimpleName() + ".entityName")+"信息");
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
  }
  
  private void doExcport(HttpServletRequest request,String dataModel, List list, HttpServletResponse response,String entityName) {
	  //List<ModelProp> res = dataModelService.getDataModelAttributes(dataModel); 
	  //List<ModelProp> res = dataModelService.getDataModelAttributes(dataModel); 
	 
	  //parser.ignoreSet(true);
	  List<ModelProp> res = getPropParser().parseProp(dataModel);
	  createExcelAndExport(request,response,res,list,entityName);
  }
  
  /**
   * 获取属性解析器
   * @return PropParser 属性解析器
   */
private PropParser getPropParser(){
	  PropParser parser = new PropParser();
	  List<String> propIgnore = new ArrayList<String>();
	  propIgnore.add("id");
	  propIgnore.add("version");
	  propIgnore.add("manageKey");
	  parser.setPropsIgnoreOfComplexObject(propIgnore);
	  return parser;
  }

   /**
    * 根据数据类型类名称获取Service
    * @param modelClassName 类名
    * @return Object service对象
    */
private Object getSerivceByName(String modelClassName){
		  String entityName = modelClassName.substring(modelClassName.lastIndexOf(".")+1);
		  String tmp = modelClassName.substring(0,modelClassName.lastIndexOf("."));
		  tmp = tmp.substring(0,tmp.lastIndexOf("."));
		  String serviceClassName = tmp  +".service.I"+entityName+ "Service";
		  try {
				Class c = Class.forName(serviceClassName);
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+c);
				Object service = ApplicationContextAccessUtilListener.getContext().getBean(c);
				return service;
		   } catch (ClassNotFoundException e) {
				e.printStackTrace();
		   }
	  return null;
  }
  
  
	/**
	 * 导出Excel
	 * @param request HttpServletRequest对象
	 * @param response HttpServletResponse对象
	 * @param res 属性列表
	 * @param list 实体对象列表
	 * @param entityName 实体名
	 */
	public void createExcelAndExport(HttpServletRequest request, HttpServletResponse response,List<ModelProp> res, List list,String entityName) {
		SheetInfo sheetInfo = getSheetInfo(res,request,entityName);
		
		Scaner scaner = new Scaner();
		
		List<String> propTryToDisplay = new ArrayList<String>();
		//propTryToDisplay.add("name");
		//propTryToDisplay.add("userName");
		//propTryToDisplay.add("description");
		
		propTryToDisplay.add("name");
		propTryToDisplay.add("username");
		propTryToDisplay.add("eqNo");
		propTryToDisplay.add("id");
		
		scaner.setTryToDisplayOfComplexObject(propTryToDisplay);
		List<ScanResult> scanResults = scaner.doScan(list, res);
		List<Integer> backgroundChangeData = new ArrayList<Integer>();
		
		List<Map<String, String>> tmpDataMapList = new ArrayList<Map<String,String>>();
		for(ScanResult scanResult : scanResults){
			for(int i = 0;i<scanResult.rowCount();i++){
				backgroundChangeData.add(i==0 ? 1 : 0);
				tmpDataMapList.add(scanResult.readRow(i));
			}
		}
		sheetInfo.setBackgroundChangeData(backgroundChangeData);
	
		
		//从以accessName为key的map中转换到以prop_x的map中
		List<Map<String, String>> dataMapList = new ArrayList<Map<String,String>>();
		Map<String,String> dataMap = null;
		for(Map<String,String> tempDataMap : tmpDataMapList){
			dataMap = new HashMap<String,String>();
			for(ModelProp prop : res){
				String headerName = tryToGetI18NMessage("props."+prop.getClassName()+"." + prop.getName(), request);
				if(!prop.isSet()){
					String propValue = tempDataMap.get(prop.getAccessName());
					dataMap.put(getPropNameByHeaderName(headerName,sheetInfo), propValue);
				}else{
					if(prop.getChildren()!=null){
						for(ModelProp childProp : prop.getChildren()){
							String propValue = tempDataMap.get(childProp.getAccessName());
							dataMap.put(getPropNameByHeaderName(headerName + tryToGetI18NMessage("props."+childProp.getClassName()+"." + childProp.getName(), request),sheetInfo), propValue);
						}
					}
				}
			}
			dataMapList.add(dataMap);
		}
		//sheetInfo.setData(complexObjectDateToMapList(request,list, sheetInfo,res));
		sheetInfo.setData(dataMapList);
		
		List<SheetInfo> exportList = new ArrayList<SheetInfo>();
		exportList.add(sheetInfo);
		ExportPOIUtil.exportExcel(response, exportList, sheetInfo.getSheetName());
		
	}

	/**
	 * 构建SheetInfo数据
	 * @param res 属性列表
	 * @param request HttpServletRequest对象
	 * @param entityName 实体名
	 * @return SheetInfo 对象
	 */
	private SheetInfo getSheetInfo(List<ModelProp> res,HttpServletRequest request,String entityName) {
		StringBuffer title = new StringBuffer(entityName);
		title.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		SheetInfo sheetInfo = new SheetInfo(title.toString());
		int[] widths = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			widths[i]=15;
		}
		int[] types = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			types[i] = HSSFCell.CELL_TYPE_STRING;
		}
		sheetInfo.setHeaders(getHeaders(res, request));
		sheetInfo.setPropertys(createPropsWithHeaders(sheetInfo.getHeaders()));
		sheetInfo.setColwidth(widths);
		sheetInfo.setCellTypes(types);
		sheetInfo.setDefaultCell(sheetInfo.getHeaders().length, HSSFCell.CELL_TYPE_STRING);
		sheetInfo.setDefaultCell(sheetInfo.getHeaders().length, HSSFCell.CELL_TYPE_STRING);
		sheetInfo.setDefaultHeadSytle(sheetInfo.getHeaders().length, SheetInfo.CELL_STYLE_ORANGE);
		sheetInfo.setDefaultOper(sheetInfo.getHeaders().length, SheetInfo.OPER_NO);
		return sheetInfo;
	}

	/**
	 * 构建Excel表头信息
	 * @param res 属性列表
	 * @param request HttpServletRequest对象
	 * @return String[] 标头数组
	 */
	private String[] getHeaders(List<ModelProp> res, HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		String strHeader =null;
		for (ModelProp mp :res) {
			if (!mp.isSet()) {
				strHeader= tryToGetI18NMessage("props."+mp.getPropClassName()+"."+mp.getName(), request);
				if (strHeader!=null && !("").equals(strHeader)) {
					list.add(strHeader);
				}
			}else{
				strHeader= tryToGetI18NMessage(mp.getDescription(), request);
				if(mp.getChildren()!=null){
					for(ModelProp childProp : mp.getChildren()){
						list.add(strHeader + tryToGetI18NMessage("props."+childProp.getPropClassName()+"."+childProp.getName(), request));
					}
				}
			}
		}
		String[] headers = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			headers[i] = list.get(i);
		}
		return headers;
	}
	// 根据headers创建properties
	private String[] createPropsWithHeaders(String[] headers) {
		List<String> props = new ArrayList<String>();
		int i = 0;
		for (String header : headers) {
			props.add("prop_" + (i++));
		}
		String[] res = new String[headers.length];
		i = 0;
		for (Object obj : props.toArray()) {
			res[i++] = (String) obj;
		}
		return res;
	}
	// 根据Header名称获取map中的键名称
	private String getPropNameByHeaderName(String header, SheetInfo sheetInfo){
		int i = 0;
		for (String h : sheetInfo.getHeaders()) {
			if (h.equals(header)) {
				break;
			}
			i++;
		}
		return "prop_" + i;
	}
	/**
	 * 尝试获取国际化信息
	 * @param code 国际化code
	 * @param request HttpServletRequest对象
	 * @return String 获取到的国际化信息
	 */
	private String tryToGetI18NMessage(String code,HttpServletRequest request){
		try{
			return getI18NMessage(request,code);
		}catch(Exception e){
			if(code.endsWith(".id")){
				return "ID";
			}
			System.out.println(code);
		}
		return null;
	}
	
	
	/**
	 * 导入页面
	 * @param request
	 * @param dataModel
	 */
	@RequestMapping(value = "/import",method = RequestMethod.GET)
	public String importUI(HttpServletRequest request,@RequestParam(value = "dataModel")String dataModel){
		Class c = null;
		try {
			c = Class.forName(dataModel);
			request.setAttribute("entityName",getI18NMessage(request, "funcs." + c.getSimpleName() + ".entityName"));
			request.setAttribute("path",StringUtil.lowerCaseFistLatter(c.getSimpleName()));
			request.setAttribute("props", getPropParser().parseProp(dataModel));
			request.setAttribute("dataModel", dataModel);
		} catch (ClassNotFoundException e) {
		}
		return "common/importUI";
	}
	
	/**
	 * 导入Excel
	 * @param request HttpServletRequest对象
	 * @param fileKeys 文件key数组
	 * @param dataModel 数据模型
	 * @param checkRepeatProps 要查重的属性数组
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/import",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doImport(HttpServletRequest request,
			@RequestParam(value = "fileKeys",required = false)String[] fileKeys,
			@RequestParam(value = "dataModel")String dataModel,
			@RequestParam(value = "from")String from,
			@RequestParam(value = "checkRepeatProps",required = false)String[] checkRepeatProps){
		JsonResult jsonResult = new JsonResult();
		if(fileKeys==null || fileKeys.length==0){
			jsonResult.setMessage("请选择文件!");
			jsonResult.setStatusCode("300");
			return jsonResult;
		}
		if(fileKeys.length>1){
			jsonResult.setMessage("一次只能选择一个文件!");
			jsonResult.setStatusCode("300");
			return jsonResult;
		}
		for(String fileKey : fileKeys){
			CommonFile file = fileService.getEntityByProperty(CommonFile.class, "manageKey", fileKey);
			List<ModelProp> props = getPropParser().parseProp(dataModel);
			try {
				//解析Excel文件到Map中
				List<Boolean> changeRecord = new ArrayList<Boolean>();
				List<Map<String,String>> dataMapList = readDataFromExcelFile(file,props,changeRecord);
				
				ValidateResultPackage validateResultPackage = new ValidateResultPackage();
				
				//Excel查重
				if(checkRepeatProps!=null && checkRepeatProps.length>0){
					List<String> checkRepeatPropsList = new ArrayList<String>();
					for(String checkReportProp : checkRepeatProps){
						checkRepeatPropsList.add(checkReportProp);
					}
					dataMapList = checkRepeat(dataMapList,props,checkRepeatPropsList,validateResultPackage);
				}
				//将map数据转换成ScanResult对象集合
				List<ScanResult> scanResultList = dataMapList2ScanResult(dataMapList,props,changeRecord);
				
				//对将ScanResult集合数据转换成数据对象集合,并进行数据库查重
				List dataList = scanResultList2DataList(scanResultList,props,validateResultPackage,dataModel,checkRepeatProps);
				
				request.getSession().setAttribute("recordCount", scanResultList.size());
				request.getSession().setAttribute("dataListForImport", dataList);
				request.getSession().setAttribute("validateResultPackage", validateResultPackage);
				request.getSession().setAttribute("checkRepeatProps", checkRepeatProps);
				request.getSession().setAttribute("props", props);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			fileService.delete(file);
		}
		
		jsonResult.setCallbackType("forward");
		jsonResult.setForwardUrl(from+"/import.do?viewResult=yes&onlyFailed=true&dataModel=" + dataModel);
		jsonResult.setMessage("Excel分析完成！");
		return jsonResult;
	}
	
	/**
	 * Excel导入确认页面（导入验证结果页面）
	 * @param request HttpServletRequest对象
	 * @param dataModel 数据模型
	 * @return String 页面路径
	 */
	@RequestMapping(value = "/import",params = "viewResult")
	public String importValidateResultView(HttpServletRequest request,
			@RequestParam(value = "dataModel")String dataModel,
			@RequestParam(value="onlyFailed",required = false)boolean onlyFailed){
		Class c = null;
		try {
			c = Class.forName(dataModel);
			request.setAttribute("entityName",getI18NMessage(request, "funcs." + c.getSimpleName() + ".entityName"));
			request.setAttribute("path",StringUtil.lowerCaseFistLatter(c.getSimpleName()));
			request.setAttribute("dataModel", dataModel);
			request.setAttribute("onlyFailed", onlyFailed);
		} catch (ClassNotFoundException e) {
		}
		return "common/importConfirm";
	}
	
	/**
	 * 确认导入
	 * @param request HttpServletRequest对象
	 * @param dataModel 数据模型
	 * @return JsonResult 操作结果
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import",params = "doImport")
	@ResponseBody
	public JsonResult importConfirmToImport(HttpServletRequest request,
			@RequestParam(value = "dataModel")String dataModel){
		JsonResult jsonResult = new JsonResult();
		ValidateResultPackage validateResultPackage = (ValidateResultPackage)request.getSession().getAttribute("validateResultPackage");
		if(validateResultPackage==null){
			jsonResult.setStatusCode("300");
			jsonResult.setMessage("没有可导入的数据!");
			return jsonResult;
		}
		
		Class c = null;
		try {
			c = Class.forName(dataModel);
		}catch(Exception e){}
		List<Object> dataList =(List<Object>) request.getSession().getAttribute("dataListForImport");
		IBaseService service = (IBaseService) getSerivceByName(dataModel);
		if(dataList!=null){
			try{
				service.add(dataList);
				request.getSession().removeAttribute("recordCount");
				request.getSession().removeAttribute("dataListForImport");
				request.getSession().removeAttribute("validateResultPackage");
				request.getSession().removeAttribute("checkRepeatProps");
				request.getSession().removeAttribute("props");
				request.getSession().setAttribute("successCount",dataList.size());
				jsonResult.setMessage("导入完成！");
			}catch(Exception e){
				request.getSession().setAttribute("importError",e);
				jsonResult.setMessage("导入完成，但是遇到了错误。");
				e.printStackTrace();
			}
		}
		jsonResult.setCallbackType("forward");
		jsonResult.setForwardUrl(StringUtil.lowerCaseFistLatter(c.getSimpleName()) + "/import.do?importResult=yes&path=" + request.getParameter("path"));
		return jsonResult;
	}
	
	@RequestMapping(value = "/import",params = "importResult")
	public String importResult(HttpServletRequest request){
		request.setAttribute("path", request.getParameter("path"));
		return "common/importResult";
	}
	
	/**
	 * 自动刷新列表页面
	 * @param request HttpServletRequest对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/import",params = "refreshList")
	@ResponseBody
	public JsonResult importRefreshList(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setMessage("操作成功！");
		return jsonResult;
	}
 
	/**
	 * 将ScanResult集合转换成可以保存到数据库的对象集合
	 * @param scanResultList 扫描结果列表
	 * @param props 属性列表
	 * @param validateResultPackage ValidateResultPackage对象
	 * @return List 对象列表 
	 */
	private List scanResultList2DataList(List<ScanResult> scanResultList, List<ModelProp> props,
			ValidateResultPackage validateResultPackage,String dataModel,
			String[] checkRepeatProps) {
		List<Object> resultList = new ArrayList<Object>();
		for(ScanResult scanResult : scanResultList){
			Object result = scanResult2Object(scanResult,props,validateResultPackage,dataModel,checkRepeatProps);
			if(result!=null){
				resultList.add(result);
			}
		}
		return resultList;
	}

	/**
	 * ScanResult对象转换成Object
	 * @param scanResult 扫描结果
	 * @param props 属性列表
	 * @param validateResultPackage ValidateResultPackage对象
	 * @param dataModel 实体名称
	 * @return Object 转换的对象
	 */
	private Object scanResult2Object(ScanResult scanResult, List<ModelProp> props, 
			ValidateResultPackage validateResultPackage, String dataModel,
			String[] checkRepeatProps) {
		Class c = null;
		try {
			c = Class.forName(dataModel);
		} catch (ClassNotFoundException e) {
			log.info("创建类" + dataModel + "失败：" + e.getMessage());
			return null;
		};
		Object dataObject = null;
		try {
			dataObject = c.newInstance();
		} catch (Exception e) {
			log.info("class " + c.getCanonicalName() + ".newInstance() 出错:" + e.getMessage());
			return null;
		}
		
		try{
			//为Object对象赋值
			for(ModelProp prop : props){
				if(!prop.isSet()){
					String strValue = (String)scanResult.getData().get(prop.getAccessName());
					Object targetObject = strToObject(strValue,prop,validateResultPackage,scanResult.getExcelRowNum(),0);
					if(targetObject!=null){
						BeanPropUtil.writeProp(dataObject, prop.getName(), targetObject);
					}
				}else{
					//Set数据
					Set<Object> setDatas = new LinkedHashSet<Object>();
					List<Map<String,String>> setDataMapList = (List<Map<String,String>>)scanResult.getData().get(prop.getAccessName());
					if(setDataMapList==null || setDataMapList.size()==0){
						continue;
					}
					
					int rowOffset = 0;
					Class setDataClass = Class.forName(prop.getPropClassName());
					for(Map<String,String> setDataMap : setDataMapList){
						
						Object setData = setDataClass.newInstance();
						
						for(ModelProp childProp : prop.getChildren()){
							if(childProp.getName().equals(prop.getMappedBy())){//忽略一对多关联多方关联一方字段赋值，该赋值由其他代码完成
								continue;
							}
							String strValue = setDataMap.get(childProp.getAccessName());
							Object targetObject = strToObject(strValue,childProp,validateResultPackage,scanResult.getExcelRowNum(),rowOffset);
							if(targetObject!=null){
								BeanPropUtil.writeProp(setData, childProp.getName(), targetObject);
							}
						}
						
						//为一对多的Set元素设置其关联字段为当前主记录对象
						if(prop.getType()==ModelPropType.ONE2MANY){
							BeanPropUtil.writeProp(setData, prop.getMappedBy(), dataObject);
						}					
						rowOffset++;
						
						//为Set元素设置ManageKey
						if(BeanPropUtil.hasProp(setDataClass, "manageKey")){
							BeanPropUtil.writeProp(setData, "manageKey", Md5Util.getManageKey());
						}
						
						setDatas.add(setData);
					}
					
					/*if(setDataClass == Unit.class){
						//List<Unit> existedUnitList = new ArrayList<Unit>();
						for(Object obj : setDatas){
							Unit u = (Unit)obj;
							List<Unit> units = unitService.getByProperties(Unit.class, new String[]{"rack","serialNumber"}, new Object[]{u.getRack(),u.getSerialNumber()}, null, null);
							if(units!=null && units.size()>0){
								Unit existedUnit = units.get(0);
								u.setId(existedUnit.getId());
								//existedUnitList.add(u);
							}
						}
					} */
					
					BeanPropUtil.writeProp(dataObject, prop.getName(), setDatas);
				}
			}
			
			//为主记录数据设置ManageKey
			if(BeanPropUtil.hasProp(c, "manageKey")){
				BeanPropUtil.writeProp(dataObject, "manageKey", Md5Util.getManageKey());
			}
			if(BeanPropUtil.hasProp(c, "maintainPerson") && BeanPropUtil.readProp(dataObject, "maintainPerson")==null){
				BeanPropUtil.writeProp(dataObject, "maintainPerson", getCurrentUser());
			}
		}catch(Exception e){
			e.printStackTrace();
			validateResultPackage.addValidateResult(scanResult.getExcelRowNum(), new ValidateResult(scanResult.getExcelRowNum(),null,null,ValidateType.PROP_VALIDATE,false,"尝试给对象赋值时出错：" + e.getMessage()));
		}
		
		//数据库查重(只有在这里进行合适，因为其他地方没有scanResult信息了)
		if(checkRepeatProps!=null && checkRepeatProps.length>0){
			IBaseService baseService = (IBaseService)getSerivceByName(dataModel);
			Object[] values = new Object[checkRepeatProps.length];
			try {
				int i = 0;
				for(String checkRepeatProp : checkRepeatProps){
						values[i++] = BeanPropUtil.readProp(dataObject, checkRepeatProp);
				}
				List list = baseService.getByProperties(c, checkRepeatProps, values, null, null);
				if(list.size()>0){
					validateResultPackage.addValidateResult(scanResult.getExcelRowNum(), new ValidateResult(scanResult.getExcelRowNum(),null,null,ValidateType.DB_REPEAT,false,"数据库重复，该行数据将被忽略!"));
					return null;
				}
			} catch (NoSuchFieldException e) {
				validateResultPackage.addValidateResult(scanResult.getExcelRowNum(), new ValidateResult(scanResult.getExcelRowNum(),null,null,ValidateType.DB_REPEAT,false,"尝试进行数据库重复验证时，无法从对象中读取数据：" + e.getMessage()));
				return null;
			}
		}
		return dataObject;
	}

	
	/**
	 * 将字符串转换为目标类型
	 * @param strValue 字符串值
	 * @param targetType 想要转换成的类型
	 * @param validateResultPackage 验证结果包
	 * @param rowIndex 行号
	 * @param modelProp 字段
	 * @return Object 字符串转换成的对象
	 */
	private Object strToObject(String strValue,ModelProp modelProp,
			ValidateResultPackage validateResultPackage,Integer rowIndex,int offSetRowIndex
			){
		if(modelProp.getTypeDesc().equals("java.lang.String")){
			validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
			return strValue;
		}
		
		if(strValue==null || strValue.trim().equals("")){
			validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功（空内容）"));
			return null;
		}
		
		if(modelProp.getTypeDesc().equals("java.lang.Integer")){
			try{
				Integer res = Integer.parseInt(strValue);
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
				return res;
			}catch(Exception e){
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"无法识别的整数值:" + strValue));
			}
			return null;
		}
		
		if(modelProp.getTypeDesc().equals("java.lang.Float")){
			try{
				Float res = Float.parseFloat(strValue);
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
				return res;
			}catch(Exception e){
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"无法识别的小数值:" + strValue));
			}
			return null;
		}
		
		if(modelProp.getTypeDesc().equals("java.lang.Double")){
			try{
				Double res =  Double.parseDouble(strValue);
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
				return res;
			}catch(Exception e){
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"无法识别的数值:" + strValue));
			}
			return null;
		}
		if(modelProp.getTypeDesc().equals("java.util.Date")){
			Date date =  DateUtil.parseDate(strValue);
			if(date==null){
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"无法识别日期:" + strValue));
			}else{
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
			}
			return date;
		}
		
		if(modelProp.getType()==ModelPropType.ENUM){
			try {
				Object enumVal = strToEnum(modelProp.getTypeDesc(),strValue);
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
				return enumVal;
			} catch (Exception e) {
				validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"无法识别的枚举值:" + strValue + "("+modelProp.getTypeDesc()+")"));
			}
			return null;
		}
		
		//剩下的被认为是复杂类型
		Object res = null;
		try {
			Class entityClass = Class.forName(modelProp.getTypeDesc());
			IBaseService baseService = (IBaseService)getSerivceByName(modelProp.getTypeDesc());
			res = baseService.queryByExcelCellValue(entityClass, strValue);
		} catch (Exception e) {
			validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"尝试将值:" + strValue + "转换为复杂对象("+modelProp.getTypeDesc()+")时出错：" + e.getMessage()));
			return null;
		}
		if(res==null){
			validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,false,"没有找到值为\""+ strValue+"\"的数据"));
		}else{
			validateResultPackage.addValidateResult(rowIndex, new ValidateResult(rowIndex + offSetRowIndex,modelProp.getDescription(),strValue,ValidateType.PROP_VALIDATE,true,"验证成功"));
		}
		return res;
		
	}
	
	
	/**
	 * 字符串值转换成枚举类型
	 * @param enumClassName 枚举名称
	 * @param strValue 要转换的字符串
	 * @return Object 枚举对象
	 * @throws Exception 异常
	 */
	private Object strToEnum(String enumClassName,String strValue) throws Exception{
		Class enumClass = Class.forName( enumClassName );
		Object[] enums = enumClass.getEnumConstants();
		if(enums!=null){
			for(Object e : enums){
				String enumDesc = (String)BeanPropUtil.readProp(e, "description");//这里认为关联的枚举类型肯定有这个字段
				if(enumDesc!=null && enumDesc.trim().equals(strValue)){
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 * Excel查重
	 * @param dataMapList 
	 * @param props
	 * @param changeRecord
	 * @param checkRepeatProps 
	 * @param validateResult
	 * @return List<Map<String, String>> 
	 */
	private List<Map<String, String>> checkRepeat(List<Map<String, String>> dataMapList, List<ModelProp> props, 
			List<String> checkRepeatProps, ValidateResultPackage validateResultPackage) {
		
		List<Map<String, String>> resultDataMap = new ArrayList<Map<String,String>>();
		Map<String,Integer> repeatCheckPropCombineKeyMap = new HashMap<String,Integer>();
		
		int rowIndex = 2;
		boolean repeatCheckSuccess = false;
		for(Map<String,String> dataMap : dataMapList){
			String mainRecordKey = "";
			for(ModelProp prop : props){
				if(!prop.isSet()){
					if(checkRepeatProps.contains(prop.getAccessName())){//包含在查重字段中
						mainRecordKey += dataMap.get(prop.getAccessName());
					}
				}
			}
			if(!mainRecordKey.trim().equals("")){//说明是新的数据行，而不是一对多数据行
				Integer cachedData = repeatCheckPropCombineKeyMap.get(mainRecordKey);
				boolean repeat = cachedData!=null;
				String validateRemark = "Excel重复验证成功";
				if(repeat){
					validateRemark = "与" + cachedData + "行重复,该行数据将被忽略！";
				}
				ValidateResult validateResult = new ValidateResult(rowIndex,null,null,ValidateType.EXCEL_REPEAT,!repeat,validateRemark);
				validateResultPackage.addValidateResult(rowIndex, validateResult);
				if(!repeat){//没有重复
					repeatCheckPropCombineKeyMap.put(mainRecordKey,rowIndex);
					repeatCheckSuccess = true;
				}
			}
			if(repeatCheckSuccess){
				resultDataMap.add(dataMap);
			}
			rowIndex++;
		}
		
		return resultDataMap;
	}

	/**
	 * 将map数据转换成ScanResult对象集合
	 * @param dataMapList
	 * @param props
	 * @param changeRecord
	 * @return   List<ScanResult> 扫描结果列表
	 */
	private List<ScanResult> dataMapList2ScanResult(List<Map<String, String>> dataMapList, List<ModelProp> props, List<Boolean> changeRecord) {
		List<ScanResult> scanResultList = new ArrayList<ScanResult>();
		int index = 0;
		ScanResult scanResult = null;
		for(Map<String,String> dataMap : dataMapList){
			if(changeRecord.get(index)){//根据changeRecord知道何时应该新建一个行对象了
				scanResult = new ScanResult();
				scanResult.setExcelRowNum(index+2);
				scanResultList.add(scanResult);
			}
			for(ModelProp prop : props){
				if(!prop.isSet() && changeRecord.get(index)){
					scanResult.getData().put(prop.getAccessName(), dataMap.get(prop.getAccessName()));
				}else if(prop.isSet()){ 
					List<Map<String,String>> childPropMapList = (List<Map<String,String>>)scanResult.getData().get(prop.getAccessName());
					if(childPropMapList==null){
						childPropMapList = new ArrayList<Map<String,String>>();
					}
					Map<String,String> childPropMap = new HashMap<String,String>();
					String childPropValSum = "";
					for(ModelProp childProp : prop.getChildren()){
						childPropValSum += dataMap.get(childProp.getAccessName());
						childPropMap.put(childProp.getAccessName(),dataMap.get(childProp.getAccessName()));
					}
					if(!childPropValSum.equals("")){//说明确实存在该行，而不是其他Set集合太大而导致的本Set集合的空白行
						childPropMapList.add(childPropMap);
					}
					scanResult.getData().put(prop.getAccessName(),childPropMapList);
					scanResult.setRowCountIfBigThan(childPropMapList.size());
				}
			}
			index++;
		}
		return scanResultList;
	}

	/**
	 * 解析Excel文件生成为Map形式
	 * @param file
	 * @param props
	 * @return List<Map<String, String>>
	 */
	private List<Map<String, String>> readDataFromExcelFile(CommonFile file, List<ModelProp> props,List<Boolean> changeRecord)throws Exception {
		 InputStream is = new FileInputStream(file.getFullName());
		 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		 
		 List<Map<String, String>> dataMapList = new ArrayList<Map<String,String>>();
		 
		 for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // 循环行Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                Map<String,String> dataMap = new HashMap<String,String>();
	                int colIndex = 0;
	                String rowColSum = "";
	                for(ModelProp prop : props){
	                	if(!prop.isSet()){
	                		String value = getCellValue(hssfRow,colIndex);
	                		rowColSum += value.trim();
	                		dataMap.put(prop.getAccessName(), value);
	                		colIndex++;
	                	}else{
	                		if(prop.getChildren()!=null){
	                			for(ModelProp childProp : prop.getChildren()){
	                				dataMap.put(childProp.getAccessName(), getCellValue(hssfRow,colIndex));
	                				colIndex++;
	                			}
	                		}
	                	}
	                }
	                changeRecord.add(!rowColSum.equals(""));
	                dataMapList.add(dataMap);
	            }
		 }
		 try{
			 is.close();
		 }catch(Exception e){}
		return dataMapList;
	}
	
	private String getCellValue(HSSFRow hssfRow,int colIndex){
		HSSFCell xh = hssfRow.getCell(colIndex);
		if(xh!=null){
			xh.setCellType(Cell.CELL_TYPE_STRING);
		}
		return xh==null ? "" : xh.getStringCellValue();
	}

	@RequestMapping(value = "/templateExport",method = RequestMethod.GET)
	public String importTemplate(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "dataModel") String dataModel){
		Class c = null;
		try {
			c = Class.forName(dataModel);
			String entityName = getI18NMessage(request, "funcs." + c.getSimpleName() + ".entityName");
			doExcport(request,dataModel,Collections.EMPTY_LIST,response,entityName + "导入模板");
		} catch (ClassNotFoundException e) {
		}
		return null;
	}
	
	@Resource
	private ICommonFileService commonFileService;
	
	 protected CommonFile parseFilee(HttpServletRequest request,String keyName,String oldKey) {
	    	String[] coverKeys = request.getParameterValues(keyName);
	    	String coverKey = null;
	    	if(coverKeys!=null && coverKeys.length>0){
	    		coverKey = coverKeys[0];
	    	}
	    	CommonFile file = null;
	    	if(StringUtil.isNotEmpty(coverKey)){
	    		file = commonFileService.getEntityByProperty(CommonFile.class, "manageKey", coverKey);
	    		if(file!=null){
	    			file.setInuse(UseStatus.InUse);
	    			commonFileService.update(file);
	    		}
	    	}
	    	if(StringUtil.isNotEmpty(oldKey) && !oldKey.equals(coverKey)){
	    		CommonFile oldFile = commonFileService.getEntityByProperty(CommonFile.class, "manageKey", oldKey);
	    		if(oldFile!=null){
	    			oldFile.setInuse(UseStatus.UnUse);
	    			commonFileService.update(oldFile);
	    		}
	    	}
	    	return file;
	}
	 
		protected void markFileAsInuse(Collection<CommonFile> filees){
			if(filees!=null){
				for(CommonFile filee : filees){
					if(filee==null) continue;
					filee.setInuse(UseStatus.InUse);
					commonFileService.update(filee);
				}
			}
		}
		protected void markFileAsUnuse(Collection<CommonFile> filees){
			if(filees!=null){
				for(CommonFile filee : filees){
					if(filee==null) continue;
					filee.setInuse(UseStatus.UnUse);
					commonFileService.update(filee);
				}
			}
		}
		protected void markFileAsUnuse(CommonFile filee){
				if(filee!=null){
					filee.setInuse(UseStatus.UnUse);
					commonFileService.update(filee);
				}
		}
	
	
}

