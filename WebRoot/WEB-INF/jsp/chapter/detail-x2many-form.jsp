<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
				<c:if test="${not historyView}">
				<!-- 标签  tags:many to many prop tab start -->
				<div class="tab-pane fade" id="tags">
					<div class="dataTables_wrapper select-list" id="tagsSelectList" style="border-bottom:1px solid #ddd;" 
						 data-dataurl="tag/selectList.do?showCheckBox=false"
						 data-groupname="tagsKeys"
						 data-entityidname="manageKey"
						 data-method="POST"
						 data-initvalues="${nfn:serialKyes(tempBean.tags,'manageKey')}"
						 >
					</div>
				</div>
				<!-- 标签 tags:!-- many to many prop tab end -->
				</c:if>
			<div class="tab-pane fade" id="covers">
				<a href="filee/viewPic.do?manageKey=${tempBean.photoFile.manageKey }" target="_blank">
					<img src="filee/viewPic.do?manageKey=${tempBean.cover.manageKey }" class="table-pic-lg hover-able"/>
				</a>
			</div> 	