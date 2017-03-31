<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="pageContent">
	<form:form method="post" action="user/sendEmailForAddUser.do"
		class="pageForm required-validate" commandName="user"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" value="${rel}" name="rel" />
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span><spring:message code="base.common.save"/></span></a></li>-->
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								<spring:message code="base.common.save"/>
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								<spring:message code="base.common.cancel"/></button>
							
						</div>
					</div>
				</li>
			</ul>
		</div>
				<div class="pageFormContent" layoutH="56">
			<p>
				<label>
					<spring:message code="props.me.huqiao.wss.sys.entity.User.email"></spring:message>：
				</label>
				<form:input path="username" id="user.username" class="textInput required email" maxlength="25" />
			</p>

			<p>
				<label>
				<spring:message code="props.me.huqiao.wss.sys.entity.User.company"></spring:message>：
				</label>
				<select class="form-control required" name="company" id="company">
                   <option value=""><spring:message code="base.common.selectone"/></option>
                   <c:forEach items="${companyList}" var="company">
                   <option value="${company.id}" ${user.company.id==company.id?'selected':''}>${isChina ? company.chineseName : company.englishName}</option>
                   </c:forEach>
            	</select>
				<font color="red">*</font>
			</p>
			
		</div>
	</form:form>
</div>

