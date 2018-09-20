/**
 * 登陆页面
 */
$(function() {
	// 加载layui
	layui.use(['element', 'table', 'form'], function() {});
	// 提交按钮
	$("#submit-form").on('submit', function() {
		var formData = $("#submit-form").serialize();
		$.ajax({
            type: "POST",
            url: "/promotion/login",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: formData,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		window.location.href = "/promotion" + jsonResult.result;
            	} else {
            		layer.msg(jsonResult.message?jsonResult.message:"用户名或者密码错误");
            	}
            }
        });
		return false;
	});
});