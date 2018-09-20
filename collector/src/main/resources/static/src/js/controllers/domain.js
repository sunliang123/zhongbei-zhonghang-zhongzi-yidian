/********************* 应用 Controller ************************/
angular.module('domainAdmin', [])
    .controller('domainAdminController', domainAdminControllerFn)
    .controller('domainAddController', domainAddControllerFn)
    .controller('domainEditController', domainEditControllerFn);

function domainAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.domainColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
    ]

    // 添加按钮
    $scope.doDomainAdd = function() {
        $modal.open({
            templateUrl: 'domain_add.html',
            controller: 'domainAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doDomainEdit = function() {
        $modal.open({
            templateUrl: 'domain_edit.html',
            controller: 'domainEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doDomainRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#domainAdminTable').dataTable();
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
            // 请求删除应用
            $http({
                method: 'post',
                url: '/collector/domain/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除应用成功！');
                } else {
					toaster.pop('error', '', '删除应用失败：' + response.data.exception.message + "!");                    
                }
                $('#domainAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function domainAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domain = {};
    
    
    // 请求添加
    $scope.requestDomainAdd = function(isValid) {
        if(isValid) {
            // 请求添加应用
            $http({
                method: 'post',
                url: '/collector/domain/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domain)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#domainAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加应用成功！');
                } else {
					toaster.pop('error', '', '添加应用失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function domainEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.domain = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#domainAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.domain, selectData);
    }
    // 请求修改
    $scope.requestDomainEdit = function(isValid) {
        if(isValid) {
            // 请求修改应用
            $http({
                method: 'put',
                url: '/collector/domain/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.domain)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#domainAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改应用成功！');
                } else {
                    toaster.pop('error', '', '修改应用失败：' + response.data.message + "!");
                }
                $('#domainAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#domainAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}