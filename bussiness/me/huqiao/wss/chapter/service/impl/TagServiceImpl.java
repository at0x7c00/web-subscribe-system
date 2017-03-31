package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.ITagDao;
import me.huqiao.wss.chapter.entity.Tag;
import me.huqiao.wss.chapter.service.ITagService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 标签Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<Tag> implements ITagService {
    /**标签DAO对象*/
    @Resource
    private ITagDao tagDao;
    @Override
    public Page<Tag> getListPage(Tag tag,Page pageInfo) {
      	pageInfo.setTotalCount(tagDao.findListRowCount(tag).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(tagDao.findListPage(tag,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<Tag>> getHistoryListPage(Tag tag, Page pageInfo) {
		pageInfo.setTotalCount(tagDao.findHistoryListRowCount(tag,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(tagDao.findHistoryListPage(tag,pageInfo));
        return pageInfo;
	}
	@Override
	public Tag findByVersion(Integer version) {
		return tagDao.findByVersion(version);
	}
	@Override
	public Page<Tag> queryByKey(String queryKey, Page<Tag> pageInfo) {
		int countRecord = tagDao.findRowCount(queryKey).intValue();
		Page<Tag> page = new Page<Tag>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<Tag> tagList = tagDao.findByKey(pageInfo,queryKey);
		page.setList(tagList);
		return page;
	}
	@Override
	public List<Tag> queryById(Integer[] ids) {
		return tagDao.findById(ids);
	}
}