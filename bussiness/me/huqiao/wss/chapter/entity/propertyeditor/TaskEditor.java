package me.huqiao.wss.chapter.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.chapter.entity.Task;
import me.huqiao.wss.chapter.service.ITaskService;
/**
 * 采集任务编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class TaskEditor extends PropertyEditorSupport{
    public ITaskService taskService;
    public TaskEditor(ITaskService taskService){
        this.taskService = taskService;
    }
    public String getAsText(){
        Task task = (Task)getValue();
        if(task==null){
            return "";
        }
        return String.valueOf(task.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        Task task = null;
task = taskService.getEntityByProperty(Task.class,"manageKey",key);
if(task==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            task = taskService.getById(Task.class,integerId);
}
if(key!=null && !key.trim().equals("") && task==null){
task=new Task();
}
        setValue(task);
    }
}