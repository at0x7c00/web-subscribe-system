package me.huqiao.wss.sys.service;
import java.util.Collection;
import java.util.List;

import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.util.web.Page;
/**
 * 部门Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IDepartmentService extends IBaseService<Department> {
    /**
     * 部门分页查询
     * @param department 查询对象
     * @param pageInfo 分页查询对象
     * @return Page<Department> 部门分页信息对象
     */
    public Page<Department> getListPage(Department department,Page pageInfo);
	/**
	  * 部门历史记录分页查询
	  * @param department 查询对象
	  * @param pageInfo 分页查询对象
	  * @return Page<HistoryRecord<Department>> 部门历史分页信息对象
	  */
	public Page<HistoryRecord<Department>> getHistoryListPage(Department department,Page pageInfo);
	/**
	 * 部门版本号查询
	 * @param version 查询版本号
	 * @return Department 部门历史记录
	 */
	public Department findByVersion(Integer version);
	/**
	 * 部门关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<Department> 部门分页信息对象
	 * 
	 */
	Page<Department> queryByKey(String queryKey, Page<Department> pageInfo);
	/**
	 * 查找多个部门
	 * @param ids id数组
	 * @return List<Department> 部门列表
	 * 
	 */
	List<Department> queryById(Integer[] ids);
	

	/**
	 * 遍历所有部门信息，生成树状信息List
	 * 
	 * @param topDepartments
	 *           顶级部门
	 * @param prefixCode
	 *            前缀
	 * @param filterId
	 *            过滤的ID
	 * @param result
	 *            结果集合
	 */
	public void tree(Collection<Department> topDepartments, String prefixCode, List<Department> result, Integer filterId) ;
}