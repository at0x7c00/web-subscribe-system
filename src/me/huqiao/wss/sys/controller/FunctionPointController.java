package me.huqiao.wss.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import me.huqiao.wss.common.controller.BaseController;
import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.propertyeditor.FunctionPointEditor;
import me.huqiao.wss.sys.service.IFunctionPointService;
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
 * 权限Controller
 * @author NOVOTS
 * @version Version 1.0
 */
@Controller
@RequestMapping("/functionPoint")
public class FunctionPointController extends BaseController {

	@Resource
	private IFunctionPointService functionPointService;

	/**
	 * 注册属性编辑器
	 * @param binder 数据绑定器
	 */
	@InitBinder
	public void initPropEditor(WebDataBinder binder) {
		binder.registerCustomEditor(FunctionPoint.class, new FunctionPointEditor(functionPointService));
	}

	/**
	 * 初始化ModelAttribute
	 * @param id
	 * @return model对象
	 */
	@ModelAttribute
	public FunctionPoint initModelAttribute(@RequestParam(value = "id", required = false) Integer id) {
		FunctionPoint functionPoint = null;
		if (id == null) {
			functionPoint = new FunctionPoint();
		} else {
			functionPoint = functionPointService.getById(FunctionPoint.class, id);
			// functionPoint.setParent(null);
		}
		return functionPoint;
	}

	/**
	 *  权限列表
	 * @param request HttpServletRequest对象
	 * @param functionPoint 权限对象
	 * @param pageInfo 分页查询对象
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, FunctionPoint functionPoint, Page pageInfo) {
		List<FunctionPoint> list = new ArrayList<FunctionPoint>();
		functionPointService.tree(functionPointService.getTopLevelFunctionPoints(), "", list, null);
		request.setAttribute("list", list);
		// findAndSaveParams(request);
	}

	/**
	 * 添加权限页面
	 * @param request HttpServletRequest 对象
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addUI(HttpServletRequest request) {
		List<FunctionPoint> topPointList = functionPointService.getTopLevelFunctionPoints();
		List<FunctionPoint> functionPointList = new ArrayList<FunctionPoint>();
		functionPointService.tree(topPointList, "", functionPointList, null);
		request.setAttribute("functionPointList", functionPointList);
		// findAndSaveParams(request);
	}

	/**
	 * 添加权限
	 * @param functionPoint 权限对象
	 * @param result BindingResult对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@Valid @ModelAttribute("functionPoint") FunctionPoint functionPoint, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}

		showSql(functionPoint, 1);
		functionPointService.add(functionPoint);
		jsonResult.setMessage("添加成功");
		return jsonResult;
	}

	/**
	 * 显示sql语句 
	 * @param functionPoint 权限对象
	 * @param i 区分添加，修改，删除
	 */
	private void showSql(FunctionPoint functionPoint, int i) {
		StringBuffer result = new StringBuffer();
		switch (i) {
		case 1:// 添加
			result.append("INSERT INTO `sys_function_point` (`id`, `icon`, `is_display`, `name`, `order_num`, `url`, `parent`) VALUES ").append("('").append(functionPointService.getNextId())
					.append("','").append(functionPoint.getIcon()).append("', '").append(functionPoint.getIsDisplay()).append("', '").append(functionPoint.getName()).append("', '")
					.append(functionPoint.getOrderNum()).append("', '").append(functionPoint.getUrl()).append("', ")
					.append(functionPoint.getParent() == null ? null : "'" + functionPoint.getParent().getId() + "'").append(");");
			break;
		case 2:// 修改
			result.append("UPDATE `sys_function_point` SET ");
			result.append("`icon`='").append(functionPoint.getIcon()).append("',`is_display`=").append("'").append(functionPoint.getIsDisplay()).append("',`name`='").append(functionPoint.getName())
					.append("',`order_num`='").append(functionPoint.getOrderNum()).append("',`url`='").append(functionPoint.getUrl()).append("',`parent`=")
					.append(functionPoint.getParent() == null ? null : "'" + functionPoint.getParent().getId() + "'");
			result.append(" WHERE (`id`='").append(functionPoint.getId()).append("');");
			break;
		case 3:// 删除
			result.append("delete from `sys_function_point` where id = ").append(functionPoint.getId()).append(";");
			break;
		}
		System.out.println("SQL>>>" + result.toString());
	}

	/**
	 * 修改权限页面
	 * @param id 权限ID 
	 * @param request HttpServletRequest对象
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateUI(@RequestParam(value = "id") Integer id, HttpServletRequest request) {
		List<FunctionPoint> topPointList = functionPointService.getTopLevelFunctionPoints();
		List<FunctionPoint> functionPointList = new ArrayList<FunctionPoint>();
		functionPointService.tree(topPointList, "", functionPointList, id);
		request.setAttribute("functionPointList", functionPointList);
		// findAndSaveParams(request);
	}

	/**
	 * 修改权限
	 * @param functionPoint 权限对象
	 * @param result BindingResult 对象
	 * @return JsonResult 操作结果 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@ModelAttribute FunctionPoint functionPoint, BindingResult result) {
		JsonResult jsonResult = new JsonResult();
		if (!validate(jsonResult, result)) {
			return jsonResult;
		}
		showSql(functionPoint, 2);
		functionPointService.update(functionPoint);
		jsonResult.setMessage("修改成功");
		return jsonResult;
	}

	/**
	 * 删除单个
	 * @param rel 当前页面与href所指定文档的关系
	 * @param functionPoint 权限对象
	 * @return JsonResult 操作结果
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(@RequestParam("rel") String rel, @ModelAttribute FunctionPoint functionPoint) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setCallbackType("");
		try {
			showSql(functionPoint, 3);
			functionPointService.delete(functionPoint);
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
	 * @return 2013-05-16 17:10:19
	 * @RequestMapping(value="/delete",method=RequestMethod.POST)
	 * @ResponseBody public JsonResult batchDelete(@RequestParam("ids")
	 *               Integer[] ids) { FunctionPoint functionPoint = null;
	 *               for(Integer id : ids){ functionPoint =
	 *               functionPointService.loadById(FunctionPoint.class, id);
	 *               functionPointService.delete(functionPoint); } JsonResult
	 *               jsonResult = new JsonResult();
	 *               jsonResult.setMessage("删除成功"); return jsonResult; }
	 */

}
