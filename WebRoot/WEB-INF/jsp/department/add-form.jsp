<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.name"/>:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="name"
											id="department.name"
											cssClass="textInput required nameFormat"
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.status"/>:
							<font style='color:red' class="required-mark">*</font>
								<label class="select">
								<form:select id="status" path="status" cssClass="required comboxed">
								<option value=""><spring:message code="base.common.selectone"/></option>
									<form:options  items="${useStatusMap}"/>
								</form:select><i></i>
								</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.parent"/>:
										<label class="select">
										<form:select id="parent" path="parent" cssClass=" comboxed">
										<option value=""><spring:message code="base.common.selectone"/></option>
											<form:options  items="${departmentList}" itemValue="manageKey" itemLabel="name"/>
										</form:select><i></i>
										</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.sys.entity.Department.sort"/>:
								<form:input path="sort"
									id="department.sort" type="text"
									cssClass="textInput  digits" />
				</label>
				</section>