package me.huqiao.wss.util.web;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
 * 导出excel页
 * @author NOVOTS
 * @version Version 1.0
 */
public class SheetInfo {
	    /**名称*/
		private String sheetName;
		/**列头*/
		private String [] headers;	
		/**列头样式*/
		private Integer [] headSytle;
		/**属性*/
		private String [] propertys;
		/**列宽*/
		private int [] colwidth;
		/**单元格类型*/
		private int [] cellTypes;
		/**尾行操作*/
		private int [] lastRowOper;
		private List data = new ArrayList();
		/**设置跨行*/
		private List<Region> regions;
		
		public final static int OPER_NO = 0;
		public final static int OPER_SUM = 1;
		public final static int OPER_COUNT = 2;
		/**无背景颜色*/
		public HSSFCellStyle headCellStyleWhite;		//无背景颜色
		public HSSFCellStyle headCellStyleGray;		//灰色背景
		/**淡橙色背景*/
		public HSSFCellStyle headCellStyleORANGE; 	//淡橙色背景
		/**淡粉色背景*/
		public HSSFCellStyle headCellStylePINK; 	//淡粉色背景
		/**淡黄色背景*/
		public HSSFCellStyle headCellStyleYELLOW; 	//淡黄色背景
		/**淡紫色背景*/
		public HSSFCellStyle headCellStyleORCHID; 	//淡紫色背景
		/**水绿色背景*/
		public HSSFCellStyle headCellStyleAQUA; 	//水绿色背景
		
		/**无背景颜色*/
		public static Integer CELL_STYLE_WHITE=1;		//无背景颜色
		/**淡橙色背景*/
		public static Integer CELL_STYLE_ORANGE=2; 	//淡橙色背景
		/**淡粉色背景*/
		public static Integer CELL_STYLE_PINK=3; 	//淡粉色背景
		/**淡黄色背景*/
		public static Integer CELL_STYLE_YELLOW=4; 	//淡黄色背景
		/**淡紫色背景*/
		public static Integer CELL_STYLE_ORCHID=5; 	//淡紫色背景
		/**水绿色背景*/
		public static Integer CELL_STYLE_AQUA=6; 	//水绿色背景
		
