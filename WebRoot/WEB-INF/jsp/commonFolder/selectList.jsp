<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<form:form onsubmit="return navTabSearch(this);"
	action="commonFolder/list.do" method="post" commandName="commonFolder"
	id="pagerForm">
	<input type="hidden" name="pageNum" value="${pageBean.pageNum}" />
	<input type="hidden" name="orderField" value="${pageBean.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${pageBean.orderDirection}" />
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">

			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<spring:message code="base.function.query" />
								</button>
							</div>
						</div>
					</li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="button" multlookup="ids"
									warn="--<spring:message code="base.common.selectone"/>--">选择带回</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="30" align="center"><input type="checkbox"
						group="ids" class="checkboxCtrl">
					</th>
					<th orderField="id"
						<c:if test="${pageBean.orderField=='id'}">class="${pageBean.orderDirection}"</c:if>><spring:message
							code="base.function.table.head.no" />
					</th>
					<th orderField="folderName"
						<c:if test="${pageBean.orderField=='folderName'}">class="${pageBean.orderDirection}"</c:if>>文件夹名</th>
					<th orderField="storePath"
						<c:if test="${pageBean.orderField=='storePath'}">class="${pageBean.orderDirection}"</c:if>>存储路径</th>
					<th orderField="storePath">选择</th>

				</tr>
			</thead>
			<tbody>
				<c:if test="${empty pageBean.list}">
					<tr>
						<td colspan="4">
							<center>
								<font style="color: gray;"><spring:message
										code="base.function.table.info.nodata" /> </font>
							</center></td>
					</tr>
				</c:if>
				<c:forEach var="tempcommonFolder" items="${pageBean.list}"
					varStatus="stauts">
					<tr target="sid_commonFolder" rel="${tempcommonFolder.id}">
						<td align="center"><input name="ids" type="checkbox"
							value="{ids:'${tempcommonFolder.id}','folderName':'${tempcommonFolder.folderName}'}" />
						</td>
						<td>${tempcommonFolder.id}</td>
						<td>${tempcommonFolder.folderName}</td>
						<td>${tempcommonFolder.storePath}</td>
						<td><a class="button"
							href="javascript:$.bringBack({folderName:'${tempcommonFolder.folderName}', id:'${tempcommonFolder.id}'})"
							title="查找带回"><span><spring:message code="base.common.form.select.select"></spring:message></span></a></td>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@include file="/WEB-INF/jsp/common/pageBar.jsp"%>
	</div>
</form:form>
