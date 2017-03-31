package me.huqiao.wss.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.sys.entity.Config;
import me.huqiao.wss.sys.service.IConfigService;
import me.huqiao.wss.sys.service.IRoleService;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 配置控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("config")
public class ConfigController extends BaseController {

	@Resource
	private IConfigService configService;
	@Resource
	private IRoleService roleService;
	/**
	 * 注册属性编辑器
	 * @param binder 数据绑定器
	 */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		// 注册属性编辑器
		//binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));
	}
	  /**
		  * 初始化ModelAttribute
		  * @param id  唯一识别主键值
		  * @return CommonFolder 文件夹对象
		  */
	@ModelAttribute
	public Config initModelAttribute(@RequestParam(value = "id", required = false) Integer id) {
		Config config = null;
		if (id == null || id.equals("")) {
			config = new Config();
		} else {
			config = configService.getById(Config.class, id);
		}
		return config;
	}

	/**
	 * 配置列表
	 * @param request HttpServletRequest对象
	 * @param Config 配置对象
	 * @param pageInfo 分页查询对象
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, Config Config, Page pageInfo) {
		// findAndSaveParams(request);
	}

	/**
	 * 日程邮件配置
	 * @param rel 当前页面与href所指定文档的关系
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/articleConfig", method = RequestMethod.GET)
	public void lessonConfigUI(@RequestParam("rel") String rel,HttpServletRequest request) {
	}
	/**
	 * 日程邮件保存
	 * @param rel 当前页面与href所指定文档的关系
	 * @param request HttpServletRequest对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/articleConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult lessonConfig(@RequestParam("rel") String rel,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}
	/**
	 * 系统参数配置
	 * @param rel  当前页面与href所指定文档的关系
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/systemConfig", method = RequestMethod.GET)
	public void systemConfigUI(@RequestParam("rel") String rel,HttpServletRequest request) {
		Config osInstall = configService.getById(Config.class, Config.DEPLOY_CONFIG_OS_INSTALL_URL);
		Config appInstall = configService.getById(Config.class, Config.DEPLOY_CONFIG_APP_INSTALL_URL);
		Config configChange = configService.getById(Config.class, Config.DEPLOY_CONFIG_CHANGE_CONFIG_URL);
		Config templateAnalysis = configService.getById(Config.class, Config.DEPLOY_CONFIG_TEMPLATE_ANALYSIS_URL);
		Config fileAnalysis = configService.getById(Config.class, Config.DEPLOY_CONFIG_FILE_ANALYSIS_URL);
		Config remoteFilesRoot = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_FILE_ROOT_DIR);
		Config remoteFileSyncUrl = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_FILE_SYNC_URL);
		Config newLocalFileNoticeUrl = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_NEW_LOCAL_FILE_NOTICE);
		Config systemBasePathCfg = configService.getById(Config.class, Config.DEPLOY_CONFIG_SYS_BASE_PATH);
		Config nmsApiAccount = configService.getById(Config.class, Config.ALARM_DATA_QUERY_ACCOUNT);
		Config nmsApiPwd = configService.getById(Config.class, Config.ALARM_DATA_QUERY_PWD);
		Config monitorQuery = configService.getById(Config.class, Config.MONITOR_DATA_QUERY_URL);
		Config alarmQuery = configService.getById(Config.class, Config.ALARM_DATA_QUERY_URL);
		Config alarmConfirm = configService.getById(Config.class, Config.ALARM_DATA_COMFIRM_URL);
		Config alarmIsolated = configService.getById(Config.class, Config.ALARM_DATA_ISOLATED_URL);
		Config cocoaUrl = configService.getById(Config.class, Config.COCOA_URL);
		Config cocoaUsername = configService.getById(Config.class, Config.COCOA_USERNAME);
		Config cocoaPassword = configService.getById(Config.class, Config.COCOA_PASSWORD);
		request.setAttribute("osInstall", osInstall);
		request.setAttribute("appInstall", appInstall);
		request.setAttribute("configChange", configChange);
		request.setAttribute("templateAnalysis", templateAnalysis);
		request.setAttribute("fileAnalysis", fileAnalysis);
		request.setAttribute("remoteFilesRoot", remoteFilesRoot);
		request.setAttribute("remoteFileSyncUrl", remoteFileSyncUrl);
		request.setAttribute("newLocalFileNoticeUrl", newLocalFileNoticeUrl);
		request.setAttribute("systemBasePathCfg", systemBasePathCfg);
		request.setAttribute("nmsApiAccount", nmsApiAccount);
		request.setAttribute("nmsApiPwd", nmsApiPwd);
		request.setAttribute("monitorQuery", monitorQuery);
		System.out.println(monitorQuery.getName());
		request.setAttribute("alarmQuery", alarmQuery);
		request.setAttribute("alarmConfirm", alarmConfirm);
		request.setAttribute("alarmIsolated", alarmIsolated);
		request.setAttribute("cocoaUrl", cocoaUrl);
		request.setAttribute("cocoaUsername", cocoaUsername);
		request.setAttribute("cocoaPassword", cocoaPassword);
		
	}
	/**
	 * 系统参数配置保存
	 * @param rel 当前页面与href所指定文档的关系
	 * @param request HttpServletRequest对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/systemConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult systemConfig(@RequestParam(value="rel",required =false) String rel,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Config osInstall = configService.getById(Config.class, Config.DEPLOY_CONFIG_OS_INSTALL_URL);
		Config appInstall = configService.getById(Config.class, Config.DEPLOY_CONFIG_APP_INSTALL_URL);
		Config configChange = configService.getById(Config.class, Config.DEPLOY_CONFIG_CHANGE_CONFIG_URL);
		Config templateAnalysis = configService.getById(Config.class, Config.DEPLOY_CONFIG_TEMPLATE_ANALYSIS_URL);
		Config fileAnalysis = configService.getById(Config.class, Config.DEPLOY_CONFIG_FILE_ANALYSIS_URL);
		Config remoteFilesRoot = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_FILE_ROOT_DIR);
		Config remoteFileSyncUrl = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_FILE_SYNC_URL);
		Config newLocalFileNoticeUrl = configService.getById(Config.class, Config.DEPLOY_CONFIG_REMOTE_NEW_LOCAL_FILE_NOTICE);
		Config systemBasePathCfg = configService.getById(Config.class, Config.DEPLOY_CONFIG_SYS_BASE_PATH);
		
		Config monitorQuery = configService.getById(Config.class, Config.MONITOR_DATA_QUERY_URL);
		Config nmsApiAccount = configService.getById(Config.class, Config.ALARM_DATA_QUERY_ACCOUNT);
		Config nmsApiPwd = configService.getById(Config.class, Config.ALARM_DATA_QUERY_PWD);
		
		Config alarmQuery = configService.getById(Config.class, Config.ALARM_DATA_QUERY_URL);
		Config alarmConfirm = configService.getById(Config.class, Config.ALARM_DATA_COMFIRM_URL);
		Config alarmIsolated = configService.getById(Config.class, Config.ALARM_DATA_ISOLATED_URL);
		Config cocoaUrl = configService.getById(Config.class, Config.COCOA_URL);
		Config cocoaUsername = configService.getById(Config.class, Config.COCOA_USERNAME);
		Config cocoaPassword = configService.getById(Config.class, Config.COCOA_PASSWORD);
		
		osInstall.setName(request.getParameter("osInstall"));
		appInstall.setName(request.getParameter("appInstall"));
		configChange.setName(request.getParameter("configChange"));
		templateAnalysis.setName(request.getParameter("templateAnalysis"));
		fileAnalysis.setName(request.getParameter("fileAnalysis"));
		remoteFilesRoot.setName(request.getParameter("remoteFilesRoot"));
		remoteFileSyncUrl.setName(request.getParameter("remoteFileSyncUrl"));
		newLocalFileNoticeUrl.setName(request.getParameter("newLocalFileNoticeUrl"));
		systemBasePathCfg.setName(request.getParameter("systemBasePathCfg"));
		
		nmsApiAccount.setName(request.getParameter("nmsApiAccount"));
		nmsApiPwd.setName(request.getParameter("nmsApiPwd"));
		monitorQuery.setName(request.getParameter("monitorQuery"));
		alarmQuery.setName(request.getParameter("alarmQuery"));
		alarmConfirm.setName(request.getParameter("alarmConfirm"));
		alarmIsolated.setName(request.getParameter("alarmIsolated"));
		cocoaUrl.setName(request.getParameter("cocoaUrl"));
		cocoaUsername.setName(request.getParameter("cocoaUsername"));
		cocoaPassword.setName(request.getParameter("cocoaPassword"));
		
		configService.update(osInstall);
		configService.update(appInstall);
		configService.update(configChange);
		configService.update(templateAnalysis);
		configService.update(fileAnalysis);
		configService.update(remoteFilesRoot);
		configService.update(remoteFileSyncUrl);
		configService.update(newLocalFileNoticeUrl);
		configService.update(systemBasePathCfg);
		
		configService.update(nmsApiAccount);
		configService.update(nmsApiPwd);
		configService.update(monitorQuery);
		configService.update(alarmQuery);
		configService.update(alarmConfirm);
		configService.update(alarmIsolated);
		configService.update(cocoaUrl);
		configService.update(cocoaUsername);
		configService.update(cocoaPassword);
		
		jsonResult.setCallbackType("none");
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}
	
	/**
	 * 指纹考勤服务器配置表单
	 * @param rel 当前页面与href所指定文档的关系
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/attendanceServerConfig", method = RequestMethod.GET)
	public void attendanceServerConfigUI(@RequestParam("rel") String rel,HttpServletRequest request) {
		//Config cfg1 = configService.getById(Config.class,Config.SYS_CONFIG_FINGERPRINT_ATTENDANCE_SERVER_IP );
		//request.setAttribute("cfg1", cfg1);
	}

}