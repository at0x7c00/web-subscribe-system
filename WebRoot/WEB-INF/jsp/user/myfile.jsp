<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<div class="row">

	<div class="col-sm-12">


		<div class="well well-sm">

			<div class="row">

				<div class="col-sm-12 col-md-12 col-lg-6">
					<div class="well well-light well-sm no-margin no-padding">

						<div class="row">

							<div class="col-sm-12">
								<div id="myCarousel" class="carousel fade profile-carousel">
									<div class="air air-bottom-right padding-10">
										<!-- <a href="javascript:void(0);" class="btn txt-color-white bg-color-teal btn-sm"><i class="fa fa-check"></i> Follow</a>&nbsp; 
											<a href="javascript:void(0);" class="btn txt-color-white bg-color-pinkDark btn-sm"><i class="fa fa-link"></i> Connect</a> -->
									</div>
									<div class="air air-top-left padding-10">
										<h4 class="txt-color-white font-md" id="dateh">Jan 1,
											2014</h4>
									</div>
									<ol class="carousel-indicators">
										<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
										<li data-target="#myCarousel" data-slide-to="1" class=""></li>
										<li data-target="#myCarousel" data-slide-to="2" class=""></li>
									</ol>
									<div class="carousel-inner">
										<!-- Slide 1 -->
										<div class="item active">
											<img src="${basePath}img/demo/s1.jpg" alt="">
										</div>
										<!-- Slide 2 -->
										<div class="item">
											<img src="${basePath}img/demo/s2.jpg" alt="">
										</div>
										<!-- Slide 3 -->
										<div class="item">
											<img src="${basePath}img/demo/m3.jpg" alt="">
										</div>
									</div>
								</div>
							</div>

							<div class="col-sm-12">

								<div class="row">

									<div class="col-sm-2 profile-pic">
										
										<div class="padding-5"></div>
									</div> 
									<div class="col-sm-6">
										<h1>
											${sessionScope['LOGIN_INFO_IN_SESSION'].user.username}  <span class="semi-bold"></span> <br> <small>
												用户名</small>
										</h1>

										<ul class="list-unstyled">
											<!-- <li>
												<p class="text-muted">
													<i class="fa fa-calendar"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">域显示名 <a
														href="javascript:void(0);" rel="tooltip" title=""
														data-placement="top"
														data-original-title="Create an Appointment">hong</a>
													</span>
												</p></li> -->
											<li>
												<p class="text-muted">
													<i class="fa fa-skype"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">${sessionScope['LOGIN_INFO_IN_SESSION'].user.chineseName} </span>
												</p></li>
											<li>
												<p class="text-muted">
													<i class="fa fa-calendar"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">部门<a
														href="javascript:void(0);"
														data-original-title="Create an Appointment">${sessionScope['LOGIN_INFO_IN_SESSION'].user.dept.name}</a>
													</span>
												</p></li>
											<li>
												<p class="text-muted">
													<i class="fa fa-phone"></i>&nbsp;&nbsp;${sessionScope['LOGIN_INFO_IN_SESSION'].user.phone}
												</p></li>
											<li>
												<p class="text-muted">
													<i class="fa fa-calendar"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">状态 <a
														href="javascript:void(0);" 
														data-original-title="Create an Appointment">${sessionScope['LOGIN_INFO_IN_SESSION'].user.status.description}</a>
													</span>
												</p></li>

											<li>
												<p class="text-muted">
													<i class="fa fa-envelope"></i>&nbsp;&nbsp;<a
														href="mailto:simmons@smartadmin">${sessionScope['LOGIN_INFO_IN_SESSION'].user.email}</a>
												</p></li>


										</ul>
										<!-- <a href="javascript:void(0);" class="btn btn-default btn-xs"><i class="fa fa-envelope-o"></i> Send Message</a> -->
										<br> <br>

									</div>
									<div class="col-sm-3"></div>

								</div>

							</div>

						</div>
					</div>
		
	
		<section id="widget-grid" class="">
		<div class="row">
			<article class="col-sm-12 ">
				<div class="jarviswidget" id="wid-id-4"
					data-widget-editbutton="false" 
					data-widget-colorbutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false" 
					data-widget-togglebutton="false"
					data-widget-collapsed="false">
					<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
				-->
					<header>
						<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
						<h2>修改密码</h2>

					</header>

					<!-- widget div-->
					<div>

						<!-- widget edit box -->
						<div class="jarviswidget-editbox">
							<!-- This area used as dropdown edit box -->

						</div>
						<!-- end widget edit box -->

						<!-- widget content -->
						<div class="widget-body no-padding">

							<form action="user/updatePassword.do" method="post" id="smart-form-register" class="smart-form" onsubmit="return md5pwd() && validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
								<fieldset>
