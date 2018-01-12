package me.huqiao.wss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 日期工具类
 * @author NOVOTS
 * @version Version 1.0
 */

public class DateUtil {
	
    /**yyyy-MM-dd HH:mm:ss*/
    public static final String EN_YEAR_MONTH_DAY_HOUR_MIN_SEC  = "yyyy-MM-dd HH:mm:ss";
    /**yyyy-MM-dd HH:mm*/
    public static final String EN_YEAR_MONTH_DAY_HOUR_MIN  = "yyyy-MM-dd HH:mm";
    /** yyyy-MM-dd HH*/
    public static final String EN_YEAR_MONTH_DAY_HOUR  = "yyyy-MM-dd HH";
    /**yyyy-MM-dd*/
    public static final String EN_YEAR_MONTH_DAY  = "yyyy-MM-dd";
    /**yyyy-MM*/
    public static final String EN_YEAR_MONTH  = "yyyy-MM";
    /**yyyy*/
    public static final String EN_YEAR = "yyyy";
    /**HH:mm:ss*/
    public static final String EN_HOUR_MIN_SEC  = "HH:mm:ss";
    /**HH:mm*/
    public static final String EN_HOUR_MIN  = "HH:mm";
    /**yyyy年MM月dd日 HH时mm分ss秒*/
    public static final String CN_YEAR_MONTH_DAY_HOUR_MIN_SEC  = "yyyy年MM月dd日 HH时mm分ss秒";
    /**yyyy-MM-dd HH:mm*/
    public static final String CN_YEAR_MONTH_DAY_HOUR_MIN  = "yyyy年MM月dd日 HH时mm分";
    /**yyyy-MM-dd HH*/
    public static final String CN_YEAR_MONTH_DAY_HOUR  = "yyyy年MM月dd日 HH时";
    /**yyyy-MM-dd*/
    public static final String CN_YEAR_MONTH_DAY  = "yyyy年MM月dd日";
    /**yyyy-MM*/
    public static final String CN_YEAR_MONTH  = "yyyy年MM月";
    /**yyyy*/
    public static final String CN_YEAR  = "yyyy年";
    /**HH:mm:ss*/
    public static final String CN_HOUR_MIN_SEC  = "HH时mm分ss秒";
    /** HH:mm*/
    public static final String CN_HOUR_MIN  = "HH时mm分";
    
    
	public static final int DISTANCE_YEAR = 0;
	public static final int DISTANCE_MONTH = 1;
	public static final int DISTANCE_DAY = 2;
    
    
    public static Map<String,String> patterns = new LinkedHashMap<String,String>();
	
	static {
		patterns.put("yyyy-MM-dd HH:mm:ss", "EN_YEAR_MONTH_DAY_HOUR_MIN_SEC");
		patterns.put("yyyy-MM-dd HH:mm", "EN_YEAR_MONTH_DAY_HOUR_MIN");
		patterns.put("yyyy-MM-dd HH", "EN_YEAR_MONTH_DAY_HOUR");
		patterns.put("yyyy-MM-dd", "EN_YEAR_MONTH_DAY");
		patterns.put("yyyy-MM", "EN_YEAR_MONTH");
		patterns.put("yyyy", "EN_YEAR");
		patterns.put("HH:mm:ss", "EN_HOUR_MIN_SEC");
		patterns.put("HH:mm", "EN_HOUR_MIN");
		patterns.put("yyyy年MM月dd日 HH时mm分ss秒", "CN_YEAR_MONTH_DAY_HOUR_MIN_SEC");
		patterns.put("yyyy年MM月dd日 HH时mm分", "CN_YEAR_MONTH_DAY_HOUR_MIN");
		patterns.put("yyyy年MM月dd日 HH时", "CN_YEAR_MONTH_DAY_HOUR");
		patterns.put("yyyy年MM月dd日", "CN_YEAR_MONTH_DAY");
		patterns.put("yyyy年MM月", "CN_YEAR_MONTH");
		patterns.put("yyyy年", "CN_YEAR");
		patterns.put("HH时mm分ss秒", "CN_HOUR_MIN_SEC");
		patterns.put("HH时mm分", "CN_HOUR_MIN");
	}
	
