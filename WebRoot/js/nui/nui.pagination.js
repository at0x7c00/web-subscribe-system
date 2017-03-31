$.fn.extend({
	//分页页码点击事件
	pageClick:function(){
		return this.each(function(){
			var $this = $(this);
			$this.click(function(e){
				if(!$(this).parent().hasClass("disabled")){
					var pageNum = $(this).data("page-value");
					var smartForm = $this.parents(".smart-form");
					smartForm.find("input[name=pageNum]").val(pageNum);
					smartForm.submit();
				}
				e.preventDefault();
			});
		});
	},
	//pageSize改变事件
	pageSizeChange : function(){
		return this.each(function(){
			var $this = $(this);
			$this.change(function(e){
				$this.parents(".smart-form").first().submit();
			});
		});
	}
	
});