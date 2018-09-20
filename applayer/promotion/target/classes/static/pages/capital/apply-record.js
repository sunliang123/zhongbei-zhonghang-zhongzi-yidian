/**
 * 机构列表
 */
window.renderTable = function(){};
window.currentOrgId = "17";
$(function() {
	window.searchData = { 
		states: ['1', '2']
	}
	if(level > 1) {
		searchData.orgId = window.currentOrgId;
	}
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
            url: "/promotion/withdrawalsApply/pages",
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
			states: searchData.states
		};
		if(window.level > 1) {
    		searchData.orgId = window.currentOrgId;
    	}
	}
	// 渲染表格
	renderTable = function(id) {
		if($(id + "_wrapper").length > 0) {
			$(id).dataTable().fnDraw();
		} else {
			var columns = [
	            { "data": "id", "title": "申请ID", orderable: false},
	            { "data": "amount", "title": "提现金额", orderable: false},
	            { "data": "applyTime", "title": "申请时间", orderable: false},
	            { "data": "orgName", "title": "申请机构代码/名称", orderable: false, "render": function(data, type, full, meta) {
	            	return full.orgCode + "/" + full.orgName;
	            }},
	            { "data": "state", "title": "状态", orderable: false, "render": function(data, type, full, meta) {
	                var state = full.state;
	                if(state == "TOBEAUDITED") {
	                	return "待审核";
	                } else if(state == "REFUSED") {
	                	return "已拒绝";
	                } else if(state == "PROCESSING") {
	                	return "提现中";
	                } else if(state == "SUCCESS") {
	                	return "提现成功";
	                } else if(state == "FAILURE") {
	                	return "提现失败";
	                } else {
	                	return state;
	                }
	            }}
	        ];
			if(window.level == 1) {
				columns.push({ "data": "id", "width": "200", "title": "操作", "className": "align-center", orderable: false, "render": function(data, type, full, meta) {
	            	var id = full.id;
	            	var state = full.state;
	            	if(state == "TOBEAUDITED") {
	            		return "<a class='refused mr20' applyid='" + id + "' href='javascript:;'>拒绝</a><a class='processing' applyid='" + id + "' href='javascript:;'>确认提现</a>";
	            	} else {
	            		return "";
	            	}
	            }});
			}
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
	renderTable("#apply-record-list-table");
	// 加载layui
	layui.use(['element', 'table'], function() {
	});
	// 搜索
	$('#search-btn').on('click', function(){
		searchData = {
			states: searchData.states
		};
		if(window.level > 1) {
    		searchData.orgId = window.currentOrgId;
    	}
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
		if(searchData.state == '0') {
			searchData.states = ['1', '2'];
		} else if(searchData.state == '1') {
			searchData.states = ['1'];
		} else if(searchData.state == '2') {
			searchData.states = ['2'];
		} else {
			layer.msg("查询参数异常");
			return;
		}
		renderTable("#apply-record-list-table");
	});
	// 拒绝申请
	$('#apply-record-list-table').on('click', 'a.refused', function(){
		var applyId = $(this).attr("applyid");
		$.ajax({
            type: "POST",
            url: "/promotion/withdrawalsApply/refuse/" + applyId,
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		layer.msg("操作成功");
            		renderTable("#apply-record-list-table");
            	} else {
            		layer.msg(jsonResult.message);
            	}
            },
            error: function(jsonResult) {
                parent.layer.msg(jsonResult.responseJSON.message)
            }
        });
	});
	// 确认提现
	$('#apply-record-list-table').on('click', 'a.processing', function(){
		var applyId = $(this).attr("applyid");
		$.ajax({
            type: "POST",
            url: "/promotion/withdrawalsApply/confirm/" + applyId,
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: "json",
            success: function (jsonResult) {
            	if("200" == jsonResult.code) {
            		layer.msg("操作成功");
            		renderTable("#apply-record-list-table");
            	} else {
            		layer.msg(jsonResult.message);
            	}
            }
        });
	});
});