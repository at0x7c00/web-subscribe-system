<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<section id="widget-grid" class="">

	<div class="row">
	
	
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div>
				<div class="widget-body no-padding">
					<form:form action="role/setPrivilege.do" cssClass="smart-form" commandName="role" onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
						<input type="hidden" value="${role.id }" name="id"/>
						<input type="hidden" value="" name="functionPointIds" id="functionPointIds"/>
						<input type="hidden" value=" " name="rel"/>
							<ul id="treeDemo" class="ztree"></ul>
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
			</div>
		</article>
		
		<%--
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div>
				<div class="widget-body no-padding">
					<form:form action="role/setPrivilege.do" cssClass="smart-form" commandName="role" onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
						<input type="hidden" value="${role.id }" name="id"/>
						<input type="hidden" value=" " name="rel"/>
						<fieldset style="padding-top:0px;">
							<div id="jstree-proton-3" style="margin-top:0px;" class="proton-demo">
								<ul>
									<li data-jstree='{"selected" : true,"value" : "" ,state : {opend : true}}'>全选
										<input type="checkbox" name="functionPointIds" value=""/>
										<ul>
											<c:forEach items="${topFunctionPoints}" var="top" varStatus="s1">
													<li data-jstree='{"selected" : ${fn:contains(role.functionPoints,top)},state : {opend : true}}'>
														${top.name }
														<input type="checkbox" value="${top.id}" name="functionPointIds" ${fn:contains(role.functionPoints,top) ? "checked" : ""}/>
														<c:if test="${not empty top.children }">
															<ul>
																<c:forEach items="${top.children}" var="second"  varStatus="s2">
																	<li data-jstree='{"selected" : ${fn:contains(role.functionPoints,second)},"value" : ${second.id} }'>
																	${second.name }
																	<input type="checkbox" value="${second.id}" name="functionPointIds"  ${fn:contains(role.functionPoints,second) ? "checked" : ""}/>
																	<c:if test="${not empty second.children }">
																		<ul>
																			<c:forEach items="${second.children}" var="third"  varStatus="s2">
																				<li data-jstree='{"selected" : ${fn:contains(role.functionPoints,third)},"value" : ${third.id} }'>
																				${third.name }
																				<input type="checkbox" value="${third.id}" name="functionPointIds"  ${fn:contains(role.functionPoints,third) ? "checked" : ""}/>
																			</c:forEach>
																		</ul>
																	</c:if>
																</c:forEach>
															</ul>
														</c:if>
														
													</li>
											</c:forEach>
										</ul>
									</li>
								</ul>
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
			
			</div>
		</article> --%>
	
	</div>

</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>
<style type="text/css">
<!--
.ztree .button{
	float : none;
}
.ztree li span.button{
	width : 16px;
	height: 16px;
}
-->
</style>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			check: {
				enable: true,
				chkboxType : { "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback : {
				onCheck : function(){
					setFunctionPointIdsValue();
				}
				
			}
			
		};

		var zNodes =[
					  { id:0, pId:0, name:"全选", open:true},
		              <n:html value="${zTreeJson}"></n:html> 
		];
		
		function setFunctionPointIdsValue(){
			var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var checkedNodes=zTreeObj.getCheckedNodes(true);
			var val = '';
			for(var i = 0;i<checkedNodes.length;i++){
				val +=  checkedNodes[i].id;
				if(i<checkedNodes.length-1){
					val += ",";
				}
			}
			$("#functionPointIds").val(val);
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			setFunctionPointIdsValue();
			
		});
		//-->
	</SCRIPT>