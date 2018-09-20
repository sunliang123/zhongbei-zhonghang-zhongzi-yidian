jQuery(function($){
	// 获取文档跟路径
	var path = $("#pathHidden").val();
	
	// 初始化
	var initFunc = function(){
		loadDests = function(routeId){};
		// step 1 : 创建表格
		var topBtnHtml = '<button class="btn btn-success " id="dtRunBtn" type="button" disabled="disabled"><i class="glyphicon glyphicon-off"></i>运行</button>';
		topBtnHtml += "&nbsp;&nbsp;";
		topBtnHtml += '<button class="btn btn-danger " id="dtStopBtn" type="button" disabled="disabled"><i class="glyphicon glyphicon-off"></i>停止</button>';
		topBtnHtml += "&nbsp;&nbsp;";
		topBtnHtml += '<button class="btn btn-primary " id="dtCreateBtn" type="button"><i class="fa fa-plus-circle"></i>增加</button>';
		topBtnHtml += "&nbsp;&nbsp;";
		topBtnHtml += '<button class="btn btn-primary " id="dtEditBtn" type="button" disabled="disabled"><i class="fa fa-edit"></i>修改</button>';
		topBtnHtml += "&nbsp;&nbsp;";
		topBtnHtml += '<button class="btn btn-primary " id="dtRemoveBtn" type="button" disabled="disabled"><i class="fa fa-times"></i>删除</button>';
		var routeListTable = $("#routeListTable").lmaDataTable({
			type: "ajax",
			topBtnHtml: topBtnHtml,
			ajax: path + window.RouteDefine_getDtRouteDefines,
		    "columns": [
				{
				    data: null,
				    defaultContent: '',
				    className: 'select-checkbox',
				    orderable: false,
				    width: 15
				},
				{ "data": "status", "width": 60, "className": "alignCenter", "title": "运行状态", "render": function(data, type, full, meta) {
					if(data == "1"){
						return "<span style='background:#4cae4c;'>　</span>";
					}else{
						return "<span style='background:red;'>　</span>";
					}
				}},
				{ "data": "name", "title": "路由名称"},
				{ "data": "endpoint", "title": "路由端点" },
				{ "data": "dests", "className": "destManage", "title": "路由目标数量", "render": function(data, type, full, meta) {
					return "<a href=\"javascript:\" onclick=\"loadDests('" + full.id + "');\">" + data.length + "</a>";
				}},
				{ "data": "isRoundRobin", "title": "是否负载均衡", "render": function(data, type, row) {
					if(data == true){
						return "是";
					}else{
						return "否";
					}
				}}
			],
			"aoColumnDefs": [
				{ sDefaultContent: '', aTargets: [ '_all' ]}
			],
			 select: {
		        style:    'os',
		        selector: 'td:first-child'
		    }
		});
		
		// step 2 : 添加表格事件
		routeListTable.on("draw.dt", function(){
			$('#routeListTable').find("th.select-checkbox").removeClass("sorting_asc");            	
	    });
		routeListTable.on("select.dt", function( e, dt, type, indexes ){
			if($("#routeListTable tr.selected").length == 1){
				$("#dtEditBtn").removeAttr("disabled");
				var nTrs = routeListTable.fnGetNodes();
				var selectData = null;
		        for(var i = 0; i < nTrs.length; i++) {
		            if($(nTrs[i]).hasClass('selected')) {
		            	selectData = routeListTable.fnGetData(nTrs[i]);
		            }
		        }
				if(selectData){
					if(selectData["status"] == 1){
						$("#dtStopBtn").removeAttr("disabled");
					}else {
						$("#dtRunBtn").removeAttr("disabled");
					}
				}
			}else {
				$("#dtEditBtn").attr("disabled", "disabled");
				$("#dtRunBtn").attr("disabled", "disabled");
				$("#dtStopBtn").attr("disabled", "disabled");
			}
			$("#dtRemoveBtn").removeAttr("disabled");
		});
		routeListTable.on("deselect.dt", function( e, dt, type, indexes ){
			if($("#routeListTable tr.selected").length == 1){
				$("#dtEditBtn").removeAttr("disabled");
				var nTrs = routeListTable.fnGetNodes();
				var selectData = null;
		        for(var i = 0; i < nTrs.length; i++){
		            if($(nTrs[i]).hasClass('selected')){
		            	selectData = routeListTable.fnGetData(nTrs[i]);
		            }
		        }
				if(selectData){
					if(selectData["status"] == 1){
						$("#dtStopBtn").removeAttr("disabled");
					}else {
						$("#dtRunBtn").removeAttr("disabled");
					}
				}
			}else {
				$("#dtEditBtn").attr("disabled", "disabled");
				$("#dtRunBtn").attr("disabled", "disabled");
				$("#dtStopBtn").attr("disabled", "disabled");
			}
			if($("#routeListTable tr.selected").length > 0){
				$("#dtRemoveBtn").removeAttr("disabled");
			}else{
				$("#dtRemoveBtn").attr("disabled", "disabled");
			}
		});
		
		// step 3 : 创建添加、修改、删除弹出窗，为添加、修改表单添加验证
		// 绑定弹出框
		$("#createRouteDialog").dialog({
			"title": "添加路由定义",
			"clickBtn": "#dtCreateBtn"
		});
		$("#editRouteDialog").dialog({
			"title": "修改路由定义",
			"clickBtn": "#dtEditBtn"
		});
		$("#removeRouteDialog").dialog({
			"title": "删除路由定义",
			"clickBtn": "#dtRemoveBtn",
			"minHeight": "30px"
		});
		// 为添加、修改表单添加验证
		var createRouteFormValidate = $("#createRouteForm").lma_validate({
			rules: {
				name: {required: true},
				endpoint: {required: true}
			},
			messages: {
				name: {required: "路由名称不能为空"},
				endpoint: {required: "路由端点不能为空"}
			}
		});
		var editRouteFormValidate = $("#editRouteForm").lma_validate({
			rules: {
				name: {required: true},
				endpoint: {required: true}
			},
			messages: {
				name: {required: "路由名称不能为空"},
				endpoint: {required: "路由端点不能为空"}
			}
		});
		// 绑定click事件
		$("#dtCreateBtn").on("click", function(){
			$("#createRouteForm")[0].reset();
			$("#createRouteForm .has-success").removeClass("has-success");
			$("#createRouteForm .glyphicon-ok").removeClass("glyphicon-ok");
			$("#createRouteForm .has-error").removeClass("has-error");
			$("#createRouteForm .glyphicon-remove").removeClass("glyphicon-remove");
			createRouteFormValidate.resetForm();
		});
		$("#dtEditBtn").on("click", function(){
			$("#editRouteForm")[0].reset();
			$("#editRouteForm .has-success").removeClass("has-success");
			$("#editRouteForm .glyphicon-ok").removeClass("glyphicon-ok");
			$("#editRouteForm .has-error").removeClass("has-error");
			$("#editRouteForm .glyphicon-remove").removeClass("glyphicon-remove");
			editRouteFormValidate.resetForm();
			
			var nTrs = routeListTable.fnGetNodes();
			var selectData = null;
	        for(var i = 0; i < nTrs.length; i++){
	            if($(nTrs[i]).hasClass('selected')){
	            	selectData = routeListTable.fnGetData(nTrs[i]);
	            }
	        }
			if(selectData){
				$.setFormValue("#editRouteForm", "id", selectData["id"]);
				$.setFormValue("#editRouteForm", "name", selectData["name"]);
				$.setFormValue("#editRouteForm", "endpoint", selectData["endpoint"]);
				$.setFormValue("#editRouteForm", "isRoundRobin", selectData["isRoundRobin"]);
			}
		});
		$("#dtRemoveBtn").on("click", function() {
			var nTrs = routeListTable.fnGetNodes();
			var selectDataIds = [];
	        for(var i = 0; i < nTrs.length; i++){
	            if($(nTrs[i]).hasClass('selected')){
	            	var selectData = routeListTable.fnGetData(nTrs[i]);
	            	selectDataIds.push(selectData["id"]);
	            }
	        }
			$.setFormValue("#removeRouteForm", "id", JSON.stringify(selectDataIds));
		});
		$("#dtRunBtn").on("click", function() {
			var nTrs = routeListTable.fnGetNodes();
			var selectData = null;
	        for(var i = 0; i < nTrs.length; i++){
	            if($(nTrs[i]).hasClass('selected')){
	            	selectData = routeListTable.fnGetData(nTrs[i]);
	            }
	        }
			if(selectData){
				var routeId = selectData["id"];
				$.dtBtnDoRequest({
					"requestUrl": path + window.RouteDefine_enableRouteDefineWithVersion,
					"message": "运行路由成功",
					"dtObj": routeListTable,
					"data": routeId,
					"resetBtn": ["#dtRunBtn", "#dtStopBtn", "#dtEditBtn", "#dtRemoveBtn"],
				});
			}
		});
		$("#dtStopBtn").on("click", function() {
			var nTrs = routeListTable.fnGetNodes();
			var selectData = null;
	        for(var i = 0; i < nTrs.length; i++){
	            if($(nTrs[i]).hasClass('selected')){
	            	selectData = routeListTable.fnGetData(nTrs[i]);
	            }
	        }
			if(selectData){
				var routeId = selectData["id"];
				$.dtBtnDoRequest({
					"requestUrl": path + window.RouteDefine_disableRouteDefine,
					"message": "停止路由成功",
					"dtObj": routeListTable,
					"data": routeId,
					"resetBtn": ["#dtRunBtn", "#dtStopBtn", "#dtEditBtn", "#dtRemoveBtn"],
				});
			}
		});
		// step 4 : 为弹出框中的添加、修改、删除按钮，添加提交事件
		$.dialogDoRequest({
			"clickBtn": "#createRouteBtn",
			"submitForm": "#createRouteForm",
			"requestUrl": path + window.RouteDefine_addRouteDefine,
			"closeBtn": "#createRouteCloseBtn",
			"resetBtn": ["#dtRunBtn", "#dtEditBtn", "#dtRemoveBtn"],
			"message": "添加路由定义成功",
			"dtObj": routeListTable
		});
		$.dialogDoRequest({
			"clickBtn": "#editRouteBtn",
			"submitForm": "#editRouteForm",
			"requestUrl": path + window.RouteDefine_updateRouteDefine,
			"closeBtn": "#editRouteCloseBtn",
			"resetBtn": ["#dtRunBtn", "#dtEditBtn", "#dtRemoveBtn"],
			"message": "修改路由定义成功",
			"dtObj": routeListTable
		});
		$.dialogDoRequest({
			"clickBtn": "#removeRouteBtn",
			"submitForm": "#removeRouteForm",
			"requestUrl": path + window.RouteDefine_deleteRouteDefine,
			"closeBtn": "#removeRouteCloseBtn",
			"resetBtn": ["#dtRunBtn", "#dtEditBtn", "#dtRemoveBtn"],
			"message": "删除路由定义成功",
			"dtObj": routeListTable,
			"requestSpecialPath": "id"
		});
		// step 5 : 增加路由目标管理窗口
		$("#destDialog").dialog({
			"title": "路由目标管理",
			"clickBtn": "#destTrigger"
		});
		$("#destTable tbody").sortable();
		// 增加一行
		var insertDestRow = function(id, url, version, status){
			var sortNum = $("#destTable tbody tr").length + 1;
			var statusSelect = '<select name="status" class="form-control"><option value="0" ' + (status=='0'?'selected="selected"':'') + '>停用</option><option value="1" ' + (status=='1'?'selected="selected"':'') + '>启用</option></select>';
			var trHtml = '<tr>';
			trHtml += '<td><input type="hidden" name="id" value="' + id + '" /><span class="removeDestRow glyphicon glyphicon-remove"></span></td>';
			trHtml += '<td><input name="url" type="text" value="' + url + '" class="form-control" /></td>';
			trHtml += '<td><input name="version" type="text" value="' + (version?version:"") + '" class="form-control" /></td>';
			trHtml += '<td class="dragTd">' + sortNum + '</td>';
			trHtml += '<td>' + statusSelect + '</td>';
			trHtml += '</tr>';
			var rowObj = $(trHtml);
			$("#destTable tbody").append(rowObj);
		};
		$("#addDestRow").on("click", function(){
			insertDestRow("", "", "", 0);
		});
		$("#destTable").on("click", ".removeDestRow", function(){
			$(this).parent().parent().remove();
		});
		loadDests = function(routeId) {
			$.setFormValue("#destForm", "routeId", routeId);
			$.ajax({
				url: path + window.RouteDest_getRouteDestinations,
				type: "POST",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				data: '{"param": ' + routeId + '}',
				success: function (data, textStatus) {
					var message = "";
					if(data.error == false){
						$("#destTrigger").trigger("click");
						$("#destTable tbody").empty();
						for(var i = 0; i < data.data.length; i++) {
							insertDestRow(data.data[i].id, data.data[i].url, data.data[i].version, data.data[i].status);
						}
					}else{
						message = data.exception.message;
		                toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    timeOut: 2000,
		                    positionClass: "toast-top-right"
		                };
		                toastr.error(message, '');
					}
				}
			});
		};
		$("#destRouteBtn").on("click", function() {
			var requestData = $("#destForm").serializeObject();
			var destArr = [];
			var orderNum = 1;
			if(requestData.id != null){
				if(requestData.status instanceof Array){
					for(var i = 0; i < requestData.url.length; i++) {
						if(requestData.url[i] && requestData.url[i] != ""){
							destArr.push({
								"id": (requestData.id[i] && requestData.id[i] != "")?requestData.id[i]:"0",
								"url": requestData.url[i],
								"orderNum": orderNum,
								"version": (requestData.version[i] && requestData.version[i] != "")?requestData.version[i]:"",
								"status": requestData.status[i],
								"routeId": requestData.routeId
							});
							orderNum++;
						}
					}
				}else {
					destArr.push({
						"id": (requestData.id && requestData.id != "")?requestData.id:"0",
						"url": requestData.url,
						"orderNum": orderNum,
						"version": requestData.version,
						"status": requestData.status,
						"routeId": requestData.routeId
					});
				}
			}
			var requestObject = {
				"routeId": requestData.routeId,
				"dests": destArr
			};
			$.ajax({
				url: path + window.RouteDest_saveAllRouteDestinations,
				type: "POST",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				data: '{"param": ' + JSON.stringify(requestObject) + '}',
				success: function (data, textStatus) {
					if(data.error == false){
						$("#destCloseBtn").trigger("click");
						routeListTable.api().ajax.reload();
					} else {
						var message = data.exception.message;
		                toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    timeOut: 2000,
		                    positionClass: "toast-top-right"
		                };
		                toastr.error(message, '');
					}
				}
			});
		});
	};
	
	initFunc();
	
});