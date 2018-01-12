<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.name"/>:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="name"
											id="tag.name"
											cssClass="textInput required"
											maxlength="255" />
				</label>
				</section>
				
				<section class="col col-3">
					<label class="input">
						唯一编码:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="code"
											id="tag.code"
											cssClass="textInput required"
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.sortNum"/>:
							<font style='color:red' class="required-mark">*</font>
								<form:input path="sortNum"
									id="tag.sortNum" type="text"
									cssClass="textInput required digits" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Tag.status"/>:
							<font style='color:red' class="required-mark">*</font>
								<label class="select">
								<form:select id="status" path="status" cssClass="required comboxed">
								<option value=""><spring:message code="base.common.selectone"/></option>
									<form:options  items="${useStatusMap}"/>
								</form:select><i></i>
								</label>
				</label>
				</section>