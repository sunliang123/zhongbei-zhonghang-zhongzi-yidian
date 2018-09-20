/**
 * 设置机构分成比例
 */
$(function() {
	// 加载layui
	layui.use(['element', 'table'], function() {});
	// 取消按钮
	$("#strategy-cancel-btn, #stockoption-cancel-btn").on('click', function() {
		parent.layer.closeAll();
	});
	if(parent.window.onlyStockoption == true) {
		$("#tab-title li").each(function(index, element) {
			if(index == 0) {
				$(this).removeClass("layui-this");
				$(this).css("display", "none");
			} else if(index == 1) {
				$(this).addClass("layui-this");
			}
		});
		
		$("#tab-content .layui-tab-item").each(function(index, element) {
			if(index == 0) {
				$(this).removeClass("layui-show");
				$(this).css("display", "none");
			} else if(index == 1) {
				$(this).addClass("layui-show");
			}
		});
	}
	// 初始化配资分成比例表格
	$.ajax({
        type: "GET",
        url: "/promotion/benefitConfig/benefitConfigList?orgId=" + parent.currentOrgId + "&resourceType=1",
        dataType: "json",
        success: function (jsonResult) {
        	if(jsonResult.code == "200") {
        		var configList = jsonResult.result;
        		for(var i = 0; i < configList.length; i++) {
        			var trHtml = '<tr>';
        			trHtml += '<td>' + configList[i].resourceName + '<input type="hidden" name="orgId" value="' + parent.currentOrgId + '" /><input type="hidden" name="resourceId" value="' + configList[i].resourceId + '" /></td>';
            		trHtml += '<td class="align-center"><input type="text" name="serviceFeeRatio" value="' + (configList[i].serviceFeeRatio?configList[i].serviceFeeRatio:'') + '" class="layui-input" />&nbsp;%</td>';
            		trHtml += '<td class="align-center"><input type="text" name="deferredFeeRatio" value="' + (configList[i].deferredFeeRatio?configList[i].deferredFeeRatio:'') + '" class="layui-input" />&nbsp;%</td>';
            		trHtml += '</tr>';
            		$("#strategy-config-tbody").append(trHtml);
        		}
        	} else {
        		parent.layer.msg(jsonResult.message);
        	}
        }
    });
	// 初始化期权分成比例表格
	$.ajax({
        type: "GET",
        url: "/promotion/benefitConfig/benefitConfigList?orgId=" + parent.currentOrgId + "&resourceType=2",
        dataType: "json",
        success: function (jsonResult) {
        	if(jsonResult.code == "200") {
        		var configList = jsonResult.result;
        		for(var i = 0; i < configList.length; i++) {
        			var trHtml = '<tr>';
            		trHtml += '<td>' + configList[i].resourceName + '<input type="hidden" name="orgId" value="' + parent.currentOrgId + '" /><input type="hidden" name="resourceId" value="' + configList[i].resourceId + '" /></td>';
            		trHtml += '<td class="align-center"><input type="text" name="rightMoneyRatio" value="' + (configList[i].rightMoneyRatio?configList[i].rightMoneyRatio:'') + '" class="layui-input">&nbsp;%</td>';
            		trHtml += '</tr>';
            		$("#stockoption-config-tbody").append(trHtml);
        		}
        	} else {
        		parent.layer.msg(jsonResult.message);
        	}
        }
    });
	// 策略提交按钮
	$("#strategy-submit-btn").on('click', function() {
		var formData = $("#strategy-config-form").serialize();
		var formDataArr = formData.split('&');
		var size = formDataArr.length / 4;
		var configFormList = [];
		for(var i = 0; i < formDataArr.length; i++) {
			var keyValue = formDataArr[i].split("=");
			if(keyValue[0] == "serviceFeeRatio" || keyValue[0] == "deferredFeeRatio") {
				// 校验百分比输出格式
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if (!reg.test(keyValue[1])) {
					layer.msg('比例格式有误，最多为两位小数的数字', {time: 1000});
					return;
				} else if (parseFloat(keyValue[1]) >= 100) {
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
            url: "/promotion/benefitConfig/strategy/config",
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
	// 期权提交按钮
	$("#stockoption-submit-btn").on('click', function() {
		var formData = $("#stockoption-config-form").serialize();
		var formDataArr = formData.split('&');
		var size = formDataArr.length / 3;
		var configFormList = [];
		for(var i = 0; i < formDataArr.length; i++) {
			var keyValue = formDataArr[i].split("=");
			if(keyValue[0] == "rightMoneyRatio") {
				// 校验百分比输出格式
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if (!reg.test(keyValue[1])) {
					layer.msg('比例格式有误，最多为两位小数的数字', {time: 1000});
					return;
				} else if (parseFloat(keyValue[1]) >= 100) {
					layer.msg('比例格式有误，不能大于100%', {time: 1000});
					return;
				}
			}
			if(i%3 == 0) {
				configFormList.push({});
				configFormList[configFormList.length-1][keyValue[0]] = keyValue[1]
			} else {
				configFormList[configFormList.length-1][keyValue[0]] = keyValue[1]; 
			}
		}
		// 发送请求
		$.ajax({
            type: "POST",
            url: "/promotion/benefitConfig/stockoption/config",
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