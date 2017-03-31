package me.huqiao.wss.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * HTML输出标签
 * @author hq
 */
public class NovotsOutTag  extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	private String value;
	
	@Override
	public int doEndTag() throws JspException {
		if(value==null){
			return EVAL_PAGE;
		}
		try {
			pageContext.getOut().write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
