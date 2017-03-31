package me.huqiao.wss.chapter.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.service.ITagService;
/**
 * 标签编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class TagEditor extends PropertyEditorSupport{
    public ITagService tagService;
    public TagEditor(ITagService tagService){
        this.tagService = tagService;
    }
    public String getAsText(){
        Tag tag = (Tag)getValue();
        if(tag==null){
            return "";
        }
        return String.valueOf(tag.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        Tag tag = null;
tag = tagService.getEntityByProperty(Tag.class,"manageKey",key);
if(tag==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            tag = tagService.getById(Tag.class,integerId);
}
if(key!=null && !key.trim().equals("") && tag==null){
tag=new Tag();
}
        setValue(tag);
    }
}