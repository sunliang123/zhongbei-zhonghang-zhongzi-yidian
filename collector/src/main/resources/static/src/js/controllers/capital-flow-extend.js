/********************* 资金流水扩展信息 Controller ************************/
angular.module('capitalFlowExtendAdmin', [])
    .controller('capitalFlowExtendAdminController', capitalFlowExtendAdminControllerFn)
    .controller('capitalFlowExtendAddController', capitalFlowExtendAddControllerFn)
    .controller('capitalFlowExtendEditController', capitalFlowExtendEditControllerFn);

function capitalFlowExtendAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.capitalFlowExtendColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
    ]

    // 添加按钮
    $scope.doCapitalFlowExtendAdd = function() {
        $modal.open({
            templateUrl: 'capitalFlowExtend_add.html',
            controller: 'capitalFlowExtendAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doCapitalFlowExtendEdit = function() {
        $modal.open({
            templateUrl: 'capitalFlowExtend_edit.html',
            controller: 'capitalFlowExtendEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doCapitalFlowExtendRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#capitalFlowExtendAdminTable').dataTable();
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
            // 请求删除资金流水扩展信息
            $http({
                method: 'post',
                url: '/collector/capitalFlowExtend/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalFlowExtendAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除资金流水扩展信息成功！');
                } else {
					toaster.pop('error', '', '删除资金流水扩展信息失败：' + response.data.exception.message + "!");                    
                }
                $('#capitalFlowExtendAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalFlowExtendAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function capitalFlowExtendAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalFlowExtend = {};
    
    
    // 请求添加
    $scope.requestCapitalFlowExtendAdd = function(isValid) {
        if(isValid) {
            // 请求添加资金流水扩展信息
            $http({
                method: 'post',
                url: '/collector/capitalFlowExtend/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalFlowExtend)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#capitalFlowExtendAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加资金流水扩展信息成功！');
                } else {
					toaster.pop('error', '', '添加资金流水扩展信息失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function capitalFlowExtendEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.capitalFlowExtend = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#capitalFlowExtendAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.capitalFlowExtend, selectData);
    }
    // 请求修改
    $scope.requestCapitalFlowExtendEdit = function(isValid) {
        if(isValid) {
            // 请求修改资金流水扩展信息
            $http({
                method: 'put',
                url: '/collector/capitalFlowExtend/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.capitalFlowExtend)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#capitalFlowExtendAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改资金流水扩展信息成功！');
                } else {
                    toaster.pop('error', '', '修改资金流水扩展信息失败：' + response.data.message + "!");
                }
                $('#capitalFlowExtendAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#capitalFlowExtendAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}