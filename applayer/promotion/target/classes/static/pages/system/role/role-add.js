/**
 * 添加角色
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
		var formData = $("#add-form").serialize();
		$.ajax({
            type: "POST",
            url: "/promotion/role/save",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: formData,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.msg("添加成功");
            		parent.layer.closeAll();
                    parent.renderTable("#role-list-table");
            	} else {
                    parent.layer.msg(jsonResult.responseJSON.message)
            	}
            },
			error: function (jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message)
            }
        });
	});
});