<input type="hidden" name="targetPanel" value="${targetPanel}"/>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-lock"></i> <input type="password"
											name="oldPassword" placeholder="原始密码"
											id="oldPassword"> 
											
											<input id="encOldPassFromPage" name="encOldPassFromPage" type="hidden"/>
											 </label>
									</section>

									<section>
										<label class="input"> <i
											class="icon-append fa fa-lock"></i> 
											<input id="encNewPassFromPage" name="encNewPassFromPage" type="hidden"/>
											<input type="password"
											name="password" placeholder="新密码" id="password">
											<b class="tooltip tooltip-bottom-right">Don't forget your
												password</b> </label>
									</section>

									<section>
										<label class="input"> <i
											class="icon-append fa fa-lock"></i> <input type="password"
											name="passwordConfirm" placeholder="再次输入新密码">
											<b class="tooltip tooltip-bottom-right">Don't forget your
												password</b> </label>
									</section>
								</fieldset>


								<footer>
									<button type="submit" class="btn btn-primary">修改密码</button>
								</footer>
							</form>

						</div>
						<!-- end widget content -->

					</div>
					<!-- end widget div -->

				</div>


			</article>


		</div>
</section>
	</div>
			</div>
			</div>
		</div>
		</div>
	<!-- end row -->

<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>
<!-- end widget grid -->

<script type="text/javascript">
	// DO NOT REMOVE : GLOBAL FUNCTIONS!

	var myDate = new Date();

	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	var date = myDate.getDate();
	var dateString = year + '年' + month + '月' + date + '日';

	//$("h4.txt-color-white font-md").text(dateString);

	var ss = $("#dateh").text(dateString);

	// PAGE RELATED SCRIPTS
	var $registerForm = $("#smart-form-register").validate({

		// Rules for form validation
		rules : {
			oldpassword : {
				required : true
			},
			password : {
				required : true,
				minlength : 1,
				maxlength : 20
			},
			passwordConfirm : {
				required : true,
				minlength : 1,
				maxlength : 20,
				equalTo : '#password'
			},

		},

		// Messages for form validation
		messages : {
			login : {
				required : 'Please enter your login'
			},
			email : {
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address'
			},
			password : {
				required : 'Please enter your password'
			},
			passwordConfirm : {
				required : 'Please enter your password one more time',
				equalTo : 'Please enter the same password as above'
			},
			
			gender : {
				required : 'Please select your gender'
			},
			terms : {
				required : 'You must agree with Terms and Conditions'
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	function md5pwd(){
				var yournewpwd = $('#password').val();
				yournewpwd = $.md5(yournewpwd);
				$('#encNewPassFromPage').val(yournewpwd);//加密新密码
				var oldPwd = $('#oldPassword').val();
				oldPwd = $.md5(oldPwd);
				oldPwd = $.md5(oldPwd+" ");
				$('#encOldPassFromPage').val(oldPwd);
				return true;
			};
	
</script>
	
		<script src="${basePath}/js/jquery/jquery.md5.js"></script>