		private File tempImageFile;
		/**宽度*/
		private int width;
		/**高度*/
		private int height;
		/**
		 * @param wb 设置列头样式
		 */
		public void setHeadCellStype(HSSFWorkbook wb){
		
			 headCellStyleWhite = wb.createCellStyle();
			 headCellStyleORANGE = wb.createCellStyle(); 	//淡橙色背景
			 headCellStylePINK = wb.createCellStyle(); 	//淡粉色背景
			 headCellStyleYELLOW = wb.createCellStyle(); 	//淡黄色背景
			 headCellStyleORCHID = wb.createCellStyle(); 	//淡紫色背景
			 headCellStyleAQUA = wb.createCellStyle(); 	//水绿色背景
			 headCellStyleGray = wb.createCellStyle();
			 
				HSSFFont headCellFont = wb.createFont();

				headCellFont.setColor(HSSFFont.COLOR_NORMAL);
				// headCellFont.setFontHeight((short)260);
				headCellFont.setFontHeightInPoints((short) 10);

				headCellFont.setFontName(HSSFFont.FONT_ARIAL);
				headCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				headCellStyleWhite.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleWhite.setFont(headCellFont);
				headCellStyleWhite.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleWhite.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleWhite.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleWhite.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
			//	headCellStyleWhite.
				headCellStyleORANGE.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleORANGE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStyleORANGE.setFont(headCellFont);
				headCellStyleORANGE.setWrapText(true);
				headCellStyleORANGE.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStyleORANGE.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				headCellStyleORANGE.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORANGE.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORANGE.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORANGE.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
				headCellStylePINK.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStylePINK.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStylePINK.setFont(headCellFont);
				headCellStylePINK.setWrapText(true);
				headCellStylePINK.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStylePINK.setFillForegroundColor(HSSFColor.ROSE.index);
				headCellStylePINK.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStylePINK.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStylePINK.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStylePINK.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
				headCellStyleGray.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleGray.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStyleGray.setFont(headCellFont);
				headCellStyleGray.setWrapText(true);
				headCellStyleGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStyleGray.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				headCellStyleGray.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleGray.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleGray.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleGray.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
				headCellStyleYELLOW.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleYELLOW.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStyleYELLOW.setFont(headCellFont);
				headCellStyleYELLOW.setWrapText(true);
				headCellStyleYELLOW.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStyleYELLOW.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
				headCellStyleYELLOW.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleYELLOW.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleYELLOW.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleYELLOW.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
				headCellStyleORCHID.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleORCHID.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStyleORCHID.setFont(headCellFont);
				headCellStyleORCHID.setWrapText(true);
				headCellStyleORCHID.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStyleORCHID.setFillForegroundColor(HSSFColor.PINK.index);
				headCellStyleORCHID.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORCHID.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORCHID.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleORCHID.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
				
				headCellStyleAQUA.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headCellStyleAQUA.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headCellStyleAQUA.setFont(headCellFont);
				headCellStyleAQUA.setWrapText(true);
				headCellStyleAQUA.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headCellStyleAQUA.setFillForegroundColor(HSSFColor.AQUA.index);
				headCellStyleAQUA.setBorderBottom(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleAQUA.setBorderLeft(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleAQUA.setBorderRight(HSSFCellStyle.BORDER_THIN) ;
				headCellStyleAQUA.setBorderTop(HSSFCellStyle.BORDER_THIN) ;
		}
		/**
		 * <p>Title:SheetInfo构造方法 </p>
		 * <p>Description:无参构造方法 </p>
		 */
		public SheetInfo(){
			
		}
		/**
		 * <p>Title: SheetInfo构造方法</p>
		 * <p>Description:设置名称的构造方法</p>
		 * @param sheetName 设置sheet标题
		 */
		public SheetInfo(String sheetName){
			this.sheetName = sheetName;
		}
		/**
		 * @return String sheet名称
		 */
		public String getSheetName() {
			return sheetName;
		}
		/**
		 * @param sheetName 要设置sheet名称
		 */
		public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}
		/**
		 * @return  String[] 列标题
		 */
		public String[] getHeaders() {
			return headers;
		}
		/**
		 * @param headers 要设置的列标题
		 */
		public void setHeaders(String[] headers) {
			this.headers = headers;
		}
		/**
		 * @return  String[] 属性名列表
		 */
		public String[] getPropertys() {
			return propertys;
		}
		/**
		 * @param propertys 属性名列表
		 */
		public void setPropertys(String[] propertys) {
			this.propertys = propertys;
		}
		/**
		 * @return int[] 各列宽度
		 */
		public int[] getColwidth() {
			return colwidth;
		}
		/**
		 * @param colwidth 各列宽度
		 */
		public void setColwidth(int[] colwidth) {
			this.colwidth = colwidth;
		}
		/**
		 * @return int[] 单元格数据类型
		 */
		public int[] getCellTypes() {
			return cellTypes;
		}
		/**
		 * @param cellTypes 单元格数据类型
		 */
		public void setCellTypes(int[] cellTypes) {
			this.cellTypes = cellTypes;
		}
		/**
		 * @return List sheet数据
		 */
		public List getData() {
			return data;
		}
		/**
		 * @param data 要设置的sheet数据
		 */
		public void setData(List data) {
			this.data = data;
		}
		/**
		 * @return Integer[] 列表样式
		 */
		public Integer[] getHeadSytle() {
			return headSytle;
		}
		/**
		 * @param headSytle 要设置列表样式
		 */
		public void setHeadSytle(Integer[] headSytle) {
			this.headSytle = headSytle;
		}
		/**
		 * 设置单元格样式
		 * @param cellStyle 样式
		 * @return HSSFCellStyle 单元格样式
		 */
		public HSSFCellStyle getHeadSytleObject(Integer cellStyle) {
			HSSFCellStyle headSytle;
			switch (cellStyle) {
			case 2:
				headSytle=headCellStyleORANGE; 
				break;
			case 3:
				headSytle=headCellStylePINK; 
				break;
			case 4:
				headSytle=headCellStyleYELLOW; 
				break;
			case 5:
				headSytle=headCellStyleORCHID; 
				break;
			case 6:
				headSytle=headCellStyleAQUA; 
				break;

			default:
				headSytle=headCellStyleWhite; 
				break;
			}
			return headSytle;
		}
		/**
		 * @return int[] 尾行操作
		 */
		public int[] getLastRowOper() {
			return lastRowOper;
		}
		/**
		 * @param lastRowOper 尾行操作
		 */
		public void setLastRowOper(int[] lastRowOper) {
			this.lastRowOper = lastRowOper;
		}
		/**
		 * @param length 设置默认尾操作
		 * @param oper 操作样式
		 */
		public void setDefaultOper(int length,int oper){
			lastRowOper = new int[length];
			for (int i = 0; i < length; i++) {
				lastRowOper[i] = oper;
			}
		}
		/**
		 * @param style  设置默认头样式
		 * @param length 列宽度 
		 */
		public void setDefaultHeadSytle(int length,Integer style){
			headSytle = new Integer[length];
			for( int i=0;i<length;i++ ){
				headSytle[i] = style;
			}
		}
		/**
		 * 单元格数据类型
		 * @param length
		 * @param cellStyle
		 */
		public void setDefaultCell(int length,int cellStyle){
			cellTypes = new int[length];
			for( int i=0;i<length;i++ ){
				cellTypes[i] = cellStyle;
			}
		}
       /**
        * @return List<Region> 跨行
        */
		public List<Region> getRegions() {
			return regions;
		}

		/**
		 * @param regions 设置跨行
		 */
		public void setRegions(List<Region> regions) {
			this.regions = regions;
		}
       /**
        * @return File 图像文件模板
        */
		public File getTempImageFile() {
			return tempImageFile;
		}
		/**
		 * @param tempImageFile 要设置的图像文件模板
		 */
		public void setTempImageFile(File tempImageFile) {
			this.tempImageFile = tempImageFile;
		}
		/**
		 * @return int 宽度
		 */
		public int getWidth() {
			return width;
		}
		/**
		 * @param width 要设置的宽度
		 */
		public void setWidth(int width) {
			this.width = width;
		}
		/**
		 * @return int 高度
		 */
		public int getHeight() {
			return height;
		}
		/**
		 * @param height 要设置的高度
		 */
		public void setHeight(int height) {
			this.height = height;
		}
		
		public boolean changeBackground(int row){
			try{
				return backgroundChangeData!=null && backgroundChangeData.get(row)==1;
			}catch(Exception e){
				return false;
			}
		}
		
		private List<Integer> backgroundChangeData;
		public void setBackgroundChangeData(List<Integer> data){
			backgroundChangeData = data;
		}
		
		
}
