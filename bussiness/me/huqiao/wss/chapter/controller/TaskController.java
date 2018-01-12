package me.huqiao.wss.chapter.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.service.ITagService;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.StringUtil;
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
 * 采集任务控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("task")
public class TaskController  extends BaseController {
   /**采集任务服务*/
    @Resource
    private ITaskService taskService;
    @Resource
    private ITagService tagService;
 /**
  * 注册属性编辑器
  * @param binder 数据绑定器
  */
    @InitBinder
	public void initPropEditor(WebDataBinder binder){
	}
    //复杂关联关系的Service
		/**
		  * 初始化ModelAttribute
		  * @param manageKey md5管理ID （非空时自动加载指定对象）
		  * @param model 页面model对象
		  * @return Task 采集任务对象
		  */
		@ModelAttribute(value="task")
		public Task initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		Task task = null;
		if (manageKey == null ||manageKey.equals("")) {
			task = new Task();
		} else {
			task =  taskService.getEntityByProperty(Task.class,"manageKey", manageKey);
			if (task==null) {
				task=new Task();
			}
		}
		return task;
	}
    /**
     * 采集任务分页查询
     * @param request HttpServletRequest对象
     * @param task 采集任务查询对象
     * @param pageInfo 分页查询对象
     * 
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,Task task,Page pageInfo) {
        Page<Task> taskPage = taskService.getListPage(task,pageInfo);
        request.setAttribute("pageBean", taskPage);
		listFormParam(request,task,pageInfo);
    }
 	/**
     * 为采集任务分页查询表单准备数据
     * @param request HttpServletRequest对象
     * @param task 采集任务查询对象
     * @param pageInfo 分页查询对象
     * 
     */
	public void listFormParam(HttpServletRequest request,Task task,Page pageInfo){
		//复杂关联关系数据准备
	}
    /**
     * 添加采集任务页面
     * @param request HttpServletRequest对象
     * @param callBack  回调函数名称
     *
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request,@RequestParam(value = "callBack",required = false)String callBack) {
    	//复杂关联关系数据准备
		//clearTempDataList(request.getSession(),"task");
		request.setAttribute("callBack", callBack);
    }
    /**
     * 添加采集任务
     * @param request HttpServletRequest对象
     * @param task 要添加的对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(HttpServletRequest request,
	@Valid @ModelAttribute("task") Task task,
	@RequestParam(value="tagsKeys",required=false)String[] tagsKeys,
	@RequestParam(value = "callBack",required = false)String callBack,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	task.setManageKey(Md5Util.getManageKey());
    	
    	HashSet<Tag> tags = new HashSet<Tag>();
		if(tagsKeys!=null){
			for(String key : tagsKeys){
			if(key==null || key.trim().equals("")) continue;
			Tag tmp = tagService.getEntityByProperty(Tag.class, "manageKey", key);
			tags.add(tmp);
			}
		}
		task.setTags(tags);
    	
    	taskService.add(task);
    	taskService.updateToStart(task);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
        return jsonResult;
    }
    /**
     * 修改采集任务页面
     * @param task 需要修改的对象实体
     * @param request HttpServletRequest请求对象
     * 
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
	public void updateUI(@ModelAttribute(value="task") Task task,HttpServletRequest request) {
	request.setAttribute("tempBean", task);
    	//复杂关联关系数据准备
	//clearTempDataList(request.getSession(),"task");
    }
    /**
     *  修改采集任务 
     * @param task 待修改的实体对象
     * @param request HttpServletRequest对象
     * @return JsonResult 操作结果
     *
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(HttpServletRequest request,
    		@RequestParam(value="tagsKeys",required=false)String[] tagsKeys,
	@ModelAttribute(value="task") Task task,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
        
      //设置标签
  		HashSet<Tag> tags = new HashSet<Tag>();
  		if(tagsKeys!=null){
  			for(String key : tagsKeys){
  				if(key==null || key.trim().equals("")) continue;
  				Tag tmp = tagService.getEntityByProperty(Tag.class, "manageKey", key);
  				tags.add(tmp);
  			}
  		}
  		task.getTags().clear();
  		task.getTags().addAll(tags);
  		taskService.update(task);
      		
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
	/**
	 *  查看采集任务页面
     * @param task 需要查看的实体对象
     * @param request HttpServletRequest对象
     * 
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(@ModelAttribute(value="task") Task task,HttpServletRequest request) {
	request.setAttribute("tempBean", task);
    	//复杂关联关系数据准备
        listFormParam(request,task,null);
    }
	/**
     * 删除单个采集任务对象
     * @param request HttpServletRequest对象
     * @param task 待删除对象
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(HttpServletRequest request,@ModelAttribute Task task) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        try {
        	taskService.delete(task);
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
    	Task task = null;
		JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
			 try {
    			task = taskService.getEntityByProperty(Task.class,"manageKey",manageKey);
    			taskService.delete(task);
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
		List<Task> tasks = new ArrayList<Task>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				Task temp_task = taskService.getEntityByProperty(Task.class, "manageKey", manageKey);
				if(temp_task!=null && !tasks.contains(temp_task)){
					tasks.add(temp_task);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("tasks",tasks);
		return "task/select-list-html";
	}
	/**
	 *  历史记录列表
     * @param request HttpServletRequest对象
	 * @param task 查询对象
     * @param pageInfo 分页查询对象
     * @return String 显示页面路径
     * 
	@RequestMapping(value = "history",params="list")
	public String historyList(HttpServletRequest request,Task task,Page pageInfo){
		Page<HistoryRecord<Task>> taskPage = taskService.getHistoryListPage(task, pageInfo);
		request.setAttribute("pageBean", taskPage);
		request.setAttribute("manageKey", task.getManageKey());
	    return "task/history-list";
	} */
	/**
	 * 查看历史记录
     * @param request HttpServletRequest对象
     * @param version 版本号
     * @return String 显示页面路径
     *
	@RequestMapping(value = "history",params="view")
	public String historyView(HttpServletRequest request,@RequestParam(value = "version")Integer version){
		Task task = taskService.findByVersion(version);
		Task preTask = (Task)taskService.findByPreVersion(Task.class,task.getManageKey(),version);
		if(preTask!=null && preTask.getManageKey().equals(task.getManageKey())){
			Map<String,CheckResult> checkResult = BeanPropUtil.propValueCheck(preTask, task);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", task);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "task/detail";
	}*/
	/**
	 * 根据关键字获取采集任务select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<Task> 采集任务Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<Task> select2Query(String queryKey,Page<Task> pageInfo, HttpServletResponse response) {
		Page<Task> page = taskService.queryByKey(queryKey, pageInfo);
		Select2<Task> select2 = new Select2<Task>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	/**
	 * 查找多个采集任务
	 * @param ids id数组
	 * @param response HttpServletResponse 对象
	 * @return List<Task> 采集任务列表
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
	public List<Task> queryById(Integer[] ids,HttpServletResponse response) {
		List<Task> taskList = taskService.queryById(ids);
		return taskList;
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
			@ModelAttribute(value="task") Task task,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",task);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "task/tab-add-form";
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
			@ModelAttribute(value="task") Task task,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",task);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "task/tab-detail-form";
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult start(HttpServletRequest request,
			@ModelAttribute(value = "task") Task task,
			@RequestParam(value = "taskNoForCancel",required = false) String taskNoForCancel) {
		JsonResult jsonResult = new JsonResult();
		
		if(StringUtil.isNotEmpty(taskNoForCancel)){
			Task taskForCancel = taskService.getEntityByProperty(Task.class, "manageKey", taskNoForCancel);
			if(taskForCancel!=null){
				taskService.updateToStop(task);
			}
		}
		
		try{
			taskService.updateToStart(task);
		}catch(Exception e){
			return JsonResult.error("启动任务时失败!");
		}
		jsonResult.setMessage("启动任务成功!");
		return jsonResult;
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult stop(HttpServletRequest request,@ModelAttribute(value = "task") Task task) {
		JsonResult jsonResult = new JsonResult();
		try{
			taskService.updateToStop(task);
		}catch(Exception e){
			return JsonResult.error("停止任务时失败!");
		}
		jsonResult.setMessage("停止任务成功!");
		return jsonResult;
	}
	
}