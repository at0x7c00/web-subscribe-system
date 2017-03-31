package me.huqiao.wss.expandimp;

import java.util.ArrayList;
import java.util.List;

/**
 * 行验证结果集
 * @author huqiao
 */
public class RowValidateResult {
	
	private List<ValidateResult> validateResultList = new ArrayList<ValidateResult>();
	private int rowNum;
	/**
	 * 是否验证成功
	 * @return boolean 验证结果
	 */
	public boolean isValidateSuccess(){
		for(ValidateResult validateResult : validateResultList){
			if(!validateResult.isValidateSuccess()){
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取验证结果集合
	 * @return List<ValidateResult> 验证结果集合
	 */
	public List<ValidateResult> getValidateResultList() {
		return validateResultList;
	}

	/**
	 * 添加验证结果
	 * @param validateResult
	 */
	public void addValidateResult(ValidateResult validateResult) {
		this.validateResultList.add(validateResult);
	}

	/**
	 * 忽略重复验证时是否验证成功。该值作为是否提交数据的唯一凭证
	 * @return boolean 验证结果
	 */
	public boolean isValidateSuccessWhenIgnoreRepeat() {
		for(ValidateResult validateResult : validateResultList){
			if(validateResult.getType()!=ValidateType.EXCEL_REPEAT && validateResult.getType()!=ValidateType.DB_REPEAT && !validateResult.isValidateSuccess()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否是重复数据
	 * @return boolean 是否重复结果
	 */
	public boolean isRepeat(){
		for(ValidateResult validateResult : validateResultList){
			if((validateResult.getType()==ValidateType.EXCEL_REPEAT || validateResult.getType()==ValidateType.DB_REPEAT) && !validateResult.isValidateSuccess()){
				return true;
			}
		}
		return false;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}
