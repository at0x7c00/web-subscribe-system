package me.huqiao.wss.sys.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户类型 <br/>Local("Local"), Domain("Domain")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum UserType {
	Local("Local"), Domain("Domain");
	/**
	 * 描述
	 */
	private String description;

	private UserType(String description) {
		this.description = description;
	}
   /**
    * 获取描述
    * @return String 描述
    */
	public String getDescription() {
		return description;
	}

	public final static Map<UserType, String> userTypeMap = new LinkedHashMap<UserType, String>();
	static {
		for (UserType s : UserType.values()) {
			userTypeMap.put(s, s.getDescription());
		}
	}
}