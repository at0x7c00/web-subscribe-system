<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@include file="/WEB-INF/jsp/f/common/header-rs.jsp" %>
	
	<body class="">
		<!-- MAIN PANEL -->
		<div id="main" role="main" style="margin-left:0px;">
			<form action="${basePath }f/index.do" method="POST" id="post-form">
			<input type="hidden" id="pageNum" name="pageNum" value="${pageBean.pageNum}"/>
			<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
			<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
			<input type="hidden" name="all" value="${all}"/>
			<input type="hidden" name="tag" value="${task.manageKey}"/>
			<input type="hidden" id="totalPageNum" name="totalPageNum" value="${pageBean.countPage}"/>
			
			<%@include file="/WEB-INF/jsp/f/common/header.jsp" %>
							
			<!-- MAIN CONTENT -->
			<div id="content" style="max-width:960px;margin:auto auto;">

				<!-- row -->
				<div class="row">
				
					<div class="col-sm-12">
				
						<div class="well">
				
							<h1>${chapter.title }</h1>
							by ${chapter.creator.chineseName } at <fmt:formatDate value="${chapter.createTime}" pattern="yyyy-MM-dd"/>
							<hr/>
							<div class="chapter-content" style="font-size:18px;padding:0px 25px;">
							<n:html value="${chapter.content}"/>
							<hr/>
							<c:if test="${not empty chapter.origin }">
							原文地址：<a href="${chapter.origin}" target="_blank">${chapter.origin }</a>
							</c:if>
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
