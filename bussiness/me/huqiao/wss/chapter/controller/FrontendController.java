package me.huqiao.wss.chapter.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.service.IChapterService;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.util.StringUtil;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/f")
public class FrontendController extends BaseController{
	
	@Resource
	private IGatherResultService gaService;
	@Resource
	private ITaskService taskService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,GatherResult gatherResult,
			Page<GatherResult>pageInfo,
			@RequestParam(value = "all",required = false)String all,
			@RequestParam(value = "tag",required = false)String tag
			){
		boolean ajax = StringUtil.isNotEmpty(request.getParameter("ajax"));
		
		Task task = taskService.getEntityByProperty(Task.class, "manageKey", tag);
		gatherResult.setTask(task);
		
		request.setAttribute("task", task);
		
		if(StringUtil.isEmpty(all)){
			gatherResult.setFavourite(YesNo.Yes);
		}
		pageInfo.setNumPerPage(20);
		Page<GatherResult> pageBean = gaService.getListPage(gatherResult, pageInfo);
		request.setAttribute("pageBean",pageBean);
		request.setAttribute("all",all);
		return "f/" + (ajax ? "ajax-list" : "index");
	}
	
	@Resource
	private IChapterService chapterService;
	
	@RequestMapping(value="/v",method=RequestMethod.GET)
    public String view(@RequestParam("k")String key,HttpServletRequest request){
		GatherResult gatherResult = gaService.getEntityByProperty(GatherResult.class, "manageKey", key);
		if(gatherResult==null){
			return "redirect:f/index.do";
		}
		Chapter c = chapterService.getEntityByProperty(Chapter.class, "gatherResultKey", gatherResult.getManageKey());
		if(c!=null){
			request.setAttribute("chapter", c);
			return "f/chapter-view";
		}
    	//gatherResult.setStatus(UseStatus.UnUse);
    	gaService.update(gatherResult);
    	return "redirect:" + gatherResult.getAccessUrl();
    }
    
}
