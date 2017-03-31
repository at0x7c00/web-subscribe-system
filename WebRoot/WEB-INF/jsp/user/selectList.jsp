<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<form:form onsubmit="return dialogSearch(this);"
	action="user/selectAllList.do" method="post" commandName="user"
	id="pagerForm">
	<input type="hidden" name="pageNum" value="${pageBean.pageNum}" />
	<input type="hidden" name="orderField" value="${pageBean.orderField}" />
	<input type="hidden" name="multiSelect" value="${multiSelect}" />
	<input type="hidden" name="type" value="${type}" />
	<input type="hidden" name="orderDirection"
		value="${pageBean.orderDirection}" />
	<div class="pageHeader">
		<div class="searchBar" style="height:75px">
			<ul class="searchContent">
				<li>
					<label><spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>：</label>
					<form:input path="userNameQuery" id="user.username" class="textInput" size="12"/>
				</li>
				<li>
					<label>
						<spring:message code="props.me.huqiao.wss.sys.entity.User.company"></spring:message>：
					</label>
					<form:input path="queryName" id="company.name" class="textInput" size="12"/>
				</li>
				
			</ul>
			<div class="subBar" style="width:150px">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
										<spring:message code="base.function.query" />
										
										
								</button>
							</div>
						</div>
					</li>
					<c:if test="${multiSelect==1}">
					<li>
						<div class="button">
							<div class="buttonContent"><button type="button" multlookup="usernames" warn="--请选择--数据"><spring:message code="base.common.select.btntext" /></button></div>
						</div>
					</li>
					</c:if>
				</ul>
			</div>
			
		</div>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<c:if test="${multiSelect==1}">
						<th width="30" align="center"><input type="checkbox" group="usernames" class="checkboxCtrl"></th>
					</c:if>
					<th orderField="username"
						<c:if test="${pageBean.orderField=='username'}">class="${pageBean.orderDirection}"</c:if>>
						<spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>
					</th>
					<th orderField="email"
						<c:if test="${pageBean.orderField=='email'}">class="${pageBean.orderDirection}"</c:if>>
						<spring:message code="props.me.huqiao.wss.sys.entity.User.email"/>
					</th>
					<th orderField="chineseName"
						<c:if test="${pageBean.orderField=='chineseName'}">class="${pageBean.orderDirection}"</c:if>>
						<spring:message code="props.me.huqiao.wss.sys.entity.User.chineseName"></spring:message>
					</th>
					 <th orderField="company"
						<c:if test="${pageBean.orderField=='company'}">class="${pageBean.orderDirection}"</c:if>>
						<spring:message code="props.me.huqiao.wss.sys.entity.User.company"></spring:message>
					</th>
					<c:if test="${multiSelect!=1}">
					<th>
						<spring:message code="base.common.selectone" />
					</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty pageBean.list}">
					<tr>
						<td colspan="38">
							<center>
								<font style="color: gray;"><spring:message
										code="base.function.table.info.nodata" /> </font>
							</center>
						</td>
					</tr>
				</c:if>
				<c:forEach var="tempUser" items="${pageBean.list}"
					varStatus="stauts">
					<tr target="sid_employee" rel="${tempUser.username}">
						<c:if test="${multiSelect==1}">
							<td align="center"><input name="usernames" type="checkbox" value="{username:'${tempUser.username}'}" 
							<c:if test="${nfn:contains(selectedItem,tempUser.username) }">
								checked="checked"
							</c:if>
							/></td>
						</c:if>
						<td>
							${tempUser.username}
						</td>
						<td>
							${tempUser.email}
						</td>
						<td>
							${tempUser.chineseName}
						</td>
						 <td>
							${tempUser.company.chineseName}
						</td>
						<c:if test="${multiSelect!=1}">
						<td>
							<a class="btnSelect" href="javascript:void(0);" onclick="selectThis('${tempUser.username}')" title="点击选择">选择</a>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@include file="/WEB-INF/jsp/common/pageBar.jsp"%>
	</div>
	<script type="text/javascript">
		function selectThis(name){
		   $.bringBack({username:name});
		}
	</script>
</form:form>
