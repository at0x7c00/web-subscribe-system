package me.huqiao.wss.sys.dao.impl;

import java.util.Collections;
import java.util.List;

import me.huqiao.wss.common.dao.impl.BaseDaoImpl;
import me.huqiao.wss.sys.dao.IFunctionPointDao;
import me.huqiao.wss.sys.entity.FunctionPoint;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 权限Dao实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class FunctionPointDaoImpl extends BaseDaoImpl<FunctionPoint> implements
		IFunctionPointDao {

	@Override
	public Long findRowCount(FunctionPoint functionPoint) {
		Criteria criteria = getSession().createCriteria(FunctionPoint.class)
				.setProjection(Projections.rowCount());
		addQueryCause(criteria, functionPoint);
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionPoint> findPage(FunctionPoint functionPoint, int first,
			int maxNum, String orderField, String orderDirection) {
		Criteria criteria = getSession().createCriteria(FunctionPoint.class)
				.setFirstResult(first).setMaxResults(maxNum);
		addQueryCause(criteria, functionPoint);
		if (orderField != null) {
			if (orderDirection == null || orderDirection.trim().equals("asc")) {
				criteria.addOrder(Order.asc(orderField));
			} else {
				criteria.addOrder(Order.desc(orderField));
			}
		} else {
			criteria.addOrder(Order.asc("id"));
		}
		return criteria.list();
	}

	private void addQueryCause(Criteria criteria, FunctionPoint functionPoint) {
		if (functionPoint.getName() != null
				&& !functionPoint.getName().trim().equals("")) {
			criteria.add(Restrictions.like("name", functionPoint.getName(),
					MatchMode.ANYWHERE));
		}
		if (functionPoint.getParent() != null
				&& functionPoint.getParent().getId() != null) {
			criteria.add(Restrictions.eq("parent.id", functionPoint.getParent()
					.getId()));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FunctionPoint> getTopLevelFunctionPoints() {
		return (List<FunctionPoint>)getSession().createCriteria(FunctionPoint.class).add(Restrictions.isNull("parent")).addOrder(Order.asc("orderNum")).list();
	}
	
	/** 
	 * 获取临近的兄弟
	 * @param p 权限对象
	 * @param type  0:表示比自己小的，1表示比自己大的
	 * @return FunctionPoint 权限对象，如果没找到返回null
	 */
	public FunctionPoint getNearByBrother(FunctionPoint p,int type){
		FunctionPoint parent=p.getParent();
		//找到第一个sortValue比自己小的兄弟
		List<FunctionPoint> brothers=null;
		if(type==0){
			if(parent==null){
				brothers=findByHQL("FROM Permission p WHERE p.parent is null AND p.sortValue<? ORDER BY p.sortValue DESC", new Object[]{p.getOrderNum()});
			}else{
				brothers=findByHQL("FROM Permission p WHERE p.parent=? AND p.sortValue<? ORDER BY p.sortValue DESC", new Object[]{parent,p.getOrderNum()});
			}
		}else if(type==1){
			if(parent==null){
				brothers=findByHQL("FROM Permission p WHERE p.parent is null AND p.sortValue>? ORDER BY p.sortValue", new Object[]{p.getOrderNum()});
			}else{
				brothers=findByHQL("FROM Permission p WHERE p.parent=? AND p.sortValue>? ORDER BY p.sortValue", new Object[]{parent,p.getOrderNum()});
			}
		}
		
		if(brothers!=null&&brothers.size()>0){
			return brothers.get(0);
		}
		return null;
	}

	@Override
	public Integer getMaxOrderNum(Integer parentId) {
		List<FunctionPoint> brothers=null;
		if(parentId==null || parentId==-1){
			brothers=findByHQL("FROM Permission p WHERE p.parent is NULL ORDER BY p.sortValue DESC",new Object[]{});
		}else{
			brothers=findByHQL("FROM Permission p WHERE p.parent.id=? ORDER BY p.sortValue DESC", new Object[]{parentId});
		}
		if(brothers!=null&&brothers.size()>0){
			return brothers.get(0).getOrderNum();
		}else{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionPoint> findByIds(Integer[] ids) {
		if(ids==null || ids.length==0){
			return Collections.EMPTY_LIST;
		}
		return getSession().createCriteria(FunctionPoint.class).add(Restrictions.in("id", ids)).list();
	}

	@Override
	public Integer getNextId() {
		return (Integer)findByHQLForUniqueResult("select max(id) from FunctionPoint", new Object[]{})+1;
	}

}
