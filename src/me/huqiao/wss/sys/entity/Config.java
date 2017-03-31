package me.huqiao.wss.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import me.huqiao.wss.sys.entity.enumtype.YesNo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/**
 * 配置
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name = "sys_config")
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})
public class Config {
	/**唯一标识ID*/
	private Integer id;
	/** 名称*/
	private String name;
	/** 数字 */
	private Integer intValue;
	/** 单浮点 */
	private Float floatValue;
	/** 双浮点 */
	private Double doubleValue;
	/** 日期型（'YYYY-DD-MM'）*/
	private Date dateValue;
	/** 日期型（'YYYY-DD-MM HH-MM-SS'）*/
	private Date datetimeValue;
	/** 描述1*/
	private String descripttion1;
	/** 描述2 */
	private String descripttion2;
	
	/** 是否 */
	private YesNo yesNo;
	/**
	 * @return Integer 获取唯一标识ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "integer")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id 要设置配置ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return String 配置名称
	 */
	@Column(name="name",columnDefinition="text")
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Integer 数字
	 */
	@Column(name="int_value")
	public Integer getIntValue() {
		return intValue;
	}
	/**
	 * @param intValue 要设置的数字
	 */
	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}
	/** 
	 * @return Float 浮点
	 */
	@Column(name="float_value")
	public Float getFloatValue() {
		return floatValue;
	}
	/**
	 * @param floatValue 要设置的浮点
	 */
	public void setFloatValue(Float floatValue) {
		this.floatValue = floatValue;
	}
	/**
	 * @return Double 双浮点
	 */
	@Column(name="double_value")
	public Double getDoubleValue() {
		return doubleValue;
	}
	/**
	 * @param doubleValue 要设置的双浮点
	 */
	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}
	/**
	 * @return Date 日期值
	 */
	@Column(name="date_value",columnDefinition="date")
	public Date getDateValue() {
		return dateValue;
	}
	/**
	 * @param dateValue 要设置日期值
	 */
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	/**
	 * @return Date 时间值
	 */
	@Column(name="datetime_value")
	public Date getDatetimeValue() {
		return datetimeValue;
	}
	/**
	 * @param datetimeValue  要设置的时间值
	 */
	public void setDatetimeValue(Date datetimeValue) {
		this.datetimeValue = datetimeValue;
	}
	/**
	 * @return String 描述1
	 */
	@Column(name="descripttion1",columnDefinition="text")
	public String getDescripttion1() {
		return descripttion1;
	}
	/**
	 * @param descripttion1 要设置的描述1
	 */
	public void setDescripttion1(String descripttion1) {
		this.descripttion1 = descripttion1;
	}
	/**
	 * @return String 描述2
	 */
	@Column(name="descripttion2",columnDefinition="text")
	public String getDescripttion2() {
		return descripttion2;
	}
	/**
	 * @param descripttion2 要设置的描述2
	 */
	public void setDescripttion2(String descripttion2) {
		this.descripttion2 = descripttion2;
	}
   /**
    * @return YesNo 是否
    */
	@Column(name = "yes_no", nullable = true, columnDefinition = "enum('Yes','No')")
	@Enumerated(EnumType.STRING)
	public YesNo getYesNo() {
		return yesNo;
	}
	/**
	 * @param yesNo 要设置的是否
	 */
	public void setYesNo(YesNo yesNo) {
		this.yesNo = yesNo;
	}
	
	/** 邮件主机*/
	public static int SYS_CONFIG_MAIL_SEND_HOST_NAME=1;
	/** 访问用户名称*/
	public static int SYS_CONFIG_MAIL_SEND_HOST_ACCESS_NAME=2;
	/**访问密码*/
	public static int SYS_CONFIG_MAIL_SEND_HOST_ACCESS_PASSWORD=3;
	/**访问是否验证*/
	public static int SYS_CONFIG_MAIL_SEND_HOST_ACCESS_SMTP_AUTH=4;
	/** 默认发送人*/
	public static int SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER=5;
	
	/** 安装os接口地址*/
	public static int DEPLOY_CONFIG_OS_INSTALL_URL=6;
	/** 部署app、版本更新、安装补丁接口地址*/
	public static int DEPLOY_CONFIG_APP_INSTALL_URL=7;
	/** 配置变更接口地址*/
	public static int DEPLOY_CONFIG_CHANGE_CONFIG_URL=8;
	/** 模板解析接口地址*/
	public static int DEPLOY_CONFIG_TEMPLATE_ANALYSIS_URL=9;
	/** 文件扫描接口地址*/
	public static int DEPLOY_CONFIG_FILE_ANALYSIS_URL=10;
	/** 远程文件根目录*/
	public static int DEPLOY_CONFIG_REMOTE_FILE_ROOT_DIR=11;
	
	/** 远程文件同步接口*/
	public static int DEPLOY_CONFIG_REMOTE_FILE_SYNC_URL=12;
	/** 新增本地文件通知接口*/
	public static int DEPLOY_CONFIG_REMOTE_NEW_LOCAL_FILE_NOTICE=13;
	public static int DEPLOY_CONFIG_SYS_BASE_PATH=14;
	
	
	/** 监控数据查询接口地址*/
	public static int MONITOR_DATA_QUERY_URL=15;
	/** 告警数据查询接口地址*/
	public static int ALARM_DATA_QUERY_URL=16;
	/** 告警数据确认接口地址*/
	public static int ALARM_DATA_COMFIRM_URL=17;
	/** 告警数据隔离接口地址*/
	public static int ALARM_DATA_ISOLATED_URL=18;
	/** 直真接口地址*/
	public static int COCOA_URL=19;
	/** 直真接口账号*/
	public static int COCOA_USERNAME=20;
	/**直真接口密码*/
	public static int COCOA_PASSWORD=21;
	
	/**
	 * 监控系统接口调用账号
	 */
	public static int ALARM_DATA_QUERY_ACCOUNT=22;
	/**
	 * 监控系统接口调用密码
	 */
	public static int ALARM_DATA_QUERY_PWD=23;
}
