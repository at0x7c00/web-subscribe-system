package me.huqiao.wss.common.entity;
/**
 * 文件元素 用于文件上传
 * @author NOVOTS
 * @version Version 1.0
 */
public class FileMeta {
    /**文件名称*/
    private String name;
    /**原始名称*/
    private String originalName;
    /**大小*/
    private String size;
    /**类型*/
    private String type;
    /**数据类型*/
    private String deleteType;
    /**访问路径*/
    private String url;
    /**删除路径*/
    private String deleteUrl;
    /**缩略图路径*/
    private String thumbnailUrl;
    /**
     * @return String 获取名称
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
     * @return String 原始名称
     */
    public String getOriginalName() {
        return originalName;
    }
    /**
     * @param originalName 要设置的原始名称
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    /**
     * @return String 大小
     */
    public String getSize() {
        return size;
    }
   /**
    * @param size 要设置的大小
    */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return String 类别
     */
    public String getType() {
        return type;
    }
    /**
     * @param type 要设置的类别
     */
    public void setType(String type) {
        this.type = type;
    }
   /**
    * @return String 要删除类别
    */
    public String getDeleteType() {
        return deleteType;
    }
    /**
     * @param deleteType 要设置的删除类别
     */
    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }
    /**
     * @return String 访问地址
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
     * @return String 要删除的访问路径
     */
    public String getDeleteUrl() {
        return deleteUrl;
    }
    /**
     * @param deleteUrl 要设置的删除路径
     */
    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }
    /**
     * @return String 缩略图的访问路径
     */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
    /**
     * @param thumbnailUrl 要设置的缩略图路径
     */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
    
}