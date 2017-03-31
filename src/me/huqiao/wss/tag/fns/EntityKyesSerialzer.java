package me.huqiao.wss.tag.fns;

import java.util.Collection;

import me.huqiao.wss.util.bean.BeanPropUtil;
/**
 * 实体关键字序列化
 * @author NOVOTS
 * @version Version 1.0
 */
public class EntityKyesSerialzer {

	public static String serialKyes(Collection data,String keyName){
		if(data==null || data.size()==0){
			return "";
		}
		StringBuffer res = new StringBuffer();
		for(Object obj : data){
			res.append(BeanPropUtil.readPropAsString(obj, keyName)).append(",");
		}
		if(res.length()>0){
			res.delete(res.length() - 1, res.length());
		}
		return res.toString();
	}
}
