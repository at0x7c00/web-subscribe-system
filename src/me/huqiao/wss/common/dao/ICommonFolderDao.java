package me.huqiao.wss.common.dao;
import java.util.List;

import me.huqiao.wss.common.entity.CommonFolder;

/**
 * 文件夹Dao接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICommonFolderDao extends IBaseDao<CommonFolder> {

	/**
	 * 查询文件夹记录数
	 * @param commonFolder 查询对象
	 * @return Long 文件夹数量
	 */
    Long findRowCount(CommonFolder commonFolder);

   
    /**
     * 分页查询文件夹信息
     * @param commonFolder 查询对象
     * @param first 开始值
     * @param maxNum 最大数量
     * @param orderField 排序字段
     * @param orderDirection 排序方向
     * @return List<CommonFolder> 文件夹列表
     */
    List<CommonFolder> findPage(CommonFolder commonFolder, int first, int maxNum,String orderField,String orderDirection);
}
