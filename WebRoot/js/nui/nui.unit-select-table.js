function unitCheckValidate(unitTable){
	var chks = unitTable.find("input[type=checkbox]");
	if(chks.length<3){
		return true;
	}
	var findFirst = false;
	var findLast = false;
	
	var validateSuccess = true;
	for(var i = 0; i<chks.length ; i++){
		if(!findFirst){
			if(chks[i].checked==true){
				findFirst = true;
			}
		}else if(!findLast){
			if(chks[i].checked==false){
				findLast = true;
			}
		}else {
			if(chks[i].checked==true){
				validateSuccess = false;
				break;
			}
		}
	}
	return validateSuccess;
}

$.fn.extend({
	
	unitTable : function(){
		return this.each(function(){
			
			var $this = $(this);
			$this.find("tr").each(function(){
				$(this).click(function(){
					
					var chk = $(this).find("input[type=checkbox]").first();
					var chkValBak = chk.get(0).checked;
					if(chk.get(0).checked){
						chk.prop("checked",false);
					}else{
						chk.prop("checked",true);
					}
					chk.change();
					
					/*var unitCountLimit = $("input[name=unitNums]").first().val();
					var checkUnitCount = $("input[type=checkbox]:checked",$(this).parent()).length;
					if(unitCountLimit!=checkUnitCount){
						alertMsg.info("请选择" + unitCountLimit + "个U位!");
						chk.prop("checked",chkValBak);
						chk.change();
						return;
					}*/
					
					if(!unitCheckValidate($this)){
						alertMsg.info(i18nMsg.unitMustBeContinuous);
						chk.prop("checked",chkValBak);
						chk.change();
					}
					
				});
			});
			
		});
	}
});