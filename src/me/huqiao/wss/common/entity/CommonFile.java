package me.huqiao.wss.common.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.util.StringUtil;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 文件
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_file")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CommonFile {
	/** 唯一识别ID号 */
	private Integer id;
	@NotBlank(message = "文件名称不能为空")
	@Length(min = 0, max = 200, message = "文件名称长度不能超过200")
	/**文件名称*/
	private String name;
	@Length(min = 0, max = 100, message = "存储长度不能超过100")
	/**存储*/
	private String storeName;
	@Length(min = 0, max = 20, message = "扩展名长度不能超过20")
	/**扩展名*/
	private String extensionName;
	/** 创建时间 */
	private Date createDate;
	/** 创建时间开始，用于查询 */
	private Date createDateStart;
	/** 创建时间结束，用于查询 */
	private Date createDateEnd;
	@NotNull(message = "所属文件夹不能为空")
	/**所属文件夹*/
	private CommonFolder folder;
	@Length(min = 0, max = 100, message = "管理长度不能超过100")
	/**管理*/
	private String manageKey;
	
	
	private UseStatus inuse;

	/**
	 * @param id
	 *            要设置的ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Integer 文件ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param name 要设置的文件名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String 文件的名称
	 */
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return this.name;
	}

	/**
	 * @param storeName 要设置的文件的存储名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return String 文件的存储名称
	 */
	@Column(name = "store_name", length = 100)
	public String getStoreName() {
		return this.storeName;
	}

	/**
	 * @param extensionName 要设置的文件的扩展名
	 *            
	 */
	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	}

	/**
	 * @return String 文件的扩展名
	 */
	@Column(name = "extension_name", length = 20)
	public String getExtensionName() {
		return this.extensionName;
	}

	/**
	 * @param createDate
	 *            要设置的开始时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Date 文件的开始时间
	 */
	@Column(name = "create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * @param folder 要设置文件的目录
	 */
	public void setFolder(CommonFolder folder) {
		this.folder = folder;
	}

	/**
	 * @return CommonFolder 文件的目录
	 */
	@ManyToOne(targetEntity = me.huqiao.wss.common.entity.CommonFolder.class)
	@JoinColumn(name = "folder")
	public CommonFolder getFolder() {
		return this.folder;
	}

	/**
	 * @param manageKey 要设置的文件的管理编码
	 */
	public void setManageKey(String manageKey) {
		this.manageKey = manageKey;
	}

	/**
	 * @return String 文件的管理编码
	 */
	@Column(name = "manage_key", length = 100)
	public String getManageKey() {
		return this.manageKey;
	}

	/**
	 * @return String 文件的完全路径
	 */
	@Transient
	public String getFullName() {
		String res = getFolder().getStorePath();
		if (!res.endsWith(File.separator)) {
			res += File.separator;
		}
		res += getManageKey();
		return res;
	}


	/**
	 * @return Date 文件的创建开始日期
	 */
	@Transient
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/**
	 * @param createDateStart 要设置文件的创建开始日期
	 *            
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/**
	 * @return Date 文件创建结束日期
	 */
	@Transient
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/**
	 * @param createDateEnd
	 *            文件创建结束日期
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	@Transient
	public boolean isVideo(){
		String fileName = getExtensionName();
		fileName = fileName.toLowerCase();
		return fileName.endsWith(".mp4")
				|| fileName.endsWith(".rmvb")
				|| fileName.endsWith(".ogg")
				|| fileName.endsWith(".webm")
				||fileName.endsWith(".wmv");
	}
	@Transient
	public boolean isPicture(){
		String fileName = getExtensionName();
		fileName = fileName.toLowerCase();
		return fileName.endsWith(".gif")
				|| fileName.endsWith(".png")
				|| fileName.endsWith(".bpm")
				|| fileName.endsWith(".jpg")
				||fileName.endsWith(".jpeg");
	}

	@Column(name = "status", nullable = true, columnDefinition = "enum('InUse','UnUse')")
	@Enumerated(EnumType.STRING)
	public UseStatus getInuse() {
		return inuse;
	}

	public void setInuse(UseStatus inuse) {
		this.inuse = inuse;
	}
	
	@Transient
	public String getFileNameOnly(){
		String res = getName();
		if(StringUtil.isEmpty(res) || res.indexOf(".")<0){
			return res;
		}else{
			return res.substring(0,res.lastIndexOf("."));
		}
	}
}

