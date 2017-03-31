<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>用户登录-${systemTitle}</title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">	
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="${basePath}js/fuckIE/html5shiv.min.js"></script>
		  <script src="${basePath}js/fuckIE/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">	
		
		<!-- SmartAdmin RTL Support is under construction
			<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.css"> -->
		
		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/demo.css">

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="img/favicon/${ficon}.ico" type="image/x-icon">
		<link rel="icon" href="img/favicon/${ficon}.ico" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="googlefonts/css.css">

	</head>
	<body id="login" class="animated fadeInDown">
	
	<!--[if lt IE 9]>
		<div style="background:gray;color:white;text-align:center;height:30px;line-height:30px;">您的浏览器已经过时了，为获得最佳使用效果，建议您使用<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank" style="color:yellow;">IE9</a>及以上、<a href="http://rj.baidu.com/soft/detail/14744.html?ald" style="color:yellow;" target="_blank">Chrome</a>或<a href="http://www.firefox.com.cn/" targete="_blank" style="color:yellow;">火狐浏览器</a>访问本系统
		</div>
	<![endif]-->
	
		<!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
		<header id="header">
			<!--<span id="logo"></span>-->

			<div id="logo-group">
			
				<span id="logo" style="width:300px;font-family:'Microsoft yahei'">
				<%-- <img src="img/logo-pale.png" alt="logo"> --%>
				<h1>${systemTitle } - 后台管理系统</h1>
				 </span>
				 

				<!-- END AJAX-DROPDOWN -->
			</div>
<%--
			<span id="login-header-space"> <span class="hidden-mobile">Need an account?</span> <a href="register.html" class="btn btn-danger">Creat account</a> </span>
 --%>

		</header>

		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			<div id="content" class="container">

				<div class="row login_form_row">
					    <%--
					<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
						<h1 class="txt-color-red login-header-big">${systemTitle}</h1>
					 --%>
<%--
						<div class="hero">

							<div class="pull-left login-desc-box-l">
								<h4 class="paragraph-header">It's Okay to be Smart. Experience the simplicity of SmartAdmin, everywhere you go!</h4>
								<div class="login-app-icons">
									<a href="javascript:void(0);" class="btn btn-danger btn-sm">Frontend Template</a>
									<a href="javascript:void(0);" class="btn btn-danger btn-sm">Find out more</a>
								</div>
							</div>
							
							<img src="img/demo/iphoneview.png" class="pull-right display-image" alt="" style="width:210px">

						</div>
 --%>

						<%--
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<h5 class="about-heading">About SmartAdmin - Are you up to date?</h5>
								<p>
									Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa.
								</p>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
								<h5 class="about-heading">Not just your average template!</h5>
								<p>
									Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi voluptatem accusantium!
								</p>
							</div>
						</div>

					</div>
						 --%>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 login_form">
						<div class="well no-padding">
							<form action="login.do" id="login-form" class="smart-form client-form" method="POST">
								<header>
									<spring:message code="base.common.login.dialogtitle"></spring:message>
								</header>


								<fieldset>
									<section>
										<label class="label"><spring:message code="base.common.login.account"></spring:message></label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="loginName">
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 请输入用户登录账号</b></label>
									</section>

									<section>
										<label class="label"><spring:message code="base.common.login.pwd"></spring:message></label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="password">
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请输入您的登录密码</b> </label>
										<div class="note">
											
										</div>
									</section>
									
									<section class="col col-6"  style="padding-left:0px;">
											<label class="label"><spring:message code="base.common.login.checkcode"></spring:message></label>
											<label class="input"> <i class="icon-append fa  fa-check-square-o"></i>
												<input type="text" name="checkcode" value=""/>
												<b class="tooltip tooltip-top-right"><i class="fa  fa-check-square-o txt-color-teal"></i> 请输入验证码</b> </label>
									</section>
									
									<section class="col col-6" style="padding-left:10px;padding-top:23px;height:59px;">
										<label>
										<img src="" width="150" height="32" id="verifyCodeImage" style="cursor: pointer;" title="点击切换图片"/>
										</label>
									</section>
			<%--
									<section style="clear:both;">
										<label class="checkbox">
											<input type="checkbox" name="remember" checked="">
											<i></i><spring:message code="base.common.login.staysingedin"></spring:message></label>
									</section>
			 --%>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										<spring:message code="base.common.login.btn.login"/>
									</button>
								</footer>
							</form>

						</div>
						
						<%--
						<h5 class="text-center"> - Or sign in using -</h5>
															
										<ul class="list-inline text-center">
											<li>
												<a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
											</li>
											<li>
												<a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
											</li>
											<li>
												<a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
											</li>
										</ul>
						 --%>
						 
						 <ul class="list-inline text-center">
						 	<li>&copy;2017 ${copyRight}
						 	</li>
						 </ul>
						
					</div>
				</div>
			</div>

		</div>

		<!--================================================== -->	

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script src="js/plugin/pace/pace.min.js"></script>

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="js/jquery/1.11.3/jquery.min.js"></script>
	    <script src="js/jqueryui/1.10.3/jquery-ui.min.js"></script>
	    
		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="js/smartwidgets/jarvis.widget.min.js"></script>
		
		<!-- EASY PIE CHARTS -->
		<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
		
		<!-- SPARKLINES -->
		<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>
		
		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>
		
		<!-- JQUERY SELECT2 INPUT -->
		<script src="js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
		
		<!-- browser msie issue fix -->
		<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>
		
		<!-- FastClick: For mobile devices -->
		<script src="js/plugin/fastclick/fastclick.js"></script>
		
		<!-- MAIN APP JS FILE -->
		<script src="js/app.js"></script>

		<script type="text/javascript">
			runAllForms();

			$(function() {
				// Validation
				$("#login-form").validate({
					// Rules for form validation
					rules : {
						loginName : {
							required : true,
						},
						password : {
							required : true,
							minlength : 1,
							maxlength : 20
						},
						checkcode : {
							required : true,
						}
					},

					// Messages for form validation
					messages : {
						loginName : {
							required : '请输入登录账号',
						},
						password : {
							required : '请输入密码'
						},
						checkcode : {
							required : '请输入验证码'
						}
						
					},

					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					}
				});
				
				$("#verifyCodeImage").click(loadVerifyCode);
		  		loadVerifyCode();
		  		
		  		var errorMsg = '${passwordError}';
		  		if(errorMsg!=''){
		  			$.bigBox({
		  				title : '提示',
		  				content :errorMsg,
		  				color : "#C46A69",
		  				//timeout: 6000,
		  				icon : "fa fa-warning shake animated",
		  				//number : "1",
		  			});
		  		}
			});
			
			function loadVerifyCode(){
		  		$("#verifyCodeImage").attr("src","${basePath}verifyimage.create?t="+new Date().getTime());
		  	}
			
		</script>

	</body>
</html>