<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div>
				<div class="widget-body no-padding">
					<ul class="ztree ztree-select" data-datalist="${nfn:createZTreeNodeData(dataList)}" data-frompanel="${fromPanel}" data-propname="${propName}"></ul>
				</div>
			</div>
		</article>
	</div>
</section> 
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp"%>