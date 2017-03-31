package me.huqiao.wss.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.common.dao.ICommonFolderDao;
import me.huqiao.wss.common.entity.CommonFolder;
import me.huqiao.wss.common.service.ICommonFolderService;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;


/**
 * 文件夹Service实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class CommonFolderServiceImpl extends BaseServiceImpl<CommonFolder> implements ICommonFolderService {
    /**文件夹Dao*/
    @Resource
    private ICommonFolderDao commonFolderDao;
    
    @Override
    public Page<CommonFolder> getPage(CommonFolder commonFolder,Page pageInfo) {
        int countRecord = commonFolderDao.findRowCount(commonFolder).intValue();
        Page<CommonFolder>  page = new Page<CommonFolder>(pageInfo == null?0:pageInfo.getPageNum(),countRecord,pageInfo.getNumPerPage());
        List<CommonFolder> commonFolderList = commonFolderDao.findPage(commonFolder,page.getStartIndex(), page.getNumPerPage(),pageInfo.getOrderField(),pageInfo.getOrderDirection());
        page.setList(commonFolderList);
        page.setOrderDirection(pageInfo.getOrderDirection()==null?"asc":pageInfo.getOrderDirection());
        page.setOrderField(pageInfo.getOrderField()==null?"id":pageInfo.getOrderField());
        return page;
    }
}
