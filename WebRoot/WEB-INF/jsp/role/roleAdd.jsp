<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<div class="pageContent">
	<form method="post" action="role/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span><spring:message code="base.common.save"/></span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><spring:message code="base.common.save"/></button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="base.common.cancel"/></button></button></div></div>
				</li>
			</ul>
		</div>
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt><spring:message code="props.me.huqiao.wss.sys.entity.Role.name"/>：</dt>
				<dd>
					<input name="roleName" type="text" class="required" size="30" alt="请输入角色名称"/>
					<span class="info"></span>
				</dd>
			</dl>
			<div class="divider"></div>
			<c:forEach var="parentFunction" items="${parentFunctionMenus}">
				<fieldset>
					<legend>
						${parentFunction.functionName}
						<input class="checkAll" id="${parentFunction.id}" type="checkbox" name="functionIds" value="${parentFunction.id}">
					</legend>
					<c:forEach var="function" items="${parentFunction.subFunctionMenus}">
						<dl>
							<dt>${function.functionName}</dt>
							<dd>
								<input id="check_${parentFunction.id}_${function.id}" type="checkbox" name="functionIds" value="${function.id}">
							</dd>
						</dl>
					</c:forEach>
					<%--<div style="float:left; display:block; margin:10px; overflow:auto; width:200px; height:200px; overflow:auto; border:solid 1px #CCC; line-height:21px; background:#FFF;">
						<ul class="tree treeFolder treeCheck expand" oncheck="">
							<c:forEach var="parentFunction" items="${parentFunctionMenus}">
								<li><a id="function_${parentFunction.id}" tname="functionIds" tvalue="${parentFunction.id}">${parentFunction.functionName}</a>
									<ul>
										<c:forEach var="function" items="${parentFunction.subFunctionMenus}">
											<li><a id="function_${parentFunction.id}" tname="functionIds" tvalue="${function.id}">${function.functionName}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
						</div>
					--%>
				</fieldset>
			</c:forEach>
		</div>
		
	</form>
	<script type="text/javascript">
		$(".checkAll").each(function(){
			$(this).click(function(){
				var id = $(this).attr("id");
				var checkedStatu = $(this).attr("checked");
				$("input[id^='check_"+id+"']").each(function(){
					if(checkedStatu=="checked"){
						$(this).attr("checked","checked");
					} else {
						$(this).removeAttr("checked");
					}
				});
			});
		});
		$("input[id^='check_']").each(function(){
			$(this).click(function(){
				if($(this).attr("checked") == "checked"){
					var id = $(this).attr("id");
					var index = id.indexOf("_")+1;
					id = id.substring(index);
					var parentId = id.substring(0,id.indexOf("_"));
					$("input[id='"+parentId+"']").attr("checked","checked");
				}
			});
		});
	</script>
</div>
