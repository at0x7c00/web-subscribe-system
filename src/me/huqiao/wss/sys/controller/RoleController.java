package me.huqiao.wss.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.Role;
import me.huqiao.wss.sys.entity.enumtype.RoleType;
import me.huqiao.wss.sys.service.IFunctionPointService;
import me.huqiao.wss.sys.service.IRoleService;
import me.huqiao.wss.util.StringUtil;
import me.huqiao.wss.util.web.JsonResult;
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
 * 角色Controller
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	@Resource
	private IRoleService roleService;

	/**
	 * 注册属性编辑器
	 * 
	 * @param binder
	 *            数据绑定器
	 */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		// 注册属性编辑器

	}

	// 复杂关联关系的Service
	@Resource
	private IFunctionPointService functionPointService;

	/**
	 * 初始化ModelAttribute
	 * 
	 * @param id
	 *            角色ID
	 * @return model 页面model对象
	 */
	@ModelAttribute
	public Role initModelAttribute(@RequestParam(value = "id", required = false) Integer id) {
		Role role = null;
		if (id == null || id.equals("")) {
			role = new Role();
		} else {
			role = roleService.getById(Role.class, id);
		}
		return role;
	}

	/**
	 * 角色列表
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param role
	 *            角色
	 * @param pageInfo
	 *            分页查询对象
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, Role role, Page pageInfo) {
		Page<Role> rolePage = roleService.getPage(role, pageInfo);
		request.setAttribute("pageBean", rolePage);
		request.setAttribute("roleTypeMap", RoleType.roleTypeMap);
	}

	/**
	 * 添加角色页面
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addUI(HttpServletRequest request) {
		request.setAttribute("roleTypeMap", RoleType.roleTypeMap);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 *            角色对象
	 * @param result
	 *            BindingResult 对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@Valid @ModelAttribute("role") Role role, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		if (StringUtil.isNotEmpty(role.getName())) {
			List<Role> roles = roleService.getByProperty(Role.class, "name", role.getName());
			if (roles.size() > 0) {
				jsonResult.setStatusCode("300");
				jsonResult.setMessage("角色名称重复");
				return jsonResult;
			}
		}
		// 默认系统时间类型保存
		roleService.add(role);
		jsonResult.setMessage("添加成功");
		return jsonResult;
	}

	/**
	 * 修改角色页面
	 * 
	 * @param id
	 *            要修改的角色ID
	 * @param request
	 *            HttpServletRequest对象
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateUI(@RequestParam(value = "id") Integer id, HttpServletRequest request) {
		request.setAttribute("roleTypeMap", RoleType.roleTypeMap);
	}

	/**
	 * 设置权限页面
	 * 
	 * @param id
	 *            设置权限的角色ID
	 * @param request
	 *            HttpServletRequest对象
	 */
	@RequestMapping(value = "/setPrivilege", method = RequestMethod.GET)
	public void setPrivilegeUI(@RequestParam(value = "id") Integer id, HttpServletRequest request) {

		Role role = roleService.getById(Role.class, id);
		request.setAttribute("role", role);
		List<FunctionPoint> topFunctionPoints = functionPointService.getTopLevelFunctionPoints();

		request.setAttribute("topFunctionPoints", topFunctionPoints);
		request.setAttribute("zTreeJson", createFunctionPointsJsonStr(topFunctionPoints, role));
		// findAndSaveParams(request);
	}

	/**
	 * 获取角色权限的json 格式的字符串
	 * 
	 * @param topFunctionPoints
	 *            角色的集合
	 * @param role
	 *            角色
	 * @return String 权限的json格式字符串
	 */
	private String createFunctionPointsJsonStr(List<FunctionPoint> topFunctionPoints, Role role) {
		StringBuffer res = new StringBuffer();
		for (FunctionPoint parent : topFunctionPoints) {
			res.append(parent.getZTreeJson(role));
		}
		return res.toString();
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 *            角色对象
	 * @param result
	 *            BindingResult对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@ModelAttribute Role role, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		roleService.update(role);
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}

	/**
	 * 为角色设置权限
	 * 
	 * @param rel
	 *            当前页面与href所指定文档的关系
	 * @param functionPointIds
	 *            权限ID 集合
	 * @param role
	 *            要设置权限的角色对象
	 * @param result
	 *            BindingResult
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/setPrivilege", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult setPrivilege(@RequestParam("rel") String rel, @RequestParam(value = "functionPointIds", required = false) Integer[] functionPointIds, @ModelAttribute Role role, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		roleService.setPrivilege(role, functionPointIds);
		jsonResult.setNavTabId(rel);
		jsonResult.setMessage("设置成功!");
		return jsonResult;
	}

	/**
	 * 删除单个角色
	 * 
	 * @param rel
	 *            当前页面与href所指定文档的关系
	 * @param role
	 *            删除的角色对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(@RequestParam("rel") String rel, @ModelAttribute Role role) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setCallbackType("");
		try {
			roleService.delete(role);
		} catch (RuntimeException re) {
			jsonResult.setMessage("该记录已被使用，不能删除！");
			return jsonResult;
		} catch (Exception e) {
			jsonResult.setMessage(e.toString());
			return jsonResult;
		}
		jsonResult.setNavTabId(rel);
		jsonResult.setMessage("删除成功");
		return jsonResult;
	}
	/**
	 * 删除多个
	 * 
	 * @param uids
	 * @return 2013-05-21 16:50:41
	 * @RequestMapping(value="/delete",method=RequestMethod.POST)
	 * @ResponseBody public JsonResult batchDelete(@RequestParam("ids")
	 *               Integer[] ids) { Role role = null; for(Integer id : ids){
	 *               role = roleService.loadById(Role.class, id);
	 *               roleService.delete(role); } JsonResult jsonResult = new
	 *               JsonResult(); jsonResult.setMessage("删除成功"); return
	 *               jsonResult; }
	 */
}
