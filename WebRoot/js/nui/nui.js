var NUI = {
		version : '1.0',
		maincontainer : $('#content'),
		container : $('#content'),
		
		ajaxLoadingMsg  : '<h1 style="font-size:48px;"><i class="fa fa-cog fa-spin"></i></h1><h4> '+i18nMsg.ajaxLoading +'</h4><br/>',
		
		ajaxError : function(xhr, ajaxOptions, thrownError){
			$(".smart-form-submit-btn").removeClass("disabled");
			if (alertMsg) {
				var msg = "";
				if(xhr.status==0 || xhr.status==12029){
					msg+="<div style='margin:2px;'><b>无法连接到服务器:</b></div>"; 
					msg+="<div style='margin:2px;padding:2px;'>请确认您的电脑网络连接正常，否则可能是系统已关闭或出现了异常。</div>"; 
				}
				msg+="<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" 
				+ "<div>ajaxOptions: "+ajaxOptions + "</div>"
				+ "<div>thrownError: "+thrownError + "</div>"
				+ "<div>"+xhr.responseText+"</div>";
				alertMsg.error(msg);
				if(msg.length>400){
					$(".bigBox").css({height:($(window).height()-50) + 'px'});
				}else{
					$(".bigBox").css({height:null});
				}
			} else {
				alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
			}
		},
		ajaxDone:function(json){
			$(".smart-form-submit-btn").removeClass("disabled");
			if(json.statusCode == NUI.statusCode.error) {
				if(json.message && alertMsg) alertMsg.error(json.message);
			} else if (json.statusCode == NUI.statusCode.timeout) {
				if(alertMsg) alertMsg.confirm(json.message || i18nMsg.sessionTimeOut, NUI.loadLogin);
				else NUI.loadLogin();
			}else if(json.statusCode == NUI.statusCode.noPermission){//add by hq 无权限提示框
				if(json.message && alertMsg) alertMsg.error(json.message);
			}else if (json.statusCode == NUI.statusCode.loginOut) {
				window.location.href=json.forwardUrl;
			}else {
				if(json.message && alertMsg) alertMsg.info(json.message);
			};
		},
		ajaxLoad: function(html){
			NUI.maincontainer.css({
				opacity : '0.0'
			}).html(html).delay(50).animate({
				opacity : '1.0'
			}, 200);
		},
		dialogAjaxLoad : function(html){
			$("#dialogBody").html(html);
		},
		maincontainer : null,
		statusCode: {ok:200, error:300, timeout:301,noPermission:1001},
		
		//显示登录框
		loadLogin:function(){
			window.location = basePath + 'loginOut.do';
		},
		
		systemTitle : document.title,
		
		selectMultiRow : false,
		
		getCurrentPanel : function(panelType){
			return (typeof panelType)=='object' ? panelType : (panelType =='dialog' ? $('#myModal') : (panelType=='jquiDialog' ? $('#dialog-message') : NUI.container));
		},
		closeJquiDialog : function(){
			$("#dialog-message").dialog("close");
		},
		ajaxStart : function(){
			$("#dialog-ajaxloading").attr("title","提示");
			$("#dialog-ajaxloading-message-body").html("加载中，请稍等...");
			$("#dialog-ajaxloading").dialog({
				width : 300,
				height : 150,
				modal : true,
				buttons : []
			});
		},
		ajaxEnd : function(){
			$("#dialog-ajaxloading").dialog("close");
		}
};

NUI.maincontainer = $("#content");

/**
 * 页面初始化
 */
