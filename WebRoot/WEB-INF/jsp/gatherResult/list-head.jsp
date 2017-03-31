<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<th  data-sortfield="id" class="${nfn:sortClass(pageBean,'id')}"><spring:message code="base.function.table.head.no"/></th>
			<th align="center" data-sortfield="title" class="${nfn:sortClass(pageBean,'title')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.title"/>
		</th>
			<th align="center" data-sortfield="url" class="${nfn:sortClass(pageBean,'url')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.url"/>
		</th>
			<th align="center" data-sortfield="task" class="${nfn:sortClass(pageBean,'task')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.task"/>
		</th>
			<th align="center" data-sortfield="createTime" class="${nfn:sortClass(pageBean,'createTime')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.createTime"/>
		</th>
			<th align="center" data-sortfield="status" class="${nfn:sortClass(pageBean,'status')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.status"/>
		</th>