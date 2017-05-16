package me.huqiao.wss.chapter.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.entity.propertyeditor.TaskEditor;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 采集结果控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("gatherResult")
public class GatherResultController  extends BaseController {
   /**采集结果服务*/
    @Resource
    private IGatherResultService gatherResultService;
 /**
  * 注册属性编辑器
  * @param binder 数据绑定器
  */
    @InitBinder
	public void initPropEditor(WebDataBinder binder){
         binder.registerCustomEditor(Task.class,new TaskEditor(taskService));
	}
    //复杂关联关系的Service
@Resource private ITaskService taskService;
		/**
		  * 初始化ModelAttribute
		  * @param manageKey md5管理ID （非空时自动加载指定对象）
		  * @param model 页面model对象
		  * @return GatherResult 采集结果对象
		  */
		@ModelAttribute(value="gatherResult")
		public GatherResult initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		GatherResult gatherResult = null;
		if (manageKey == null ||manageKey.equals("")) {
			gatherResult = new GatherResult();
		} else {
			gatherResult =  gatherResultService.getEntityByProperty(GatherResult.class,"manageKey", manageKey);
			if (gatherResult==null) {
				gatherResult=new GatherResult();
			}
		}
		return gatherResult;
	}
    /**
     * 采集结果分页查询
     * @param request HttpServletRequest对象
     * @param gatherResult 采集结果查询对象
     * @param pageInfo 分页查询对象
     * 
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,GatherResult gatherResult,Page pageInfo) {
        Page<GatherResult> gatherResultPage = gatherResultService.getListPage(gatherResult,pageInfo);
        request.setAttribute("pageBean", gatherResultPage);
		listFormParam(request,gatherResult,pageInfo);
    }
 	/**
     * 为采集结果分页查询表单准备数据
     * @param request HttpServletRequest对象
     * @param gatherResult 采集结果查询对象
     * @param pageInfo 分页查询对象
     * 
     */
	public void listFormParam(HttpServletRequest request,GatherResult gatherResult,Page pageInfo){
		//复杂关联关系数据准备
					List<Task> taskList = taskService.getByProperties(Task.class,null,null,null,null);
	request.setAttribute("taskList",taskList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	}
    /**
     * 添加采集结果页面
     * @param request HttpServletRequest对象
     * @param callBack  回调函数名称
     *
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request,@RequestParam(value = "callBack",required = false)String callBack) {
    	//复杂关联关系数据准备
					List<Task> taskList = taskService.getByProperties(Task.class,null,null,null,null);
	request.setAttribute("taskList",taskList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
		//clearTempDataList(request.getSession(),"gatherResult");
		request.setAttribute("callBack", callBack);
    }
    /**
     * 添加采集结果
     * @param request HttpServletRequest对象
     * @param gatherResult 要添加的对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(HttpServletRequest request,
	@Valid @ModelAttribute("gatherResult") GatherResult gatherResult,
	@RequestParam(value = "callBack",required = false)String callBack,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	//默认系统时间类型保存
	/*
		#ONE_TO_MANY_VALUE_SAVE_ADD
	*/
	    //保存多对多关联关系
	//保持一对多关联关系
	gatherResult.setManageKey(Md5Util.getManageKey());
    	gatherResultService.add(gatherResult);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
        return jsonResult;
    }
    /**
     * 修改采集结果页面
     * @param gatherResult 需要修改的对象实体
     * @param request HttpServletRequest请求对象
     * 
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
	public void updateUI(@ModelAttribute(value="gatherResult") GatherResult gatherResult,HttpServletRequest request) {
	request.setAttribute("tempBean", gatherResult);
    	//复杂关联关系数据准备
					List<Task> taskList = taskService.getByProperties(Task.class,null,null,null,null);
	request.setAttribute("taskList",taskList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	//clearTempDataList(request.getSession(),"gatherResult");
    }
    /**
     *  修改采集结果 
     * @param gatherResult 待修改的实体对象
     * @param request HttpServletRequest对象
     * @return JsonResult 操作结果
     *
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(HttpServletRequest request,
	@ModelAttribute(value="gatherResult") GatherResult gatherResult,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
	    //保存多对多关联关系
		//保持一对多关联关系
        gatherResultService.update(gatherResult);
	// jsonResult.setNavTabId(rel);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
	/**
	 *  查看采集结果页面
     * @param gatherResult 需要查看的实体对象
     * @param request HttpServletRequest对象
     * 
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(@ModelAttribute(value="gatherResult") GatherResult gatherResult,HttpServletRequest request) {
	request.setAttribute("tempBean", gatherResult);
    	//复杂关联关系数据准备
        listFormParam(request,gatherResult,null);
    }
    
    
    @RequestMapping(value="/view",method=RequestMethod.GET)
    public String view(@ModelAttribute(value="gatherResult") GatherResult gatherResult,HttpServletRequest request){
    	gatherResult.setStatus(UseStatus.UnUse);
    	gatherResultService.update(gatherResult);
    	return "redirect:" + gatherResult.getAccessUrl();
    }
    
    
    @RequestMapping(value="/mark",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult mark(HttpServletRequest request,
	@ModelAttribute(value="gatherResult") GatherResult gatherResult,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
        gatherResultService.update(gatherResult);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
    
	/**
     * 删除单个采集结果对象
     * @param request HttpServletRequest对象
     * @param gatherResult 待删除对象
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(HttpServletRequest request,@ModelAttribute GatherResult gatherResult) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        try {
        	gatherResultService.delete(gatherResult);
		} catch (RuntimeException re) {
			jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.inuse"));
			return jsonResult;
		}catch (Exception e) {
			jsonResult.setMessage(e.toString());
			return jsonResult;
		}
	//jsonResult.setNavTabId(rel);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.success"));
        return jsonResult;
    }
    /**
     * 删除多个对象
     * @param manageKeys 唯一识别ID数组
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
	public JsonResult batchDelete(HttpServletRequest request,@RequestParam("manageKeys") String[] manageKeys) {
    	GatherResult gatherResult = null;
		JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
			 try {
    			gatherResult = gatherResultService.getEntityByProperty(GatherResult.class,"manageKey",manageKey);
    			gatherResultService.delete(gatherResult);
			}catch (RuntimeException re) {
				jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.inuse"));
				return jsonResult;
			}catch (Exception e) {
				jsonResult.setMessage(e.toString());
				return jsonResult;
			}
    	}
		jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.success"));
    	return jsonResult;
    } 
    @RequestMapping(value="/markAsRead",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult markAsRead(HttpServletRequest request,@RequestParam("manageKeys") String[] manageKeys) {
    	GatherResult gatherResult = null;
    	JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
    		try {
    			gatherResult = gatherResultService.getEntityByProperty(GatherResult.class,"manageKey",manageKey);
    			gatherResult.setStatus(UseStatus.UnUse);
    			gatherResultService.update(gatherResult);
    		}catch (RuntimeException re) {
    			jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.inuse"));
    			return jsonResult;
    		}catch (Exception e) {
    			jsonResult.setMessage(e.toString());
    			return jsonResult;
    		}
    	}
    	jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.success"));
    	return jsonResult;
    } 
	 /**
	  *选择对象返回html
      *@param request HttpServletRequest对象
	  *@param manageKeys manageKey 数组
	  *@return String 返回jsp页面路径
      */
	@RequestMapping(value = "/selectList",params = "htmlType")
	public String selectListWithHtml(HttpServletRequest request,
			@RequestParam(value = "manageKey",required = false)String[] manageKeys
			){
		List<GatherResult> gatherResults = new ArrayList<GatherResult>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				GatherResult temp_gatherResult = gatherResultService.getEntityByProperty(GatherResult.class, "manageKey", manageKey);
				if(temp_gatherResult!=null && !gatherResults.contains(temp_gatherResult)){
					gatherResults.add(temp_gatherResult);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("gatherResults",gatherResults);
		return "gatherResult/select-list-html";
	}
	/**
	 *  历史记录列表
     * @param request HttpServletRequest对象
	 * @param gatherResult 查询对象
     * @param pageInfo 分页查询对象
     * @return String 显示页面路径
     * 
	@RequestMapping(value = "history",params="list")
	public String historyList(HttpServletRequest request,GatherResult gatherResult,Page pageInfo){
		Page<HistoryRecord<GatherResult>> gatherResultPage = gatherResultService.getHistoryListPage(gatherResult, pageInfo);
		request.setAttribute("pageBean", gatherResultPage);
		request.setAttribute("manageKey", gatherResult.getManageKey());
	    return "gatherResult/history-list";
	} */
	/**
	 * 查看历史记录
     * @param request HttpServletRequest对象
     * @param version 版本号
     * @return String 显示页面路径
     *
	@RequestMapping(value = "history",params="view")
	public String historyView(HttpServletRequest request,@RequestParam(value = "version")Integer version){
		GatherResult gatherResult = gatherResultService.findByVersion(version);
		GatherResult preGatherResult = (GatherResult)gatherResultService.findByPreVersion(GatherResult.class,gatherResult.getManageKey(),version);
		if(preGatherResult!=null && preGatherResult.getManageKey().equals(gatherResult.getManageKey())){
			Map<String,CheckResult> checkResult = BeanPropUtil.propValueCheck(preGatherResult, gatherResult);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", gatherResult);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "gatherResult/detail";
	}*/
	/**
	 * 根据关键字获取采集结果select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<GatherResult> 采集结果Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<GatherResult> select2Query(String queryKey,Page<GatherResult> pageInfo, HttpServletResponse response) {
		Page<GatherResult> page = gatherResultService.queryByKey(queryKey, pageInfo);
		Select2<GatherResult> select2 = new Select2<GatherResult>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	/**
	 * 查找多个采集结果
	 * @param ids id数组
	 * @param response HttpServletResponse 对象
	 * @return List<GatherResult> 采集结果列表
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
public List<GatherResult> queryById(Integer[] ids,HttpServletResponse response) {
		List<GatherResult> gatherResultList = gatherResultService.queryById(ids);
		return gatherResultList;
	}
	/**
	 * tab页添加表单
	 * @param trTarget tr的target值
	 * @param trIndex tr的序号
     * @param propName 表单元素name前缀
	 * @return String jsp路径
	 */
	@RequestMapping(value = "/tabAddForm")
public String tabAddForm(
			@ModelAttribute(value="gatherResult") GatherResult gatherResult,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",gatherResult);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "gatherResult/tab-add-form";
	}
/**
	 * tab页查看详情页面
	 * @param trTarget tr的target值
	 * @param trIndex tr的序号
     * @param propName 表单元素name前缀
	 * @return String jsp路径
	 */
	@RequestMapping(value = "/tabDetailForm")
	public String tabDetailForm(
			@ModelAttribute(value="gatherResult") GatherResult gatherResult,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",gatherResult);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "gatherResult/tab-detail-form";
	}
}