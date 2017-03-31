<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="pageContent">
	<form:form method="post" action="user/add.do"
		class="smart-form required-validate" commandName="user"
		onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
		<input type="hidden" name="targetPanel" value="${targetPanel}"/>
		<fieldset>
			<div class="row">
				<%@include file="/WEB-INF/jsp/user/add-form.jsp" %>
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
	
<div id="empInfo" style="display:none"
	class="popover  editable-container editable-popup fade top in">
	<div class="arrow" style="left: 49.8355263157895%;"></div>
	<h3 class="popover-title">添加用户</h3>
	<div class="popover-content">
		<div>
			<div class="editableform-loading" style="display: none;"></div>
			<form id="userInfoForm"  class="form-inline editableform" action="user/add.do" style="" method="post">
				<div class="control-group">
					<div>
						<div class="editable-input">
							<div class="editable-address">

								<label> <span>用户名</span> <input type="text"
									name="username" class="input-small"> </label>
							</div>
							<div class="editable-address">
								<label> <span>密码：</span> <input type="text"
									name="password" class="input-small"> </label>
							</div>
							<div class="editable-address">
								<label> <span>邮箱：</span> <input type="text"
									class="input-small" name="email"> </label>
							</div>
							<div class="editable-address">
								<label> <span>电话：</span> <input type="text"
									class="input-small" name="telephone"> </label>
							</div>
						</div>
						<div class="editable-buttons">
							<button type="submit" id="userFormSub"
								class="btn btn-primary btn-sm ">
								<i class="glyphicon glyphicon-ok"></i>
							</button>
							<button type="button"
								class="btn btn-default btn-sm editable-cancel ">
								<i class="glyphicon glyphicon-remove"></i>
							</button>
						</div>
					</div>
					
				</div>

			</form>
		</div>
	</div>
</div>
</div>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>
