<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:forEach items="${pageBean.list }" var="c">
	<tr>
		<td colspan="2">
			<div class="co">
				<div onclick="scoreAdd('${c.manageKey}')" class="score add"><i class="fa  fa-caret-up"></i></div>
				<div class="count" id="score_${c.manageKey}">${c.score}</div>
				<div onclick="scoreDelete('${c.manageKey}')" class="score delete"><i class="fa  fa-caret-down"></i></div>
			</div>
			<h4 class="b-title">
				<a href="${basePath}f/v.do?k=${c.manageKey}" target="_blank">
					${c.title}
				</a>
				<small>
				<n:pv url="gatherResult/mark.do">
				<i class="fa fa-heart${c.favourite eq 'Yes'?'':'-o' } fav" onclick="fav('${c.manageKey}',this);"></i>
				</n:pv>
				
				<a href="../f/index.do?tag=${c.task.manageKey}${not empty all ? '&all=':''}${all}" target="_blank">
				<span class="info">${c.task.name}</span>
				</a>
				<i><fmt:formatDate value="${c.createTime}" pattern="MM-dd HH:mm"/></i>
				
				
				<em>
				<n:shorthand length="30" content="${c.accessUrl }"></n:shorthand>
				&nbsp;
				&nbsp;
				</em>
				</small>
			</h4>
		</td>
		<td class="text-center hidden-xs hidden-sm" style="width:150px;">
				<div style="width:150px;height:60px;">
				
				 <div class="big-mark">
					 <fmt:formatDate value="${c.createTime}" pattern="dd"/>
				 </div>
				 <div class="middle-mark">
				 	 <fmt:formatDate value="${c.createTime}" pattern="M月"/>
				 </div>
				</div>
			<%--
			by 
				<a href="../f/index.do?tag=${c.task.manageKey}${not empty all ? '&all=':''}${all}" target="_blank">
				<span class="info">${c.task.name}</span>
				</a>
				<br>
				<small><i><fmt:formatDate value="${c.createTime}" pattern="MM-dd HH:mm"/></i></small>
			 --%>
		</td>
	</tr>
</c:forEach>