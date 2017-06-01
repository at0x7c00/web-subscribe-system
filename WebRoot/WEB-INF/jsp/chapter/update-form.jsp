<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.title"/>:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="title"
											id="chapter.title"
											cssClass="textInput required"
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.category"/>:
							<font style='color:red' class="required-mark">*</font>
										<label class="select">
										<form:select id="category" path="category" cssClass="required comboxed">
										<option value=""><spring:message code="base.common.selectone"/></option>
											<form:options  items="${categoryList}" itemValue="manageKey" itemLabel="name"/>
										</form:select><i></i>
										</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.sortNum"/>:
								<form:input path="sortNum"
									id="chapter.sortNum" type="text"
									cssClass="textInput  digits" />
				</label>
				</section>
				<section class="col col-12">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.content"/>:
										<label class="textarea">
										<form:textarea path="content"
											id="chapter.content" cols="60"
											rows="5"
											cssClass="ckeditor-able"
											 />
										</label>
					</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.status"/>:
								<label class="select">
								<form:select id="status" path="status" cssClass=" comboxed">
								<option value=""><spring:message code="base.common.selectone"/></option>
									<form:options  items="${useStatusMap}"/>
								</form:select><i></i>
								</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.origin"/>:
										<form:input path="origin"
											id="chapter.origin"
											cssClass="textInput "
											maxlength="255" />
				</label>
				</section>