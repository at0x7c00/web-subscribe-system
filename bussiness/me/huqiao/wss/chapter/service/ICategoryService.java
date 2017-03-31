package me.huqiao.wss.chapter.service;
import java.util.List;

import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;
/**
 * 分类Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICategoryService extends IBaseService<Category> {
    /**
     * 分类分页查询
     * @param category 查询对象
     * @param pageInfo 分页查询对象
     * @return Page<Category> 分类分页信息对象
     */
    public Page<Category> getListPage(Category category,Page pageInfo);
	/**
	  * 分类历史记录分页查询
	  * @param category 查询对象
	  * @param pageInfo 分页查询对象
	  * @return Page<HistoryRecord<Category>> 分类历史分页信息对象
	  */
	public Page<HistoryRecord<Category>> getHistoryListPage(Category category,Page pageInfo);
	/**
	 * 分类版本号查询
	 * @param version 查询版本号
	 * @return Category 分类历史记录
	 */
	public Category findByVersion(Integer version);
	/**
	 * 分类关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<Category> 分类分页信息对象
	 * 
	 */
	Page<Category> queryByKey(String queryKey, Page<Category> pageInfo);
	/**
	 * 查找多个分类
	 * @param ids id数组
	 * @return List<Category> 分类列表
	 * 
	 */
	List<Category> queryById(Integer[] ids);
}