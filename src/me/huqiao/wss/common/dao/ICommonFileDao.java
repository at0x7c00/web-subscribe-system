package me.huqiao.wss.common.dao;
import java.util.Date;
import java.util.List;

import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.util.web.Page;

/**
 * 文件DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICommonFileDao extends IBaseDao<CommonFile> {

    /**
     * 查询文件记录数
     * @param filee 查询对象
     * @return Long 记录数量
     */
    Long findRowCount(CommonFile filee);

  
    /**
     * 分页查询文件信息
     * @param filee 查询对象
     * @param first 开始值
     * @param maxNum 最大数量
     * @param orderField 排序字段
     * @param orderDirection 排序方向
     * @return List<CommonFile> 文件列表
     */
    List<CommonFile> findPage(CommonFile filee, int first, int maxNum,String orderField,String orderDirection);
    /**
	 * 文件关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<CommonFile> 工单列表
	 */
	List<CommonFile> findByKey(Page pageInfo,String queryKey);
	/**
	 * 文件关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);


	List<CommonFile> findNotInusedByCreateTime(Date before);
}
