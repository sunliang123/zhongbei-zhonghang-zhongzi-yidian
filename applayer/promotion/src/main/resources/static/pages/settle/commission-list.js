
window.renderTable = function(){};
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
            url: "/promotion/orgflow/pages",
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
	            { "data": "id", "title": "订单ID", orderable: false},
	            { "data": "flowNo", "title": "流水号", orderable: false},
	            { "data": "resourceType", "title": "业务类型", orderable: false,"render": function(data, type, full, meta) {
                    if (full.resourceType) {
                        if (full.resourceType == "BUYRECORD") {
                            return "配资";
                        } else if (full.resourceType == "STOCKOPTIONTRADE") {
                            return "期权";
                        } else if("ORGWITHDRAWALSAPPLY"==full.resourceType){
                        	return "机构提现申请";
						}else{
                            return full.resourceType;
						}
                    }else {
                    	return "";
					}
                }},
	            { "data": "originAmount", "title": "原始收入", orderable: false},
	            { "data": "amount", "title": "平台收入", orderable: false},
	            { "data": "type", "title": "佣金类型", orderable: false,"render": function(data, type, full, meta) {
                    if (full.type) {
                        if (full.type == "ServiceFeeAssign") {
                            return "信息服务费";
                        }else if (full.type == "DeferredChargesAssign") {
                            return "递延费";
                        }else if (full.type == "RightMoneyAssign") {
                            return "期权收益";
                        }else if (full.type == "Withdrawals") {
                            return "提现";
                        }else{
                        	return full.type;
						}
                    }else {
                    	return "";
					}
                }},
	            { "data": "occurrenceTime", "title": "结算时间", orderable: false}
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
	renderTable("#commission-list-table");
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
		renderTable("#commission-list-table");
	});
});