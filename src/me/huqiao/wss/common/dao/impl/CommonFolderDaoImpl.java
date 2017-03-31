package me.huqiao.wss.common.dao.impl;
import java.util.List;

import me.huqiao.wss.common.dao.ICommonFolderDao;
import me.huqiao.wss.common.entity.CommonFolder;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

/**
 * 文件夹Dao实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Repository
public class CommonFolderDaoImpl extends BaseDaoImpl<CommonFolder> implements ICommonFolderDao {

    @Override
    public Long findRowCount(CommonFolder commonFolder) {
        Criteria criteria = getSession().createCriteria(CommonFolder.class).setProjection(Projections.rowCount());
        addQueryCause(criteria,commonFolder);
        return (Long) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CommonFolder> findPage(CommonFolder commonFolder, int first, int maxNum,String orderField,String orderDirection) {
        Criteria criteria = getSession().createCriteria(CommonFolder.class).setFirstResult(first).setMaxResults(maxNum);
        addQueryCause(criteria,commonFolder);
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
    /**
     * 根据查询对象往criteria对象增加查询条件
     * @param criteria Hibernate criteria对象
     * @param commonFolder 查询对象
     */
    private void addQueryCause(Criteria criteria,CommonFolder commonFolder){
    	 
	}

}
