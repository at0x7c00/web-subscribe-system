package me.huqiao.wss.expandimp;

/**
 * 数据验证方式<br>EXCEL_REPEAT("Excel重复验证"),PROP_VALIDATE("字段值合法性验证"),DB_REPEAT("数据库重复验证"),DB_INSERT("数据库操作"),OHTER("其他情况");
 * @author huqiao
 * @version 1.0
 */
public enum ValidateType {
	/**
	 * Excel重复验证
	 */
	EXCEL_REPEAT("Excel重复验证"),
	/**
	 * 字段值合法性验证
	 */
	PROP_VALIDATE("字段值合法性验证"),
	/**
	 * 数据库重复验证
	 */
	DB_REPEAT("数据库重复验证"),
	/**
	 * 数据库插入是否成功
	 */
	DB_INSERT("数据库操作"),
	/**
	 * 其他未知情况
	 */
	OHTER("其他情况");
	private String description;

	private  ValidateType(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
}
