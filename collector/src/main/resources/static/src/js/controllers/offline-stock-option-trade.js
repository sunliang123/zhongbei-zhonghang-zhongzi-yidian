/********************* 线下期权交易信息 Controller ************************/
angular.module('offlineStockOptionTradeAdmin', [])
    .controller('offlineStockOptionTradeAdminController', offlineStockOptionTradeAdminControllerFn)
    .controller('offlineStockOptionTradeAddController', offlineStockOptionTradeAddControllerFn)
    .controller('offlineStockOptionTradeEditController', offlineStockOptionTradeEditControllerFn);

function offlineStockOptionTradeAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.offlineStockOptionTradeColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'thirdTradeNo', title: '对应的第三方交易单号' }
        ,{ data: 'state', title: '状态' }
        ,{ data: 'stockCode', title: '股票代码' }
        ,{ data: 'stockName', title: '股票名称' }
        ,{ data: 'nominalAmount', title: '名义本金' }
        ,{ data: 'rightMoneyRatio', title: '权利金比例' }
        ,{ data: 'rightMoney', title: '权利金' }
        ,{ data: 'cycle', title: '周期' }
        ,{ data: 'expireTime', title: '到期时间' }
        ,{ data: 'buyingTime', title: '成交时间' }
        ,{ data: 'buyingPrice', title: '成交价格' }
        ,{ data: 'sellingTime', title: '卖出时间' }
        ,{ data: 'sellingPrice', title: '卖出价格' }
        ,{ data: 'rightTime', title: '行权时间' }
        ,{ data: 'profit', title: '盈利' }
        ,{ data: 'orgId', title: '对应的期权第三方机构' }
    ]

    // 添加按钮
    $scope.doOfflineStockOptionTradeAdd = function() {
        $modal.open({
            templateUrl: 'offlineStockOptionTrade_add.html',
            controller: 'offlineStockOptionTradeAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doOfflineStockOptionTradeEdit = function() {
        $modal.open({
            templateUrl: 'offlineStockOptionTrade_edit.html',
            controller: 'offlineStockOptionTradeEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doOfflineStockOptionTradeRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#offlineStockOptionTradeAdminTable').dataTable();
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
            // 请求删除线下期权交易信息
            $http({
                method: 'post',
                url: '/collector/offlineStockOptionTrade/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#offlineStockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除线下期权交易信息成功！');
                } else {
					toaster.pop('error', '', '删除线下期权交易信息失败：' + response.data.exception.message + "!");                    
                }
                $('#offlineStockOptionTradeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#offlineStockOptionTradeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function offlineStockOptionTradeAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.offlineStockOptionTrade = {};
    
    
    // 请求添加
    $scope.requestOfflineStockOptionTradeAdd = function(isValid) {
        if(isValid) {
            // 请求添加线下期权交易信息
            $http({
                method: 'post',
                url: '/collector/offlineStockOptionTrade/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.offlineStockOptionTrade)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#offlineStockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加线下期权交易信息成功！');
                } else {
					toaster.pop('error', '', '添加线下期权交易信息失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function offlineStockOptionTradeEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.offlineStockOptionTrade = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#offlineStockOptionTradeAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.offlineStockOptionTrade, selectData);
    }
    // 请求修改
    $scope.requestOfflineStockOptionTradeEdit = function(isValid) {
        if(isValid) {
            // 请求修改线下期权交易信息
            $http({
                method: 'put',
                url: '/collector/offlineStockOptionTrade/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.offlineStockOptionTrade)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#offlineStockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改线下期权交易信息成功！');
                } else {
                    toaster.pop('error', '', '修改线下期权交易信息失败：' + response.data.message + "!");
                }
                $('#offlineStockOptionTradeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#offlineStockOptionTradeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}