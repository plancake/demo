$( document ).ready(function() {
	
	var currentDocumentViewId = 0;
	
	function openDocument(documentUrl)
	{
		$("#documentOverlayContent iframe").attr('src', documentUrl);
		
		$("#documentOverlayContent").show();		
		$(".overlay").show();
		
		$.ajax({
			  type: "POST",
			  url: '/account/document-views/' + 10 + '?mode=start&view_id=0',
			  success: function(documentViewId) {
				  currentDocumentViewId = documentViewId;
			  }
		});
	}

	function closeDocument()
	{
		$("#documentOverlayContent").hide();		
		$(".overlay").hide();
		
		$.ajax({
			  type: "POST",
			  url: '/account/document-views/' + 10 + '?mode=end&view_id=' + currentDocumentViewId,
			  success: function(documentViewId) {
				  currentDocumentViewId = 0;
			  }
		});		
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