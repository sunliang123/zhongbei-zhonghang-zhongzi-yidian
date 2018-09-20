/********************* 同步错误日志 Controller ************************/
angular.module('domainTableReceiveErrorAdmin', [])
    .controller('domainTableReceiveErrorAdminController', domainTableReceiveErrorAdminControllerFn)
    .controller('domainTableReceiveErrorAddController', domainTableReceiveErrorAddControllerFn)
    .controller('domainTableReceiveErrorEditController', domainTableReceiveErrorEditControllerFn);

function domainTableReceiveErrorAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.domainTableReceiveErrorColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
    ]

    // 添加按钮
    $scope.doDomainTableReceiveErrorAdd = function() {
        $modal.open({
            templateUrl: 'domainTableReceiveError_add.html',
            controller: 'domainTableReceiveErrorAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doDomainTableReceiveErrorEdit = function() {
        $modal.open({
            templateUrl: 'domainTableReceiveError_edit.html',
            controller: 'domainTableReceiveErrorEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doDomainTableReceiveErrorRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#domainTableReceiveErrorAdminTable').dataTable();
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
            // 请求删除同步错误日志
            $http({
                method: 'post',
                url: '/collector/domainTableReceiveError/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableReceiveErrorAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除同步错误日志成功！');
                } else {
					toaster.pop('error', '', '删除同步错误日志失败：' + response.data.exception.message + "!");                    
                }
                $('#domainTableReceiveErrorAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableReceiveErrorAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function domainTableReceiveErrorAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTableReceiveError = {};
    
    
    // 请求添加
    $scope.requestDomainTableReceiveErrorAdd = function(isValid) {
        if(isValid) {
            // 请求添加同步错误日志
            $http({
                method: 'post',
                url: '/collector/domainTableReceiveError/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTableReceiveError)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#domainTableReceiveErrorAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加同步错误日志成功！');
                } else {
					toaster.pop('error', '', '添加同步错误日志失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function domainTableReceiveErrorEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTableReceiveError = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#domainTableReceiveErrorAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.domainTableReceiveError, selectData);
    }
    // 请求修改
    $scope.requestDomainTableReceiveErrorEdit = function(isValid) {
        if(isValid) {
            // 请求修改同步错误日志
            $http({
                method: 'put',
                url: '/collector/domainTableReceiveError/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTableReceiveError)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableReceiveErrorAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改同步错误日志成功！');
                } else {
                    toaster.pop('error', '', '修改同步错误日志失败：' + response.data.message + "!");
                }
                $('#domainTableReceiveErrorAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableReceiveErrorAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}