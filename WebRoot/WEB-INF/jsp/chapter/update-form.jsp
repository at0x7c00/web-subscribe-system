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
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.createTime"/>:
							<font style='color:red' class="required-mark">*</font>
								<label class="input">
								<i class="icon-append fa fa-calendar"></i>
								<input name="createTime" id="chapter.createTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}' value='${tempBean.createTime}'/>" class="date_required required textInput valid"/>
								</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.updateTime"/>:
							<font style='color:red' class="required-mark">*</font>
								<label class="input">
								<i class="icon-append fa fa-calendar"></i>
								<input name="updateTime" id="chapter.updateTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY_HOUR_MIN_SEC}' value='${tempBean.updateTime}'/>" class="date_required required textInput valid"/>
								</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.creator"/>:
							<font style='color:red' class="required-mark">*</font>
										<label class="select">
										<form:select id="creator" path="creator" cssClass="required comboxed">
										<option value=""><spring:message code="base.common.selectone"/></option>
											<form :options  items="${userList}" itemValue="username" itemLabel="chineseName"/> 
										</form:select><i></i>
										</label>
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.readCount"/>:
								<form:input path="readCount"
									id="chapter.readCount" type="text"
									cssClass="textInput  digits" />
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
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.cover"/>:
				</label>
				</section>
				<section class="col col-3">
					<label class="input">
						<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.content"/>:
										<label class="textarea">
										<form:textarea path="content"
											id="chapter.content" cols="60"
											rows="5"
											cssClass=""
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