<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- START ROW -->
	<div class="row">

		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-0" 
			data-widget-deletebutton="false"
			data-widget-colorbutton="false" data-widget-editbutton="false" 
			data-widget-custombutton="false">
			
				<header>
					<span class="widget-icon"> <i class="fa fa-file"></i> </span>
					<h2>文件夹管理</h2>

				</header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">

						<form:form action="${basePath}commonFolder/list.do" class="smart-form smart-form-search" onsubmit="return ajaxSearch(this)" commandName="commonFolder">
						
							<input type="hidden" name="pageNum" value="${pageBean.pageNum}"/>
							<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
							<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
						
							<div id="form-search-mobile" class="btn-header transparent pull-left" style="margin-top:-5px;">
								<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
							</div>
						
				
							<div style="padding:5px;">
							
								<a class="btn btn-primary" 
								href="${basePath}commonFolder/add.do" title = "添加文件夹"
								target="dialogTodo"  style="padding:5px 15px;"><i class="fa fa-plus"></i>
								 <spring:message code="base.function.add"/>
								</a>
							
								<a class="btn btn-danger" href="${basePath}commonFolder/delete.do?ids={ids}" target="ajaxTodo"
								 confirm="确认要删除这些数据吗？" warn="请选择要删除的数据" style="padding:5px 15px;"><i class="fa fa-trash-o"></i>
								 <spring:message code="base.function.delete"/>
								</a>
								<a class="btn btn-warning" href="commonFolder/update.do?id={ids}"  rel="ids" target="dialogTodo" title="修改文件夹" style="padding:5px 15px;" data-mustone="true" data-onlyone="true"><i class="fa fa-edit"></i> <spring:message code="base.function.update"/></a>
							</div>
							<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
							<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
								<tr>
									<th align="center">
										<label class="checkbox">
										<input type="checkbox" name="checkbox" class="smart-form-checkall" data-groupname="ids">
										<i></i>
										</label>
									</th>
									<th data-sortfield = "id" class="${nfn:sortClass(pageBean,'id')}"><spring:message code="base.function.table.head.no" />
									</th>
									<th  data-sortfield = "folderName"  class="${nfn:sortClass(pageBean,'folderName')}">
										<spring:message code="props.me.huqiao.wss.sys.entity.CommonFolder.folderName"></spring:message>
									</th>
				
									<th  data-sortfield = "storePath"  class="${nfn:sortClass(pageBean,'storePath')}" >
										<spring:message code="props.me.huqiao.wss.sys.entity.CommonFolder.storePath"></spring:message>
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
								<c:forEach var="tempcommonFolder" items="${pageBean.list}"
									varStatus="stauts">
									<tr target="ids" rel="${tempcommonFolder.id}">
										<td align="center">
										<label class="checkbox">
										<input name="ids" type="checkbox" value="${tempcommonFolder.id}"/><i></i>
										</label>
										</td>
										<td>${tempcommonFolder.id}</td>
										<td>${tempcommonFolder.folderName}</td>
										<td>${tempcommonFolder.storePath}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						
						
						<%@include file="/WEB-INF/jsp/common/pageBar.jsp" %>
						
						</form:form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->

		</article>
		<!-- END COL -->

	</div>

	<!-- END ROW -->

</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>