	/**
	 * 尝试解析字符串为日期类型
	 * @param date 要解析的字符串格式的日期
	 * @return Date 转换成的日期
	 */
	public static Date parseDate(String date){
		for(Map.Entry<String, String> fmtEntry : patterns.entrySet()){
			try{
				return new SimpleDateFormat(fmtEntry.getKey()).parse(date);
			}catch(Exception e){
			}
		}
		return null;
	}

	/**
	 * 将字符串按指定模板转化为日期
	 * @param date 要转换的日期
	 * @param pettern 要转换的格式
	 * @return Date 转换之后的日期
	 */
	public static Date parse(String date,String pettern){
    	Date retVal = null;
    	if(date==null||"".equals(date)){
    		return retVal;
    	}
    	if (pettern==null||"".equals(pettern.trim())) {
			pettern=DateUtil.EN_YEAR_MONTH_DAY;
		}
    	try {
			retVal=new SimpleDateFormat(pettern).parse(date);
		} catch (ParseException e) {
		}
    	return retVal;
    }
	/**
	 * 将字符串按指定模板转化为日期
	 * @param date 要转换的日期
	 * @param pettern 要转换的格式
	 * @return Date 转换之后的日期
	 */
    public static String formatDate(Date date,String pettern){
    	if(date==null){
    		return "";
    	}
    	if (pettern==null||"".equals(pettern.trim())) {
			pettern=DateUtil.EN_YEAR_MONTH_DAY;
		}
    	return new SimpleDateFormat(pettern).format(date);
    }
 
    /**
     * 将日期格式化成"yyyy-MM-dd"格式
     * @param date 要转换的日期
     * @return String 转换成的字符串
     */
    public static String formatDate(Date date){
    	if(date==null){
    		return "";
    	}
    	return DateUtil.formatDate(date,DateUtil.EN_YEAR_MONTH_DAY);
    }
    
	
    /**
     * 计算两个时间相差的"年"或"天"或"日"
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param type 相差的类别  0代表年,1代表月,2代表日
     * @return int 相差的"年"或"天"或"日"
     */
	public static int distanceDate(Date startDate, Date endDate, int type) {

		// TODO Auto-generated method stub
		int ret = 0;
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		boolean isPositive = true;
		if (null == startDate || null == endDate) {
			return ret;
		}
		if (startDate.before(endDate)) {
			date1.setTime(startDate);
			date2.setTime(endDate);
		} else {
			date1.setTime(endDate);
			date2.setTime(startDate);
			isPositive = false;
		}
		date1.set(Calendar.HOUR_OF_DAY, 0);
		date1.set(Calendar.MINUTE, 0);
		date1.set(Calendar.SECOND, 0);
		date1.set(Calendar.MILLISECOND, 0);
		date2.set(Calendar.HOUR_OF_DAY, 0);
		date2.set(Calendar.MINUTE, 0);
		date2.set(Calendar.SECOND, 0);
		date2.set(Calendar.MILLISECOND, 0);
		while (date1.before(date2)) {
			if (type == DateUtil.DISTANCE_YEAR) {
				date1.add(Calendar.YEAR, 1);
			} else if (type == DateUtil.DISTANCE_MONTH) {
				date1.add(Calendar.MONTH, 1);
			} else {
				date1.add(Calendar.DATE, 1);
			}
			if (!date1.after(date2))
				ret++;
		}
		if (!isPositive)
			ret = -ret;
		return ret;
	}
	
	/**
	 * 获取当前日期所在月份的第一天所在日期0点0分0秒
	 * @param date 要操作的日期对象
	 * @return Date 月份的第一天
	 */
	public static Date getFistDateInMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期所在月份的最后一天所在日期23点59分59秒
	 * @param date 要操作的日期对象
	 * @return Date 最后一天所在日期
	 */
	public static Date getLastDateInMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	

