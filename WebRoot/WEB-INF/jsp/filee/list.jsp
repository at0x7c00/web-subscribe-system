<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- START ROW -->
	<div class="row">

		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" 
			data-widget-custombutton="false">
			
				<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-file"></i> </span>
					<h2>文件管理</h2>

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

<!-- 
						<div class="widget-body-toolbar">
						here...
						</div>
 -->

						<form:form action="${basePath}filee/list.do" class="smart-form smart-form-search" onsubmit="return ajaxSearch(this)" commandName="commonFile">
						
							<input type="hidden" name="pageNum" value="${pageBean.pageNum}"/>
							<input type="hidden" name="orderField" value="${pageBean.orderField}"/>
							<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
						
							<div id="form-search-mobile" class="btn-header transparent pull-left" style="margin-top:-5px;">
								<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
							</div>
						
							<fieldset>
								<div class="row">
									<section class="col col-2">
										<label class="input">
											<form:input path="name" placeholder="文件名"/>
										</label>
									</section>
									<section class="col col-2">
										<label class="input">
											<input type="text" placeholder="拓展名">
										</label>
									</section>
									<section class="col col-2">
										<label class="input">
											<input type="text" placeholder="上传日期开始">
										</label>
									</section>
									<section class="col col-2">
										<label class="input">
											<input type="text" placeholder="上传日期结束">
										</label>
									</section>
									<section class="col col-2">
										<label class="input">
											<input type="text" placeholder="文件夹">
										</label>
									</section>
									
									<section class="col col-2">
											<a class="btn btn-primary smart-form-submit-btn" href="#" style="padding:5px 15px;"><i class="fa fa-search"></i> <spring:message code="base.function.query"/></a>
									</section>
								</div>
							</fieldset>
				
							<div style="padding:5px;">
								<a class="btn btn-danger" href="${basePath}filee/delete.do?ids={ids}" target="ajaxTodo" confirm="确认要删除这些数据吗？" warn="请选择要删除的数据" style="padding:5px 15px;"><i class="fa fa-trash-o"></i> <spring:message code="base.function.delete"/></a>
							</div>
							
							
							<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
							
							
							
							<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
								<tr>
									<th align="center">
										<label class="checkbox">
												<input type="checkbox" name="checkbox" class="smart-form-checkall" data-groupname="ids">
										<i></i></label>
									</th>
									<th data-sortfield="id"
										
										<c:choose>
										<c:when test="${pageBean.orderField=='id'}">
											class="sorting sorting_${pageBean.orderDirection}"
										</c:when>
										<c:otherwise>
											class="sorting"
										</c:otherwise>
									</c:choose>
									>
										<spring:message code="base.function.table.head.no" /></th>
								
									<th orderfield="name"  data-sortfield="name" 
										<c:choose>
											<c:when test="${pageBean.orderField=='name'}">
												class="sorting sorting_${pageBean.orderDirection}"
											</c:when>
											<c:otherwise>
												class="sorting"
											</c:otherwise>
										</c:choose>
									>
										<spring:message
											code="props.me.huqiao.wss.sys.entity.Filee.name"></spring:message>
									</th>
										
									<th orderField="extensionName" data-sortfield="extensionName" 
										<c:choose>
										<c:when test="${pageBean.orderField=='extensionName'}">
											class="sorting sorting_${pageBean.orderDirection}"
										</c:when>
										<c:otherwise>
											class="sorting"
										</c:otherwise>
									</c:choose>
										<spring:message
											code="props.me.huqiao.wss.sys.entity.Filee.extensionName"></spring:message>
									</th>
									<th orderField="createDate" 
										<spring:message
											code="props.me.huqiao.wss.sys.entity.Filee.createDate"></spring:message>
									</th>
									<th orderField="folder" class="sorting"
										<spring:message
											code="props.me.huqiao.wss.sys.entity.Filee.folder"></spring:message>
									</th>
									<th orderField="manageKey"
										ManageKey</th>
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
								<c:forEach var="tempfilee" items="${pageBean.list}"
									varStatus="stauts">
									<tr target="ids" rel="${tempfilee.id}">
										<td align="center">
										<label class="checkbox">
										<input name="ids" type="checkbox" value="${tempfilee.id}"/><i></i>
										</label>
										</td>
										<td>${tempfilee.id}</td>
										<td>${tempfilee.name}</td>
										<td>${tempfilee.extensionName}</td>
										<td><fmt:formatDate value="${tempfilee.createDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${tempfilee.folder.folderName}</td>
										<td>${tempfilee.manageKey}</td>
				
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
