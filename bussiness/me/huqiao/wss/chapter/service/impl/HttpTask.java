package me.huqiao.wss.chapter.service.impl;

import java.net.URL;
import java.util.Date;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.entity.enumtype.TaskStatus;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.listener.ApplicationContextAccessUtilListener;
import me.huqiao.wss.util.DateUtil;
import me.huqiao.wss.util.Md5Util;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpTask implements Runnable {
	
	static Logger log = Logger.getLogger(HttpTask.class);

	private Task task;
	
	
	public HttpTask(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		doTask();
	}
	
	public void doTask(){
		ITaskService taskService = ApplicationContextAccessUtilListener.getContext().getBean(ITaskService.class);
		IGatherResultService grService = ApplicationContextAccessUtilListener.getContext().getBean(IGatherResultService.class);
		
		Date now = new Date();
		task.setStatus(TaskStatus.Executing);
		taskService.update(task);
		
		String urlPath = task.getUrl();
		
		log.info("schedule task to invoke http api : " + urlPath);
		
		try{
			
			URL url = new URL(urlPath);
			String oldRes = task.getLog();
			
			String res = "";
			
			Document doc = Jsoup.connect(urlPath).timeout(80*1000).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36").get();
			
			for(Element e :doc.select(task.getSelector())){
				String href = e.attr("href");
				String title = e.html();
				GatherResult gr = new GatherResult();
				gr.setCreateTime(new Date());
				gr.setManageKey(Md5Util.getManageKey());
				gr.setStatus(UseStatus.InUse);
				gr.setTask(task);
				gr.setTitle(title);
				gr.setUrl(href);
				if(!grService.existed(gr)){
					grService.add(gr);
				}
				
				res += title + ",";
			}
			
			res = (oldRes==null ? "" : (oldRes + "\r\n")) +"["+DateUtil.formatDate(now,"yyyy-MM-dd HH:mm:ss")+"]" + url + ":" + res;
			task.setLog(res);
			task.setStatus(TaskStatus.Scheduled);
			
		}catch(Exception e){
			//e.printStackTrace();
			StringBuffer s = new StringBuffer();
			s.append(e.getMessage()).append("\r\n");
			for(StackTraceElement ste : e.getStackTrace()){
				s.append(ste.getClassName())
				.append(".").append(ste.getMethodName())
				.append(" (").append(ste.getFileName()).append(")[Line:").append(ste.getLineNumber()).append("]").append("\r\n");
			}
			task.addLog(task.getUrl() + "异常！！！" + s.toString());
			task.setStatus(TaskStatus.Scheduled);
		}
		taskService.update(task);
		
		log.info("task complated.");
	}

}
