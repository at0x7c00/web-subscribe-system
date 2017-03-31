package me.huqiao.wss.chapter.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.chapter.service.ICategoryService;
/**
 * 分类编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class CategoryEditor extends PropertyEditorSupport{
    public ICategoryService categoryService;
    public CategoryEditor(ICategoryService categoryService){
        this.categoryService = categoryService;
    }
    public String getAsText(){
        Category category = (Category)getValue();
        if(category==null){
            return "";
        }
        return String.valueOf(category.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        Category category = null;
category = categoryService.getEntityByProperty(Category.class,"manageKey",key);
if(category==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            category = categoryService.getById(Category.class,integerId);
}
if(key!=null && !key.trim().equals("") && category==null){
category=new Category();
}
        setValue(category);
    }
}