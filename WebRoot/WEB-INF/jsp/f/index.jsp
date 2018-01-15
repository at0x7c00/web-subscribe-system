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
		<script type="text/javascript">
	    var loadInt = null;
		$(function(){
				var scrollTop = $(window).scrollTop();
				menuDisplay(scrollTop);
				$(window).bind("scroll",function() {
					aaa();
				});
				
				toastr.options = {
				  "closeButton": false,
				  "debug": false,
				  "newestOnTop": false,
				  "progressBar": false,
				  "positionClass": "toast-top-right",
				  "preventDuplicates": true,
				  "onclick": null,
				  "showDuration": "300",
				  "hideDuration": "1000",
				  "timeOut": "3000",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				};
				
				initUI($(document));
		});
		
		function aaa(){
			var windowWidth = $(window).width();
			var wellWidth = $(".well").width();
			var scrollTop = $(window).scrollTop();
			if(scrollTop<50) {  
	            $('.back-to-top').fadeOut(300)  
	        }else {  
	            $('.back-to-top').fadeIn(300)  
	        }  
			
		   if($(window).scrollTop() + $(window).height() == $(document).height()) {
			   if(loadInt!=null){
				   window.clearTimeout(loadInt);
				   loadInt = null;
			   }
			   loadInt =  window.setTimeout(function(){
				   getNewPage();
			   },100);
		   }
		   menuDisplay(scrollTop);
		}
		function menuDisplay(s){
				if(s>50){
				     $(".task-list").addClass("out");
				}else{
					 $(".task-list").removeClass("out");
				}
		}
		function getNewPage(){
			var pageNum = parseInt($("#pageNum").val())+1;
			//console.log("p="+pageNum+",t="+$("#totalPageNum").val())
			if(parseInt(pageNum)>parseInt($("#totalPageNum").val())){
				showNoRecord();
				return;
			}
			 displayLodading();
			 var params = '';
			 var data = $("#post-form").serializeArray();
			 for(var k in data){
				 var p = data[k];
				 if(p.name == 'pageNum'){
					 continue;
				 }
				 params += "&" + p.name+"=" + p.value;
			 }
			 url = "${basePath}index.do?ajax=true&pageNum=" + pageNum + params;
			$.get(url,function(d){
				//console.log(d)
				var content = $(d);
				content.insertAfter($("#post-table tbody tr:last-child"));
				initUI(content);
				$("#pageNum").val(parseInt($("#pageNum").val())+1);
				hideLoading();
			});
		}
		function displayLodading(){
			$("#loading").show();
		}
		function hideLoading(){
			$("#loading").hide();
		}
		function showNoRecord(){
			$("#no-record-div").show();
		}
		</script>
	</body>

</html>
