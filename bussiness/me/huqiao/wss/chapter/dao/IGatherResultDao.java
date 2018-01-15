package me.huqiao.wss.chapter.dao;
import java.util.List;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 采集结果DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IGatherResultDao extends IBaseDao<GatherResult> {
    /**
     * 采集结果查询记录数量
     * @param gatherResult 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(GatherResult gatherResult);
	/**
	 * 采集结果历史记录数量
     * @param gatherResult 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(GatherResult gatherResult,Page pageInfo);
    /**
     * 采集结果���页查询
     * @param gatherResult 查询对象
     * @param pageInfo 分页查询对象
     * @return  List<GatherResult>  采集结果列表 
     */
    List<GatherResult> findListPage(GatherResult gatherResult, Page pageInfo);
	/**
	 * 采集结果历史记录分页查询
     * @param gatherResult 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<GatherResult>>  采集结果历史列表
	 */
    List<HistoryRecord<GatherResult>> findHistoryListPage(GatherResult gatherResult, Page pageInfo);
	/**
     * 采集结果版本号查询
     * @param version 版本号
	 * @return GatherResult 采集结果历史记录
     */
	GatherResult findByVersion(Integer version);
	/**
	 * 添加采集结果查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param gatherResult 查询对象
	 */
	public void queryCause(Criteria criteria, GatherResult gatherResult);
	/**
	 * 采集结果关键字查询
	 * @param  queryKey 查询关键��
	 * @return List<GatherResult> 采集结果列表
	 */
	List<GatherResult> findByKey(Page pageInfo,String queryKey);
	/**
	 * 采集结果关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多个采集结果
     * @param  ids id数组
	 * @return List<GatherResult>  采集结果列表
     */
	List<GatherResult> findById(Integer[] ids);
	
	boolean existed(GatherResult gr);
	
	GatherResult nextToCheck(Integer afterId);
}