/********************* 资金账户 Controller ************************/
angular.module('capitalAccountAdmin', [])
    .controller('capitalAccountAdminController', capitalAccountAdminControllerFn)
    .controller('capitalAccountAddController', capitalAccountAddControllerFn)
    .controller('capitalAccountEditController', capitalAccountEditControllerFn);

function capitalAccountAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.capitalAccountColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'balance', title: '账户余额' }
        ,{ data: 'availableBalance', title: '账户可用余额' }
        ,{ data: 'frozenCapital', title: '冻结资金' }
        ,{ data: 'updateTime', title: '更新时间' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'publisherSerialCode', title: '发布人序列号' }
    ]

    // 添加按钮
    $scope.doCapitalAccountAdd = function() {
        $modal.open({
            templateUrl: 'capitalAccount_add.html',
            controller: 'capitalAccountAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doCapitalAccountEdit = function() {
        $modal.open({
            templateUrl: 'capitalAccount_edit.html',
            controller: 'capitalAccountEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doCapitalAccountRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#capitalAccountAdminTable').dataTable();
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
            // 请求删除资金账户
            $http({
                method: 'post',
                url: '/collector/capitalAccount/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalAccountAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除资金账户成功！');
                } else {
					toaster.pop('error', '', '删除资金账户失败：' + response.data.exception.message + "!");                    
                }
                $('#capitalAccountAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalAccountAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function capitalAccountAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalAccount = {};
    
    
    // 请求添加
    $scope.requestCapitalAccountAdd = function(isValid) {
        if(isValid) {
            // 请求添加资金账户
            $http({
                method: 'post',
                url: '/collector/capitalAccount/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalAccount)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#capitalAccountAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加资金账户成功！');
                } else {
					toaster.pop('error', '', '添加资金账户失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function capitalAccountEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalAccount = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#capitalAccountAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.capitalAccount, selectData);
    }
    // 请求修改
    $scope.requestCapitalAccountEdit = function(isValid) {
        if(isValid) {
            // 请求修改资金账户
            $http({
                method: 'put',
                url: '/collector/capitalAccount/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalAccount)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalAccountAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改资金账户成功！');
                } else {
                    toaster.pop('error', '', '修改资金账户失败：' + response.data.message + "!");
                }
                $('#capitalAccountAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalAccountAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}