package me.huqiao.wss.taglib;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 日期格式自定义标签
 * @author NOVOTS
 * @version Version 1.0
 */
public class FormatDateTag extends TagSupport{

	private Date value;
	private String pattern;
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();//得到out
		try {
			out.println(new SimpleDateFormat(pattern).format(value));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
