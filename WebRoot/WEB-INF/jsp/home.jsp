<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<section id="widget-grid" class="">
<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false"
			 data-widget-deletebutton="false" data-widget-custombutton="false" style="margin-bottom:10px;">
				<header>
					<span class="widget-icon"> <i class="fa fa-lg fa-fw  fa-user"></i>
					</span>
					<h2>个人信息</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
					
							<div class="row" style="padding:4px 20px;">
							
							   <section class="col col-12" style="padding:4px">
							   		<label class="input">
							   			账号信息:<a href="#user/myfile.do" title="账号信息">${LOGIN_INFO_IN_SESSION.user.username }</a>
							   			<a href="${basePath}loginOut.do" title="退出系统" data-logout-msg="确定要退出系统吗？"><i class="fa fa-sign-out"></i>退出</a>
							   		</label>
							   </section>
							   
							   <c:if test="${not empty LOGIN_INFO_IN_SESSION.user.agent }">
							   <section class="col col-12" style="padding:4px">
							   		<label class="input">
							   			账户余额:<a href="${basePath}agent/tradeRecord.do?agentKey=${tempBean.manageKey}" target="dialogTodo" title="交易记录">
							   			￥${agent.balance}元
							   			</a>
							   		</label>
							   </section>
							   </c:if>
							   
							   <section class="col col-12" style="padding:4px">
							   		<label class="input">
							   			上次登录时间: 
							   			<c:choose>
							   				<c:when test="${empty LOGIN_INFO_IN_SESSION.user.lastLoginTime }">
							   				无(未登录过)
							   				</c:when>
							   				<c:otherwise>
							   				<fmt:formatDate value="${LOGIN_INFO_IN_SESSION.user.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							   				</c:otherwise>
							   			</c:choose>
							   		</label>
							   </section>
							</div>
					
					</div>
				</div>
			</div>
		</article>
	</div>
</section>


<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-lg fa-fw  fa-bullhorn"></i>
					</span>
					<h2>公告</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<form:form action="${basePath}home.do" class="smart-form smart-form-search" onsubmit="return ajaxSearch(this,'${targetPanel}')" commandName="notice">
							<input type="hidden" name="pageNum" value="${pageBean.pageNum}" />
							<input type="hidden" name="orderField" value="${pageBean.orderField}" />
							<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}" />
							<input type="hidden" name="targetPanel" value="${targetPanel}" />
							<div id="form-search-mobile" class="btn-header transparent pull-left" style="margin-top:-5px;">
								<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a>
								</span>
							</div>
							<fieldset>
								<div class="row">

									<section class="col col-2">
										<label class="input"> <form:input path="content" id="notice.content" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.content')}" />
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <label class="select"> <form:select id="level" path="level" cssClass="comboxed">
													<option value="">-${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.level')}-</option>
													<form:options items="${noticeLevelMap}" />
												</form:select> <i></i>
										</label>
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <form:input path="title" id="notice.title" cssClass="textInput" maxlength="255" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.title')}" />
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i> <input name="createTimeStart" id="notice.createTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});" value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}' value='${notice.createTimeStart}'/>" class="date_not_required textInput valid" size="12"
											placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.createTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.start')})" />
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i> <input name="createTimeEnd" id="notice.createTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});" value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}' value='${notice.createTimeEnd}'/>" class="date_not_required textInput valid" size="12"
											placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.createTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.end')})" />
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <form:input path="creatorQuery" id="notice.creatorQuery" type="text" cssClass="textInput" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.notice.entity.Notice.creator')}" />
										</label>
									</section>


									<section class="col col-2 pull-right">
										<a class="btn btn-primary smart-form-submit-btn pull-right" href="#" style="padding:5px 15px;"><i class="fa fa-search"></i> <spring:message code="base.function.query" /></a>
									</section>
								</div>
							</fieldset>
							<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;">
								<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
									<thead>
										<tr>
										 <%--
											<th data-sortfield="id" class="${nfn:sortClass(pageBean,'id')}"><spring:message code="base.function.table.head.no" /></th>
										 --%>
											<th align="center" data-sortfield="title" class="${nfn:sortClass(pageBean,'title')}" style="width:50%"><spring:message code="props.me.huqiao.wss.notice.entity.Notice.title" /></th>
											<th align="center" data-sortfield="level" class="${nfn:sortClass(pageBean,'level')}"><spring:message code="props.me.huqiao.wss.notice.entity.Notice.level" /></th>
											<th align="center" data-sortfield="createTime" class="${nfn:sortClass(pageBean,'createTime')}"><spring:message code="props.me.huqiao.wss.notice.entity.Notice.createTime" /></th>
											<th align="center" data-sortfield="creator" class="${nfn:sortClass(pageBean,'creator')}"><spring:message code="props.me.huqiao.wss.notice.entity.Notice.creator" /></th>

										</tr>
									</thead>
									<tbody>
										<c:if test="${empty pageBean.list}">
											<tr>
												<td colspan="8">
													<center>
														<font style="color: gray;"><spring:message code="base.function.table.info.nodata" /> </font>
													</center>
												</td>
											</tr>
										</c:if>
										<c:forEach var="tempBean" items="${pageBean.list}" varStatus="stauts">
											<tr target="manageKeys" rel="${tempBean.manageKey}">
											<%--
												<td>${tempBean.id}</td>
											 --%>
												<td><a href="${basePath}notice/detail.do?manageKey=${tempBean.manageKey}" target="dialogTodo" title="查看公告">${tempBean.title}</a></td>
												<td>${noticeLevelMap[tempBean.level]}</td>
												<td><fmt:formatDate value="${tempBean.createTime}" pattern="${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}" /></td>
												<td>${tempBean.creator.name}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<%@include file="/WEB-INF/jsp/common/pageBar.jsp"%>
						</form:form>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp"%>