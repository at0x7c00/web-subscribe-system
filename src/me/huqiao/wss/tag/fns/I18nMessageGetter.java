package me.huqiao.wss.tag.fns;

import javax.servlet.http.HttpServletRequest;

import me.huqiao.wss.i18n.MySessionLocaleRsolver;
import me.huqiao.wss.listener.InitApplicationAttributeListener;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;

/**
 * 国际化
 * @author NOVOTS
 * @version Version 1.0
 */
public class I18nMessageGetter {

	public static String i18nMessage(HttpServletRequest request,String code){
		WebApplicationContext wac = InitApplicationAttributeListener.wac;
		ResourceBundleMessageSource messageSource  = (ResourceBundleMessageSource)wac.getBean("messageSource");
		MySessionLocaleRsolver localeResolver =(MySessionLocaleRsolver) wac.getBean("localeResolver");
		return messageSource.getMessage(code,null,localeResolver.resolveLocale(request));
	}
}
