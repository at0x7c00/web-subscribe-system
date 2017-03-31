package me.huqiao.wss.chapter.dao;
import java.util.List;

import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 标签DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ITagDao extends IBaseDao<Tag> {
    /**
     * 标签查询记录数量
     * @param tag 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(Tag tag);
	/**
	 * 标签历史记录数量
     * @param tag 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(Tag tag,Page pageInfo);
    /**
     * 标签分页查询
     * @param tag 查询对象
     * @param pageInfo 分页查询对象
     * @return  List<Tag>  标签列表 
     */
    List<Tag> findListPage(Tag tag, Page pageInfo);
	/**
	 * 标签历史记录分页查询
     * @param tag 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<Tag>>  标签历史列表
	 */
    List<HistoryRecord<Tag>> findHistoryListPage(Tag tag, Page pageInfo);
	/**
     * 标签版本号查询
     * @param version 版本号
	 * @return Tag 标签历史记录
     */
	Tag findByVersion(Integer version);
	/**
	 * 添加标签查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param tag 查询对象
	 */
	public void queryCause(Criteria criteria, Tag tag);
	/**
	 * 标签关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<Tag> 标签列表
	 */
	List<Tag> findByKey(Page pageInfo,String queryKey);
	/**
	 * 标签关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多��标签
     * @param  ids id数组
	 * @return List<Tag>  标签列表
     */
	List<Tag> findById(Integer[] ids);
}