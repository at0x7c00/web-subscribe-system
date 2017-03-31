package me.huqiao.wss.chapter.service;
import java.util.List;

import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;
/**
 * 采集任务Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ITaskService extends IBaseService<Task> {
    /**
     * 采集任务分页查询
     * @param task 查询对象
     * @param pageInfo 分页查询对象
     * @return Page<Task> 采集任务分页信息对象
     */
    public Page<Task> getListPage(Task task,Page pageInfo);
	/**
	  * 采集任务历史记录分页查询
	  * @param task 查询对象
	  * @param pageInfo 分页查询对象
	  * @return Page<HistoryRecord<Task>> 采集任务历史分页信息对象
	  */
	public Page<HistoryRecord<Task>> getHistoryListPage(Task task,Page pageInfo);
	/**
	 * 采集任务版本号查询
	 * @param version 查询版本号
	 * @return Task 采集任务历史记录
	 */
	public Task findByVersion(Integer version);
	/**
	 * 采集任务关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<Task> 采集任务分页信息对象
	 * 
	 */
	Page<Task> queryByKey(String queryKey, Page<Task> pageInfo);
	/**
	 * 查找多个采集任务
	 * @param ids id数组
	 * @return List<Task> 采集任务列表
	 * 
	 */
	List<Task> queryById(Integer[] ids);
}