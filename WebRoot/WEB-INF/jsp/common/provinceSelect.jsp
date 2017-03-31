<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:if test="${not ___currentUser.isAgent }">
<section class="col col-2">
		<label class="input"> 
				<label class="select">
				<form:select id="areaKey" path="areaKey" cssClass="comboxed area-select">
				<option value="">-区域-</option>
					<form:options  items="${areas}" itemLabel="name" itemValue="manageKey"/>
				</form:select>
				<i></i>
				</label>
		</label>
</section>
<section class="col col-2">
		<label class="input"> 
				<label class="select">
				<form:select id="provinceKey" path="provinceKey" cssClass="comboxed">
				<option value="">-省份-</option>
					<form:options  items="${provinces}" itemLabel="name" itemValue="manageKey"/>
				</form:select>
				<i></i>
				</label>
		</label>
</section>
</c:if>