package me.huqiao.wss.expandimp;

/**
 * 验证检查结果
 * @author huqiao
 * @version 1.0
 */
public class ValidateResult {

	private Integer rowNum;
	private ValidateType type;
	private boolean validateSuccess;
	private String description;
	private String prop18nCode;
	private String strValue;
	
	public ValidateResult(Integer rowNum, String prop18nCode,String strValue,ValidateType type, boolean validateSuccess, String description) {
		this.rowNum = rowNum;
		this.type = type;
		this.validateSuccess = validateSuccess;
		this.description = description;
		this.prop18nCode = prop18nCode;
		this.strValue = strValue;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public ValidateType getType() {
		return type;
	}
	public void setType(ValidateType type) {
		this.type = type;
	}
	public boolean isValidateSuccess() {
		return validateSuccess;
	}
	public void setValidateSuccess(boolean validateSuccess) {
		this.validateSuccess = validateSuccess;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProp18nCode() {
		return prop18nCode;
	}
	public void setProp18nCode(String prop18nCode) {
		this.prop18nCode = prop18nCode;
	}
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	public String getCssClass(){
		if(validateSuccess){
			return "success";
		}
		if(type==ValidateType.DB_REPEAT || type==ValidateType.EXCEL_REPEAT){
			return "warning";
		}
		return "danger";
	}
	
	public String getValidateResultDesc(){
		if(validateSuccess){
			return "成功";
		}
		if(type==ValidateType.DB_REPEAT || type==ValidateType.EXCEL_REPEAT){
			return "警告";
		}
		return "错误";
	}
	@Override
	public String toString() {
		return "行号:" + rowNum + ", 类型:" + type.getDescription() + ", 结果:" + (validateSuccess ? "成功" : "失败") + ", 说明:" + description + "";
	}
}
