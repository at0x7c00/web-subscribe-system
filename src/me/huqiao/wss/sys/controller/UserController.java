package me.huqiao.wss.sys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.service.ICommonFileService;
import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.sys.entity.Role;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.sys.entity.enumtype.UserStatus;
import me.huqiao.wss.sys.entity.enumtype.UserType;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.sys.entity.propertyeditor.DepartmentEditor;
import me.huqiao.wss.sys.service.IConfigService;
import me.huqiao.wss.sys.service.IDepartmentService;
import me.huqiao.wss.sys.service.IRoleService;
import me.huqiao.wss.sys.service.IUserService;
import me.huqiao.wss.util.Constants;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.web.JsonResult;
import me.huqiao.wss.util.web.LoginInfo;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;
	
	@Resource
	private ICommonFileService fileeService;
	@Resource
	private IDepartmentService departmentService;
    /**
     *  注册属性编辑器
     * @param binder 数据绑定器
     */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		binder.registerCustomEditor(Department.class, new DepartmentEditor(departmentService));
		// 注册属性编辑器
	}

	@Resource
	private IRoleService roleService;
	@Resource
	private IConfigService configService;
    /**
     * 初始化ModelAttribute
     * @param userName 用户名
     * @return model 页面model 对象
     */
	@ModelAttribute
	public User initModelAttribute(@RequestParam(value = "userName", required = false) String userName) {
		User user = null;
		if (userName == null || userName.equals("")) {
			user = new User();
		} else {
			user = userService.getById(User.class, userName);
		}
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * 用户列表分页查询
	 * @param request HttpServletRequest对象
	 * @param roleId  角色ID
	 * @param deptRights 部门权限范围
	 * @param pageInfo 分页查询对象
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, @RequestParam(value = "roleId", required = false) String roleId, @RequestParam(value = "deptRights", required = false) String deptRights, User user, Page pageInfo) {
		// createTestData();
		if (!"".equals(roleId) && roleId != null) {
			user.setRoleId(roleId);
			request.setAttribute("roleId", roleId);
		}
		if (!"".equals(deptRights) && deptRights != null) {
			user.setDeptRights(deptRights);
			request.setAttribute("deptRights", deptRights);
		}
		Page<User> userPage = userService.getPage(user, pageInfo);
		request.setAttribute("pageBean", userPage);
		// 复杂关联关系数据准备
		request.setAttribute("userStatusMap", UserStatus.userStatusMap);
		request.setAttribute("userTypeMap", UserType.userTypeMap);
	}
   
