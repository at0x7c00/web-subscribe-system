<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta data-vue-meta="true" vmid="keywords" name="keywords" content="IT快报,快报,Java,IT资讯,Vue.js,微信小程序,Kotlin,RxJava,React Native,Wireshark,敏捷开发,Bootstrap,OKHttp,正则表达式,WebGL,Webpack,Docker,MVVM"/>
		<meta data-vue-meta="true" vmid="description" name="description" content="IT快报致力于传递IT行业最新动态资讯，把握计算机行业发展趋势，搜集整理编程干货。为中国程序员打造第一资讯类社区服务。IT最新资讯您只需要看这里！"/>
		

		<title> ${not empty task ? task.name:'' }${not empty task ? '-':'' }IT快报-大事小事尽知道! </title>
		<meta name="description" content="">
		<meta name="author" content="">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/bootstrap.min.css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="${basePath}js/fuckIE/html5shiv.min.js"></script>
		  <script src="${basePath}js/fuckIE/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/font-awesome.min.css">
		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/smartadmin-production.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/smartadmin-skins.css">

		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/demo.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}css/core.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}js/toastr/toastr.min.css">

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="${basePath}img/favicon/${ficon}.ico" type="image/x-icon">
		<link rel="icon" href="${basePath}img/favicon/${ficon}.ico" type="image/x-icon">

	</head>
	
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
			
			<div class="header">
				<div style="max-width:960px;margin:auto auto;">
					<div class="row" style="height:70px;line-height:70px;">
						<!-- col -->
						<div class="col-xs-12 col-sm-3 col-md-4 col-lg-4">
							<img src="img/logo.png" alt="logo">
						</div>
						<!-- end col -->
					</div>
				</div>
			</div>
			
			<div class="divide-wrap" style="">
				<div class="colorbar-before"></div>
				<div class="colorbar-after"></div>
				<div class="colorbar"></div>
			</div>
							
			<!-- MAIN CONTENT -->
			<div id="content" style="max-width:960px;margin:auto auto;">

				<!-- row -->
				<div class="row">
				
					<div class="col-sm-12">
				
						<div class="well">
				
							<c:choose>
							<c:when test="${not empty task }">
								<div style="padding:15px 0px;">
										<a href="../f/index.do" target="_self">
										首页 
										</a>
										
										<c:choose>
											<c:when test="${empty all }">
												&gt; 
												<a href="../f/index.do?all=t${not empty task ? '&tag=':''}${task.manageKey}">
												 全部
												</a> &gt; 精选
											</c:when>
											<c:otherwise>
												 &gt; 全部
												 &gt; 
												<a href="../f/index.do${not empty task ? '?tag=':''}${task.manageKey}">
												 精选
												</a>
											</c:otherwise>
										</c:choose>
										
										&gt; ${task.name} 
										&nbsp;（共${pageBean.totalCount }篇文章）
								</div>
							</c:when>
							<c:otherwise>
								<div style="padding:15px 0px;">
									<c:choose>
										<c:when test="${empty all }">
											<a href="../f/index.do?all=t"> 全部
											</a>
											 &gt;
											精选
										</c:when>
										<c:otherwise>
											全部 &gt;
											<a href="../f/index.do"> 
											精选
											</a>
										</c:otherwise>
									</c:choose>
									&nbsp;（共${pageBean.totalCount }篇文章）
								</div>
							</c:otherwise>
							</c:choose>
							
				
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
							<img alt="" src="../img/loading.gif"/>  <span style="padding:0px 15px;color:#ddd;">加载中...</span>
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
		
		
		
		<div class="back-to-top" title="返回顶部"> 
			<i class="fa fa-arrow-up"></i>
		</div>
		<!-- END MAIN PANEL -->

		<!-- PAGE FOOTER -->
		<div class="page-footer">
			<div class="row">
				<div class="col-xs-12 col-sm-12 text-center">
					<div class="txt-color-white inline-block">
						<i class="txt-color-blueLight ">
						©2017 itkuaibao.cn
						京ICP备15056042号-2
						<br/>
						<br/>
						 </i>
					</div>
				</div>
			</div>
		</div>
		<!-- END PAGE FOOTER -->

		<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
		Note: These tiles are completely responsive,
		you can add as many as you like
		-->
		<!-- END SHORTCUT AREA -->

		<!-- END SHORTCUT AREA -->
		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="${basePath}js/jquery/1.11.1/jquery.min.js"></script>
		<script src="${basePath}js/toastr/toastr.min.js"></script>
		<script type="text/javascript">
		    var loadInt = null;
			$(function(){
					$(window).bind("scroll",function() {
						if($(window).scrollTop()<50) {  
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
					});
					
					$(".back-to-top").click(function(){
						$(document.body).animate({scrollTop:0},300);
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
			});
			function getNewPage(){
				var pageNum = $("#pageNum").val();
				console.log("p="+pageNum+",t="+$("#totalPageNum").val())
				if(parseInt(pageNum)>=parseInt($("#totalPageNum").val())){
					showNoRecord();
					return;
				}
				 displayLodading();
				$.post("../f/index.do?ajax=true&pageNum=" + pageNum,$("#post-form").serializeArray(),function(d){
					$(d).insertAfter($("#post-table tbody tr:last-child"));
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
			function fav(k,ele){
				var favourite = $(ele).hasClass("fa-heart") ? 'No':'Yes';
				$.get("../gatherResult/mark.do?favourite="+ favourite +"&manageKey=" + k,function(d){
					if(d.statusCode!=200){
						toastr['error'](d.message);
					}else{
						if($(ele).hasClass("fa-heart-o")){
							$(ele).removeClass("fa-heart-o").addClass("fa-heart");
						}else{
							$(ele).addClass("fa-heart-o").removeClass("fa-heart");
						}
					}
				});
			}
			function showNoRecord(){
				$("#no-record-div").show();
			}
			function scoreAdd(k){
				$.get("../gatherResult/score/good.do?manageKey=" + k,function(d){
					if(d=='already'){
						toastr['info']("不能重复评价！");
					}else{
						$("#score_" + k).html(d);
					}
				});
			}
			function scoreDelete(k){
				$.get("../gatherResult/score/bad.do?manageKey=" + k,function(d){
					if(d=='already'){
						toastr['info']("不能重复评价！");
					}else{
						$("#score_" + k).html(d);
					}
				});
			}
			
		</script>
	</body>

</html>
