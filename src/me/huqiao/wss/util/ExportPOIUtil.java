package me.huqiao.wss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import me.huqiao.wss.common.service.impl.CommonFileServiceImpl;
import me.huqiao.wss.util.web.SheetInfo;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 导出工具类
 * @author NOVOTS
 * @version Version 1.0
 */
//public class ExportPOIUtil extends AbstractExcelView{
public class ExportPOIUtil {
	private static final Logger log = Logger.getLogger(ExportPOIUtil.class);
    /**
     * 导出到Excel
     * @param response HttpServletResponse对象
     * @param sheetList excel页列表
     * @param exportName 导出名称
     */
	public static void exportExcel(HttpServletResponse response,List<SheetInfo> sheetList,String exportName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		Iterator it = sheetList.iterator();
		while( it.hasNext() ){
			SheetInfo sheetInfo = (SheetInfo)it.next();
			sheetInfo.setHeadCellStype(wb);
			
			int iNo = 0;
			HSSFSheet sheet = wb.createSheet(sheetInfo.getSheetName());
			
			int index = wb.getSheetIndex(sheetInfo.getSheetName());
			wb.setSheetName(index, sheetInfo.getSheetName());
			for (int i = 0; i < sheetInfo.getColwidth().length; ++i) {
				sheet.setColumnWidth((short) i, (short) (256 *sheetInfo.getColwidth()[i]));
			}
			
			if( iNo == 0 ){
				HSSFRow headRow = sheet.createRow((short) iNo);
				HSSFCell headCell = null;
		
				// 设置单元格显示风格
				for (int i = 0; i <  sheetInfo.getHeaders().length; ++i) {
					headCell = headRow.createCell((short) i);
					headCell.setCellType(HSSFCell.CELL_TYPE_STRING);
					headCell.setCellValue(sheetInfo.getHeaders()[i]);
					if( sheetInfo.getHeadSytle()[i] != null ){
						headCell.setCellStyle(sheetInfo.getHeadSytleObject(sheetInfo.getHeadSytle()[i]));
					}
				}
			}
	
	        //设置行记录单元格显示风格
	        HSSFCellStyle dataCellStyleForCenterString = wb.createCellStyle();
	        HSSFCellStyle dataCellStyleForLeftString = wb.createCellStyle();
	        HSSFCellStyle dataCellStyleForMergeCellCenterString = wb.createCellStyle();
	        HSSFCellStyle dataCellStyleForMergeCellLeftString = wb.createCellStyle();
	        HSSFFont dataCellFont = wb.createFont();

	        dataCellFont.setColor( HSSFFont.COLOR_NORMAL );
	        dataCellFont.setFontHeightInPoints((short)10);
	        dataCellFont.setFontName( HSSFFont.FONT_ARIAL );
	        dataCellFont.setBoldweight( HSSFFont.BOLDWEIGHT_NORMAL );

	        dataCellStyleForCenterString.setAlignment( HSSFCellStyle.ALIGN_CENTER );
	        dataCellStyleForCenterString.setFont( dataCellFont );

	        dataCellStyleForLeftString.setAlignment( HSSFCellStyle.ALIGN_LEFT );
	        dataCellStyleForLeftString.setFont( dataCellFont );
	        dataCellStyleForLeftString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
	        
	        CreationHelper createHelper = wb.getCreationHelper();
	        CellStyle dateTypeCellStyle = wb.createCellStyle();
	        dateTypeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d"));
	        dateTypeCellStyle.setAlignment( HSSFCellStyle.ALIGN_CENTER );
	        dateTypeCellStyle.setFont( dataCellFont );
	        
	        CellStyle dateTypeCellStyle2 = wb.createCellStyle();
	        dateTypeCellStyle2.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d"));
	        dateTypeCellStyle2.setAlignment( HSSFCellStyle.ALIGN_CENTER );
	        dateTypeCellStyle2.setFont( dataCellFont);
	        dateTypeCellStyle2.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
	        
	        dataCellStyleForMergeCellCenterString.setAlignment( HSSFCellStyle.ALIGN_CENTER );
	        dataCellStyleForMergeCellCenterString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
	        dataCellStyleForMergeCellCenterString.setFont( dataCellFont );

	        dataCellStyleForMergeCellLeftString.setAlignment( HSSFCellStyle.ALIGN_LEFT );
	        dataCellStyleForMergeCellLeftString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
	        dataCellStyleForMergeCellLeftString.setFont( dataCellFont );
	        
	        
	        HSSFCellStyle cellStyleBackup = sheetInfo.headCellStyleWhite;
	        
			Iterator iter = sheetInfo.getData().iterator();
			while (iter.hasNext()) {
				Map map = (Map)iter.next();
	
				HSSFRow row = sheet.createRow((short) (++iNo));
				
				HSSFCell cell = null;
				for (int i = 0; i < sheetInfo.getPropertys().length; ++i) {
					try {
						Object value =	map.get(sheetInfo.getPropertys()[i]);
						String sValue = "";
						if( value != null ){
							sValue = value.toString();
						}
						
						cell = row.createCell((short) i);
						if( sheetInfo.getCellTypes()[i] == HSSFCell.CELL_TYPE_NUMERIC ){
							cell.setCellType(sheetInfo.getCellTypes()[i]);
							if( "".equals(sValue) ){
								sValue = "0";
							}
							cell.setCellValue(Double.parseDouble(sValue));
							cell.setCellStyle(dataCellStyleForCenterString);
						}else if( sheetInfo.getCellTypes()[i] == HSSFCell.CELL_TYPE_STRING ){
							cell.setCellType(sheetInfo.getCellTypes()[i]);
							cell.setCellValue(sValue);
							cell.setCellStyle(dataCellStyleForCenterString);
						}else{
							if(value!=null){
								cell.setCellValue((Date)value);
								cell.setCellStyle(dateTypeCellStyle);
							}
						}
						cell.setCellStyle(cellStyleBackup);
					} catch (Exception e) {
						//System.out.println(sheetInfo.getCellTypes()[i]+","+sheetInfo.getSheetName());
						//e.printStackTrace();
						log.error("导出错误:" + sheetInfo.getHeaders()[i]);
					}
				}
				if(sheetInfo.changeBackground(iNo)){
					if(cellStyleBackup==sheetInfo.headCellStyleGray){
						cellStyleBackup = sheetInfo.headCellStyleWhite;
					}else{
						cellStyleBackup = sheetInfo.headCellStyleGray;
					}
				}
			}
			//合并单元格
			if(sheetInfo.getRegions()!=null){
				for(Region region : sheetInfo.getRegions()){
					sheet.addMergedRegion(region);
				}
			}
		}

		// 生成写入response 输出流
		String strFileName = exportName + ".xls";
		try {
			CommonFileServiceImpl.exportExcelFile(response, wb, strFileName);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 导出到excel同一页
	 * @param response HttpServletResponse对象
	 * @param sheetList excel页列表
	 * @param exportName 导出名
	 */
	public static void exportExcelToOneSheet(HttpServletResponse response,List<SheetInfo> sheetList,String exportName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		Iterator it = sheetList.iterator();
		
		HSSFSheet sheet = wb.createSheet(exportName);
		int rowIndex = 0;
		int colCount = 0;
		SheetInfo sheetInfo = null; 
		while( it.hasNext() ){
			sheetInfo = (SheetInfo)it.next();
			sheetInfo.setHeadCellStype(wb);
			
			
			wb.setSheetName(0,exportName);
			for (; colCount < sheetInfo.getColwidth().length; ++colCount) {
				sheet.setColumnWidth((short) colCount, (short) (256 *sheetInfo.getColwidth()[colCount]));
			}
			
			HSSFRow titleRow = sheet.createRow((short) rowIndex);
			HSSFCell title = titleRow.createCell(0);
			HSSFCellStyle titleStyle = wb.createCellStyle();
			HSSFFont titleFont = wb.createFont();
			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			titleStyle.setFont(titleFont);
			title.setCellValue(sheetInfo.getSheetName());
			title.setCellStyle(titleStyle);
			sheet.addMergedRegion(new Region(rowIndex,(short)0,rowIndex,(short)(sheetInfo.getColwidth().length-1)));
			
			HSSFRow headRow = sheet.createRow((short) ++rowIndex);
			HSSFCell headCell = null;
			
			// 设置单元格显示风格
			for (int i = 0; i <  sheetInfo.getHeaders().length; ++i) {
				headCell = headRow.createCell((short) i);
				headCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				headCell.setCellValue(sheetInfo.getHeaders()[i]);
				if( sheetInfo.getHeadSytle()[i] != null ){
					headCell.setCellStyle(sheetInfo.getHeadSytleObject(sheetInfo.getHeadSytle()[i]));
				}
			}
			
			//设置行记录单元格显示风格
			HSSFCellStyle dataCellStyleForCenterString = wb.createCellStyle();
			HSSFCellStyle dataCellStyleForLeftString = wb.createCellStyle();
			HSSFCellStyle dataCellStyleForMergeCellCenterString = wb.createCellStyle();
			HSSFCellStyle dataCellStyleForMergeCellLeftString = wb.createCellStyle();
			HSSFFont dataCellFont = wb.createFont();
			
			dataCellFont.setColor( HSSFFont.COLOR_NORMAL );
			dataCellFont.setFontHeightInPoints((short)10);
			dataCellFont.setFontName( HSSFFont.FONT_ARIAL );
			dataCellFont.setBoldweight( HSSFFont.BOLDWEIGHT_NORMAL );
			
			dataCellStyleForCenterString.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			dataCellStyleForCenterString.setFont( dataCellFont );
			
			dataCellStyleForLeftString.setAlignment( HSSFCellStyle.ALIGN_LEFT );
			dataCellStyleForLeftString.setFont( dataCellFont );
			// added by FS, 20080722
			dataCellStyleForLeftString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
			// end added by FS, 20080722
			
			dataCellStyleForMergeCellCenterString.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			dataCellStyleForMergeCellCenterString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
			dataCellStyleForMergeCellCenterString.setFont( dataCellFont );
			
			dataCellStyleForMergeCellLeftString.setAlignment( HSSFCellStyle.ALIGN_LEFT );
			dataCellStyleForMergeCellLeftString.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
			dataCellStyleForMergeCellLeftString.setFont( dataCellFont );
			
			Iterator iter = sheetInfo.getData().iterator();
			while (iter.hasNext()) {
				Map map = (Map)iter.next();
				
				HSSFRow row = sheet.createRow((short) (++rowIndex));
				
				HSSFCell cell = null;
				for (int i = 0; i < sheetInfo.getPropertys().length; ++i) {
					try {
						
						Object value =	map.get(sheetInfo.getPropertys()[i]);
						String sValue = "";
						if( value != null ){
							sValue = value.toString();
						}
						
						cell = row.createCell((short) i);
						cell.setCellType(sheetInfo.getCellTypes()[i]);
						if( sheetInfo.getCellTypes()[i] == HSSFCell.CELL_TYPE_NUMERIC ){
							if( "".equals(sValue) ){
								sValue = "0";
							}
							cell.setCellValue(Double.parseDouble(sValue));
						} 
						if( sheetInfo.getCellTypes()[i] == HSSFCell.CELL_TYPE_STRING ){
							cell.setCellValue(sValue);
						}
						cell.setCellStyle(dataCellStyleForCenterString);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("导出错误:" + sheetInfo.getHeaders()[i]);
					}
				}
			}
			//合并单元格
			if(sheetInfo.getRegions()!=null){
				for(Region region : sheetInfo.getRegions()){
					sheet.addMergedRegion(new Region(region.getRowFrom()+rowIndex,region.getColumnFrom(),region.getRowTo()+rowIndex,region.getColumnTo()));
				}
			}
			
			rowIndex+=2;
			if(sheetInfo.getTempImageFile()!=null){
				try {
					exportImageToExcel(wb,sheet,sheetInfo.getTempImageFile(),rowIndex,0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rowIndex+=(sheetInfo.getHeight()/18)+2;
			}
		}
		
		// 生成写入response 输出流
		String strFileName = exportName + ".xls";
		try {
			CommonFileServiceImpl.exportExcelFile(response, wb, strFileName);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		//删除临时文件
		for(SheetInfo si : sheetList){
			if(si.getTempImageFile()!=null){
				si.getTempImageFile().delete();
			}
		}
		
	}
	/**
	 * 导出图片到Excel中
	 * @param wb HSSFWorkbook对象
	 * @param sheet excel页
	 * @param tempFile 要导出的图片文件
	 * @param row 行数
	 * @param col 列数
	 * @throws Exception 抛出异常
	 */
	private static void  exportImageToExcel(HSSFWorkbook wb,HSSFSheet sheet,File tempFile,int row,int col)throws Exception{
	    InputStream is = new FileInputStream(tempFile);
	    byte[] bytes = IOUtils.toByteArray(is);
	    int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	    is.close();
	    CreationHelper helper = wb.getCreationHelper();
	    Drawing drawing = sheet.createDrawingPatriarch();
	    ClientAnchor anchor = helper.createClientAnchor();
	    anchor.setCol1(col);
	    anchor.setRow1(row);
	    Picture pict = drawing.createPicture(anchor, pictureIdx);
	    pict.resize();
	}


}
