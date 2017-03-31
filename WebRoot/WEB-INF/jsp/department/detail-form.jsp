<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['name'].changed}">title= "${checkResult['name'].info}";</c:if>
						   >
			<span class="${checkResult['name'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.name"/>:
									<c:out value="${tempBean.name}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['status'].changed}">title= "${checkResult['status'].info}";</c:if>
						   >
			<span class="${checkResult['status'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.status"/>:
								${useStatusMap[tempBean.status]}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['parent'].changed}">title= "${checkResult['parent'].info}";</c:if>
						   >
			<span class="${checkResult['parent'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.parent"/>:
									${tempBean.parent.name}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['sort'].changed}">title= "${checkResult['sort'].info}";</c:if>
						   >
			<span class="${checkResult['sort'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.sort"/>:
									<c:out value="${tempBean.sort}"/>
			</span>
		</label>
	</section>