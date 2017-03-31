package me.huqiao.wss.sys.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.sys.service.IDepartmentService;
/**
 * 部门编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class DepartmentEditor extends PropertyEditorSupport{
    public IDepartmentService departmentService;
    public DepartmentEditor(IDepartmentService departmentService){
        this.departmentService = departmentService;
    }
    public String getAsText(){
        Department department = (Department)getValue();
        if(department==null){
            return "";
        }
        return String.valueOf(department.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        Department department = null;
department = departmentService.getEntityByProperty(Department.class,"manageKey",key);
if(department==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            department = departmentService.getById(Department.class,integerId);
}
if(key!=null && !key.trim().equals("") && department==null){
department=new Department();
}
        setValue(department);
    }
}