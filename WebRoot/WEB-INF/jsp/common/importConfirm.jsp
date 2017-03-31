<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<form method="post" action="${path}/import.do" class="smart-form required-validate" 
				onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
				<input type="hidden" name="targetPanel" value="${targetPanel}"/>
				<input type="hidden" name="dataModel" value="${dataModel}"/>
				<input type="hidden" name="doImport" value="yes"/>
				<input type="hidden" name="path" value="${path }"/>
				<div class="widget-body">
					<p>
					Excel文件内总共<b>${validateResultPackage.rowCount - 1}</b>行数据，发现<b>${recordCount}</b>条主记录。
						 	验证成功：<b>${validateResultPackage.validateSuccessCount }</b>，
						 	验证失败：<b>${validateResultPackage.validateFailedCount }</b>，
						 	重复数据：<b>${validateResultPackage.repeatRowCount }</b>
						 	<br/>
						 	<c:choose>
								<c:when test="${not empty checkRepeatProps}">
									 	选择的查重字段：
									 	<c:forEach items="${checkRepeatProps }" var="checkRepeatProp">
									 		<c:forEach items="${props}" var="prop">
									 			<c:if test="${not prop.set and prop.name eq checkRepeatProp }">
									 				<spring:message code="${prop.description}"/>
									 			</c:if>
									 		</c:forEach>
									 	</c:forEach>
								</c:when>
								<c:otherwise>
								
								<p class="alert alert-warning" style="margin-bottom:0px;">
									<i class="fa fa-warning fa-fw fa-lg"></i><strong>注意：</strong> 
									未选择查重字段，系统将不会进行查重操作!
								</p>
								</c:otherwise>
							</c:choose>
					</p>
					
					<fieldset style="padding-top:5px;">
						<div class="row">
							<c:choose>
								<c:when test="${onlyFailed }">
						 			仅显示了验证失败的信息，点击这里<a href="${basePath}${path}/import.do?viewResult=yes&onlyFailed=false&dataModel=${dataModel}" target="dialogTodo" title="导入${entityName}">查看全部验证记录</a>
								</c:when>
								<c:otherwise>
						 			显示了全部验证信息，点击这里<a href="${basePath}${path}/import.do?viewResult=yes&onlyFailed=true&dataModel=${dataModel}" target="dialogTodo" title="导入${entityName}">仅显示验证失败记录</a>
								</c:otherwise>
							</c:choose>
						</div>
					</fieldset>
					
					<div class="table-responsive" style="height:400px;overflow:auto;">
							<table class="table">
								<thead>
									<tr>
										<th> <i class="class="fa  fa-reorder""></i> 行号</th>
										<th> <i class="fa fa-building"></i> 字段名称</th>
										<th> <i class="fa fa-calendar"></i> Excel值</th>
										<th> <i class="fa  fa-check-circle"></i> 验证结果</th>
										<th> <i class="fa fa-info-circle"></i> 备注</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach begin="2" end="${validateResultPackage.rowCount}" var="rowIndex">
										<c:set var="rowValidateResult" value="${validateResultPackage.validateResultListMap[rowIndex] }"/>
										<c:if test="${(not onlyFailed) or (not rowValidateResult.validateSuccess)}">
										<c:forEach items="${rowValidateResult.validateResultList }" var="validateResult">
											<c:if test="${(not onlyFailed) or (not validateResult.validateSuccess)}">
												<tr class="${validateResult.cssClass}">
													<td>${validateResult.rowNum }</td>
													<td>
														<c:choose>
															<c:when test="${not empty validateResult.prop18nCode }">
															<c:choose>
																<c:when test="${fn:endsWith(validateResult.prop18nCode,'.id')}">
																U位ID
																</c:when>
																<c:otherwise>
																<spring:message code="${validateResult.prop18nCode}"/>
																</c:otherwise>
															</c:choose>
															
															</c:when>
															<c:otherwise>
															[重复或整体性验证]
															</c:otherwise>
														</c:choose>
													</td>
													<td>
													<c:out value="${validateResult.strValue}"/>
													</td>
													<td>
													${validateResult.validateResultDesc}
													</td>
													<td>
													${validateResult.description }
													</td>
												</tr>
											</c:if>
										</c:forEach>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				
				
				<footer>
					<c:if test="${not empty validateResultPackage }">
					<button warn="确认要导入这些数据吗？" title="${validateResultPackage.validateSuccessWhenIgnoreRepeat and fn:length(dataListForImport)>0 ? '点击确认导入' : '还有问题未解决，不能导入'}" type="submit" class="btn btn-primary smart-form-submit-btn ${validateResultPackage.validateSuccessWhenIgnoreRepeat and fn:length(dataListForImport)>0 ? '' : 'disabled'}">
						确认导入(${validateResultPackage.validateSuccessCount}条数据)
					</button>
					</c:if>
					<button type="button" class="btn btn-default btn-cancel" data-targetpanel = "${targetPanel}">
						<spring:message code="base.common.cancel"></spring:message>
					</button>
				</footer>
			</form>
		</article>
	</div>
</section>
<%@include  file="/WEB-INF/jsp/common/pageSetJS.jsp"%>