package me.huqiao.wss.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 文件操作工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class FileUtil {
	/**
	 * 获取文件扩展名
	 * @param originalFilename 初始文件名
	 * @return String 扩展名
	 */
	public static String getExtensionName(String originalFilename){
		if(originalFilename==null || originalFilename.trim().equals("")){
			return "";
		}
		return originalFilename.substring(originalFilename.lastIndexOf("."));
	}

	
	/**
	 * 保存文件到磁盘，如果该文件已经存在则将会被覆盖
	 * @param path 路径
	 * @param fileName 文件名
	 * @param data 数据
	 * @throws IOException 输入输出异常
	 */
	public static void saveFileOnDisk(String path,String fileName,byte[] data) throws IOException{
		File parentDir = new File(path);
		if(!parentDir.exists()){
			parentDir.mkdirs();
		}
		File file = new File(parentDir,fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		saveFileOnDisk(file,data);
	}
	
	/**
	 * 保存文件到磁盘，如果该文件已经存在则将会被覆盖
	 * @param path 路径
	 * @param fileName 文件名
	 * @param is 数据
	 * @throws IOException 输入输出异常
	 */
	public static void saveFileOnDisk(String path,String fileName,InputStream is) throws IOException{
		File parentDir = new File(path);
		if(!parentDir.exists()){
			parentDir.mkdirs();
		}
		File file = new File(parentDir,fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		saveFileOnDisk(file,is);
	}
	
	/**
	 * 保存文件到磁盘，如果该文件已经存在则将会被覆盖
	 * @param file 文件
	 * @param data 数据
	 * @throws IOException 输入输出异常
	 */
	public static void saveFileOnDisk(File file,byte[] data) throws IOException{
		FileOutputStream fos = new FileOutputStream(file,false);
		fos.write(data);
		fos.close();
	}
	
	/**
	 * 保存文件到磁盘，如果该文件已经存在则将会被覆盖
	 * @param file 文件
	 * @param is 数据
	 * @throws IOException 输入输出异常
	 */
	public static void saveFileOnDisk(File file,InputStream is) throws IOException{
		FileOutputStream fos = new FileOutputStream(file,false);
		byte[] buffer = new byte[1024*1024*10];
		int len;
		try{
			while((len=is.read(buffer))!=-1){
				fos.write(buffer,0,len);
			}
		}catch(Exception e){}finally{
			try{
				fos.close();
			}catch(Exception e){}finally{
				try{
					is.close();
				}catch(Exception e){}
			}
		}
	}
	
	/**
	 * 保存文件到磁盘，如果该文件已经存在则将会被覆盖
	 * @param file 文件
	 * @param data 数据
	 * @throws IOException 输入输出异常
	 */
	public static void saveFileOnDisk(File file,String data) throws IOException{
		saveFileOnDisk(file,data.getBytes());
	}
	
	
	/**
	 * 删除文件，可以是单个文件或文件夹 
	 * @param fileName 文件名
	 * @return boolean 操作是否成功
	 */
    public static boolean delete(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            return false;
        } else {
            if(file.isFile())
                return deleteFile(fileName);  
            else
                return deleteDirectory(fileName);
        }
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件  
     * @param dir 目录
     * @return boolean 操作结果
     */
    private static boolean deleteDirectory(String dir) {
        //如果dir不以文件分隔符结尾，自动添加文件分隔符
        if(!dir.endsWith(File.separator))  
            dir = dir+File.separator;
        File dirFile = new File(dir);
        //如果dir对应的文件不存在，或者不是一个目录，则退出    
        if(!dirFile.exists() || !dirFile.isDirectory()){ 
            return false;
        }
        boolean flag = true;     
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for(File file : files){
            //删除子文件
            if(file.isFile()){  
                flag = deleteFile(file.getAbsolutePath()); 
                if(!flag)
                    break;
            }
           //删除子目录
            else {
                flag = deleteDirectory(file.getAbsolutePath()); 
                if(!flag)
                    break;
            }
        }
        if(!flag)
            return false;
        //删除当前目录
        if(dirFile.delete())
            return true;
        else
            return false;
    }
   
    /**
     * 删除单个文件 
     * @param fileName 要删除文件名
     * @return boolean 删除结果
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            file.delete();
            return true; 
        } else {
            return false;
        }
    }
    
    /**
     * 根据文件路径判断文件是否为图片
     * @param filePath 文件路径
     * @return boolean 是否为图片
     */
    public static boolean isImage(String filePath){
    	boolean valid = true;
    	try {
    	    BufferedImage image=ImageIO.read(new File(filePath));
    	    if (image == null) {
    	        valid = false;
    	    }
    	} catch(IOException ex) {
    	    valid=false;
    	}
    	return valid;
    }
}
