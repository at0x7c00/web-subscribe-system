<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<th  data-sortfield="id" class="${nfn:sortClass(pageBean,'id')}" width="50px"><spring:message code="base.function.table.head.no"/></th>
		<th align="center" data-sortfield="name" class="${nfn:sortClass(pageBean,'name')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Task.name"/>
		</th>
		<th width="100px">
		状态
		</th>