<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="selector"
											id="task.selector"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Task.selector')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
								<form:input path="cycle"
									id="task.cycle" type="text"
									cssClass="textInputnumber" size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Task.cycle')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="name"
											id="task.name"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Task.name')}"/>
				</label>
				</section>
					<section class="col col-2">
						<label class="input"> 
										<form:input path="url"
											id="task.url"
											cssClass="textInput"
											maxlength="255"  size="12" placeholder="${nfn:i18nMessage(reqCtx,'props.me.huqiao.wss.chapter.entity.Task.url')}"/>
				</label>
				</section>