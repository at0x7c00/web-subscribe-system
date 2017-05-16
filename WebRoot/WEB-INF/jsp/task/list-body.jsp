<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<td>${tempBean.id}</td>
		<td>
		<span class="label label-success hspan">
		${tempBean.name} 
		</span>
		<a href="${tempBean.url}" target="_blank">
		<span class="label label-danger hspan">
		<b>${tempBean.url}</b>  
		</span>
		</a>
		<span class="label label-info hspan" >${tempBean.selector}</span>&nbsp;&nbsp;<i class="fa fa-clock-o"></i> ${tempBean.cycle} min
		</td>
		<td>
	<c:choose>
		<c:when test="${ (tempBean.status eq 'NotStart') or (tempBean.status eq 'Ended')}">
			<a href="${basePath}task/start.do?manageKey=${tempBean.manageKey}" class="btn btn-primary" target="ajaxTodo" method="GET"><i class="fa fa-play"></i> 开始</a>
		</c:when>
		<c:when test="${ (tempBean.status eq 'Scheduled') or (tempBean.status eq 'Executing')}">
			<a href="${basePath}task/stop.do?manageKey=${tempBean.manageKey}" class="btn btn-danger"  target="ajaxTodo" method="GET"><i class="fa fa-stop"></i> 停止</a>
		</c:when>
	</c:choose>
	</td>