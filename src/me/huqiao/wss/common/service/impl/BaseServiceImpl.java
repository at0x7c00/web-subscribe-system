package me.huqiao.wss.common.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.util.bean.BeanPropUtil;
import me.huqiao.wss.util.web.Page;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * 基本业务实现类
 * @author NOVOTS
 * @version Version 1.0
 */
@Service("baseService")
public class BaseServiceImpl<T> implements IBaseService<T> {

	static Logger log = Logger.getLogger(BaseServiceImpl.class);
	
	@Resource
	private IBaseDao<T> baseDao;
	
	@Override
	public T getById(Class<T> entityClass, Serializable id) {
		return baseDao.findById(entityClass, id);
	}

	@Override
	public List<T> findAll(Class<T> entityClass) {
		return baseDao.findAll(entityClass);
	}

	@Override
	public void add(T entity) throws RuntimeException{
		baseDao.save(entity);
	}
	
	@Override
	public void add(List<T> entities){
		for(T entity :  entities){
			baseDao.save(entity);
		}
	}
	
	@Override
	public void update(T entity) throws RuntimeException{
		baseDao.update(entity);
	}

	@Override
	public void delete(T entity) throws RuntimeException{
		baseDao.delete(entity);
	}

	@Override
	public List<T> getByProperty(Class<T> entityClass, String propertyName,
			Object value) {
		return baseDao.findByProperty(entityClass, propertyName, value);
	}

    @Override
    public T getEntityByProperty(Class<T> entityClass, String propertyName, Object value) {
        return baseDao.findEntityByProperty(entityClass, propertyName, value);
    }

    @Override
    public T loadById(Class<T> entityClass, Serializable id) {
        if(id == null)
            return null;
        return baseDao.loadById(entityClass, id);
    }

    @Override
    public int getTotalCount(Class<T> entityClass) {
        return baseDao.findCount(entityClass).intValue();
    }

    @Override
    public Long getRowCountByProperty(Class<T> entityClass, String propertyName,
            String propertyValue) {
        return baseDao.findRowCountByProperty(entityClass, propertyName, propertyValue);
    }
    
	@Override
	public List<T> getByProperties(Class<T> entityClass, String[] propertyNames, Object[] values,String orderBy,Integer limit) {
		return baseDao.findByProperties(entityClass, propertyNames, values,orderBy,limit);
	}

	@Override
	public Object findByPreVersion(Class c, Serializable key, Integer version) {
		return baseDao.findByPreVersion(c, key, version);
	}
	
	@Override
	public Page<T> getListPage(T param, Page pageInfo) {
		return null;
	}

	@Override
	public T queryByExcelCellValue(Class<T> entityClass,String cellValue) {
		List<String> propertyTryToQuery = new ArrayList<String>();
		propertyTryToQuery.add("name");
		propertyTryToQuery.add("username");
		propertyTryToQuery.add("eqNo");
		propertyTryToQuery.add("id");
		//在这里添加其他可能的字段
		
		for(String prop : propertyTryToQuery){
			if(BeanPropUtil.hasProp(entityClass, prop)){
				Object paramValue = null;
				try{
					paramValue = prop.equals("id")? Integer.parseInt(cellValue) : cellValue;
				}catch(Exception e){return null;}
				return getEntityByProperty(entityClass, prop,paramValue);
			}
		}
		return null;
	}
}
