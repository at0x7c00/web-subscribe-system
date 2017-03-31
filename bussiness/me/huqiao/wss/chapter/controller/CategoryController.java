package me.huqiao.wss.chapter.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.chapter.service.ICategoryService;
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
 * 分类控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("category")
public class CategoryController  extends BaseController {
   /**分类服务*/
    @Resource
    private ICategoryService categoryService;
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
		  * @return Category 分类对象
		  */
		@ModelAttribute(value="category")
		public Category initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		Category category = null;
		if (manageKey == null ||manageKey.equals("")) {
			category = new Category();
		} else {
			category =  categoryService.getEntityByProperty(Category.class,"manageKey", manageKey);
			if (category==null) {
				category=new Category();
			}
		}
		return category;
	}
    /**
     * 分类分页查询
     * @param request HttpServletRequest对象
     * @param category 分类查询对象
     * @param pageInfo 分页查询对象
     * 
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,Category category,Page pageInfo) {
        Page<Category> categoryPage = categoryService.getListPage(category,pageInfo);
        request.setAttribute("pageBean", categoryPage);
		listFormParam(request,category,pageInfo);
    }
 	/**
     * 为分类分页查询表单准备数据
     * @param request HttpServletRequest对象
     * @param category 分类查询对象
     * @param pageInfo 分页查询对象
     * 
     */
	public void listFormParam(HttpServletRequest request,Category category,Page pageInfo){
		//复杂���联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	}
    /**
     * 添加分类页面
     * @param request HttpServletRequest对象
     * @param callBack  回调函数名称
     *
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request,@RequestParam(value = "callBack",required = false)String callBack) {
    	//复杂关联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
		//clearTempDataList(request.getSession(),"category");
		request.setAttribute("callBack", callBack);
    }
    /**
     * 添加分类
     * @param request HttpServletRequest对象
     * @param category 要添加的对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(HttpServletRequest request,
	@Valid @ModelAttribute("category") Category category,
	@RequestParam(value = "callBack",required = false)String callBack,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	//默认系统时间类型保存
	/*
		#ONE_TO_MANY_VALUE_SAVE_ADD
	*/
	    //保存多对多关联关系
	//保持一对多关联关系
	category.setManageKey(Md5Util.getManageKey());
    	categoryService.add(category);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
        return jsonResult;
    }
    /**
     * 修改分类页面
     * @param category 需要修改的对象实体
     * @param request HttpServletRequest请求对象
     * 
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
	public void updateUI(@ModelAttribute(value="category") Category category,HttpServletRequest request) {
	request.setAttribute("tempBean", category);
    	//复杂关联关系数据准备
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	//clearTempDataList(request.getSession(),"category");
    }
    /**
     *  修改分类 
     * @param category 待修改的实体对象
     * @param request HttpServletRequest对象
     * @return JsonResult 操作结果
     *
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(HttpServletRequest request,
	@ModelAttribute(value="category") Category category,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
	    //保存多对多关联关系
		//保持一对多关联关系
        categoryService.update(category);
	// jsonResult.setNavTabId(rel);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
	/**
	 *  查看分类页面
     * @param category 需要查看的实体对象
     * @param request HttpServletRequest对象
     * 
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(@ModelAttribute(value="category") Category category,HttpServletRequest request) {
	request.setAttribute("tempBean", category);
    	//复杂关联关系数据准备
        listFormParam(request,category,null);
    }
	/**
     * 删除单个分类对象
     * @param request HttpServletRequest对象
     * @param category 待删除对象
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(HttpServletRequest request,@ModelAttribute Category category) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        try {
        	categoryService.delete(category);
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
    	Category category = null;
		JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
			 try {
    			category = categoryService.getEntityByProperty(Category.class,"manageKey",manageKey);
    			categoryService.delete(category);
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
		List<Category> categorys = new ArrayList<Category>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				Category temp_category = categoryService.getEntityByProperty(Category.class, "manageKey", manageKey);
				if(temp_category!=null && !categorys.contains(temp_category)){
					categorys.add(temp_category);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("categorys",categorys);
		return "category/select-list-html";
	}
	/**
	 *  历史记录列表
     * @param request HttpServletRequest对象
	 * @param category 查询对象
     * @param pageInfo 分页查询对象
     * @return String 显示页面路径
     * 
	@RequestMapping(value = "history",params="list")
	public String historyList(HttpServletRequest request,Category category,Page pageInfo){
		Page<HistoryRecord<Category>> categoryPage = categoryService.getHistoryListPage(category, pageInfo);
		request.setAttribute("pageBean", categoryPage);
		request.setAttribute("manageKey", category.getManageKey());
	    return "category/history-list";
	} */
	/**
	 * 查看历史记录
     * @param request HttpServletRequest对象
     * @param version 版本号
     * @return String 显示页面路径
     *
	@RequestMapping(value = "history",params="view")
	public String historyView(HttpServletRequest request,@RequestParam(value = "version")Integer version){
		Category category = categoryService.findByVersion(version);
		Category preCategory = (Category)categoryService.findByPreVersion(Category.class,category.getManageKey(),version);
		if(preCategory!=null && preCategory.getManageKey().equals(category.getManageKey())){
			Map<String,CheckResult> checkResult = BeanPropUtil.propValueCheck(preCategory, category);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", category);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "category/detail";
	}*/
	/**
	 * 根据关键字获取分类select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<Category> 分类Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<Category> select2Query(String queryKey,Page<Category> pageInfo, HttpServletResponse response) {
		Page<Category> page = categoryService.queryByKey(queryKey, pageInfo);
		Select2<Category> select2 = new Select2<Category>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	/**
	 * 查找多个分类
	 * @param ids id数组
	 * @param response HttpServletResponse 对象
	 * @return List<Category> 分类列表
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
public List<Category> queryById(Integer[] ids,HttpServletResponse response) {
		List<Category> categoryList = categoryService.queryById(ids);
		return categoryList;
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
			@ModelAttribute(value="category") Category category,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",category);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "category/tab-add-form";
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
			@ModelAttribute(value="category") Category category,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",category);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "category/tab-detail-form";
	}
}