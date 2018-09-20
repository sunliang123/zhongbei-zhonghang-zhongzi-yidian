/********************* 机构账户流水 Controller ************************/
angular.module('organizationAccountFlowAdmin', [])
    .controller('organizationAccountFlowAdminController', organizationAccountFlowAdminControllerFn)
    .controller('organizationAccountFlowAddController', organizationAccountFlowAddControllerFn)
    .controller('organizationAccountFlowEditController', organizationAccountFlowEditControllerFn);

function organizationAccountFlowAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.organizationAccountFlowColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'flowNo', title: '流水号' }
        ,{ data: 'amount', title: '金额' }
        ,{ data: 'originAmount', title: '原始资金' }
        ,{ data: 'type', title: '流水类型' }
        ,{ data: 'remark', title: '备注' }
        ,{ data: 'occurrenceTime', title: '产生时间' }
        ,{ data: 'orgId', title: '对应的机构' }
        ,{ data: 'resourceType', title: '对应的资源类型' }
        ,{ data: 'resourceId', title: '对应的资源ID' }
        ,{ data: 'resourceTradeNo', title: '对应的资源交易单号' }
    ]

    // 添加按钮
    $scope.doOrganizationAccountFlowAdd = function() {
        $modal.open({
            templateUrl: 'organizationAccountFlow_add.html',
            controller: 'organizationAccountFlowAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doOrganizationAccountFlowEdit = function() {
        $modal.open({
            templateUrl: 'organizationAccountFlow_edit.html',
            controller: 'organizationAccountFlowEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doOrganizationAccountFlowRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#organizationAccountFlowAdminTable').dataTable();
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
            // 请求删除机构账户流水
            $http({
                method: 'post',
                url: '/collector/organizationAccountFlow/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAccountFlowAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除机构账户流水成功！');
                } else {
					toaster.pop('error', '', '删除机构账户流水失败：' + response.data.exception.message + "!");                    
                }
                $('#organizationAccountFlowAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAccountFlowAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function organizationAccountFlowAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationAccountFlow = {};
    
    
    // 请求添加
    $scope.requestOrganizationAccountFlowAdd = function(isValid) {
        if(isValid) {
            // 请求添加机构账户流水
            $http({
                method: 'post',
                url: '/collector/organizationAccountFlow/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationAccountFlow)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#organizationAccountFlowAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加机构账户流水成功！');
                } else {
					toaster.pop('error', '', '添加机构账户流水失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function organizationAccountFlowEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationAccountFlow = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#organizationAccountFlowAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.organizationAccountFlow, selectData);
    }
    // 请求修改
    $scope.requestOrganizationAccountFlowEdit = function(isValid) {
        if(isValid) {
            // 请求修改机构账户流水
            $http({
                method: 'put',
                url: '/collector/organizationAccountFlow/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationAccountFlow)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAccountFlowAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改机构账户流水成功！');
                } else {
                    toaster.pop('error', '', '修改机构账户流水失败：' + response.data.message + "!");
                }
                $('#organizationAccountFlowAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAccountFlowAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}