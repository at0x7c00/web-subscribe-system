<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="pageHeader">
	<form:form onsubmit="return navTabSearch(this);" action="role/roleListUI" method="post" commandName="role">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label><spring:message code="props.me.huqiao.wss.sys.entity.Role.name"/>：</label>
					<form:input path="roleName"/>
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="base.function.query"/></button></div></div></li>
				</ul>
			</div>
		</div>
	</form:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<n:pv url="admin/role/add.do">
			<li><a class="add" href="role/add.do" target="navTab"><span><spring:message code="base.function.add"/></span></a></li>
			</n:pv>
			<n:pv url="admin/role/delete.do">
			<li><a class="delete" href="role/delete.do?id={sid_role}" target="ajaxTodo" title="确定要<spring:message code="base.function.delete"/>吗?"><span><spring:message code="base.function.delete"/></span></a></li>
			</n:pv>
			<n:pv url="admin/role/update.do">
			<li><a class="edit" href="role/update.do?id={sid_role}" target="navTab"><span><spring:message code="base.function.update"/></span></a></li>
			<</n:pv>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30" align="center"></th>
				<th width="80" align="center"><spring:message code="props.me.huqiao.wss.sys.entity.Role.name"/></th>
				<th align="center"><spring:message code="props.me.huqiao.wss.sys.entity.Role.functionPoint"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="role" items="${page.list}" varStatus="stauts">
				<tr target="sid_role" rel="${role.id}">
					<td align="center">${stauts.index+1}</td>
					<td>${role.roleName}</td>
					<td>${role.functionNames}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pageBar.jsp" %>
</div>
