<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="title"
											id="chapter.title"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.title')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<label class="select">
										<form:select id="category" path="category" cssClass="required comboxed">
										<option value="">-<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.category"/>-</option>
											<form:options  items="${categoryList}" itemValue="manageKey" itemLabel="name"/>
										</form:select>
										<i></i>
										</label>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<i class="icon-append fa fa-calendar"></i>
								<input name="createTimeStart" id="chapter.createTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY}' value='${chapter.createTimeStart}'/>" class="date_not_required textInput valid"  size="12"
									   placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.createTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.start')})"
									   />
			</label>
				</section>
					<section class="col col-2">
					<label class="input"> 
								<i class="icon-append fa fa-calendar"></i>
								<input name="createTimeEnd" id="chapter.createTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY}' value='${chapter.createTimeEnd}'/>" class="date_not_required textInput valid"  size="12"
									   placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.createTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.end')})"
									   />
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<i class="icon-append fa fa-calendar"></i>
								<input name="updateTimeStart" id="chapter.updateTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY}' value='${chapter.updateTimeStart}'/>" class="date_not_required textInput valid"  size="12"
									   placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.updateTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.start')})"
									   />
			</label>
				</section>
					<section class="col col-2">
					<label class="input"> 
								<i class="icon-append fa fa-calendar"></i>
								<input name="updateTimeEnd" id="chapter.updateTime" onclick="WdatePicker({dateFmt:'${applicationScope.EN_YEAR_MONTH_DAY}'});"
								value="<fmt:formatDate pattern='${applicationScope.EN_YEAR_MONTH_DAY}' value='${chapter.updateTimeEnd}'/>" class="date_not_required textInput valid"  size="12"
									   placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.updateTime')}(${nfn:i18nMessage(reqCtx,'base.common.form.end')})"
									   />
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<label class="select">
										<form:select id="creator" path="creator" cssClass="required comboxed">
										<option value="">-<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.creator"/>-</option>
											<form:options  items="${userList}" itemValue="username" itemLabel="chineseName"/> 
										</form:select>
										<i></i>
										</label>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<form:input path="readCount"
									id="chapter.readCount" type="text"
									cssClass="textInputdigits" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.readCount')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<form:input path="sortNum"
									id="chapter.sortNum" type="text"
									cssClass="textInputdigits" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.sortNum')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<label class="select">
										<form:select id="cover" path="cover" cssClass=" comboxed">
										<option value="">-<spring:message code="props.me.huqiao.wss.chapter.entity.Chapter.cover"/>-</option>
											<form:options  items="${commonFileList}" itemValue="manageKey" itemLabel="manageKey"/>
										</form:select>
										<i></i>
										</label>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="content"
											id="chapter.content" size="12"
											 placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.content')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<label class="select">
								<form:select id="status" path="status" cssClass="comboxed">
								<option value="">-${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.status')}-</option>
									<form:options  items="${useStatusMap}"/>
								</form:select>
								<i></i>
								</label>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="origin"
											id="chapter.origin"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Chapter.origin')}"/>
				</label>
				</section>