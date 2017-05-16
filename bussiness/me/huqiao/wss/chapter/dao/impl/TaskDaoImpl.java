package me.huqiao.wss.chapter.dao.impl;
import java.util.ArrayList;
import java.util.List;

import me.huqiao.wss.chapter.dao.ITaskDao;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.entity.enumtype.TaskStatus;
import me.huqiao.wss.common.dao.impl.BaseDaoImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.history.entity.TestRevisionEntity;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.AuditQueryCreator;
import org.springframework.stereotype.Repository;
/**
 * 采集任务DAO实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class TaskDaoImpl extends BaseDaoImpl<Task> implements ITaskDao {
    @Override
    public Long findListRowCount(Task task) {
        Criteria criteria = getSession().createCriteria(Task.class).setProjection(Projections.rowCount());
        queryCause(criteria,task);
        return (Long) criteria.uniqueResult();
    }
	@Override
	public Long findHistoryListRowCount(Task task,Page pageInfo) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forRevisionsOfEntity(Task.class, false, true);
		query.add(AuditEntity.property("manageKey").eq(task.getManageKey()));
		queryCause(query,pageInfo);
		query.addProjection(AuditEntity.property("manageKey").count());
		return (Long) query.getSingleResult();
	}
    @SuppressWarnings("unchecked")
    @Override
    public List<Task> findListPage(Task task, Page pageInfo){
    	Criteria criteria = getSession().createCriteria(Task.class).setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage());
        queryCause(criteria,task);
        if(pageInfo.getOrderField()!=null && !pageInfo.getOrderField().trim().equals("")){
         	if(pageInfo.getOrderDirection()==null || pageInfo.getOrderDirection().trim().equals("asc")){
         		criteria.addOrder(Order.asc(pageInfo.getOrderField()));
         	}else{
         		criteria.addOrder(Order.desc(pageInfo.getOrderField()));
         	}
         }else{
         	criteria.addOrder(Order.asc("id")); 
         }
        return criteria.list();
    }
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryRecord<Task>> findHistoryListPage(Task task, Page pageInfo) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forRevisionsOfEntity(Task.class, false, true);
		query.setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage());
		query.add(AuditEntity.property("manageKey").eq(task.getManageKey()));
		queryCause(query,pageInfo);
		if (pageInfo.getOrderField() != null && !pageInfo.getOrderField().trim().equals("")) {
			if (pageInfo.getOrderDirection() == null || pageInfo.getOrderDirection().trim().equals("asc")) {
				query.addOrder(AuditEntity.property(pageInfo.getOrderField()).asc());
			} else {
				query.addOrder(AuditEntity.property(pageInfo.getOrderField()).desc());
			}
		} else {
			query.addOrder(AuditEntity.property("id").desc());
		}
		List list = query.getResultList();
		List<HistoryRecord<Task>> res = new ArrayList<HistoryRecord<Task>>();
		for(Object obj : list){
			Object[] array = (Object[])obj;
			HistoryRecord<Task> record = new HistoryRecord<Task>();
			record.setRecord((Task)array[0]);
			record.setRevisionEntity((TestRevisionEntity)array[1]);
			record.setType((RevisionType)array[2]);
			res.add(record);
		}
		return res;
	}
	/**
	  * 添加历史记录查询条件
      * @param query 历史查询对象
      * @param pageInfo 历史记录分页查询对象
	  */
	public void queryCause(AuditQuery query,Page pageInfo) {
		if(pageInfo.getOperateDateStart()!=null){
			query.add(AuditEntity.revisionProperty("timestamp").ge(pageInfo.getOperateDateStart()));
		}
		if(pageInfo.getOperateDateEnd()!=null){
			query.add(AuditEntity.revisionProperty("timestamp").le(pageInfo.getOperateDateEnd()));
		}
		if(pageInfo.getOperator()!=null && !pageInfo.getOperator().trim().equals("")){
			query.add(AuditEntity.revisionProperty("username").like(pageInfo.getOperator(),MatchMode.ANYWHERE));
		}
		if(pageInfo.getOperateType()!=null && !pageInfo.getOperateType().trim().equals("")){
			query.add(AuditEntity.revisionType().eq(RevisionType.valueOf(pageInfo.getOperateType())));
		}
	}
	/**
	  * 根据查询对象往criteria对象增加查询条件
      * @param criteria Hibernate criteria对象
      * @param task 查询对象
	  */
    public void queryCause(Criteria criteria,Task task){
       if(task.getSelector()!=null
 && ! task.getSelector().trim().equals("")){
		criteria.add(Restrictions.like("selector",task.getSelector(),MatchMode.ANYWHERE));
}
       if(task.getCycle()!=null
){
		criteria.add(Restrictions.eq("cycle",task.getCycle()));
}
       if(task.getName()!=null
 && ! task.getName().trim().equals("")){
		criteria.add(Restrictions.like("name",task.getName(),MatchMode.ANYWHERE));
}
       if(task.getUrl()!=null
 && ! task.getUrl().trim().equals("")){
		criteria.add(Restrictions.like("url",task.getUrl(),MatchMode.ANYWHERE));
}
    }
	@Override
	public Task findByVersion(Integer version) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forEntitiesAtRevision(Task.class, version);
		query.add(AuditEntity.revisionNumber().eq(version));
		List list = query.getResultList();
		if(list!=null && list.size()>0){
			return (Task)list.get(0);
		}
		return null;
	}
	@Override
	public List<Task> findByKey(Page pageInfo,String queryKey) {
		Criteria criteria = getSession().createCriteria(Task.class).setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage()).add(Restrictions.like("name", queryKey,MatchMode.ANYWHERE));
		return criteria.list();
	}
	@Override
	public Long findRowCount(String queryKey) {
		Criteria criteria = getSession().createCriteria(Task.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.like("name", queryKey,MatchMode.ANYWHERE));
		return (Long) criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findById(Integer[] ids) {
		Criteria criteria = getSession().createCriteria(Task.class)
		.add(Restrictions.in("id", ids));
		return criteria.list();
	}
	@Override
	public List<Task> findAllActive() {
		Criteria criteria = getSession().createCriteria(Task.class).add(Restrictions.and(Restrictions.ne("status", TaskStatus.NotStart), Restrictions.ne("status", TaskStatus.Ended)));
		return criteria.list();
	}
	
}