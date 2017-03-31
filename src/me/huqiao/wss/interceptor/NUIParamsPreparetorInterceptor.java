package me.huqiao.wss.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限验证拦截器
 * @author NOVOTS
 * @version Version 1.0
 */
public class NUIParamsPreparetorInterceptor extends HandlerInterceptorAdapter {

	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		 String targetPanel = request.getParameter("targetPanel");
		 targetPanel = targetPanel==null || targetPanel.trim().equals("")  ? "content" : targetPanel;
		 request.setAttribute("targetPanel", targetPanel);
		 return true;
	 }
}
