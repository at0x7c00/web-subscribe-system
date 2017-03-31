<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
					<h2>用户管理</h2>

				</header>

				<div>

					<div class="jarviswidget-editbox">

					</div>

					<div class="widget-body no-padding">


						<form:form action="user/list.do" class="smart-form smart-form-search" onsubmit="return ajaxSearch(this,'${targetPanel}')" commandName="user">
						
							<input type="hidden" name="pageNum" value="${pageBean.pageNum}"/>
							<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
							<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
							<input type="hidden" name="targetPanel" value="${targetPanel}"/>
						
							<div id="form-search-mobile" class="btn-header transparent pull-left" style="margin-top:-5px;">
								<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
							</div>
						
							<%@include file="/WEB-INF/jsp/user/list-form.jsp" %>
				
							<div style="padding:5px;">
								<a class="btn btn-primary" href="user/add.do" target="dialogTodo" title="添加用户" style="padding:5px 15px;" id="addBtn"><i class="fa fa-plus"></i> <spring:message code="base.function.add"/></a>
								<a class="btn btn-danger" href="${basePath}user/delete.do?usernames={usernames}" target="ajaxTodo" confirm="确认要删除这些数据吗？" warn="请选择要删除的数据" style="padding:5px 15px;"><i class="fa fa-trash-o"></i> <spring:message code="base.function.delete"/></a>
								<a class="btn btn-warning" href="user/update.do?userName={usernames}"  rel="usernames" target="dialogTodo" title="修改用户" style="padding:5px 15px;" data-mustone="true" data-onlyone="true"><i class="fa fa-edit"></i> <spring:message code="base.function.update"/></a>
								<a class="btn btn-primary" href="user/detail.do?userName={usernames}" rel="usernames" target="dialogTodo" title="查看用户" style="padding:5px 15px;" data-mustone="true" data-onlyone="true" id="viewBtn"><i class="fa fa-eye"></i> <spring:message code="base.function.view"/></a>
								
								<a class="btn btn-primary" href="user/setRole.do?userName={usernames}" rel="usernames" target="dialogTodo" title="设置角色" style="padding:5px 15px;" data-mustone="true" data-onlyone="true" id="viewBtn"><i class="fa fa-edit"></i> <spring:message code="funcs.User.setRole"/></a>
<!-- 								<a class="btn btn-primary" href="ajax/addserver.jsp?targetPanel=dialog" rel="usernames" target="dialogTodo" title="添加服务器" style="padding:5px 15px;" id="viewBtn"><i class="fa  fa-plus"></i> 添加服务器</a> -->
							</div>
							<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
							
							<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
							
							<tr>
								<th align="center"  width="4%">
										<label class="checkbox">
												<input type="checkbox" name="checkbox" class="smart-form-checkall" data-groupname="usernames">
										<i></i></label>
								</th>
								<%@include file="/WEB-INF/jsp/user/list-head.jsp" %>
							</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageBean.list}">
									<tr>
										<td colspan="8">
											<center>
												<font style="color: gray;"><spring:message
														code="base.function.table.info.nodata" /> </font>
											</center>
											
										</td>
									</tr>
								</c:if>
								<c:forEach var="tempuser" items="${pageBean.list}"
									varStatus="stauts">
									<tr target="usernames" rel="${tempuser.username}">
										<td align="center">
										<label class="checkbox">
										<input name="usernames" type="checkbox" value="${tempuser.username}"/><i></i>
										</label>
										</td>
										<%@include file="/WEB-INF/jsp/user/list-body.jsp" %>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						<%@include file="/WEB-INF/jsp/common/pageBar.jsp" %>
						</form:form>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>
