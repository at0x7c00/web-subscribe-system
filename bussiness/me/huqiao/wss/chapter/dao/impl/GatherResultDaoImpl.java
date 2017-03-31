package me.huqiao.wss.chapter.dao.impl;
import java.util.ArrayList;
import java.util.List;

import me.huqiao.wss.chapter.dao.IGatherResultDao;
import me.huqiao.wss.chapter.entity.GatherResult;
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
 * 采集结果DAO实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class GatherResultDaoImpl extends BaseDaoImpl<GatherResult> implements IGatherResultDao {
    @Override
    public Long findListRowCount(GatherResult gatherResult) {
        Criteria criteria = getSession().createCriteria(GatherResult.class).setProjection(Projections.rowCount());
        queryCause(criteria,gatherResult);
        return (Long) criteria.uniqueResult();
    }
	@Override
	public Long findHistoryListRowCount(GatherResult gatherResult,Page pageInfo) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forRevisionsOfEntity(GatherResult.class, false, true);
		query.add(AuditEntity.property("manageKey").eq(gatherResult.getManageKey()));
		queryCause(query,pageInfo);
		query.addProjection(AuditEntity.property("manageKey").count());
		return (Long) query.getSingleResult();
	}
    @SuppressWarnings("unchecked")
    @Override
    public List<GatherResult> findListPage(GatherResult gatherResult, Page pageInfo){
    	Criteria criteria = getSession().createCriteria(GatherResult.class).setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage());
        queryCause(criteria,gatherResult);
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
	public List<HistoryRecord<GatherResult>> findHistoryListPage(GatherResult gatherResult, Page pageInfo) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forRevisionsOfEntity(GatherResult.class, false, true);
		query.setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage());
		query.add(AuditEntity.property("manageKey").eq(gatherResult.getManageKey()));
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
		List<HistoryRecord<GatherResult>> res = new ArrayList<HistoryRecord<GatherResult>>();
		for(Object obj : list){
			Object[] array = (Object[])obj;
			HistoryRecord<GatherResult> record = new HistoryRecord<GatherResult>();
			record.setRecord((GatherResult)array[0]);
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
      * @param gatherResult 查询对象
	  */
    public void queryCause(Criteria criteria,GatherResult gatherResult){
       if(gatherResult.getTitle()!=null
 && ! gatherResult.getTitle().trim().equals("")){
		criteria.add(Restrictions.like("title",gatherResult.getTitle(),MatchMode.ANYWHERE));
}
       if(gatherResult.getUrl()!=null
 && ! gatherResult.getUrl().trim().equals("")){
		criteria.add(Restrictions.like("url",gatherResult.getUrl(),MatchMode.ANYWHERE));
}
				if(gatherResult.getTask()!=null && gatherResult.getTask().getId() != null ){
					criteria.add(Restrictions.eq("task",gatherResult.getTask()));
				}
if(gatherResult.getCreateTimeStart()!=null){
criteria.add(Restrictions.ge("createTime",gatherResult.getCreateTimeStart()));
}
if(gatherResult.getCreateTimeEnd()!=null){
criteria.add(Restrictions.le("createTime",gatherResult.getCreateTimeEnd()));
}
       if(gatherResult.getStatus()!=null
){
		criteria.add(Restrictions.eq("status",gatherResult.getStatus()));
}
    }
	@Override
	public GatherResult findByVersion(Integer version) {
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		AuditQuery query = queryCreator2.forEntitiesAtRevision(GatherResult.class, version);
		query.add(AuditEntity.revisionNumber().eq(version));
		List list = query.getResultList();
		if(list!=null && list.size()>0){
			return (GatherResult)list.get(0);
		}
		return null;
	}
	@Override
	public List<GatherResult> findByKey(Page pageInfo,String queryKey) {
		Criteria criteria = getSession().createCriteria(GatherResult.class).setFirstResult(pageInfo.getStartIndex()).setMaxResults(pageInfo.getNumPerPage()).add(Restrictions.like("manageKey", queryKey,MatchMode.ANYWHERE));
		return criteria.list();
	}
	@Override
	public Long findRowCount(String queryKey) {
		Criteria criteria = getSession().createCriteria(GatherResult.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.like("manageKey", queryKey,MatchMode.ANYWHERE));
		return (Long) criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GatherResult> findById(Integer[] ids) {
		Criteria criteria = getSession().createCriteria(GatherResult.class)
		.add(Restrictions.in("id", ids));
		return criteria.list();
	}
}