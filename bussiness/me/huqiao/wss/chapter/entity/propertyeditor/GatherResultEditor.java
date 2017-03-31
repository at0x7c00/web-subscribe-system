package me.huqiao.wss.chapter.entity.propertyeditor;
import java.beans.PropertyEditorSupport;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.service.IGatherResultService;
/**
 * 采集结果编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class GatherResultEditor extends PropertyEditorSupport{
    public IGatherResultService gatherResultService;
    public GatherResultEditor(IGatherResultService gatherResultService){
        this.gatherResultService = gatherResultService;
    }
    public String getAsText(){
        GatherResult gatherResult = (GatherResult)getValue();
        if(gatherResult==null){
            return "";
        }
        return String.valueOf(gatherResult.getId());
    }
    public void setAsText(String key)throws IllegalArgumentException {
        GatherResult gatherResult = null;
gatherResult = gatherResultService.getEntityByProperty(GatherResult.class,"manageKey",key);
if(gatherResult==null){
            Integer integerId = null;
            try {integerId = Integer.parseInt(key);} catch (Exception e) {}
            gatherResult = gatherResultService.getById(GatherResult.class,integerId);
}
if(key!=null && !key.trim().equals("") && gatherResult==null){
gatherResult=new GatherResult();
}
        setValue(gatherResult);
    }
}