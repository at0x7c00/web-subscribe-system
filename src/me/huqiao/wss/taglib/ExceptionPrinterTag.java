package me.huqiao.wss.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 异常打印自定义标签
 * @author NOVOTS
 * @version Version 1.0
 */
public class ExceptionPrinterTag extends TagSupport {
	
	private Throwable throwable;
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();//得到out
		try {
			while(throwable!=null){
				out.println("<b style='font-size:14px;'>"+throwable.toString() +"</b><br/>");
				for(StackTraceElement ste : throwable.getStackTrace()){
					out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at " + ste.getClassName() + "." + ste.getMethodName() + "(" +ste.getFileName() + ":" + ste.getLineNumber() +")<br/>");
				}
				throwable = throwable.getCause();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
}
