package me.huqiao.wss.chapter.service.impl;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.chapter.dao.IGatherResultDao;
import me.huqiao.wss.chapter.entity.GatherResult;
import me.huqiao.wss.chapter.service.IGatherResultService;
import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.redis.service.IRedisService;
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
    @Resource
    private IRedisService redisService;
    @Override
    public Page<GatherResult> getListPage(GatherResult gatherResult,Page pageInfo) {
      	pageInfo.setTotalCount(gatherResultDao.findListRowCount(gatherResult).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "createTime": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "desc": pageInfo.getOrderDirection());
		List<GatherResult> list = gatherResultDao.findListPage(gatherResult,pageInfo);
		for(GatherResult gr : list){
			String scoreStr = redisService.get("chapter:" + gr.getId());
			Integer score = scoreStr == null || scoreStr.trim().equals("") ? 0 : Integer.parseInt(scoreStr);
			gr.setScore(score);
		}
		pageInfo.setList(list);
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
	@Override
	public boolean gooded(String ip, GatherResult gr) {
		return redisService.existed(getGoodKey(ip, gr));
	}
	private String getGoodKey(String ip, GatherResult gr) {
		return "good-chapter:" + gr.getId() +"-"+ ip;
	}
	@Override
	public boolean baded(String ip, GatherResult gr) {
		return redisService.existed(getBadKey(ip, gr));
	}
	@Override
	public Long good(String ip, GatherResult gr) {
		redisService.set(getGoodKey(ip, gr), "gooded");
		if(baded(ip, gr)){
			redisService.del(getBadKey(ip, gr));
			return redisService.incrementBy("chapter:" + gr.getId(),2);
		}else{
			return redisService.increment("chapter:" + gr.getId());
		}
	}
	private String getBadKey(String ip, GatherResult gr) {
		return "bad-chapter:" + gr.getId() +"-"+ ip;
	}
	@Override
	public Long bad(String ip, GatherResult gr) {
		redisService.set(getBadKey(ip, gr), "baded");
		if(gooded(ip,gr)){
			redisService.del(getGoodKey(ip, gr));
			return redisService.decrementBy("chapter:" + gr.getId(),2);
		}else{
			return redisService.decrement("chapter:" + gr.getId());
		}
	}
	@Override
	public GatherResult nextToCheck(Integer afterId) {
		return gatherResultDao.nextToCheck(afterId);
	}
}