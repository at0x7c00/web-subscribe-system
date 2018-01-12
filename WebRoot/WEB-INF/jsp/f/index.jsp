<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@include file="/WEB-INF/jsp/f/common/header-rs.jsp" %>
	
	<body class="">
		<!-- MAIN PANEL -->
		<div id="main" role="main" style="margin-left:0px;">
			<form action="${basePath }index.do" method="POST" id="post-form">
			<input type="hidden" id="pageNum" name="pageNum" value="${pageBean.pageNum}"/>
			<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
			<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
			<input type="hidden" name="scope" value="${scope}"/>
			<input type="hidden" name="tag" value="${tag.code}"/>
			<input type="hidden" name="taskKey" value="${task.manageKey}"/>
			<input type="hidden" id="totalPageNum" name="totalPageNum" value="${pageBean.countPage}"/>
			
			<%@include file="/WEB-INF/jsp/f/common/header.jsp" %>
			
			<%@include file="/WEB-INF/jsp/f/common/tags.jsp" %>
			
			<!-- MAIN CONTENT -->
			<div id="content" style="max-width:960px;margin:auto auto;position: relative;">
				<!-- row -->
				<div class="row">
				
					<div class="col-sm-12">
				
						<div class="well">
				
							<%@include file="/WEB-INF/jsp/f/common/nav.jsp" %>
							
				
							<table class="table table-striped table-forum" id="post-table">
							<%--
								<thead>
									<tr>
										<th colspan="2"><a href="forum.html"> Projects </a> > Business Requirement Docs</th>
										<th class="text-center hidden-xs hidden-sm" style="width: 100px;">Topics</th>
										<th class="text-center hidden-xs hidden-sm" style="width: 100px;">Posts</th>
										<th class="hidden-xs hidden-sm" style="width: 200px;">Last Post</th>
									</tr>
								</thead>
							 --%>
								<tbody>
								
									<c:if test="${empty pageBean.list }">
										<tr>
											<td colspan="3">
											 暂无记录
											</td>
										</tr>
									</c:if>
									<%@include file="/WEB-INF/jsp/f/common/table-tr.jsp" %>
								</tbody>
							</table>
							
							
							<div style="display: none;text-align: center;padding:20px;" id="loading">
							<img alt="" src="${basePath}img/loading.gif"/>  <span style="padding:0px 15px;color:#ddd;">加载中...</span>
							</div>
				
				
							<div id="no-record-div">
								没有更多数据了.
							</div>
				
							
						</div>
					</div>
				
				</div>
				
				<!-- end row -->

			</div>
			
			<!-- END MAIN CONTENT -->
			</form>
		</div>
		
		
		<%@include file="/WEB-INF/jsp/f/common/footer.jsp" %>
		<%@include file="/WEB-INF/jsp/f/common/footer-rs.jsp" %>
	</body>

</html>
