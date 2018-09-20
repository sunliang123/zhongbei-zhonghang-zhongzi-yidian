jQuery(function($){
	// 获取文档跟路径
	var path = $("#pathHidden").val();
	
	// 初始化
	var initFunc = function(){
		
		// step 1 : 菜单选中当前功能
		$("#side-menu li>a").on("click", function(){
			$(this).parent("li").addClass("active");
		});
		$("#side-menu li:contains('行业管理')>a").each(function(){
			$(this).trigger("click");
		});
		
		// step 2 : 定义按钮编辑设置
		var editor = new $.fn.dataTable.Editor({
		    ajax: path + "/as/manage/industry/action",
		    idSrc: "id",
		    table: "#userListTable",
		    fields: [{
		        	label: "名称:",
		            name: "name"
		        }, {
		            label: "排序:",
		            name: "sortNum"
		        }
		    ],
		    i18n: {
		    	
		    }
		});
		// step 3 : 创建表格
		$('#userListTable').css("width", "100%");
		var userListTable = $('#userListTable').DataTable({
			lengthChange: false,
		    ajax: path + "/as/manage/industry/list",
		    "columns": [
				{
				    data: null,
				    defaultContent: '',
				    className: 'select-checkbox',
				    orderable: false,
				    width: 15
				},
				{ "data": "id", "title": "序号" },
				{ "data": "name", "title": "名称" },
				{ "data": "sortNum", "title": "排序" }
			],
			"aoColumnDefs": [
				{ sDefaultContent: '', aTargets: [ '_all' ]}
			],
			 select: {
		        style:    'os',
		        selector: 'td:first-child'
		    },
		    "language": {
				"search": '<span>搜索：</span>',
				"paginate": {
					"previous": "上一页",
					"next": "下一页",
					"first": "第一页",
					"last": "最后"
				},
				"zeroRecords": "没有内容",
				"info": "总共_TOTAL_条记录，总共_PAGES_页，当前第_PAGE_页",
				"infoEmpty": "0条记录",
				"infoFiltered": ""
			}
		});
		userListTable.on("draw.dt", function(){
			$('#userListTable').find("th.select-checkbox").removeClass("sorting_asc");            	
	    });
		// step 4 : 为表格添加按钮
		new $.fn.dataTable.Buttons(userListTable, {
			className: "",
			buttons: [
		   		{ extend: "create", text: "<i class='fa fa-plus-circle'></i> 增加", editor: editor, className: "btn btn-primary marginRight10"},
		    	{ extend: "edit",   text: "<i class='fa fa-edit'></i> 修改", editor: editor, className: "btn btn-primary marginRight10"},
		    	{ extend: "remove", text: "<i class='fa fa-times'></i> 删除", editor: editor, className: "btn btn-primary marginRight10"}
			],
			dom: {
				container: {
					className: ""
				},
				button: {
					className: ""
				}
			}
		});
		userListTable.buttons().container().appendTo($('.col-sm-6:eq(0)', userListTable.table().container()));
	};
	
	// 检查是否登录
	var User_CheckLogin = path + "/as/manage/user/checkLogin";
    $.ajax({
		url: User_CheckLogin,
		type: "POST",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: '{"param":{}}',
		success: function (data, textStatus) {
			if(data.data == false){
				window.location.href= path + "/admin/login";
			}else{
				initFunc();
			}
		}
	});
});