function initUI(panelType){
	var panel = (typeof panelType)=='object' ? panelType : (panelType =='dialog' ? $('#myModal') : (panelType=='jquiDialog' ? $('#dialog-message') : NUI.container));
	
	initPagination(panel);
	
	//ajax打开连接
	$('a[target=ajaxToLoad]',panel).ajaxToLoad();
	
	//表单提交按钮
	$(".smart-form-submit-btn",panel).click(function(e){
		var warn = $(this).attr("warn");
		if(warn){
			alertMsg.jquiConfirm(warn,function(){
				$(this).addClass("disabled");
				$(panel).find(".smart-form").submit();
			});
		}else{
			$(this).addClass("disabled");
			$(panel).find(".smart-form").submit();
		}
		
		e.preventDefault();
	});
	
	initDataTable(panel);
	
	$("a[target=ajaxTodo]",panel).ajaxTodo();
	
	$("a[target=dialogTodo]",panel).dialogTodo();
	$("a[target=jquiDialogTodo]",panel).jquiDialogTodo();
	
	//dialog 取消按钮点击事件
	$(".btn-cancel").click(function(){
		if($(this).data("targetpanel") =='dialog'){
			$('#myModal').modal("hide");
		}
	});
	
	//$("input.required").css({background:'url(img/input_bg.png) no-repeat scroll'});
	//$("input.required").css({'background-position' : '100% 0'});
	
	$(".object-select",panel).objectselect(null,panel);
	/*$(".object-select",panel).each(function(){
		var $this = $(this);
		var url = $this.data("queryurl");
		var delay = parseInt($this.data("querydelay")) || 250;
		var method = $this.data("querymethod") || 'POST';
		var placeholder = $this.attr("placeholder");
		var multiple  = $this.data("multiple") || false;
		var initUrl = $this.data("initurl");
		var minLength = $this.data("minlength") || 0;
		var renderdata = $this.data("renderdata");
		var echodata = $this.data("echodata");
		var callCustomSelection = $this.data("customcallback");
		var pageSize = $this.data("pagesize") || 10;
		var type = $this.data("type") || null;
		if(callCustomSelection){
			callCustomSelection = eval(callCustomSelection);
		}
		var selectlistId = $this.data("selectlist") || false;
		$(this).select2({
			placeholder: placeholder,
			multiple: multiple,
			ajax: {
				url: url,
				dataType: 'json',
				delay: delay,
				method:method,
				allowClear:true,
				quietMillis : 300,
				data: function (term, page,type) {
					return {
						queryKey: term, // search term
						pageNum: page,
						numPerPage : pageSize,
						type : type
					};
				},
				results: function (data, page) {
					var more = (page * 10) < data.total_count;
					return { results: data.items, more: more };
				},
				cache: true
			},
			initSelection: function(element, callback) {
				var id = $(element).val();
		        if (id !== "" && initUrl!="") {
		            $.ajax(initUrl + id, {
		                dataType: "json"
		            }).done(function(data) { callback(data,renderdata); });
		        }
			},
			escapeMarkup: function (markup) {return markup; }, // let our custom formatter work
			minimumInputLength: minLength,
		    formatResult: function(rep){
	    		return formatRepo(rep,renderdata);
		    },
		    // omitted for brevity, see the source of this page
		    formatSelection: function(repo){
		    	if(callCustomSelection && $.isFunction(callCustomSelection)){
		    		return callCustomSelection(repo,$("#"+selectlistId,panel),echodata);
		    	}
		    	return formatRepoSelection(repo,echodata);
		    },
	        formatNoMatches: function () { return i18nMsg.select2.nomatches; },
	        formatInputTooShort: function (input, min) { var n = min - input.length; return i18nMsg.select2.pleaseEnter + n + i18nMsg.select2.largeThan; },
	        formatInputTooLong: function (input, max) { var n = input.length - max; return i18nMsg.select2.pleaseDelete + n + i18nMsg.select2.character; },
	        formatSelectionTooBig: function (limit) { return i18nMsg.select2.canOnlySelect + limit + i18nMsg.select2.item; },
	        formatLoadMore: function (pageNumber) { return i18nMsg.select2.loading; },
	        formatSearching: function () { return i18nMsg.select2.searching; },
		});
		
	});*/
	
	$('#form-search-mobile',panel).click(function(){
		if($('.smart-form').hasClass('smart-form-search')){
			$('.smart-form').removeClass('smart-form-search');
			$(this).find('i').removeClass('fa-search').addClass('fa-times');
		}else{
			$('.smart-form').addClass('smart-form-search');
			$(this).find('i').removeClass('fa-times').addClass('fa-search');
		}
	});
	
	$(".select-list",panel).selectListInit();
	$("i[data-toggle='row-detail']",panel).each(function(){
		$(this).click(function(){
			var $this = $(this);
			var isopen = $this.attr("isopen");
			if(isopen){
				$this.removeClass("fa-minus-square").addClass("fa-plus-square");
				this.title="Show Details";
				$this.parent().parent().parent().next().remove();
				$this.removeAttr("isopen");
			}else{
				var dataUrl = $this.data("dataurl");
				var groupName = $this.data("groupname");
				var entityIdName = $this.data("entityidname");
				var method = $this.data("method");
				var initValues = $this.data("initvalues");
				if(!dataUrl || !groupName){
					return;
				}
				var data = {htmlType:'yes',};
				data[entityIdName] = initValues;
				data["keyName"] = groupName;
				$.ajax({
					type : method || "POST",
					url : dataUrl,
					data : data,
					dataType : 'html',
					cache : true, // (warning: this will cause a timestamp and will call the request twice)
					beforeSend : function() {
						//NUI.ajaxStart();
					},
					success : function(data) {
						
						var $div = $('<div class="modal-body no-padding" style="border:8px solid #ddd"/>');
						
						var $tr = $("<tr/>");
						var $td = $("<td/>");
						var $tdNull = $("<td/>");
						$td.css("padding","0px");
						var $parentTr = $this.parents("tr").first();
						
						$td.attr("colspan",$parentTr.find("td").length);
						
						$tr.append($td).append($tdNull);
						$div.html(data);
						$td.append($div);
						
						$tr.append($tdNull);
						$tr.append($td);
						
						$tr.insertAfter($parentTr);
						initUI($tr);
						
						$this.removeClass("fa-plus-square").addClass("fa-minus-square");
						$this.title="Hide Details";
						$this.attr("isopen",true);
						//NUI.ajaxEnd();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
						//NUI.ajaxEnd();
					},
					async : true
				});
			}
			
		});
		
	});
	
	$(".select-list-delete-btn",panel).deleteSelectListItem();
	$(".attachement-dialog-add-btn",panel).attachementDialogAdd();
	$(".softwareAtt-dialog-add-btn",panel).softwareAttDialogAdd();
	
	$(".tab-form",panel).tabForm();
	
	$("a[target=nuiExport]",panel).nuiExport();
	
	$(".ztree-select",panel).ztreeSelect();
	$(".ztree-select-lookup",panel).ztreeSelectLookup();
	
	//导入相关
	$(".smart-form-upload",panel).dropzone({
		//url: "/file/post",
		addRemoveLinks : true,
		maxFiles : 1,
		maxFilesize: 500,
		paramName : 'photofile',
		dictResponseError: 'Error uploading file!',
		acceptedFiles : null,
		success : function(e,data){
			alert(data.success+"," + data.file_key);
			$("input[name=fileKey]",panel).val(data.file_key);
		}
	});
	
	//对话框添加附件
	$(".attachement-dropzone",panel).each(function(){
		var $this = $(this);
		var selectlist = $this.data("selectlist");
		var select2 = $this.data("select2");
		var formTargetPanel = $this.data("formtargetpanel");
		var maxFilesize = $this.data("maxfilesize");
		var acceptedFiles = $this.data("acceptedfiles");
		//var acceptedMimeTypes = $this.data("acceptedfiles");
		$this.dropzone({
			//url: "/file/post",
			addRemoveLinks : true,
			maxFiles : 1,
			maxFilesize: maxFilesize,
			paramName : 'photofile',
			dictResponseError: 'Error uploading file!',
			//acceptedMimeTypes : null,
			acceptedFiles : acceptedFiles,
			accept : function(file,done){
				done();
			},
			success : function(e,data){
				if(selectlist!=undefined && selectlist!=''){
					$("#" + selectlist,NUI.getCurrentPanel(formTargetPanel)).loadSelectList("add",data.file_key);
				}else{
					$("#" + select2,NUI.getCurrentPanel(formTargetPanel)).objectselect(data.id,panel);
				}
				NUI.closeJquiDialog();
			}
		});
	});
	
	$(".unit-table",panel).unitTable();
	
	$(".area-select").each(function(){
		$(this).change(function(){
			var areaKey = $(this).val();
			loadProvinces(areaKey);
		});
		
		var areaKey = $(this).val();
		var provinceKey = $("#provinceKey").val(); 
		if(areaKey && areaKey!='' && provinceKey==''){
			loadProvinces(areaKey);
		}
	});
	
	
	$(".page-select-div",panel).click(function(){
		var _this = $(this);
		$("#pageKey",panel).val(_this.data("page-key"));
		_this.parents("form").first().submit();
	});
	
    $(".change-to-refresh",panel).change(function(){
    	$(this).parents("form").first().submit();
    });
	
    
    $(".ckeditor-able").ckeditor({
		toolbar: [
				//{ name: 'document', items: [ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ] },	// Defines toolbar group with name (used to create voice label) and items in 3 subgroups.
				[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],			// Defines toolbar group without name.
				['Bold','Italic','Underline','Strike','Subscript','Superscript','Image','Flash'],
				['-','Outdent','Indent'],
				//左对齐             居中对齐          右对齐          两端对齐
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				// 样式       格式      字体    字体大小
                ['Styles','Format','Font','FontSize'],
				//文本颜色     背景颜色
                ['TextColor','BGColor','FullPage']
			], 
		width:800,
		height:150,
		language: 'zh-cn' //简体中文
	});
    
    
}

