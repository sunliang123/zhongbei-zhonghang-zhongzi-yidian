/**
 * 公共方法，公共操作
 */
$(function() {
	// 退出登陆
	$("#logout-link").on("click", function(){
		$.ajax({
            type: "GET",
            url: "/promotion/logout",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            success: function (jsonResult) {
            	window.location.href = "/promotion/login";
            }
        });
	});
	// 初始化菜单
	layui.use('element', function(){
		var element = layui.element;
		$.ajax({
	        type: "GET",
	        url: "/promotion/menus",
	        dataType: "json",
	        success: function (jsonResult) {
	        	var url = window.location.href;
	            var menus = jsonResult;
	            $.each(menus,function (index,menu){
	                var html = '<li class="layui-nav-item"><a href="javascript:;" style="text-decoration:none;"><i class="layui-icon">' + (menu.icon?menu.icon:'') + '</i></i>'+menu.name+'</a><dl class="layui-nav-child">';
	                var liOpened = false;
	                $.each(menu.childs,function (index,child) {
	                	if(url.indexOf(child.url) >= 0) {
	                		html += '<dd class="layui-this"><a href="'+child.url+'">'+child.name+'</a></dd>';
	                		liOpened = true;
	                	} else {
	                		html += '<dd><a href="'+child.url+'">'+child.name+'</a></dd>';
	                	}
	                })
	                html += '</dl></li>';
	                if(liOpened) {
	                	html = html.replace(/layui-nav-item/, "layui-nav-item layui-nav-itemed");
	                }
	                $(".layui-nav.layui-nav-tree").append(html);
	            });
	            element.render(null, "promotion-nav");
	        }
	    });
	});
    
    // 获取当前登陆的用户信息
	$.ajax({
        type: "GET",
        url: "/promotion/user/getCurrent",
        dataType: "json",
        async: false,
        success: function (jsonResult) {
        	window.currentOrgId = jsonResult.result.org.id;
        	window.currentOrgCode = jsonResult.result.org.code;
        	window.level = jsonResult.result.org.level;
        	window.onlyStockoption = jsonResult.result.onlyStockoption;
        	$("#nickname").html(jsonResult.result.nickname);
        }
    });
	// 弹出修改登陆密码页面
	$('#modify-pwd-link').on('click', function(){
		var index = layer.open({
			type: 2,
			title: '修改登陆密码',
			shadeClose: true,
			shade: 0.8,
			area: ['500px', '310px'],
			content: '/promotion/pages/user/modify-password.html',
		});
	});
});