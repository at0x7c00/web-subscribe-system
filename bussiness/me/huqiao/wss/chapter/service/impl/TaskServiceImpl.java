package me.huqiao.wss.chapter.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.ITaskDao;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.entity.enumtype.TaskStatus;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 采集任务Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements ITaskService {
	
	Logger log = Logger.getLogger(TaskServiceImpl.class);
	
	private Map<String,ScheduledFuture> taskIdFutureMap = new ConcurrentHashMap<String, ScheduledFuture>();
	private ScheduledExecutorService service = Executors.newScheduledThreadPool(20);
	
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
	
	
	@Override
	public void init() {
		List<Task> tasks = findAllActive();
		Date now = new Date();
		for(Task task : tasks){
			startFutureForTask(task);
		}
	}
	
	private void startFutureForTask(Task task){
		ScheduledFuture future = null;
		
		String key = task.getManageKey();
		future = taskIdFutureMap.get(key);
		if(future!=null){
			future.cancel(true);
		}
		
		Date now = new Date();
		long delay = 0;
		future = service.scheduleAtFixedRate(new HttpTask(task), delay, task.getCycle().longValue(), TimeUnit.MINUTES);
		taskIdFutureMap.put(key, future);
	}
	
	private void stopFutureForTask(Task task,Project project){
		ScheduledFuture future = null;
		
		String key = task.getManageKey();
		future = taskIdFutureMap.get(key);
		if(future==null){
			return;
		}
		future.cancel(true);
		taskIdFutureMap.remove(key);
	}
	
	@Override
	public List<Task> findAllActive() {
		return taskDao.findAllActive();
	}
	@Override
	public void updateToStop(Task task) {
		String key = task.getManageKey();
		ScheduledFuture future = taskIdFutureMap.get(key);
		if(future!=null){
			future.cancel(true);
		}
		taskIdFutureMap.remove(key);
		task.setStatus(TaskStatus.Ended);
		update(task);
		
	}
	
	@Override
	public void updateToStart(Task task) {
		startFutureForTask(task);
		task.setStatus(TaskStatus.Scheduled);
		update(task);
	}
	
	
	@Override
	@Transactional
	public void execute(Task task) {
		new HttpTask(task).doTask();
	}
	
	
}