<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
				<td>
									${tempBean.title}
				</td>
				<td>
									${tempBean.url}
				</td>
				<td>
									${tempBean.task.name}
				</td>
				<td>
								<fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/>
				</td>
				<td>
									${useStatusMap[tempBean.status]}
				</td>