function loadProvinces(areaKey){
	$.get(basePath + "area/getProvincesByArea.do?manageKey=" + areaKey,function(d){
		$("#provinceKey").find("option:not(:first)").remove();
		for(var i = 0;i<d.length;i++){
			$("#provinceKey").append($("<option value=\""+d[i].manageKey+"\">"+d[i].name+"</option>"))
		}
	});
}

function formatRepo (repo,renderdata,segment) {
	if(!renderdata){
		return "--";
	}
    if (repo.loading) return repo.text;
	var a = renderdata.split(",");
	var val = '';
	for(var i = 0;i<a.length;i++){
		if(repo[a[i]] && repo[a[i]]!=''){
			if(i>0){
				val += segment;
			}
			val += repo[a[i]];
		}
	}
    var markup = '<div class="clearfix"><div class="col-sm-3" title="'+val+'">' + val + '</div></div>';
    return markup;
}

function formatRepoSelection (repo,renderdata,segment) {
	var d = null;
	try{
		d = repo[0];
	} catch(e){
		d = repo;
	}
	if(!d){
		d = repo;
	}
	var a = renderdata.split(",");
	var val = '';
	for(var i = 0;i<a.length;i++){
		val += d[a[i]]
		if(i<a.length-2){
			val += "*";
		}
	}
	return val;
}

