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

						<form:form action="role/add.do" cssClass="smart-form" commandName="role" onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
							<input type="hidden" name="targetPanel" value="${targetPanel }"/>
							<fieldset>
								<div class="row">
									<section class="col col-6">
										<label class="input">
											<spring:message code="props.me.huqiao.wss.sys.entity.Role.name"/>: <font color="red">*</font>
											<form:input path="name" id="role.name" class="textInput required nameFormat" maxlength="255"></form:input>
										</label>
									</section>
									
									<section class="col col-6">
											<label class="input"> 
													<label class="select">
													类别：<font color="red">*</font>
													<form:select id="type" path="type" cssClass="required comboxed">
													<option value="">-请选择-</option>
														<form:options  items="${roleTypeMap}"/>
													</form:select>
													<i style="top:27px;"></i>
													</label>
									</label>
									</section>
									
								</div>
								<div class="row">
									<section class="col col-12" style="width:100%;">
										<label class="input">
										<spring:message code="props.me.huqiao.wss.sys.entity.Role.description"/>:
										<form:textarea path="description"  rows="3" class="textInput" style="width:100%;"/>
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
