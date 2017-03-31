/**  
 * @Title DetailEntity.java
 * @Package me.huqiao.wss.common.entity
 * @Description TODO(用一句话描述该文件做什么)
 * @author  zhanghya 
 * @date  2015-5-28 下午3:14:09
 * @version V1.0  
 */
package me.huqiao.wss.common.entity;

/**
 * 统计中键值对
 * @author NOVOTS
 * @version Version 1.0
 */
public class DetailEntity {
	/** key值*/
	private String key;
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
	public String getKey() {
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
	public void setKey(String key) {
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
