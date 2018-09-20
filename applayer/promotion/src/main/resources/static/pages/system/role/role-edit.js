/**
 * 添加角色
 */
$(function() {
    var roleId = parent.currentRoleId;

	// 加载layui
	layui.use(['element', 'table', 'form'], function() {});

    // 取消按钮
    $("#cancel-btn").on('click', function() {
        parent.layer.closeAll();
    });
    // 提交按钮
    $("#submit-btn").on('click', function() {
        var formData = $("#edit-form").serialize();
        $.ajax({
            type: "POST",
            url: "/promotion/role/modify",
            dataType: "json",
            data: formData,
            traditional: true,
            success: function (jsonResult) {
                if("200" == jsonResult.code) {
                    parent.layer.msg("修改成功");
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

	//初始化修改信息
    $.ajax({
        type: "GET",
        url: "/promotion/role/"+roleId,
        dataType: "json",
        success: function (jsonResult) {
            if("200" == jsonResult.code) {
                var result = jsonResult.result;
                $("[name='id']").val(result.id);
                $("[name='name']").val(result.name);
                $("[name='code']").val(result.code);
                $("[name='description']").val(result.description);
            } else {
                parent.layer.msg(jsonResult.message);
            }
        }
    });
});