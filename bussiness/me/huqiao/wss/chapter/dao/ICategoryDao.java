package me.huqiao.wss.chapter.dao;
import java.util.List;

import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.hibernate.Criteria;
/**
 * 分类DAO接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICategoryDao extends IBaseDao<Category> {
    /**
     * 分类查询记录数量
     * @param category 查询对象
     * @return Long 记录数量
     */
	Long findListRowCount(Category category);
	/**
	 * 分类历史记录数量
     * @param category 查询对象
     * @param pageInfo 分页查询对象
	 * @return Long 历史记录数量
     */
	Long findHistoryListRowCount(Category category,Page pageInfo);
    /**
     * 分类分页查询
     * @param category 查询对象
     * @param pageInfo 分页查询对象
     * @return  List<Category>  分类列表 
     */
    List<Category> findListPage(Category category, Page pageInfo);
	/**
	 * 分类历史记录分页查询
     * @param category 查询对象
     * @param pageInfo 分页查询对象
     * @return List<HistoryRecord<Category>>  分类历史列表
	 */
    List<HistoryRecord<Category>> findHistoryListPage(Category category, Page pageInfo);
	/**
     * 分类版本号查询
     * @param version 版本号
	 * @return Category 分类历史记录
     */
	Category findByVersion(Integer version);
	/**
	 * 添加分类查询条件
	 * @param criteria Hibernate Criteria对象
	 * @param category 查询对象
	 */
	public void queryCause(Criteria criteria, Category category);
	/**
	 * 分类关键字查询
	 * @param  queryKey 查询关键字
	 * @return List<Category> 分类列表
	 */
	List<Category> findByKey(Page pageInfo,String queryKey);
	/**
	 * 分类关键字查询数量
	 * @param queryKey 查询关键字
	 * @return Long 记录数量
	 */
	Long findRowCount(String queryKey);
    /**
     * 查找多个分类
     * @param  ids id数组
	 * @return List<Category>  分类列表
     */
	List<Category> findById(Integer[] ids);
}