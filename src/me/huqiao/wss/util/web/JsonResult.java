package me.huqiao.wss.util.web;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON格式操作结果
 * @author NOVOTS
 * @version Version 1.0
 */
public class JsonResult {

	/** 正常 */
	public static final String STATUS_CODE_OK = "200";
	/** 错误 */
	public static final String STATUS_CODE_ERROR = "300";
	/** 超时 */
	public static final String STATUS_CODE_TIMEOUT = "301";
	/** 未登录 */
	public static final String STATUS_CODE_NO_LOGIN = "1000";
	/** 无权限访问 */
	public static final String STATUS_CODE_NO_PERMISSION = "1001";
	/** 登录页面 */
	public static final String LOGIN_UI_URL = "loginUI.do";
	/** 状态码 */
	private String statusCode = "200";
	/** 返回文本信息 */
	private String message = "操作成功";

	/** 下次切换到那个navTab时会重新载入内容 */
	private String navTabId = "dlg_page";
	/** 前页面与href所指定文档的关系 */
	private String rel = "";
	/** 关闭当前tab */
	private String callbackType = "closeCurrent";
	/** callbackType="forward"时需要forwardUrl值 */
	private String forwardUrl = "";
	/** 确认信息 */
	private String confirmMsg = "";
	/** 目标panel */
	private String targetPanel = "page";

	public JsonResult() {
	}

	/**
	 * <p>
	 * Title:jsonResult构造方法
	 * </p>
	 * <p>
	 * Description:jsonResult 设置提示信息的构造方法
	 * </p>
	 * 
	 * @param message
	 *            要设置的提示信息
	 */
	public JsonResult(String message) {
		this.message = message;
	}

	/**
	 * @return String 状态码
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            要设置的状态码
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return String 提示信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message  要设置的提示信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return String 导航标签ID
	 */
	public String getNavTabId() {
		return navTabId;
	}

	/**
	 * @param navTabId 要设置的导航标签ID
	 */
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	/**
	 * @return String 前页面与href所指定文档的关系
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel 要设置 前页面与href所指定文档的关系
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return String 回调方式
	 */
	public String getCallbackType() {
		return callbackType;
	}

	/**
	 * @param callbackType
	 *            设置回调方式
	 */
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	/**
	 * @return String 跳转的路径
	 */
	public String getForwardUrl() {
		return forwardUrl;
	}

	/**
	 * @param forwardUrl
	 *            设置要跳转的路径
	 */
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	/**
	 * @return String 确认信息
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}

	/**
	 * @param confirmMsg
	 *            要设置的确认信息
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	/**
	 * 返回jsonResult结果
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param statusCode
	 *            状态码
	 * @param forwardUrl
	 *            要跳转的路径
	 * @param message
	 *            显示信息
	 * @throws Exception
	 *             异常
	 */
	public static void outputJsonResult(HttpServletResponse response, String statusCode, String forwardUrl, String message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(statusCode);
		jsonResult.setForwardUrl(forwardUrl);
		jsonResult.setMessage(message);
		jsonGenerator.writeObject(jsonResult);
	}

	/**
	 * @return String 目标panel
	 */
	public String getTargetPanel() {
		return targetPanel;
	}

	/**
	 * @param targetPanel 要设置的目标panel
	 */
	public void setTargetPanel(String targetPanel) {
		this.targetPanel = targetPanel;
	}
	
	public static JsonResult error(String error){
		JsonResult res = new JsonResult();
		res.setStatusCode(STATUS_CODE_ERROR);
		res.setMessage(error);
		return res;
	}
	
	public static JsonResult success(String error){
		JsonResult res = new JsonResult();
		res.setStatusCode(STATUS_CODE_OK);
		res.setMessage(error);
		return res;
	}

}
