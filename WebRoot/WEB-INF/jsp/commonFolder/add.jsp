<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="pageContent">
	<form:form method="post" action="commonFolder/add.do"
		class="smart-form required-validate" commandName="commonFolder"
		onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
		<input type="hidden" name="targetPanel" value="${targetPanel}"/>
		<fieldset>
			<div class="row">
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.CommonFolder.folderName"></spring:message>： 
						<form:input path="folderName" id="folderName" class="textInput required" maxlength="255" />
					</label>
				</section>
				
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.CommonFolder.storePath"></spring:message>：
						<form:input path="storePath" id="storePath" class="textInput required" maxlength="255" />
					</label>
				</section>
			</div>
		</fieldset>
		
		<footer>
			<button type="submit" class="btn btn-primary smart-form-submit-btn">
				<spring:message code="base.common.ok"></spring:message>
			</button>
			<button type="button" class="btn btn-default btn-cancel" data-targetpanel ="${targetPanel}">
				<spring:message code="base.common.cancel"></spring:message>
			</button>
		</footer>
	</form:form>
	
</div>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>
