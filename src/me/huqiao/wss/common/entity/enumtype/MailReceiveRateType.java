package me.huqiao.wss.common.entity.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 邮件接收频率 <br> Never("不发送"),RealTime("实时"),EveryDay("每天"),Weekly("每周")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum MailReceiveRateType {
	Never("不发送"),RealTime("实时"),EveryDay("每天"),Weekly("每周");
	/**描述*/
	private String description;

	private MailReceiveRateType(String description) {
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
	public final static Map<MailReceiveRateType, String> mailReceiveRateTypeMap = new LinkedHashMap<MailReceiveRateType, String>();
	static {
		for (MailReceiveRateType s : MailReceiveRateType.values()) {
			mailReceiveRateTypeMap.put(s, s.getDescription());
		}
	}

}