/*	public static String aa(String str, int i) {
		if ((i + "").length() > str.length()) {
			return i + "";
		}
		int oldlen = str.length();
		str += i;
		str = str.substring(str.length() - oldlen);
		return str;
	}*/

	/**
	 * 添加用户页面
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addUI(HttpServletRequest request) {
		// 复杂关联关系数据准备
		request.setAttribute("userStatusMap", UserStatus.userStatusMap);
		request.setAttribute("yesNoMap", YesNo.yesNoMap);
		List<Department> departmentList = departmentService.getByProperties(Department.class, new String[] { "status" }, new Object[] { UseStatus.InUse }, "sort", null);
		request.setAttribute("departmentList", departmentList);
		
	}

	/**
	 * 添加用户
	 * @param request HttpServletRequest对象
	 * @param user 要添加的对象
	 * @param result BindingResult对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(HttpServletRequest request, @Valid @ModelAttribute("user") User user, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		if (user!=null && user.getUsername()!=null && !("").equals(user.getUsername())) {
			List<User> checkUser = userService.getByProperty(User.class, "username", user.getUsername());
			if (checkUser.size()>0) {
				jsonResult.setStatusCode("300");
				jsonResult.setCallbackType("");
				jsonResult.setMessage("Repeated User!");
				return jsonResult;
			}
			user.setPassword(Md5Util.getMD5Code(user.getTextpwd()));
			Role role = roleService.getById(Role.class, "");
			if (role != null) {
				Set<Role> roles = new HashSet<Role>();
				roles.add(role);
				user.setRoles(roles);
			}
			
			userService.add(user);
			jsonResult.setMessage("添加成功");
			return jsonResult;
		}else {
			jsonResult.setStatusCode("300");
			jsonResult.setCallbackType("");
			jsonResult.setMessage("Empty User!");
			return jsonResult;
		}
	}
	

	/**
	 * 修改用户页面
	 * @param userName 用户名
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateUI(@RequestParam(value = "userName") String userName, HttpServletRequest request) {
		request.setAttribute("userStatusMap", UserStatus.userStatusMap);
		request.setAttribute("yesNoMap", YesNo.yesNoMap);
		List<Department> departmentList = departmentService.getByProperties(Department.class, new String[] { "status" }, new Object[] { UseStatus.InUse }, "sort", null);
		request.setAttribute("departmentList", departmentList);
	}
	/**
	 * 查看用户信息
	 * @param userName 用户名
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/detail",method= RequestMethod.GET)
	public void detail(@RequestParam(value = "userName") String userName, HttpServletRequest request){
		request.setAttribute("userStatusMap", UserStatus.userStatusMap);
		request.setAttribute("yesNoMap", YesNo.yesNoMap);
	}
	
	/**
	 * 修改用户
	 * @param user 修改对象
	 * @param result BindingResult对象
	 * @param textpwd 密码
	 * @return 2013-05-21 16:50:41
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@ModelAttribute User user, BindingResult result,@RequestParam(value = "textpwd" ,required = true)String textpwd) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		user.setPassword(Md5Util.getMD5Code(textpwd));
		userService.update(user);
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}
   /**
    * 修改密码
    * @param request HttpServletRequest对象
    */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public void updatePasswordUI(HttpServletRequest request) {
	}
  /**
   * 修改密码 
   * @param encOldPassFromPage 旧的密码
   * @param encNewPassFromPage 新的密码
   * @return 操作结果 
   */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updatePassword( String encOldPassFromPage, String encNewPassFromPage) {
		User user = getCurrentUser();
		JsonResult jsonResult = new JsonResult();
		//jsonResult.setNavTabId(rel);
		String pwdMd5InDb = user.getPassword();
		String suffix = " ";
		if (!Md5Util.checkPasswordWithPwdEncoded(encOldPassFromPage, suffix, pwdMd5InDb)) {
			jsonResult.setStatusCode("300");
			jsonResult.setCallbackType("");
			jsonResult.setMessage("原密码输入错误");
			return jsonResult;
		}
		user.setPassword(encNewPassFromPage);
		userService.update(user);
		freshLoginInfo(user);
		//jsonResult.setStatusCode("200");
		//jsonResult.setCallbackType("closeCurrent");
		jsonResult.setCallbackType("none");
		jsonResult.setMessage("密码修改成功");
		return jsonResult;
	}
	
   /**
    * 刷新用户信息
    * @param user 用户对象
    */
	private void freshLoginInfo(User user) {
		LoginInfo loginInfo = (LoginInfo) getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION);
		loginInfo.setUser(user);
		setSessionAttribute(Constants.LOGIN_INFO_IN_SESSION, loginInfo);
	}

	/**
	 * 设置角色页面
	 * @param userName 用户名
	 * @param request  HttpServletRequest对象
	 */
	@RequestMapping(value = "/setRole", method = RequestMethod.GET)
	public void setRoleUI(@RequestParam(value = "userName") String userName, HttpServletRequest request) {
		request.setAttribute("userStatusMap", UserStatus.userStatusMap);
		request.setAttribute("roleList", roleService.findAll(Role.class));
	}

	/**
	 * 为用户设置角色
	 * @param roleIds 角色ID数组
	 * @param groupIds 群组ID数组
	 * @param req HttpServletRequest对象
	 * @param user 用户对象
	 * @param result BindingResult对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "/setRole", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult setRole(@RequestParam(value = "roleIds", required = false) Integer[] roleIds,
			@RequestParam(value = "areaIds", required = false) Integer[] areaIds,
			HttpServletRequest req,
			@ModelAttribute User user, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		userService.setRoles(user, roleIds);
		jsonResult.setMessage("角色设置成功!");
		return jsonResult;
	}

	/**
	 * 删除多个用户
	 * @param usernames 用户名数组
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@RequestParam("usernames") String[] usernames) {
		StringBuffer error = new StringBuffer();
		JsonResult jsonResult = new JsonResult();
		for(String username : usernames){
			User user = userService.getById(User.class, username);
			try {
				userService.delete(user);
			} catch (Exception e) {
				error.append(username+";");
			}
		}
		if (error!=null &&!("").equals(error.toString())){
			error.append("已被应用，不能被删除！");
			jsonResult.setCallbackType("");
			jsonResult.setMessage(error.toString());
		}else {
			jsonResult.setCallbackType("");
			jsonResult.setMessage("删除成功");
			
		}
		return jsonResult;
	}
	/**
	 * 选择所有用户列表
	 * @param request HttpServletRequest
	 * @param user 用户对象
	 * @param pageInfo 分页查询对象
	 * @return String 显示页面路径
	 */
	@RequestMapping(value = "/selectAllList")
	public String selectAllList(HttpServletRequest request, User user,Page pageInfo) {
		user.setStatus(UserStatus.Active);
		Page<User> employeePage = userService.getPage(user,pageInfo);
		request.setAttribute("pageBean", employeePage);
		
		request.setAttribute("multiSelect",request.getParameter("multiSelect"));
    	request.setAttribute("selectedItem",request.getParameter("selectedItem"));
    	request.setAttribute("type",request.getParameter("type"));
    	request.setAttribute("targetType","dialog");
    	
		return "admin/user/selectList";
	}
	
	/**
	 * 获取用户
	 * @param queryKey 查询关键字
	 * @param pageInfo 分页查询对象
	 * @param response HttpServletResponse
	 * @return Select2<User> 查询对象
	 */
	@RequestMapping(value="/select2Query")
	@ResponseBody
	public Select2<User> select2Query(String queryKey,Page<User> pageInfo, HttpServletResponse response) {
		Page<User> page = userService.queryByKey(queryKey, pageInfo);
		Select2<User> select2 = new Select2<User>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}
	
	/**
	 * 根据用户名获取用户列表
	 * @param username 用户名数组
	 * @param response HttpServletResponse
	 */
	@RequestMapping(value="/queryById")
	@ResponseBody
	public List<User> queryById(String[] username,HttpServletResponse response) {
		List<User> userList = userService.queryById(username);
		return userList;
	}
	@RequestMapping(value="/myfile")
	public void myfileUI( HttpServletRequest request){
		
	}
	
}