function initPagination(panel){
	$(".pagination",panel).find("a").pageClick();
	$('select[name=numPerPage]',panel).pageSizeChange();
}

function initDataTable(panel){
	$('.sorting',panel).tableColSortable();
	$(".smart-form-checkall",panel).checkAllClick(panel);
	
	$(".table .checkbox input[type=checkbox]",panel).unbind("change").change(function(e){
		var $this = $(this).parent().parent().parent();
		var target = $this.attr("target");
		if(!$(this).prop("checked")){
			$this.find("td").removeAttr("style");
			$this.removeClass("selected-tr");
			//$this.find("input[name="+target+"]").prop("checked",false).change()
		}else{
			$this.addClass("selected-tr");
			$this.find("td").css("background","#ecf3f8");
			//$this.find("input[name="+target+"]").prop("checked","checked").change();
		}
		
		var hideChk = $(this);
		if($("input[name="+hideChk.attr("name")+"]:checked").length==0){
			NUI.selectMultiRow = false;
		}
		//console.debug(NUI.selectMultiRow)
		
	});
	$(".table .checkbox",panel).unbind("click").click(function(){
		var hideChk = $(this).find("input[type=checkbox]:first");
		if(hideChk.prop("chcked")){
			NUI.selectMultiRow = true;
		}else{
			//console.debug($("input[name="+hideChk.attr("name")+"]:checked").length)
			NUI.selectMultiRow = $("input[name="+hideChk.attr("name")+"]:checked").length > 0;
		}
	});
	$(".table>tbody>tr",panel).each(function(){
		$(this).find("td:not(:first)").unbind("click").click(function(){
			
			var $this = $(this).parent();
			var target = $this.attr("target");
			var rel = $this.attr("rel");
			//console.debug(NUI.selectMultiRow)
			if(!NUI.selectMultiRow){
				//清除表的行选择状态
				$(this).parent().parent().find("tr").each(function(){
					var tr = $(this);
					if(rel==tr.attr("rel")){
						return;
					}
					tr.find("td").removeAttr("style");
					tr.removeClass("selected-tr");
					tr.find("input[name="+target+"]").prop("checked",false).change();
				});
			}
			
			if($this.hasClass("selected-tr")){
				$this.find("td").removeAttr("style");
				$this.removeClass("selected-tr");
				$this.find("input[name="+target+"]").prop("checked",false).change()
			}else{
				$this.addClass("selected-tr");
				$this.find("td").css("background","#ecf3f8");
				$this.find("input[name="+target+"]").prop("checked","checked").change();
			}
		});
	});
}


