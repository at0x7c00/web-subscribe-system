<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<th  data-sortfield="id" class="${nfn:sortClass(pageBean,'id')}"><spring:message code="base.function.table.head.no"/></th>
			<th align="center" data-sortfield="title" class="${nfn:sortClass(pageBean,'title')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.title"/>
		</th>
			<th align="center" data-sortfield="category" class="${nfn:sortClass(pageBean,'category')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.category"/>
		</th>
			<th align="center" data-sortfield="createTime" class="${nfn:sortClass(pageBean,'createTime')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.createTime"/>
		</th>
			<th align="center" data-sortfield="updateTime" class="${nfn:sortClass(pageBean,'updateTime')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.updateTime"/>
		</th>
			<th align="center" data-sortfield="creator" class="${nfn:sortClass(pageBean,'creator')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.creator"/>
		</th>
			<th align="center" data-sortfield="readCount" class="${nfn:sortClass(pageBean,'readCount')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.readCount"/>
		</th>
			<th align="center" data-sortfield="sortNum" class="${nfn:sortClass(pageBean,'sortNum')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.sortNum"/>
		</th>
			<th align="center" data-sortfield="cover" class="${nfn:sortClass(pageBean,'cover')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.cover"/>
		</th>
			<th align="center" data-sortfield="content" class="${nfn:sortClass(pageBean,'content')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.content"/>
		</th>
			<th align="center" data-sortfield="status" class="${nfn:sortClass(pageBean,'status')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.status"/>
		</th>
			<th align="center" data-sortfield="origin" class="${nfn:sortClass(pageBean,'origin')}">
			<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.origin"/>
		</th>