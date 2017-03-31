package me.huqiao.wss.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * 缩写自定义标签
 * @author NOVOTS
 * @version Version 1.0
 */
public class ShortHandTag  extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	private String content;
	private Integer length;
	
	private boolean htmlSecurity = true;
	
	@Override
	public int doEndTag() throws JspException {
		if(content==null){
			return EVAL_PAGE;
		}
		String res = "";
		if(htmlSecurity){
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll(">", "&gt;");
		}
		if(content.length()<=length){
			res = content;
		}else{
			res = content.substring(0,length)  + "...";
		}
		try {
			pageContext.getOut().write(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public boolean isHtmlSecurity() {
		return htmlSecurity;
	}


	public void setHtmlSecurity(boolean htmlSecurity) {
		this.htmlSecurity = htmlSecurity;
	}
	
}
