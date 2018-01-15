package me.huqiao.wss.chapter.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.chapter.entity.enumtype.TaskStatus;
import me.huqiao.wss.sys.entity.enumtype.YesNo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 采集任务
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "wss_task")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Task {
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

	/** 选择器 */
	private String selector;
	/** 采集频率(分钟) */
	private Double cycle;
	/** 采集频率(分钟)开始，用于查询 */
	private Double cycleStart;
	/** 采集频率(分钟)结束，用于查询 */
	private Double cycleEnd;
	/** 名称 */
	private String name;
	/** 网址 */
	private String url;
	/** MD5管理ID */
	protected String manageKey;
	
	private String log;
	private String contentSelector;
	
	/** 状态 */
	private TaskStatus status;
	
	private YesNo markAsFav;
	
	
	private Set<Tag> tags;
	
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
	 * @param selector
	 *            要设置的选择器
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}

	/**
	 * @return String 选择器
	 */
	@Column(name = "_selector", length = 255, nullable = true)
	public String getSelector() {
		return this.selector;
	}

	/**
	 * @param cycle
	 *            要设置的采集频率(分钟)
	 */
	public void setCycle(Double cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return Double 采集频率(分钟)
	 */
	@Column(name = "_cycle", nullable = true)
	public Double getCycle() {
		return this.cycle;
	}

	/**
	 * @param cycleStart
	 *            要设置的采集频率(分钟)开始日期
	 */
	public void setCycleStart(Double cycleStart) {
		this.cycleStart = cycleStart;
	}

	/**
	 * @return Double 采集频率(分钟)开始日期
	 */
	@Transient
	public Double getCycleStart() {
		return this.cycleStart;
	}

	/**
	 * @param cycleEnd
	 *            要设置的采集频率(分钟)结束日期
	 */
	public void setCycleEnd(Double cycleEnd) {
		this.cycleEnd = cycleEnd;
	}

	/**
	 * @return Double 采集频率(分钟)结束日期
	 */
	@Transient
	public Double getCycleEnd() {
		return this.cycleEnd;
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

	/**
	 * @param url
	 *            要设置的网址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Transient
	public String getRootUrl(){
		String res = getUrl();
		String p = res.substring(0,res.indexOf("://"));
		res =  res.substring(res.indexOf("://") + 3);
		if(res.contains("/")){
			res = res.substring(0,res.indexOf("/"));
		}
		return p + "://"  + res;
	}

	/**
	 * @return String 网址
	 */
	@Column(name = "url", length = 255, nullable = true)
	public String getUrl() {
		return this.url;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Task other = null;
		try {
			other = (Task) obj;
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
		return "Task [manageKey=" + manageKey + "]";
	}

	@Column(name = "execute_result",columnDefinition = "longtext")
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Column(name = "status", nullable = true, columnDefinition = "enum('NotStart','Scheduled','Executing','Ended')")
	@Enumerated(EnumType.STRING)
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public void addLog(String log) {
		String logs = getLog();
		if(logs!=null && logs.length()>1024){
			this.setLog("...\r\n"+logs.substring(logs.length() - 1024,logs.length())+ "\r\n");
		}
		this.setLog((this.getLog()==null ?"" : this.getLog()) + "\r\n" + log);
	}

	@Column(name = "mark_as_fav", nullable = true, columnDefinition = "enum('Yes','No')")
	@Enumerated(EnumType.STRING)
	public YesNo getMarkAsFav() {
		return markAsFav;
	}

	public void setMarkAsFav(YesNo markAsFav) {
		this.markAsFav = markAsFav;
	}

	@ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.MERGE },fetch = FetchType.EAGER)
	@JoinTable(name = "lnk_task_tag", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@OrderBy("id")
	@Lazy(value = false)
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Column(name = "content_selector",nullable = true)
	public String getContentSelector() {
		return contentSelector;
	}

	public void setContentSelector(String contentSelector) {
		this.contentSelector = contentSelector;
	}
	
	
	
}