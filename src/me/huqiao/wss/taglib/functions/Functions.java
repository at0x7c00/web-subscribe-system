package me.huqiao.wss.taglib.functions;

public class Functions {

	/**
	 * 判断逗号分隔的字符串是否包含一个子字符串
	 * @param str 目标字符串
	 * @param substr 子字符串
	 * @return boolean 是否包含结果
	 */
	public static boolean contains(String str,String substr){
		if(str==null || str.trim().equals("")){
			return false;
		}
		if(str.indexOf(",")<0){
			return str.equals(substr);
		}
		for(String s : str.split(",")){
			if(s.equals(substr)){
				return true;
			}
		}
		return false;
	}
	
}
