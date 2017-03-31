package me.huqiao.wss.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.common.entity.CommonFolder;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.entity.propertyeditor.CommonFolderEditor;
import me.huqiao.wss.common.service.ICommonFileService;
import me.huqiao.wss.common.service.ICommonFolderService;
import me.huqiao.wss.util.FileUtil;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.Page;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("filee")
public class CommonFileController extends BaseController {
	
	static Logger logger = Logger.getLogger(CommonFileController.class);
    /** 文件服务*/
	@Resource
	private ICommonFileService fileeService;
	/**
	  * 注册属性编辑器
	  * @param binder 数据绑定器
	  */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		// 注册属性编辑器
		binder.registerCustomEditor(CommonFolder.class, new CommonFolderEditor(commonFolderService));
	}

	/** 文件目录服务*/
	@Resource
	private ICommonFolderService commonFolderService;
	/**
	  * 初始化ModelAttribute
	  * @param id 唯一标志ID （非空时自动加载指定对象）
	  * @return CommonFile 文件对象
	  */
	@ModelAttribute
	public CommonFile initModelAttribute(@RequestParam(value = "id", required = false) Integer id) {
		CommonFile filee = null;
		if (id == null || id.equals("")) {
			filee = new CommonFile();
		} else {
			filee = fileeService.getById(CommonFile.class, id);
		}
		return filee;
	}

	/**
	 * 文件分页查询
	 * @param request HttpServletRequest对象
	 * @param pageInfo 分页信息对象
	 * @param filee 查询对象
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, CommonFile filee, Page pageInfo) {
		Page<CommonFile> fileePage = fileeService.getPage(filee, pageInfo);
		request.setAttribute("pageBean", fileePage);
		// 复杂关联关系数据准备
		List<CommonFolder> commonFolderList = commonFolderService.findAll(CommonFolder.class);
		request.setAttribute("commonFolderList", commonFolderList);
		
		// findAndSaveParams(request);
	}

	/**
	 * 添加文件页面
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addUI(HttpServletRequest request) {
		// findAndSaveParams(request);
	}

	/**
	 * 添加文件
	 * @param photofile 头像文件
	 * @param folderId 目录ID
	 * @param userKey 用户key
	 * @param request  HttpServletRequest
	 * @return String 返回jsp页面路径
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult add(MultipartFile photofile, @RequestParam(value = "folderId", required = false) Integer folderId,
			@RequestParam(value = "userKey",required=false)String userKey,
            HttpServletRequest request) {
		
		//System.out.println("userKey=" + userKey);
		//if(getCurrentUser()==null && (userKey==null || userKey.trim().equals("") || !MemoryStorage.getInstance().verifyUserkey(userKey))){
		//	return "error";
		//}
		
		folderId = folderId == null ? 1 : folderId;
		
		/*byte[] bytes = null;
		
		try {
			bytes = photofile.getBytes();
		} catch (IOException e1) {
			e1.printStackTrace();
			return new UploadResult(false,e1.getMessage());
		}
		*/
		CommonFolder folder = commonFolderService.getById(CommonFolder.class, folderId);
		CommonFile filee = new CommonFile();
		filee.setCreateDate(new Date());
		filee.setManageKey(Md5Util.getManageKey());
		filee.setExtensionName(FileUtil.getExtensionName(photofile.getOriginalFilename()));
		filee.setName(photofile.getOriginalFilename());
		try {
			FileUtil.saveFileOnDisk(folder.getStorePath(),filee.getManageKey(), photofile.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return new UploadResult(false,e.getMessage());
		}
		filee.setInuse(UseStatus.UnUse);
		filee.setStoreName("");
		filee.setFolder(folder);
		fileeService.add(filee);
		return new UploadResult(true,filee.getManageKey());
	}
	

	/**
	 * 对话框添加附件
	 * @param request HttpServletRequest对象
	 * @param selectlist 选择列表参数
	 * @param formTargetPanel 来自哪个panel
	 * @param maxFilesize 最大文件数量
	 * @param acceptedFiles 接受的类型
	 */
	@RequestMapping(value = "/dialogToAdd",method = RequestMethod.GET)
	public void dialogToAdd(HttpServletRequest request,
			@RequestParam(value = "selectlist") String selectlist,
			@RequestParam(value = "formTargetPanel")String formTargetPanel,
			@RequestParam(value = "maxFilesize")String maxFilesize,
			@RequestParam(value = "acceptedFiles")String acceptedFiles
			){
		request.setAttribute("selectlist", selectlist);
		request.setAttribute("formTargetPanel", formTargetPanel);
		request.setAttribute("maxFilesize", maxFilesize);
		request.setAttribute("acceptedFiles", acceptedFiles);
	}
	
	/**
	 * 文件上传结果
	 * @author huqiao
	 */
	private class UploadResult{
		private boolean success;
		private String file_key;
		
		public UploadResult(boolean success, String file_key) {
			this.success = success;
			this.file_key = file_key;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getFile_key() {
			return file_key;
		}
		public void setFile_key(String file_key) {
			this.file_key = file_key;
		}
	}

	
	/**
	 * 修改文件页面
	 * @param id 对象id
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateUI(@RequestParam(value = "id") Integer id, HttpServletRequest request) {
		// 复杂关联关系数据准备
		List<CommonFolder> commonFolderList = commonFolderService.findAll(CommonFolder.class);
		request.setAttribute("commonFolderList", commonFolderList);

		// findAndSaveParams(request);
	}

	/**
	 * 修改文件
	 * @param rel 当前页面与href所指定文档的关系
	 * @param filee 待修改的实体对象
	 * @param result  BindingResult对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@RequestParam("rel") String rel, @ModelAttribute CommonFile filee, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		if (filee.getFolder().getId() == null) {
			filee.setFolder(null);
		}
		fileeService.update(filee);
		jsonResult.setNavTabId(rel);
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}

	/**
	 * 删除单个文件对象
	 * @param ids 要删除文件id数组
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(Integer[] ids) {
		for(Integer id : ids){
			CommonFile filee = fileeService.loadById(CommonFile.class, id);
			fileeService.delete(filee);
		}
		JsonResult jsonResult = new JsonResult();
		jsonResult.setMessage("删除成功");
		return jsonResult;
	}
	
	/**
	 * 下载文件
	 * @param key 下载文件的唯一标识 
	 * @param response HttpServletResponse 对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult downloadFile(@RequestParam("key") String key, HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setCallbackType("");
		if("".equals(key)|| key == null){
			jsonResult.setMessage("下载失败，文件未找到");
			return jsonResult;
		}else{
			if(fileeService.downLoadFile(response,key)){
				jsonResult.setMessage("下载成功");
			}else{
				jsonResult.setStatusCode("300");
				jsonResult.setMessage("下载文件失败，文件不存在！");
			}
		}
		return jsonResult;
	}
	
	@RequestMapping(value = "/selectList",params = "htmlType")
	public String selectListWithHtml(HttpServletRequest request,
			@RequestParam(value = "manageKey",required = false)String[] manageKeys){
		List<CommonFile> commonFiles = new ArrayList<CommonFile>();
		if(manageKeys!=null){
			for(String manageKey : manageKeys){
				CommonFile tmpCommonFile = fileeService.getEntityByProperty(CommonFile.class, "manageKey", manageKey);
				if(tmpCommonFile!=null && !commonFiles.contains(tmpCommonFile)){
					commonFiles.add(tmpCommonFile);
				}
			}
		}
		request.setAttribute("showCheckBox",request.getParameter("showCheckBox"));
		request.setAttribute("keyName",request.getParameter("keyName"));
		request.setAttribute("commonFiles",commonFiles);
		return "filee/select-list-html";
	}
   /**
    * 查看文件
    * @param manageKey 要查看的文件manageKey
    * @param request HttpServletRequest对象
    * @param response HttpServletResponse对象
    * @return String null
    */
	@RequestMapping(value = "/viewPic", method = RequestMethod.GET)
	public String viewPic(@RequestParam(value = "manageKey")String manageKey,HttpServletRequest request,HttpServletResponse response){
		
		CommonFile filee = fileeService.getEntityByProperty(CommonFile.class, "manageKey", manageKey);
		if(filee==null){
			return null;
		}
		
		response.setCharacterEncoding("UTF-8");
		///response.setHeader("Info", filee.getFullName());
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		try {
			OutputStream ops = response.getOutputStream();
			logger.info("read file from " + filee.getFullName());
			FileInputStream fis = new FileInputStream(new File(filee.getFullName())); 
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[1024];
			int i;
			while((i=bis.read(buffer))!=-1){
				ops.write(buffer,0,i);
			}
			ops.close();
			bis.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 在线编辑图像
	 * @param id 文件id
	 * @param request HttpServletRequest对象
	 * @param CKEditorFuncNum  编辑方法排序号
	 * @param type 类别
	 * @param multipartFile 大的图像
	 * @return ModelMap 对象
	 */
	
	@RequestMapping("/ckeditorupload")
    public ModelMap ckeditorUploadImage(
    		String id,HttpServletRequest request,
    		Integer CKEditorFuncNum,
            @RequestParam(value = "type")String type,
            @RequestParam(value="upload")MultipartFile multipartFile){
   	 
	   	String errorMessage = null;
	   	byte[] bytes = null;
		try {
			bytes = multipartFile.getBytes();
		} catch (IOException e1) {
			errorMessage = e1.getMessage();
			return new ModelMap("CKEditorFuncNum",CKEditorFuncNum)
            .addAttribute("errorMessage",errorMessage)
            .addAttribute("type",type);
		}
	   	
	   	CommonFolder folder = commonFolderService.getById(CommonFolder.class, 1);
		CommonFile filee = new CommonFile();
		filee.setInuse(UseStatus.UnUse);
		filee.setCreateDate(new Date());
		filee.setManageKey(Md5Util.getManageKey());
		filee.setExtensionName(FileUtil.getExtensionName(multipartFile.getOriginalFilename()));
		filee.setName(multipartFile.getOriginalFilename());
		try {
			FileUtil.saveFileOnDisk(folder.getStorePath(),filee.getManageKey(), bytes);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage =  e.getMessage();
		}
		filee.setStoreName("");
		filee.setFolder(folder);
		fileeService.add(filee);
        return new ModelMap("CKEditorFuncNum",CKEditorFuncNum)
                    .addAttribute("errorMessage",errorMessage)
                    .addAttribute("type",type)
                    .addAttribute("fileeManageKey",filee.getManageKey())
                    .addAttribute("oriFileName",filee.getName());
    }
	/**
	 * 根据关键字获取文件select2对象
	 * @param queryKey 查询关键字
	 * @param pageInfo 查询分页信息
	 * @param response HttpServletResponse对象
	 * @return Select2<CommonFile> 文件Select2对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody 
	public Select2<CommonFile> select2Query(String queryKey,Page<CommonFile> pageInfo, HttpServletResponse response) {
		Page<CommonFile> page = fileeService.queryByKey(queryKey, pageInfo);
		Select2<CommonFile> select2 = new Select2<CommonFile>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
}
