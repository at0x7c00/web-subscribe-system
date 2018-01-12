package me.huqiao.wss.chapter.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.service.IChapterService;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.chapter.service.ITagService;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.util.StringUtil;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontendController{
	
	@Resource
	private IGatherResultService gaService;
	@Resource
	private ITaskService taskService;
	@Resource
	private ITagService tagService;
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,GatherResult gatherResult,Page<GatherResult>pageInfo,@RequestParam(value = "scope",required = false)String scope){
		return   query(request,gatherResult,pageInfo,scope==null ? "fav": scope,null,request.getParameter("taskKey"));
	}
	
	@RequestMapping("/tag/{code}")
	public String tag(HttpServletRequest request,GatherResult gatherResult,Page<GatherResult>pageInfo,
			@PathVariable(value = "code")String code){
		return query(request,gatherResult,pageInfo,"fav",code,null);
	}
	
	@RequestMapping("/site/{code}")
	public String site(HttpServletRequest request,GatherResult gatherResult,Page<GatherResult>pageInfo,
			@PathVariable(value = "code")String code){
		return  query(request,gatherResult,pageInfo,request.getParameter("scope"),null,code);
	}
	
	@RequestMapping("/tag/{code}/{scope}")
	public String tagWithScope(HttpServletRequest request,GatherResult gatherResult,Page<GatherResult>pageInfo,
			@PathVariable(value = "scope")String scope,
			@PathVariable(value = "code")String code){
		return query(request,gatherResult,pageInfo,scope,code,null);
	}
	
	
	public String query(HttpServletRequest request,
			GatherResult gatherResult,
			Page<GatherResult>pageInfo,
			String scope,
			String code,
			String siteKey){
		boolean ajax = StringUtil.isNotEmpty(request.getParameter("ajax"));
		
		if(StringUtil.isEmpty(code)){
			code = request.getParameter("tag");
		}
		if(StringUtil.isNotEmpty(code)){
			Tag tag = tagService.getEntityByProperty(Tag.class, "code", code);
			if(tag!=null){
				Set<Tag> tags = new HashSet<Tag>();
				tags.add(tag);
				gatherResult.setTags(tags);
			}
			request.setAttribute("tag", tag);
		}
		
		if(StringUtil.isEmpty(siteKey)){
			siteKey = request.getParameter("taskKey");
		}
		if(StringUtil.isNotEmpty(siteKey)){
			Task task = taskService.getEntityByProperty(Task.class, "manageKey", siteKey);
			if(task!=null){
				gatherResult.setTask(task);
				request.setAttribute("task", task);
			}
		}
		
		if(scope==null){
			scope = request.getParameter("scope");
		}
		if(!"all".equals(scope)){
			gatherResult.setFavourite(YesNo.Yes);
		}
		pageInfo.setNumPerPage(10);
		Page<GatherResult> pageBean = gaService.getListPage(gatherResult, pageInfo);
		request.setAttribute("pageBean",pageBean);
		request.setAttribute("scope",scope);
		
		List<Tag> tagList = tagService.getByProperties(Tag.class,new String[]{"status"},new Object[]{UseStatus.InUse},"sortNum",null);
		request.setAttribute("tagList",tagList);
		
		return "f/" + (ajax ? "ajax-list" : "index");
	}
	
	
	
	@Resource
	private IChapterService chapterService;
	
	@RequestMapping(value="/v/{k}",method=RequestMethod.GET)
    public String view(@PathVariable("k")String key,HttpServletRequest request){
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
	
	@RequestMapping(value="/tag",method=RequestMethod.GET)
	@ResponseBody
    public JsonResult view(@RequestParam("k")String key,@RequestParam("used")boolean used,@RequestParam("tag")String tagKey,HttpServletRequest request){
		try{
			GatherResult gatherResult = gaService.getEntityByProperty(GatherResult.class, "manageKey", key);
			Tag tag = tagService.getEntityByProperty(Tag.class, "manageKey", tagKey);
			if(gatherResult!=null && tag!=null){
				if(used){
					if(gatherResult.getTags()==null){
						gatherResult.setTags(new HashSet<Tag>());
					}
					gatherResult.getTags().add(tag);
				}else{
					if(gatherResult!=null && tag!=null){
						gatherResult.getTags().remove(tag);
					}
				}
				gaService.update(gatherResult);
			}
			return JsonResult.success("ok");
		}catch(Exception e){
			return JsonResult.error(e.getMessage());
		}
    }
	
	@RequestMapping(value="/live",method=RequestMethod.GET)
	public String live(@RequestParam(value = "pid",required = false)String pid,
			HttpServletRequest request){
		if(StringUtil.isEmpty(pid)){
			pid = "cctv5";
		}
		request.setAttribute("pid", pid);
		return "f/live";
	}
    
}
