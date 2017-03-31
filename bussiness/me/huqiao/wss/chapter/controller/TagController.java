package me.huqiao.wss.chapter.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.service.ITagService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
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
 * 标签控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("tag")
public class TagController  extends BaseController {
   /**标签服务*/
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
		  * @return Tag 标签对象
		  */
		@ModelAttribute(value="tag")
		public Tag initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		Tag tag = null;
		if (manageKey == null ||manageKey.equals("")) {
			tag = new Tag();
		} else {
			tag =  tagService.getEntityByProperty(Tag.class,"manageKey", manageKey);
			if (tag==null) {
				tag=new Tag();
			}
		}
		return tag;
	}
    /**
     * 标签分页查询
     * @param request HttpServletRequest对象
     * @param tag 标签查询对象
     * @param pageInfo 分页查询对象
     * 
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,Tag tag,Page pageInfo) {
        Page<Tag> tagPage = tagService.getListPage(tag,pageInfo);
        request.setAttribute("pageBean", tagPage);
		listFormParam(request,tag,pageInfo);
    }
 	/**
     * 为标签分页查询表单准备数据
     * @param request HttpServletRequest对象
     * @param tag 标签查询对象
     * @param pageInfo 分页查询对象
     * 
     */
	public void listFormParam(HttpServletRequest request,Tag tag,Page pageInfo){
		//复杂关联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	}
    /**
     * 添加标签页面
     * @param request HttpServletRequest对象
     * @param callBack  回调函数名称
     *
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request,@RequestParam(value = "callBack",required = false)String callBack) {
    	//复杂关联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
		//clearTempDataList(request.getSession(),"tag");
		request.setAttribute("callBack", callBack);
    }
    /**
     * 添加标签
     * @param request HttpServletRequest对象
     * @param tag 要添加的对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(HttpServletRequest request,
	@Valid @ModelAttribute("tag") Tag tag,
	@RequestParam(value = "callBack",required = false)String callBack,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	//默认系统时间类型保存
	/*
		#ONE_TO_MANY_VALUE_SAVE_ADD
	*/
	    //保存多对多关联关系
	//保持一对多关联关系
	tag.setManageKey(Md5Util.getManageKey());
    	tagService.add(tag);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
        return jsonResult;
    }
    /**
     * 修改标签页面
     * @param tag 需要修改的对象实体
     * @param request HttpServletRequest请求对象
     * 
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
	public void updateUI(@ModelAttribute(value="tag") Tag tag,HttpServletRequest request) {
	request.setAttribute("tempBean", tag);
    	//复杂关联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	//clearTempDataList(request.getSession(),"tag");
    }
    /**
     *  修改标签 
     * @param tag 待修改的实体对象
     * @param request HttpServletRequest对象
     * @return JsonResult 操作结果
     *
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(HttpServletRequest request,
	@ModelAttribute(value="tag") Tag tag,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
	    //保存多对多关联关系
		//保持一对多关联关系
        tagService.update(tag);
	// jsonResult.setNavTabId(rel);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
	/**
	 *  查看标签页面
     * @param tag 需要查看的实体对象
     * @param request HttpServletRequest对象
     * 
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(@ModelAttribute(value="tag") Tag tag,HttpServletRequest request) {
	request.setAttribute("tempBean", tag);
    	//复杂关联关系数据准备
        listFormParam(request,tag,null);
    }
	/**
     * 删除单个标签对象
     * @param request HttpServletRequest对象
     * @param tag 待删除对象
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(HttpServletRequest request,@ModelAttribute Tag tag) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        try {
        	tagService.delete(tag);
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
    	Tag tag = null;
		JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
			 try {
    			tag = tagService.getEntityByProperty(Tag.class,"manageKey",manageKey);
    			tagService.delete(tag);
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
		List<Tag> tags = new ArrayList<Tag>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				Tag temp_tag = tagService.getEntityByProperty(Tag.class, "manageKey", manageKey);
				if(temp_tag!=null && !tags.contains(temp_tag)){
					tags.add(temp_tag);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("tags",tags);
		return "tag/select-list-html";
	}
	/**
	 *  历史记录列表
     * @param request HttpServletRequest对象
	 * @param tag 查询对象
     * @param pageInfo 分页查询对象
     * @return String 显示页面路径
     * 
	@RequestMapping(value = "history",params="list")
	public String historyList(HttpServletRequest request,Tag tag,Page pageInfo){
		Page<HistoryRecord<Tag>> tagPage = tagService.getHistoryListPage(tag, pageInfo);
		request.setAttribute("pageBean", tagPage);
		request.setAttribute("manageKey", tag.getManageKey());
	    return "tag/history-list";
	} */
	/**
	 * 查看历史记录
     * @param request HttpServletRequest对象
     * @param version 版本号
     * @return String 显示页面路径
     *
	@RequestMapping(value = "history",params="view")
	public String historyView(HttpServletRequest request,@RequestParam(value = "version")Integer version){
		Tag tag = tagService.findByVersion(version);
		Tag preTag = (Tag)tagService.findByPreVersion(Tag.class,tag.getManageKey(),version);
		if(preTag!=null && preTag.getManageKey().equals(tag.getManageKey())){
			Map<String,CheckResult> checkResult = BeanPropUtil.propValueCheck(preTag, tag);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", tag);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "tag/detail";
	}*/
	/**
	 * 根据关键字获取标签select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<Tag> 标签Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<Tag> select2Query(String queryKey,Page<Tag> pageInfo, HttpServletResponse response) {
		Page<Tag> page = tagService.queryByKey(queryKey, pageInfo);
		Select2<Tag> select2 = new Select2<Tag>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	/**
	 * 查找多个标签
	 * @param ids id数组
	 * @param response HttpServletResponse 对象
	 * @return List<Tag> 标签列表
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
public List<Tag> queryById(Integer[] ids,HttpServletResponse response) {
		List<Tag> tagList = tagService.queryById(ids);
		return tagList;
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
			@ModelAttribute(value="tag") Tag tag,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",tag);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "tag/tab-add-form";
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
			@ModelAttribute(value="tag") Tag tag,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",tag);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "tag/tab-detail-form";
	}
}