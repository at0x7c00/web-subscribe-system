var alertMsg = {
	confirm: function(msg,callback) {
		$.SmartMessageBox({
			title : "<i class='fa fa-warning txt-color-orangeDark'></i> <span class='txt-color-orangeDark'>" +i18nMsg.confirmTip + '</span>',
			content : msg,
			buttons : '['+i18nMsg.no+']['+i18nMsg.yes+']'
		}, function(ButtonPressed) {
			if (ButtonPressed == "Yes" || ButtonPressed == "是") {
				if(callback){
					callback();
				}
			}
		});
	},
	jquiConfirm: function(msg,callback) {
		$("#dialog-message-body").html(msg);
		$("#dialog-message").dialog({
			autoOpen : true,
			modal : true,
			width : 312,
			height : 185,
			title : i18nMsg.confirmTip,
			buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; " + i18nMsg.cancel,
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
				}
			}, {
				html : "<i class='fa fa-check'></i>&nbsp; " + i18nMsg.sure, 
				"class" : "btn btn-danger",
				click : function() {
					if(callback){
						callback();
					}
					$(this).dialog("close");
				}
			}]
		});
	},
	info:function(msg,icon){
		icon = icon || "fa fa-check shake animated";
		/*
		$.smallBox({
			title : i18nMsg.info,
			content : msg,
			color : "#296191",
			iconSmall : "fa fa-check bounce animated",
			timeout : 3000
		});*/
		$.bigBox({
			title : i18nMsg.info,
			content : msg,
			color : "#3276B1",
			//timeout: 6000,
			icon : icon,
			//number : "1",
			timeout : 3000
		});
		
	
	},
	error:function(msg){
		/*$.smallBox({
			title : i18nMsg.error,
			content : msg,
			color : "#C46A69",
			icon : "fa fa-warning swing animated"
		});*/
		$.bigBox({
			title : i18nMsg.error,
			content :msg,
			color : "#C46A69",
			//timeout: 6000,
			icon : "fa fa-warning shake animated",
			//number : "1",
		});
		
		
	}
}