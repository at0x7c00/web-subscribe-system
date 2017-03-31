<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-0"
				data-widget-colorbutton="false" data-widget-editbutton="false"
				data-widget-deletebutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-file"></i>
					</span>
					<h2>系统参数配置</h2>
				</header>
				<div>
					<ul id="myTab1" class="nav nav-tabs bordered">
						<li class="active"><a href="#mainInfo" data-toggle="tab">配置信息</a></li>
						<li><a href="#monitorConfig" data-toggle="tab">监控配置</a></li>
						<li><a href="#cocoaConfig" data-toggle="tab">直真配置</a></li>
					</ul>
					<form method="post" action="config/systemConfig.do"  onsubmit="return validateCallback(this, ${targetPanel eq 'dialog' ? 'dialog' :'navTab' }AjaxDone);"
						class="smart-form required-validate">
						<div id="myTabContent1" class="tab-content">
							<div class="tab-pane fade in active" id="mainInfo">
								<fieldset>
									<div class="row">
										<section class="col col-10">
											<label>安装操作系统接口URL</label> <label class="input">
												<input type="text" name="osInstall" value="${osInstall.name}" placeholder="安装操作系统接口URL">
											</label>
										</section>
										<section class="col col-10">
											<label>部署app、安装补丁、版本更新接口URL</label> <label class="input">
												<input type="text" name="appInstall" value="${appInstall.name}" placeholder="部署app、安装补丁、版本更新接口UR">
											</label>
										</section>
										<section class="col col-10">
											<label>配置变更接口URL</label> <label class="input">
												<input type="text" name="configChange" value="${configChange.name}" placeholder="配置变更接口URL">
											</label>
										</section>
										<section class="col col-10">
											<label>模板解析接口URL</label> <label class="input">
												<input type="text" name="templateAnalysis" value="${templateAnalysis.name}" placeholder="模板解析接口UR">
											</label>
										</section>
										<section class="col col-10">
											<label>文件扫描接口URL</label> <label class="input">
												<input type="text" name="fileAnalysis" value="${fileAnalysis.name}" placeholder="文件扫描接口URL">
											</label>
										</section>
										
										<section class="col col-10">
											<label>远程文件同步接口</label> <label class="input">
												<input type="text" name="remoteFileSyncUrl" value="${remoteFileSyncUrl.name}" placeholder="远程文件夹根目录">
											</label>
										</section>
										
										<section class="col col-10">
											<label>新增本地文件通知接口</label> <label class="input">
												<input type="text" name="newLocalFileNoticeUrl" value="${newLocalFileNoticeUrl.name}" placeholder="远程文件夹根目录">
											</label>
										</section>
										
										<section class="col col-10">
											<label>远程文件夹根目录</label> <label class="input">
												<input type="text" name="remoteFilesRoot" value="${remoteFilesRoot.name}" placeholder="远程文件夹根目录">
											</label>
										</section>
										
										<section class="col col-10">
											<label>系统根路径</label> <label class="input">
												<input type="text" name="systemBasePathCfg" value="${systemBasePathCfg.name}" placeholder="远程文件夹根目录">
											</label>
										</section>
									</div>
								</fieldset>
							</div>
						
							<div class="tab-pane fade tab-form" id="monitorConfig">
								<fieldset>
									<div class="row">
										<section class="col col-10">
											<label>监控系统接口调用账号</label> <label class="input">
												<input type="text" name="nmsApiAccount" value="${nmsApiAccount.name}" placeholder="监控系统接口调用账号">
											</label>
										</section>
										<section class="col col-10">
											<label>监控系统接口调用密码</label> <label class="input">
												<input type="password" name="nmsApiPwd" value="${nmsApiPwd.name}" placeholder="监控系统接口调用密码">
											</label>
										</section>
										<section class="col col-10">
											<label>监控数据查询接口地址</label> <label class="input">
												<input type="text" name="monitorQuery" value="${monitorQuery.name}" placeholder="监控数据查询接口地址">
											</label>
										</section>
										<section class="col col-10">
											<label>告警数据查询接口地址</label> <label class="input">
												<input type="text" name="alarmQuery" value="${alarmQuery.name}" placeholder="告警数据查询接口地址">
											</label>
										</section>
										<section class="col col-10">
											<label>告警数据确认接口地址</label> <label class="input">
												<input type="text" name="alarmConfirm" value="${alarmConfirm.name}" placeholder="告警数据确认接口地址">
											</label>
										</section>
										<section class="col col-10">
											<label>告警数据隔离接口地址</label> <label class="input">
												<input type="text" name="alarmIsolated" value="${alarmIsolated.name}" placeholder="告警数据隔离接口地址">
											</label>
										</section>
									</div>
								</fieldset>
							</div>
							<div class="tab-pane fade tab-form" id="cocoaConfig">
								<fieldset>
									<div class="row">
										<section class="col col-10">
											<label>直真接口地址</label> <label class="input">
												<input type="text" name="cocoaUrl" value="${cocoaUrl.name}" placeholder="直真接口地址">
											</label>
										</section>
										<section class="col col-10">
											<label>直真接口账号</label> <label class="input">
												<input type="text" name="cocoaUsername" value="${cocoaUsername.name}" placeholder="直真接口账号">
											</label>
										</section>
										<section class="col col-10">
											<label>直真接口密码</label> <label class="input">
												<input type="password" name="cocoaPassword" value="${cocoaPassword.name}" placeholder="直真接口密码">
											</label>
										</section>
									</div>
								</fieldset>
							</div>
							
						</div>

						<footer>
							<button type="submit" class="btn btn-primary smart-form-submit-btn">
								<spring:message code="base.common.ok"></spring:message>
							</button>
							<button type="button" class="btn btn-default btn-cancel"
								data-targetpanel="${targetPanel}">
								<spring:message code="base.common.cancel"></spring:message>
							</button>
						</footer>
					</form>

				</div>

			</div>
	</article>
	</div>
</section>
<%@include file="/WEB-INF/jsp/common/pageSetJS.jsp"%>

