/**
 * 机构列表
 */
window.renderTable = function(){};
window.currentPublisherId = null;
window.currentPublisherOrgCode = null;
$(function() {
	window.searchData = { currentOrgCode: currentOrgCode, userType: "1" }
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
            url: "/promotion/customer/adminPage",
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
	            { "data": "publisherId", "title": "客户ID", orderable: false},
	            { "data": "publisherPhone", "title": "客户手机号", orderable: false},
	            { "data": "publisherName", "title": "客户姓名", orderable: false},
	            { "data": "isTest", "title": "用户类型", orderable: false, "render": function(data, type, full, meta) {
	            	if(full.isTest && full.isTest == true) {
	            		return "测试用户";
	            	} else {
	            		return "正式用户";
	            	}
	            }},
	            { "data": "orgName", "title": "所属机构代码/名称", orderable: false, "render": function(data, type, full, meta) {
	            	return full.orgCode + "/" + full.orgName;
	            }},
	            { "data": "availableBalance", "title": "可用资金", orderable: false},
	            { "data": "frozenCapital", "title": "冻结资金", orderable: false},
	            { "data": "balance", "title": "总资金", orderable: false},
	            { "data": "createTime", "title": "注册时间", orderable: false},
	            { "data": "endType", "title": "注册来源", orderable: false, "render": function(data, type, full, meta) {
	            	var endType = full.endType;
	            	// I表示IOS，A表示Android，PC表示PC，H5表示移动端
	            	if(endType === 'I') {
	            		return 'IOS';
	            	} else if(endType === 'A') {
	            		return '安卓'
	            	} else if(endType === 'PC') {
	            		return 'PC网页'
	            	} else if(endType === 'H5') {
	            		return 'H5移动端'
	            	} 
	            	return endType;
	            }},
	            { "data": "id", "width": "110", "title": "操作", "className": "align-center", orderable: false, "render": function(data, type, full, meta) {
	            	return "<a class='publisherorg' orgcode='" + full.orgCode + "' publisherid='" + full.publisherId + "'>修改所属机构</a>";
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
	renderTable("#customer-list-table");
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
		renderTable("#customer-list-table");
	});
	// 修改所属机构
	$("#customer-list-table_wrapper").on("click", ".publisherorg", function(event){
		currentPublisherId = $(this).attr("publisherid");
		currentPublisherOrgCode = $(this).attr("orgcode");
		layer.open({
			type: 2,
			title: '修改所属机构',
			shadeClose: true,
			shade: 0.8,
			area: ['500px', '200px'],
			content: 'set-publisherorg.html'
		});
	});
});