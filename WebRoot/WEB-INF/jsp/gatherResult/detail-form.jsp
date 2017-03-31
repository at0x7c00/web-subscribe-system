<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['title'].changed}">title= "${checkResult['title'].info}";</c:if>
						   >
			<span class="${checkResult['title'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.title"/>:
									<c:out value="${tempBean.title}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['url'].changed}">title= "${checkResult['url'].info}";</c:if>
						   >
			<span class="${checkResult['url'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.url"/>:
									<c:out value="${tempBean.url}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['task'].changed}">title= "${checkResult['task'].info}";</c:if>
						   >
			<span class="${checkResult['task'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.task"/>:
									${tempBean.task.name}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['createTime'].changed}">title= "${checkResult['createTime'].info}";</c:if>
						   >
			<span class="${checkResult['createTime'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.createTime"/>:
									<fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['status'].changed}">title= "${checkResult['status'].info}";</c:if>
						   >
			<span class="${checkResult['status'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.GatherResult.status"/>:
								${useStatusMap[tempBean.status]}
			</span>
		</label>
	</section>