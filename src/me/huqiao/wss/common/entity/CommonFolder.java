package me.huqiao.wss.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 文件夹
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_common_folder")
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})
public class CommonFolder {
	
	public final static Integer ID_ARTICLE = 3;
	/**唯一识别ID号*/
	private Integer id;
	@NotBlank(message = "文件夹名不能为空")
	@Length(min = 0, max = 100, message = "文件夹名长度不能超过100")
    /**文件夹名*/
	private String folderName;
	@NotBlank(message = "存储路径不能为空")
	@Length(min = 0, max = 100, message = "存储路径长度不能超过100")
	/**存储路径*/
	private String storePath;
    /**
     * @param id 要设置的唯一识别ID号
     */
	public void setId(Integer id) {
		this.id = id;
	}
    /**
     * @return Integer 文件夹的唯一识别ID号
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return this.id;
	}
   /**
    * @param folderName 要设置的文件夹名称
    */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
   /**
    * @return String 文件夹名称
    */
	@Column(name = "folder_name", length = 100, nullable = false)
	public String getFolderName() {
		return this.folderName;
	}
   /**
    * @param storePath 要设置文件夹存储路径
    */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
    /**
     * @return String 文件夹存储路径
     */
	@Column(name = "store_path", length = 100, nullable = false)
	public String getStorePath() {
		return this.storePath;
	}
	public   final static String  APP_FOLDER_PATH = "2";
	public  final static String  OS_FOLDER_PATH = "3";
	public  final static String  PATCH_FOLDER_PATH = "4";
	public  final static String  CONFIG_FOLDER_PATH = "5";
	public  final static String  SCRIPT_FOLDER_PATH = "6";
}
