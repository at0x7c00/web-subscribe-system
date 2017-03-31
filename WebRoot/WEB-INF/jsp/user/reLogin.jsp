<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="pageContent">
	<form method="post" action="reLogin.do" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="base.common.cancel"/></button></button></div></div></li>
			</ul>
		</div>
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label><spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>：</label>
				<input type="text" name="loginName" size="30" class="required"/>
			</div>
			<div class="unit">
				<label><spring:message code="props.me.huqiao.wss.sys.entity.User.password"></spring:message>：</label>
				<input type="password" name="password" size="30" class="required"/>
			</div>
		</div>
		
	</form>
	
</div>
