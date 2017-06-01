<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<td>${tempBean.id}</td>
		<td>
		<h3><a href="${basePath}chapter/update.do?manageKey=${tempBean.manageKey}" target="dialogTodo" title="修改文章">${tempBean.title}</a></h3>
		分类:${tempBean.category.name} |
		作者:${tempBean.creator.username} |
		创建时间:<fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/> |
		修改时间:<fmt:formatDate value="${tempBean.updateTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/> |
		浏览:${tempBean.readCount} |
		排序:${tempBean.sortNum} |
		状态:${useStatusMap[tempBean.status]} |
		来源:<a href="${tempBean.origin}" target="blank"><n:shorthand content="${tempBean.origin}" length="20"/></a>
		</td>
