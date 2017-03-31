package me.huqiao.wss.sys.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;

import me.huqiao.wss.sys.entity.comparator.FunctionPointComparator;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 权限
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_function_point")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class FunctionPoint {
	/** 唯一标志ID */
	private Integer id;
	@Length(min = 0, max = 255, message = "图标长度不能超过255")
	/**图标*/
	private String icon;
	@NotNull(message = "页面可见不能为空")
	/**页面可见*/
	private Integer isDisplay;
	@NotNull(message = "排序号不能为空")
	/**排序号*/
	private Integer orderNum;
	/** 上级 */
	private FunctionPoint parent;
	@NotBlank(message = "名称不能为空")
	@Length(min = 0, max = 255, message = "名称长度不能超过255")
	/**名称*/
	private String name;
	@Length(min = 0, max = 255, message = "功能路径长度不能超过255")
	/**功能路径*/
	private String url;
	/** 下级权限 */
	private Set<FunctionPoint> children;

	/**
	 * @param id
	 *            要设置唯一标志
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Integer 唯一标志
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param icon
	 *            要设置的图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return String 图标
	 */
	@Column(name = "icon", length = 255)
	public String getIcon() {
		return this.icon;
	}

	/**
	 * @param isDisplay
	 *            要设置是否页面可见
	 */
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	/**
	 * @return Integer 是否页面可见
	 */
	@Column(name = "is_display", nullable = false)
	public Integer getIsDisplay() {
		return this.isDisplay;
	}

	/**
	 * @param orderNum
	 *            要设置的排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return Integer 排序
	 */
	@Column(name = "order_num", nullable = false)
	public Integer getOrderNum() {
		return this.orderNum;
	}
   /**
    * @param parent 要设置上级权限
    */
	public void setParent(FunctionPoint parent) {
		this.parent = parent;
	}
	/**
	 * @return FunctionPoint 上级权限
	 */
	@ManyToOne(targetEntity = FunctionPoint.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	@Fetch(FetchMode.JOIN)
	public FunctionPoint getParent() {
		return this.parent;
	}
	/**
	 * @param name 要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    /**
     * @return String 要设置的名称
     */
	@Column(name = "name", length = 255, nullable = false)
	public String getName() {
		return this.name;
	}
    /**
     * @param url 要设置的功能路径
     */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return String 功能路径
	 */
	@Column(name = "url", length = 255)
	public String getUrl() {
		return this.url;
	}
   /**
    * @return String rel
    */
	@Transient
	public String getRelName() {
		if (url.contains("?")) {
			return "&rel=";
		}
		return "?rel=";
	}
    /**
     * @return Set<FunctionPoint> 下级的权限集合
     */
	@OneToMany(targetEntity = FunctionPoint.class, mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@OrderBy("orderNum")
	@Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
	public Set<FunctionPoint> getChildren() {
		return children;
	}
    /**
     * @param children 要设置的下级权限
     */
	public void setChildren(Set<FunctionPoint> children) {
		this.children = children;
	}
    /**
     * 是否相等
     */
	public boolean equals(Object obj) {
		return (obj instanceof FunctionPoint) && this.getId() != null && obj != null && this.getId().intValue() == (((FunctionPoint) obj).getId());
	}
   /**
    * 复制权限
    * @return 复制的权限
    */
	public FunctionPoint copy() {
		FunctionPoint copyOfSelf = new FunctionPoint();
		copyOfSelf.setChildren(new TreeSet<FunctionPoint>(new FunctionPointComparator()));
		copyOfSelf.setIcon(this.getIcon());
		copyOfSelf.setId(this.getId());
		copyOfSelf.setIsDisplay(this.getIsDisplay());
		copyOfSelf.setName(this.getName());
		copyOfSelf.setOrderNum(this.getOrderNum());
		copyOfSelf.setUrl(this.getUrl());
		copyOfSelf.setParent(this.getParent());
		return copyOfSelf;
	}
   /**
    * @return 国际化名称
    */
	@Transient
	public String getI18nCode() {
		if (getUrl() == null) {
			throw new RuntimeException("no url found for function point");
		}
		if (!getUrl().contains("/")) {
			throw new RuntimeException("wrong format url of function point");
		}
		String[] url = getUrl().split("/");
		String result = "funcs.";
		if (url[0].trim().equals("")) {
			throw new RuntimeException("wrong format url of function point");
		} else if (url[1].endsWith(".do") || url[1].contains(".do?")) {
			result += upcaseFirst(url[0]) + "." + removeExtension(url[1]);
		} else {
			result += upcaseFirst(url[1]) + ".list";
		}
		// System.out.println(result);
		return result;
	}
    /**
     * 字符串首字母大写
     * @param s 操作对象
     * @return String 大写后的字符串
     */
	private String upcaseFirst(String s) {
		if (s.length() == 1) {
			return s.toUpperCase();
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
    /**
     * 删除扩展
     * @param s 要操作对象
     * @return String 删除扩展之后的字符串
     */
	private String removeExtension(String s) {
		return s.substring(0, s.indexOf("."));
	}
    /**
     * 获取下级权限的字符串
     * @return String 下级权限字符串格式
     */
	@Transient
	public String getChildrenUrls() {
		StringBuffer res = new StringBuffer();
		for (FunctionPoint c : getChildren()) {
			res.append(c.getUrl()).append(";").append(c.getChildrenUrls()).append(";");
		}
		return res.toString();
	}
     /**
      * 获得角色的权限的json格式字符串
      * @param role 角色对象
      * @return String  角色的权限的json格式字符串
      */
	@Transient
	public String getZTreeJson(Role role) {
		StringBuffer res = new StringBuffer();
		FunctionPoint parent = getParent();
		res.append("{ id:").append(this.getId()).append(", pId:").append(parent == null ? "0" : parent.getId()).append(", name:'").append(getName()).append("', open:true,checked:")
				.append(role.getFunctionPoints().contains(this)).append("},");
		for (FunctionPoint child : getChildren()) {
			res.append(child.getZTreeJson(role));
		}
		return res.toString();
	}
}
