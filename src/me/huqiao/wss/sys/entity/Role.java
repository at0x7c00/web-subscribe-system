package me.huqiao.wss.sys.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import me.huqiao.wss.sys.entity.enumtype.RoleType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 角色
 * 
 * @author NOVOTS
 * @version Version 1.0
 **/
@Entity
@Table(name = "sys_role")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Role {

	/** id : 编号 */
	private Integer id;
	@NotBlank(message = "名称不能为空")
	@Length(min = 0, max = 100, message = "名称长度不能超过100")
	/**name：名称*/
	private String name;// 名称
	@Length(min = 0, max = 255, message = "描述长度不能超过255")
	/**description:描述 */
	private String description;// 描述
	/** functionPoints:权限 */
	private Set<FunctionPoint> functionPoints;// 权限

	private String manageKey;
	private RoleType type;

	/**
	 * @param id
	 *            要设置的id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param name
	 *            要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String 返回的名称
	 */
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return this.name;
	}

	/**
	 * @param description
	 *            要设置的描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return String 返回的描述
	 */
	@Column(name = "description", length = 255)
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param functionPoints
	 *            要设置的权限
	 */
	public void setFunctionPoints(Set<FunctionPoint> functionPoints) {
		this.functionPoints = functionPoints;
	}

	/**
	 * @return String 返回的权限集合
	 */
	@ManyToMany(targetEntity = FunctionPoint.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "sys_role_function_point", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "functionpoint_id") })
	public Set<FunctionPoint> getFunctionPoints() {
		return this.functionPoints;
	}

	/**
	 * @return int 返回hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(name = "_key")
	public String getManageKey() {
		return manageKey;
	}

	public void setManageKey(String manageKey) {
		this.manageKey = manageKey;
	}

	@Column(name = "_type", nullable = false, columnDefinition = "enum('Normal','Advance')")
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}
}
