<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<script type="text/javascript">
		var basePath = '${basePath}';
		</script>
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title> ${systemTitle}</title>
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
	</head>
	<body class="smart-style-3">
		
		<!-- POSSIBLE CLASSES: minified, fixed-ribbon, fixed-header, fixed-width
			 You can also add different skin classes such as "smart-skin-1", "smart-skin-2" etc...-->
		
		<!-- HEADER -->
		<header id="header">
			<div id="logo-group" style="width:400px;">

				<!-- PLACE YOUR LOGO HERE -->
				<%--
				<span id="logo"> <img src="${basePath}img/logo-pale.png" alt="SmartAdmin"> </span>
				 --%>
				<!-- END LOGO PLACEHOLDER -->
				
				<h1 style="color:#058dc7;">&nbsp;&nbsp;<i class="fa  fa-th-large" ></i>&nbsp;${systemTitle }-后台管理系统</h1>
			</div>

			<!-- 右上角工具栏 -->
			<div class="pull-right">

				<!-- 展开收缩按钮 -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="切换菜单"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- 展开收缩按钮 -->

				<!-- 退出按钮 -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="${basePath}loginOut.do" title="退出系统" data-logout-msg="确定要退出系统吗？"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- 退出按钮 -->

				<!-- 手机搜索按钮 -->
				<div id="search-mobile" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
				</div>
				<!-- 手机搜索按钮 -->

				<!-- 搜索输入框 -->
				<!-- <form action="#ajax/search.html" class="header-search pull-right">
					<input type="text" name="param" placeholder="Find reports and more" id="search-fld">
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
					<a href="javascript:void(0);" id="cancel-search-js" title="Cancel Search"><i class="fa fa-times"></i></a>
				</form>
				 -->
				<!-- 搜索输入框 -->

				<!-- 全屏按钮 -->
				<div id="fullscreen" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0);" onclick="launchFullscreen(document.documentElement);" title="全屏"><i class="fa fa-fullscreen"></i></a> </span>
				</div>
				<!-- 全屏按钮 -->

				<!-- 多语言切换
				<ul class="header-dropdown-list hidden-xs">
					<li>
						<c:choose>
							<c:when test="${(locale.displayLanguage eq '中文') or (locale.displayLanguage eq 'Chinese')}">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" > <img alt="" src="${basePath}img/flags/zh.png"> <span> 中文 </span> <i class="fa fa-angle-down"></i> </a>
							</c:when>
							<c:otherwise>
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" > <img alt="" src="${basePath}img/flags/us.png"> <span> English </span> <i class="fa fa-angle-down"></i> </a>
							</c:otherwise>
						</c:choose>
					
						<ul class="dropdown-menu pull-right">
							<li class="${((locale.displayLanguage eq '中文') or (locale.displayLanguage eq 'Chinese')) ? 'active':''}">
								<a href="javascript:void(0);" locale="zh_CN" class='localebar' title="切换到中文版"><img alt="" src="${basePath}img/flags/zh.png"> 中文 </a>
							</li>
							<li class="${(not(locale.displayLanguage eq '中文') or (locale.displayLanguage eq 'Chinese')) ? 'active':''}">
								<a href="javascript:void(0);" locale="en_US" class='localebar' title="Swith to English version"><img alt="" src="${basePath}img/flags/us.png"> English</a>
							</li>
						</ul>
					</li>
				</ul> -->
				<!-- 多语言切换 -->
			</div>
			<!-- 右上角工具栏 -->

		</header>
		<!-- END HEADER -->

		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel">

			<!--用户信息 -->
			<div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
					
					<a href="javascript:void(0);" id="show-shortcut">
					
						<img src="${basePath}img/avatars/male.png" alt="me" class="online" /> 
						<span>
							${LOGIN_INFO_IN_SESSION.user.username }
						</span>
						<i class="fa fa-angle-down"></i>
					</a> 
				</span>
				 
			</div>

			<nav>
				<ul>
				<%--
					<li class="">
						<a href="${basePath}monthSummary/list.do" title="首页"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">首页</span></a>
					</li>
				 --%>
					<c:forEach var="top" items="${sessionScope['LOGIN_INFO_IN_SESSION'].topFunctionPoints}" varStatus="s1">
						<c:if test="${top.isDisplay==1}">
							<li>
								<a href="#"><i class="fa fa-lg fa-fw ${empty top.icon ? 'fa-table':top.icon }"></i> <span class="menu-item-parent"><spring:message code="${top.i18nCode}"/></span></a>
								<ul>
									<c:forEach var="second" items="${top.children}" varStatus="status">
										<c:if test="${second.isDisplay==1}">
											<li><a href="${second.url}${second.relName}nav_tab_${second.id}" data-children="${second.childrenUrls}"><spring:message code="${second.i18nCode}"/></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</nav>
			<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>

		</aside>
		<!-- END NAVIGATION -->

		<!-- MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

