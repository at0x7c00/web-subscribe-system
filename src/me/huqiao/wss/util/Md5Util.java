package me.huqiao.wss.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
/**
 * md5工具类
 * @author NOVOTS
 * @version Version 1.0
 */
public class Md5Util {
	
	public static void main(String[] args) {
		System.out.println(getMD5Code("a"));
	}
	
	/**
	 * 获取md5加密
	 * @param str 要操作的字符串
	 * @return String 加密之后的字符串
	 */
	public static String getMD5Code(String str){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(str, null);
	}
	
	/**
	 * 当前时间毫秒数+ radom的32位MD5字符串
	 * @return String md5字符串
	 */
	public static String getManageKey(){
		return getMD5Code(System.currentTimeMillis()+""+Math.random());
	}
	
	/**
	 * 获取随机码
	 * @return String 0~1之间的小数值字符串
	 */
	public static String getRandomCode(){
		return Math.random()+"";
	}
	
	/**
	 * 验证密码(进行随机码混淆之后的密码验证)
	 * @param encPassFromPage 前台传递的md5码
	 * @param randomCodeInSession 在Session中存放的随机码
	 * @param pwdMd5InDb 用户数据库中的md5密码
	 * @return boolean 是否相等
	 */
	public static boolean checkPasswordWithPwdEncoded(String encPassFromPage,String randomCodeInSession,String pwdMd5InDb){
		if(randomCodeInSession==null){
			return false;
		}
		return getMD5Code(pwdMd5InDb+randomCodeInSession).equals(encPassFromPage);
	}
	/**
	 * 不进行随机码混淆的密码验证
	 * @param textpwd 明码密码
	 * @param pwdMd5InDb 数据库中md5加密的密码
	 * @return boolean 密码是否相等
	 */
	public static boolean checkPassword(String textpwd,String pwdMd5InDb){
		String md5=getMD5Code(textpwd);
		return getMD5Code(textpwd).equals(pwdMd5InDb);
	}
}