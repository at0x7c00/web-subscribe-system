<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['selector'].changed}">title= "${checkResult['selector'].info}";</c:if>
						   >
			<span class="${checkResult['selector'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.selector"/>:
									<c:out value="${tempBean.selector}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['cycle'].changed}">title= "${checkResult['cycle'].info}";</c:if>
						   >
			<span class="${checkResult['cycle'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.cycle"/>:
									<c:out value="${tempBean.cycle}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['name'].changed}">title= "${checkResult['name'].info}";</c:if>
						   >
			<span class="${checkResult['name'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.name"/>:
									<c:out value="${tempBean.name}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['url'].changed}">title= "${checkResult['url'].info}";</c:if>
						   >
			<span class="${checkResult['url'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.url"/>:
									<c:out value="${tempBean.url}"/>
			</span>
		</label>
	</section>