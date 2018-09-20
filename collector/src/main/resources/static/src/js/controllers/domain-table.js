/********************* 应用表 Controller ************************/
angular.module('domainTableAdmin', [])
    .controller('domainTableAdminController', domainTableAdminControllerFn)
    .controller('domainTableAddController', domainTableAddControllerFn)
    .controller('domainTableEditController', domainTableEditControllerFn);

function domainTableAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.domainTableColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
    ]

    // 添加按钮
    $scope.doDomainTableAdd = function() {
        $modal.open({
            templateUrl: 'domainTable_add.html',
            controller: 'domainTableAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doDomainTableEdit = function() {
        $modal.open({
            templateUrl: 'domainTable_edit.html',
            controller: 'domainTableEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doDomainTableRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#domainTableAdminTable').dataTable();
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
            // 请求删除应用表
            $http({
                method: 'post',
                url: '/collector/domainTable/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除应用表成功！');
                } else {
					toaster.pop('error', '', '删除应用表失败：' + response.data.exception.message + "!");                    
                }
                $('#domainTableAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function domainTableAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTable = {};
    
    
    // 请求添加
    $scope.requestDomainTableAdd = function(isValid) {
        if(isValid) {
            // 请求添加应用表
            $http({
                method: 'post',
                url: '/collector/domainTable/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTable)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#domainTableAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加应用表成功！');
                } else {
					toaster.pop('error', '', '添加应用表失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function domainTableEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domainTable = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#domainTableAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.domainTable, selectData);
    }
    // 请求修改
    $scope.requestDomainTableEdit = function(isValid) {
        if(isValid) {
            // 请求修改应用表
            $http({
                method: 'put',
                url: '/collector/domainTable/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domainTable)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainTableAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改应用表成功！');
                } else {
                    toaster.pop('error', '', '修改应用表失败：' + response.data.message + "!");
                }
                $('#domainTableAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainTableAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}