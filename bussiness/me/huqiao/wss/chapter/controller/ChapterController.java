package me.huqiao.wss.chapter.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.entity.propertyeditor.CategoryEditor;
import me.huqiao.wss.chapter.service.ICategoryService;
import me.huqiao.wss.chapter.service.IChapterService;
import me.huqiao.wss.chapter.service.ITagService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.entity.propertyeditor.CommonFileEditor;
import me.huqiao.wss.common.service.ICommonFileService;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.sys.entity.propertyeditor.UserEditor;
import me.huqiao.wss.sys.service.IUserService;
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
 * 文章控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("chapter")
public class ChapterController  extends BaseController {
   /**文章服务*/
    @Resource
    private IChapterService chapterService;
 /**
  * 注册属性编辑器
  * @param binder 数据绑定器
  */
    @InitBinder
	public void initPropEditor(WebDataBinder binder){
         binder.registerCustomEditor(Category.class,new CategoryEditor(categoryService));
         binder.registerCustomEditor(User.class,new UserEditor(userService));
         binder.registerCustomEditor(CommonFile.class,new CommonFileEditor(commonFileService));
	}
    //复杂关联关系的Service
@Resource private ICategoryService categoryService;
@Resource private ITagService tagService;
@Resource private IUserService userService;
@Resource private ICommonFileService commonFileService;
		/**
		  * 初始化ModelAttribute
		  * @param manageKey md5管理ID （非空时自动加载指定对象）
		  * @param model 页面model对象
		  * @return Chapter 文章对象
		  */
		@ModelAttribute(value="chapter")
		public Chapter initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		Chapter chapter = null;
		if (manageKey == null ||manageKey.equals("")) {
			chapter = new Chapter();
		} else {
			chapter =  chapterService.getEntityByProperty(Chapter.class,"manageKey", manageKey);
			if (chapter==null) {
				chapter=new Chapter();
			}
		}
		return chapter;
	}
    /**
     * 文章分页查询
     * @param request HttpServletRequest对象
     * @param chapter 文章查询对象
     * @param pageInfo 分页查询对象
     * 
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,Chapter chapter,Page pageInfo) {
        Page<Chapter> chapterPage = chapterService.getListPage(chapter,pageInfo);
        request.setAttribute("pageBean", chapterPage);
		listFormParam(request,chapter,pageInfo);
    }
 	/**
     * 为文章分页查询表单准备数据
     * @param request HttpServletRequest对象
     * @param chapter 文章查询对象
     * @param pageInfo 分页查询对象
     * 
     */
	public void listFormParam(HttpServletRequest request,Chapter chapter,Page pageInfo){
		//复杂关联关系数据准备
					List<Category> categoryList = categoryService.getByProperties(Category.class,new String[]{"status"},new Object[]{UseStatus.InUse},null,null);
	request.setAttribute("categoryList",categoryList);
					List<User> userList = userService.getByProperties(User.class,null,null,null,null);
	request.setAttribute("userList",userList);
					List<CommonFile> commonFileList = commonFileService.getByProperties(CommonFile.class,null,null,null,null);
	request.setAttribute("commonFileList",commonFileList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	}
    /**
     * 添加文章页面
     * @param request HttpServletRequest对象
     * @param callBack  回调函数名称
     *
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request,@RequestParam(value = "callBack",required = false)String callBack) {
    	//复杂关联关系数据准备
					List<Category> categoryList = categoryService.getByProperties(Category.class,new String[]{"status"},new Object[]{UseStatus.InUse},null,null);
	request.setAttribute("categoryList",categoryList);
					List<User> userList = userService.getByProperties(User.class,null,null,null,null);
	request.setAttribute("userList",userList);
					List<CommonFile> commonFileList = commonFileService.getByProperties(CommonFile.class,null,null,null,null);
	request.setAttribute("commonFileList",commonFileList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
		//clearTempDataList(request.getSession(),"chapter");
		request.setAttribute("callBack", callBack);
    }
    /**
     * 添加文章
     * @param request HttpServletRequest对象
     * @param chapter 要添加的对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(HttpServletRequest request,
	@Valid @ModelAttribute("chapter") Chapter chapter,
	@RequestParam(value = "callBack",required = false)String callBack,
		@RequestParam(value="tagsKeys",required=false)String[] tagsKeys,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	//默认系统时间类型保存
chapter.setCreateTime(new Date());
	/*
		#ONE_TO_MANY_VALUE_SAVE_ADD
	*/
	    //保存多对多关联关系
		//设置标签
		HashSet<Tag> tags = new HashSet<Tag>();
		if(tagsKeys!=null){
			for(String key : tagsKeys){
			if(key==null || key.trim().equals("")) continue;
			Tag tmp = tagService.getEntityByProperty(Tag.class, "manageKey", key);
			tags.add(tmp);
			}
		}
		chapter.setTags(tags);
		chapter.setCover(parseFilee(request,"coverKeys",null));
	//保持一对多关联关系
	chapter.setManageKey(Md5Util.getManageKey());
    	chapterService.add(chapter);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
        return jsonResult;
    }
    /**
     * 修改文章页面
     * @param chapter 需要修改的对象实体
     * @param request HttpServletRequest请求对象
     * 
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
	public void updateUI(@ModelAttribute(value="chapter") Chapter chapter,HttpServletRequest request) {
	request.setAttribute("tempBean", chapter);
    	//复杂关联关系数据准备
					List<Category> categoryList = categoryService.getByProperties(Category.class,new String[]{"status"},new Object[]{UseStatus.InUse},null,null);
					if(chapter.getCategory()!=null && chapter.getCategory().getStatus()==UseStatus.UnUse){
						categoryList.add(chapter.getCategory());
					}
	request.setAttribute("categoryList",categoryList);
					List<User> userList = userService.getByProperties(User.class,null,null,null,null);
	request.setAttribute("userList",userList);
					List<CommonFile> commonFileList = commonFileService.getByProperties(CommonFile.class,null,null,null,null);
	request.setAttribute("commonFileList",commonFileList);
request.setAttribute("useStatusMap",UseStatus.useStatusMap);
	//clearTempDataList(request.getSession(),"chapter");
    }
    /**
     *  修改文章 
     * @param chapter 待修改的实体对象
     * @param request HttpServletRequest对象
     * @return JsonResult 操作结果
     *
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(HttpServletRequest request,
	@ModelAttribute(value="chapter") Chapter chapter,
		@RequestParam(value="tagsKeys",required=false)String[] tagsKeys,
	BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
	    //保存多对多关联关系
		//设置标签
		HashSet<Tag> tags = new HashSet<Tag>();
		if(tagsKeys!=null){
			for(String key : tagsKeys){
				if(key==null || key.trim().equals("")) continue;
				Tag tmp = tagService.getEntityByProperty(Tag.class, "manageKey", key);
				tags.add(tmp);
			}
		}
		chapter.getTags().clear();
		chapter.getTags().addAll(tags);
		String oldKey = null;
		if(chapter.getCover()!=null){
			oldKey = chapter.getCover().getManageKey();
		}
		chapter.setCover(parseFilee(request,"coverKeys",oldKey));
		//保持一对多关联关系
        chapterService.update(chapter);
	// jsonResult.setNavTabId(rel);
        jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
        return jsonResult;
    }
	/**
	 *  查看文章页面
     * @param chapter 需要查看的实体对象
     * @param request HttpServletRequest对象
     * 
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(@ModelAttribute(value="chapter") Chapter chapter,HttpServletRequest request) {
	request.setAttribute("tempBean", chapter);
    	//复杂关联关系数据准备
        listFormParam(request,chapter,null);
    }
	/**
     * 删除单个文章对象
     * @param request HttpServletRequest对象
     * @param chapter 待删除对象
     * @return JsonResult 操作结果
     * 
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(HttpServletRequest request,@ModelAttribute Chapter chapter) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        try {
        	chapterService.delete(chapter);
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
    	Chapter chapter = null;
		JsonResult jsonResult = new JsonResult();
    	for(String manageKey : manageKeys){
			 try {
    			chapter = chapterService.getEntityByProperty(Chapter.class,"manageKey",manageKey);
		markFileAsUnuse(chapter.getCover());
    			chapterService.delete(chapter);
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
		List<Chapter> chapters = new ArrayList<Chapter>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				Chapter temp_chapter = chapterService.getEntityByProperty(Chapter.class, "manageKey", manageKey);
				if(temp_chapter!=null && !chapters.contains(temp_chapter)){
					chapters.add(temp_chapter);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("chapters",chapters);
		return "chapter/select-list-html";
	}
	/**
	 *  历史记录列表
     * @param request HttpServletRequest对象
	 * @param chapter 查询对象
     * @param pageInfo 分页查询对象
     * @return String 显示页面路径
     * 
	@RequestMapping(value = "history",params="list")
	public String historyList(HttpServletRequest request,Chapter chapter,Page pageInfo){
		Page<HistoryRecord<Chapter>> chapterPage = chapterService.getHistoryListPage(chapter, pageInfo);
		request.setAttribute("pageBean", chapterPage);
		request.setAttribute("manageKey", chapter.getManageKey());
	    return "chapter/history-list";
	} */
	/**
	 * 查看历史记录
     * @param request HttpServletRequest对象
     * @param version 版本号
     * @return String 显示页面路径
     *
	@RequestMapping(value = "history",params="view")
	public String historyView(HttpServletRequest request,@RequestParam(value = "version")Integer version){
		Chapter chapter = chapterService.findByVersion(version);
		Chapter preChapter = (Chapter)chapterService.findByPreVersion(Chapter.class,chapter.getManageKey(),version);
		if(preChapter!=null && preChapter.getManageKey().equals(chapter.getManageKey())){
			Map<String,CheckResult> checkResult = BeanPropUtil.propValueCheck(preChapter, chapter);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", chapter);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "chapter/detail";
	}*/
	/**
	 * 根据关键字获取文章select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<Chapter> 文章Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<Chapter> select2Query(String queryKey,Page<Chapter> pageInfo, HttpServletResponse response) {
		Page<Chapter> page = chapterService.queryByKey(queryKey, pageInfo);
		Select2<Chapter> select2 = new Select2<Chapter>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	/**
	 * 查找多个文章
	 * @param ids id数组
	 * @param response HttpServletResponse 对象
	 * @return List<Chapter> 文章列表
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
public List<Chapter> queryById(Integer[] ids,HttpServletResponse response) {
		List<Chapter> chapterList = chapterService.queryById(ids);
		return chapterList;
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
			@ModelAttribute(value="chapter") Chapter chapter,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",chapter);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "chapter/tab-add-form";
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
			@ModelAttribute(value="chapter") Chapter chapter,
			@RequestParam(value= "trTarget") String trTarget,
			@RequestParam(value= "trIndex") Integer trIndex,
			@RequestParam(value = "propName") String propName,
			HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean",chapter);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "chapter/tab-detail-form";
	}
}