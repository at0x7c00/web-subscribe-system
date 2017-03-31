package me.huqiao.wss.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * HTML输出标签
 * @author hq
 */
public class NovotsHtmlTag  extends TagSupport{
	
	
    public static Map<String,String> specialCharactersRepresentation = new HashMap<String,String>();
    static {
        specialCharactersRepresentation.put("&amp;","&");
        specialCharactersRepresentation.put("&lt;","<");
        specialCharactersRepresentation.put("&gt;",">");;
        specialCharactersRepresentation.put("&#034;","\"");;
        specialCharactersRepresentation.put("&#039;","'");;
    }

	private static final long serialVersionUID = 1L;
	
	private String value;
	private Integer length;
	
	@Override
	public int doEndTag() throws JspException {
		if(value==null){
			return EVAL_PAGE;
		}
		for(Map.Entry<String, String> entry : specialCharactersRepresentation.entrySet()){
			value = value.replaceAll(entry.getKey(), entry.getValue());
		}
		if(length!=null && length >0){
			value = value.substring(0,length);
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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
}
