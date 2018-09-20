/**
 * 申请提现
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
		
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if (!reg.test(formData.amount)) {
			layer.msg('金额格式有误，最多为两位小数的数字', {time: 1000});
			return ;
		}
		if(!formData.paymentPassword || !(formData.paymentPassword.trim())) {
			parent.layer.msg("支付密码不能为空");
			return;
		}
		formData.orgId = parent.currentOrgId;
		$.ajax({
            type: "POST",
            url: "/promotion/withdrawalsApply/",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: $.param(formData),
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.closeAll();
            		parent.layer.msg("申请已提交");
            		parent.renderTable("#account-flow-table");
            		parent.refreshAccount();
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