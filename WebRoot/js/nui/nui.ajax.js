//Ajax提交form表单
function ajaxSearch(form,targetPanel){
	var $form = $(form);
	$.ajax({
		type: $form.attr("method") || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"html",
		cache: false,
		
		beforeSend : function(){
			ajaxLoading(targetPanel=='dialog' ? $("#dialogBody") : null);
		},
		
		success: targetPanel=='dialog' ? NUI.dialogAjaxLoad : NUI.ajaxLoad,
		error: NUI.ajaxError
	});
	return false;
}

function navTabAjaxDone(json){
	NUI.ajaxDone(json);
	if (json.statusCode == NUI.statusCode.ok && json.callbackType!='none'){
		$(".smart-form",$("#content")).submit();
	}
}

function dialogAjaxDone(json){
	NUI.ajaxDone(json);
	if ("forward" == json.callbackType) {
		dialogTodo(null,basePath + json.forwardUrl);
	}else  if (json.statusCode == NUI.statusCode.ok  && json.callbackType!='none'){
		$("#myModal").modal("hide");
		$(".smart-form",$("#content")).submit();
	}
}
/**
 * 普通ajax表单提交
 * @param {Object} form
 * @param {Object} callback
 * @param {String} confirmMsg 提示确认信息
 */
function validateCallback(form, callback, confirmMsg) {
	var $form = $(form);
	$form.validate({ignore:".ignore"});
	if (!$form.valid()) {
		alertMsg.info(i18nMsg.formValidateFailed,"fa fa-warning shake animated");
		$(".smart-form-submit-btn").removeClass("disabled");
		return false;
	}
	if(!tryToValidateNetworkcard($form)){
		$(".smart-form-submit-btn").removeClass("disabled");
		return false;
	}
	//将tabaddfrom表单中在，值为空的*.id类型字段remove掉
	//alert($form.find(".tab-form input[name$='.id']").length);
	$form.find(".tab-form input[name$='.id'],.tab-form select[name$='.id']").each(function(){ 
		var $ipt = $(this)
		if(!$ipt.val() || $ipt.val()==""){
			$ipt.remove();
		}
	});
	
	var _submitFn = function(){
		try{
			$.ajax({
				type: $form.attr("method") || 'POST',
				url:$form.attr("action"),
				data:$form.serializeArray(),
				dataType:"json",
				cache: false,
				success: callback || NUI.ajaxDone,
				error: NUI.ajaxError
			});
		}catch(e){
			alert("ajax请求时出现异常:"+e.message);
		}
	}
	
	if (confirmMsg) {
		alertMsg.confirm(confirmMsg,_submitFn);
	} else {
		_submitFn();
	}
	
	return false;
}
function tryToValidateNetworkcard(form){
	var unitTable = $(".unit-table",form).first();
	if($("input[name=unitNums]",form).length==0 || unitTable.find("tr").length==0){
		return true;
	}
	var unitCountLimit = $("input[name=unitNums]",form).first().val();
	if(!unitCountLimit || unitCountLimit==''){
		return true;
	}
	var checkUnitCount = $(".unit-table",form).first().find("input[type=checkbox]:checked").length;
	var action = form.attr("action");
	if(checkUnitCount==0)return true;
	if(unitCountLimit!=checkUnitCount&&action!='rack/add.do'){
		alertMsg.info("请选择" + unitCountLimit + "个U位!");
		return false;
	}
	return true;
}

function ajaxTodo(url, callback,method){
	var $callback = callback || navTabAjaxDone;
	if (! $.isFunction($callback)) $callback = eval('(' + callback + ')');
	$.ajax({
		type:method||'POST',
		url:url,
		dataType:"json",
		cache: false,
		success: $callback,
		error: NUI.ajaxError
	});
}

