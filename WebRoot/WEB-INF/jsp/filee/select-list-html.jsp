<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;">
	<table id="dt_basic"  class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th align="center">
					<label class="checkbox">
							<input type="checkbox" name="checkbox" class="smart-form-checkall" data-groupname="${keyName }">
					<i></i></label>
				</th>
				<th data-sortfield="id" >
					<spring:message code="base.function.table.head.no" /></th>
			
				<th orderfield="name"  data-sortfield="name"  >
					<spring:message
						code="props.me.huqiao.wss.sys.entity.Filee.name"></spring:message>
				</th>
					
				<th orderField="extensionName" data-sortfield="extensionName" >
					<spring:message
						code="props.me.huqiao.wss.sys.entity.Filee.extensionName"></spring:message>
				</th>
				<th orderField="createDate" >
					<spring:message
						code="props.me.huqiao.wss.sys.entity.Filee.createDate"></spring:message>
				</th>
				<th orderField="folder">
					<spring:message
						code="props.me.huqiao.wss.sys.entity.Filee.folder"></spring:message>
				</th>
				<!-- <th orderField="manageKey">
					ManageKey</th> -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty commonFiles}">
				<tr>
					<td colspan="8">
						<center>
							<font style="color: gray;"><spring:message
									code="base.function.table.info.nodata" /> </font>
						</center></td>
				</tr>
			</c:if>
			<c:forEach var="tempfilee" items="${commonFiles}"
				varStatus="stauts">
				<tr target="${keyName}" rel="${tempfilee.manageKey}">
					<td align="center">
					<label class="checkbox">
					<input name="${keyName }" type="checkbox" value=""/><i></i>
					</label>
					<input name="${keyName }" type="hidden" value="${tempfilee.manageKey}"/><i></i>
					</td>
					<td>${tempfilee.id}</td>
					<td>
					<a href="${basePath}filee/downloadFile.do?key=${tempfilee.manageKey}" target="_blank" title="点击下载${tempfilee.name}">
					${tempfilee.name}
					</a>
					</td>
					<td>${tempfilee.extensionName}</td>
					<td><fmt:formatDate value="${tempfilee.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${tempfilee.folder.folderName}</td>
					<%-- <td>${tempfilee.manageKey}</td> --%>
	
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
