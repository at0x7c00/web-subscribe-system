package me.huqiao.wss.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.TestRevisionEntity;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.AuditQueryCreator;
import org.jfree.util.Log;
import org.springframework.stereotype.Repository;

/**
 * 数据访问操作基类实现类
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T>{

    /**定义Log日志 */
    private final Logger logger = Logger.getLogger(BaseDaoImpl.class);
    /**sessionFactory对象*/
    @Resource
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public T loadById(final Class<T> entityClass,final Serializable id) {
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("开始查找ID为" + id + "的实体：" + entityClass.getName());  
            } 
            if(id==null||"".equals(id))return null;
            return (T) getSession().load(entityClass, id);  
        } catch (RuntimeException e) {  
            logger.error("查找指定ID实体异常，ID：" + id, e);  
            throw e;  
        }  
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T findById(final Class<T> entityClass,final Serializable id) {
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("开始查找ID为" + id + "的实体：" + entityClass.getName());  
            }
            if(id==null||"".equals(id))return null;
            Session session = getSession();
            T res = (T) session.get(entityClass, id);
            if(res!=null){
            	session.refresh(res);
            }
            return res;  
        } catch (RuntimeException e) {  
            logger.error("查找指定ID实体异常，ID：" + id, e);  
            throw e;  
        }  
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(final Class<T> entityClass) {
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("开始查找所有实体：" + entityClass.getName());  
            }
            return getSession().createCriteria(entityClass).list();
        } catch (RuntimeException e) {  
            logger.error("查找指定实体集合异常，实体：" + entityClass.getName(), e);
            throw e;  
        }  
    }

    @Override
    public void save(T entity) {
        try {  
            if (logger.isDebugEnabled())   
                logger.debug("开始保存实体：" + entity.getClass().getName());  
            getSession().save(entity);
        } catch (RuntimeException e) {  
            logger.error("保存实体异常，实体名：" + entity.getClass().getName(), e);
            throw e;  
        } 
    }
    
    @Override
    public void update(T entity) {
        try {  
            if (logger.isDebugEnabled())   
                logger.debug("开始更新实体：" + entity.getClass().getName());  
            getSession().update(entity);
        } catch (RuntimeException e) {  
            logger.error("更新实体异常，实体名：" + entity.getClass().getName(), e);
            throw e;  
        } 
    }

    @Override
    public void delete(T entity) {
        try {  
            if (logger.isDebugEnabled())  
                logger.debug("开始删除实体：" + entity.getClass().getName());  
            getSession().delete(entity);
        } catch (RuntimeException e) {  
            logger.error("删除实体异常，实体名：" + entity.getClass().getName(), e);
            throw e;  
        } 
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByProperty(final Class<T> entityClass,final String propertyName,  
            final Object value) {  
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("开始查找指定属性：" + propertyName + "为" + value + "的实体"  
                        + entityClass.getName());  
            }
            String orderField = getOrderByField(entityClass);
            String queryStr = "from " + entityClass.getName()  
                    + " as model where model." + propertyName + "=?";  
            if(orderField!=null){
            	queryStr +=" order by model."+orderField +" asc";
            }
            Query query = getSession().createQuery(queryStr);
            query.setParameter(0, value);
            return query.list();  
        } catch (RuntimeException e) {  
            logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);  
            throw e;  
        }  
    }
    /**
     * 获取排序字段
     * @param clazz 实体类
     * @return String 字段名称
     */
    private String getOrderByField(Class clazz){
    	String className = clazz.getName();
    	if(className.equals("me.huqiao.wss.employee.entity.Employee")){
    		return "empNo";
    	}else if(className.equals("me.huqiao.wss.employee.entity.Department")){
    		return "deptNo";
    	}
    	try {
			Field filed = clazz.getDeclaredField("orderNum");
			return "orderNum";
		} catch (Exception e) {
			Log.info("class "+ clazz.getName()+" not has field named orderNum");
		}
		try {
			Field filed = clazz.getDeclaredField("id");
			return "id";
		} catch (Exception e) {
			Log.info("class "+ clazz.getName()+" not has field named id");
		}
    	return null;
    }
    
    @Override
    public Criteria createCriteria(final Class<T> entityClass,final Criterion...criterions){
        Criteria criteria = getSession().createCriteria(entityClass);
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        return criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findEntityByProperty(Class<T> entityClass, String propertyName, Object value) {
        Criteria criteria = getSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value));
        List<T> list = criteria.list();
        if(list.size()>0){
        	return (T) list.get(0);
        }else{
        	return null;
        }
    }
    
    /**
     * 得到当前Session
     * @return Session session对象
     */
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Long findCount(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByPage(Class<T> entityClass, int currentNum, int pageCount) {
        Criteria criteria = getSession().createCriteria(entityClass)
            .setFirstResult(currentNum).setMaxResults(pageCount);
        return criteria.list();
    }

    @Override
    public Long findRowCountByProperty(Class<T> entityClass, String propertyName,
            String propertyValue) {
        Criteria criteria = getSession().createCriteria(entityClass)
            .setProjection(Projections.rowCount())
            .add(Restrictions.eq(propertyName,propertyValue));
        return (Long) criteria.uniqueResult();
    }
    
	@SuppressWarnings("unchecked")
	public List<T> findByHQL(String hql, Object[] paramValues) {
		Query query = getSession().createQuery(hql);
		if (paramValues != null)
			for (int i = 0; i < paramValues.length; i++) {
				query.setParameter(i, paramValues[i]);
			}
		List<T> list = query.list();
		if(list==null){
			return Collections.EMPTY_LIST;
		}
		return list;
	}
	@Deprecated
	public List<T> findByHQL(String hql, Map<String,Object> paramValues) {
		Query query = getSession().createQuery(hql);
		if (paramValues != null)
			for (Map.Entry<String, Object> entry : paramValues.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		List<T> list = query.list();
		if(list==null){
			return Collections.EMPTY_LIST;
		}
		return list;
	}
	
	public List findByHQL2(String hql, Map<String,Object> paramValues) {
		Query query = getSession().createQuery(hql);
		if (paramValues != null)
			for (Map.Entry<String, Object> entry : paramValues.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		List<T> list = query.list();
		if(list==null){
			return Collections.EMPTY_LIST;
		}
		return list;
	}

	@Override
	public Object findByHQLForUniqueResult(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		if (params != null)
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		return query.uniqueResult();
	}
	public Object findByHQLForUniqueResult(String hql, Map<String,Object> params) {
		Query query = getSession().createQuery(hql);
		if (params != null)
			for (Map.Entry<String, Object> param:params.entrySet()) {
				query.setParameter(param.getKey(),param.getValue());
			}
		return query.uniqueResult();
	}


	@Override
	public List<T> findByProperties(Class<T> entityClass, String[] propertyNames, Object[] values, String orderBy,Integer limit) {
		Map<String,Object> params = new HashMap<String,Object>();
		try {  
            StringBuffer hql = new StringBuffer("from " + entityClass.getName()  + " as model where 1=1");
            if(propertyNames!=null){
	            for(int i = 0;i<propertyNames.length;i++){
	            	if(values[i]==null){
	            		hql.append(" and model." + propertyNames[i] + " is null");
	            	}else{
	            		hql.append(" and model." + propertyNames[i] + "=:"+propertyNames[i].replaceAll("\\.", ""));
	            		params.put(propertyNames[i].replaceAll("\\.", ""), values[i]);
	            	}
	            	
	            }
            }
            hql.append(" order by " + orderBy);
            Query query = getSession().createQuery(hql.toString());
            if(values!=null){
	            for(Map.Entry<String, Object> entry : params.entrySet()){
	            	//if(entry.getValue()==null) continue;
	            	query.setParameter(entry.getKey(), entry.getValue());
	            }
            }
            if(limit!=null){
            	query.setMaxResults(limit);
            }
            return query.list();  
        } catch (RuntimeException e) {  
        	e.printStackTrace();
        }
		return Collections.emptyList();
	}
	
	@Override
	public Object findByPreVersion(Class c,Serializable key, Integer version) {
		
		AuditReader reader = AuditReaderFactory.get(getSession());
		AuditQueryCreator queryCreator2 = reader.createQuery();
		
		AuditQuery query = queryCreator2.forRevisionsOfEntity(c, false, false);
		query.add(AuditEntity.property("manageKey").eq(key));
		query.addOrder(AuditEntity.revisionNumber().desc());
		
		List list = query.getResultList();
		
		for(Object obj : list){
			Object[] array = (Object[])obj;
			Object r = array[0];
			TestRevisionEntity tre = ((TestRevisionEntity)array[1]);
			if(tre.getId()<version){
				return r;
			}
		}
		return null;
	}
	
}
