package me.huqiao.wss.history.entity;

import org.hibernate.envers.RevisionType;

public class HistoryRecord<T> {

	protected T record;
	
	protected TestRevisionEntity revisionEntity;
	
	protected RevisionType type;

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	public TestRevisionEntity getRevisionEntity() {
		return revisionEntity;
	}

	public void setRevisionEntity(TestRevisionEntity revisionEntity) {
		this.revisionEntity = revisionEntity;
	}

	public RevisionType getType() {
		return type;
	}

	public void setType(RevisionType type) {
		this.type = type;
	}
}
