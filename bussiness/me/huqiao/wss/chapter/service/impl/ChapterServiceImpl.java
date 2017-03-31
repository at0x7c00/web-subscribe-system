package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.IChapterDao;
import me.huqiao.wss.chapter.entity.Chapter;
import me.huqiao.wss.chapter.service.IChapterService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 文章Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class ChapterServiceImpl extends BaseServiceImpl<Chapter> implements IChapterService {
    /**文章DAO对象*/
    @Resource
    private IChapterDao chapterDao;
    @Override
    public Page<Chapter> getListPage(Chapter chapter,Page pageInfo) {
      	pageInfo.setTotalCount(chapterDao.findListRowCount(chapter).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(chapterDao.findListPage(chapter,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<Chapter>> getHistoryListPage(Chapter chapter, Page pageInfo) {
		pageInfo.setTotalCount(chapterDao.findHistoryListRowCount(chapter,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(chapterDao.findHistoryListPage(chapter,pageInfo));
        return pageInfo;
	}
	@Override
	public Chapter findByVersion(Integer version) {
		return chapterDao.findByVersion(version);
	}
	@Override
	public Page<Chapter> queryByKey(String queryKey, Page<Chapter> pageInfo) {
		int countRecord = chapterDao.findRowCount(queryKey).intValue();
		Page<Chapter> page = new Page<Chapter>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<Chapter> chapterList = chapterDao.findByKey(pageInfo,queryKey);
		page.setList(chapterList);
		return page;
	}
	@Override
	public List<Chapter> queryById(Integer[] ids) {
		return chapterDao.findById(ids);
	}
}