package me.huqiao.wss.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextAccessUtilListener implements ServletContextListener {

	
	private static WebApplicationContext context;
	
	public static WebApplicationContext getContext() {
		return context;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context =  WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
