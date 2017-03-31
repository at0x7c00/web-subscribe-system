<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<form method="post" action="${path}/import.do" class="smart-form required-validate" 
				onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
				<input type="hidden" name="targetPanel" value="${targetPanel}"/>
				<input type="hidden" name="dataModel" value="${dataModel}"/>
				<input type="hidden" name="from" value="${path}"/>
				<div id="attachments">
					<div style="padding:5px">
						<a class="btn btn-primary attachement-dialog-add-btn"
						   href="javascript:void(0)" style="padding:5px 15px;" 
						   data-selectlist="attachmentsSelectList" 
						   data-targetpanel="${targetPanel}"
						   data-maxfilesize="100"
						   data-acceptedfiles=".xls,.xlsx"
						   >
							<i class="fa fa-plus"></i> <spring:message code="base.function.add"/>文件
						</a>
					
						<a class="btn btn-danger select-list-delete-btn"
						   href="javascript:void(0)" style="padding:5px 15px;" data-selectlist="attachmentsSelectList">
							<i class="fa fa-trash-o"></i> <spring:message code="base.function.delete"/>
						</a>
						
					</div>
					<div class="dataTables_wrapper select-list" id="attachmentsSelectList" style="border-bottom:1px solid #ddd;" 
						 data-dataurl="filee/selectList.do"
						 data-groupname="fileKeys"
						 data-entityidname="manageKey"
						 data-method="POST"
						 data-initvalues=""
						 >
					</div>
				</div>
				
				<br/>
				选择查重字段(不选择表示不查重)：
				<hr/>
				<fieldset>
					<div class="row">
						<c:forEach items="${props}" var="prop">
								<c:if test="${not prop.set}">
								<section class="col col-3">
									<label class="checkbox">
										<input type="checkbox" name="checkRepeatProps" value="${prop.accessName}"/><spring:message code="${prop.description}"></spring:message>
										<i></i>
									</label>
								</section>
								</c:if>
						</c:forEach>
					</div>
				</fieldset>
				
				点击这里<a href="${basePath}${path}/templateExport.do?dataModel=${dataModel}" target="_blank">下载${entityName}导入模板</a>。
				如果你刚刚导入过，<a href="${basePath}${path}/import.do?viewResult=yes&onlyFailed=true&dataModel=${dataModel}" target="dialogTodo" title="导入${entityName}">点击这里查看分析结果</a>。
				<footer>
					<button type="submit" class="btn btn-primary smart-form-submit-btn">
						<spring:message code="base.common.ok"></spring:message>
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