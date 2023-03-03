//region === Global Ajax Settings ===
$(document)
	.ajaxSend(function (e, xhr, options){
		if (options.type.toUpperCase()==="POST"){
			$.blockUI();
		}
		NProgress.start();
	}).ajaxStart(function (){
		NProgress.set(0.4);
	}).ajaxStop(function (){
		$.unblockUI();
		NProgress.done();
	}).ajaxError(function (event, jgxhr, settings, thrownError){
		$.unblockUI();
		NProgress.done();
		if (event.redirect){
			window.location.href = event.redirect;
		}
	}).ajaxComplete(function (){
		$.unblockUI();
		NProgress.done();
	}).ajaxSuccess(function (event, request, settings){
		$.unblockUI();
		NProgress.done();
		if (event.redirect){
			window.location.href = event.redirect;
		}
		var location = request.getResponseHeader('Location');
		if (location && location != window.location.href){
			window.location.href = location;
		}
	});
//endregion === Global Ajax Settings ===


//region === Start of When Document Is Ready ===
$(document).ready(function() {

});