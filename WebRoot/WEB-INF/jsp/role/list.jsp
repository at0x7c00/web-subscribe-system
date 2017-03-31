<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">

	<div class="row">
	<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-0" 
			data-widget-colorbutton="false" data-widget-editbutton="false" 
			data-widget-deletebutton="false"
			data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-file"></i> </span>
					<h2>角色管理</h2>
				</header>
				<div>
				<form:form action="role/list.do" cssClass="smart-form" commandName="functionPoint" onsubmit="return ajaxSearch(this,'${targetPanel}');">
					<input type="hidden" value="${targetPanel}" name="targetPanel"/>
				
					<div class="widget-body no-padding">
					<div style="padding:5px;">
						<a class="btn btn-primary" href="role/add.do" target="dialogTodo" title="添加角色" style="padding:5px 15px;" id="addBtn"><i class="fa fa-plus"></i> <spring:message code="base.function.add"/></a>
					</div>
					<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
							<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
							<tr>
								<th width="30%">
									<spring:message code="base.function.table.head.no"/>
								</th>
								<th>
									<spring:message code="props.me.huqiao.wss.sys.entity.Role.name"/>
								</th>
								<th>类别</th>
								<th>
									<spring:message code="props.me.huqiao.wss.sys.entity.Role.description"/>:
								</th>
								<th width="20%">
									<spring:message code="base.function.table.head.operate"/>
								</th>
							</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageBean.list}">
									<tr>
										<td colspan="8">
											<center>
												<font style="color: gray;"><spring:message
														code="base.function.table.info.nodata" /> </font>
											</center></td>
									</tr>
								</c:if>
								<c:forEach var="temprole" items="${pageBean.list}"
									varStatus="stauts">
									<tr>
										<td>
											${temprole.id}
										</td>
										<td>
											${temprole.name}
										</td>
										<td>
										${temprole.type.description }
										</td>
										<td>
											${temprole.description}
										</td>
										<td>
											<a class="edit" href="role/update.do?id=${temprole.id}&rel=${rel}" target="dialogTodo"  title="修改权限"><span><spring:message code="base.function.update"/></span> </a>|
											<a class="delete" href="role/delete.do?id=${temprole.id}&rel=${rel}&targetPanel=page" target="ajaxTodo" confirm="确定要<spring:message code="base.function.delete"/>吗?" method="GET"><span><spring:message code="base.function.delete"/></span>
											</a>
											|
											<a href="role/setPrivilege.do?id=${temprole.id }" target="dialogTodo" title="<spring:message code="funcs.Role.setPrivilege"/>"><spring:message code="funcs.Role.setPrivilege"/></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<%@include file="/WEB-INF/jsp/common/pageBar.jsp"%>
					</div>
					</div>
				</form:form>
					
				</div>
				</div>
			</article>
		</div>
	</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>
