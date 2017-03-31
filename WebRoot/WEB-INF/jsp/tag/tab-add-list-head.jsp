<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
		<th align="center" data-sortfield="sortNum" class="${nfn:sortClass(pageBean,'sortNum')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.sortNum"/>
		</th>
		<th align="center" data-sortfield="status" class="${nfn:sortClass(pageBean,'status')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.status"/>
		</th>
		<th align="center" data-sortfield="name" class="${nfn:sortClass(pageBean,'name')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.name"/>
		</th>