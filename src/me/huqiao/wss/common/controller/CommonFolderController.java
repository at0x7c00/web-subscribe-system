package me.huqiao.wss.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import me.huqiao.wss.common.entity.CommonFolder;
import me.huqiao.wss.common.service.ICommonFolderService;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件夹Controller
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("commonFolder")
public class CommonFolderController extends BaseController {

    @Resource
    private ICommonFolderService commonFolderService;
   
    
    //复杂关联关系的Service
	
    /**
	  * 初始化ModelAttribute
	  * @param id  唯一识别主键值
	  * @return CommonFolder 文件夹对象
	  */
    @ModelAttribute
    public CommonFolder initModelAttribute(@RequestParam(value="id",required=false) Integer id){
    	CommonFolder commonFolder = null;
    	if(id==null){
    		commonFolder = new CommonFolder();
    	}else{
    		commonFolder = commonFolderService.getById(CommonFolder.class, id);
    	}
    	return commonFolder;
    }
    
    
    /**
     * 文件夹分页查询
     * @param request HttpServletRequest对象
     * @param commonFolder 查询对象
     * @param pageInfo 分页查询对象
     */
    @RequestMapping(value="/list")
    public void list(HttpServletRequest request,CommonFolder commonFolder,Page pageInfo) {
        Page<CommonFolder> commonFolderPage = commonFolderService.getPage(commonFolder,pageInfo);
        request.setAttribute("pageBean", commonFolderPage);
    	//findAndSaveParams(request);
    }
    /**
     * 文件夹查询
     * @param request HttpServletRequest 对象
     * @param commonFolder 查询对象
     * @param pageInfo 分页信息对象
     */
    @RequestMapping(value="/selectList")
    public void selectList(HttpServletRequest request,CommonFolder commonFolder,Page pageInfo) {
    	 Page<CommonFolder> commonFolderPage = commonFolderService.getPage(commonFolder,pageInfo);
         request.setAttribute("pageBean", commonFolderPage);
      	//findAndSaveParams(request);
    }
    /**
     * 文件夹分页查询
     * @param request HttpServletRequest对象
     * @param commonFolder 查询对象
     * @param pageInfo 分页纤细对象
     * @return 文件夹列表
     */
    @RequestMapping(value="/suggestList")
    @ResponseBody
    public List<CommonFolder> suggestList(HttpServletRequest request,CommonFolder commonFolder,Page pageInfo) {
    	List<CommonFolder> list = commonFolderService.findAll(CommonFolder.class);
    	request.setAttribute("pageBean", list);
    	return list;
    }
  
    /**
     * 添加文件夹页面
     * @param request HttpServletRequest对象
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public void addUI(HttpServletRequest request) {
    	//findAndSaveParams(request);
    }
    
    /**
     * 添加文件夹
     * @param commonFolder 要添加的对象
     * @param result BindingResult对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@Valid @ModelAttribute("commonFolder") CommonFolder commonFolder,BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
    	//默认系统时间类型保存
    	
    	
    	commonFolderService.add(commonFolder);
    	jsonResult.setMessage("添加成功");
        return jsonResult;
    }
    
 
    /**
     * 修改文件夹页面
     * @param id 需要修改的对象实体ID
     * @param request HttpServletRequest请求对象
     */
    @RequestMapping(value="/update",method=RequestMethod.GET)
    public void updateUI(@RequestParam(value="id") Integer id,HttpServletRequest request) {
    	//复杂关联关系数据准备
    	
    	//findAndSaveParams(request);
    }
    
  
    /**
     * 修改文件夹
     * @param commonFolder 待修改的实体对象
     * @param result BindingResult对象
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult update(@ModelAttribute CommonFolder commonFolder,BindingResult result) {
    	JsonResult jsonResult = new JsonResult();
    	if(!validate(jsonResult,result)){
    		return jsonResult;
    	}
	    
        commonFolderService.update(commonFolder);
        jsonResult.setMessage("修改成功");
        return jsonResult;
    }
    
    /**
     * 删除文件夹对象
     * @param ids ID数组
     * @return JsonResult 操作结果
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(@RequestParam("ids") Integer[] ids) {
    	for(Integer id : ids){
    		CommonFolder commonFolder = commonFolderService.loadById(CommonFolder.class, id);
    		commonFolderService.delete(commonFolder);
    	}
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCallbackType("");
        jsonResult.setMessage("删除成功");
        return jsonResult;
    }
    /**
     * 删除多个
     * @param uids
     * @return
     * 2013-05-16 16:15:08
   
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult batchDelete(@RequestParam("ids") Integer[] ids) {
    	CommonFolder commonFolder = null;
    	for(Integer id : ids){
    		commonFolder = commonFolderService.loadById(CommonFolder.class, id);
    		commonFolderService.delete(commonFolder);
    	}
    	JsonResult jsonResult = new JsonResult();
    	jsonResult.setMessage("删除成功");
    	return jsonResult;
    } 
    */
    
}
