/********************* 提现订单 Controller ************************/
angular.module('withdrawalsOrderAdmin', [])
    .controller('withdrawalsOrderAdminController', withdrawalsOrderAdminControllerFn)
    .controller('withdrawalsOrderAddController', withdrawalsOrderAddControllerFn)
    .controller('withdrawalsOrderEditController', withdrawalsOrderEditControllerFn);

function withdrawalsOrderAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.withdrawalsOrderColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据id' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'withdrawalsNo', title: '提现单号' }
        ,{ data: 'thirdWithdrawalsNo', title: '第三方单号' }
        ,{ data: 'thirdRespCode', title: '请求第三方代扣响应码' }
        ,{ data: 'thirdRespMsg', title: '请求第三方代扣响应提示' }
        ,{ data: 'amount', title: '金额' }
        ,{ data: 'state', title: '提现状态' }
        ,{ data: 'name', title: '姓名' }
        ,{ data: 'idCard', title: '身份证号' }
        ,{ data: 'bankCard', title: '银行卡号' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'createTime', title: '创建时间' }
        ,{ data: 'updateTime', title: '更新时间' }
    ]

    // 添加按钮
    $scope.doWithdrawalsOrderAdd = function() {
        $modal.open({
            templateUrl: 'withdrawalsOrder_add.html',
            controller: 'withdrawalsOrderAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doWithdrawalsOrderEdit = function() {
        $modal.open({
            templateUrl: 'withdrawalsOrder_edit.html',
            controller: 'withdrawalsOrderEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doWithdrawalsOrderRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#withdrawalsOrderAdminTable').dataTable();
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
            // 请求删除提现订单
            $http({
                method: 'post',
                url: '/collector/withdrawalsOrder/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#withdrawalsOrderAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除提现订单成功！');
                } else {
					toaster.pop('error', '', '删除提现订单失败：' + response.data.exception.message + "!");                    
                }
                $('#withdrawalsOrderAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#withdrawalsOrderAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function withdrawalsOrderAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.withdrawalsOrder = {};
    
    
    // 请求添加
    $scope.requestWithdrawalsOrderAdd = function(isValid) {
        if(isValid) {
            // 请求添加提现订单
            $http({
                method: 'post',
                url: '/collector/withdrawalsOrder/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.withdrawalsOrder)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#withdrawalsOrderAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加提现订单成功！');
                } else {
					toaster.pop('error', '', '添加提现订单失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function withdrawalsOrderEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.withdrawalsOrder = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#withdrawalsOrderAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.withdrawalsOrder, selectData);
    }
    // 请求修改
    $scope.requestWithdrawalsOrderEdit = function(isValid) {
        if(isValid) {
            // 请求修改提现订单
            $http({
                method: 'put',
                url: '/collector/withdrawalsOrder/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.withdrawalsOrder)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#withdrawalsOrderAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改提现订单成功！');
                } else {
                    toaster.pop('error', '', '修改提现订单失败：' + response.data.message + "!");
                }
                $('#withdrawalsOrderAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#withdrawalsOrderAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}