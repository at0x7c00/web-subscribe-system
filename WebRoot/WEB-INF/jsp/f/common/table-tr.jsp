<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:forEach items="${pageBean.list }" var="c">
	<tr>
		<td colspan="2">
			<h4><a href="${basePath}f/v.do?k=${c.manageKey}" target="_blank">
				${c.title }
			</a>
				<small>
				<n:pv url="gatherResult/markxx.do">
				<i class="fa fa-heart${c.favourite eq 'Yes'?'':'-o' } fav" onclick="fav('${c.manageKey}',this);"></i>
				</n:pv>
				<em>
				<n:shorthand length="30" content="${c.accessUrl }"></n:shorthand>
				&nbsp;
				&nbsp;
				</em>
				</small>
			</h4>
		</td>
		<td class="text-center hidden-xs hidden-sm">
		<%--
			<a href="javascript:void(0);">431</a>
		 --%>
		</td>
		<td class="text-center hidden-xs hidden-sm">
		<%--
			<a href="javascript:void(0);">1342</a>
		 --%>
		</td>
		<td class="hidden-xs hidden-sm">
		by 
			<a href="../f/index.do?tag=${c.task.manageKey}${not empty all ? '&all=':''}${all}" target="_blank">
			<span class="info">${c.task.name}</span>
			</a>
			<br>
			<small><i><fmt:formatDate value="${c.createTime}" pattern="MM-dd HH:mm"/></i></small>
		</td>
	</tr>
</c:forEach>