/********************* 策略类型 Controller ************************/
angular.module('strategyTypeAdmin', [])
    .controller('strategyTypeAdminController', strategyTypeAdminControllerFn)
    .controller('strategyTypeAddController', strategyTypeAddControllerFn)
    .controller('strategyTypeEditController', strategyTypeEditControllerFn);

function strategyTypeAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.strategyTypeColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据id' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'name', title: '名称' }
        ,{ data: 'state', title: '状态' }
        ,{ data: 'serviceFeePerWan', title: '每万元收取的服务费用' }
        ,{ data: 'wearingPoint', title: '穿仓点' }
        ,{ data: 'profit', title: '止盈点' }
        ,{ data: 'deferred', title: '递延费' }
        ,{ data: 'cycle', title: '周期' }
    ]

    // 添加按钮
    $scope.doStrategyTypeAdd = function() {
        $modal.open({
            templateUrl: 'strategyType_add.html',
            controller: 'strategyTypeAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doStrategyTypeEdit = function() {
        $modal.open({
            templateUrl: 'strategyType_edit.html',
            controller: 'strategyTypeEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doStrategyTypeRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#strategyTypeAdminTable').dataTable();
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
            // 请求删除策略类型
            $http({
                method: 'post',
                url: '/collector/strategyType/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#strategyTypeAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除策略类型成功！');
                } else {
					toaster.pop('error', '', '删除策略类型失败：' + response.data.exception.message + "!");                    
                }
                $('#strategyTypeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#strategyTypeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function strategyTypeAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.strategyType = {};
    
	$scope.strategyType.state = "false";
    
    // 请求添加
    $scope.requestStrategyTypeAdd = function(isValid) {
        if(isValid) {
            // 请求添加策略类型
            $http({
                method: 'post',
                url: '/collector/strategyType/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.strategyType)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#strategyTypeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加策略类型成功！');
                } else {
					toaster.pop('error', '', '添加策略类型失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function strategyTypeEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.strategyType = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#strategyTypeAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.strategyType, selectData);
    }
    // 请求修改
    $scope.requestStrategyTypeEdit = function(isValid) {
        if(isValid) {
            // 请求修改策略类型
            $http({
                method: 'put',
                url: '/collector/strategyType/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.strategyType)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#strategyTypeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改策略类型成功！');
                } else {
                    toaster.pop('error', '', '修改策略类型失败：' + response.data.message + "!");
                }
                $('#strategyTypeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#strategyTypeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}