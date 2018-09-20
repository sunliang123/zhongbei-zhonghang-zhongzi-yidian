/**
 * 设置提现密码
 */
$(function() {
	// 加载layui
	layui.use(['element', 'table', 'form'], function() {});
	// 取消按钮
	$("#cancel-btn").on('click', function() {
		parent.layer.closeAll();
	});
	// 提交按钮
	$("#submit-btn").on('click', function() {
		var formData = {};
		var formDataArr = $("#submit-form").serializeArray();
		for(var i = 0; i < formDataArr.length; i++) {
			var name = formDataArr[i].name;
			var value = formDataArr[i].value;
			if(formData[name]) {
				formData[name] = formData[name] + "," + value;
			} else {
				formData[name] = value;
			}
		}
		if(!formData.paymentPassword.match(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$/)) {
			parent.layer.msg("支付密码必须为8位以上数字+字母组合");
			return;
		}
		if(formData.paymentPassword != formData.rePaymentPassword) {
			parent.layer.msg("两次输入的支付密码不一致");
			return;
		}
		$.ajax({
            type: "PUT",
            url: "/promotion/organizationAccount/" + parent.currentOrgId + "/modifyPaymentPassword",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: $.param(formData),
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.closeAll();
            		parent.layer.msg("设置成功");
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            },
            error: function(jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message)
            }
        });
	});
});