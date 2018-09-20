/**
 * 角色列表
 */
window.renderTable = function(){};
window.currentUserId = "";
window.orgId = "";
$(function() {
    var searchData = {
        currentOrgCode: '01'
    };
    // 加载数据
    function retrieveData(sSource, aoData, fnCallback, oSettings) {
        var draw = (aoData[3].value / 10) + 1;
        oSettings.iDraw = draw;
        // 搜索
        var keyword = aoData[5].value.value;
        searchData.page = (draw - 1);
        searchData.size = 10;
        $.ajax({
            type: "POST",
            url: "/promotion/user/pages",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(searchData),
            success: function (jsonResult) {
                var dtData = {
                    "draw": draw,
                    "recordsTotal": jsonResult.result.totalElements,
                    "recordsFiltered": jsonResult.result.totalElements,
                    "data": jsonResult.result.content
                };
                fnCallback(dtData);
            }
        });
        searchData = {
            currentOrgCode: searchData.currentOrgCode
        };
    }
    // 渲染表格
    renderTable = function(id) {
        if($(id + "_wrapper").length > 0) {
            $(id).dataTable().fnDraw();
        } else {
            var columns = [
                { "data": "id", "title": "ID", orderable: false},
                { "data": "username", "title": "登陆名", orderable: false},
                { "data": "nickname", "title": "用户名", orderable: false},
                { "data": "createTime", "title": "创建时间", orderable: false},
                { "data": "organizationName", "title": "所属机构", orderable: false, "render": function(data, type, full, meta) {
                    if(full.org!=null) {
                        return full.org.name;
                    }
                    return '';
                }},
                { "data": "roleName", "title": "所拥角色", orderable: false},
                { "data": "id", "width": "230", "title": "操作", "className": "align-center", orderable: false, "render": function(data, type, full, meta) {
                    if(full.code=="SUPERADMIN") {
                        return "-";
                    }
                    return "<a class='edit mr10' orgId='"+full.org.id+"' userId='" + full.id + "' href='javascript:;'>修改角色</a>";
                }}
            ];
            $(id).dataTable({
                "responsive": true,
                "processing": true,
                "serverSide": true,
                "bPaginate": true,
                "dom": "<'row'<'col-sm-6'><'col-sm-6'>><'row'<'col-sm-12'tr>><'row'<'col-sm-3'i><'col-sm-9'p>>",
                "fnServerData": retrieveData,
                "columns": columns,
                "oLanguage": {                        //汉化
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sSearch": '<span>搜索：</span>',
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前页",
                        "sNext": "后页",
                        "sLast": "尾页"
                    }
                }
            });
        }
    }
    // 执行
    renderTable("#user-list-table");
    // 加载layui
    layui.use(['element', 'table'], function() {
    });
    // 搜索
    $('#search-btn').on('click', function(){
        var formDataArr = $("#search-form").serializeArray();
        for(var i = 0; i < formDataArr.length; i++) {
            var name = formDataArr[i].name;
            var value = formDataArr[i].value;
            if(searchData[name]) {
                searchData[name] = searchData[name] + "," + value;
            } else {
                searchData[name] = value;
            }
        }
        renderTable("#user-list-table");
    });
    //重置
    $('#reset-btn').on('click', function(){
        $("[name='id']").val("");
        renderTable("#user-list-table");
    });

    $('#add-btn').on('click', function(){
        var index = layer.open({
            type: 2,
            title: '添加用户',
            shadeClose: true,
            shade: 0.8,
            area: ['60%', '80%'],
            content: 'user-add.html',
        });
    });

    $('#user-list-table').on('click','a.edit', function(){
        currentUserId = $(this).attr("userId");
        orgId = $(this).attr("orgId");
        layer.open({
            type: 2,
            title: '设置角色',
            shadeClose: true,
            shade: 0.8,
            area: ['60%', '80%'],
            content: 'user-role.html',
        });
    });
    //
    // $('#role-list-table').on('click','a.detail', function(){
    //     currentRoleId = $(this).attr("roleId");
    //     layer.open({
    //         type: 2,
    //         title: '查看权限',
    //         shadeClose: true,
    //         shade: 0.8,
    //         area: ['60%', '80%'],
    //         content: 'role-detail.html',
    //     });
    // });
    //
    // $('#role-list-table').on('click','a.edit', function(){
    //     currentRoleId = $(this).attr("roleId");
    //     layer.open({
    //         type: 2,
    //         title: '编辑角色',
    //         shadeClose: true,
    //         shade: 0.8,
    //         area: ['60%', '80%'],
    //         content: 'role-edit.html',
    //     });
    // });
});