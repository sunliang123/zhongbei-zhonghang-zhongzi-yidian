/********************* 资金流水 Controller ************************/
angular.module('capitalFlowAdmin', [])
    .controller('capitalFlowAdminController', capitalFlowAdminControllerFn)
    .controller('capitalFlowAddController', capitalFlowAddControllerFn)
    .controller('capitalFlowEditController', capitalFlowEditControllerFn);

function capitalFlowAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.capitalFlowColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'flowNo', title: '流水号' }
        ,{ data: 'amount', title: '金额' }
        ,{ data: 'type', title: '流水类型' }
        ,{ data: 'remark', title: '备注' }
        ,{ data: 'occurrenceTime', title: '产生时间' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'extendType', title: '扩展类型' }
        ,{ data: 'extendId', title: '扩展ID' }
    ]

    // 添加按钮
    $scope.doCapitalFlowAdd = function() {
        $modal.open({
            templateUrl: 'capitalFlow_add.html',
            controller: 'capitalFlowAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doCapitalFlowEdit = function() {
        $modal.open({
            templateUrl: 'capitalFlow_edit.html',
            controller: 'capitalFlowEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doCapitalFlowRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#capitalFlowAdminTable').dataTable();
        var nTrs = dtObj.fnGetNodes();
        var ids = "";
        for(var i = 0; i < nTrs.length; i++) {
            if(jQuery(nTrs[i]).hasClass('selected')) {
                $scope.selectDataIds.push(dtObj.fnGetData(nTrs[i]).id);
                ids += dtObj.fnGetData(nTrs[i]).id + ",";
            }
        }

		if($scope.selectDataIds.length > 0) {
			ids = ids.substring(0, ids.length-1);
		}
        swal({
            title: "确定删除吗?",
            text: "确定删除后数据不可恢复!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: true
        }, function() {
            // 请求删除资金流水
            $http({
                method: 'post',
                url: '/collector/capitalFlow/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalFlowAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除资金流水成功！');
                } else {
					toaster.pop('error', '', '删除资金流水失败：' + response.data.exception.message + "!");                    
                }
                $('#capitalFlowAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalFlowAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function capitalFlowAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalFlow = {};
    
    
    // 请求添加
    $scope.requestCapitalFlowAdd = function(isValid) {
        if(isValid) {
            // 请求添加资金流水
            $http({
                method: 'post',
                url: '/collector/capitalFlow/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalFlow)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#capitalFlowAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加资金流水成功！');
                } else {
					toaster.pop('error', '', '添加资金流水失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function capitalFlowEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalFlow = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#capitalFlowAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.capitalFlow, selectData);
    }
    // 请求修改
    $scope.requestCapitalFlowEdit = function(isValid) {
        if(isValid) {
            // 请求修改资金流水
            $http({
                method: 'put',
                url: '/collector/capitalFlow/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalFlow)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalFlowAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改资金流水成功！');
                } else {
                    toaster.pop('error', '', '修改资金流水失败：' + response.data.message + "!");
                }
                $('#capitalFlowAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalFlowAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}