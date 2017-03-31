package me.huqiao.wss.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;
/**
 * 图片工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class ImageUtil {
	static List<String> fileSuffixSupported = new ArrayList<String>();
	static {
		fileSuffixSupported.add("png");
		fileSuffixSupported.add("gif");
		fileSuffixSupported.add("jpg");
		fileSuffixSupported.add("bmp");
	}
	
	/**
	 * 图片缩放  <br/>
	 * 若图片横比200小，高比300小，不变  <br/>
	 * 若图片横比200小，高比300大，高缩小到300，图片比例不变  <br/>
	 * 若图片横比200大，高比300小，横缩小到200，图片比例不变  <br/>
	 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300  <br/>
	 * @param input 输入
	 * @param output 输出
	 * @param width 宽度
	 * @param height 高度
	 * @throws Exception 抛出异常
	 */
	public static void zoom(String input,String output,int width,int height)throws Exception{
		Thumbnails.of(input).size(width,height).toFile(output);
	}
	
	/**
	 * 图片裁切
	 * @param x1  选择区域左上角的x坐标
	 * @param y1  选择区域左上角的y坐标
	 * @param width 选择区域的宽度
	 * @param height  选择区域的高度
	 * @param sourcePath 源图片路径
	 * @param descpath 裁切后图片的保存路径
	 */
	public static void cut(int x1, int y1, int width, int height, String sourcePath, String descpath,String fileSuffix)throws IOException,IIOException,IllegalArgumentException  {
		
		try{
			tryWithFileSuffix(x1, y1, width, height, descpath, fileSuffix, sourcePath);
		}catch(Exception e){
			int fileSuffixIndex = 0;
			boolean success = false;
			while(!success){
				try{
					tryWithFileSuffix(x1, y1, width, height, descpath, fileSuffixSupported.get(fileSuffixIndex++), sourcePath);
				}catch(IIOException e2){
					success = false;
					if(fileSuffixIndex==fileSuffixSupported.size()){
						throw e2;
					}
					continue;
				}catch(IllegalArgumentException e2){
					success = false;
					if(fileSuffixIndex==fileSuffixSupported.size()){
						throw e2;
					}
					continue;
				}catch(IOException e2){
					throw e2;
				}
				break;
			}
		}
	}
   /**
    * 带后缀的输出图像
    * @param x1 指定的x坐标
    * @param y1 指定的Y坐标
    * @param width 图像的款度
    * @param height 图像的高度
    * @param descpath 输出路径
    * @param fileSuffix 文件后缀
    * @param sourcePath 源文件路径
    * @throws IOException 输入输出异常
    * @throws IllegalArgumentException 错误语法异常
    */
	private static void tryWithFileSuffix(int x1, int y1, int width, int height, String descpath, String fileSuffix, String sourcePath) throws IOException,IllegalArgumentException {
		
		InputStream is = null;
		ImageInputStream iis = null;
		try{
			is = new FileInputStream(sourcePath);
			if(fileSuffix.startsWith(".")){
				fileSuffix = fileSuffix.substring(1);
			}
			
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			
			
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x1, y1, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, fileSuffix, new File(descpath));
		
		}catch(IOException e){
			throw e;
		}catch(IllegalArgumentException e){
			throw e;
		}finally{
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				iis = null;
			}
		}
		
	}
	
}
