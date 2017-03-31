package me.huqiao.wss.util.web;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import me.huqiao.wss.util.DateUtil;

import org.springframework.util.StringUtils;
/**
 * 日期属性编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class CommonDateEditor extends PropertyEditorSupport implements PropertyEditor{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	    if(StringUtils.isEmpty(text))
	        return;
	    Date date = null;
	    for(Map.Entry<String,String> entry : DateUtil.patterns.entrySet()){
	        try{
	        	date = new SimpleDateFormat(entry.getKey()).parse(text);
	            break;
	        }catch(Exception e){
	        }
	    }
	    if(date != null)
	        setValue(date);
	}

	@Override
	public String getAsText() {
	    Date date = (Date) getValue();
	    if(date == null)
	        return "";
	    return new SimpleDateFormat(DateUtil.EN_YEAR_MONTH_DAY).format(date);

	}
}
