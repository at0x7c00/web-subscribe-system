<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.name"/>:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="name"
											id="task.name"
											cssClass="textInput required"
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.url"/>:
										<form:input path="url"
											id="task.url"
											cssClass="textInput "
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.selector"/>:
							<font style='color:red' class="required-mark">*</font>
										<form:input path="selector"
											id="task.selector"
											cssClass="textInput required"
											maxlength="255" />
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Task.cycle"/>:
								<form:input path="cycle"
									id="task.cycle" type="text"
									cssClass="textInput  number" />
				</label>
				</section>
				
				<section class="col col-3">
					<label class="input">
						直接标记为推荐:
							<font style='color:red' class="required-mark">*</font>
								<label class="select">
								<form:select id="markAsFav" path="markAsFav" cssClass="required comboxed">
								<option value=""><spring:message code="base.common.selectone"/></option>
									<form:options  items="${yesNoMap}"/>
								</form:select><i></i>
								</label>
				</label>
				</section>