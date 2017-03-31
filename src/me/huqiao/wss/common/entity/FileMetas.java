package me.huqiao.wss.common.entity;

import java.util.List;
/**
 * 上传文件元素
 * @author NOVOTS
 * @version Version 1.0
 */
public class FileMetas {
    /**文件元素列表*/
    private List<FileMeta> files;
    /**
     * @return List<FileMeta> 文件元素列表
     */
    public List<FileMeta> getFiles() {
        return files;
    }
   /**
    * @param files 要设置的文件元素列表
    */
    public void setFiles(List<FileMeta> files) {
        this.files = files;
    }
    
    
}