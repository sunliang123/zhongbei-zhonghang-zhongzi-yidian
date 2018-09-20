/********************* 期权周期 Controller ************************/
angular.module('stockOptionCycleAdmin', [])
    .controller('stockOptionCycleAdminController', stockOptionCycleAdminControllerFn)
    .controller('stockOptionCycleAddController', stockOptionCycleAddControllerFn)
    .controller('stockOptionCycleEditController', stockOptionCycleEditControllerFn);

function stockOptionCycleAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.stockOptionCycleColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据id' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'name', title: '周期名称' }
        ,{ data: 'cycle', title: '周期天数' }
        ,{ data: 'cycleMonth', title: '周期月数' }
    ]

    // 添加按钮
    $scope.doStockOptionCycleAdd = function() {
        $modal.open({
            templateUrl: 'stockOptionCycle_add.html',
            controller: 'stockOptionCycleAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doStockOptionCycleEdit = function() {
        $modal.open({
            templateUrl: 'stockOptionCycle_edit.html',
            controller: 'stockOptionCycleEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doStockOptionCycleRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#stockOptionCycleAdminTable').dataTable();
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
            // 请求删除期权周期
            $http({
                method: 'post',
                url: '/collector/stockOptionCycle/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#stockOptionCycleAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除期权周期成功！');
                } else {
					toaster.pop('error', '', '删除期权周期失败：' + response.data.exception.message + "!");                    
                }
                $('#stockOptionCycleAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#stockOptionCycleAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function stockOptionCycleAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.stockOptionCycle = {};
    
    
    // 请求添加
    $scope.requestStockOptionCycleAdd = function(isValid) {
        if(isValid) {
            // 请求添加期权周期
            $http({
                method: 'post',
                url: '/collector/stockOptionCycle/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.stockOptionCycle)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#stockOptionCycleAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加期权周期成功！');
                } else {
					toaster.pop('error', '', '添加期权周期失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function stockOptionCycleEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.stockOptionCycle = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#stockOptionCycleAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.stockOptionCycle, selectData);
    }
    // 请求修改
    $scope.requestStockOptionCycleEdit = function(isValid) {
        if(isValid) {
            // 请求修改期权周期
            $http({
                method: 'put',
                url: '/collector/stockOptionCycle/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.stockOptionCycle)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#stockOptionCycleAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改期权周期成功！');
                } else {
                    toaster.pop('error', '', '修改期权周期失败：' + response.data.message + "!");
                }
                $('#stockOptionCycleAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#stockOptionCycleAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}