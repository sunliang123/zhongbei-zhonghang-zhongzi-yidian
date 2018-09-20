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
        var uPattern = /^[a-zA-Z0-9_-]{4,10}$/;
        var password = $('[name="password"]').val();
        var againPassword = $('[name="again-password"]').val();
        var userName = $('[name="username"]').val();
        var nickName = $('[name="nickname"]').val();
        if (!uPattern.test(userName)||!uPattern.test(nickName)) {
            alert("用户名或昵称有误，请重新输入！");
        }else if(userName=="") {
            alert("用户名不能为空！");
        }else if(nickName=="") {
            alert("昵称不能为空");
        }else if(password==""||againPassword=="") {
            alert("密码不能为空！");
        }else if(password!=againPassword) {
            alert("两次输入的密码不一致，请重新输入");
        }else {
            var formData = $("#add-form").serialize();
            $.ajax({
                type: "POST",
                url: "/promotion/user/save",
                dataType: "json",
                data: formData,
                success: function (jsonResult) {
                    if("200" == jsonResult.code) {
                        parent.layer.msg("添加成功");
                        parent.layer.closeAll();
                        parent.renderTable("#user-list-table");
                    } else {
                        parent.layer.msg(jsonResult.message)
                    }
                },
                error: function (jsonResult) {
                    parent.layer.msg(jsonResult.responseJSON.message)
                }
            });
        }
    });
});