/********************* 绑卡 Controller ************************/
angular.module('bindCardAdmin', [])
    .controller('bindCardAdminController', bindCardAdminControllerFn)
    .controller('bindCardAddController', bindCardAddControllerFn)
    .controller('bindCardEditController', bindCardEditControllerFn);

function bindCardAdminControllerFn($scope, $modal, toaster, $http, tokenService) {
    $scope.bindCardColumns = [
        { data: null, defaultContent: '', className: 'select-checkbox', width: 15},
        { data: 'id', title: '主键' }
        ,{ data: 'dataId', title: '数据ID' }
        ,{ data: 'domain', title: '应用域' }
        ,{ data: 'idCard', title: '身份证号' }
        ,{ data: 'phone', title: '手机号' }
        ,{ data: 'bankCard', title: '银行卡号' }
        ,{ data: 'bankCode', title: '银行代码' }
        ,{ data: 'bankName', title: '银行名称' }
        ,{ data: 'branchName', title: '支行名称' }
        ,{ data: 'branchCode', title: '支行cnaps code' }
        ,{ data: 'resourceType', title: '绑卡对象的资源类型' }
        ,{ data: 'resourceId', title: '绑卡对象的ID' }
        ,{ data: 'contractNo', title: '对应的支付平台编号' }
        ,{ data: 'createTime', title: '绑卡时间' }
    ]

    // 添加按钮
    $scope.doBindCardAdd = function() {
        $modal.open({
            templateUrl: 'bindCard_add.html',
            controller: 'bindCardAddController',
            size: 'md'
        });
    }
    // 修改按钮
    $scope.doBindCardEdit = function() {
        $modal.open({
            templateUrl: 'bindCard_edit.html',
            controller: 'bindCardEditController',
            size: 'md'
        });
    }

    // 删除按钮
    $scope.doBindCardRemove = function() {
        // 获取已选择的行id
        $scope.selectDataIds = [];
        var dtObj = $('#bindCardAdminTable').dataTable();
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
            // 请求删除绑卡
            $http({
                method: 'post',
                url: '/collector/bindCard/deletes',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param({ids: ids})
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#bindCardAdminTable').dataTable().api().ajax.reload();
                    toaster.pop('success', '', '删除绑卡成功！');
                } else {
					toaster.pop('error', '', '删除绑卡失败：' + response.data.exception.message + "!");                    
                }
                $('#bindCardAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#bindCardAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        });
    }
}

function bindCardAddControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.bindCard = {};
    
    
    // 请求添加
    $scope.requestBindCardAdd = function(isValid) {
        if(isValid) {
            // 请求添加绑卡
            $http({
                method: 'post',
                url: '/collector/bindCard/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.bindCard)
            }).then(function(response) {
                if(response.data.code === "200") {
                    $('#bindCardAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '添加绑卡成功！');
                } else {
					toaster.pop('error', '', '添加绑卡失败：' + response.data.exception.message + "!");                    
                }
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}

function bindCardEditControllerFn($scope, $modalInstance, toaster, $http, tokenService) {
    $scope.bindCard = {};
    // 获取已选择的行
    var selectData = null;
    var dtObj = $('#bindCardAdminTable').dataTable();
    var nTrs = dtObj.fnGetNodes();
    for(var i = 0; i < nTrs.length; i++) {
        if(jQuery(nTrs[i]).hasClass('selected')) {
            selectData = dtObj.fnGetData(nTrs[i]);
        }
    }
    if(selectData) {
        jQuery.extend($scope.bindCard, selectData);
    }
    // 请求修改
    $scope.requestBindCardEdit = function(isValid) {
        if(isValid) {
            // 请求修改绑卡
            $http({
                method: 'put',
                url: '/collector/bindCard/',
                headers: {
                	'Content-Type': 'application/x-www-form-urlencoded' 
                },
                data: jQuery.param($scope.bindCard)
            }).then(function(response) {
                if(response.data.code === "200") {
                	$('#bindCardAdminTable').dataTable().api().ajax.reload();
                    $modalInstance.close();
                    toaster.pop('success', '', '修改绑卡成功！');
                } else {
                    toaster.pop('error', '', '修改绑卡失败：' + response.data.message + "!");
                }
                $('#bindCardAdminTable_wrapper').find(".btn-table-edit").attr("disabled", "disabled");
                $('#bindCardAdminTable_wrapper').find(".btn-table-remove").attr("disabled", "disabled");
            }, function(response) {
                tokenService.handleHttpFailed(response);
            });
        }
    }
}