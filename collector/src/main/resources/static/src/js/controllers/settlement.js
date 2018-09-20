/********************* 结算 Controller ************************/
angular.module('settlementAdmin', [])
    .controller('settlementAdminController', settlementAdminControllerFn)
    .controller('settlementAddController', settlementAddControllerFn)
    .controller('settlementEditController', settlementEditControllerFn);

function settlementAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.settlementColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'buyRecordId', title: '对应的点买记录' }
        ,{ data: 'profitOrLoss', title: '盈亏' }
        ,{ data: 'publisherProfitOrLoss', title: '发布人盈亏' }
        ,{ data: 'investorProfitOrLoss', title: '投资人盈亏' }
        ,{ data: 'settlementTime', title: '结算时间' }
    ]

    // 添加按钮
    $scope.doSettlementAdd = function() {
        $modal.open({
            templateUrl: 'settlement_add.html',
            controller: 'settlementAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doSettlementEdit = function() {
        $modal.open({
            templateUrl: 'settlement_edit.html',
            controller: 'settlementEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doSettlementRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#settlementAdminTable').dataTable();
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
            // 请求删除结算
            $http({
                method: 'post',
                url: '/collector/settlement/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#settlementAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除结算成功！');
                } else {
					toaster.pop('error', '', '删除结算失败：' + response.data.exception.message + "!");                    
                }
                $('#settlementAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#settlementAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function settlementAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.settlement = {};
    
    
    // 请求添加
    $scope.requestSettlementAdd = function(isValid) {
        if(isValid) {
            // 请求添加结算
            $http({
                method: 'post',
                url: '/collector/settlement/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.settlement)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#settlementAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加结算成功！');
                } else {
					toaster.pop('error', '', '添加结算失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function settlementEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.settlement = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#settlementAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.settlement, selectData);
    }
    // 请求修改
    $scope.requestSettlementEdit = function(isValid) {
        if(isValid) {
            // 请求修改结算
            $http({
                method: 'put',
                url: '/collector/settlement/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.settlement)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#settlementAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改结算成功！');
                } else {
                    toaster.pop('error', '', '修改结算失败：' + response.data.message + "!");
                }
                $('#settlementAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#settlementAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}