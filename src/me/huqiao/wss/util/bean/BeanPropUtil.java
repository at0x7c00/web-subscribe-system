package me.huqiao.wss.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean属性操作工具
 * @author huqiao
 *
 */
public class BeanPropUtil {
	
	/**
	 * 字段值变化检查
	 * @param o1 比较对象
	 * @param o2 比较对象
	 * @return Map<String,CheckResult> 比较结果Map
	 */
	public static Map<String,CheckResult> propValueCheck(Object o1,Object o2){
		Map<String,CheckResult>  res = new HashMap<String,CheckResult>();
		if(o1==null || o2==null){
			return res;
		}
		Class c1 = o1.getClass();
		Class c2 = o2.getClass();
		
		checkByExtends(o1, o2, res, c1, c2);
		return res;
	}

	private static void checkByExtends(Object o1, Object o2, Map<String, CheckResult> res, Class c1, Class c2) {
		for(Field f1 : c1.getDeclaredFields()){
			Object v1 = null;
			Object v2 = null;
			try {
				f1.setAccessible(true);
				 v1 = f1.get(o1);
				Field f2 = c2.getDeclaredField(f1.getName());
				f2.setAccessible(true);
				 v2 = f2.get(o2);
				
				if(v1 instanceof Collection && v2 instanceof Collection){
					Collection coll1 = (Collection)v1;
					Collection coll2 = (Collection)v2;
					
					boolean change = false;
					for(Object o : coll2){
						boolean find = false;
						for(Object oo : coll1){
							find = o==null ? oo==null : o.equals(oo);
							if(find){
								break;
							}
						}
						if(!find){
							change = true;
							break;
						}
					}
					if(!change){
						for(Object o : coll1){
							boolean find = false;
							for(Object oo : coll2){
								find = o==null ? oo==null : o.equals(oo);
								if(find){
									break;
								}
							}
							if(!find){
								change = true;
								break;
							}
						}
					}
					//System.out.println(f1.getName()+":" + change);
					res.put(f1.getName(), new CheckResult(change,""));
				}else{
					if((v1==null && v2==null)||v1==v2 || (v1!=null && v1.equals(v2)) || (v2!=null && v1!=null && v1.toString().equals(v2.toString()))){
					//	System.out.println(f1.getName()+":false");
						res.put(f1.getName(), new CheckResult(false,""));
					}else{
					//	System.out.println(f1.getName()+":true");
						res.put(f1.getName(), new CheckResult(true,"从「"+format(v1)+"」修改为「"+format(v2)+"」"));
					}
				}
			} catch (Exception e) {
				//System.out.println(v1);
				//System.out.println(v2);
				e.printStackTrace();
			}
		}
		if(c1.getSuperclass()!=null && c2.getSuperclass()!=null){
			checkByExtends(o1,o2,res,c1.getSuperclass(),c2.getSuperclass());
		}
	}


	public static String readPropAsString(Object target, String name) {
		Object value = null;
		try {
			value = readProp(target, name);
		} catch (NoSuchFieldException e) {
			return "";
		}
		return format(value);
	}
	
