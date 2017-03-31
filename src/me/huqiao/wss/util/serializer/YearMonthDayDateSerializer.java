package me.huqiao.wss.util.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.huqiao.wss.util.DateUtil;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * 将日期类型转换成json格式
 * @author NOVOTS
 * @version Version 1.0
 */
public final class YearMonthDayDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jgen,SerializerProvider provider) throws IOException,JsonProcessingException {
		  SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.EN_YEAR_MONTH_DAY);
	      jgen.writeString(sdf.format(date));
	}
}
