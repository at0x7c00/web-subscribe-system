package me.huqiao.wss.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 字符串工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class StringUtil {
	
	/**
	 * 获取对象或对象的属性的字符串值<br/>
	 * @param obj eg student
	 * @param props eg "Teacher","Age" 获取学生的教师的年龄的字符串表单形式
	 * @return String 对象属性字符串
	 */
	public static String mildToString(Object obj,String...props){
		try{
			if(obj==null){
				return "";
			}else if(obj instanceof String){
				return obj.toString();
			}else if(props!=null && props.length>0 && props[0]!=null){//复杂类型获取指定字段
				Method m=findMethodFromClassByName(obj.getClass(),"get"+props[0]);
				if(m==null){
					return obj.toString();
				}
				Object result= m.invoke(obj);
				if(props.length>1){
					String[] newProps=new String[props.length-1]; 
					for(int i=1;i<props.length;i++){
						newProps[i-1]=props[i];
					}
					return mildToString(result,newProps);
				}else{
					return mildToString(result);
				}
			}
		}catch(Exception e){
			//System.out.println("exception ocur when read "+props[0]+" on " + obj);
			e.printStackTrace();
		}
		//System.out.println("read"+ obj.toString()+" for prop : "+ props);
		return obj.toString();
	}
	
	/**
	 * 尝试从Class或Class的超类获取指定名称的方法
	 * @param clazz 实体
	 * @param name 方法名
	 * @return Method 没有找到指定的方法时返回null
	 */
	public static Method findMethodFromClassByName(Class clazz,String name){
		if(clazz==null){
			return null;
		}
		Method m = null;
		try {
			 m = clazz.getDeclaredMethod(name);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			return findMethodFromClassByName(clazz.getSuperclass(),name);
		}
		return m;
	}
	/**
	 * 尝试从Class或Class的超类获取指定名称的字段
	 * @param clazz
	 * @param name
	 * @return Field 对象字段 ，没有找到指定的方法时返回null
	 */
	public static Field findFieldFromClassByName(Class clazz,String name){
		if(clazz==null){
			return null;
		}
		Field f = null;
			try {
				f = clazz.getDeclaredField(name);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				return findFieldFromClassByName(clazz.getSuperclass(),name);
			}
		return f;
	}
	
	/**
	 * 把15位身份证转换成18位
	 * @param id 要转换对象
	 * @return String 18位字符串
	 */
	public static final String getIDCard_18bit(String id){
		// 若是15位，则转换成18位；否则直接返回ID
		if (15 == id.length()) {
			final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
			final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
			int i, j, s = 0;
			String newid;
			newid = id;
			newid = newid.substring(0, 6) + "19" + newid.substring(6, id.length());
			for (i = 0; i < newid.length(); i++) {
				j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
				s = s + j;
			}
			s = s % 11;
			newid = newid + A[s];

			return newid;
		} else {
			return id;
		}

	}
	/**
	* 从身份证获取出生日期
	* @param cardNumber 已经校验合法的身份证号
	* @return String YYYY-MM-DD 出生日期
	*/
	public static String getBirthDateFromCard(String cardNumber){
	String card = cardNumber.trim();
		String year;
		String month;
		String day;
		if (card.length() == 18) { // 处理18位身份证
			year = card.substring(6, 10);
			month = card.substring(10, 12);
			day = card.substring(12, 14);
		} else { // 处理非18位身份证
			year = card.substring(6, 8);
			month = card.substring(8, 10);
			day = card.substring(10, 12);
			year = "19" + year;
		}
		if (month.length() == 1) {
			month = "0" + month; // 补足两位
		}
		if (day.length() == 1) {
			day = "0" + day; // 补足两位
		}
		return year + "-" + month + "-" + day;
	}
	
    /**
     * 判断字符串是否不为空
     * @return boolean 是否不为空
     */
    public static boolean isNotEmpty(String value){
        if(value == null)
            return false;
        if(value.trim().equals(""))
            return false;
        return true;
    }
    
    /**
     * 判断字符串是否为空
     * @return boolean 是否为空
     */
    public static boolean isEmpty(String value){
        return value==null || value.trim().equals("");
    }
    
    /**
     * 判断字符串中是否全是数字
     * @param value 要判断的字符串
     * @return boolean 是否全是数字
     */
    public static boolean isNum(String value){
    	boolean isNum = value.matches("[0-9]+");
    	return isNum;
    }
    
    /**
     * 小写第一个字母
     * @param str 要操作的字符串
     * @return String 小写后的结果
     */
    public static String lowerCaseFistLatter(String str){
    	if(str==null || str.trim().equals("")){
    		return str;
    	}
    	if(str.length()==1){
    		return str.toLowerCase();
    	}
    	return str.substring(0,1).toLowerCase() + str.substring(1);
    	
    }
    
    
    public static String formatNumAsFixLength(int value,int length){
    	String str = "";
    	for(int i = 0;i<length;i++){
    		str += "0";
    	}
    	String strValue = value+"";
    	if(str.length() < strValue.length()){
    		return strValue;
    	}
    	
    	str = str.substring(0,str.length() - strValue.length()) + strValue;
    	return str;
    	
    }
 
   public static void main(String[]args){
	   System.out.println(formatNumAsFixLength(999,4));
	   
   }
}