	public static void writeProp(Object target, String name,Object value) throws NoSuchFieldException {
		if(target==null){
			return;
		}
		
		String methodName = "set"+ name.substring(0,1).toUpperCase() + name.substring(1);
		Class class1 = target.getClass();
		Method m = null;
		Class tmpClass = null;
		
		tmpClass = class1;
		
		while(m==null && tmpClass!=null){
			try {
				Class paramClass = value.getClass();
				//if(paramClass.getName().startsWith("java.util.LinkedHashSet")){
				//	paramClass = Set.class;
				//}
				
				while(paramClass!=null && m==null){
					try{
						m = tmpClass.getDeclaredMethod(methodName,paramClass);
					}catch(Exception ex){
						paramClass = paramClass.getSuperclass();//尝试参数的父类
					}
				}
				
				if(m==null){
					for(Class pClass : value.getClass().getInterfaces()){//尝试接口
						try{
							m = tmpClass.getDeclaredMethod(methodName,pClass);
							break;
						}catch(Exception e){
						}
					}
				}
				if(m==null){
					throw new NoSuchMethodException();
				}
			} catch (Exception e) {
				tmpClass = tmpClass.getSuperclass();
			}
		}
		
		if(m==null){
			throw new NoSuchFieldException("从类"+class1.getSimpleName() + "中找不到字段"+ name);
		}
		
		m.setAccessible(true);
		try {
			m.invoke(target,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		/*for(Method method : c.getDeclaredMethods()){
			System.out.println(method.getName());
			for(Class type : method.getParameterTypes()){
				System.out.println(type);
			}
			System.out.println();
		}*/
	}
	public static Object readProp(Object target, String name) throws NoSuchFieldException {
		if(target==null){
			return null;
		}
		
		String methodName = "get"+ name.substring(0,1).toUpperCase() + name.substring(1);
		Class class1 = target.getClass();
		Method m = null;
		Class tmpClass = null;
		
		tmpClass = class1;
		while(m==null && tmpClass!=null){
			try {
				m = tmpClass.getDeclaredMethod(methodName);
			} catch (Exception e) {
				tmpClass = tmpClass.getSuperclass();
			}
		}
		
		if(m==null){
			throw new NoSuchFieldException();
		}
		
		m.setAccessible(true);
		try {
			return  m.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String format(Object obj){
		if(obj instanceof Date){
			String tmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj);
			tmp = tmp.replaceAll(" 00:00:00", "");
			return tmp;
		}
		if(obj instanceof String){
			if(obj==null || obj.toString().trim().equals("")){
				return "";
			}else{
				return (String)obj;
			}
		}
		if(obj==null){
			return "";
		}
		boolean findFieldSuccess = true;
		Object nameValueObject = null;
		try {
			nameValueObject = readProp(obj,"name");
		} catch (NoSuchFieldException e) {
			try {
				nameValueObject = readProp(obj,"userName");
			} catch (NoSuchFieldException e1) {
				//findFieldSuccess = false;
				try {
					nameValueObject = readProp(obj,"description");
				} catch (NoSuchFieldException e2) {
					findFieldSuccess = false;
				}
			}
		}
		if(findFieldSuccess){
			return nameValueObject==null ? null : nameValueObject.toString();
		}
		return obj.toString();
	}
	
	
	/**
	 * 将复杂对象格式化为字符串
	 * @param obj  复杂对象
	 * @param tryToDisplayPropOfComplexObject 对象复杂属性列表
	 * @return String 转换成的结果
	 */
	public static String format(Object obj,List<String> tryToDisplayPropOfComplexObject){
		if(obj instanceof Date){
			String tmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj);
			tmp = tmp.replaceAll(" 00:00:00", "");
			return tmp;
		}
		if(obj instanceof String){
			if(obj==null || obj.toString().trim().equals("")){
				return "";
			}
		}
		if(obj==null){
			return "";
		}
		boolean findFieldSuccess = false;
		Object nameValueObject = null;
		
		if(tryToDisplayPropOfComplexObject!=null){
			for(String propToDisplay : tryToDisplayPropOfComplexObject){
				try{
					nameValueObject = readProp(obj,propToDisplay);
					findFieldSuccess = true;
					break;
				}catch(Exception e){
				}
			}
		}
		if(findFieldSuccess){
			return nameValueObject==null ? null : nameValueObject.toString();
		}
		if(obj.getClass().isEnum()){
			return readPropAsString(obj, "description");
		}
		return obj.toString();
	}
	
	
	/**
	 * 判断是否含制定属性名称
	 * @param c Class对象
	 * @param propName 属性名
	 * @return 是否含有的结果
	 */
	public static boolean hasProp(Class c,String propName){
		while(c!=null){
			try {
				return c.getDeclaredField(propName)!=null;
			} catch (Exception e) {
				c = c.getSuperclass();
			}
		}
		return false;
	}
	
}
