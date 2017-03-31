<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<section id="widget-grid" class="">

	<div class="row">
	
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div>
			
				<div class="widget-body no-padding">
					
					<form:form action="user/setRole.do" cssClass="smart-form" commandName="role" onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);">
						<input type="hidden" value="${user.username }" name="userName" />
						<fieldset>
								<div class="row">
									<section class="col col-3">
										<label class="input">
											<spring:message code="props.me.huqiao.wss.sys.entity.User.username"></spring:message>： 
											<c:out value="${user.username }"/>
										</label>
									</section>
									
									<section class="col col-3">
										<label class="input">
											<spring:message code="props.me.huqiao.wss.sys.entity.User.status"></spring:message>： 
											${userStatusMap[user.status] }
										</label>
									</section>
									
								</div>
						</fieldset>
						
						
						
						<div class="dataTables_wrapper" style="border-bottom:1px solid #ddd;"> 
						<table id="dt_basic" class="table table-striped table-bordered table-hover" style="border:1px solid red;">
							<thead>
								<tr>
									<th width="30" align="center">
										<label class="checkbox">
												<input type="checkbox" name="checkbox" class="smart-form-checkall" data-groupname="roleIds">
										<i></i></label>
									</th>
									<th width="10%">
										<spring:message code="base.function.table.head.no"/>
									</th>
									<th width="30%">
									<spring:message code="props.me.huqiao.wss.sys.entity.Role.name"></spring:message>
									
									</th>
									<th>
									<spring:message code="props.me.huqiao.wss.sys.entity.Role.description"></spring:message>
									
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty roleList}">
									<tr>
										<td colspan="3">
											<center>
												<font style="color: gray;"><spring:message code="base.function.table.info.nodata"/> </font>
											</center>
										</td>
									</tr>
								</c:if>
								<c:forEach var="temprole" items="${roleList}"
									varStatus="stauts">
									<tr>
										<td align="center">
										
										<label class="checkbox">
										<input name="roleIds" type="checkbox" value="${temprole.id}" <c:if test="${fn:contains(user.roles,temprole)}">checked="checked"</c:if>/><i></i>
										</label>
										
										</td>
										<td>
											${temprole.id}
										</td>
										<td>
											${temprole.name}
										</td>
										<td>
											${temprole.description}
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						
						<fieldset>
								<div class="row">
									<section class="col col-12">
											权限范围： 
											<c:forEach items="${areaList}" var="area">
												<input type="checkbox" id="area_${area.id}" 
												${fn:contains(user.areas,area) ? 'checked':''}
												name="areaIds" value="${area.id}"/>
												<label for="area_${area.id}">
												<c:out value="${area.name}"/>
												</label>
												&nbsp;&nbsp;
											</c:forEach>
									</section>
									
									
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
		</article>
	
	</div>

</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp" %>
