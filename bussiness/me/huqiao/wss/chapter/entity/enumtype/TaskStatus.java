package me.huqiao.wss.chapter.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TaskStatus {
	 NotStart("未开始"),Scheduled("计划中"),Executing("执行中"),Ended("已结束");
	 /**  description 描述信息*/
	    private String description;
	    private TaskStatus(String description){this.description = description;}
	    /**
		  *  获取描述信息
	      * @return String 描述信息
		  */
	    public String getDescription(){return description;}
	    /**
		  * 枚举对象-描述信息 键值对
	      * @return Map<ScheduleTaskStatus,String> 枚举对象-描述信息 键值对
		  */
	    public final static Map<TaskStatus,String> scheduleTaskStatusMap = new LinkedHashMap<TaskStatus,String>();
	    static{
	        for(TaskStatus s : TaskStatus.values()){
	          scheduleTaskStatusMap.put(s,s.getDescription());  
	        }
	    }
}
