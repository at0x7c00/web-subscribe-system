package me.huqiao.wss.i18n;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;
/**
 * session解析器
 * @author NOVOTS
 * @version Version 1.0
 */

public class MySessionLocaleRsolver extends CookieLocaleResolver {
	
	static final int SECOND_OF_WEEK = 60 * 60 * 24 * 7;
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String localeName = getCookieMap(request).get(LOCALE_REQUEST_ATTRIBUTE_NAME);
		if(localeName!=null && !localeName.trim().equals("")){
			String[] tmp = localeName.split("_");
			if(tmp.length!=2){
				return request.getLocale();
			}
			Locale locale = new Locale(tmp[0],tmp[1]);
			setLocale(request,null,locale);
			return locale;
		}
		setLocale(request,null,request.getLocale());
		return request.getLocale();
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		if(response!=null){
			Cookie cookie = new Cookie(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME,locale.toString());
			cookie.setMaxAge(SECOND_OF_WEEK);
			response.addCookie(cookie);
		}
		request.getSession().setAttribute("locale", locale);
		request.getSession().setAttribute("isChina", "zh".equals(locale.getLanguage()));
	}
	
	/**
     * 将cookie数组转换成map键值对
     * @param request HttpServletRequest对象
     * @return map键值对
     */
    public Map<String,String> getCookieMap(HttpServletRequest request){
    	Map<String,String> cookiemap = new LinkedHashMap<String, String>();
    	if(request.getCookies()==null){
    		return cookiemap;
    	}
    	for(Cookie cookie : request.getCookies()){
    		cookiemap.put(cookie.getName(), cookie.getValue());
    	}
    	return cookiemap;
    }

}
