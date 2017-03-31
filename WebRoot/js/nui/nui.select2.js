$.fn.extend({
	objectselect : function(val,panel){
		return this.each(function(){
			var $this = $(this);
			if(val){//初始值
				$this.val(val);
			}
			var url = $this.data("queryurl");
			var delay = parseInt($this.data("querydelay")) || 250;
			var method = $this.data("querymethod") || 'POST';
			var placeholder = $this.attr("placeholder");
			var multiple  = $this.data("multiple") || false;
			var initUrl = $this.data("initurl");
			var params = $this.data("params");
			var segment = $this.data("segment");
			if(!segment || segment==''){
				segment = "-";
			}
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
						var res = {
							queryKey: term, // search term
							pageNum: page,
							numPerPage : pageSize,
							type : type
						};
						var paramsArray = params ? params.split(",") : [];
						var p = $this.parents(".smart-form");
						if(p.length==0){
							p = $this.parents("#wizard-1");
						}
						for(var i = 0;i<paramsArray.length;i++){
							var ele = $("input[name="+paramsArray[i]+"],select[name="+paramsArray[i]+"]",p);
							res[paramsArray[i]] = ele.first().val();
						}
						return res;
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
		    		return formatRepo(rep,renderdata,segment);
			    },
			    // omitted for brevity, see the source of this page
			    formatSelection: function(repo){
			    	if(callCustomSelection && $.isFunction(callCustomSelection)){
			    		return callCustomSelection(repo,$("#"+selectlistId,panel),echodata);
			    	}
			    	return formatRepoSelection(repo,echodata,segment);
			    },
		        formatNoMatches: function () { return i18nMsg.select2.nomatches; },
		        formatInputTooShort: function (input, min) { var n = min - input.length; return i18nMsg.select2.pleaseEnter + n + i18nMsg.select2.largeThan; },
		        formatInputTooLong: function (input, max) { var n = input.length - max; return i18nMsg.select2.pleaseDelete + n + i18nMsg.select2.character; },
		        formatSelectionTooBig: function (limit) { return i18nMsg.select2.canOnlySelect + limit + i18nMsg.select2.item; },
		        formatLoadMore: function (pageNumber) { return i18nMsg.select2.loading; },
		        formatSearching: function () { return i18nMsg.select2.searching; },
			});
		});
	}
});