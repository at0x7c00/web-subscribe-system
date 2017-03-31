package me.huqiao.wss.util;




/**
 * 通用工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class CommonUtil {

    
	/**
	 * 判断字符串对象是否为空或空字符串
	 * @param str 目标字符串
	 * @return boolean 是否为空
	 */
    public static boolean isNotNullAndEmptyForStr(String str) {
        if (str == null || str.trim().equals("")) 
            return false;
        else 
            return true;
    }
  
}
