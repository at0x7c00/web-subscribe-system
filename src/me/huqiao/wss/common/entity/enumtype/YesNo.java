package me.huqiao.wss.common.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 是否 <br>Yes("Yes"), No("No")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum YesNo {
	Yes("Yes"), No("No");
	private String description;

	private YesNo(String description) {
		this.description = description;
	}
    /**
     * 获取描述
     * @return String 描述
     */
	public String getDescription() {
		return description;
	}
	/** 
	 *  获取描述信息
	 *  枚举对象-描述信息 键值对
	 *  @return Map<${entity.className},String> 枚举对象-描述信息 键值对
	 */ 
	public final static Map<YesNo, String> yesNoMap = new LinkedHashMap<YesNo, String>();
	static {
		for (YesNo s : YesNo.values()) {
			yesNoMap.put(s, s.getDescription());
		}
	}
//	public static void main(String[] args) {
//		System.out.println(YesNo.Yes.toString().equals("Yes"));
//	}
}