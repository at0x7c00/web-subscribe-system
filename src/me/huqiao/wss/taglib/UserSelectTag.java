package me.huqiao.wss.taglib;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import me.huqiao.wss.sys.entity.User;



/**
 * 选择弹出窗口选择，复杂生成类似以下的代码片段<br/>
 * <pre>
&lt;input id="employee.superior" name="superior.emp" type="hidden"/&gt;&lt;!--A:真正传递值的隐藏域--&gt;

&lt;input name="superior.name" type="text" class="required textInput valid" value="" 
suggestFields="emp,name" suggesturl="employee/suggestAll.do" 
lookupgroup="superior" autocomplete="on" /&gt;&lt;!--B:显示出来的文本框--&gt;

&lt;a class="btnLook" href="employee/selectAllList.do?multiSelect=0" hiddenid="emp_empNo" 
width="960" height="600" lookupgroup=""&gt;选择员工&lt;/a&gt;&lt;!--C:放大镜效果的选择按钮--&gt;

{"name":"总经理","emp":"2013001"}
</pre>
 * @author NOVOTS
 * @version Version 1.0
 *
 */
public class UserSelectTag extends TagSupport{
	
	/**
	 * input的name名称
	 */
	private String name;
	/**
	 * 是否多选
	 */
	private boolean multiSelect = false;
	
	private Integer width;
	private Integer height;
	private String cssClass;
	private Object value;
	private String params = "";
	private String propMap = "";
	private String callBack;
	
	private boolean isMultiSameName;
	
	private boolean useClean;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7436150012478051266L;

	@Override
	public int doEndTag() throws JspException {
		StringBuffer content = new StringBuffer();
		content.append("<input id=\"").append(name).append("_username\" readonly=\"true\" name=\"").append(name).append("\" type=\"text\" value=\"").append(makeEmpNosStr()).append("\"/>");
		content.append("<a  method=\"POST\" class=\"btnLook\"  isMultiSameName=\"").append(isMultiSameName()).append("\" href=\"user/selectAllList.do?multiSelect=").append(multiSelect?"1":"0");
		if(params!=null && !params.trim().equals("")){
			String name;
			String value;
			String[] tempArray = null;
			StringBuffer paramStr = new StringBuffer("");
			for(String param : params.split("&")){
				tempArray = param.split("=");
				name = tempArray[0];
				paramStr.append("&").append(name).append("=");
				if(tempArray.length>1){
					try {
						value = URLEncoder.encode(tempArray[1],"UTF8");
						//paramStr.append(value);
						paramStr.append(tempArray[1]);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			content.append(paramStr);
		}
		content.append("\" hiddenid=\"").append(name).append("_username\"");
		
		
		
		content.append("width=\"").append(getWidth()).append("\" height=\"").append(getHeight()).append("\" lookupgroup=\"");
		content.append(name);
		content.append("\" ");
		content.append("propMap=\"").append(getPropMap()).append("\"");
		if(callBack!=null &&!callBack.trim().equals("")){
			content.append("callBack=\"").append(callBack).append("\"");
		}
		content.append("><spring:message code=\"").append("funcs.User.select\" />").append("</a>");
		if(useClean){
			content.append("<a class='btnDel' groupname='").append(name).append("' href='javascript:void(0);' title='清除'>清除</a>");
		}
		
		try {
			pageContext.getOut().write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public static void main(String[] args) {
		String str = "%E4%B8%8A%E6%B5%B71";
		System.out.println(URLDecoder.decode(str));
	}
	
	private String makeEmpNosStr(){
		StringBuffer res = new StringBuffer("");
		if(value!=null){
			for(User emp : createCollectionFromValue()){
				if(res.length()>0){
					res.append(",");
				}
				res.append(emp.getUsername());
			}
		}
		return res.toString();
	}
	private String makeEmpNamesStr(){
		StringBuffer res = new StringBuffer("");
		if(value!=null){
			for(User emp : createCollectionFromValue()){
				if(res.length()>0){
					res.append(",");
				}
				res.append(emp.getUsername());
			}
		}
		return res.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}


	public Integer getWidth() {
		if(width==null){
			return 900;
		}
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		if(height==null){
			return 500;
		}
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getCssClass() {
		if(cssClass==null){
			return "";
		}
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getPropMap() {
		if(propMap==null || propMap.trim().equals("")){
			return "{"+this.getName()+":'username',"+this.getName()+"_name:'username'}";
		}
		return propMap;
	}

	public void setPropMap(String propMap) {
		this.propMap = propMap;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Collection<User> createCollectionFromValue(){
		if(value==null){
			return Collections.EMPTY_LIST;
		}
		if(value instanceof Collection){
			return (Collection<User>)value;
		}
		Set<User> emps = new HashSet<User>();
		emps.add((User)value);
		return emps;
	}

	public boolean isUseClean() {
		return useClean;
	}

	public void setUseClean(boolean useClean) {
		this.useClean = useClean;
	}

    public boolean isMultiSameName() {
        return isMultiSameName;
    }

    public void setIsMultiSameName(boolean isMultiSameName) {
        this.isMultiSameName = isMultiSameName;
    }

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}
    
}
