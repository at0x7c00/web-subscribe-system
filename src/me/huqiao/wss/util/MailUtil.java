package me.huqiao.wss.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import me.huqiao.wss.listener.InitApplicationAttributeListener;
import me.huqiao.wss.sys.entity.Config;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 系统邮件配置
 * @author NOVOTS
 * @version Version 1.0
 */
public class MailUtil {
	private static final Logger log = Logger.getLogger(MailUtil.class);
	/**
	 * 邮件发送服务初始化
	 * @return JavaMailSenderImpl 邮件发送
	 */
	public static JavaMailSenderImpl getJavaMailSenderImpl() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Config c1 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_NAME;
		Config c2 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_NAME;
		Config c3 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_PASSWORD;
		Config c4 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_SMTP_AUTH;
		mailSender.setHost(c1.getDescripttion1()); //邮件主机
		mailSender.setUsername(c2.getDescripttion1());//访问用户名
		mailSender.setPassword(c3.getDescripttion1());//访问密码
		Properties  prop  =  new  Properties();
		if ("Yes".equals(c4.getDescripttion1())) {
			prop.setProperty("mail.smtp.auth", "true" );//是否验证  
		}
		mailSender.setJavaMailProperties(prop);
		return mailSender;
	}
	/**
	 * 发送邮件
	 * @param mail 要发送的信息
	 */
	public static void sendMail(SimpleMailMessage mail){
		Config c1 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_NAME;
		Config c5 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER;
		mail.setFrom(c5.getDescripttion1());//默认发送人
		//System.out.println("length---------"+mail.getTo().length);
		if(!"".equals(c1.getDescripttion1())&&c1.getDescripttion1()!=null){
			mail.setTo(clearEmptyTo(mail.getTo()));
			if(mail.getTo()!=null && mail.getTo().length>=1){
				MailUtil.getJavaMailSenderImpl().send(mail);			
			}else{
				if(!"".equals(mail.getSubject())&&mail.getSubject()!=null)
					log.info(mail.getSubject()+"收件人邮件为空,请核实!");
			}
		}else{
			log.info("邮件主机不能为空,请核实!");
		}	
	}
	/**
	 * 清空收件人地址为空或空字符串的地址
	 * @param oldTo 要清空的收件人数组
	 * @return String[] 清空之后收件人地址数组 
	 */
	private static String[] clearEmptyTo(String[] oldTo){
		List<String> newTo = new ArrayList<String>();
		if(oldTo!=null){
			for(String str : oldTo){
				if(str!=null && !str.trim().equals("")){
					newTo.add(str);
				}
			}
		}
		String[] res = new String[newTo.size()];
		int index = 0;
		for(String str : newTo){
			res[index++]=str;
		}
		return res;
	}
	/**
	 * 设置要发送邮件的发送人信息
	 * @return SimpleMailMessage 设置之后的邮件信息
	 */
	public static SimpleMailMessage getSimpleMailMessage(){
		SimpleMailMessage  mail  =  new  SimpleMailMessage();
		Config c5 = InitApplicationAttributeListener.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER;
		mail.setFrom(c5.getDescripttion1());//默认发送人
		return mail;
	}
}
