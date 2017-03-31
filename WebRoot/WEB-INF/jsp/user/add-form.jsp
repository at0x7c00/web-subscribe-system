<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- include command here... -->
<section class="col col-3">
	<label class="input" style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;"> <spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>：<font color="red">*</font> <form:input path="username" id="username" class="textInput required usernameFormat" maxlength="25" />
	</label>
</section>

<section class="col col-3">
	<label class="input" style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;"> <spring:message code="props.me.huqiao.wss.sys.entity.User.chineseName"></spring:message>：<font color="red">*</font> <form:input path="chineseName" id="user.chineseName" class="textInput required" maxlength="25" />
	</label>
</section>

<section class="col col-3">
	<label class="input"> <spring:message code="base.common.login.pwd" />：<font color="red">*</font> <input type="hidden" id="isFixPass" name="isFixPass" value="true" /> <form:password path="textpwd" class="textInput required" maxlength="100" disabled="disabled" />
	</label>
</section>
<section class="col col-3">
	<label class="input" style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;"> <spring:message code="props.me.huqiao.wss.sys.entity.User.email"></spring:message>：<font color="red">*</font> <form:input path="email" id="email" class="textInput required email" maxlength="25" />
	</label>
</section>
<section class="col col-3">
	<label class="input" style="font:13px/16px 'Open Sans', Helvetica, Arial, sans-serif;"> <spring:message code="props.me.huqiao.wss.sys.entity.User.phone"></spring:message>：<font color="red">*</font> <form:input path="phone" id="phone" class="textInput required phone" maxlength="25" />
	</label>
</section>
<section class="col col-3">
	<label class="input"> <spring:message code="props.me.huqiao.wss.sys.entity.User.dept" />: <label class="select"> <form:select id="dept" path="dept" cssClass=" comboxed">
				<option value=""><spring:message code="base.common.selectone" /></option>
				<form:options items="${departmentList}" itemValue="manageKey" itemLabel="name" />
			</form:select><i></i>
	</label>
	</label>
</section>

<section class="col col-3">
	<label> <spring:message code="props.me.huqiao.wss.sys.entity.User.status"></spring:message>:
	</label> <label class="select"> <form:select path="status" items="${userStatusMap}" id="user_status" useremployee="checkEmp()"></form:select> <i></i>
	</label>
</section>



<script>
	function showinfo() {
		$("#empInfo").attr("style", "top:-38px;left:50%; display:block;");
		$("#empInfo").show();
	}
	$(".btn.btn-default.btn-sm.editable-cancel").click(function() {
		$("#empInfo").slideUp("normal");
	});
</script>