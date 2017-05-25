package me.huqiao.wss.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import me.huqiao.wss.interceptor.PrivilegeValidateInterceptor;
import me.huqiao.wss.util.Constants;
import me.huqiao.wss.util.web.LoginInfo;


/**
 * 权限验证自定义标签
 * @author NOVOTS
 * @version Version 1.0
 */
public class PrivilegeValidateTag  extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	private String url;

	@Override
	public int doStartTag() throws JspException {
		PrivilegeValidateInterceptor privilegeValidateInterceptor = new PrivilegeValidateInterceptor();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		LoginInfo loginInfo = (LoginInfo) req.getSession().getAttribute(Constants.LOGIN_INFO_IN_SESSION);
		/*if(1==1){
			return EVAL_BODY_INCLUDE;
		}*/
		if(loginInfo==null){
			return SKIP_BODY;
		}
		if(privilegeValidateInterceptor.hasPrivilege(loginInfo, url,false,null,null)!=null){
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
