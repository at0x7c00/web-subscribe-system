package me.huqiao.wss.sys.entity.enumtype;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 性别  <br/>M("男"),F("女"),None("未知")
 * @author NOVOTS
 * @version Version 1.0
 */
public enum Sex{
    M("男"),F("女"),None("未知");
    /**描述*/
    private String description;
    private Sex(String description){this.description = description;}
    /**
     * 获取描述
     * @return String 描述
     */
    public String getDescription(){return description;}
    public final static Map<Sex,String> sexMap = new LinkedHashMap<Sex,String>();
    static{
        for(Sex s : Sex.values()){
          sexMap.put(s,s.getDescription());  
        }
    }
}