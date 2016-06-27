$( document ).ready(function() {
	
	// From Unix timestamp (in seconds) to formatted date and time
	// See http://stackoverflow.com/questions/847185/convert-a-unix-timestamp-to-time-in-javascript	
	// Using moment.js would be nicer, but I had some problems with it.
	function timeConverter(UNIX_timestamp){
		  var a = new Date(UNIX_timestamp * 1000);
		  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		  var year = a.getFullYear();
		  var month = months[a.getMonth()];
		  var date = a.getDate();
		  var hour = a.getHours();
		  var min = a.getMinutes() < 10 ? '0' + a.getMinutes() : a.getMinutes();
		  var sec = a.getSeconds() < 10 ? '0' + a.getSeconds() : a.getSeconds();
		  var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
		  return time;
	}
	
	if ($('#sellerDealsList').length) {  // seller account

		var updateViews = function () {
			var documentIds = [];
			
			$('.dealsList tr td > ul > li').each(function () {
				documentIds.push($(this).attr('class').replace('document_', ''));
			});
			
			for (var i = 0; i < documentIds.length; i++) {
				documentId = documentIds[i];
				(function (documentId) {
					$.ajax({
						  type: "GET",
						  url: '/account/document-views/' + documentId,
						  success: function(viewsDataFromServer) {
							  var $views = $('li.document_' + documentId + ' ul.documentViewsLog');
							  
							  $views.empty(); // reset the list of views

							  
							  for (var i = 0; i < viewsDataFromServer.length; i++) {

								  var viewRecord = viewsDataFromServer[i];
								  
								  var viewedAt = viewRecord[0] / 1000; // server sends milliseconds
								  var viewedTill = viewRecord[1] / 1000; // server sends milliseconds								  
								  
								  var viewMessage = "viewed on " + timeConverter(viewedAt);
								  
								  if (viewedTill) {
									  viewMessage += " for " + (viewedTill - viewedAt) + " seconds";
								  } else {
									  viewMessage += " (session not closed)";
								  }
								  
								  $views.append("<li>" + viewMessage + "</li>");
							  }
						  }
					});
				})(documentId);
			}
		}

		updateViews();
		setInterval(updateViews, 30000);		
	}
	
});