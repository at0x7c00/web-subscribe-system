package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.ITaskDao;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 采集任务Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements ITaskService {
    /**采集任务DAO对象*/
    @Resource
    private ITaskDao taskDao;
    @Override
    public Page<Task> getListPage(Task task,Page pageInfo) {
      	pageInfo.setTotalCount(taskDao.findListRowCount(task).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(taskDao.findListPage(task,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<Task>> getHistoryListPage(Task task, Page pageInfo) {
		pageInfo.setTotalCount(taskDao.findHistoryListRowCount(task,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(taskDao.findHistoryListPage(task,pageInfo));
        return pageInfo;
	}
	@Override
	public Task findByVersion(Integer version) {
		return taskDao.findByVersion(version);
	}
	@Override
	public Page<Task> queryByKey(String queryKey, Page<Task> pageInfo) {
		int countRecord = taskDao.findRowCount(queryKey).intValue();
		Page<Task> page = new Page<Task>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<Task> taskList = taskDao.findByKey(pageInfo,queryKey);
		page.setList(taskList);
		return page;
	}
	@Override
	public List<Task> queryById(Integer[] ids) {
		return taskDao.findById(ids);
	}
}