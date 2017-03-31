$.shortcut_dropdown1 = $('#showcut1');
		$.shortcut_dropdown2 = $('#showcut2');
	/*	$("#show-shortcut2").click(function(){
		if ($.shortcut_dropdown2.is(":visible")) {
			shortcut_buttons_hide($.shortcut_dropdown2);
		} else {
			shortcut_buttons_show($.shortcut_dropdown2);
		}
		e.preventDefault();
		
		});*/
		$("#show-shortcut1").click(function (){
		if ($.shortcut_dropdown1.is(":visible")) {
			shortcut_buttons_hide($.shortcut_dropdown1);
		} else {
			shortcut_buttons_show($.shortcut_dropdown1);
			getNodeList();
			
		}
	
		e.preventDefault();
		
		});
		
		function shortcut_buttons_hide( shortcut_dropdown ) {
		shortcut_dropdown.animate({
			height : "hide"
		}, 300, "easeOutCirc");
		$.root_.removeClass('shortcut-on');

	}
	function shortcut_buttons_show(shortcut_dropdown ) {
		shortcut_dropdown.animate({
			height : "show"
		}, 200, "easeOutCirc")
		$.root_.addClass('shortcut-on');
		
		
	}
	
	$(document).mouseup(function(e) {
		if (!$.shortcut_dropdown1.is(e.target)// if the target of the click isn't the container...
		&& $.shortcut_dropdown1.has(e.target).length === 0) {
			shortcut_buttons_hide($.shortcut_dropdown1);
		}
	});
	
	function getNodeList(){
		
		$.get("company/nodeSwitch.do",function(data){
			$("div[id='showcut1']").html(data);
		});
	}
	
	function getServiceGroup(manageKey){
		$.get("node/getNodeList.do?comManageKey="+manageKey,function(data){
			var liLength = $("div[id='showcut1']").children("ul").length;
			if(liLength>1){
				$("div[id='showcut1']").children("ul").eq(1).remove();
			}
			$(data).appendTo("div[id='showcut1']");
		});
		
		
	}
	
	function getNode(manageKey){
		
		$.cookie('selectedNode', manageKey);
		
		//alert($.cookie('selectedNode'));
	}