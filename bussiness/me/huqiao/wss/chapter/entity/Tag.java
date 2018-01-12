package me.huqiao.wss.chapter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.common.entity.enumtype.UseStatus;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 标签
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "wss_tag")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Tag {
	/** 唯一识别ID号 */
	protected Integer id;

	/**
	 * @param id
	 *            要设置的唯一标示号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	/**@return Integer 唯一标示号*/
	public Integer getId() {
		return this.id;
	}

	/** 排序 */
	private Integer sortNum;
	/** 排序开始，用于查询 */
	private Integer sortNumStart;
	/** 排序结束，用于查询 */
	private Integer sortNumEnd;
	/** 状态 */
	private UseStatus status;
	/** 名称 */
	private String name;
	/** MD5管理ID */
	protected String manageKey;
	
	private String code;

	/** @return String MD5管理ID */
	public String getManageKey() {
		return manageKey;
	}

	/**
	 * @param manageKey
	 *            要设置的MD5管理ID
	 */
	public void setManageKey(String manageKey) {
		this.manageKey = manageKey;
	}

	/**
	 * @param sortNum
	 *            要设置的排序
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return Integer 排序
	 */
	@Column(name = "sort_num", nullable = true)
	public Integer getSortNum() {
		return this.sortNum;
	}

	/**
	 * @param sortNumStart
	 *            要设置的排序开始日期
	 */
	public void setSortNumStart(Integer sortNumStart) {
		this.sortNumStart = sortNumStart;
	}

	/**
	 * @return Integer 排序开始日期
	 */
	@Transient
	public Integer getSortNumStart() {
		return this.sortNumStart;
	}

	/**
	 * @param sortNumEnd
	 *            要设置的排序结束日期
	 */
	public void setSortNumEnd(Integer sortNumEnd) {
		this.sortNumEnd = sortNumEnd;
	}

	/**
	 * @return Integer 排序结束日期
	 */
	@Transient
	public Integer getSortNumEnd() {
		return this.sortNumEnd;
	}

	/**
	 * @param status
	 *            要设置的状态
	 */
	public void setStatus(UseStatus status) {
		this.status = status;
	}

	/**
	 * @return UseStatus 状态
	 */
	@Column(name = "status", nullable = true, columnDefinition = "enum('InUse','UnUse')")
	@Enumerated(EnumType.STRING)
	public UseStatus getStatus() {
		return this.status;
	}

	/**
	 * @param name
	 *            要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String 名称
	 */
	@Column(name = "name", length = 255, nullable = true)
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Tag other = null;
		try {
			other = (Tag) obj;
		} catch (Exception e) {
			return false;
		}
		if (manageKey == null) {
			if (other.getManageKey() != null)
				return false;
		} else if (!manageKey.equals(other.getManageKey()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((manageKey == null) ? 0 : manageKey.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Tag [manageKey=" + manageKey + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}