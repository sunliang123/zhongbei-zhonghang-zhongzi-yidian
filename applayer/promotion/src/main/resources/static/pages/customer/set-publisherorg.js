/**
 * 修改发布人所属机构
 */
$(function() {
	$("input[name='orgCode']").val(parent.currentPublisherOrgCode);
	
	// 加载layui
	layui.use(['element', 'table', 'form'], function() {});
	// 取消按钮
	$("#cancel-btn").on('click', function() {
		parent.layer.closeAll();
	});
	// 提交按钮
	$("#submit-btn").on('click', function() {
		var formData = $("#add-form").serialize();
		var orgCode = $("input[name='orgCode']").val();
		if(orgCode.trim() == "") {
			parent.layer.msg("机构代码不能为空！");
			return;
		}
		$.ajax({
            type: "POST",
            url: "/promotion/customer/publisherOrg/" + parent.currentPublisherId + "/" + orgCode,
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.closeAll();
            		parent.renderTable("#customer-list-table");
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            },error:function (jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message);
            }
        });
	});
});