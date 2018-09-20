/**
 * 机构列表
 */
window.renderTable = function(){};
window.ztreeObj = {};
window.selectedNode = {};
window.currentOrgPId = "";
$(function() {
	window.searchData = { parentId: currentOrgId, loginOrgId: currentOrgId, onlyLoginOrg: true }
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
            url: "/promotion/organization/adminPage",
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
	}
	// 渲染表格
	renderTable = function(id) {
		if($(id + "_wrapper").length > 0) {
			$(id).dataTable().fnDraw();
		} else {
			var columns = [
	            { "data": "id", "title": "机构ID", orderable: false},
	            { "data": "code", "title": "机构代码", orderable: false},
	            { "data": "name", "title": "机构名称", orderable: false},
	            { "data": "level", "title": "机构类型", orderable: false, "render": function(data, type, full, meta) {
	                var level = full.level;
	                return level + "级机构";
	            }},
	            { "data": "state", "title": "机构状态", orderable: false, "render": function(data, type, full, meta) {
	                var state = full.state;
	                if(state == "NORMAL") {
	                	return "正常";
	                } else if(state == "FROZEN") {
	                	return "停用";
	                } else if(state == "DESTROY") {
	                	return "注销";
	                } else {
	                	return state;
	                }
	            }},
	            { "data": "parentCode", "title": "从属机构代码", orderable: false},
	            { "data": "createTime", "title": "创建时间", orderable: false},
	            { "data": "id", "width": "230", "title": "操作", "className": "align-center", orderable: false, "render": function(data, type, full, meta) {
	            	var id = full.id;
	            	var result = "";
	            	if(window.level == 1) {
	            		if(full.level == 1) {
	            			result = "<a class='detail' level='" + full.level + "' pid='"+full.parentId+"' orgid='" + full.id + "' href='javascript:;'>查看详情</a>";
	            		} else {
	            			result = "<a class='benefit mr10' orgid='" + full.id + "' href='javascript:;'>分成比例</a><a class='detail' level='" + full.level + "' pid='"+full.parentId+"' orgid='" + full.id + "' href='javascript:;'>查看详情</a>";
	            		}
	            	} else {
	            		result = "<a class='detail' level='" + full.level + "' pid='"+full.parentId+"' orgid='" + full.id + "' href='javascript:;'>详情</a>";
	            	}
	            	if(window.currentOrgId == full.id) {
	            		result = "<a class='markup mr10' orgid='" + full.id + "' href='javascript:;'>加价比例</a>" + result;
	            	}
	            	return result;
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
	renderTable("#org-list-table");
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
		renderTable("#org-list-table");
	});
	// 加载机构树
	var setting = {
		view: {
			dblClickExpand: function(treeId, treeNode) {
				return treeNode.level > 0;
			}
		},
		data:{
			simpleData:{
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: 0
			}
		},
		async: {
			enable: true,
			url: "/promotion/organization/adminTree?orgId=" + currentOrgId,
			dataType: "json",
			type: "get"
		},
		callback: {
			// 点击节点列表显示子节点
			onClick: function(event,treeId,treeNode) {
				selectedNode = treeNode;
				if(treeNode.id == 0) {
					searchData.onlyLoginOrg = true;
					$("#top-btn-list").css("display", "none");
				} else {
					searchData.onlyLoginOrg = false;
					$("#top-btn-list").css("display", "");
				}
				searchData.parentId = treeNode.id;
				renderTable("#org-list-table");
			}
		}
	};
	ztreeObj = $.fn.zTree.init($("#org-tree"), setting);
	// 弹出添加页面
	$('#add-btn').on('click', function(){
		var index = layer.open({
			type: 2,
			title: '添加机构',
			shadeClose: true,
			shade: 0.8,
			area: ['500px', '250px'],
			content: 'org-add.html',
		});
	});
	// 弹出页面_设置分成比例
	$('#org-list-table').on('click', 'a.benefit', function(){
		currentOrgId = $(this).attr("orgid");
		layer.open({
			type: 2,
			title: '设置分成比例',
			shadeClose: true,
			shade: 0.8,
			area: ['60%', '80%'],
			content: 'benefit-config.html',
		});
	});
	// 弹出页面_设置加价比例
	$('#org-list-table').on('click', 'a.markup', function(){
		currentOrgId = $(this).attr("orgid");
		layer.open({
			type: 2,
			title: '设置加价比例',
			shadeClose: true,
			shade: 0.8,
			area: ['60%', '80%'],
			content: 'markup-config.html',
		});
	});
	// 弹出页面_查看详情
	$('#org-list-table').on('click', 'a.detail', function(){
		currentOrgId = $(this).attr("orgid");
		currentOrgPId = $(this).attr("pid");
		currentOrgLevel = $(this).attr("level");
		layer.open({
			type: 2,
			title: '查看机构详情',
			shadeClose: true,
			shade: 0.8,
			area: ['60%', '80%'],
			content: 'org-detail.html',
		});
	});
});