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

    function isExist(element) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == element) {
                return true;
            }
        } return false;
    }
    // 提交按钮
	$("#submit-btn").on('click', function() {
        var permissionIds = new Array();
        $("input[name='permission']:checked").each(function() {
            var permissionId = $(this).val();
            var pid = $(this).attr("pid");
            if(!isExist(permissionIds)) {
                permissionIds.push(parseInt(pid));
            }
            permissionIds.push(parseInt(permissionId));
        })
		$.ajax({
            type: "POST",
            url: "/promotion/role/permission/"+roleId,
            dataType: "json",
            data: {"permissionIds": permissionIds},
            traditional: true,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
                    parent.layer.msg("授权成功");
            		parent.layer.closeAll();
                    parent.renderTable("#role-list-table");
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            },
            error: function (jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message)
            }
        });
	});

	//初始化权限信息
    $.ajax({
        type: "GET",
        url: "/promotion/role/permissions",
        dataType: "json",
        async:false,
        success: function (jsonResult) {
            if("200" == jsonResult.code) {
                var permissions = jsonResult.result;
                var html = '';
                $.each(permissions,function (index,permission){
                    if(permission.pid==0) {
                        html += '<label class="label-danger">'+permission.name+'</label><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                        $.each(permissions,function (index,cpermission){
                            if(cpermission.pid==permission.id) {
                                html += '<input title='+cpermission.name+' pid='+permission.id+' value='+cpermission.id+' type="checkbox" name="permission"/></span>&nbsp;&nbsp;&nbsp;&nbsp;';
                            }
                        })
                        html += "<br>";
                    }
                });
                $("#permissions").append(html);
            } else {
                parent.layer.msg(jsonResult.message);
            }
        }
    });
});