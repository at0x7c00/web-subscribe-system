<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="dt-row">
<div  class="row" >
	<div class="col-sm-4" style="padding-top:5px;">
		<div class="dataTables_info" id="dt_basic_info">
		&nbsp;&nbsp;&nbsp;<spring:message code="base.common.pagebar.display"/>
		<select class="combox" name="numPerPage"
			onchange="${(empty targetType) or (targetType eq 'navTab') ? 'navTabPageBreak':'dialogPageBreak'}({numPerPage:this.value})">
			<option value="10"
				<c:if test="${pageBean.numPerPage eq 10}">selected="selected"</c:if>>
				10
			</option>
			<option value="20"
				<c:if test="${pageBean.numPerPage eq 20}">selected="selected"</c:if>>
				20
			</option>
			<option value="50"
				<c:if test="${pageBean.numPerPage eq 50}">selected="selected"</c:if>>
				50
			</option>
			<option value="100"
				<c:if test="${pageBean.numPerPage eq 100}">selected="selected"</c:if>>
				100
			</option>
			<option value="200"
				<c:if test="${pageBean.numPerPage eq 200}">selected="selected"</c:if>>
				200
			</option>
		</select>
		<spring:message code="base.common.pagebar.unit"/>，<spring:message code="base.common.pagebar.total"/>${pageBean.totalCount}<spring:message code="base.common.pagebar.unit"/>
		</div>
	</div>
	<div class="col-sm-8 text-right">
		<div class="dataTables_paginate paging_bootstrap_full" style="padding-right:5px;">
			<ul class="pagination">
				<li class="first ${pageBean.pageNum eq 1 ?'disabled':''}" ><a href="#" data-page-value="1"><spring:message code="dwz.pagebar.first"></spring:message></a></li>
				<li class="prev ${pageBean.pageNum eq 1 ?'disabled':''}"  ><a href="#" data-page-value="${pageBean.pageNum-1>0?pageBean.pageNum-1:1}"><spring:message code="dwz.pagebar.pre"/></a></li>
				
				<c:forEach items="${pageBean.pageBarIndex}" var="pageIndex">
					<li class="${pageBean.pageNum eq pageIndex?'active':''}" ><a href="#" data-page-value="${pageIndex}">${pageIndex}</a></li>
				</c:forEach>
				
				<li class="next ${pageBean.countPage==0 or pageBean.countPage eq pageBean.pageNum ?'disabled':''}"><a href="#" data-page-value="${pageBean.pageNum + 1 > pageBean.countPage ? pageBean.countPage : pageBean.pageNum + 1}"><spring:message code="dwz.pagebar.next"/></a>										</li>
				<li class="last ${pageBean.countPage==0 or pageBean.countPage eq pageBean.pageNum ?'disabled':''}"><a href="#" data-page-value="${pageBean.countPage}"><spring:message code="dwz.pagebar.last"/></a></li>
			</ul>&nbsp;&nbsp;&nbsp;
		</div>
	</div>
</div>
</div>