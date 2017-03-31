<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<td>${tempBean.id}</td>
		<td>
		${tempBean.title}
		</td>
			<td>${tempBean.category.name}</td>
			<td><fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/></td>
			<td><fmt:formatDate value="${tempBean.updateTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/></td>
			<td>${tempBean.creator.username}</td>
		<td>
		${tempBean.readCount}
		</td>
		<td>
		${tempBean.sortNum}
		</td>
			<td>${tempBean.cover.name}</td>
		<td>
		${tempBean.content}
		</td>
		<td>
		${useStatusMap[tempBean.status]}
		</td>
		<td>
		${tempBean.origin}
		</td>