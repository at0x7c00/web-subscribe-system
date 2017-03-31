<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
					<div class="tab-pane fade in active" id="baseProperties">	
						<fieldset>
							<div class="row">
								 	<section class="col col-12 p1-required">
										<label class="input">
											<b style="color:red;">${error}</b>
										</label>
									</section>
							</div>
						</fieldset>
					</div>
				<footer>
					<button type="button" class="btn btn-primary btn-cancel pull-right" data-targetpanel = "${targetPanel}">
						<spring:message code="base.common.ok"></spring:message>
					</button>
				</footer>
		</article>
	</div>
</section>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>