	/**
	 * 获得某月第一天
	 * @param year 年份
	 * @param month 月份（0代表1月）
	 * @return Date 第一天日期
	 */
	public static Date getFirstDateInMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 当年第一天
	 * @return Date 当前年的第一天
	 */
	public static Date getFirstDayOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	
	/**
	 * 或得某月的最后一天
	 * @param year 年份
	 * @param month 月份
	 * @return Date 最后一天
	 */
	public static Date getLastDateInMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int maxDayInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	

	/**
	 * 得到某天的开始时间0时0分0秒
	 * @param dateValue 要操作的日期
	 * @return Date 一天开始时间
	 */
	public static Date getBeginDateOfDate(Date dateValue) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateValue);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	
	/**
	 * 一天最后的时间23时59分59秒
	 * @param dateValue 要操作的日期
	 * @return  Date 一天的最后一秒
	 */
	public static Date getEndDateOfDate(Date dateValue) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateValue);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	
	/**
	 * 求两个时间的毫秒差
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return long 毫米差
	 */
	public static long timeDiff(Date start,Date end){
		return end.getTime() - start.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return float 相差的天数
	 */
	public static float timeDayDiff(Date start,Date end){
		return timeDiff(start,end)*1f/(1000*60*60*24);
	}
	
	
	/**
	 * 返回时间范围以内的每天的Date集合
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return List<Date> 时间列表
	 */
	public static List<Date> getDateList(Date start,Date end){
		List<Date> result = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		while(cal.getTime().getTime()<=end.getTime()){
			result.add(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return result;
	}
	
	
	
	/**
	 * 获取次日零点时间
	 * @return Date 零点时间
	 */
	public static Date getMiddleNightTime(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 昨天零时零分零秒
	 * @return Date 零时零分零秒
	 */
	public synchronized  static Date yesterday(){
		return yesterday(new Date());
	}
	
	/**
	 * 获得昨天的零时零分零秒
	 * @param date  操作日期
	 * @return Date 昨天的零时零分零秒
	 */
	public synchronized  static Date yesterday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 判断某个日期是否在给定的日期范围之内
	 * @param date 要比较的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return boolean 是否在范围内
	 */
	public synchronized static boolean between(Date date,Date start,Date end){
		return date.compareTo(start)>=0 && date.compareTo(end)<=0;
	}
	
	/**
	 * 在日期start的field上增加value个单位值
	 * @param start 开始日期
	 * @param field 操作字段
	 * @param value 要加的值
	 * @return Date 操作后的日期
	 */
	public static Date datePlus(Date start,int field,int value){
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(field, value);
		return cal.getTime();
	}
	
	public static String howLongBefore(Date date){
		Date now = new Date();
		long mSeconds = timeDiff(date, now);
		long min = mSeconds / (1000 * 60);
		int mi = 1;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = min / dd;
		long hour = (min - day * dd) / hh;
		long minute = (min - day * dd - hour * hh) / mi;

		String strDay = "" + day;
		String strHour = "" + hour;
		String strMinute = "" + minute;
		if(day>365){
			return new Double(day*1.0/365).intValue() + "年前";
		}else if(day>=30){
			return new Double(day*1.0/30).intValue() + "个月前";
		}
		
		if (strDay.equals("0")) {
			strDay="";
		}else {
			strDay=strDay+"天前";
		}
		if (strHour.equals("0")) {
			strHour="";
		}else {
			strHour=strHour+"小时前";
		}
		if (strMinute.equals("0")) {
			strMinute="刚刚";
		}else if(minute<5){
			strMinute="刚刚";
		}else {
			strMinute=strMinute+"分钟前";
		}
		if(day >= 2){
			return strDay;
		}
		if(day>=1){
			return "昨天";
		}
		if(hour>0){
			return strHour;
		}
		return strMinute;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(howLongBefore(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-09-07 11:56:00")));
	}
	
}
