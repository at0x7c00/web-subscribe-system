package me.huqiao.wss.chapter.entity;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.sys.entity.User;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文章
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "wss_chapter")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Chapter {
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

	/** 标题 */
	private String title;
	/** 分类 */
	private Category category;
	/** 分类模糊查询条件 */
	private String categoryQuery;
	/** 标签 */
	private Set<Tag> tags;
	/** 创建时间 */
	private Date createTime;
	/** 创建时间开始，用于查询 */
	private Date createTimeStart;
	/** 创建时间结束，用于查询 */
	private Date createTimeEnd;
	/** 修改时间 */
	private Date updateTime;
	/** 修改时间开始，用于查询 */
	private Date updateTimeStart;
	/** 修改时间结束，用于查询 */
	private Date updateTimeEnd;
	/** 创建人 */
	private User creator;
	/** 创建人模糊查询条件 */
	private String creatorQuery;
	/** 阅读次数 */
	private Integer readCount;
	/** 阅读次数开始，用于查询 */
	private Integer readCountStart;
	/** 阅读次数结束，用于查询 */
	private Integer readCountEnd;
	/** 排序号 */
	private Integer sortNum;
	/** 排序号开始，用于查询 */
	private Integer sortNumStart;
	/** 排序号结束，用于查询 */
	private Integer sortNumEnd;
	/** 封面图片 */
	private CommonFile cover;
	/** 封面图片模糊查询条件 */
	private String coverQuery;
	/** 内容 */
	private String content;
	/** 状态 */
	private UseStatus status;
	/** 来源 */
	private String origin;
	/** MD5管理ID */
	protected String manageKey;
	
	private String gatherResultKey;

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
	 * @param title
	 *            要设置的标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return String 标题
	 */
	@Column(name = "title", length = 255, nullable = true)
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param category
	 *            要设置的分类
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param categoryQuery
	 *            要设置的分类模糊查询条件
	 */
	public void setCategoryQuery(String categoryQuery) {
		this.categoryQuery = categoryQuery;
	}

	/**
	 * @return Category 分类
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.chapter.entity.Category.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	public Category getCategory() {
		return this.category;
	}

	/**
	 * @return String 分类模糊查询条件
	 */
	@Transient
	public String getCategoryQuery() {
		return this.categoryQuery;
	}

	/**
	 * @param tags
	 *            要设置的标签
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return Set<Tag> 标签
	 */
	@ManyToMany(targetEntity = me.huqiao.wss.chapter.entity.Tag.class)
	@JoinTable(name = "lnk_chapter_tag", joinColumns = { @JoinColumn(name = "chapter_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@JsonIgnore
	public Set<Tag> getTags() {
		return this.tags;
	}

	/**
	 * @param createTime
	 *            要设置的创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Date 创建时间
	 */
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTimeStart
	 *            要设置的创建时间开始日期
	 */
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	/**
	 * @return Date 创建时间开始日期
	 */
	@Transient
	public Date getCreateTimeStart() {
		return this.createTimeStart;
	}

	/**
	 * @param createTimeEnd
	 *            要设置的创建时间结束日期
	 */
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * @return Date 创建时间结束日期
	 */
	@Transient
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	/**
	 * @param updateTime
	 *            要设置的修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return Date 修改时间
	 */
	@Column(name = "update_time", nullable = true)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @param updateTimeStart
	 *            要设置的修改时间开始日期
	 */
	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	/**
	 * @return Date 修改时间开始日期
	 */
	@Transient
	public Date getUpdateTimeStart() {
		return this.updateTimeStart;
	}

	/**
	 * @param updateTimeEnd
	 *            要设置的修改时间结束日期
	 */
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	/**
	 * @return Date 修改时间结束日期
	 */
	@Transient
	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	/**
	 * @param creator
	 *            要设置的创建人
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @param creatorQuery
	 *            要设置的创建人模糊查询条件
	 */
	public void setCreatorQuery(String creatorQuery) {
		this.creatorQuery = creatorQuery;
	}

	/**
	 * @return User 创建人
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.sys.entity.User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	public User getCreator() {
		return this.creator;
	}

	/**
	 * @return String 创建人模糊查询条件
	 */
	@Transient
	public String getCreatorQuery() {
		return this.creatorQuery;
	}

	/**
	 * @param readCount
	 *            要设置的阅读次数
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	/**
	 * @return Integer 阅读次数
	 */
	@Column(name = "read_count", nullable = true)
	public Integer getReadCount() {
		return this.readCount;
	}

	/**
	 * @param readCountStart
	 *            要设置的阅读次数开始日期
	 */
	public void setReadCountStart(Integer readCountStart) {
		this.readCountStart = readCountStart;
	}

	/**
	 * @return Integer 阅读次数开始日期
	 */
	@Transient
	public Integer getReadCountStart() {
		return this.readCountStart;
	}

	/**
	 * @param readCountEnd
	 *            要设置的阅读次数结束日期
	 */
	public void setReadCountEnd(Integer readCountEnd) {
		this.readCountEnd = readCountEnd;
	}

	/**
	 * @return Integer 阅读次数结束日期
	 */
	@Transient
	public Integer getReadCountEnd() {
		return this.readCountEnd;
	}

	/**
	 * @param sortNum
	 *            要设置的排序号
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return Integer 排序号
	 */
	@Column(name = "sort_num", nullable = true)
	public Integer getSortNum() {
		return this.sortNum;
	}

	/**
	 * @param sortNumStart
	 *            要设置的排序号开始日期
	 */
	public void setSortNumStart(Integer sortNumStart) {
		this.sortNumStart = sortNumStart;
	}

	/**
	 * @return Integer 排序号开始日期
	 */
	@Transient
	public Integer getSortNumStart() {
		return this.sortNumStart;
	}

	/**
	 * @param sortNumEnd
	 *            要设置的排序号结束日期
	 */
	public void setSortNumEnd(Integer sortNumEnd) {
		this.sortNumEnd = sortNumEnd;
	}

	/**
	 * @return Integer 排序号结束日期
	 */
	@Transient
	public Integer getSortNumEnd() {
		return this.sortNumEnd;
	}

	/**
	 * @param cover
	 *            要设置的封面图片
	 */
	public void setCover(CommonFile cover) {
		this.cover = cover;
	}

	/**
	 * @param coverQuery
	 *            要设置的封面图片模糊查询条件
	 */
	public void setCoverQuery(String coverQuery) {
		this.coverQuery = coverQuery;
	}

	/**
	 * @return CommonFile 封面图片
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.common.entity.CommonFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cover_id", nullable = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	public CommonFile getCover() {
		return this.cover;
	}

	/**
	 * @return String 封面图片模糊查询条件
	 */
	@Transient
	public String getCoverQuery() {
		return this.coverQuery;
	}

	/**
	 * @param content
	 *            要设置的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return String 内容
	 */
	@Column(name = "content", length = 255, columnDefinition = "text", nullable = true)
	public String getContent() {
		return this.content;
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
	 * @param origin
	 *            要设置的来源
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return String 来源
	 */
	@Column(name = "_origin", length = 255, nullable = true)
	public String getOrigin() {
		return this.origin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Chapter other = null;
		try {
			other = (Chapter) obj;
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
		return "Chapter [manageKey=" + manageKey + "]";
	}
	
	@Column(name = "gather_result_key",nullable = true)
	public String getGatherResultKey() {
		return gatherResultKey;
	}

	public void setGatherResultKey(String gatherResultKey) {
		this.gatherResultKey = gatherResultKey;
	}
	
	
}