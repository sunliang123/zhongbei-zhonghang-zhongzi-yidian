/**
 * 添加角色
 */
$(function() {
    var userId = parent.currentUserId;
    var orgId = parent.orgId;
    // 加载layui
	layui.use(['element', 'table', 'form'], function() {});

    //初始化权限信息
    $.ajax({
        type: "GET",
        url: "/promotion/role/",
        dataType: "json",
        async:false,
        success: function (jsonResult) {
            if("200" == jsonResult.code) {
                var roles = jsonResult.result;
                var html = '';
                $.each(roles,function (index,role){
                    if(role.code!="SUPERADMIN") {
                        html += '<input title='+role.name+' class="radio" value='+role.id+' type="radio" name="role"/>&nbsp;&nbsp;&nbsp;&nbsp;';
                        if((index+1)%3==0) {
                            html += "<br>";
                        }
                    }
                });
                $("#role").append(html);
            } else {
                parent.layer.msg(jsonResult.message);
            }
        }
    });

	// 取消按钮
    $("#cancel-btn").on('click', function() {
        parent.layer.closeAll();
    });
    // 提交按钮
    $("#submit-btn").on('click', function() {
        $.ajax({
            type: "POST",
            url: "/promotion/user/"+userId+"/role",
            dataType: "json",
            data: {"roleId":$('input:radio:checked').val()},
            traditional: true,
            success: function (jsonResult) {
                if("200" == jsonResult.code) {
                    alert("修改成功");
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