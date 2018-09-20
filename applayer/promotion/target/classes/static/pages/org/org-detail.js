/**
 * 机构详情
 */
$(function() {
	var currentOrgId = parent.currentOrgId;
    var currentOrgPId = parent.currentOrgPId;
    var currentOrgLevel = parent.currentOrgLevel;
    if(currentOrgLevel == 1) {
    	$("#org-detail-tab li").each(function(index,element) {
    		if(index != 0) {
    			$(this).css("display", "none");
    		}
    	});
    }
	// 加载layui
	layui.use(['element', 'table'], function() {});
	// 修改机构名称
	$("#name-modify-link").on('click', function(){
		$("#name-display").css("display", "none");
		$("#name-modify").css("display", "");
	});
	$("#name-modify-btn").on('click', function(){
		// 请求修改机构名称
		var formData = $("#name-modify-form").serialize();
		$.ajax({
            type: "POST",
            url: "/promotion/organization/modifyName",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: formData,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		$('span[data="name"], input[data="name"]').each(function(i){
        				var tagName = this.tagName;
        				if(tagName.toUpperCase() == "SPAN") {
        					$(this).html(jsonResult.result.name);
        				} else {
        					$(this).val(jsonResult.result.name);
        				}
        			});
            		$("#name-display").css("display", "");
            		$("#name-modify").css("display", "none");
            		// 更新树节点名称
            		var node = parent.ztreeObj.getNodeByParam("id", currentOrgId, null);
            		node.name = jsonResult.result.name;
            		parent.ztreeObj.updateNode(node);
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            }
        });
	});
	// 初始加载结构信息
	$.ajax({
        type: "GET",
        url: "/promotion/organization/detail?orgId=" + currentOrgId,
        dataType: "json",
        success: function (jsonResult) {
        	if("200" == jsonResult.code) {
        		var org = jsonResult.result;
        		for(var prop in org) {
        			$('.detail span[data="' + prop + '"], .detail input[data="' + prop + '"]').each(function(i){
        				var tagName = this.tagName;
        				if(tagName.toUpperCase() == "SPAN") {
        					$(this).html(org[prop]);
        				} else {
        					$(this).val(org[prop]);
        				}
        			});
        		}
        	} else {
        		parent.layer.msg(jsonResult.message);
        	}
        }
    });
	// 初始加载绑卡信息
	$.ajax({
        type: "GET",
        url: "/promotion/organization/" + currentOrgId + "/bindcard",
        dataType: "json",
        success: function (jsonResult) {
        	if("200" == jsonResult.code) {
        		var bindcard = jsonResult.result;
        		if(bindcard) {
        			for(var prop in bindcard) {
            			$('.bindcard input[data="' + prop + '"]').each(function(i){
            				$(this).val(bindcard[prop]);
            			});
            		}
        		}
        	} else {
        		parent.layer.msg(jsonResult.message);
        	}
        }
    });
	// 绑卡保存按钮
	$("#bind-card-submit-btn").on('click', function() {
		var formData = $("#bind-card-form").serialize();
		$.ajax({
            type: "POST",
            url: "/promotion/organization/" + currentOrgId + "/bindcard",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            data: formData,
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		parent.layer.msg("修改成功!");
            	} else {
            		parent.layer.msg(jsonResult.message);
            	}
            }
        });
	});
	// 绑卡取消按钮
	$("#bind-card-cancel-btn").on('click', function() {
		parent.layer.closeAll();
	});


    $("#user-cancel-btn").on('click', function() {
        parent.layer.closeAll();
    });
//得到当前用户
    $.ajax({
        type: "GET",
        url: "/promotion/user/getCurrent",
        dataType: "json",
        success: function (jsonResult) {
            $("[name='currentId']").val(jsonResult.result.org.id);
        }
    });

    // 提交按钮
    $("#user-submit-btn").on('click', function() {
        var currentId = $("[name='currentId']").val();
    	if(currentId!=currentOrgPId) {
			alert("你不能为该机构添加管理员！");
		}else {
            $("[name='org.id']").val(currentOrgId);
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
                            alert("添加成功");
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
        }
    });
});