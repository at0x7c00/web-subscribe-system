<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
	<td>
		<label class="checkbox">
			<input type="checkbox" name="${trTarget}Chk" value="${trIndex}"/>
			<i></i></label>
		<input type="hidden" name="rowGuard${trIndex}" value="1"/>
	</td>
				<td>
								<label class="input">
										<input name="${propName}[${trIndex}].selector"
											id="${propName}[${trIndex}].selector"
											class="textInput required"
											 value="<c:out value="${tempBean.selector}"/>"
											maxlength="255" />
								</label>
				</td>
				<td>
								<label class="input">
								<input name="${propName}[${trIndex}].cycle"
									id="${propName}[${trIndex}].cycle" type="text"
									   value="<c:out value="${tempBean.cycle}"/>"
									class="textInput  number" />
									</label>
				</td>
				<td>
								<label class="input">
										<input name="${propName}[${trIndex}].name"
											id="${propName}[${trIndex}].name"
											class="textInput required"
											 value="<c:out value="${tempBean.name}"/>"
											maxlength="255" />
								</label>
				</td>
				<td>
								<label class="input">
										<input name="${propName}[${trIndex}].url"
											id="${propName}[${trIndex}].url"
											class="textInput "
											 value="<c:out value="${tempBean.url}"/>"
											maxlength="255" />
								</label>
				</td>