$( document ).ready(function() {
	
	if ($('#buyerDealsList').length) {
		$('#buyerDealsList a').click(function (e) {
			
			// $("#dialog iframe").attr('src', $(this).attr('hred')).dialog();
			
			
			
			$("#dialog").show();
			
			e.preventDefault();
			return false;
		});
	}
	
});