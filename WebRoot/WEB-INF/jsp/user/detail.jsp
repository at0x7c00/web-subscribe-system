<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="pageContent">
	<form:form method="post" action="user/update.do"
		class="smart-form required-validate" commandName="user"
		onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
		<input type="hidden" name="targetPanel" value="${targetPanel}"/>
		<fieldset>
			<div class="row">
				<section class="col col-3">
					<label class="input">
					<font style="font-size:13px" >
						<spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>： 
					</font>
						${user.username }	
					</label>
				
				</section>
				
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.chineseName"></spring:message>：
						${user.chineseName }
					</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.email"></spring:message>：
						${user.email }
					</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.phone"></spring:message>：
						${user.phone }
					</label>
				</section>
				
				
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.status"></spring:message>：
						${userStatusMap[user.status]}
					</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.dept"/>:
						${user.dept.name}
				</label>
				</section>
				
			
			</div>
		</fieldset>
		
		<footer>
			<button type="button" class="btn btn-default btn-cancel" data-targetpanel = "${targetPanel}">
				<spring:message code="base.common.ok"></spring:message>
			</button>
		</footer>
	</form:form>
	
</div>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>
<script type="text/javascript">
<!--
$(function(){
	var targetPanel = '${targetPanel}';
	var panel = targetPanel == 'dialog'  ? NUI.container : $("#dialogBody");
	$(".smart-form",panel).validate({
		// Rules for form validation
		rules : {
			username : {
				required : true,
				
			},
			password : {
				required : true,
				minlength : 1,
				maxlength : 20
			}
		},

		// Messages for form validation
		messages : {
			username : {
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address xxx'
			},
			password : {
				required : 'Please enter your password'
			}
		},

		
	});
	
});
//-->
</script>
