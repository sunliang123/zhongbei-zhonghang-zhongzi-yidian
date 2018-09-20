/**
 * 机构列表
 */
window.renderTable = function(){};
$(function() {
	window.searchData = { currentOrgCode: currentOrgCode }
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
            url: "/promotion/promotionBuyRecord/adminPage",
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
	            { "data": "buyRecordId", "title": "策略ID", orderable: false},
	            { "data": "tradeNo", "title": "交易单号", orderable: false},
	            { "data": "publisherId", "title": "用户ID", orderable: false},
	            { "data": "publisherPhone", "title": "手机号码", orderable: false},
	            { "data": "stockCode", "title": "股票代码/名称", orderable: false, "render": function(data, type, full, meta) {
	            	return full.stockCode + "/" + full.stockName;
	            }},
	            { "data": "strategyTypeName", "title": "策略类型", orderable: false},
	            { "data": "state", "title": "策略状态", orderable: false, "render": function(data, type, full, meta) {
	                var state = full.state;
	                if(state == "1") {
	                	return "买入中";
	                } else if(state == "2") {
	                	return "买入锁定";
	                } else if(state == "3") {
	                	return "持仓中";
	                } else if(state == "4") {
	                	return "卖出申请";
	                } else if(state == "5") {
	                	return "卖出锁定";
	                } else if(state == "6") {
	                	return "已平仓";
	                } else if(state == "8") {
	                	var windControlType = full.windControlType;
	                	if(windControlType) {
	                		return "卖出失败";
	                	} else {
	                		return "买入失败";
	                	}
	                } else {
	                	return state;
	                }
	            }},
	            { "data": "applyAmount", "title": "策略市值", orderable: false},
	            { "data": "numberOfStrand", "title": "买入股数", orderable: false},
	            { "data": "buyingTime", "title": "买入时间", orderable: false},
	            { "data": "buyingPrice", "title": "买入价格", orderable: false},
	            { "data": "sellingTime", "title": "卖出时间", orderable: false},
	            { "data": "sellingPrice", "title": "卖出价格", orderable: false},
	            { "data": "lastPrice", "title": "当前价格", orderable: false},
	            { "data": "profitOrLoss", "title": "盈亏", orderable: false},
	            { "data": "orgName", "title": "所属机构代码/名称", orderable: false, "render": function(data, type, full, meta) {
	            	return full.orgCode + "/" + full.orgName;
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
	renderTable("#strategy-list-table");
	// 加载layui
	layui.use(['element', 'table'], function() {
	});
	// 搜索
	$('#search-btn').on('click', function(){
		searchData = {
			currentOrgCode: searchData.currentOrgCode
		};
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
		renderTable("#strategy-list-table");
	});
});