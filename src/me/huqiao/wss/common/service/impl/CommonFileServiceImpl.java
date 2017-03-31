package me.huqiao.wss.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import me.huqiao.wss.common.dao.ICommonFileDao;
import me.huqiao.wss.common.entity.CommonFile;
import me.huqiao.wss.common.entity.CommonFolder;
import me.huqiao.wss.common.entity.enumtype.UseStatus;
import me.huqiao.wss.common.service.ICommonFileService;
import me.huqiao.wss.util.DateUtil;
import me.huqiao.wss.util.web.Page;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *  文件Service实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class CommonFileServiceImpl extends BaseServiceImpl<CommonFile> implements ICommonFileService {
	
    /**定义Log日志*/
    private final Logger log = Logger.getLogger(CommonFileServiceImpl.class);
    /**文件dao*/
    @Resource
    private ICommonFileDao fileeDao;
    
    @Override
    public Page<CommonFile> getPage(CommonFile filee,Page pageInfo) {
        int countRecord = fileeDao.findRowCount(filee).intValue();
        Page<CommonFile>  page = new Page<CommonFile>(pageInfo == null?0:pageInfo.getPageNum(),countRecord,pageInfo.getNumPerPage());
        List<CommonFile> fileeList = fileeDao.findPage(filee,page.getStartIndex(), page.getNumPerPage(),pageInfo.getOrderField(),pageInfo.getOrderDirection());
        page.setList(fileeList);
        page.setOrderDirection(pageInfo.getOrderDirection()==null?"asc":pageInfo.getOrderDirection());
        page.setOrderField(pageInfo.getOrderField()==null?"id":pageInfo.getOrderField());
        return page;
    }
    /**
     * 导出文件记录
     * @param response HttpServletResponse对象
     * @param wb excel页
     * @param strFileName 文件名
     * @throws Exception 抛出异常
     */
    public static void exportExcelFile(HttpServletResponse response,
			HSSFWorkbook wb, String strFileName) throws Exception {
		try {
			ServletOutputStream out = response.getOutputStream();
			strFileName = URLEncoder.encode(strFileName,"UTF-8");

//			response.setContentType("application/attachment");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + strFileName);
			
			try {
				wb.write(out);
				out.close();
			} catch (Exception e) {
				//throw new MisException("用户取消文件导出！" + e.toString());
			}
			// log.info(misEx.getInfo());
		} catch (Exception e) {
			//log.error("文件导出时出错！", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean downLoadFile(HttpServletResponse response, String key) {
		List<CommonFile> fileeList = this.getByProperty(CommonFile.class, "manageKey", key);
		if(fileeList ==null || fileeList.size() == 0){
			log.error("文件未找到");
			return false;
		}else{
			CommonFile filee = fileeList.get(0);
			CommonFolder comFolder = filee.getFolder();
			String strFileFullPath = "";
			String strFileName = "";

			strFileFullPath = comFolder.getStorePath();

			if ("".equals(filee.getExtensionName())) {
				strFileName = filee.getManageKey();
			} else {
				strFileName = filee.getManageKey();
			}
			
			// 读出文件写入response 对象中
			File file1 = null;
			file1 = new File(strFileFullPath+File.separator+strFileName);
			
			try {
				ServletOutputStream out = response.getOutputStream();
				FileInputStream sIS = new FileInputStream(file1);
				int availInt = sIS.available();
				byte b[] = new byte[availInt];
				int readOut = sIS.read(b);
				strFileName = new String(strFileName.getBytes("GBK"), "ISO-8859-1");
				//response.setContentType("application/msexcel;charset=UTF-8");
				response.setContentType("application/attachment");
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filee.getName(),"UTF-8"));

				try {
					out.write(b);
					out.close();
				} catch (Exception e) {

				}
				sIS.close();
			} catch (Exception e) {
				log.error("文件下载时出错！" + e.toString());
			}
			return true;
		}
	}
	/* (非 Javadoc)
	 * <p>Title: queryByKey</p>
	 * <p>Description: </p>
	 * @param queryKey
	 * @param pageInfo
	 * @return
	 * @see me.huqiao.wss.common.service.ICommonFileService#queryByKey(java.lang.String, me.huqiao.wss.util.web.Page)
	 */
	@Override
	public Page<CommonFile> queryByKey(String queryKey, Page<CommonFile> pageInfo) {
		// TODO Auto-generated method stub
		int countRecord = fileeDao.findRowCount(queryKey).intValue();
		Page<CommonFile> page = new Page<CommonFile>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<CommonFile> commonFiles = fileeDao.findByKey(pageInfo,queryKey);
		page.setList(commonFiles);
		
		return page;
	}
	
	public void delete(CommonFile file){
		new File(file.getFullName()).delete();
		fileeDao.delete(file);
	}
	
	@Scheduled(cron = "${cron.filee.two.o-clock.at.middle.night}")
	public void deleteFileNotInUsed() {
		Date before = null;
		Date now = new Date();
		before = DateUtil.datePlus(now, Calendar.DAY_OF_MONTH, -1);
		before = DateUtil.getBeginDateOfDate(before);
		List<CommonFile> filesNotInused = findNotInusedByCreateTime(before);
		log.info("now="  + DateUtil.formatDate(now,"yyyy-MM-dd HH:mm:ss")) ;
		log.info("Delete file not inuse before:" + DateUtil.formatDate(before,"yyyy-MM-dd HH:mm:ss"));
		log.info((filesNotInused==null ? 0 : filesNotInused.size()) + " files has found.");
		String x = "";
		for(CommonFile file : filesNotInused){
			try{
				x+=file.getId() + ",";
				System.out.println(file.getInuse());
				delete(file);
			}catch(Exception e){
				file.setInuse(UseStatus.InUse);
				update(file);
				continue;
			}
			File diskFile = new File(file.getFullName());
			if(diskFile.exists()){
				diskFile.delete();
				log.info("Delete file not inuse:" + file.getFullName() );
			}
		}
		System.out.println(x);
	}

	@Override
	public List<CommonFile> findNotInusedByCreateTime(Date before) {
		return fileeDao.findNotInusedByCreateTime(before);
	}
	
	
	
	public List<CommonFile> findAttachementFromContent(String content){
		List<CommonFile> atts = new ArrayList<CommonFile>();
		if (content!=null && !("").equals(content)) {
			List<String> attFileIds = parseImageFilee(content);
			for(String attFileId : attFileIds){
				CommonFile filee = getEntityByProperty(CommonFile.class, "manageKey", attFileId);
				if(filee!=null){
					atts.add(filee);
				}
			}
		}
		return atts;
	}
	
	private List<String> parseImageFilee(String content){
		List<String> res = new ArrayList<String>();
		Document doc = Jsoup.parse(content);
		Elements eles = doc.select("img");
		for(Element ele : eles){
			String src = ele.attr("src");
			if(src.contains("/filee/viewPic.do") || src.contains("/filee/downloadFile.do")){
				if(!src.contains("=")){
					continue;
				}
				src = src.split("=")[1];
				res.add(src);
			}
		}
		return res;
	}
	
	public List<CommonFile> findDeleteAtts(List<CommonFile> oldAtts, List<CommonFile> newAtts) {
		List<CommonFile> res = new ArrayList<CommonFile>();
		for(CommonFile oldAtt : oldAtts){
			boolean find = false;
			for(CommonFile newAtt : newAtts){
				if(oldAtt.getManageKey().equals(newAtt.getManageKey())){
					find = true;
					break;
				}
			}
			if(!find){
				res.add(oldAtt);
			}
		}
		return res;
	}
	
	
}