function dialogTodo(title,url,modalId){
	var modalId = modalId|| 'myModal';
	$.ajax({
		type : "GET",
		url : url,
		data : {targetPanel : 'dialog'},
		dataType : 'html',
		cache : true, // (warning: this will cause a timestamp and will call the request twice)
		beforeSend : function() {
			if(title){
				$("#myModalLabel").html(title);
			}
			$("#myModal").modal();
			ajaxLoading($("#dialogBody"));
		},
		success : function(data) {
			try{
				var json = eval("("+ data+ ")");
				NUI.ajaxDone(json);
			}catch(e){
				$("#dialogBody").html(data);
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
		},
		async : true
	});
}

function jquiDialogTodo(title,url,width,height,btns){
	width = width || 760;
	height = height || 400;
	btns = btns || [{
		html : "<i class='fa fa-check'></i>&nbsp; " + i18nMsg.sure, 
		"class" : "btn btn-primary",
		click : function() {
			//if(callback){
			//	callback();
			//}
			$(this).dialog("close");
		}
	}];
	$.ajax({
		type : "GET",
		url : url,
		data : {targetPanel : 'jquiDialog'},
		dataType : 'html',
		cache : true, // (warning: this will cause a timestamp and will call the request twice)
		beforeSend : function() {
			//$("#myModalLabel").html(title);
			//$("#myModal").modal();
			//ajaxLoading($("#dialog-message-body"));
		},
		success : function(data) {
			try{
				var json = eval("("+ data+ ")");
				NUI.ajaxDone(json);
			}catch(e){
				//$("#dialogBody").html(data);
				$("#dialog-message-body").html(data);
				$("#dialog-message").dialog({
					autoOpen : true,
					modal : true,
					width : width,
					height : height,
					title : title,
					buttons : btns
				});
				
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
		},
		async : true
	});
	
}

function ajaxRequest(url){
	
}

//显示Loading...状态信息
function ajaxLoading(target){
	if(!target){
		NUI.maincontainer.css({opacity : '1.0'}).html('<div style="text-align:center;margin-top:'+($(window).height() - 75 - 90)/2+'px;">'+NUI.ajaxLoadingMsg+'</div>');
	}else{
		target.css({opacity : '1.0'}).html('<div style="text-align:center;">'+NUI.ajaxLoadingMsg+'</div>');
	}
}

$.fn.extend({
	ajaxTodo : function(){
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".smart-form"));
				var method = $this.attr("method");
				
				if (!url.isFinishedTm()) {
					alertMsg.info($this.attr("warn") || i18nMsg.pleaseSelect);
					return false;
				}
				var confirm = $this.attr("confirm");
				if (confirm) {
					alertMsg.jquiConfirm(confirm,function(){
							ajaxTodo(url, $this.attr("callback"),method);
					});
				} else {
					ajaxTodo(url, $this.attr("callback"),method);
				}
				event.preventDefault();
			});
		});
	},
	
	dialogTodo : function(){
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				event.preventDefault();
				
				var form = $(event.target).parents(".smart-form").first();
				var mustone = $(this).data("mustone") ;//至少一个
				var onlyone = $(this).data("onlyone") ;//最多选择一个
				
				var rel =$(this).attr("rel");
				
				var mone = mustone=='true' || mustone =='1' || mustone == 'yes';
				var oone = onlyone=='true' || onlyone =='1' || onlyone == 'yes';
				var checkboxs = $("input[name="+rel+"]:checked");
				
				if(mustone && checkboxs.length==0){
					alertMsg.info(i18nMsg.mustselectone,"fa fa-warning shake animated");
					return;
				}
				
				if(onlyone && checkboxs.length>1){
					alertMsg.error(i18nMsg.onlyselectone);
					return;
				}
				
				var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".smart-form"));
				dialogTodo($this.attr("title") || url,url);
			});
		});
	},
	jquiDialogTodo : function(){
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				event.preventDefault();
				
				var form = $(event.target).parents(".smart-form").first();
				var mustone = $(this).data("mustone") ;//至少一个
				var onlyone = $(this).data("onlyone") ;//最多选择一个
				
				var rel =$(this).attr("rel");
				var width = $(this).attr("width");
				var height = $(this).attr("height");
				var mone = mustone=='true' || mustone =='1' || mustone == 'yes';
				var oone = onlyone=='true' || onlyone =='1' || onlyone == 'yes';
				var checkboxs = $("input[name="+rel+"]:checked");
				
				if(mustone && checkboxs.length==0){
					alertMsg.info(i18nMsg.mustselectone,"fa fa-warning shake animated");
					return;
				}
				
				if(onlyone && checkboxs.length>1){
					alertMsg.error(i18nMsg.onlyselectone);
					return;
				}
				
				var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".smart-form"));
				jquiDialogTodo($this.attr("title") || url,url,width,height);
			});
		});
	},
	nuiExport : function(){
		function _doExport($this) {
			var $form = $this.parents(".smart-form").first();
			var url = $this.attr("href");
			var model = $this.data("model") || "";
			window.open(url+(url.indexOf('?') == -1 ? "?" : "&")+$form.serialize() + "&dataModel=" + model);
		}
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				var title = $this.attr("title");
				if (title) {
					alertMsg.jquiConfirm(i18nMsg.sureof + title + i18nMsg.ma, function(){_doExport($this);});
				} else {_doExport($this);}
				event.preventDefault();
			});
		});
	}
	
});