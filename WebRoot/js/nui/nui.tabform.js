var tabForm = {
		canAdd  : true,
		getCanAdd : function(){
			return tabForm.canAdd;
		},
		setCanAdd : function(v){
			tabForm.canAdd = v;
		}
};
function addTabFormRow(tabFormCfg,async,idVal,otherParams){
	
	//if(!tabForm.getCanAdd()){
	//	alertMsg.info(i18nMsg.tofast,"fa fa-warning shake animated");
	//	return;
	//}
	$(tabFormCfg.dataTable).incrementTrIndex();
	var trindex = $(tabFormCfg.dataTable).getTrIndex();
	tabFormCfg.panel.find("input[type=hidden][name="+tabFormCfg.trTarget+"TrCount]").val(trindex);
	var data = {trTarget  : tabFormCfg.trTarget,trIndex : trindex, propName : tabFormCfg.trTarget+"List",manageKey:(idVal ? idVal : '')};
	if(otherParams){
		for(var pname in otherParams){
			data[pname] = otherParams[pname];
		}
	}
	$.ajax({
		type : "GET",
		url : tabFormCfg.formurl,
		data : data,
		dataType : 'html',
		cache : true, // (warning: this will cause a timestamp and will call the request twice)
		beforeSend : function() {
			tabForm.setCanAdd(false);
		},
		success : function(data) {
			tabFormCfg.dataTable.find("tbody").append($("<tr  target='"+tabFormCfg.trTarget+"Chk' rel='"+trindex+"'>" + data + "</tr>"));
			//initDataTable($this);
			initUI(tabFormCfg.panel);
			tabForm.setCanAdd(true);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
		},
		async : async
	});
	
}
$.fn.extend({
	tabForm : function(){
		return this.each(function(){
			var $this = $(this);
			//var formurl = $this.data("formurl");
			//var echourl = $this.data("echourl");
			//var initdata = $this.data("initdata");
			//var trTarget = $this.data("trtarget");
			//var dataTable = $this.find(".tab-form-table").first();
			
			var tabFormCfg = {
					panel : $this,
					formurl:$this.data("formurl"),
					//echourl:$this.data("echourl"),
					initdata:$this.data("initdata"),
					trTarget:$this.data("trtarget"),
					dataTable:$this.find(".tab-form-table").first()	
			};
			
			//回显
			if(tabFormCfg.initdata && tabFormCfg.initdata!=''){
				var  idVals = tabFormCfg.initdata.split(",");
				for(var i = 0; i<idVals.length;i++){
					addTabFormRow(tabFormCfg,false,idVals[i]);
				}
			}
			
			//为添加按钮绑定事件
			$this.find(".tab-form-menu .add-btn").each(function(){
				$(this).click(function(){
					addTabFormRow(tabFormCfg,true);
				});
			});
			
			//为删除按钮绑定时间
			$this.find(".tab-form-menu .delete-btn").each(function(){
				$(this).click(function(){
					var dataTable = $this.find(".tab-form-table").first();
					var trForDelete = dataTable.find("input[name="+tabFormCfg.trTarget+"Chk]:checked");
					if(trForDelete.length==0){
						alertMsg.info(i18nMsg.pleaseSelect,"fa fa-warning shake animated");
						return;
					}
					trForDelete.each(function(){
						$(this).parent().parent().parent().remove();
					});
				});
			});
			
		});
	},
	getTrIndex : function(){
		var trindex = $(this).attr("trindex") || "-1";
		trindex = parseInt(trindex);
		if(trindex<0) return 0;
		return trindex;
	},
	incrementTrIndex : function(increment){
		increment = increment || 1;
		var trindex = $(this).attr("trindex") || "-1";
		trindex = parseInt(trindex);
		trindex += increment;
		$(this).attr("trindex",trindex);
	},
	batchAddBySoftwareFile : function(softwareKey,serverKey){
		if(!softwareKey || softwareKey==''){
			return;
		}
		var $this = $(this);
		var tabFormCfg = {
			panel : $this,
			formurl:$this.data("formurl"),
			//echourl:$this.data("echourl"),
			initdata:$this.data("initdata"),
			trTarget:$this.data("trtarget"),
			dataTable:$this.find(".tab-form-table").first()	
		};
		$.ajax({
			type : "GET",
			url : tabFormCfg.formurl,
			data : {softwareKey :softwareKey,serverKey:serverKey},
			dataType : 'json',
			cache : true, 
			beforeSend : function() {
				tabForm.setCanAdd(false);
			},
			success : function(datas) {
				$this.find(".tab-form-table>tbody>tr").remove();//清空之前的
				for(var i = 0;i<datas.length;i++){
					var otherParams = {keyName : datas[i].keyName,value : datas[i].value};
					addTabFormRow(tabFormCfg,false,null,otherParams);
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alertMsg.error('<h4 style="margin-top:10px; display:block; text-align:left"> Error '+xhr.status+':' + xhr.statusText+'!</h4>' + xhr.responseText);
			},
			async : true
		});
	}
	
});


