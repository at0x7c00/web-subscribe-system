
package me.huqiao.wss.common.entity;

import java.util.List;

/**
 * 统计中返回结果
 * @author NOVOTS
 * @version Version 1.0
 */
public class ContentEntity {
	/** 数据源类型 */
	private String sourceType;

	/** 数据源类型对应的数据*/
	private List<DetailEntity> detail;
	private List<RackDetailEntity> rackDetails;

	/**@return rackDetails
	 */
	public List<RackDetailEntity> getRackDetails() {
		return rackDetails;
	}

	/** @param rackDetails 要设置的 rackDetails
	 */
	public void setRackDetails(List<RackDetailEntity> rackDetails) {
		this.rackDetails = rackDetails;
	}

	/**
	 * @return String 数据源类型
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType 要设置的数据源类型
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return List<DetailEntity>  数据源类型对应的数据
	 */
	public List<DetailEntity> getDetail() {
		return detail;
	}

	/**
	 * @param detail 要设置的数据源类型对应的数据
	 */
	public void setDetail(List<DetailEntity> detail) {
		this.detail = detail;
	}

}
