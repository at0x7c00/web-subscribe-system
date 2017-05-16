package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.IGatherResultDao;
import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 采集结果Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class GatherResultServiceImpl extends BaseServiceImpl<GatherResult> implements IGatherResultService {
    /**采集结果DAO对象*/
    @Resource
    private IGatherResultDao gatherResultDao;
    @Override
    public Page<GatherResult> getListPage(GatherResult gatherResult,Page pageInfo) {
      	pageInfo.setTotalCount(gatherResultDao.findListRowCount(gatherResult).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "createTime": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "desc": pageInfo.getOrderDirection());
		pageInfo.setList(gatherResultDao.findListPage(gatherResult,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<GatherResult>> getHistoryListPage(GatherResult gatherResult, Page pageInfo) {
		pageInfo.setTotalCount(gatherResultDao.findHistoryListRowCount(gatherResult,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "createTime": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "desc": pageInfo.getOrderDirection());
		pageInfo.setList(gatherResultDao.findHistoryListPage(gatherResult,pageInfo));
        return pageInfo;
	}
	@Override
	public GatherResult findByVersion(Integer version) {
		return gatherResultDao.findByVersion(version);
	}
	@Override
	public Page<GatherResult> queryByKey(String queryKey, Page<GatherResult> pageInfo) {
		int countRecord = gatherResultDao.findRowCount(queryKey).intValue();
		Page<GatherResult> page = new Page<GatherResult>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<GatherResult> gatherResultList = gatherResultDao.findByKey(pageInfo,queryKey);
		page.setList(gatherResultList);
		return page;
	}
	@Override
	public List<GatherResult> queryById(Integer[] ids) {
		return gatherResultDao.findById(ids);
	}
	
	@Override
	public boolean existed(GatherResult gr) {
		return gatherResultDao.existed(gr);
	}
}