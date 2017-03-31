<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<section class="col col-3">
	<label class="input">
	<i class="icon-append fa fa-calendar"></i>
	<input type="text" name="operateDateStart" 
	value="<fmt:formatDate value="${pageBean.operateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
	onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});" 
	class="date_not_required textInput valid" size="12"
	placeholder="${nfn:i18nMessage(reqCtx,'history.list.time')}(${nfn:i18nMessage(reqCtx,'base.common.form.start')})"
	/>
	</label>
</section>

<section class="col col-3">
	<label class="input">
	<i class="icon-append fa fa-calendar"></i>
	<input type="text" name="operateDateEnd" 
	value="<fmt:formatDate value="${pageBean.operateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
	onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});" 
	class="date_not_required textInput valid" size="12"
	placeholder="${nfn:i18nMessage(reqCtx,'history.list.time')}(${nfn:i18nMessage(reqCtx,'base.common.form.start')})"
	/>
	</label>
</section>

<section class="col col-2">
	<label class="input">
	<input name="operator"
		id="operator"
		class="textInput"
		value="<c:out value="${pageBean.operator}"/>"
		maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'history.list.operator')}"/>
	</label>
</section>

<section class="col col-2">
	<label class="select">
	<select name="operateType">
		<option value="">-<spring:message code="history.list.operatetype"/>-</option>
		<option value="ADD"  ${pageBean.operateType=='ADD'?'selected':''}><spring:message code="history.list.operatetype.add"/></option>
		<option value="DEL"  ${pageBean.operateType=='DEL'?'selected':''}><spring:message code="history.list.operatetype.del"/></option>
		<option value="MOD"  ${pageBean.operateType=='MOD'?'selected':''}><spring:message code="history.list.operatetype.mod"/></option>
	</select><i></i>
	</label>		
</section>	
<c:if test="${empty software}">
<section class="col col-2">
	<label class="select">
	<select name="deviceStatus">
		<option value="">-<spring:message code="history.list.deviceStatus"/>-</option>
		<c:forEach items="${physicalDeviceStatusMap}" var="temp">
		<option value="${temp.key}"  ${pageBean.deviceStatus==temp.key?'selected':''}>${isChina?temp.value:temp.key}</option>
		</c:forEach>
		
	</select><i></i>
	</label>		
</section>
</c:if>