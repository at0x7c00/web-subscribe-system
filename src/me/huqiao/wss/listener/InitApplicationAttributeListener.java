package me.huqiao.wss.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import me.huqiao.wss.chapter.service.ITaskService;
import me.huqiao.wss.sys.entity.Config;
import me.huqiao.wss.sys.service.IConfigService;
import me.huqiao.wss.sys.service.impl.MemoryStorage;
import me.huqiao.wss.util.DateUtil;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 初始应用属性监听器
 * @author NOVOTS
 * @version Version 1.0
 */
public class InitApplicationAttributeListener implements ServletContextListener{
	
	public static Config SYS_CONFIG_MAIL_SEND_HOST_NAME =null;
	public static Config SYS_CONFIG_MAIL_SEND_HOST_ACCESS_NAME =null;
	public static Config SYS_CONFIG_MAIL_SEND_HOST_ACCESS_PASSWORD =null;
	public static Config SYS_CONFIG_MAIL_SEND_HOST_ACCESS_SMTP_AUTH =null;
	public static Config SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER =null;
	public static Config SYS_CONFIG_MAIL_SEND_HOST_ACCESS_APP_KEY =null;
	public static Config SYS_CONFIG_USER_DEFAULT_ROLE =null;
	
	public static WebApplicationContext wac;
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.setAttribute("EN_YEAR_MONTH_DAY_HOUR_MIN_SEC", DateUtil.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC);
		context.setAttribute("EN_YEAR_MONTH_DAY_HOUR_MIN", DateUtil.EN_YEAR_MONTH_DAY_HOUR_MIN);
		context.setAttribute("EN_YEAR_MONTH_DAY_HOUR", DateUtil.EN_YEAR_MONTH_DAY_HOUR);
		context.setAttribute("EN_YEAR_MONTH_DAY", DateUtil.EN_YEAR_MONTH_DAY);
		context.setAttribute("EN_YEAR_MONTH", DateUtil.EN_YEAR_MONTH);
		context.setAttribute("EN_YEAR", DateUtil.EN_YEAR);
		context.setAttribute("EN_HOUR_MIN_SEC", DateUtil.EN_HOUR_MIN_SEC);
		context.setAttribute("EN_HOUR_MIN", DateUtil.EN_HOUR_MIN);
		context.setAttribute("CN_YEAR_MONTH_DAY_HOUR_MIN_SEC", DateUtil.CN_YEAR_MONTH_DAY_HOUR_MIN_SEC);
		context.setAttribute("CN_YEAR_MONTH_DAY_HOUR_MIN", DateUtil.CN_YEAR_MONTH_DAY_HOUR_MIN);
		context.setAttribute("CN_YEAR_MONTH_DAY_HOUR", DateUtil.CN_YEAR_MONTH_DAY_HOUR);
		context.setAttribute("CN_YEAR_MONTH_DAY", DateUtil.CN_YEAR_MONTH_DAY);
		context.setAttribute("CN_YEAR_MONTH", DateUtil.CN_YEAR_MONTH);
		context.setAttribute("CN_YEAR", DateUtil.CN_YEAR);
		context.setAttribute("CN_HOUR_MIN_SEC", DateUtil.CN_HOUR_MIN_SEC);
		context.setAttribute("CN_HOUR_MIN", DateUtil.CN_HOUR_MIN);
		
		wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		IConfigService configService = wac.getBean(IConfigService.class);
		
		SYS_CONFIG_MAIL_SEND_HOST_NAME = configService.getById(Config.class, Config.SYS_CONFIG_MAIL_SEND_HOST_NAME);
	    SYS_CONFIG_MAIL_SEND_HOST_ACCESS_NAME = configService.getById(Config.class, Config.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_NAME);
		SYS_CONFIG_MAIL_SEND_HOST_ACCESS_PASSWORD = configService.getById(Config.class, Config.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_PASSWORD);
		SYS_CONFIG_MAIL_SEND_HOST_ACCESS_SMTP_AUTH = configService.getById(Config.class, Config.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_SMTP_AUTH);
		SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER = configService.getById(Config.class, Config.SYS_CONFIG_MAIL_SEND_HOST_ACCESS_DEFAULT_SENDER);
	    
	    MemoryStorage.getInstance().init(sce.getServletContext().getRealPath("/WEB-INF/classes/memorystorage.db"));
	    
	    ITaskService taskService = wac.getBean(ITaskService.class);
	    taskService.init();
		
		
	}

}
