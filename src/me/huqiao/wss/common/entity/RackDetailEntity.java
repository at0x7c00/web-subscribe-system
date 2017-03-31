/** 用一句话描述该文件做什么
 * @author  zhanghya 
 * @version V1.0  
 */
package me.huqiao.wss.common.entity;


/**这里用一句话描述这个类的作用
 * @author NOVOTS
 * @version Version 1.0
 */

public class RackDetailEntity {
	/** key值*/
	private Integer key;
	/** key对应的值 */
	private Integer value;
	/**
	 * 机柜占用百分比
	 */
	private String percent;
	/**机柜位置*/
	private String location;

	/**
	 * @return String key值
	 */
	public Integer getKey() {
		return key;
	}

	/**@return String 位置
	 */
	public String getLocation() {
		return location;
	}

	/** @param location 要设置的 位置
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param key  要设置的 key值
	 *           
	 */
	public void setKey(Integer key) {
		this.key = key;
	}

	/**
	 * @return Integer key对应的值
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value 要设置的 value
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**@return String  u位占用百分比
	 */
	public String getPercent() {
		return percent;
	}

	/** @param percent 要设置的 percent
	 */
	public void setPercent(String percent) {
		this.percent = percent;
	}


	
}
