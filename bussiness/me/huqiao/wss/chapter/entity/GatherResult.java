package me.huqiao.wss.chapter.entity;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.sys.entity.enumtype.YesNo;
import me.huqiao.wss.util.DateUtil;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 采集结果
 * @author NOVOTS
 * @version Version 1.0
 */
@Entity
@Table(name="wss_gather_result")
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})
public class GatherResult
{
/**唯一识别ID号 */
protected Integer id;
	/**@param id 要设置的唯一标示号*/
public void setId(Integer id){this.id=id;}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(columnDefinition="integer")
	/**@return Integer 唯一标示号*/
public Integer getId(){return this.id;}
/**标题*/
private String title;
/**URL*/
private String url;
/**所属任务*/
private Task task;
	/**所属任务模糊查询条件*/
private String taskQuery;
/**采集时间*/
private Date createTime;
	/**采集时间开始，用于查询*/
private Date createTimeStart;
	/**采集时间结束，用于查询*/
private Date createTimeEnd;
/**阅读状态*/
private UseStatus status;
private YesNo favourite;

private Set<Tag> tags;

	/**MD5管理ID*/
	protected String manageKey;
	/**@return String MD5管理ID */
	public String getManageKey() {
		return manageKey;
	}
	/**
	 * @param manageKey 要设置的MD5管理ID 
	 */
	public void setManageKey(String manageKey) {
		this.manageKey = manageKey;
	}
/**
 * @param title 要设置的标题
 */
public void setTitle(String title){
    this.title = title;
}
/**
 * @return String 标题 
 */
@Column(name="title",length=255,nullable=true)
public String getTitle(){
		return this.title;	
}
/**
 * @param url 要设置的URL
 */
public void setUrl(String url){
    this.url = url;
}

@Transient
public String getAccessUrl(){
	if(this.isRelativeUrl()){
		if(this.isRootRelativeUrl()){
			return  this.getTask().getRootUrl() +  this.getUrl();
		}else{
			return  this.getTask().getUrl() +  this.getUrl();
		}
	}else{
		return  this.getUrl();
	}
}

/**
 * @return String URL 
 */
@Column(name="url",length=255,nullable=true)
public String getUrl(){
		return this.url;	
}

@Transient
public boolean isRelativeUrl(){
	return !url.startsWith("http");
}

@Transient
public boolean isRootRelativeUrl(){
	return url.startsWith("/");
}

/**
 * @param task 要设置的所属任务
 */
public void setTask(Task task){
    this.task = task;
}
/**
 * @param taskQuery 要设置的所属任务模糊查询条件
 */
public void setTaskQuery(String taskQuery){
    this.taskQuery = taskQuery;
}
/**
 * @return Task 所属任务 
 */
@ManyToOne(targetEntity=me.huqiao.wss.chapter.entity.Task.class,fetch=FetchType.LAZY)
@JoinColumn(name="task_id",nullable=true)
@Fetch(FetchMode.SELECT)
@JsonIgnore
public Task getTask(){
		return this.task;	
}
/**
 * @return  String 所属任务模糊查询条件
 */
@Transient
public String getTaskQuery(){
    return this.taskQuery;
}
/**
 * @param createTime 要设置的采集时间
 */
public void setCreateTime(Date createTime){
    this.createTime = createTime;
}
/**
 * @return Date 采集时间 
 */
@Column(name="create_time",nullable=true)
public Date getCreateTime(){
		return this.createTime;	
}
/**
  * @param createTimeStart 要设置的采集时间开始日期
  */
public void setCreateTimeStart(Date createTimeStart){
    this.createTimeStart = createTimeStart;
}
/**
  * @return Date 采集时间开始日期
  */
@Transient
public Date getCreateTimeStart(){
    return this.createTimeStart;
}
/**
  * @param createTimeEnd 要设置的采集时间结束日期
  */
public void setCreateTimeEnd(Date createTimeEnd){
    this.createTimeEnd = createTimeEnd;
}
/**
  * @return Date 采集时间结束日期
  */
@Transient
public Date getCreateTimeEnd(){
    return this.createTimeEnd;
}
/**
 * @param status 要设置的阅读状态
 */
public void setStatus(UseStatus status){
    this.status = status;
}
/**
 * @return UseStatus 阅读状态 
 */
@Column(name="status",nullable=true,columnDefinition="enum('InUse','UnUse')")
@Enumerated(EnumType.STRING)
public UseStatus getStatus(){
		return this.status;	
}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		GatherResult other = null;
		try{
			other = (GatherResult) obj;
		}catch(Exception e){
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
		return "GatherResult [manageKey=" + manageKey + "]";
	}
	
	@Column(name="_favourite",nullable=true,columnDefinition="enum('Yes','No')")
	@Enumerated(EnumType.STRING)
	public YesNo getFavourite() {
		return favourite;
	}
	public void setFavourite(YesNo favourite) {
		this.favourite = favourite;
	}
	
	public Integer score;
	public String scoreAddIps;
	public String scoreDeleteIps;
	
	@Column(name="score")
	public Integer getScore() {
		if(score==null){
			score  = 0;
		}
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void addScore(String ip){
		if(this.score==null){
			this.score = 0;
		}
		this.score += 1;
		if(scoreAddIps==null){
			scoreAddIps = "";
		}
		if(scoreDeleteIps==null){
			scoreDeleteIps = "";
		}
		this.scoreDeleteIps = this.scoreDeleteIps.replace(ip, "");
		this.scoreAddIps+="," + ip;
	}
	
	public void deleteScore(String ip){
		if(this.score==null){
			this.score = 0;
		}
		this.score -= 1;
		if(scoreAddIps==null){
			scoreAddIps = "";
		}
		if(scoreDeleteIps==null){
			scoreDeleteIps = "";
		}
		this.scoreAddIps = this.scoreAddIps.replace(ip, "");
		this.scoreDeleteIps+="," + ip;
	}
	@Column(name = "score_add_ips",columnDefinition="text",nullable=true)
	public String getScoreAddIps() {
		return scoreAddIps;
	}
	public void setScoreAddIps(String scoreAddIps) {
		this.scoreAddIps = scoreAddIps;
	}
	@Column(name = "score_delete_ips",columnDefinition="text",nullable=true)
	public String getScoreDeleteIps() {
		return scoreDeleteIps;
	}
	public void setScoreDeleteIps(String scoreDeleteIps) {
		this.scoreDeleteIps = scoreDeleteIps;
	}
	
	@ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "lnk_gather_result_tag", joinColumns = { @JoinColumn(name = "gather_result_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@OrderBy("id")
	@Lazy(value = false)
	@JsonIgnore
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@Transient
	public String getCreateTimeStr(){
		return DateUtil.howLongBefore(getCreateTime());
	}
	
	
}