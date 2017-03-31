package me.huqiao.wss.sys.entity.enumtype;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author NOVOTS
 * @version Version 1.0
 */
public enum RoleType{
    Normal("普通"),Advance("高级");
    /**描述*/
    private String description;
    private RoleType(String description){this.description = description;}
    /**
     * 获取描述
     * @return String 描述
     */
    public String getDescription(){return description;}
    public final static Map<RoleType,String> roleTypeMap = new LinkedHashMap<RoleType,String>();
    static{
        for(RoleType s : RoleType.values()){
          roleTypeMap.put(s,s.getDescription());  
        }
    }
}