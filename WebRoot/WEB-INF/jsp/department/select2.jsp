<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%
	pageContext.setAttribute("path", request.getParameter("path"));
    pageContext.setAttribute("c", request.getParameter("class"));
	pageContext.setAttribute("multiple", request.getParameter("multiple"));
%>
<form:input id="${path}" path="${path}" cssClass="object-select ${c}" style="width:100%;" 
data-queryurl="${basePath}department/select2Query.do"
data-querydelay="250"
data-multiple="${empty multiple ? 'false' : multiple}"
data-querymethod="POST"
data-initurl="${basePath}department/queryById.do?ids="
data-minlength="0"
data-renderdata="name"
data-echodata="name"
/>