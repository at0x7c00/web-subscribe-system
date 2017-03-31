package me.huqiao.wss.datamodel;

import java.util.List;

/**
 * 数据模型字段
 */
public class ModelProp {
	

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 是否可为空
	 */
	private boolean nullable;
	
	/**
	 * 类型描述
	 */
	private String typeDesc;
	
	/**
	 * 访问名称
	 */
	private String accessName;
	
	/**
	 * 所属类名称
	 */
	private String className;
	
	/**
	 * 字段类型名称
	 */
	private String propClassName;
	
	/**
	 * 是否为Set类型
	 */
	private boolean set;
	/**
	 * 是否非持久化
	 */
	private boolean transiented;
	
	/**
	 * 集合类型字段元素字段集合
	 */
	private List<ModelProp> children;
	
	private ModelPropType type;
	
	/**
	 * 一对多时对方关联字段
	 */
	private String mappedBy;
	
	public ModelProp(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getAccessName() {
		return accessName;
	}
	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPropClassName() {
		return propClassName;
	}
	public void setPropClassName(String propClassName) {
		this.propClassName = propClassName;
	}
	public boolean isSet() {
		return set;
	}
	public void setSet(boolean set) {
		this.set = set;
	}
	public boolean isTransiented() {
		return transiented;
	}
	public void setTransiented(boolean transiented) {
		this.transiented = transiented;
	}
	public List<ModelProp> getChildren() {
		return children;
	}
	public void setChildren(List<ModelProp> children) {
		this.children = children;
	}
	public ModelPropType getType() {
		return type;
	}
	public void setType(ModelPropType type) {
		this.type = type;
	}
	public String getMappedBy() {
		return mappedBy;
	}
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
}
