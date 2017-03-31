package me.huqiao.wss.sys.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户状态<br/> Active("活动"), Approving("待审核"), InActive("已禁止")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum UserStatus {
	Active("活动"), Approving("待审核"), InActive("已禁止");
	/**描述*/
	private String description;

	private UserStatus(String description) {
		this.description = description;
	}
    /**
     * 获取描述
     * @return String 描述
     */
	public String getDescription() {
		return description;
	}

	public final static Map<UserStatus, String> userStatusMap = new LinkedHashMap<UserStatus, String>();
	static {
		for (UserStatus s : UserStatus.values()) {
			userStatusMap.put(s, s.getDescription());
		}
	}
}