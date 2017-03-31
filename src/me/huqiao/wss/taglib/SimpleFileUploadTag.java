package me.huqiao.wss.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 文件上传,生成类似以下的代码片段<br/>
 * <pre>
 *  &lt;input id="testFileInput" 
 		type="file" 
 		name="photofile" 
 		class="textInput required"
		uploaderOption="{swf:'dwz-ria/uploadify/scripts/uploadify.swf',
		uploader:'filee/add.do',
		formData:{},
		buttonText:'选择文件...',
		fileSizeLimit:'200KB', 
		fileObjName:'photofile',
		fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
		fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;*.*;',
		auto:false,
		multi:false,
		removeCompleted:false,
		onUploadSuccess:uploadifySuccess,
		onQueueComplete:uploadifyQueueComplete}"/&gt;
 * </pre>
 * 该文件上传input基于uploadify( http://www.uploadify.com/documentation/)和dwz。文件被上传成功之后，将会返回给你一个manageKey,
 * 这个文件已经被保存到了数据库，你只需要拿着这个manageKey到后台进行进一步的关联即可
 * @author NOVOTS
 * @version Version 1.0
 */
public class SimpleFileUploadTag extends TagSupport{

	private static final long serialVersionUID = -3275021771962745617L;
	
	private String id;
	private String name;
	private String cssClass;
	private String buttonText;
	private Integer fileSizeLimit;
	private String fileTypeExts;
	private boolean multiSelect = false;
	private String folder;
	private Integer width;
	private Integer height;

	@Override
	public int doEndTag() throws JspException {
		StringBuffer content = new StringBuffer();
		content.append("<input id=\"").append(getId()).append("\" ");
		content.append("type=\"file\"  ");
		content.append("name=\"").append(getName()).append("\"  ");
		content.append("class=\"").append(getCssClass()).append("\" ");
		content.append("uploaderOption=\"{swf:'dwz-ria/uploadify/scripts/uploadify.swf', ");
		content.append("uploader:'filee/add.do', ");
		content.append("formData:{folderId:'").append(getFolder()).append("'}, ");
		content.append("buttonText:'").append(getButtonText()).append("', ");
		content.append("fileSizeLimit:'").append(getFileSizeLimit()).append("KB',  ");
		content.append("fileObjName:'").append(getName()).append("', ");
		content.append("fileTypeDesc:'").append(getFileTypeExts()).append("', ");
		content.append("fileTypeExts:'").append(getFileTypeExts()).append("', ");
		content.append("queueSizeLimit:1,");
		content.append("auto:true, ");
		if(getWidth()!=null){
			content.append("width:").append(getWidth()).append(",");
		}
		if(getHeight()!=null){
			content.append("height:").append(getHeight()).append(",");
		}
		content.append("multi:").append(isMultiSelect()).append(", ");
		content.append("removeCompleted:false, ");
		content.append("onUploadSuccess:uploadifySuccess, ");
		content.append("onQueueComplete:uploadifyQueueComplete}\"/> ");
		try {
			pageContext.getOut().write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(content);
		return EVAL_PAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getButtonText() {
		if(buttonText==null || buttonText.trim().equals("") ){
			return "选择文件";
		}
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public Integer getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(Integer fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}

	public String getFileTypeExts() {
		if(fileTypeExts == null){
			return "*.*";
		}
		return fileTypeExts;
	}

	public void setFileTypeExts(String fileTypeExts) {
		this.fileTypeExts = fileTypeExts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
}
