package me.huqiao.wss.sys.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.taglib.treeselect.ZTreeNode;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 部门
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_department")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Department implements ZTreeNode {
	/** 唯一识别ID号 */
	protected Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return this.id;
	}

	/** 名称 */
	private String name;
	/** 状态 */
	private UseStatus status;
	/** 上级 */
	private Department parent;
	/**
	 * 下级
	 */
	private Set<Department> children;
	/** 上级模糊查询条件 */
	private String parentQuery;
	/** 排序 */
	private Integer sort;
	/** 排序开始，用于查询 */
	private Integer sortStart;
	/** 排序结束，用于查询 */
	private Integer sortEnd;
	/** MD5管理ID */
	protected String manageKey;

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
	 * @param name
	 *            要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String 名称
	 */
	@Column(name = "name", length = 255, nullable = false)
	public String getName() {
		return this.name;
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
	@Column(name = "status", nullable = false, columnDefinition = "enum('InUse','UnUse')")
	@Enumerated(EnumType.STRING)
	public UseStatus getStatus() {
		return this.status;
	}

	/**
	 * @param parent
	 *            要设置的上级
	 */
	public void setParent(Department parent) {
		this.parent = parent;
	}

	/**
	 * @param parentQuery
	 *            要设置的上级模糊查询条件
	 */
	public void setParentQuery(String parentQuery) {
		this.parentQuery = parentQuery;
	}

	/**
	 * @return Department 上级
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.sys.entity.Department.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", nullable = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	public Department getParent() {
		return this.parent;
	}

	/**
	 * @return String 上级模糊查询条件
	 */
	@Transient
	public String getParentQuery() {
		return this.parentQuery;
	}

	/**
	 * @param sort
	 *            要设置的排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return Integer 排序
	 */
	@Column(name = "sort", nullable = true)
	public Integer getSort() {
		return this.sort;
	}

	/**
	 * @param sortStart
	 *            要设置的排序开始日期
	 */
	public void setSortStart(Integer sortStart) {
		this.sortStart = sortStart;
	}

	/**
	 * @return Integer 排序开始日期
	 */
	@Transient
	public Integer getSortStart() {
		return this.sortStart;
	}

	/**
	 * @param sortEnd
	 *            要设置的排序结束日期
	 */
	public void setSortEnd(Integer sortEnd) {
		this.sortEnd = sortEnd;
	}

	/**
	 * @return Integer 排序结束日期
	 */
	@Transient
	public Integer getSortEnd() {
		return this.sortEnd;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Department other = null;
		try {
			other = (Department) obj;
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
		result = prime * result + ((manageKey == null) ? 0 : manageKey.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Department [manageKey=" + manageKey + "]";
	}

	/** 版本号 */
	protected int version;

	/**
	 * @return int 版本号
	 */
	@Column(name = "_version", columnDefinition = "int(11) default 0")
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            要设置的版本号
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * 版本号自增1
	 */
	public void incrementVersion() {
		version++;
	}

	@Transient
	@Override
	@JsonIgnore
	public ZTreeNode getZTreeNodeParent() {
		return getParent();
	}

	@Transient
	@Override
	@JsonIgnore
	public String getZTreeNodeName() {
		return getName();
	}

	@Transient
	@Override
	@JsonIgnore
	public String getZTreeNodeId() {
		return getManageKey();
	}

	/**
     * @return Set<Category> 下级的部门
     */
	@OneToMany(targetEntity = Department.class, mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@OrderBy("sort")
	@Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
	@JsonIgnore
	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	
}