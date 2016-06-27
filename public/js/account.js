$( document ).ready(function() {
	
	function openDocument(documentUrl)
	{
		$("#documentOverlayContent iframe").attr('src', documentUrl);
		
		$("#documentOverlayContent").show();		
		$(".overlay").show();
	}

	function closeDocument()
	{
		$("#documentOverlayContent").hide();		
		$(".overlay").hide();
	}	
	
	if ($('#buyerDealsList').length) {
		
		$('#buyerDealsList a').click(function (e) {
			
			openDocument($(this).attr('href'));

			e.preventDefault();
			return false;
		});
		
		$('#overlayClose a').click(function(e) {
			closeDocument();
			e.preventDefault();
			return false;			
		});
		
	}
	
	$('.overlay, .overlayClose a').click(function (e) {
		closeDocument();
		
		e.preventDefault();
		return false;		
	});
	
});