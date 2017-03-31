/** 用一句话描述该文件做什么
 * @author  zhanghya 
 * @version V1.0  
 */
package me.huqiao.wss.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**这里用一句话描述这个类的作用
 * @author NOVOTS
 * @version Version 1.0
 */
public class CopyUtil {
	public static void Copy(Object source, Object dest)throws Exception {  
        //获取属性  
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);  
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();  
          
        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);  
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();  
          
        try{  
            for(int i=0;i<sourceProperty.length;i++){  
                  
                for(int j=0;j<destProperty.length;j++){ 
                //	System.out.println(sourceProperty[i].getName());
                	if(sourceProperty[i].getName().equalsIgnoreCase("taskUrl"))break;
                   	if(sourceProperty[i].getName().equalsIgnoreCase("optasknums"))break;
                  	if(sourceProperty[i].getName().equalsIgnoreCase("outTimes"))break;
                	if(sourceProperty[i].getName().equalsIgnoreCase("task"))break;
                    if(sourceProperty[i].getName().equalsIgnoreCase("id"))break;
                    if(sourceProperty[i].getName().equalsIgnoreCase("ipList"))break;
                    if(sourceProperty[i].getName().equalsIgnoreCase("deployResult"))break;
                    if(sourceProperty[i].getName().equalsIgnoreCase("sortedTasks"))break;
                    if(sourceProperty[i].getName().equals(destProperty[j].getName())){  
                        //调用source的getter方法和dest的setter方法  
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));  
                        break;                    
                    }  
                }  
            }  
        }catch(Exception e){  
        	e.printStackTrace();
            throw new Exception("属性复制失败:"+e.getMessage());  
        }  
    }  

}
