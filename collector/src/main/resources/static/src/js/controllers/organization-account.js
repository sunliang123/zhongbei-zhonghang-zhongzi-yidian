/********************* 机构账户 Controller ************************/
angular.module('organizationAccountAdmin', [])
    .controller('organizationAccountAdminController', organizationAccountAdminControllerFn)
    .controller('organizationAccountAddController', organizationAccountAddControllerFn)
    .controller('organizationAccountEditController', organizationAccountEditControllerFn);

function organizationAccountAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.organizationAccountColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'balance', title: '账户余额' }
        ,{ data: 'availableBalance', title: '账户可用余额' }
        ,{ data: 'frozenCapital', title: '冻结资金' }
        ,{ data: 'updateTime', title: '更新时间' }
        ,{ data: 'orgId', title: '对应的机构' }
    ]

    // 添加按钮
    $scope.doOrganizationAccountAdd = function() {
        $modal.open({
            templateUrl: 'organizationAccount_add.html',
            controller: 'organizationAccountAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doOrganizationAccountEdit = function() {
        $modal.open({
            templateUrl: 'organizationAccount_edit.html',
            controller: 'organizationAccountEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doOrganizationAccountRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#organizationAccountAdminTable').dataTable();
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
            // 请求删除机构账户
            $http({
                method: 'post',
                url: '/collector/organizationAccount/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAccountAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除机构账户成功！');
                } else {
					toaster.pop('error', '', '删除机构账户失败：' + response.data.exception.message + "!");                    
                }
                $('#organizationAccountAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAccountAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function organizationAccountAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationAccount = {};
    
    
    // 请求添加
    $scope.requestOrganizationAccountAdd = function(isValid) {
        if(isValid) {
            // 请求添加机构账户
            $http({
                method: 'post',
                url: '/collector/organizationAccount/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationAccount)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#organizationAccountAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加机构账户成功！');
                } else {
					toaster.pop('error', '', '添加机构账户失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function organizationAccountEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationAccount = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#organizationAccountAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.organizationAccount, selectData);
    }
    // 请求修改
    $scope.requestOrganizationAccountEdit = function(isValid) {
        if(isValid) {
            // 请求修改机构账户
            $http({
                method: 'put',
                url: '/collector/organizationAccount/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationAccount)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAccountAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改机构账户成功！');
                } else {
                    toaster.pop('error', '', '修改机构账户失败：' + response.data.message + "!");
                }
                $('#organizationAccountAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAccountAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}