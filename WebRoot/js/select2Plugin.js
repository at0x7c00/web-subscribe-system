$(function($){
	$('.js-data-example-ajax').select2({
		placeholder: "请选择……",
		ajax: {
			url: siteUrl+"getUsers.do",
			dataType: 'json',
			delay: 250,
			method:'POST',
			data: function (params) {
				return {
					q: params.term, // search term
					page: params.page
				};
			},
			processResults: function (data, page) {
				// parse the results into the format expected by Select2.
				// since we are using custom formatting functions we do not need to
				// alter the remote JSON data
				
				return {
					results: data.result,
					pagination: {
				      more: data.more
				    }
				};
			},
			cache: true
		},
		escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
		minimumInputLength: 1,
	    templateResult: formatRepo, // omitted for brevity, see the source of this page
	    templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
	});
	
	function formatRepo (repo) {
	    if (repo.loading) return repo.text;

	    var markup = '<div class="clearfix">' +
	    '<div class="col-sm-1">' +
	    '<img src="' + repo.owner.avatar_url + '" style="max-width: 100%" />' +
	    '</div>' +
	    '<div clas="col-sm-10">' +
	    '<div class="clearfix">' +
	    '<div class="col-sm-6">' + repo.full_name + '</div>' +
	    '<div class="col-sm-3"><i class="fa fa-code-fork"></i> ' + repo.forks_count + '</div>' +
	    '<div class="col-sm-2"><i class="fa fa-star"></i> ' + repo.stargazers_count + '</div>' +
	    '</div>';

	    if (repo.description) {
	      markup += '<div>' + repo.description + '</div>';
	    }

	    markup += '</div></div>';

	    return markup;
   }

   function formatRepoSelection (repo) {
     return repo.full_name || repo.text;
   }
});