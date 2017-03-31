package me.huqiao.wss.history.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity(name ="aud_audit_log")
@RevisionEntity(ExampleListener.class)
public class TestRevisionEntity{

	@Id
    @GeneratedValue
    @RevisionNumber
    private int id;
	
    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
