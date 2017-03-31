<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
					<section class="col col-2">
						<label class="input"> 
								<form:input path="sortNum"
									id="tag.sortNum" type="text"
									cssClass="textInputdigits" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Tag.sortNum')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<label class="select">
								<form:select id="status" path="status" cssClass="comboxed">
								<option value="">-${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Tag.status')}-</option>
									<form:options  items="${useStatusMap}"/>
								</form:select>
								<i></i>
								</label>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="name"
											id="tag.name"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Tag.name')}"/>
				</label>
				</section>