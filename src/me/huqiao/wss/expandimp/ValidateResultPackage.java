package me.huqiao.wss.expandimp;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证结果包
 * @author huqiao
 * @version 1.0
 */
public class ValidateResultPackage {

	private Map<Integer,RowValidateResult> validateResultListMap = new HashMap<Integer,RowValidateResult>();
	private int rowCount = 0;
	
	/**
	 * 添加验证结果
	 * @param rowNum
	 * @param validateResult
	 */
	public void addValidateResult(Integer rowNum,ValidateResult validateResult){
		getValidateResultList(rowNum).addValidateResult(validateResult); 
		recordRowNum(rowNum);
	}
	
	/**
	 * 记录行号
	 * @param rowNum
	 */
	private void recordRowNum(int rowNum){
		if(rowNum>rowCount){
			rowCount = rowNum;
		}
	}
	
	/**
	 * 获取行验证结果集合，不存在则创建一个返回
	 * @param rowNum 行数
	 * @return RowValidateResult 行验证结果
	 */
	private RowValidateResult getValidateResultList(Integer rowNum){
		RowValidateResult rowValidateReult = validateResultListMap.get(rowNum);
		if(rowValidateReult==null){
			rowValidateReult = new  RowValidateResult();
			rowValidateReult.setRowNum(rowNum);
			validateResultListMap.put(rowNum, rowValidateReult);
		}
		return rowValidateReult;
	}

	/**
	 * 获取到验证结果Map
	 * @return Map<Integer, RowValidateResult> 验证map
	 */
	public Map<Integer, RowValidateResult> getValidateResultListMap() {
		return validateResultListMap;
	}

	/**
	 * 获取最大行号
	 * @return int 行数
	 */
	public int getRowCount() {
		return rowCount;
	}
	
	/**
	 * 计算验证成功的数量
	 * @return int 成功的数量
	 */
	public int getValidateSuccessCount(){
		int i = 0;
		for(Map.Entry<Integer, RowValidateResult> entry : validateResultListMap.entrySet()){
			if(entry.getValue().isValidateSuccess()){
				i++;
			}
		}
		return i;
	}
	
	/**
	 * 忽略重复验证时是否验证成功。该值作为是否提交数据的唯一凭证
	 * @return boolean 是否验证成功
	 */
	public boolean isValidateSuccessWhenIgnoreRepeat(){
		for(Map.Entry<Integer, RowValidateResult> entry : validateResultListMap.entrySet()){
			if(!entry.getValue().isValidateSuccessWhenIgnoreRepeat()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取重复数据
	 * @return int 重复行数
	 */
	public int getRepeatRowCount(){
		int i = 0;
		for(Map.Entry<Integer, RowValidateResult> entry : validateResultListMap.entrySet()){
			if(entry.getValue().isRepeat()){
				i++;
			}
		}
		return i;
	}
	
	/**
	 * 验证失败数量
	 * @return int 验证失败数量
	 */
	public int getValidateFailedCount(){
		int i = 0;
		for(Map.Entry<Integer, RowValidateResult> entry : validateResultListMap.entrySet()){
			if(!entry.getValue().isValidateSuccess()){
				i++;
			}
		}
		return i;
	}
	
	
}