<%--
 
				<span class="ribbon-button-alignment"> 
				<span id="refresh" class="btn btn-ribbon" data-title="refresh"  
				rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." 
				data-html="true" 
				data-reset-msg="Would you like to RESET all your saved widgets and clear LocalStorage?">
				<i class="fa fa-refresh"></i>
				</span>
				</span>--%>

				<!-- breadcrumb -->
				<ol class="breadcrumb">
				</ol>
				<!-- end breadcrumb -->
			</div>
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

			</div>
			<!-- END MAIN CONTENT -->
			
			<!--[if lt IE 9]>
			<div style="background:gray;color:white;text-align:center;height:30px;line-height:30px;">您的浏览器已经过时了，为获得最佳使用效果，建议您使用<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank" style="color:yellow;">IE9</a>及以上、<a href="http://rj.baidu.com/soft/detail/14744.html?ald" style="color:yellow;" target="_blank">Chrome</a>或<a href="http://www.firefox.com.cn/" targete="_blank" style="color:yellow;">火狐浏览器</a>访问本系统
			</div>
		<![endif]-->

		</div>
		<!-- END MAIN PANEL -->

		<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
		Note: These tiles are completely responsive,
		you can add as many as you like
		-->
		<div id="shortcut">
			<ul>
				<li>
					<a href="#user/myfile.do" class="jarvismetro-tile big-cubes bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span><spring:message code="funcs.user.info"></spring:message> </span> </span> </a>
				</li>
			</ul>
		</div>
		<div class="shortcut" id="showcut1">
			
		</div>
		<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" 
  		 data-keyboard="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="closeBtn" style="">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body" id="dialogBody">
		
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
		
		<div id="dialog-message" title="Dialog Simple Title">
			<p id="dialog-message-body">
				
			</p>
		</div>
		
		<div id="dialog-ajaxloading" title="提示">
			<p id="dialog-ajaxloading-message-body">
				
			</p>
		</div>
		
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
			$('.modal-dialog').draggable({
	            handle: ".modal-header"
	        });
	        $(function(){
	         try{
	          if($.cookie('selectNode')==null){
	          
	          
	          }
	         
	         }catch(err){
	         
	         }
	        
	        });
	        
	        function selectNodeInCookie(){
	        try{
	       
	        
	        }catch(err){
	        
	        
	        }
	        }
		</script>
		<style type="text/css">
		.modal-header{
			cursor: move;
			padding:8px;
			background : #fafafa;
		}
		.modal-body{
			padding-bottom:5px;
		}
		em.invalid{
			display:block;
			color:#A90329;
			font-style: normal;
		}
		input.required {
		/* 	background : url(img/input_bg.png) no-repeat scroll; */
			background-position:100% 0;
		}
		.selected-tr td{
			/*background :red;*/
		}
		/*.table-striped>tbody>tr:nth-child(odd)>td{background-color:#f9f9f9}*/
		.modal-header .close{
			font-size:28px;
		}
		.smart-form .input{
		font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;
		}
		.select2-drop .clearfix .col-sm-3{
			width : 100%;
		}
		.object-select{
			height:32px;
		}
		.select2-results{
			padding : 3px 0px 4px;
		}
		.form-menu .btn{
			padding:5px 15px;
			margin:1px 0px;
		}
		.tab-form-menu{
			height:40px;
		}
		.tab-form-menu a{
			padding:5px 10px;margin:5px 1px;
		}
		.right-side-bar{
			border:1px solid red;
			position:absolute;
			right:0px;
			top:100px;
			width:800px;
			height:600px;
		}
		.change-markup{
			color:red;
			border-bottom: 1px dashed red;
		}
		.ui-dialog{
			border-radius : 0px;
		}
		.ui-widget-header,.ui-dialog-title{
			font-weight:100 !important;
		}
		.dropzone{
			min-height: 180px;
		}
		</style>
	</body>

</html>
