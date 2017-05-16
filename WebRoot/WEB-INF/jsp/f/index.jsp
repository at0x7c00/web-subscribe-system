<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta data-vue-meta="true" vmid="keywords" name="keywords" content="IT快报,快报,Java,IT资讯,Vue.js,微信小程序,Kotlin,RxJava,React Native,Wireshark,敏捷开发,Bootstrap,OKHttp,正则表达式,WebGL,Webpack,Docker,MVVM"/>
		<meta data-vue-meta="true" vmid="description" name="description" content="IT快报致力于传递IT行业最新动态资讯，把握计算机行业发展趋势，搜集整理编程干货。为中国程序员打造第一资讯类社区服务。IT最新资讯您只需要看这里！"/>
		

		<title> IT快报-大事小事尽知道! </title>
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

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="${basePath}img/favicon/${ficon}.ico" type="image/x-icon">
		<link rel="icon" href="${basePath}img/favicon/${ficon}.ico" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="${basePath}css/googlefonts/css.css">
		
		<link rel="stylesheet" type="text/css" media="screen" href="${basePath}js/zTree/theme/zTreeStyle/style.css">

		<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
		<link rel="apple-touch-icon" href="${basePath}img/splash/sptouch-icon-iphone.png">
		<link rel="apple-touch-icon" sizes="76x76" href="${basePath}img/splash/touch-icon-ipad.png">
		<link rel="apple-touch-icon" sizes="120x120" href="${basePath}img/splash/touch-icon-iphone-retina.png">
		<link rel="apple-touch-icon" sizes="152x152" href="${basePath}img/splash/touch-icon-ipad-retina.png">
		
		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		
		<!-- Startup image for web apps -->
		<link rel="apple-touch-startup-image" href="${basePath}img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
		<link rel="apple-touch-startup-image" href="${basePath}img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
		<link rel="apple-touch-startup-image" href="${basePath}img/splash/iphone.png" media="screen and (max-device-width: 320px)">
		<link href="${basePath}js/plugin/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet"/>
		<link href="${basePath}js/plugin/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet"/>
		<link href="${basePath}js/plugin/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet"/>
		<link href="${basePath}js/plugin/My97DatePicker/skin/WdatePicker.css" rel="stylesheet"/>
	    <link rel="stylesheet" href="${basePath}css/node.css">
	    
	   <style type="text/css">
		.table-forum tr td h4{
			font-size:18px;
		}
		</style>

	</head>
	
	<body class="">
		<!-- MAIN PANEL -->
		<div id="main" role="main" style="margin-left:0px;">
			<form action="${basePath }f/index.do" method="POST">
			<input type="hidden" name="pageNum" value="${pageBean.pageNum}"/>
			<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
			<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
			<input type="hidden" name="all" value="${all}"/>
							
			<!-- MAIN CONTENT -->
			<div id="content">

				<!-- Bread crumb is created dynamically -->
				<!-- row -->
				<div class="row" style="height:70px;line-height:70px;">
					<!-- col -->
					<div class="col-xs-12 col-sm-3 col-md-4 col-lg-4">
						<img src="img/logo.png" alt="SmartAdmin">
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
				
				<!-- row -->
				<div class="row">
				
					<div class="col-sm-12">
				
						<div class="well">
				
							<table class="table table-striped table-forum">
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
											<td colspan="4">
											 暂无记录
											</td>
										</tr>
									</c:if>
									<c:forEach items="${pageBean.list }" var="c">
										<tr>
											<td colspan="2">
												<h4><a href="${basePath}f/v.do?k=${c.manageKey}" target="_blank">
													${c.title }
												</a>
													<small><em><n:shorthand length="30" content="${c.accessUrl }"></n:shorthand></em></small>
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
												<a href="${c.task.url}" target="_blank">
												<span class="info">${c.task.name}</span>
												</a>
												<br>
												<small><i><fmt:formatDate value="${c.createTime}" pattern="MM-dd HH:mm"/></i></small>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
				
							<div class="text-center">
				                <%@include file="/WEB-INF/jsp/f/common/pageBar.jsp" %>
				            </div>
				
						</div>
					</div>
				
				</div>
				
				<!-- end row -->

			</div>
			<!-- END MAIN CONTENT -->
			</form>
		</div>
		<!-- END MAIN PANEL -->

		<!-- PAGE FOOTER -->
		<div class="page-footer">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
				</div>

				<div class="col-xs-6 col-sm-6 text-right">
					<div class="txt-color-white inline-block">
						<i class="txt-color-blueLight ">
						© 2017 itkuaibao.cn
						京ICP备15056042号-2
						&nbsp;&nbsp;&nbsp;&nbsp;
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
		<div id="shortcut">
			<ul>
				<li>
					<a href="inbox.html" class="jarvismetro-tile big-cubes bg-color-blue"> <span class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail <span class="label pull-right bg-color-darken">14</span></span> </span> </a>
				</li>
				<li>
					<a href="calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span> </span> </a>
				</li>
				<li>
					<a href="gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple"> <span class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span> </span> </a>
				</li>
				<li>
					<a href="invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
				</li>
				<li>
					<a href="gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
				</li>
				<li>
					<a href="profile.html" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
				</li>
			</ul>
		</div>
		<!-- END SHORTCUT AREA -->

		<!-- END SHORTCUT AREA -->
		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="${basePath}js/jquery/1.11.1/jquery.min.js"></script>
		<script src="${basePath}js/jquery/jquery.md5.js"></script>
		
		
		
		<script src="${basePath}js/jqueryui/1.10.3/jquery-ui.min.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->
		<script src="${basePath}js/bootstrap/bootstrap.min.js"></script>
		
		
		<!-- CUSTOM NOTIFICATION -->
		<script src="${basePath}js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="${basePath}js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="${basePath}js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="${basePath}js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="${basePath}js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="${basePath}js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="${basePath}js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="${basePath}js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="${basePath}js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices: you can disable this in app.js -->
		<script src="${basePath}js/plugin/fastclick/fastclick.js"></script>
		
		<script src="${basePath}js/ckeditor/ckeditor.js" type="text/javascript"></script>
		<script src="${basePath}js/ckeditor/adapters/jquery.js" type="text/javascript"></script>
		

		<!--[if IE 7]>

		<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

		<![endif]-->
		
		<script type="text/javascript" src="${basePath}js/nui/nui.modal.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.i18n.zh.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.alertMsg.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.ajax.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.datatable.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.pagination.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.select-list.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.tabform.js"></script> 
		<script type="text/javascript" src="${basePath}js/nui/nui.unit-select-table.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.select2.js"></script>
		<script type="text/javascript" src="${basePath}js/nui/nui.js"></script>

		<%--
		<script src="${basePath}js/migrate/jquery-migrate-1.2.1.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.cust.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.resize.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.pie.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.orderBar.min.js"></script>
        <script type="text/javascript" src="${basePath}js/plugin/flot/jquery.flot.tooltip.js"></script>
        --%>
        <script type="text/javascript" src="${basePath}js/zTree/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript" src="${basePath}js/nui/nui.ztreeselect.js"></script>

		<!-- BEGIN:File Upload Plugin JS files-->
		<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
		<!-- The Templates plugin is included to render the upload/download listings -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/vendor/tmpl.min.js"></script>
		<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/vendor/load-image.min.js"></script>
		<!-- The Canvas to Blob plugin is included for image resizing functionality -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/vendor/canvas-to-blob.min.js"></script>
		<!-- blueimp Gallery script -->
		<script src="${basePath}js/plugin/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
		<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.iframe-transport.js"></script>
		<!-- The basic File Upload plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload.js"></script>
		<!-- The File Upload processing plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-process.js"></script>
		<!-- The File Upload image preview & resize plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-image.js"></script>
		<!-- The File Upload audio preview plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-audio.js"></script>
		<!-- The File Upload video preview plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-video.js"></script>
		<!-- The File Upload validation plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
		<!-- The File Upload user interface plugin -->
		<script src="${basePath}js/plugin/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
		
		<script src="${basePath}js/plugin/dropzone/dropzone.min.js"></script>
		<script src="${basePath}js/node.js"></script>
		 
		<script src="${basePath}js/plugin/My97DatePicker/calendar.js"></script>
		<script src="${basePath}js/plugin/My97DatePicker/WdatePicker.js"></script>
		<!-- Demo purpose only -->
		<script src="${basePath}js/demo.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="${basePath}js/app.js"></script>
		<script src="${basePath}js/jquery/jquery.cookie.js"></script>

		<script type="text/javascript">
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			pageSetUp();

		})

		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
			var _gaq = _gaq || [];
				_gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
				_gaq.push(['_trackPageview']);
			
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();
			function PageBreak(data){
				$("form").first().submit();
			}
			$(function(){
				$(".pagination li a").click(function(){
					if($(this).parent().hasClass("disabled")) return;
					var pageValue = $(this).attr("data-page-value");
					$("input[name=pageNum]").val(pageValue);
					$("form").first().submit();
				});
			});
		</script>

	</body>

</html>
