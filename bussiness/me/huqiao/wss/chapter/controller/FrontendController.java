package me.huqiao.wss.chapter.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.util.StringUtil;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/f")
public class FrontendController extends BaseController{
	
	@Resource
	private IGatherResultService gaService;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request,GatherResult gatherResult,
			Page<GatherResult>pageInfo,
			@RequestParam(value = "all",required = false)String all){
		if(StringUtil.isEmpty(all)){
			gatherResult.setFavourite(YesNo.Yes);
		}
		Page<GatherResult> pageBean = gaService.getListPage(gatherResult, pageInfo);
		request.setAttribute("pageBean",pageBean);
		request.setAttribute("all",all);
	}
	
	 @RequestMapping(value="/v",method=RequestMethod.GET)
    public String view(@RequestParam("k")String key,HttpServletRequest request){
		GatherResult gatherResult = gaService.getEntityByProperty(GatherResult.class, "manageKey", key);
		if(gatherResult==null){
			return "redirect:f/index.do";
		}
    	//gatherResult.setStatus(UseStatus.UnUse);
    	gaService.update(gatherResult);
    	return "redirect:" + gatherResult.getAccessUrl();
    }
    
}
