package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.ICategoryDao;
import me.huqiao.wss.chapter.entity.Category;
import me.huqiao.wss.chapter.service.ICategoryService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 分类Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements ICategoryService {
    /**分类DAO对象*/
    @Resource
    private ICategoryDao categoryDao;
    @Override
    public Page<Category> getListPage(Category category,Page pageInfo) {
      	pageInfo.setTotalCount(categoryDao.findListRowCount(category).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(categoryDao.findListPage(category,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<Category>> getHistoryListPage(Category category, Page pageInfo) {
		pageInfo.setTotalCount(categoryDao.findHistoryListRowCount(category,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(categoryDao.findHistoryListPage(category,pageInfo));
        return pageInfo;
	}
	@Override
	public Category findByVersion(Integer version) {
		return categoryDao.findByVersion(version);
	}
	@Override
	public Page<Category> queryByKey(String queryKey, Page<Category> pageInfo) {
		int countRecord = categoryDao.findRowCount(queryKey).intValue();
		Page<Category> page = new Page<Category>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<Category> categoryList = categoryDao.findByKey(pageInfo,queryKey);
		page.setList(categoryList);
		return page;
	}
	@Override
	public List<Category> queryById(Integer[] ids) {
		return categoryDao.findById(ids);
	}
}