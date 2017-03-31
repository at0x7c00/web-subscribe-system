<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="dataTables_wrapper"
							style="border-bottom:1px solid #ddd;">
<table id="dt_basic"
	   class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<c:if test="${not(showCheckBox eq 'false')}">
				<th width="16" align="center">
					<label class="checkbox">
					<input type="checkbox" class="smart-form-checkall" data-groupname="${keyName}">
					<i></i>
					</label>
				</th>
			</c:if>
			<%@include file="/WEB-INF/jsp/department/list-head.jsp"%>
		</tr>
	</thead>
	<tbody id="dataTbody">
		<c:if test="${empty departments}">
		<tr id="firstTr">
			<td colspan="20">
				<center>
					<font style="color: gray;">
						<spring:message code="base.function.table.info.nodata" /> 
					</font>
				</center>
			</td>
		</tr>
		</c:if>
		<c:forEach var="tempBean" items="${departments}" varStatus="stauts">
			<tr target="${keyName}" rel="${tempBean.manageKey}">
				<c:if test="${not(showCheckBox eq 'false')}">
					<td align="center"  width="16">
						<label class="checkbox">
							<input name="${keyName}" type="checkbox" value=""/><i></i>
						</label>
						<input name="${keyName}" type="hidden" value="${tempBean.manageKey}"/><i></i>
					</td>
				</c:if>
				<%@include file="/WEB-INF/jsp/department/list-body.jsp" %>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>