<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['sortNum'].changed}">title= "${checkResult['sortNum'].info}";</c:if>
						   >
			<span class="${checkResult['sortNum'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.sortNum"/>:
									<c:out value="${tempBean.sortNum}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['status'].changed}">title= "${checkResult['status'].info}";</c:if>
						   >
			<span class="${checkResult['status'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.status"/>:
								${useStatusMap[tempBean.status]}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['name'].changed}">title= "${checkResult['name'].info}";</c:if>
						   >
			<span class="${checkResult['name'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.name"/>:
									<c:out value="${tempBean.name}"/>
			</span>
		</label>
	</section>