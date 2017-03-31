package me.huqiao.wss.common.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 周类别<br>Monday("星期一"),Tuesday("星期二"),Wednesday("星期三"),Thursday("星期四"),Friday("星期五"),Saturday("星期六"),Sunday("星期日")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum DayOfWeek {
	Monday("星期一"),Tuesday("星期二"),Wednesday("星期三"),Thursday("星期四"),Friday("星期五"),Saturday("星期六"),Sunday("星期日");
	/**描述*/
	private String description;
	private DayOfWeek(String description) {
		this.description = description;
	}
   /**
    * @return String 获取描述
    */
	public String getDescription() {
		return description;
	}
	/** 
	 *  获取描述信息
	 *  枚举对象-描述信息 键值对
	 *  @return Map<${entity.className},String> 枚举对象-描述信息 键值对
	 */  
	public final static Map<DayOfWeek, String> dayOfWeekMap = new LinkedHashMap<DayOfWeek, String>();
	static {
		for (DayOfWeek s : DayOfWeek.values()) {
			dayOfWeekMap.put(s, s.getDescription());
		}
	}

}
