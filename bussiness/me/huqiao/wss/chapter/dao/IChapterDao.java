package me.huqiao.wss.chapter.dao;
import java.util.List;

import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 文章DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IChapterDao extends IBaseDao<Chapter> {
    /**
     * 文章查询记录数量
     * @param chapter 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(Chapter chapter);
	/**
	 * 文章历史记录数量
     * @param chapter 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(Chapter chapter,Page pageInfo);
    /**
     * 文章分页查询
     * @param chapter 查询对象
     * @param pageInfo 分页查询对象
     * @return  List<Chapter>  文章列表 
     */
    List<Chapter> findListPage(Chapter chapter, Page pageInfo);
	/**
	 * 文章历史记录分页查询
     * @param chapter 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<Chapter>>  文章历史列表
	 */
    List<HistoryRecord<Chapter>> findHistoryListPage(Chapter chapter, Page pageInfo);
	/**
     * 文章版本号查询
     * @param version 版本号
	 * @return Chapter 文章历史记录
     */
	Chapter findByVersion(Integer version);
	/**
	 * 添加文章查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param chapter 查询对象
	 */
	public void queryCause(Criteria criteria, Chapter chapter);
	/**
	 * 文章关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<Chapter> 文章列表
	 */
	List<Chapter> findByKey(Page pageInfo,String queryKey);
	/**
	 * 文章关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多个文章
     * @param  ids id数组
	 * @return List<Chapter>  文章列表
     */
	List<Chapter> findById(Integer[] ids);
}