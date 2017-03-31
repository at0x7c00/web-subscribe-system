<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<div class="pageContent">
	<form:form method="post" action="filee/update.do"
		cssClass="pageForm required-validate" commandName="filee"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" value="${rel}" name="rel" />
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span><spring:message code="base.common.save"/></span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								<spring:message code="base.common.save" />
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								<spring:message code="base.common.cancel" />
							</button>
							
						</div>
					</div></li>
			</ul>
		</div>
		<div class="pageFormContent" layoutH="56">
			<form:input type="hidden" path="id" value="${filee.id}" />
			<p>
				<label><spring:message code="props.me.huqiao.wss.sys.entity.Filee.name"/>：</label>
				<form:input path="name" id="filee.name" class="textInput required"
					maxlength="100" />
			</p>
			<p>
				<label>存储：</label>
				<form:input path="storeName" id="filee.storeName" class="textInput"
					maxlength="100" />
			</p>
			<p>
				<label><spring:message code="props.me.huqiao.wss.sys.entity.Filee.extensionName"></spring:message>：</label>
				<form:input path="extensionName" id="filee.extensionName"
					class="textInput" maxlength="20" />
			</p>
			<p>
				<label><spring:message code="props.me.huqiao.wss.sys.entity.Filee.createDate"></spring:message>：</label><input name="createDate" id="filee.createDate"
					onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});"
					value="<fmt:formatDate pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}" value="${createDateStart}"/>"
					class="date_not_required textInput valid" />
			</p>
			<p>
				<label><spring:message code="props.me.huqiao.wss.sys.entity.Filee.folder"/>：</label>
				<form:select id="folder.id" path="folder"
					cssClass="comboxed required ">
					<option value="">
					
						<spring:message code="base.common.selectone" />
						
					</option>
					<form:options items="${commonFolderList}" itemValue="id"
						itemLabel="folderName" cssClass="comboxed" />
				</form:select>
				&nbsp;<font color="red">*</font>
			</p>
			
			
		</div>

	</form:form>
</div>
