<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<form:form method="post" action="${tempBean.ciType.url}/start.do"
				class="smart-form required-validate" commandName="${tempBean.ciType.url}"
				onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
				<input type="hidden" name="targetPanel" value="${targetPanel}" />
					<input type="hidden" name="manageKey" value="${tempBean.manageKey}"/>
					<input type="hidden" name="changeFlag" id="changeFlag">
				<div id="myTabContent1" class="tab-content">
					<div class="tab-pane fade in active" id="baseProperties">
						<fieldset>
							<div class="row">
								<%-- <section class="col col-3">
									<label class="input"> <spring:message
											code="props.me.huqiao.wss.asset.manage.physicaldevice.entity.PhysicalDevice.stopReason" />:
										<textarea name="stopReason" id="stopReason" cols="60" rows="5"></textarea>
									</label>
								</section> --%>
								<p><spring:message code="funcs.PhysicalDevice.start.confirm.title"></spring:message>?</p>
								<br>
							</div>
						</fieldset>
					</div>
				</div>
				<footer>
					<button type="submit" class="btn btn-success smart-form-submit-btn" onclick="setFlag()">
						<spring:message code="funcs.PhysicalDevice.yes"></spring:message>
					</button>
					<button type="submit" class="btn btn-primary smart-form-submit-btn">
						<spring:message code="funcs.PhysicalDevice.no"></spring:message>
					</button>
					<button type="button" class="btn btn-default btn-cancel"
						data-targetpanel="${targetPanel}">
						<spring:message code="base.common.cancel"></spring:message>
					</button>
				</footer>
			</form:form>
		</article>
	</div>
</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp"%>