$.fn.extend({
	ajaxToLoad : function(){
		return this.each(function(){
			var $this = $(this);
			$this.click(function(e){
				
				e.preventDefault();
				var $this = $(e.currentTarget);
				if($this.attr("href")=='javascript:void(0);'){
					return;
				}
				var href = unescape($this.attr("href")).replaceTmById($(e.target).parents(".smart-form"));
				
			    // if parent is not active then get hash, or else page is assumed to be loaded
				//if (!$this.attr('target')) {

				    // update window with hash
				    // you could also do here:  $.device === "mobile" - and save a little more memory

				    if ($.root_.hasClass('mobile-view-activated')) {
					    $.root_.removeClass('hidden-menu');
					    
					    window.setTimeout(function(){
					    	if (window.location.search) {
								window.location.href =
									window.location.href.replace(window.location.search, '')
										.replace(window.location.hash, '') + '#' + href;
							} else {
								window.location.hash = href
							}
					    }, 150);
					    // it may not need this delay...
				    } else {
						if (window.location.search) {
							window.location.href =
								window.location.href.replace(window.location.search, '')
									.replace(window.location.hash, '') + '#' + href;
						} else {
							window.location.hash = href;
						}
				    }
			    //}
				
				var t  = '';
				if(window.location.hash.indexOf('__t')>=0){
					window.location.hash = window.location.hash.substring(0, window.location.hash.indexOf("__t=")+4) + (parseInt(window.location.hash.substring(window.location.hash.indexOf('__t') + 4)) + 1);
				}else{
					if(window.location.hash.indexOf('?')>=0){
						t += '&__t=';
					}else{
						t += '?__t=';
					}
					t += new Date().getTime();
				}
				window.location.hash = window.location.hash + t;
				
			});
		});
	},
	
});


(function($){
	/**
	 * 扩展String方法
	 */
	$.extend(String.prototype, {
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		isInteger:function(){
			return (new RegExp(/^\d+$/).test(this));
		},
		isNumber: function(value, element) {
			return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
		},
		trim:function(){
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith:function (pattern){
			return this.indexOf(pattern) === 0;
		},
		endsWith:function(pattern) {
			var d = this.length - pattern.length;
			return d >= 0 && this.lastIndexOf(pattern) === d;
		},
		replaceSuffix:function(index){
			return this.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index);
		},
		trans:function(){
			return this.replace(/&lt;/g, '<').replace(/&gt;/g,'>').replace(/&quot;/g, '"');
		},
		encodeTXT: function(){
			return (this).replaceAll('&', '&amp;').replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;");
		},
		replaceAll:function(os, ns){
			return this.replace(new RegExp(os,"gm"),ns);
		},
		replaceTm:function($data){
			if (!$data) return this;
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				return $data[$1.replace(/[{}]+/g, "")];
			});
		},
		replaceTmById:function(_box,singleparam){
			var $parent = _box || $(document);
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				var paramName = $1.replace(/[{}]+/g, "");
				var $input = $parent.find("#"+paramName);
				if($input.get(0)){
					//如果按照id查询能直接获取到该input，那就直接用这个值
					return $input.val() ? $input.val() : $1;
				}else{
					//否则按照name来查询
					$input = $parent.find("input[name="+paramName+"]:checkbox:checked");
					 if($input.length==0){
						 return $1;//没找到值
					 }else if((singleparam==paramName && $input.length>1)){//this param must be unique in current page
						 //最多选一个
						 return $1;
					 }else if((singleparam==paramName && $input.length==1)){//ok
						 return $input.val();
					 }else{
						 //选择多个
						 var res = "";
						 $input.each(function(i){
							 res+=(i==0)?$(this).val():"&"+paramName+"="+$(this).val();
						 });
						 return res;
					 }
				}
			});
		},
		isFinishedTm:function(){
			return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		},
		isValidPwd:function() {
			return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this)); 
		},
		isValidMail:function(){
			return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
		},
		isSpaces:function() {
			for(var i=0; i<this.length; i+=1) {
				var ch = this.charAt(i);
				if (ch!=' '&& ch!="\n" && ch!="\t" && ch!="\r") {return false;}
			}
			return true;
		},
		isPhone:function() {
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		}
	});
	
})(jQuery);

$.fn.modal.Constructor.prototype.enforceFocus = function() {};



