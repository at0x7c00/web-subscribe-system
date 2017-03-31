package me.huqiao.wss.common.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用状态 <br>InUse("InUse"),UnUse("Disabled")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum UseStatus {
	InUse("启用"),UnUse("禁用");
	/**描述*/
	private String description;

	private UseStatus(String description) {
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
	public final static Map<UseStatus, String> useStatusMap = new LinkedHashMap<UseStatus, String>();
	static {
		for (UseStatus s : UseStatus.values()) {
			useStatusMap.put(s, s.getDescription());
		}
	}
}