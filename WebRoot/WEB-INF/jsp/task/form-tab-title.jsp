<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<li>
	<a href="#tags" data-toggle="tab" <c:if test="${checkResult['tags'].changed}">style="color:red;" </c:if>><spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.tags"/></a>
</li>
<li class=""><a href="#logs" data-toggle="tab">执行日志</a></li>