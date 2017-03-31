package me.huqiao.wss.sys.entity.enumtype;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 是否<br>  Yes("是"),No("否");
 * @author NOVOTS
 * @version Version 1.0
 */
public enum YesNo{
    Yes("是"),No("否");
 /**  description 描述信息*/
    private String description;
    private YesNo(String description){this.description = description;}
    /**
	  *  获取描述信息
      * @return String 描述信息
	  */
    public String getDescription(){return description;}
    /**
	  * 枚举对象-描述信息 键值对
      * @return Map<YesNo,String> 枚举对象-描述信息 键值对
	  */
    public final static Map<YesNo,String> yesNoMap = new LinkedHashMap<YesNo,String>();
    static{
        for(YesNo s : YesNo.values()){
          yesNoMap.put(s,s.getDescription());  
        }
    }
}