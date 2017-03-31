<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- include command -->

<fieldset>
	<div class="row">
		<section class="col col-2">
			<label class="input"> 
			<form:input path="username" id="user.username" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.sys.entity.User.username') }" ></form:input>
			</label>
		</section>
		
		<section class="col col-2">
		<label class="input"> 
		<form:input path="userNameQuery" id="user.userNameQuery" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.sys.entity.User.name') }" cssClass="textInput" maxlength="255" size="12" />
		</label>
			<%-- <label> 
				<spring:message code="props.me.huqiao.wss.sys.entity.User.name" />：
			</label>
			<form:input path="userNameQuery" id="user.userNameQuery" cssClass="textInput" maxlength="255" size="12" /> --%>
		</section>
		
		<section class="col col-2">
			<label class="select"> 
				<form:select id="status" path="status" cssClass="comboxed">
				<option value="" style="">${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.sys.entity.User.status') }</option>
				<form:options items="${yesNoMap}"  />
			</form:select> 
			<i></i>
			</label> 
		</section>
		
		<section class="col col-2">
				<a class="btn btn-primary smart-form-submit-btn" href="#" style="padding:5px 15px;"><i class="fa fa-search"></i> <spring:message code="base.function.query"/></a>
		</section>
	</div>
</fieldset>