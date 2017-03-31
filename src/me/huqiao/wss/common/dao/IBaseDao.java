package me.huqiao.wss.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 * 数据访问操作基类接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IBaseDao<T> {

	/**
	 * 根据ID获取某实体实例
	 * @param entityClass 要获取的实体类
	 * @param id 要获取对象的ID
	 * @return 实体对象
	 */
    public T loadById(final Class<T> entityClass, final Serializable id);  
    
    /**
     * 根据ID获取某实体实例
     * @param entityClass 要获取的实体类
     * @param id 要获取对象的ID
     * @return T 实体对象
     */
    public T findById(final Class<T> entityClass, final Serializable id);  
 
    /**
     * 查询所有实体  
     * @param entityClass 实体类
     * @return List<T> 记录列表
     */
    public List<T> findAll(final Class<T> entityClass);  
  
    /**
     * 保存实体 
     * @param entity 要保存的实体对象
     */
    public void save(T entity);  
  
    /**
     * 更新当前实体
     * @param entity 实体对象
     */
    public void update(T entity);
    
    /**
     * 删除某一实体
     * @param entity 要删除的实体
     */
    public void delete(T entity);  
  
    /**
     * 查找指定属性的实体集合 
     * @param entityClass 实体类
     * @param propertyName 指定的属性
     * @param value 属性值
     * @return List<T> 实体列表
     */
    public List<T> findByProperty(final Class<T> entityClass,final String propertyName, final Object value);
   
    /**
     * 按HQL语句查询
     * @param hql HQL字符串
     * @param params 参数
     * @return List<T> 记录列表
     */
    public List<T> findByHQL(String hql,Object[] params);
    
    /**
     * 按HQL语句查询
     * @param hql HQL字符串
     * @param paramValues 参数键值对
     * @return List<T> 记录列表
     */
    
    public List<T> findByHQL(String hql, Map<String,Object> paramValues);
  
    /**
     * 按HQL语句查询
     * @param hql HQL字符串
     * @param paramValues 参数键值对
     * @return List 记录列表
     */
    public List findByHQL2(String hql, Map<String,Object> paramValues);
    
    /**
     * 按HQL语句查询
     * @param hql HQL字符串
     * @param params 参数数组
     * @return Object 查询记录
     */
    public Object findByHQLForUniqueResult(String hql,Object[] params);
   
    /**
     * 根据某一属性查询唯一实体类
     * @param entityClass 实体名
     * @param propertyName 属性名
     * @param value  属性值
     * @return T 记录
     */
    public T findEntityByProperty(final Class<T> entityClass,final String propertyName,  
            final Object value);
    

    /**
     * 创建Criteria查询对象类
     * @param entityClass 实体类
     * @param criterions  Hibernate Criteria对象
     * @return Criteria Criteria对象
     */
    public Criteria createCriteria(final Class<T> entityClass,final Criterion...criterions);
    
    
    /**
     * 查询某实体对象的集合数
     * @param entityClass 实体类
     * @return Long 实体总数量
     */
    public Long findCount(final Class<T> entityClass);

  
    /**
     * 分页查询信息
     * @param entityClass 实体对象
     * @param currentNum 当前数
     * @param pageCount 每页显示数量
     * @return List<T> 记录列表
     */
    public List<T> findByPage(final Class<T> entityClass,final int currentNum,int pageCount);

    /**
     * 根据某属性值查询等于该属性值的记录数
     * @param entityClass 持久化实体类
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return Long 录数总数量
     */
    public Long findRowCountByProperty(final Class<T> entityClass,final String propertyName,final String propertyValue); 
   
    /**
     * 根据HQL查询唯一结果
     * @param hql HQL字符串
     * @param params 参数键值对
     * @return Object 记录
     */
    public Object findByHQLForUniqueResult(String hql, Map<String,Object> params);
    
    /**
     * 根据一或多个属性查询
     * @param entityClass 实体类
     * @param propertyNames 属性名数组
     * @param values 属性值数组
     * @param orderBy 排序的字段
     * @param limit 限制的数量
     * @return List<T> 记录列表
     */
    public List<T> findByProperties(Class<T> entityClass, String[] propertyNames, Object[] values, String orderBy,Integer limit);
    
    /**
     * 查询指定版本历史记录
     * @param c 实体类
     * @param key 标识
     * @param version 版本号
     * @return Object 实体对象
     */
    public Object findByPreVersion(Class c, Serializable key, Integer version);
}
