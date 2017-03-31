package me.huqiao.wss.sys.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.sys.entity.enumtype.UserStatus;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户类
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
/**
 * @projectName catfish
 * @packageName me.huqiao.wss.sys.entity
 * @ClassName: User
 * @Description: 用户类
 * @author zhanghya
 * @date 2015-5-25 上午11:35:58
 */
public class User {

	/** 用户名 */
	@NotBlank(message = "用户名不能为空")
	private String username;
	/** 密码 */
	private String password;

	/** 明文密码，不持久化 */
	private String textpwd;
	/** 状态 */
	private UserStatus status;
	/** 用户角色 */
	private Set<Role> roles;
	/** 权限范围检索项 */
	private String deptRights;
	/** 用户部门号 */
	private Department dept;
	/** 邮箱 */
	private String email;
	/** 电话 */
	private String phone;

	/** 中文名称 */
	private String chineseName;

	/** 用于查询 */
	private String queryName;

	/** 查询关键字 */
	private String query;
	/** 角色ID 仅供查询项用,不产生数据字段 */
	private String roleId;
	/** 查询字段 */
	private String userNameQuery;

	private Date thisLoginTime;
	private Date lastLoginTime;

	@Id
	@GeneratedValue(generator = "userName_generator")
	@GenericGenerator(name = "userName_generator", strategy = "assigned")
	@Column(name = "username", length = 20, nullable = false)
	/**
	 * @return String  用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            要设置的 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return String 密码
	 */
	@Column(name = "password", length = 100, nullable = false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return UserStatus 状态
	 */
	@Column(name = "status", nullable = false, columnDefinition = "enum('Active','Approving','InActive')")
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            要设置的状态
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * 得到用户所有角色的字符串格式
	 * 
	 * @return String 所有角色字符串
	 */
	@Transient
	@JsonIgnore
	public String getRoleStr() {
		StringBuffer result = new StringBuffer();
		if (this.getRoles() != null) {
			for (Role role : getRoles()) {
				result.append(result.length() > 0 ? "、" + role.getName() : role.getName());
			}
		}
		return result.toString();
	}

	/**
	 * 得到用户角色集合
	 * 
	 * @return Set<Role> 角色集合
	 */
	@ManyToMany(targetEntity = me.huqiao.wss.sys.entity.Role.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "userName") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@OrderBy("id")
	@Lazy(value = false)
	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            要设置的 roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return String 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Department 得到部门
	 */
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept", nullable = true)
	@Fetch(FetchMode.SELECT)
	public Department getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            设置部门dept
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * @return String 电话
	 */
	@JsonIgnore
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            要设置的电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return String 查询的关键字
	 */
	@Transient
	@JsonIgnore
	public String getUserNameQuery() {
		return userNameQuery;
	}

	/**
	 * @return String 角色id
	 */
	@Transient
	@JsonIgnore
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            要设置的角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param userNameQuery
	 *            要设置的 userNameQuery
	 */
	public void setUserNameQuery(String userNameQuery) {
		this.userNameQuery = userNameQuery;
	}

	/**
	 * @return deptRights 部门权限
	 */
	@Transient
	@JsonIgnore
	public String getDeptRights() {
		return deptRights;
	}

	/**
	 * @param deptRights
	 *            要设置的 deptRights
	 */
	public void setDeptRights(String deptRights) {
		this.deptRights = deptRights;
	}

	/**
	 * @return String 密码
	 */
	@Transient
	@JsonIgnore
	public String getTextpwd() {
		return textpwd;
	}

	/**
	 * @param textpwd
	 *            要设置的密码
	 */
	public void setTextpwd(String textpwd) {
		this.textpwd = textpwd;
	}

	/**
	 * @return String 中文名
	 */
	@Column(name = "chinese_name", length = 255, nullable = false)
	public String getChineseName() {
		return chineseName;
	}

	@Transient
	public String getName() {
		return getChineseName();
	}

	/**
	 * @param chineseName
	 *            要设置的中文名
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	/**
	 * @return String 查询条件
	 */
	@Transient
	@JsonIgnore
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @param queryName
	 *            设置查询条件
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * @return int 获得哈希code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * @return boolean 比较对象是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/**
	 * @return length 获得内容的字数
	 */
	@Transient
	@JsonIgnore
	public Integer getChineseNameLength() {
		Integer length = 0;
		if (this.chineseName != null && !("").equals(this.chineseName)) {
			length = this.chineseName.length();
		}
		return length;
	}

	/**
	 * @return String 获取查询条件
	 */
	@Transient
	@JsonIgnore
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            要设置的 查询字符串
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return String 返回username
	 */
	@Transient
	public String getId() {
		return this.getUsername();
	}

	@Override
	public String toString() {
		return username;
	}

	public Date getThisLoginTime() {
		return thisLoginTime;
	}

	public void setThisLoginTime(Date thisLoginTime) {
		this.thisLoginTime = thisLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void recordLoginTime(Date date) {
		lastLoginTime = thisLoginTime;
		thisLoginTime = date;
	}
	
}
