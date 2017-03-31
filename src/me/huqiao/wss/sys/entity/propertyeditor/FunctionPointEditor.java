package me.huqiao.wss.sys.entity.propertyeditor;

import java.beans.PropertyEditorSupport;

import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.service.IFunctionPointService;
/**
 * 功能点编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class FunctionPointEditor extends PropertyEditorSupport {
	private IFunctionPointService functionPointService;

	public FunctionPointEditor(IFunctionPointService functionPointService) {
		this.functionPointService = functionPointService;
	}

	public String getAsText() {
		FunctionPoint functionPoint = (FunctionPoint) getValue();
		if (functionPoint == null)
			return "";
		return String.valueOf(functionPoint.getId());
	}

	public void setAsText(String id) throws IllegalArgumentException {
		FunctionPoint functionPoint = new FunctionPoint();
		Integer integerId = null;
		try {
			integerId = Integer.parseInt(id);
		} catch (Exception e) {
		}
		functionPoint = functionPointService.getById(FunctionPoint.class,integerId);
		setValue(functionPoint);
	}
}