<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%
	pageContext.setAttribute("path", request.getParameter("path"));
	pageContext.setAttribute("c", request.getParameter("class"));
	
%>
<form:input id="${path}" path="${path}" cssClass="object-select ${c}" style="width:100%;" 
data-queryurl="${basePath}user/select2Query.do"
data-querydelay="250"
data-multiple="${empty multiple ? 'false' : multiple}"
data-querymethod="POST"
data-params="username"
data-initurl="${basePath}user/queryById.do?username="
data-minlength="0"
data-renderdata="username,chineseName"
data-echodata="username"
/>