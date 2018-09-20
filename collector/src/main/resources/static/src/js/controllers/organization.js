/********************* 机构 Controller ************************/
angular.module('organizationAdmin', [])
    .controller('organizationAdminController', organizationAdminControllerFn)
    .controller('organizationAddController', organizationAddControllerFn)
    .controller('organizationEditController', organizationEditControllerFn);

function organizationAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.organizationColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'code', title: '机构代码' }
        ,{ data: 'name', title: '机构名称' }
        ,{ data: 'level', title: '层级' }
        ,{ data: 'state', title: '机构状态' }
        ,{ data: 'parentId', title: '父级机构' }
        ,{ data: 'createTime', title: '创建时间' }
        ,{ data: 'remark', title: '备注' }
    ]

    // 添加按钮
    $scope.doOrganizationAdd = function() {
        $modal.open({
            templateUrl: 'organization_add.html',
            controller: 'organizationAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doOrganizationEdit = function() {
        $modal.open({
            templateUrl: 'organization_edit.html',
            controller: 'organizationEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doOrganizationRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#organizationAdminTable').dataTable();
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
            // 请求删除机构
            $http({
                method: 'post',
                url: '/collector/organization/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除机构成功！');
                } else {
					toaster.pop('error', '', '删除机构失败：' + response.data.exception.message + "!");                    
                }
                $('#organizationAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function organizationAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organization = {};
    
    
    // 请求添加
    $scope.requestOrganizationAdd = function(isValid) {
        if(isValid) {
            // 请求添加机构
            $http({
                method: 'post',
                url: '/collector/organization/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organization)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#organizationAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加机构成功！');
                } else {
					toaster.pop('error', '', '添加机构失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function organizationEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organization = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#organizationAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.organization, selectData);
    }
    // 请求修改
    $scope.requestOrganizationEdit = function(isValid) {
        if(isValid) {
            // 请求修改机构
            $http({
                method: 'put',
                url: '/collector/organization/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organization)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改机构成功！');
                } else {
                    toaster.pop('error', '', '修改机构失败：' + response.data.message + "!");
                }
                $('#organizationAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}