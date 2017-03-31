package me.huqiao.wss.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * jackson JSON处理工具类
 * @author huqiao
 */
public class JacksonUtil {

	private  static ObjectMapper objectMapper = null;
	
	static {
		objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.getSerializationConfig().setDateFormat(df);
		objectMapper.getDeserializationConfig().setDateFormat(df);
	}
	
	public static String object2Json(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
		return  objectMapper.writeValueAsString(obj);
	}
	
	public static Object json2Object(String json,Class type) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, type);
	}
}
