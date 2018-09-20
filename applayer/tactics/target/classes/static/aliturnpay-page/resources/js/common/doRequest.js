/**
 * 请求http
 */
$.dialogDoRequest = function(options){
	var settings = $.extend({
		"clickBtn": "",
		"submitForm": "",
		"requestUrl": "",
		"requestSpecialPath": "",
		"closeBtn": "",
		"resetBtn": [],
		"message": "",
		"dtObj": null
	}, options || {});
	
	$(settings.clickBtn).on("click", function(){
		$(settings.submitForm).submit();
	});
	$(settings.submitForm).on("submit", function(){
		var requestData = null;
		if(settings.requestSpecialPath && settings.requestSpecialPath != "") {
			var requestObject = $(settings.submitForm).serializeObject();
			requestData = requestObject[settings.requestSpecialPath];
		}else{
			requestData = $(settings.submitForm).serializeJson();
		}
		$.ajax({
			url: settings.requestUrl,
			type: "POST",
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			data: '{"param": ' + requestData + '}',
			success: function (data, textStatus) {
				var message = "";
				if(data.error == false){
					$(settings.closeBtn).trigger("click");
					if(settings.dtObj){
						settings.dtObj.api().ajax.reload();
					}
					for(var i = 0; i < settings.resetBtn.length; i++){
						$(settings.resetBtn[i]).attr("disabled", "disabled");
					}
	                toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    timeOut: 2000,
	                    positionClass: "toast-top-right"
	                };
	                toastr.success(settings.message, '');
				}else{
					message = data.exception.message;
	                toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    timeOut: 2000,
	                    positionClass: "toast-top-right"
	                };
	                toastr.error(message, '');
				}
			}
		});
		return false;
	});
};

$.dtBtnDoRequest = function(options){
	var settings = $.extend({
		"requestUrl": "",
		"message": "",
		"dtObj": null,
		"data": "",
		"resetBtn": [],
	}, options || {});
	
	$.ajax({
		url: settings.requestUrl,
		type: "POST",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: '{"param": ' + settings.data + '}',
		success: function (data, textStatus) {
			if(data.error == false){
				if(settings.dtObj){
					settings.dtObj.api().ajax.reload();
				}
				for(var i = 0; i < settings.resetBtn.length; i++){
					$(settings.resetBtn[i]).attr("disabled", "disabled");
				}
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 2000,
                    positionClass: "toast-top-right"
                };
                toastr.success(settings.message, '');
			} else {
				var message = data.exception.message;
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 2000,
                    positionClass: "toast-top-right"
                };
                toastr.error(message, '');
			}
		}
	});
};