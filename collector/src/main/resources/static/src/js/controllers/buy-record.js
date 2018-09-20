/********************* 点买记录 Controller ************************/
angular.module('buyRecordAdmin', [])
    .controller('buyRecordAdminController', buyRecordAdminControllerFn)
    .controller('buyRecordAddController', buyRecordAddControllerFn)
    .controller('buyRecordEditController', buyRecordEditControllerFn);

function buyRecordAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.buyRecordColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'serialCode', title: '系列号' }
        ,{ data: 'tradeNo', title: '交易编号' }
        ,{ data: 'applyAmount', title: '申请资金' }
        ,{ data: 'serviceFee', title: '信息服务费' }
        ,{ data: 'reserveFund', title: '保证金' }
        ,{ data: 'deferred', title: '是否递延' }
        ,{ data: 'deferredFee', title: '递延费' }
        ,{ data: 'profitPoint', title: '止盈点' }
        ,{ data: 'profitWarnPosition', title: '止盈预警点位' }
        ,{ data: 'profitPosition', title: '止盈点位' }
        ,{ data: 'lossPoint', title: '止损点' }
        ,{ data: 'lossMultiple', title: '杠杆倍数' }
        ,{ data: 'lossWarnPosition', title: '止损预警点位' }
        ,{ data: 'lossPosition', title: '止损点位' }
        ,{ data: 'state', title: '状态' }
        ,{ data: 'numberOfStrand', title: '持股数' }
        ,{ data: 'delegatePrice', title: '委托价格' }
        ,{ data: 'delegateNumber', title: '委托编号' }
        ,{ data: 'buyingTime', title: '点买时间' }
        ,{ data: 'buyingPrice', title: '买入价格' }
        ,{ data: 'expireTime', title: '到期时间' }
        ,{ data: 'windControlType', title: '风控类型' }
        ,{ data: 'sellingTime', title: '卖出时间' }
        ,{ data: 'sellingPrice', title: '卖出价格' }
        ,{ data: 'stockCode', title: '股票代码' }
        ,{ data: 'stockName', title: '股票名称' }
        ,{ data: 'strategyTypeId', title: '策略类型ID' }
        ,{ data: 'investorId', title: '投资人ID' }
        ,{ data: 'investorName', title: '投资人名称' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'publisherSerialCode', title: '发布人序列号' }
        ,{ data: 'publisherPhone', title: '发布人手机号' }
        ,{ data: 'createTime', title: '点买记录创建时间' }
        ,{ data: 'updateTime', title: '点买记录更新时间' }
    ]

    // 添加按钮
    $scope.doBuyRecordAdd = function() {
        $modal.open({
            templateUrl: 'buyRecord_add.html',
            controller: 'buyRecordAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doBuyRecordEdit = function() {
        $modal.open({
            templateUrl: 'buyRecord_edit.html',
            controller: 'buyRecordEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doBuyRecordRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#buyRecordAdminTable').dataTable();
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
            // 请求删除点买记录
            $http({
                method: 'post',
                url: '/collector/buyRecord/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#buyRecordAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除点买记录成功！');
                } else {
					toaster.pop('error', '', '删除点买记录失败：' + response.data.exception.message + "!");                    
                }
                $('#buyRecordAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#buyRecordAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function buyRecordAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.buyRecord = {};
    
	$scope.buyRecord.deferred = "false";
    
    // 请求添加
    $scope.requestBuyRecordAdd = function(isValid) {
        if(isValid) {
            // 请求添加点买记录
            $http({
                method: 'post',
                url: '/collector/buyRecord/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.buyRecord)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#buyRecordAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加点买记录成功！');
                } else {
					toaster.pop('error', '', '添加点买记录失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function buyRecordEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.buyRecord = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#buyRecordAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.buyRecord, selectData);
    }
    // 请求修改
    $scope.requestBuyRecordEdit = function(isValid) {
        if(isValid) {
            // 请求修改点买记录
            $http({
                method: 'put',
                url: '/collector/buyRecord/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.buyRecord)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#buyRecordAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改点买记录成功！');
                } else {
                    toaster.pop('error', '', '修改点买记录失败：' + response.data.message + "!");
                }
                $('#buyRecordAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#buyRecordAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}