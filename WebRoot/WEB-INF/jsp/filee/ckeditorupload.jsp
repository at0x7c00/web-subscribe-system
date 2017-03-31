<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<script type="text/javascript">
	window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}','${basePath}filee/${type eq 'images'?'viewPic':'downloadFile'}.do?${type eq 'images'?'manageKey':'key'}=${fileeManageKey}',function() {
	    if(${!empty errorMessage}){
	    	if("${errorMessage}" =="file size out of limit"){
	    		alert("<spring:message code="base.common.upload.sizeoutoflimit"/>");
	    	}else{
		    	alert("${errorMessage}");
	    	}
	    	return false;
	    }
		var dialog = this.getDialog();
	    if ( dialog.getName() == 'link' ) {
	        oEditor = dialog.getParentEditor();
	        var newElement = window.parent.CKEDITOR.dom.element.createFromHtml( "<a "
	        		+ "href='${basePath}filee/${type eq 'images'?'viewPic':'downloadFile'}.do?${type eq 'images'?'manageKey':'key'}=${fileeManageKey}'>${oriFileName}</a>", oEditor.document );
	    	oEditor.insertElement( newElement );
	    	dialog.hide();
		    return false;
	    }
	});   
</script>
