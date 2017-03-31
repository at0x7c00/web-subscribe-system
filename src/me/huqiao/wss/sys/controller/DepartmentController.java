package me.huqiao.wss.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.common.entity.Select2;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.sys.entity.propertyeditor.DepartmentEditor;
import me.huqiao.wss.sys.service.IDepartmentService;
import me.huqiao.wss.util.Md5Util;
import me.huqiao.wss.util.bean.BeanPropUtil;
import me.huqiao.wss.util.bean.CheckResult;
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
 * 部门控制器
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
	/** 部门服务 */
	@Resource
	private IDepartmentService departmentService;

	/**
	 * 注册属性编辑器
	 * 
	 * @param binder
	 *            数据绑定器
	 */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		binder.registerCustomEditor(Department.class, new DepartmentEditor(departmentService));
	}

	// 复杂关联关系的Service
	/**
	 * 初始化ModelAttribute
	 * 
	 * @param manageKey
	 *            md5管理ID （非空时自动加载指定对象）
	 * @param model
	 *            页面model对象
	 * @return Department 部门对象
	 */
	@ModelAttribute(value = "department")
	public Department initModelAttribute(@RequestParam(value = "manageKey", required = false) String manageKey, Model model) {
		Department department = null;
		if (manageKey == null || manageKey.equals("")) {
			department = new Department();
		} else {
			department = departmentService.getEntityByProperty(Department.class, "manageKey", manageKey);
			if (department == null) {
				department = new Department();
			}
		}
		return department;
	}

	/**
	 * 部门分页查询
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param department
	 *            部门查询对象
	 * @param pageInfo
	 *            分页查询对象
	 * 
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, Department department, Page pageInfo) {
		Page<Department> departmentPage = departmentService.getListPage(department, pageInfo);
		request.setAttribute("pageBean", departmentPage);
		listFormParam(request, department, pageInfo);
	}

	/**
	 * 为部门分页查询表单准备数据
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param department
	 *            部门查询对象
	 * @param pageInfo
	 *            分页查询对象
	 * 
	 */
	public void listFormParam(HttpServletRequest request, Department department, Page pageInfo) {
		// 复杂关联关系数据准备
		request.setAttribute("useStatusMap", UseStatus.useStatusMap);
		
		List<Department> topDepartmentList = departmentService.getByProperties(Department.class, new String[] { "status","parent" }, new Object[] { UseStatus.InUse,null}, "sort", null);
		List<Department> departmentList = new ArrayList<Department>();
		departmentService.tree(topDepartmentList,"",departmentList,null);
		request.setAttribute("departmentList", departmentList);
		
		
		
		
	}

	/**
	 * 添加部门页面
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param callBack
	 *            回调函数名称
	 * 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addUI(HttpServletRequest request, @RequestParam(value = "callBack", required = false) String callBack) {
		// 复杂关联关系数据准备
		request.setAttribute("useStatusMap", UseStatus.useStatusMap);
		
		List<Department> topDepartmentList = departmentService.getByProperties(Department.class, new String[] { "status","parent" }, new Object[] { UseStatus.InUse,null}, "sort", null);
		List<Department> departmentList = new ArrayList<Department>();
		departmentService.tree(topDepartmentList,"",departmentList,null);
		request.setAttribute("departmentList", departmentList);
		// clearTempDataList(request.getSession(),"department");
		request.setAttribute("callBack", callBack);
	}

	/**
	 * 添加部门
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param department
	 *            要添加的对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(HttpServletRequest request, @Valid @ModelAttribute("department") Department department, @RequestParam(value = "callBack", required = false) String callBack, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		// 默认系统时间类型保存
		/*
		 * #ONE_TO_MANY_VALUE_SAVE_ADD
		 */
		// 保存多对多关联关系
		// 保持一对多关联关系 
		List<Department> departments = departmentService.getByProperty(Department.class, "name", department.getName());
		if(departments.size()>0){
			jsonResult.setStatusCode("300");
			jsonResult.setMessage("部门名称重复");
			return jsonResult;
		}
		department.setManageKey(Md5Util.getManageKey());
		departmentService.add(department);
		jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.add.success"));
		return jsonResult;
	}

	/**
	 * 修改部门页面
	 * 
	 * @param department
	 *            需要修改的对象实体
	 * @param request
	 *            HttpServletRequest请求对象
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateUI(@ModelAttribute(value = "department")Department department, HttpServletRequest request) {
		request.setAttribute("tempBean", department);
		// 复杂关联关系数据准备
		request.setAttribute("useStatusMap", UseStatus.useStatusMap);
		
		List<Department> topDepartmentList = departmentService.getByProperties(Department.class, new String[] { "status","parent" }, new Object[] { UseStatus.InUse,null}, "sort", null);
		List<Department> departmentList = new ArrayList<Department>();
		departmentService.tree(topDepartmentList,"",departmentList,null);
		
		if (department.getParent() != null && department.getParent().getStatus() == UseStatus.UnUse) {
			departmentList.add(department.getParent());
		}
		request.setAttribute("departmentList", departmentList);
		// clearTempDataList(request.getSession(),"department");
	}

	/**
	 * 修改部门
	 * 
	 * @param department
	 *            待修改的实体对象
	 * @param request
	 *            HttpServletRequest对象
	 * @return JsonResult 操作结果
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(HttpServletRequest request, @ModelAttribute(value = "department") Department department, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		// 保存多对多关联关系
		// 保持一对多关联关系
		departmentService.update(department);
		// jsonResult.setNavTabId(rel);
		jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.update.success"));
		return jsonResult;
	}

	/**
	 * 查看部门页面
	 * 
	 * @param department
	 *            需要查看的实体对象
	 * @param request
	 *            HttpServletRequest对象
	 * 
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail(@ModelAttribute(value = "department")Department department, HttpServletRequest request) {
		request.setAttribute("tempBean", department);
		// 复杂关联关系数据准备
		listFormParam(request, department, null);
	}

	/**
	 * 删除单个部门对象
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param department
	 *            待删除对象
	 * @return JsonResult 操作结果
	 * 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(HttpServletRequest request, @ModelAttribute(value = "department") Department department) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setCallbackType("");
		try {
			departmentService.delete(department);
		} catch (RuntimeException re) {
			jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.inuse"));jsonResult.setStatusCode("300");
			return jsonResult;
		} catch (Exception e) {
			jsonResult.setMessage(e.toString());
			return jsonResult;
		}
		// jsonResult.setNavTabId(rel);
		jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.success"));
		return jsonResult;
	}

	/**
	 * 删除多个对象
	 * 
	 * @param manageKeys
	 *            唯一识别ID数组
	 * @return JsonResult 操作结果
	 * 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult batchDelete(HttpServletRequest request, @RequestParam("manageKeys") String[] manageKeys) {
		Department department = null;
		JsonResult jsonResult = new JsonResult();
		for (String manageKey : manageKeys) {
			try {
				department = departmentService.getEntityByProperty(Department.class, "manageKey", manageKey);
				departmentService.delete(department);
			} catch (RuntimeException re) {
				jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.inuse"));jsonResult.setStatusCode("300");
				return jsonResult;
			} catch (Exception e) {
				jsonResult.setMessage(e.toString());
				return jsonResult;
			}
		}
		jsonResult.setMessage(getI18NMessage(request, "base.common.controller.operate.delete.success"));
		return jsonResult;
	}

	/**
	 * 选择对象返回html
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param manageKeys
	 *            manageKey 数组
	 * @return String 返回jsp页面路径
	 */
	@RequestMapping(value = "/selectList", params = "htmlType")
	public String selectListWithHtml(HttpServletRequest request, @RequestParam(value = "manageKey", required = false) String[] manageKeys) {
		List<Department> departments = new ArrayList<Department>();
		if (manageKeys != null) {
			for (String manageKey : manageKeys) {
				Department temp_department = departmentService.getEntityByProperty(Department.class, "manageKey", manageKey);
				if (temp_department != null && !departments.contains(temp_department)) {
					departments.add(temp_department);
				}
			}
		}
		request.setAttribute("showCheckBox", request.getParameter("showCheckBox"));
		request.setAttribute("keyName", request.getParameter("keyName"));
		request.setAttribute("departments", departments);
		return "department/select-list-html";
	}

	/**
	 * 历史记录列表
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param department
	 *            查询对象
	 * @param pageInfo
	 *            分页查询对象
	 * @return String 显示页面路径
	 * 
	 */
	@RequestMapping(value = "history", params = "list")
	public String historyList(HttpServletRequest request, Department department, Page pageInfo) {
		Page<HistoryRecord<Department>> departmentPage = departmentService.getHistoryListPage(department, pageInfo);
		request.setAttribute("pageBean", departmentPage);
		request.setAttribute("manageKey", department.getManageKey());
		return "department/history-list";
	}

	/**
	 * 查看历史记录
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param version
	 *            版本号
	 * @return String 显示页面路径
	 * 
	 */
	@RequestMapping(value = "history", params = "view")
	public String historyView(HttpServletRequest request, @RequestParam(value = "version") Integer version) {
		Department department = departmentService.findByVersion(version);
		Department preDepartment = (Department) departmentService.findByPreVersion(Department.class, department.getManageKey(), version);
		if (preDepartment != null && preDepartment.getManageKey().equals(department.getManageKey())) {
			Map<String, CheckResult> checkResult = BeanPropUtil.propValueCheck(preDepartment, department);
			request.setAttribute("checkResult", checkResult);
		}
		request.setAttribute("tempBean", department);
		request.setAttribute("showOk", "no");
		request.setAttribute("historyView", true);
		return "department/detail";
	}

	/**
	 * 根据关键字获取部门select2对象
	 * 
	 * @param queryKey
	 *            查询关键字
	 * @param pageInfo
	 *            查询分页信息
	 * @param response
	 *            HttpServletResponse对象
	 * @return Select2<Department> 部门Select2对象
	 */
	@RequestMapping(value = "/select2Query")
	@ResponseBody
	public Select2<Department> select2Query(String queryKey, Page<Department> pageInfo, HttpServletResponse response) {
		Page<Department> page = departmentService.queryByKey(queryKey, pageInfo);
		Select2<Department> select2 = new Select2<Department>();
		select2.setTotal_count(page.getTotalCount());
		select2.setItems(page.getList());
		return select2;
	}

	/**
	 * ���找多个部门
	 * 
	 * @param ids
	 *            id数组
	 * @param response
	 *            HttpServletResponse 对象
	 * @return List<Department> 部门列表
	 */
	@RequestMapping(value = "/queryById")
	@ResponseBody
	public List<Department> queryById(Integer[] ids, HttpServletResponse response) {
		List<Department> departmentList = departmentService.queryById(ids);
		return departmentList;
	}

	/**
	 * tab页添加表单
	 * 
	 * @param trTarget
	 *            tr的target值
	 * @param trIndex
	 *            tr的序号
	 * @param propName
	 *            表单元素name前缀
	 * @return String jsp路径
	 */
	@RequestMapping(value = "/tabAddForm")
	public String tabAddForm(@ModelAttribute(value = "department") Department department, @RequestParam(value = "trTarget") String trTarget, @RequestParam(value = "trIndex") Integer trIndex, @RequestParam(value = "propName") String propName, HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean", department);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "department/tab-add-form";
	}

	/**
	 * tab页查看详情页面
	 * 
	 * @param trTarget
	 *            tr的target值
	 * @param trIndex
	 *            tr的序号
	 * @param propName
	 *            表单元素name前缀
	 * @return String jsp路径
	 */
	@RequestMapping(value = "/tabDetailForm")
	public String tabDetailForm(@ModelAttribute(value = "department") Department department, @RequestParam(value = "trTarget") String trTarget, @RequestParam(value = "trIndex") Integer trIndex, @RequestParam(value = "propName") String propName, HttpServletRequest request) {
		addUI(request, null);
		request.setAttribute("tempBean", department);
		request.setAttribute("trTarget", trTarget);
		request.setAttribute("trIndex", trIndex);
		request.setAttribute("propName", propName);
		return "department/tab-detail-form";
	}

	@RequestMapping(value = "/selectTree")
	public String selectTree(HttpServletRequest request, @RequestParam(value = "fromPanel") String fromPanel, @RequestParam(value = "propName") String propName) {
		List<Department> categoryList = departmentService.getByProperties(Department.class, new String[] { "status" }, new Object[] { UseStatus.InUse }, "sort", null);
		request.setAttribute("dataList", categoryList);
		request.setAttribute("fromPanel", fromPanel);
		request.setAttribute("propName", propName);
		return "common/selectTree";
	}
}