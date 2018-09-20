/********************* 用户股票期权交易信息 Controller ************************/
angular.module('stockOptionTradeAdmin', [])
    .controller('stockOptionTradeAdminController', stockOptionTradeAdminControllerFn)
    .controller('stockOptionTradeAddController', stockOptionTradeAddControllerFn)
    .controller('stockOptionTradeEditController', stockOptionTradeEditControllerFn);

function stockOptionTradeAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.stockOptionTradeColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据id' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'tradeNo', title: '交易单号' }
        ,{ data: 'state', title: '交易状态' }
        ,{ data: 'stockCode', title: '股票代码' }
        ,{ data: 'stockName', title: '股票名称' }
        ,{ data: 'nominalAmount', title: '名义本金' }
        ,{ data: 'rightMoneyRatio', title: '权利金比例' }
        ,{ data: 'rightMoney', title: '权利金' }
        ,{ data: 'cycleId', title: '周期ID' }
        ,{ data: 'cycle', title: '周期' }
        ,{ data: 'cycleMonth', title: '周期月数' }
        ,{ data: 'cycleName', title: '周期名称' }
        ,{ data: 'expireTime', title: '到期时间' }
        ,{ data: 'applyTime', title: '申购时间' }
        ,{ data: 'buyingType', title: '买入方式' }
        ,{ data: 'buyingTime', title: '成交时间' }
        ,{ data: 'buyingPrice', title: '成交价格' }
        ,{ data: 'sellingTime', title: '卖出时间' }
        ,{ data: 'sellingPrice', title: '卖出价格' }
        ,{ data: 'rightTime', title: '行权时间' }
        ,{ data: 'profit', title: '盈利' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'publisherPhone', title: '发布人手机号码' }
        ,{ data: 'updateTime', title: '更新时间' }
        ,{ data: 'offlineTrade', title: '对应的线下期权交易信息' }
        ,{ data: 'status', title: '线下期权交易状态' }
        ,{ data: 'dataId', title: '数据id' }
    ]

    // 添加按钮
    $scope.doStockOptionTradeAdd = function() {
        $modal.open({
            templateUrl: 'stockOptionTrade_add.html',
            controller: 'stockOptionTradeAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doStockOptionTradeEdit = function() {
        $modal.open({
            templateUrl: 'stockOptionTrade_edit.html',
            controller: 'stockOptionTradeEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doStockOptionTradeRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#stockOptionTradeAdminTable').dataTable();
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
            // 请求删除用户股票期权交易信息
            $http({
                method: 'post',
                url: '/collector/stockOptionTrade/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#stockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除用户股票期权交易信息成功！');
                } else {
					toaster.pop('error', '', '删除用户股票期权交易信息失败：' + response.data.exception.message + "!");                    
                }
                $('#stockOptionTradeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#stockOptionTradeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function stockOptionTradeAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.stockOptionTrade = {};
    
    
    // 请求添加
    $scope.requestStockOptionTradeAdd = function(isValid) {
        if(isValid) {
            // 请求添加用户股票期权交易信息
            $http({
                method: 'post',
                url: '/collector/stockOptionTrade/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.stockOptionTrade)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#stockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加用户股票期权交易信息成功！');
                } else {
					toaster.pop('error', '', '添加用户股票期权交易信息失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function stockOptionTradeEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.stockOptionTrade = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#stockOptionTradeAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.stockOptionTrade, selectData);
    }
    // 请求修改
    $scope.requestStockOptionTradeEdit = function(isValid) {
        if(isValid) {
            // 请求修改用户股票期权交易信息
            $http({
                method: 'put',
                url: '/collector/stockOptionTrade/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.stockOptionTrade)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#stockOptionTradeAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改用户股票期权交易信息成功！');
                } else {
                    toaster.pop('error', '', '修改用户股票期权交易信息失败：' + response.data.message + "!");
                }
                $('#stockOptionTradeAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#stockOptionTradeAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}