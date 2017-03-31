$.fn.extend({
	ztreeSelect : function(){
		return this.each(function(){
				var $this = $(this);
				var zNodes = eval($this.data("datalist"));
				var fromPanel =$this.data("frompanel");
				var propName = $this.data("propname");
				var setting = {
					check: {
						enable: false,
						chkboxType : { "Y" : "ps", "N" : "ps" }
					},
					data: { 
						simpleData: {
							enable: true
						} 
					},
					callback : {
						onCheck : function(){
							doWhenCheck();
						},
						onClick : function(event, treeId, treeNode, clickFlag){
							//alert(fromPanel+"," + propName)
							$("select[name="+propName+"]",NUI.getCurrentPanel(fromPanel)).val(treeNode.id == 0 ? '' : treeNode.id);
							$("#dialog-message").dialog("close");
						}
					}
					
				};

				function doWhenCheck(){
					/*var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
					var checkedNodes=zTreeObj.getCheckedNodes(true);
					var val = '';
					for(var i = 0;i<checkedNodes.length;i++){
						val +=  checkedNodes[i].id;
						if(i<checkedNodes.length-1){
							val += ",";
						}
					}
					$("#functionPointIds").val(val);*/
				}
				
				$.fn.zTree.init($(this), setting, zNodes);
			
		});
	},
	ztreeSelectLookup : function(){
		return this.each(function(){
			var url = $(this).data("url");
			var width = $(this).data("width") || 360;
			var height = $(this).data("height") || 388;
			var title = $(this).attr("title") || i18nMsg.pleaseSelect;
			var _this = $(this);
			$(this).click(function(e){
				var btns = [{
							html : "<i class='fa fa-mail-forward'></i>&nbsp; " + i18nMsg.cancel, 
							"class" : "btn btn-cancel btn-default",
							click : function() {
								$(this).dialog("close");
							}
							},	
							{
								html : "<i class='fa fa-times'></i>&nbsp; " + i18nMsg.clear, 
								"class" : "btn btn-danger",
								click : function() {
									_this.val("");
									$(this).dialog("close");
								}
							}
				];
				jquiDialogTodo(title,url,width,height,btns);
				e.preventDefault();
				return false;
			});
		});
	}
});