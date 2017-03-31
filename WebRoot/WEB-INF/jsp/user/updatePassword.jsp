<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<div class="pageContent">
		<form id="signupForm" method="post" action="user/updatePassword.do" class="pageForm required-validate" onsubmit="return md5pwd() && validateCallback(this, navTabAjaxDone);">
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="base.common.save"/></button></div></div>
					</li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="base.common.cancel"/></button></button></div></div>
					</li>
				</ul>
			</div>
			<div class="pageFormContent" layoutH="55">
				<input name="rel" type="hidden" value="${rel}">
				<p class="nowrap">
					<label style="width:200px;"><spring:message code="funcs.User.updatepwd.oldPwd"/></label>
					<input type="hidden" id="encOldPassFromPage" name="encOldPassFromPage"/>
					<input id="oldPassword" type="password" class="required"/><span style="color: red"></span>
				</p>
				<p class="nowrap">
					<label style="width:200px;"><spring:message code="funcs.User.updatepwd.newPwd"/></label>
					<input type="hidden" id="encNewPassFromPage" name="encNewPassFromPage"/>
					<input id="password" type="password" class="required"  minlength="6"/><span style="color: red"></span>
				</p>
				<p class="nowrap">
					<label style="width:200px;"><spring:message code="funcs.User.updatepwd.confirmnewPwd"/></label>
					<input id="confirm_password" type="password" class="required" equalto="#password"/><span style="color: red"></span>
				</p>
			</div>
			
		</form>
		<script type="text/javascript">
			$().ready(function() {
			 $("#signupForm").validate({
			    errorElement: "span",
			      rules: {
						   oldPassword:{
							     required: true
						   },
						   password: {
							     required: true,
						         minlength: 6
						   },
						  /*  confirm_password: {
						    	required: true,
						   		equalTo: "#password"
						   } */
					   },
					   messages: {
						   oldPassword:{
							   required:"请输入原密码"
						   },
						   password: {
							   required:"请输入新密码",
						       minlength: jQuery.format("密码不能小于{0}个字符")
						   },
						  /*  confirm_password: {
						    	required: "请输入确认密码",
						    	equalTo: "两次输入密码不一致不一致"
						   } */
					  }
				});
			});
			function callback(){
				alert("原密码输入错误");
			}
			function md5pwd(){
				var yournewpwd = $('#password').val();
				yournewpwd = $.md5(yournewpwd);
				$('#encNewPassFromPage').val(yournewpwd);//加密新密码
				var oldPwd = $('#oldPassword').val();
				oldPwd = $.md5(oldPwd);
				oldPwd = $.md5(oldPwd+" ");
				$('#encOldPassFromPage').val(oldPwd);
				return true;
			}
		</script>
</div>
