package me.huqiao.wss.chapter.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.chapter.service.IChapterService;
/**
 * 文章编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class ChapterEditor extends PropertyEditorSupport{
    public IChapterService chapterService;
    public ChapterEditor(IChapterService chapterService){
        this.chapterService = chapterService;
    }
    public String getAsText(){
        Chapter chapter = (Chapter)getValue();
        if(chapter==null){
            return "";
        }
        return String.valueOf(chapter.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        Chapter chapter = null;
chapter = chapterService.getEntityByProperty(Chapter.class,"manageKey",key);
if(chapter==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            chapter = chapterService.getById(Chapter.class,integerId);
}
if(key!=null && !key.trim().equals("") && chapter==null){
chapter=new Chapter();
}
        setValue(chapter);
    }
}