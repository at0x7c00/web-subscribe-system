$.fn.extend({
	//表格排序
	tableColSortable : function(){
		return this.each(function(){
			$(this).click(function(e){
				
				var $this = $(this);
				if(!$this.hasClass("sorting")){
					return;
				}
				var sortfield = $(this).data("sortfield");
				if(!sortfield){
					return;
				}
				var sortDirection = "asc";
				if($this.hasClass("sorting_asc")){
					sortDirection = "desc";
				}
				
				$this.parents(".smart-form").find("input[name=orderField]").val(sortfield);
				$this.parents(".smart-form").find("input[name=orderDirection]").val(sortDirection);
				$this.parents(".smart-form").submit();
			});
		});
	},
	checkAllClick : function(panel){
		return this.each(function(){
			$(this).click(function(){
				var $this = $(this);
				var groupName = $this.data("groupname");
				if($this.get(0).checked){
					$("input[name="+groupName+"]:checkbox",panel).prop("checked","checked").change();
				}else{
					$("input[name="+groupName+"]:checkbox",panel).prop("checked",false).change();
				}
			});
		});
	}
});