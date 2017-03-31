package me.huqiao.wss.chapter.service;
import java.util.List;

import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;
/**
 * 标签Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ITagService extends IBaseService<Tag> {
    /**
     * 标签分页查询
     * @param tag 查询对象
     * @param pageInfo 分页查询对象
     * @return Page<Tag> 标签分页信息对象
     */
    public Page<Tag> getListPage(Tag tag,Page pageInfo);
	/**
	  * 标签历史记录分页查询
	  * @param tag 查询对象
	  * @param pageInfo 分页查询对象
	  * @return Page<HistoryRecord<Tag>> 标签历史分页信息对象
	  */
	public Page<HistoryRecord<Tag>> getHistoryListPage(Tag tag,Page pageInfo);
	/**
	 * 标签版本号查询
	 * @param version 查询版本号
	 * @return Tag 标签历史记录
	 */
	public Tag findByVersion(Integer version);
	/**
	 * 标签关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<Tag> 标签分页信息对象
	 * 
	 */
	Page<Tag> queryByKey(String queryKey, Page<Tag> pageInfo);
	/**
	 * 查找多个标签
	 * @param ids id数组
	 * @return List<Tag> 标签列表
	 * 
	 */
	List<Tag> queryById(Integer[] ids);
}