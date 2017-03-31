<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<c:if test="${not historyView}">
	<li>
		<a href="#tags" data-toggle="tab" <c:if test="${checkResult['tags'].changed}">style="color:red;" </c:if>><spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.tags"/></a>
	</li>
		</c:if>
		<c:if test="${not historyView}">
	<li>
		<a href="#covers" data-toggle="tab" <c:if test="${checkResult['cover'].changed}">style="color:red;" </c:if>><spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.cover"/></a>
	</li>
		</c:if>