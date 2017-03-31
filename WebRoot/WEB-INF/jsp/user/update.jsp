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
						<spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>： 
						<input type="hidden" name="userName" value="${user.username}">
						${user.username}
					</label>
				</section>
				
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.chineseName"></spring:message>：<font color="red">*</font>
						<form:input path="chineseName" id="user.chineseName" class="textInput required" maxlength="25" />
					</label>
				</section>
				
				<section class="col col-3">
					<label class="input">
						<spring:message code="base.common.login.pwd"/>：<font color="red">*</font>
						<input type="hidden" id="isFixPass" name="isFixPass" value="true"/>
						<form:password path="textpwd" 	class="textInput " maxlength="100" disabled="disabled" />
					</label>
				</section>
				<section class="col col-3">
					<label class="input"
						style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;">
						<spring:message
							code="props.me.huqiao.wss.sys.entity.User.email"></spring:message>：<font color="red">*</font>
						<form:input path="email" id="email"
							class="textInput required email" maxlength="25" /> </label>
				</section>
				<section class="col col-3">
					<label class="input"
						style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;">
						<spring:message
							code="props.me.huqiao.wss.sys.entity.User.phone"></spring:message>：<font color="red">*</font>
						<form:input path="phone" id="phone"
							class="textInput required phone" maxlength="25" /> </label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.User.dept"/>：
										<label class="select">
										<form:select id="dept" path="dept" cssClass=" comboxed">
										<option value=""><spring:message code="base.common.selectone"/></option>
											<form:options  items="${departmentList}" itemValue="manageKey" itemLabel="name"/>
										</form:select><i></i>
										</label>
				</label>
				</section>
				
				
				<section class="col col-3">
				<label><spring:message code="props.me.huqiao.wss.sys.entity.User.status"></spring:message>：</label>
					<label class="select">
						<form:select path="status"  items="${userStatusMap}" id="user_status" useremployee="checkEmp()" cssClass="required" ></form:select>
					<i></i>
					</label>
					
					
				</section>
				
			</div>
		</fieldset>
		
		<footer>
			<button type="submit" class="btn btn-primary smart-form-submit-btn">
				<spring:message code="base.common.ok"></spring:message>
			</button>
			<button type="button" class="btn btn-default btn-cancel" data-targetpanel = "${targetPanel}">
				<spring:message code="base.common.cancel"></spring:message>
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
