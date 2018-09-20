/**
 * 设置机构加价比例
 */
$(function() {
	// 加载layui
	layui.use(['element', 'table'], function() {});
	// 取消按钮
	$("#stockoption-cancel-btn").on('click', function() {
		parent.layer.closeAll();
	});
	// 初始化期权分成比例表格
	$.ajax({
        type: "GET",
        url: "/promotion/priceMarkupConfig/priceMarkupConfigList?orgId=" + parent.currentOrgId + "&resourceType=2",
        dataType: "json",
        success: function (jsonResult) {
        	if(jsonResult.code == "200") {
        		var configList = jsonResult.result;
        		for(var i = 0; i < configList.length; i++) {
        			var trHtml = '<tr>';
            		trHtml += '<td>' + configList[i].resourceName + '<input type="hidden" name="orgId" value="' + parent.currentOrgId + '" /><input type="hidden" name="resourceType" value="' + configList[i].resourceType + '" /><input type="hidden" name="resourceId" value="' + configList[i].resourceId + '" /></td>';
            		trHtml += '<td class="align-center"><input type="text" name="ratio" value="' + (configList[i].ratio?configList[i].ratio:'0') + '" class="layui-input">&nbsp;%</td>';
            		trHtml += '</tr>';
            		$("#stockoption-config-tbody").append(trHtml);
        		}
        	} else {
        		parent.layer.msg(jsonResult.message);
        	}
        }
    });
	// 期权提交按钮
	$("#stockoption-submit-btn").on('click', function() {
		var formData = $("#stockoption-config-form").serialize();
		var formDataArr = formData.split('&');
		var size = formDataArr.length / 3;
		var configFormList = [];
		for(var i = 0; i < formDataArr.length; i++) {
			var keyValue = formDataArr[i].split("=");
			if(keyValue[0] == "ratio") {
				// 校验百分比输出格式
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if (!reg.test(keyValue[1])) {
					layer.msg('比例格式有误，最多为两位小数的数字', {time: 1000});
					return;
				} else if (parseFloat(keyValue[1]) > 100) {
					layer.msg('比例格式有误，不能大于100%', {time: 1000});
					return;
				}
			}
			if(i%4 == 0) {
				configFormList.push({});
				configFormList[configFormList.length-1][keyValue[0]] = keyValue[1]
			} else {
				configFormList[configFormList.length-1][keyValue[0]] = keyValue[1]; 
			}
		}
		// 发送请求
		$.ajax({
            type: "POST",
            url: "/promotion/priceMarkupConfig/priceMarkupConfig",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(configFormList),
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.closeAll();
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            }
        });
	});
});