/**
 * 添加角色
 */
$(function() {
    var roleId = parent.currentRoleId;

	// 加载layui
	layui.use(['element', 'table', 'form'], function() {});

	//初始化权限信息
    $.ajax({
        type: "GET",
        url: "/promotion/role/"+roleId,
        dataType: "json",
        async:false,
        success: function (jsonResult) {
            if("200" == jsonResult.code) {
                var permissions = jsonResult.result.permissionVos;
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
        },
        error: function (jsonResult) {
            parent.layer.msg(jsonResult.responseJSON.message)
        }
    });
});