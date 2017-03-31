<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<th  data-sortfield="id" class="${nfn:sortClass(pageBean,'id')}"><spring:message code="base.function.table.head.no"/></th>
			<th align="center" data-sortfield="selector" class="${nfn:sortClass(pageBean,'selector')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Task.selector"/>
		</th>
			<th align="center" data-sortfield="cycle" class="${nfn:sortClass(pageBean,'cycle')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Task.cycle"/>
		</th>
			<th align="center" data-sortfield="name" class="${nfn:sortClass(pageBean,'name')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Task.name"/>
		</th>
			<th align="center" data-sortfield="url" class="${nfn:sortClass(pageBean,'url')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Task.url"/>
		</th>