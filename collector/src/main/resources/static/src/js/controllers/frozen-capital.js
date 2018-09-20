/********************* 冻结资金 Controller ************************/
angular.module('frozenCapitalAdmin', [])
    .controller('frozenCapitalAdminController', frozenCapitalAdminControllerFn)
    .controller('frozenCapitalAddController', frozenCapitalAddControllerFn)
    .controller('frozenCapitalEditController', frozenCapitalEditControllerFn);

function frozenCapitalAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.frozenCapitalColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'amount', title: '冻结金额' }
        ,{ data: 'status', title: '状态' }
        ,{ data: 'type', title: '类型' }
        ,{ data: 'frozenTime', title: '冻结时间' }
        ,{ data: 'thawTime', title: '解冻时间' }
        ,{ data: 'buyRecordId', title: '点买记录ID' }
        ,{ data: 'withdrawalsNo', title: '提现单号' }
        ,{ data: 'publisherId', title: '发布人ID' }
    ]

    // 添加按钮
    $scope.doFrozenCapitalAdd = function() {
        $modal.open({
            templateUrl: 'frozenCapital_add.html',
            controller: 'frozenCapitalAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doFrozenCapitalEdit = function() {
        $modal.open({
            templateUrl: 'frozenCapital_edit.html',
            controller: 'frozenCapitalEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doFrozenCapitalRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#frozenCapitalAdminTable').dataTable();
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
            // 请求删除冻结资金
            $http({
                method: 'post',
                url: '/collector/frozenCapital/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#frozenCapitalAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除冻结资金成功！');
                } else {
					toaster.pop('error', '', '删除冻结资金失败：' + response.data.exception.message + "!");                    
                }
                $('#frozenCapitalAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#frozenCapitalAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function frozenCapitalAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.frozenCapital = {};
    
    
    // 请求添加
    $scope.requestFrozenCapitalAdd = function(isValid) {
        if(isValid) {
            // 请求添加冻结资金
            $http({
                method: 'post',
                url: '/collector/frozenCapital/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.frozenCapital)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#frozenCapitalAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加冻结资金成功！');
                } else {
					toaster.pop('error', '', '添加冻结资金失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function frozenCapitalEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.frozenCapital = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#frozenCapitalAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.frozenCapital, selectData);
    }
    // 请求修改
    $scope.requestFrozenCapitalEdit = function(isValid) {
        if(isValid) {
            // 请求修改冻结资金
            $http({
                method: 'put',
                url: '/collector/frozenCapital/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.frozenCapital)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#frozenCapitalAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改冻结资金成功！');
                } else {
                    toaster.pop('error', '', '修改冻结资金失败：' + response.data.message + "!");
                }
                $('#frozenCapitalAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#frozenCapitalAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}