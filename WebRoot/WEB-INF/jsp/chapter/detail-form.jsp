<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<section class="col col-12">
		<label class="input"
						   <c:if test="${checkResult['title'].changed}">title= "${checkResult['title'].info}";</c:if>
						   >
			<span class="${checkResult['title'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.title"/>:
					<b>
					<c:out value="${tempBean.title}"/>
					</b>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['category'].changed}">title= "${checkResult['category'].info}";</c:if>
						   >
			<span class="${checkResult['category'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.category"/>:
									${tempBean.category.name}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['createTime'].changed}">title= "${checkResult['createTime'].info}";</c:if>
						   >
			<span class="${checkResult['createTime'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.createTime"/>:
									<fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['updateTime'].changed}">title= "${checkResult['updateTime'].info}";</c:if>
						   >
			<span class="${checkResult['updateTime'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.updateTime"/>:
									<fmt:formatDate value="${tempBean.updateTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['creator'].changed}">title= "${checkResult['creator'].info}";</c:if>
						   >
			<span class="${checkResult['creator'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.creator"/>:
									${tempBean.creator.username}
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['readCount'].changed}">title= "${checkResult['readCount'].info}";</c:if>
						   >
			<span class="${checkResult['readCount'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.readCount"/>:
									<c:out value="${tempBean.readCount}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['sortNum'].changed}">title= "${checkResult['sortNum'].info}";</c:if>
						   >
			<span class="${checkResult['sortNum'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.sortNum"/>:
									<c:out value="${tempBean.sortNum}"/>
			</span>
		</label>
	</section>
	<section class="col col-3">
		<label class="input"
						   <c:if test="${checkResult['status'].changed}">title= "${checkResult['status'].info}";</c:if>
						   >
			<span class="${checkResult['status'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.status"/>:
								${useStatusMap[tempBean.status]}
			</span>
		</label>
	</section>
	<section class="col col-12">
		<label class="input"
						   <c:if test="${checkResult['origin'].changed}">title= "${checkResult['origin'].info}";</c:if>
						   >
			<span class="${checkResult['origin'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.origin"/>:
									<c:out value="${tempBean.origin}"/>
			</span>
		</label>
	</section>
	<section class="col col-12">
		<label class="input"
						   <c:if test="${checkResult['content'].changed}">title= "${checkResult['content'].info}";</c:if>
						   >
			<span class="${checkResult['content'].changed ? 'change-markup':''}">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.content"/>:
									<c:out value="${tempBean.content}"/>
			</span>
		</label>
	</section>