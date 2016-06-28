$( document ).ready(function() {
	
	if ($('#buyerDealsList').length) {  // buyer account

		var currentDocumentId = 0;
		var currentDocumentViewId = 0;
		
		function openDocument(documentId, documentUrl)
		{
			currentDocumentId = documentId;
			
			$("#documentOverlayContent iframe").attr('src', documentUrl);
			
			$("#documentOverlayContent").show();		
			$(".overlay").show();
			
			$.ajax({
				  type: "POST",
				  url: '/account/document-views/' + documentId + '?mode=start&view_id=0',
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
				  url: '/account/document-views/' + currentDocumentId + '?mode=end&view_id=' + currentDocumentViewId,
				  success: function(documentViewId) {
					  currentDocumentViewId = 0;
					  currentDocumentId = 0;
				  }
			});		
		}		
		
		$('#buyerDealsList a').click(function (e) {
			
			openDocument(parseInt($(this).parent().find('.documentId').text(), 10), $(this).attr('href'));

			e.preventDefault();
			return false;
		});

		$('.overlay, .overlayClose a').click(function (e) {
			closeDocument();
			
			e.preventDefault();
			return false;		
		});		
		
	}
	
});