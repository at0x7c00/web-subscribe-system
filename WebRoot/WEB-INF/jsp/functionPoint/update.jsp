<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>


<section id="widget-grid" class="">
	<!-- START ROW -->
	<div class="row">
		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">
				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">

						<form:form action="functionPoint/update.do" cssClass="smart-form" commandName="functionPoint" onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
							<input type="hidden" name="targetPanel" value="${targetPanel }"/>
							<input type="hidden" name="id" value="${functionPoint.id }"/>
							<fieldset>
								<div class="row">
								
									<section class="col col-3">
										<label class="input">
											<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.name"></spring:message>： 
											<form:input path="name" id="functionPoint.name" class="textInput required" maxlength="255"></form:input>
										</label>
									</section>
									
									
									<section class="col col-3">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.url"/>:
										<form:input path="url" id="functionPoint.url" class="textInput required" maxlength="255" />
										</label>
									</section>
									
									<section class="col col-3">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.icon"/>:
										<form:input path="icon" id="functionPoint.icon" class="textInput" maxlength="255" />
										</label>
									</section>
				
									<section class="col col-3">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.isDisplay"/>:
										<form:input path="isDisplay" id="functionPoint.isDisplay" type="text" class="textInput digits" />
										</label>
									</section>
									
									<section class="col col-3">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.orderNum"/>:
										<form:input path="orderNum" id="functionPoint.orderNum" type="text" class="textInput digits" />
										</label>
									</section>
									
									
									<section class="col col-3">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.FunctionPoint.parent"/>：
										<select id="parent.id" name="parent" class="comboxed">
											<option value="">
												<spring:message code="base.common.selectone"/>
											</option>
											<c:forEach items="${functionPointList}" var="point">
												<option value="${point.id}" ${functionPoint.parent.id==point.id?'selected':'' }>${point.name}</option>
											</c:forEach>
										</select>
										</label>
									</section>
								</div>
							</fieldset>

							<footer>
								<button type="submit" class="btn btn-primary  smart-form-submit-btn">
									<spring:message code="base.common.save"/>
								</button>
								<button type="button" class="btn btn-default  btn-cancel" data-targetpanel = "${targetPanel}">
									<spring:message code="base.common.cancel"/>
								</button>
							</footer>
						</form:form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			<!-- end widget -->

		</article>
		<!-- END COL -->

	</div>

	<!-- END ROW -->
</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>
