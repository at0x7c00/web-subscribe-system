package me.huqiao.wss.sys.dao.impl;

import java.util.List;

import me.huqiao.wss.common.dao.impl.BaseDaoImpl;
import me.huqiao.wss.sys.dao.IUserDao;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.sys.entity.enumtype.UserStatus;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * userDao 的实现类
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public Long findRowCount(User user) {
		Criteria criteria = getSession().createCriteria(User.class)
				.setProjection(Projections.rowCount());
		addQueryCause(criteria, user);
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findPage(User user, int first, int maxNum,
			String orderField, String orderDirection) {
		Criteria criteria = getSession().createCriteria(User.class)
				.setFirstResult(first).setMaxResults(maxNum);
		addQueryCause(criteria, user);
		if (orderField != null) {
			if (orderDirection == null || orderDirection.trim().equals("asc")) {
				criteria.addOrder(Order.asc(orderField));
			} else {
				criteria.addOrder(Order.desc(orderField));
			}
		} else {
			criteria.addOrder(Order.asc("username"));
		}
		return criteria.list();
	}
   /**
    * 根据查询对象往criteria对象增加查询条件
    * @param criteria Hibernate criteria对象
    * @param user 查询对象
    */
	private void addQueryCause(Criteria criteria, User user) {
		if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
			criteria.add(Restrictions.or(Restrictions.like("username", user.getUsername(),
					MatchMode.ANYWHERE),Restrictions.like("chineseName", user.getUsername(),MatchMode.ANYWHERE)));
		}
		if (user.getUserNameQuery() != null && !user.getUserNameQuery().trim().equals("")) {
			criteria.add(Restrictions.like("chineseName", user.getQueryName(),MatchMode.ANYWHERE));
		}
		if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
			criteria.add(Restrictions.like("password", user.getPassword(),
					MatchMode.ANYWHERE));
		}
		if (user.getStatus() != null) {
			criteria.add(Restrictions.eq("status", user.getStatus()));
		}
		if (user.getRoleId()!= null && !user.getRoleId().trim().equals("")) {
			criteria.createAlias("roles", "role").add(Restrictions.eq("role.id", new Integer(user.getRoleId())));
		}
		if (!"".equals(user.getDeptRights())&&user.getDeptRights()!= null) {
			criteria.createAlias("departments", "dept").add(Restrictions.like("dept.name", user.getDeptRights(),MatchMode.ANYWHERE));
		}
		
		if (user.getQueryName() != null&&!"".equals(user.getQueryName())) {
			Criteria companyCriteria = criteria.createCriteria("company");
			companyCriteria.add(Restrictions.like("chineseName", user.getQueryName(),MatchMode.ANYWHERE));
		}
		
	}

	@Override
	public List<User> findAllActive(User user) {
		Criteria criteria = getSession().createCriteria(User.class);
		if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
			criteria.add(Restrictions.like("username", user.getUsername(),
					MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("status", UserStatus.Active));
		List<User> list = criteria.list();
		for (User u : list) {
			Hibernate.initialize(u);
		}
		return list;
	}

	@Override
	public List<User> findByQueryKey(String queryKey) {
		Criteria criteria = getSession().createCriteria(User.class).add((Restrictions.or(Restrictions.like("chineseName", queryKey,MatchMode.ANYWHERE),
				Restrictions.like("email", queryKey,MatchMode.ANYWHERE))));
		return criteria.list();
	}

	@Override
	public Long findRowCount(String queryKey) {
		Criteria criteria = getSession().createCriteria(User.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.or(Restrictions.like("chineseName", queryKey,MatchMode.ANYWHERE),
						Restrictions.like("email", queryKey,MatchMode.ANYWHERE)));
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findListByUserName(String[] userNames) {
		Criteria criteria = getSession().createCriteria(User.class)
				.add(Restrictions.in("username", userNames));
		return criteria.list();
	}


}
