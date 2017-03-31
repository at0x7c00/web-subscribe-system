package me.huqiao.wss.common.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.util.web.Page;
/**
 * 文件Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface ICommonFileService extends IBaseService<CommonFile> {

	/**
	 * 文件分页查询
	 * @param filee 查询对象
	 * @param pageInfo 分页信息对象
	 * @return  Page<CommonFile> 文件分页信息对象
	 */
    public Page<CommonFile> getPage(CommonFile filee,Page pageInfo);
    
    /**
     * 通用下载文件类
     * @param response HttpServletResponse对象
     * @param key 对象唯一标识
     * @return boolean 返回操作结果
     */
    public boolean downLoadFile(HttpServletResponse response,String key);
    /**
	 * 文件关键字查询
	 * @param  queryKey 关键字
	 * @param  pageInfo 分页查询对象
	 * @return Page<CommonFile> 文件分页信息对象
	 */
	Page<CommonFile> queryByKey(String queryKey, Page<CommonFile> pageInfo);
	
	public void deleteFileNotInUsed();
	public List<CommonFile> findNotInusedByCreateTime(Date before);
	
	public List<CommonFile> findAttachementFromContent(String content);
	
	public List<CommonFile> findDeleteAtts(List<CommonFile> oldAtts, List<CommonFile> newAtts);
    
}
