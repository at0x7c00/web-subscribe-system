package me.huqiao.wss.util.web;

/**
 * 导入结果
 * @author NOVOTS
 * @version Version 1.0
 */
public class ImportResult {
    /**导入总数*/
	private int total = 0;
	/**成功数量*/
	private int success = 0;
	/**提示信息 */
	private String message;
	/**
	 * @return int 导入总数
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total 要设置的导入总数
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return  int 成功数量
	 */
	public int getSuccess() {
		return success;
	}
	/**
	 * @param success 要设置成功数量
	 */
	public void setSuccess(int success) {
		this.success = success;
	}
	/**
	 * @return String 操作信息
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message 要设置操作信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
