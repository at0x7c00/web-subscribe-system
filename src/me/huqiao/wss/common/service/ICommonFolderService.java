package me.huqiao.wss.common.service;

import me.huqiao.wss.common.entity.CommonFolder;
import me.huqiao.wss.util.web.Page;

/**
 * 文件夹Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICommonFolderService extends IBaseService<CommonFolder> {

	/**
	 * 文件夹分页查询
	 * @param commonFolder 查询对象
	 * @param pageInfo 分页查询对象
	 * @return Page<CommonFolder> 分页信息对象
	 */
    public Page<CommonFolder> getPage(CommonFolder commonFolder,Page pageInfo);
    
}
