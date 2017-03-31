<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
					<div class="widget-body no-padding" style="border:1px solid #e5e5e5;">
						<form:form action="department/history.do" class="smart-form smart-form-search" onsubmit="return ajaxSearch(this,'${targetPanel}')" commandName="rack">
							<input type="hidden" name="pageNum" value="${pageBean.pageNum}"/>
							<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
							<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
							<input type="hidden" name="targetPanel" value="${targetPanel}"/>
							<input type="hidden" name="list" value="yes"/>
							<input type="hidden" name="manageKey" value="${manageKey }"/>
							<div id="form-search-mobile" class="btn-header transparent pull-left" style="margin-top:-5px;">
								<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
							</div>
						    <fieldset>
							<div class="row">
								<%@include file="/WEB-INF/jsp/common/history-list-form.jsp" %>	
								<section class="col col-2 pull-right">
										<a class="btn btn-primary smart-form-submit-btn pull-right" href="#" style="padding:5px 15px;"><i class="fa fa-search"></i> <spring:message code="base.function.query"/></a>
								</section>
							</div>
							</fieldset>
							<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
							<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
							<tr>
								<th><spring:message code="history.list.operatetype.column.version"/></th>
								<th><spring:message code="history.list.operatetype.column.operatetype"/></th>
								<th><spring:message code="history.list.operatetype.column.operator"/></th>
								<th><spring:message code="history.list.operatetype.column.operatetime"/></th>
								<%@include file="/WEB-INF/jsp/department/history-list-head.jsp" %>
								<th><spring:message code="base.function.view"/></th>
							</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageBean.list}">
									<tr>
										<td colspan="30">
											<center>
												<font style="color: gray;"><spring:message code="base.function.table.info.nodata" /> </font>
											</center>
										</td>
									</tr>
								</c:if>
								<c:forEach var="historyRecord" items="${pageBean.list}"
									varStatus="stauts">
									<c:set var="tempBean" value="${historyRecord.record}"/>
									<tr target="sid_server" rel="${historyRecord.revisionEntity.id}">
										<td>${tempBean.version}</td>
										<td>
											<c:choose>
												<c:when test="${historyRecord.type eq 'ADD'}"><b style="color:green;background: #000;padding:1px 5px;"><spring:message code="history.list.operatetype.add"/></b></c:when>
												<c:when test="${historyRecord.type eq 'DEL'}"><b style="color:red;background: #000;padding:1px 5px;"><spring:message code="history.list.operatetype.del"/></b></c:when>
												<c:when test="${historyRecord.type eq 'MOD'}"><b style="color:yellow;background: #000;padding:1px 5px;"><spring:message code="history.list.operatetype.mod"/></b></c:when>
											</c:choose>
										</td>
										<td>${historyRecord.revisionEntity.username}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${historyRecord.revisionEntity.timestamp}"/></td>
										<%@include file="/WEB-INF/jsp/department/list-body.jsp" %>
										<td>
											<n:pv url="department/history.do">
												<a href="department/history.do?version=${historyRecord.revisionEntity.id}&view=yes" rel="manageKeys" target="jquiDialogTodo" title="<spring:message code="base.function.view"></spring:message><spring:message code="base.function.history"/>" width="760" height="400"> <spring:message code="base.function.view"/></a>
											</n:pv>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						<%@include file="/WEB-INF/jsp/common/pageBar.jsp" %>
						</form:form>
					</div>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>