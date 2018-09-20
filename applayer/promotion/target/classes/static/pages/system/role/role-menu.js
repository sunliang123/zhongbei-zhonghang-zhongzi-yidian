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
        var menuIds = new Array();
        $("input[name='menu']:checked").each(function() {
            var menuId = $(this).val();
            var pid = $(this).attr("pid");
            if(!isExist(menuIds)) {
                menuIds.push(parseInt(pid));
            }
            menuIds.push(parseInt(menuId));
        })
		$.ajax({
            type: "POST",
            url: "/promotion/role/menu/"+roleId,
            dataType: "json",
            data: {"menuIds": menuIds},
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
        url: "/promotion/role/menus",
        dataType: "json",
        async:false,
        success: function (jsonResult) {
            if("200" == jsonResult.code) {
                var menus = jsonResult.result;
                var html = '';
                $.each(menus,function (index,menu){
                    if(menu.pid==0) {
                        html += '<label class="label-danger">'+menu.name+'</label><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                        $.each(menus,function (index,cmenu){
                            if(cmenu.pid==menu.id) {
                                html += '<input title='+cmenu.name+' pid='+menu.id+' value='+cmenu.id+' type="checkbox" name="menu"/></span>&nbsp;&nbsp;&nbsp;&nbsp;';
                            }
                        })
                        html += "<br>";
                    }
                });
                $("#menus").append(html);
            } else {
                parent.layer.msg(jsonResult.message);
            }
        }
    });
});