package me.huqiao.wss.chapter.dao;
import java.util.List;

import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 采集任务DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ITaskDao extends IBaseDao<Task> {
    /**
     * 采集任务查询记录数量
     * @param task 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(Task task);
	/**
	 * 采集任务历史记录数量
     * @param task 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(Task task,Page pageInfo);
    /**
     * 采集任务分页查询
     * @param task 查询对象
     * @param pageInfo 分页查询对象
     * @return  List<Task>  采集任务列表 
     */
    List<Task> findListPage(Task task, Page pageInfo);
	/**
	 * 采集任务历史记录分页查询
     * @param task 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<Task>>  采集任务历史列表
	 */
    List<HistoryRecord<Task>> findHistoryListPage(Task task, Page pageInfo);
	/**
     * 采集任务版本号查询
     * @param version 版本号
	 * @return Task 采集任务历史记录
     */
	Task findByVersion(Integer version);
	/**
	 * 添加采集任务查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param task 查询对象
	 */
	public void queryCause(Criteria criteria, Task task);
	/**
	 * 采集任务关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<Task> 采集任务列表
	 */
	List<Task> findByKey(Page pageInfo,String queryKey);
	/**
	 * 采集任务关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多个采集任务
     * @param  ids id数组
	 * @return List<Task>  采集任务列表
     */
	List<Task> findById(Integer[] ids);
}