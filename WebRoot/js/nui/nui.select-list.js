function selectListSelection(repo,selectList){
	selectList.loadSelectList("add",repo.manageKey || repo.id || repo.username);
	return "";
}

$.fn.extend({
	selectListInit : function(){
		return this.each(function(){
			var $this = $(this);
			var dataUrl = $this.data("dataurl");
			var groupName = $this.data("groupname");
			var entityIdName = $this.data("entityidname");
			var method = $this.data("method");
			var initValues = $this.data("initvalues");
			if(!dataUrl || !groupName){
				return true;
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
				},
				success : function(data) {
					$this.html(data);
					initDataTable($this);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
				},
				async : true
			});
			
		});
		
	},
	loadSelectList : function(operation,idVal){
		return this.each(function(){
			var $this = $(this);
			var dataUrl = $this.data("dataurl");
			var groupName = $this.data("groupname");
			var entityIdName = $this.data("entityidname");
			var method = $this.data("method");
			if(!dataUrl || !groupName){
				return true;
			}
			if(operation=='add'){//添加
				var idVals = '';
				var inputs = $("input[name="+groupName+"]",$this);
				for(var i = 0;i<inputs.length;i++){
					idVals += $(inputs[i]).val();
					if(i<inputs.length-1){
						idVals += ',';
					}
				}
				if(idVal){
					if(idVals.length>0){
						idVals += ',';
					}
					idVals += idVal;
				}
				//alert(idVals)
				var data = {htmlType:'yes',};
				data[entityIdName] = idVals;
				data["keyName"] = groupName;
				
				$.ajax({
					type : method || "POST",
					url : dataUrl,
					data : data,
					dataType : 'html',
					cache : true, // (warning: this will cause a timestamp and will call the request twice)
					beforeSend : function() {
					},
					success : function(data) {
						$this.html(data);
						initDataTable($this);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
					},
					async : true
				});
				
			}else if(operation=='delete'){//删除
				if(!idVal){
					return;
				}
				$("input[name="+groupName+"][value="+idVal+"]",$this).remove();
				$this.loadSelectList("add",null);
			}
			
		});
	},
	deleteSelectListItem : function(){
		return this.each(function(){
			$(this).click(function(){
				var $this = $(this);
				var selectlistid=$this.data("selectlist");
				if(!selectlistid) return;
				var selectlist = $("#" + selectlistid);
				if(selectlist.length==0) return;
				var groupname = selectlist.data("groupname");
				if(!groupname) return;
				var forDeleteChk = $("input[name="+groupname+"]:checked");
				if(forDeleteChk.length==0){
					alertMsg.info(i18nMsg.mustselectone);
					return;
				}
				forDeleteChk.each(function(){
					$(this).parent().parent().parent().remove();
				});
				
			});
			
		});
	},
	attachementDialogAdd : function(){
		return this.each(function(){
			$(this).click(function(){
				var $this = $(this);
				var title = $this.attr("title") || '添加文件';
				var selectlist = $this.data("selectlist");
				var targetPanel = $this.data("targetpanel");
				var maxFilesize = $this.data("maxfilesize");
				var acceptedFiles = $this.data("acceptedfiles");
				jquiDialogTodo(title,basePath + 'filee/dialogToAdd.do?selectlist='+selectlist
						+'&formTargetPanel=' + targetPanel
						+'&maxFilesize=' + maxFilesize
						+'&acceptedFiles=' + acceptedFiles);
			});
		});
	},
	
    softwareAttDialogAdd:function(){
    	return this.each(function(){
			$(this).click(function(){
				var $this = $(this);
				var title = $this.attr("title") || '添加软件文件';
				var selectlist = $this.data("selectlist");
				var select2 = $this.data("select2");
				var acceptedfolder = $this.data("acceptedfolder");
				var targetPanel = $this.data("targetpanel");
				var maxFilesize = $this.data("maxfilesize");
				var acceptedFiles = $this.data("acceptedfiles");
				var acceptedType =$this.data("acceptedtype");
				jquiDialogTodo(title,basePath + 'softwareFile/dialogToAdd.do?selectlist='+(selectlist ? selectlist : '')
						+'&select2=' + (select2 ? select2 : '')
						+'&formTargetPanel=' + targetPanel
						+'&maxFilesize=' + maxFilesize
						+'&acceptedFiles=' + acceptedFiles
						+'&acceptedfolder='+acceptedfolder
						+'&acceptedtype='+acceptedType);
			});
		});
	}
});