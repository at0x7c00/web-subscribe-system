<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<td>
		<label class="checkbox">
			<input type="checkbox" name="${trTarget}Chk" value="${trIndex}"/>
			<i></i></label>
		<input type="hidden" name="rowGuard${trIndex}" value="1"/>
	</td>
				<td>
								<label class="input">
										<input name="${propName}[${trIndex}].name"
											id="${propName}[${trIndex}].name"
											class="textInput required"
											 value="<c:out value="${tmpTmpBean.name}"/>"
											maxlength="255" />
								</label>
				</td>
				<td>
										<label class="select">
										<select id="${propName}[${trIndex}].parent.id" name="${propName}[${trIndex}].parent.id" class=" comboxed">
											<option value=""><spring:message code="base.common.selectone"/></option>
											<c:forEach items="${departmentList}" var="department" >
												<option value="${department.id}" ${tmpTmpBean.parent.id eq department.id ? 'selected' : ''}><c:out value="${department.name}"/></option>
											</c:forEach>
										</select><i></i>
										</label>
				</td>
				<td>
										<label class="select">
										<select id="${propName}[${trIndex}].children.id" name="${propName}[${trIndex}].children.id" class=" comboxed">
											<option value=""><spring:message code="base.common.selectone"/></option>
											<c:forEach items="${departmentList}" var="department" >
												<option value="${department.id}" ${tmpTmpBean.children.id eq department.id ? 'selected' : ''}><c:out value="${department.name}"/></option>
											</c:forEach>
										</select><i></i>
										</label>
				</td>
				<td>
								<label class="select">
								<select id="${propName}[${trIndex}].status" name="${propName}[${trIndex}].status" cssClass="required comboxed">
								<option value=""><spring:message code="base.common.selectone"/></option>
									<c:forEach items="${useStatusMap}" var="entry" >
										<option value="${entry.key}" ${tmpTmpBean.status eq entry.key ? 'selected' : ''}><c:out value="${entry.value}"/></option>
									</c:forEach>
								</select><i></i>
								</label>
				</td>