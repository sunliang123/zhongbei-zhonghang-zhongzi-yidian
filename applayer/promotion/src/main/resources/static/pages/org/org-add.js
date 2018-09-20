/**
 * 添加机构
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
		if(formData.length > 0) {
			formData = "parentId=" + parent.searchData.parentId + "&" + formData;
		}
		$.ajax({
            type: "POST",
            url: "/promotion/organization/",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: formData,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.renderTable("#org-list-table");
            		parent.layer.closeAll();
            		var newNode = {
        				"id": jsonResult.result.id,
        				"name": jsonResult.result.name,
        				"level": jsonResult.result.level,
        				"pid": jsonResult.result.parentId,
        				"open": false
        			};
            		parent.ztreeObj.addNodes(parent.selectedNode, newNode); 
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            },error:function (jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message);
            }
        });
	});
});