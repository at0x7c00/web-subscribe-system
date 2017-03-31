package me.huqiao.wss.sys.dao.impl;
import java.util.Collections;
import java.util.List;

import me.huqiao.wss.common.dao.impl.BaseDaoImpl;
import me.huqiao.wss.sys.dao.IRoleDao;
import me.huqiao.wss.sys.entity.Role;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 角色Dao 实现层
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

    @Override
    public Long findRowCount(Role role) {
        Criteria criteria = getSession().createCriteria(Role.class).setProjection(Projections.rowCount());
        addQueryCause(criteria,role);
        return (Long) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findPage(Role role, int first, int maxNum,String orderField,String orderDirection) {
        Criteria criteria = getSession().createCriteria(Role.class).setFirstResult(first).setMaxResults(maxNum);
        addQueryCause(criteria,role);
        if(orderField!=null){
        	if(orderDirection==null || orderDirection.trim().equals("asc")){
        		criteria.addOrder(Order.asc(orderField));
        	}else{
        		criteria.addOrder(Order.desc(orderField));
        	}
        }else{
        	criteria.addOrder(Order.asc("id"));
        }
        return criteria.list();
    }
    
    private void addQueryCause(Criteria criteria,Role role){
    	 if(role.getName()!=null && ! role.getName().trim().equals("")){criteria.add(Restrictions.like("name", role.getName(),MatchMode.ANYWHERE));}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByIds(Integer[] ids) {
		if(ids==null || ids.length==0){
			return Collections.EMPTY_LIST;
		}
		return getSession().createCriteria(Role.class).add(Restrictions.in("id", ids)).list();
	}

}
