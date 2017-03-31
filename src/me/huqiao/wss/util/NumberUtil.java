package me.huqiao.wss.util;
/**
 * 数字工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class NumberUtil {
	public static final int ROUND_DEFAULT_DIGIT = 2;
	/**
	 * 四舍五入value，当n>0时，保留小数点后n位，当n<0时将整数部分从左往右第n位以后变为0 ,n小于0直接返回value
	 * @param value 要操作的浮点数
	 * @param n  要显示的位数
	 * @return double 双浮点数
	 */
	public static double round(double value,int n){
		/*if(n<0){
			return value;
		}*/
		double d = Math.pow(10, n);
		return  Math.round(value*d)/d;
	}
	/**
	 * 百分位四舍五入（位数设定：NumberUtil.ROUND_DEFAULT_DIGIT=2）
	 * @param value 要操作的浮点数
	 * @return double 百分位四舍五入后的浮点数
	 */
	public static double round2Digit(double value){
		return  round(value,NumberUtil.ROUND_DEFAULT_DIGIT);
	}
	
	/**
	 * double类型如果没有小数部分则只显示整数，不显示小数点及小数
	 * @param value 要操作的浮点数
	 * @return String 操作后字符串
	 */
	public static String round2DigitToString(double value){
		return  NumberUtil.doubleToString(round(value,NumberUtil.ROUND_DEFAULT_DIGIT));
	}
	/**
	 * 双浮点数到字符串
	 * @param value 要操作的浮点数
	 * @return String 返回的字符串
	 */
	public static String doubleToString(double value){
		String strValue = "";
		if( Math.floor(value) == value ){
			strValue = strValue + (int)value ;
		}else{
			strValue = strValue + value;
		}
		return strValue;
	}
	/**
	 * <p>进0.5</p>
	 * <p>当n>0时，保留小数点后n位，当n<0时将整数部分从左往右第n位以后变为0</p>
	 * @param value 要操作的浮点数
	 * @param n 小数点后几位
	 * @return Double 双精度浮点数
	 */
	public static Double BinaryCeil(Double value,int n){
		Double retVal=Double.valueOf(0);
		double d = Math.pow(10, n-1);
		if (value!=null) {
			retVal=Math.ceil(value*d*2)/(d*2);
		}
		return retVal;
	}
	
}
