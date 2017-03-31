package me.huqiao.wss.common.service;

import java.io.Serializable;
import java.util.List;

import me.huqiao.wss.util.web.Page;

/**
 * 业务接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IBaseService<T> {

  
	/**
	 * 根据ID懒加载某实体记录
	 * @param entityClass 实体名称
	 * @param id 查询ID 
	 * @return T 实体记录
	 */
    public T loadById(Class<T> entityClass, Serializable id); 
    
    /**
     * 根据ID得到某实体实例
     * @param entityClass 实体名称
     * @param id 查询ID 
     * @return T 实体记录
     */
    public T getById(Class<T> entityClass, Serializable id);  
  
   
    /**
     * 获取所有实体  
     * @param entityClass 获取实体类
     * @return 记录列表
     */
    public List<T> findAll(Class<T> entityClass);  
    /**
     * 添加实体 
     * @param entity 实体类名
     * @throws RuntimeException 运行异常
     */
    public void add(T entity) throws RuntimeException;  
    
    /**
     * 批量添加
     * @param entities 需要添加的实体集合
     */
    public void add(List<T> entities);
  
 
    /**
     * 修改当前实体
     * @param entity 类实体
     * @throws RuntimeException 运行异常
     */
    public void update(T entity) throws RuntimeException;
    
   
    /**
     * 删除某一实体
     * @param entity 类实体
     * @throws RuntimeException 运行异常
     */
    public void delete(T entity) throws RuntimeException;  
  

    /**
     * 查找指定属性的实体集合 
     * @param entityClass 实体类
     * @param propertyName 属性名
     * @param value 属性值
     * @return List<T> 记录列表
     */
    public List<T> getByProperty(Class<T> entityClass, String propertyName,Object value);
    
    
    /**
     * 根据指定属性得到唯一对象
     * @param entityClass 实体名称
     * @param propertyName 属性名
     * @param value 属性值
     * @return  T 实体记录
     */
    public T getEntityByProperty (Class<T> entityClass, String propertyName,Object value);
 
    /**
     * 得到某实体类的记录数
     * @param entityClass
     * @return int 记录数
     */
    public int getTotalCount (Class<T> entityClass);
    
  
    /**
     * 根据某属性值查询等于该属性值的记录数
     * @param entityClass 实体类
     * @param propertyName 属性名
     * @param propertyValue 属相值
     * @return Long 记录数量
     */
    public Long getRowCountByProperty(final Class<T> entityClass,final String propertyName,final String propertyValue);
    
  
    /**
     * 根据多个属性值查询等于该属性值的记录数
     * @param entityClass 实体名
     * @param propertyNames 属性数组
     * @param values 值数组
     * @param orderBy 排序
     * @param limit 限制数
     * @return List<T> 记录列表
     */
    public List<T> getByProperties(Class<T> entityClass, String[] propertyNames, Object[] values,String orderBy,Integer limit);
    
    /**
     * 查询指定版本历史记录
     * @param c 类型
     * @param key 主键值
     * @param version 版本号
     * @return 查询结果
     */
    public Object findByPreVersion(Class c, Serializable key, Integer version);
    
    
    /**
     * 获取到分页查询信息
     * @param param 查询对象
     * @param pageInfo 分页数据
     * @return 查询到的分页结果
     */
    public Page<T> getListPage(T param,Page pageInfo);
    
    
    /**
     * 按照Excel单元格值查询对象
     * @param entityClass 要查询的类
     * @param cellValue 单元格内容
     * @return T 查询到的结果
     */
    public T queryByExcelCellValue(Class<T> entityClass,String cellValue);

    
}
