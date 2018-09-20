/********************* 策略发布人 Controller ************************/
angular.module('publisherAdmin', [])
    .controller('publisherAdminController', publisherAdminControllerFn)
    .controller('publisherAddController', publisherAddControllerFn)
    .controller('publisherEditController', publisherEditControllerFn);

function publisherAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.publisherColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据id' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'serialCode', title: '序列码' }
        ,{ data: 'phone', title: '电话' }
        ,{ data: 'password', title: '密码' }
        ,{ data: 'promotionCode', title: '推广码' }
        ,{ data: 'promoter', title: '推广人' }
        ,{ data: 'createTime', title: '注册时间' }
        ,{ data: 'isTest', title: '是否为测试用户，测试用户不能提现' }
        ,{ data: 'endType', title: '用户使用的终端类型，I表示IOS，A表示Android，PC表示PC，H5表示移动端' }
        ,{ data: 'headPortrait', title: '头像' }
    ]

    // 添加按钮
    $scope.doPublisherAdd = function() {
        $modal.open({
            templateUrl: 'publisher_add.html',
            controller: 'publisherAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doPublisherEdit = function() {
        $modal.open({
            templateUrl: 'publisher_edit.html',
            controller: 'publisherEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doPublisherRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#publisherAdminTable').dataTable();
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
            // 请求删除策略发布人
            $http({
                method: 'post',
                url: '/collector/publisher/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#publisherAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除策略发布人成功！');
                } else {
					toaster.pop('error', '', '删除策略发布人失败：' + response.data.exception.message + "!");                    
                }
                $('#publisherAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#publisherAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function publisherAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.publisher = {};
    
	$scope.publisher.isTest = "false";
    
    // 请求添加
    $scope.requestPublisherAdd = function(isValid) {
        if(isValid) {
            // 请求添加策略发布人
            $http({
                method: 'post',
                url: '/collector/publisher/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.publisher)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#publisherAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加策略发布人成功！');
                } else {
					toaster.pop('error', '', '添加策略发布人失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function publisherEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.publisher = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#publisherAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.publisher, selectData);
    }
    // 请求修改
    $scope.requestPublisherEdit = function(isValid) {
        if(isValid) {
            // 请求修改策略发布人
            $http({
                method: 'put',
                url: '/collector/publisher/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.publisher)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#publisherAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改策略发布人成功！');
                } else {
                    toaster.pop('error', '', '修改策略发布人失败：' + response.data.message + "!");
                }
                $('#publisherAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#publisherAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}