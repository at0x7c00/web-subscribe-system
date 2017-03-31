package me.huqiao.wss.common.entity;

import java.util.List;

/**
 * 统计返回结果
 * @author NOVOTS
 * @version Version 1.0
 */
public class StatisticRecordEntity {
	
	/**
	 * @Fields displayType : 统计的类型
	 */
	private String displayType;
	/**
	 * @Fields content : 其对应的数据
	 */
	private List<ContentEntity> content;
	/**
	 * @return displayType
	 */
	public String getDisplayType() {
		return displayType;
	}
	/**
	 * @param displayType 要设置的 displayType
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	/**
	 * @return content
	 */
	public List<ContentEntity> getContent() {
		return content;
	}
	/**
	 * @param content 要设置的 content
	 */
	public void setContent(List<ContentEntity> content) {
		this.content = content;
	}
	
	
}
