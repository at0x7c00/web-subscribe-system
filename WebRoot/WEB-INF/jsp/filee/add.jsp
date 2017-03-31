<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<section id="widget-grid" class="">

	<!-- START ROW -->
	<div class="row">

		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
				<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">

						<form action="" class="smart-form">
							<fieldset>
								<div class="row">
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="文件夹">
										</label>
									</section>
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="2/12">
										</label>
									</section>
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="2/12">
										</label>
									</section>
								</div>
								<div class="row">
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="2/12">
										</label>
									</section>
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="2/12">
										</label>
									</section>
									<section class="col col-4">
										<label class="input">
											<input type="text" placeholder="2/12">
										</label>
									</section>
								</div>
							</fieldset>

							<footer>
								<button type="submit" class="btn btn-primary" >
									Submit
								</button>
								<button type="button" class="btn btn-default" onclick="window.history.back();">
									Back
								</button>
							</footer>
						</form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			<!-- end widget -->

		</article>
		<!-- END COL -->

	</div>

	<!-- END ROW -->
</section>
