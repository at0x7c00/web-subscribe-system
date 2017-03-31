package me.huqiao.wss.datamodel;

import java.lang.reflect.Field;

public class PropUtil {

	public static void setValue(Object obj,String propName,Object value) throws IllegalArgumentException, IllegalAccessException{
		Field f = findField(obj.getClass(),propName);
		if(f!=null){
			f.setAccessible(true);
			f.set(obj, value);
		}
	}
	
	public static Object getValue(Object obj,String propName) throws IllegalArgumentException, IllegalAccessException{
		Field f = findField(obj.getClass(),propName);
		if(f!=null){
			f.setAccessible(true);
			return f.get(obj);
		}
		return null;
	}
	
	private static Field findField(Class c,String fieldName){
		Field field = null;
		while(field==null && c!=null){
			try {
				field = c.getDeclaredField(fieldName);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			c = c.getSuperclass();
		}
		return field;
	}
	
}
