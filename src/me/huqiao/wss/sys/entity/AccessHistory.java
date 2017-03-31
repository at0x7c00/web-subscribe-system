package me.huqiao.wss.sys.entity;

/**
 * 访问历史
 * @author NOVOTS
 * @version Version 1.0
 */
public class AccessHistory {
    /**名称*/
	private String name;
	/**访问路径*/
	private String url;
	/**导航标签ID*/
	private String navTabId;
	/**
	 * @return String 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return String 访问路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url 要设置的访问路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return String 导航标签ID
	 */
	public String getNavTabId() {
		return navTabId;
	}
	/**
	 * @param navTabId 要设置的导航标签ID
	 */
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
}
