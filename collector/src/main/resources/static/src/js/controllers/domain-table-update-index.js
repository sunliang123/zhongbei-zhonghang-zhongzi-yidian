/********************* 应用表更新最新索引 Controller ************************/
angular.module('domainTableUpdateIndexAdmin', [])
    .controller('domainTableUpdateIndexAdminController', domainTableUpdateIndexAdminControllerFn)
    .controller('domainTableUpdateIndexAddController', domainTableUpdateIndexAddControllerFn)
    .controller('domainTableUpdateIndexEditController', domainTableUpdateIndexEditControllerFn);

function domainTableUpdateIndexAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.domainTableUpdateIndexColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
    ]

    // 添加按钮
    $scope.doDomainTableUpdateIndexAdd = function() {
        $modal.open({
            templateUrl: 'domainTableUpdateIndex_add.html',
            controller: 'domainTableUpdateIndexAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doDomainTableUpdateIndexEdit = function() {
        $modal.open({
            templateUrl: 'domainTableUpdateIndex_edit.html',
            controller: 'domainTableUpdateIndexEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doDomainTableUpdateIndexRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#domainTableUpdateIndexAdminTable').dataTable();
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
            // 请求删除应用表更新最新索引
            $http({
                method: 'post',
                url: '/collector/domainTableUpdateIndex/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableUpdateIndexAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除应用表更新最新索引成功！');
                } else {
					toaster.pop('error', '', '删除应用表更新最新索引失败：' + response.data.exception.message + "!");                    
                }
                $('#domainTableUpdateIndexAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableUpdateIndexAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function domainTableUpdateIndexAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTableUpdateIndex = {};
    
    
    // 请求添加
    $scope.requestDomainTableUpdateIndexAdd = function(isValid) {
        if(isValid) {
            // 请求添加应用表更新最新索引
            $http({
                method: 'post',
                url: '/collector/domainTableUpdateIndex/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTableUpdateIndex)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#domainTableUpdateIndexAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加应用表更新最新索引成功！');
                } else {
					toaster.pop('error', '', '添加应用表更新最新索引失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function domainTableUpdateIndexEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTableUpdateIndex = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#domainTableUpdateIndexAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.domainTableUpdateIndex, selectData);
    }
    // 请求修改
    $scope.requestDomainTableUpdateIndexEdit = function(isValid) {
        if(isValid) {
            // 请求修改应用表更新最新索引
            $http({
                method: 'put',
                url: '/collector/domainTableUpdateIndex/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTableUpdateIndex)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableUpdateIndexAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改应用表更新最新索引成功！');
                } else {
                    toaster.pop('error', '', '修改应用表更新最新索引失败：' + response.data.message + "!");
                }
                $('#domainTableUpdateIndexAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableUpdateIndexAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}