package me.huqiao.wss.chapter.service;
import java.util.List;

import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;
/**
 * 采集结果Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IGatherResultService extends IBaseService<GatherResult> {
    /**
     * 采集结果分页查询
     * @param gatherResult 查询对象
     * @param pageInfo 分页查询对象
     * @return Page<GatherResult> 采集结果分页信息对象
     */
    public Page<GatherResult> getListPage(GatherResult gatherResult,Page pageInfo);
	/**
	  * 采集结果历史��录分页查询
	  * @param gatherResult 查询对象
	  * @param pageInfo 分页查询对象
	  * @return Page<HistoryRecord<GatherResult>> 采集结果历史分页信息对象
	  */
	public Page<HistoryRecord<GatherResult>> getHistoryListPage(GatherResult gatherResult,Page pageInfo);
	/**
	 * 采集结果版本号查询
	 * @param version 查询版本号
	 * @return GatherResult 采集结果历史记录
	 */
	public GatherResult findByVersion(Integer version);
	/**
	 * 采集结果关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<GatherResult> 采集结果分页信息对象
	 * 
	 */
	Page<GatherResult> queryByKey(String queryKey, Page<GatherResult> pageInfo);
	/**
	 * 查找多个采集结果
	 * @param ids id数组
	 * @return List<GatherResult> 采集结果列表
	 * 
	 */
	List<GatherResult> queryById(Integer[] ids);
	public boolean existed(GatherResult gr);
	
	public boolean gooded(String ip,GatherResult gr);
	public boolean baded(String ip,GatherResult gr);
	public Long good(String ip,GatherResult gr);
	public Long bad(String ip,GatherResult gr);
	
	public GatherResult nextToCheck(Integer afterId) ;
}