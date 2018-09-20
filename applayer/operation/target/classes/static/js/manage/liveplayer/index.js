/**
 * 直播管理
 * 
 * @author luomengan
 */
/**
 * 时间戳转日期
 */
window.renderTable = function(){};
window.currentCid = '';
function timestampToTime(timestamp) {
	var date = new Date(timestamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()) + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}
/**
 * 业务逻辑
 */
$(function() {
	var searchData = {};
	// 加载数据
	function retrieveData(sSource, aoData, fnCallback, oSettings) {
		var draw = (aoData[3].value / 10) + 1;
		oSettings.iDraw = draw;
		// 搜索
		var keyword = aoData[5].value.value;
		searchData.page = (draw - 1);
		searchData.size = 10;
		$.ajax({
            type: "GET",
            url: "../liveplayer/pages?page=" + searchData.page + "&size=" + searchData.size,
            dataType: "json",
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
				{ "data": "cid", "title": "直播频道ID", orderable: false},
	            { "data": "name", "title": "直播频道名称", orderable: false},
	            { "data": "ctime", "title": "创建日期", orderable: false, "render": function(data, type, full, meta) {
	            	return window.timestampToTime(full.ctime);
	            }},
	            { "data": "current", "title": "当前直播频道", orderable: false, "render": function(data, type, full, meta) {
	            	if(full.current == true) {
	            		return "<span style='color: #E8A915;'>是</span>";
	            	} else {
	            		return "否";
	            	}
	            }},
	            { "data": "status", "title": "直播状态", orderable: false, "render": function(data, type, full, meta) {
	            	var status = full.status;
	            	if(status == 0) {
	            		return "<span style='color: rgb(21, 232, 136);'>空闲</span>";
	            	} else if(status == 1) {
	            		return "<span style='color: #E8A915;'>直播中</span>";
	            	} else if(status == 2) {
	            		return "<span style='color: #9E9E9E;'>禁用</span>";
	            	} else if(status == 3) {
	            		return "<span style='color: #E8A915;'>直播录制中</span>";
	            	} else {
	            		return status;
	            	}
	            }},
	            { "data": "cid", "width": 300, "className": "text-center", "title": "操作", orderable: false, "render": function(data, type, full, meta) {
	            	var result = "<button class='address' cid='" + full.cid + "'>地址</button>&nbsp;&nbsp;";
	            	result += "<button class='edit' cid='" + full.cid + "'>编辑</button>&nbsp;&nbsp;";
	            	result += "<button class='delete' cid='" + full.cid + "'>删除</button>&nbsp;&nbsp;";
	            	result += "<button class='current' cid='" + full.cid + "'>设置当前直播频道</button>";
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
	renderTable("#mainTable");
	// 编辑
	$("#mainTable_wrapper").on("click", ".edit", function(event){
		currentCid = $(this).attr("cid");
		layer.open({
			type: 2,
			title: '编辑直播频道',
			shadeClose: true,
			shade: 0.8,
			area: ['500px', '200px'],
			content: '../liveplayer/edit'
		});
	});
	// 添加
	$("#create-btn").on("click", function() {
		layer.open({
			type: 2,
			title: '创建直播频道',
			shadeClose: true,
			shade: 0.8,
			area: ['500px', '200px'],
			content: '../liveplayer/add'
		});
	});
	// 查看推流/拉流地址
	$("#mainTable_wrapper").on("click", ".address", function(event){
		currentCid = $(this).attr("cid");
		layer.open({
			type: 2,
			title: '直播地址',
			shadeClose: true,
			shade: 0.8,
			area: ['1000px', '450px'],
			content: '../liveplayer/address'
		});
	});
	// 删除
	$("#mainTable_wrapper").on("click", ".delete", function(event){
		currentCid = $(this).attr("cid");
		layer.confirm('删除后不能恢复，确定要删除吗？', {
			btn : [ '确定', '取消' ]
		}, function(index) {
			layer.close(index);
			$.ajax({
	            type: "POST",
	            url: "../liveplayer/disable/" + currentCid,
	            contentType: "application/x-www-form-urlencoded",
	            dataType: "json",
	            success: function (jsonResult) {
	            	if(jsonResult.code != "200") {
	            		layer.msg(jsonResult.message);
	            	} else {
	            		renderTable("#mainTable");
	            	}
	            }
	        });
		});
	});
	// 禁用
	$("#mainTable_wrapper").on("click", ".disable", function(event){
		currentCid = $(this).attr("cid");
		layer.confirm('确定要禁用吗？', {
			btn : [ '确定', '取消' ]
		}, function(index) {
			layer.close(index);
			$.ajax({
	            type: "POST",
	            url: "../liveplayer/disable/" + currentCid,
	            contentType: "application/x-www-form-urlencoded",
	            dataType: "json",
	            success: function (jsonResult) {
	            	if(jsonResult.code != "200") {
	            		layer.msg(jsonResult.message);
	            	} else {
	            		renderTable("#mainTable");
	            	}
	            }
	        });
		});
	});
	// 禁用
	$("#mainTable_wrapper").on("click", ".current", function(event){
		currentCid = $(this).attr("cid");
		$.ajax({
            type: "POST",
            url: "../liveplayer/current/" + currentCid,
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: function (jsonResult) {
            	if(jsonResult.code != "200") {
            		layer.msg(jsonResult.message);
            	} else {
            		renderTable("#mainTable");
            	}
            }
        });
	});
});