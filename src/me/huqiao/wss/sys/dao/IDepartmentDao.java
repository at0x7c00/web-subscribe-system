package me.huqiao.wss.sys.dao;
import java.util.List;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 部门DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IDepartmentDao extends IBaseDao<Department> {
    /**
     * 部门查询记录数量
     * @param department 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(Department department);
	/**
	 * 部门历史记录数量
     * @param department 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(Department department,Page pageInfo);
    /**
     * 部门分页查询
     * @param department ��询对象
     * @param pageInfo 分页查询对象
     * @return  List<Department>  部门列表 
     */
    List<Department> findListPage(Department department, Page pageInfo);
	/**
	 * 部门历史记录分页查询
     * @param department 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<Department>>  部门历史列表
	 */
    List<HistoryRecord<Department>> findHistoryListPage(Department department, Page pageInfo);
	/**
     * 部门版本号查询
     * @param version 版本号
	 * @return Department 部门历史记录
     */
	Department findByVersion(Integer version);
	/**
	 * 添加部门查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param department 查询对象
	 */
	public void queryCause(Criteria criteria, Department department);
	/**
	 * 部门关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<Department> 部门列表
	 */
	List<Department> findByKey(Page pageInfo,String queryKey);
	/**
	 * ���门关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多个部门
     * @param  ids id数组
	 * @return List<Department>  部门列表
     */
	List<Department> findById(Integer[] ids);
}