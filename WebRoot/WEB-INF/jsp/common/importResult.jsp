<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<form method="post" action="${path}/import.do" class="smart-form required-validate" 
				onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
				<input type="hidden" name="targetPanel" value="${targetPanel}"/>
				<input type="hidden" name="path" value="${path }"/>
				<input type="hidden" name="refreshList" value="yes"/>
				<div class="widget-body">
					<p>
						 	<c:choose>
								<c:when test="${not empty importError}">
									<p class="alert alert-warning" style="margin-bottom:0px;">
										<i class="fa fa-warning fa-fw fa-lg"></i><strong>保存到数据库时遇到错误：</strong> <br/>
										<n:exceptionPrinter throwable="${importError}"></n:exceptionPrinter>	
									</p>
								</c:when>
								<c:otherwise>
									<p class="alert alert-success" style="margin-bottom:0px;">
										导入成功，共导入${successCount}条数据！										
									</p>
								</c:otherwise>
							</c:choose>
					</p>
					
				</div>
				<footer>
					<button  type="submit" class="btn btn-primary smart-form-submit-btn">
						刷新列表
					</button>
					<button type="button" class="btn btn-default btn-cancel" data-targetpanel = "${targetPanel}">
						<spring:message code="base.common.cancel"></spring:message>
					</button>
				</footer>
			</form>
		</article>
	</div>
</section>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>