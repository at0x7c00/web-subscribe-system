<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>修改头像</title>
<link rel="stylesheet" type="text/css" href="${basePath}jcrop/jquery.jcrop.css">
<link href="${basePath}dwz-ria/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />

<script src="${pageContext.request.contextPath}/dwz-ria/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${basePath}jcrop/jquery.jcrop.js"></script>
<script type="text/javascript" src="${basePath}dwz-ria/uploadify/scripts/jquery.uploadify.js?_t=<%=new Date().getTime()%>"></script>
<script type="text/javascript" src="${basePath}js/jquery.nailthumb.1.1.min.js?_t=<%=new Date().getTime()%>"></script>
</head>
<body>
	<script type="text/javascript">
		var boxWidth = 400;
		var boxHeight = 400;
		var width = 0;
		var height = 0;
		var img = new Image();
		var jump = ${empty fileManageKey};
		if(!jump){
			img.src = '${basePath}admin/filee/viewPic.do?manageKey=${fileManageKey}';
			img.onload = function() {
				width = $("#cropbox").width();
				height = $("#cropbox").height();
			};
		}
		

		var jcrop_api;
		jQuery(window).load(function() {
			initJcorp();
			initUpload();
		});

		function initJcorp() {
			jQuery('#cropbox').Jcrop(
					{
						onChange : showPreview,
						onSelect : showPreview,
						aspectRatio : 1
					},
					function() {
						jcrop_api = this;
						jcrop_api.animateTo([ width / 8, height / 8,
								width - width / 8, height - height / 8 ]);
					});
		}

		function initUpload() {
			$(":file[uploaderOption]").each(function() {
				var $this = $(this);
				var options = {
					fileObjName : $this.attr("name") || "file",
					auto : true,
					multi : false,
					onUploadError : function() {
						alert("error")
					}
				};

				var uploaderOption = jsonEval($this.attr("uploaderOption"));
				$.extend(options, uploaderOption);
				$this.uploadify(options);
			});
		}

		function showPreview(coords) {
			var rx = parseInt("150") / coords.w;
			var ry = parseInt("150") / coords.h;

			jQuery('#preview').css({
				width : Math.round(rx * width) + 'px',
				height : Math.round(ry * height) + 'px',
				marginLeft : '-' + Math.round(rx * coords.x) + 'px',
				marginTop : '-' + Math.round(ry * coords.y) + 'px'
			});
			//实际坐标计算
			$('#x1').html(coords.x);
			$('#y1').html(coords.y);
			$('#x2').html(coords.x2);
			$('#y2').html(coords.y2);
			$('#w').html(coords.w);
			$('#h').html(coords.h);
		}

		function checkForm() {
			if ('../oneInchPhoto/temp/20140910191428078.jpg' == '/images/none.jpg') {
				alert("请选择图像");
				return false;
			}

			if ($('#x').html() == '') {
				alert("请选择裁剪，鼠标放到大图上按住滑动裁剪");
				return false;
			}
			var strURL = "${basePath}admin/user/updatePhoto.do";
			$.post(strURL, {
				x1 : $("#x1").html(),
				y1 : $("#y1").html(),
				x2 : $("#x2").html(),
				y2 : $("#y2").html(),
				w : $("#w").html(),
				h : $("#h").html(),
				userKey : '${userKey}',
				fileeKey : $('#fileManageKey').val(),
				userName : '${userName}'
			}, function(data) {
				if (data == 'ok') {
					window.close();
				} else {
					alert("修改头像时遇到错误：" + data);
				}
			});
		}

		function jsonEval(data) {
			try {
				if ($.type(data) == 'string')
					return eval('(' + data + ')');
				else
					return data;
			} catch (e) {
				return {};
			}
		}

		function uploadifySuccess(file, data, response) {
			data = eval('(' + data + ')');
			if (!data.success) {
				alert('文件上传时遇到异常:' + data.error);
				return;
			}
			$('#fileManageKey').val(data.file_key);
			width = $("#cropbox").width();
			height = $("#cropbox").height();
			
			if(jump){
				var url = window.location.href;
				if(url.indexOf('?')<0){
					url += "?fileManageKey=" + data.file_key;
				}else{
					url += "&fileManageKey=" + data.file_key;
				}
				//window.location.href = url;
				window.open(url,'_self');
				return;
			}
			
			jcrop_api.setImage('${basePath}admin/filee/viewPic.do?manageKey='
					+ data.file_key);
			$("#preview").attr("src",'${basePath}admin/filee/viewPic.do?manageKey='+ data.file_key);
			jcrop_api.animateTo([ width / 8, height / 8, width - width / 8,
					height - height / 8 ]);
		}

		function uploadifyQueueComplete(queueData) {

		}
	</script>
	<style type="text/css">
.button {
	height: 25px;
	font-size: 12px;
	font-weight: bold;
	color: #183152;
}
</style>
	<input type="button" value="<spring:message code="base.common.submit"/>" onclick="checkForm();" class="button" />

<c:choose>
	<c:when test="${empty fileManageKey}">
		<table>
		<tr>
			<td>
			<div style="padding:2px;background:#c1c1c1">
			<div style="border:1px dashed gray;padding:5px;margin:3px;background: #c1c1c1;height:300px;width:300px;">
			</div>
			</div>
			
			</td>
			<td valign="top">
			<div style="padding:2px;background:#c1c1c1">
				<div style="border:1px dashed gray;padding:5px;margin:3px;background: #c1c1c1;height:150px;width:150px;">
				</div>
				</div>
			</td>
		</tr>
	</table>
	</c:when>
	<c:otherwise>
		<table>
		<tr>
			<td><img src="${basePath}admin/filee/viewPic.do?manageKey=${fileManageKey}" id="cropbox" />
			</td>
			<td valign="top">
				<div style="width:150px;height:150px;overflow:hidden;">
					<img src="${basePath}admin/filee/viewPic.do?manageKey=${fileManageKey}" id="preview" />
				</div>

				<div style="color:#183152;font-size:12px;">
					<span id="w"></span>×<span id="h"></span> <br /> (<span id="x1"></span>, <span id="y1"></span>) (<span id="x2"></span>, <span id="y2"></span>) <input type="text" id="fileManageKey"
						value="${fileManageKey}" style="display:none;" />
				</div></td>
		</tr>
		</table>
	</c:otherwise>
</c:choose>
	<input id="photofile" type="file" name="photofile" class=""
		uploaderOption="{swf:'${basePath}dwz-ria/uploadify/scripts/uploadify.swf?_t=<%=new Date().getTime()%>', uploader:'${basePath}admin/filee/add.do', formData:{folderId:'1',userKey:'${userKey}'}, buttonText:'选择文件', fileSizeLimit:'5000KB',  fileObjName:'photofile', fileTypeDesc:'*.*', fileTypeExts:'*.*', queueSizeLimit:1,auto:true, multi:false, removeCompleted:false, onUploadSuccess:uploadifySuccess, onQueueComplete:uploadifyQueueComplete}" />
</body>
</html>