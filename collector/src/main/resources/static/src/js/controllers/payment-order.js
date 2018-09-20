/********************* 支付订单 Controller ************************/
angular.module('paymentOrderAdmin', [])
    .controller('paymentOrderAdminController', paymentOrderAdminControllerFn)
    .controller('paymentOrderAddController', paymentOrderAddControllerFn)
    .controller('paymentOrderEditController', paymentOrderEditControllerFn);

function paymentOrderAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.paymentOrderColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'paymentNo', title: '支付单号' }
        ,{ data: 'thirdPaymentNo', title: '第三方支付单号' }
        ,{ data: 'amount', title: '金额' }
        ,{ data: 'partAmount', title: '部分支付的金额，支付宝转账方式用户具体转账的金额不可控，因此加入该字段记录' }
        ,{ data: 'type', title: '支付方式' }
        ,{ data: 'alipayAccount', title: '支付宝账号' }
        ,{ data: 'state', title: '支付状态' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'createTime', title: '创建时间' }
        ,{ data: 'updateTime', title: '更新时间' }
    ]

    // 添加按钮
    $scope.doPaymentOrderAdd = function() {
        $modal.open({
            templateUrl: 'paymentOrder_add.html',
            controller: 'paymentOrderAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doPaymentOrderEdit = function() {
        $modal.open({
            templateUrl: 'paymentOrder_edit.html',
            controller: 'paymentOrderEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doPaymentOrderRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#paymentOrderAdminTable').dataTable();
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
            // 请求删除支付订单
            $http({
                method: 'post',
                url: '/collector/paymentOrder/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#paymentOrderAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除支付订单成功！');
                } else {
					toaster.pop('error', '', '删除支付订单失败：' + response.data.exception.message + "!");                    
                }
                $('#paymentOrderAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#paymentOrderAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function paymentOrderAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.paymentOrder = {};
    
    
    // 请求添加
    $scope.requestPaymentOrderAdd = function(isValid) {
        if(isValid) {
            // 请求添加支付订单
            $http({
                method: 'post',
                url: '/collector/paymentOrder/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.paymentOrder)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#paymentOrderAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加支付订单成功！');
                } else {
					toaster.pop('error', '', '添加支付订单失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function paymentOrderEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.paymentOrder = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#paymentOrderAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.paymentOrder, selectData);
    }
    // 请求修改
    $scope.requestPaymentOrderEdit = function(isValid) {
        if(isValid) {
            // 请求修改支付订单
            $http({
                method: 'put',
                url: '/collector/paymentOrder/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.paymentOrder)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#paymentOrderAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改支付订单成功！');
                } else {
                    toaster.pop('error', '', '修改支付订单失败：' + response.data.message + "!");
                }
                $('#paymentOrderAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#paymentOrderAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}