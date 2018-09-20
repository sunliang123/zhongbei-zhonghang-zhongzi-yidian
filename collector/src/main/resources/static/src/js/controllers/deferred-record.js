/********************* 递延记录 Controller ************************/
angular.module('deferredRecordAdmin', [])
    .controller('deferredRecordAdminController', deferredRecordAdminControllerFn)
    .controller('deferredRecordAddController', deferredRecordAddControllerFn)
    .controller('deferredRecordEditController', deferredRecordEditControllerFn);

function deferredRecordAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.deferredRecordColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'fee', title: '费用' }
        ,{ data: 'deferredTime', title: '递延时间' }
        ,{ data: 'strategyTypeId', title: '策略类型ID' }
        ,{ data: 'strategyTypeName', title: '策略类型名称' }
        ,{ data: 'cycle', title: '周期' }
        ,{ data: 'buyRecordId', title: '点买记录ID' }
        ,{ data: 'publisherId', title: '发布人ID' }
    ]

    // 添加按钮
    $scope.doDeferredRecordAdd = function() {
        $modal.open({
            templateUrl: 'deferredRecord_add.html',
            controller: 'deferredRecordAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doDeferredRecordEdit = function() {
        $modal.open({
            templateUrl: 'deferredRecord_edit.html',
            controller: 'deferredRecordEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doDeferredRecordRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#deferredRecordAdminTable').dataTable();
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
            // 请求删除递延记录
            $http({
                method: 'post',
                url: '/collector/deferredRecord/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#deferredRecordAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除递延记录成功！');
                } else {
					toaster.pop('error', '', '删除递延记录失败：' + response.data.exception.message + "!");                    
                }
                $('#deferredRecordAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#deferredRecordAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function deferredRecordAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.deferredRecord = {};
    
    
    // 请求添加
    $scope.requestDeferredRecordAdd = function(isValid) {
        if(isValid) {
            // 请求添加递延记录
            $http({
                method: 'post',
                url: '/collector/deferredRecord/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.deferredRecord)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#deferredRecordAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加递延记录成功！');
                } else {
					toaster.pop('error', '', '添加递延记录失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function deferredRecordEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.deferredRecord = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#deferredRecordAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.deferredRecord, selectData);
    }
    // 请求修改
    $scope.requestDeferredRecordEdit = function(isValid) {
        if(isValid) {
            // 请求修改递延记录
            $http({
                method: 'put',
                url: '/collector/deferredRecord/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.deferredRecord)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#deferredRecordAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改递延记录成功！');
                } else {
                    toaster.pop('error', '', '修改递延记录失败：' + response.data.message + "!");
                }
                $('#deferredRecordAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#deferredRecordAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}