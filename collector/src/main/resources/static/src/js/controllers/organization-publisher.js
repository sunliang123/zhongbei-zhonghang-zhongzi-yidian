/********************* 机构推广的发布人 Controller ************************/
angular.module('organizationPublisherAdmin', [])
    .controller('organizationPublisherAdminController', organizationPublisherAdminControllerFn)
    .controller('organizationPublisherAddController', organizationPublisherAddControllerFn)
    .controller('organizationPublisherEditController', organizationPublisherEditControllerFn);

function organizationPublisherAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.organizationPublisherColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'publisherId', title: '发布人ID' }
        ,{ data: 'orgCode', title: '机构代码' }
        ,{ data: 'createTime', title: '创建时间' }
    ]

    // 添加按钮
    $scope.doOrganizationPublisherAdd = function() {
        $modal.open({
            templateUrl: 'organizationPublisher_add.html',
            controller: 'organizationPublisherAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doOrganizationPublisherEdit = function() {
        $modal.open({
            templateUrl: 'organizationPublisher_edit.html',
            controller: 'organizationPublisherEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doOrganizationPublisherRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#organizationPublisherAdminTable').dataTable();
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
            // 请求删除机构推广的发布人
            $http({
                method: 'post',
                url: '/collector/organizationPublisher/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationPublisherAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除机构推广的发布人成功！');
                } else {
					toaster.pop('error', '', '删除机构推广的发布人失败：' + response.data.exception.message + "!");                    
                }
                $('#organizationPublisherAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationPublisherAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function organizationPublisherAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationPublisher = {};
    
    
    // 请求添加
    $scope.requestOrganizationPublisherAdd = function(isValid) {
        if(isValid) {
            // 请求添加机构推广的发布人
            $http({
                method: 'post',
                url: '/collector/organizationPublisher/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationPublisher)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#organizationPublisherAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加机构推广的发布人成功！');
                } else {
					toaster.pop('error', '', '添加机构推广的发布人失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function organizationPublisherEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.organizationPublisher = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#organizationPublisherAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.organizationPublisher, selectData);
    }
    // 请求修改
    $scope.requestOrganizationPublisherEdit = function(isValid) {
        if(isValid) {
            // 请求修改机构推广的发布人
            $http({
                method: 'put',
                url: '/collector/organizationPublisher/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.organizationPublisher)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#organizationPublisherAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改机构推广的发布人成功！');
                } else {
                    toaster.pop('error', '', '修改机构推广的发布人失败：' + response.data.message + "!");
                }
                $('#organizationPublisherAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#organizationPublisherAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}