'use strict';

/**
 * 0.1.1
 * General-purpose jQuery wrapper. Simply pass the plugin name as the expression.
 *
 * It is possible to specify a default set of parameters for each jQuery plugin.
 * Under the jq key, namespace each plugin by that which will be passed to ui-jq.
 * Unfortunately, at this time you can only pre-define the first parameter.
 * @example { jq : { datepicker : { showOn:'click' } } }
 *
 * @param ui-jq {string} The $elm.[pluginName]() to call.
 * @param [ui-options] {mixed} Expression to be evaluated and passed as options to the function
 *     Multiple parameters can be separated by commas
 * @param [ui-refresh] {expression} Watch expression and refire plugin on changes
 *
 * @example <input ui-jq="datepicker" ui-options="{showOn:'click'},secondParameter,thirdParameter" ui-refresh="iChange">
 */
angular.module('ui.jq', ['ui.load']).
    value('uiJqConfig', {
        dataTable: {
            responsive: true,
            dom: "<'row'<'col-sm-6 btn-table-top'><'col-sm-6'f>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-2'l><'col-sm-5'i><'col-sm-5'p>>",
            serverSide: false,
            columns: [{ "data": "name", "title": "未设置列" }],
            language: {
                lengthMenu: '每页_MENU_记录',
                search: '<span>搜索：</span>',
                autoFill: { increment: 'Change each cell by: <input type="number" value="1">' },
                paginate: { previous: "上一页", next: "下一页", first: "第一页", last: "最后" },
                zeroRecords: "没有内容",
                info: "总共_TOTAL_条记录，总共_PAGES_页，当前第_PAGE_页",
                infoEmpty: "0条记录",
                infoFiltered: ""
            },
            processing: true,
            aoColumnDefs: [ { sDefaultContent: '', aTargets: [ '_all' ]} ],
            select: { style: 'os', selector: 'td:first-child' }
        },
        zTree: {
            url: ''
        }
    }).
    directive('uiJq', ['uiJqConfig', 'JQ_CONFIG', 'uiLoad', '$timeout', '$compile', 'tokenService', 
    function uiJqInjectingFunction(uiJqConfig, JQ_CONFIG, uiLoad, $timeout, $compile, tokenService) {

    return {
        restrict: 'A',
        compile: function uiJqCompilingFunction(tElm, tAttrs) {
            if (!angular.isFunction(tElm[tAttrs.uiJq]) && !JQ_CONFIG[tAttrs.uiJq]) {
                throw new Error('ui-jq: The "' + tAttrs.uiJq + '" function does not exist');
            }

            var options = uiJqConfig && uiJqConfig[tAttrs.uiJq];
            return function uiJqLinkingFunction(scope, elm, attrs) {
                function getOptions() {
                    var linkOptions = [];
                    // If ui-options are passed, merge (or override) them onto global defaults and pass to the jQuery method
                    if (attrs.uiOptions) {
                        linkOptions = scope.$eval('[' + attrs.uiOptions + ']');
                        if (angular.isObject(options) && angular.isObject(linkOptions[0])) {
                            linkOptions[0] = angular.extend({}, options, linkOptions[0]);
                        }
                    } else if (options) {
                        linkOptions = [options];
                    }
                    if(linkOptions[0] && tAttrs.uiColumnsAttr) {
                        linkOptions[0].columns = scope[tAttrs.uiColumnsAttr];
                    }
                    return linkOptions;
                }

                // If change compatibility is enabled, the form input's "change" event will trigger an "input" event
                if (attrs.ngModel && elm.is('select,input,textarea')) {
                  elm.bind('change', function() {
                    elm.trigger('input');
                  });
                }

                // Call jQuery method and pass relevant options
                function callPlugin() {
                  $timeout(function() {
                    if(attrs.uiJq === 'dataTable') {
                        if($.fn.dataTable.ext.errMode === "alert") {
                            $.fn.dataTable.ext.errMode = function ( settings, helpPage, message ) { 
                                if(helpPage == 7) {
                                    tokenService.handleHttpFailed({
                                        "status": 403
                                    });
                                } else {
                                    console.log(helpPage);    
                                }
                            };
                        }
                        var datatableSettings = getOptions();
                        datatableSettings[0].ajax = {
                            'url': datatableSettings[0].ajax,
                            "dataType": 'json',
                            "type": "GET",
                            "beforeSend": function (request) {
                                request.setRequestHeader(tokenService.getKey(), tokenService.getToken());
                            }
                        }
                        // dataTable表格
                        var dtObj = elm[attrs.uiJq].apply(elm, datatableSettings);
                        if(attrs.id) {
                            var topHtml = '';
                            if(attrs.uiBtnAddClick) {
                                topHtml += '<button ng-click="' + attrs.uiBtnAddClick + '" class="btn btn-default btn-table-add" type="button"><i class="glyphicon glyphicon-plus"></i>增加</button>';
                            }
                            if(attrs.uiBtnEditClick) {
                                topHtml += '<button ng-click="' + attrs.uiBtnEditClick + '" class="btn btn-default btn-table-edit" type="button" disabled="disabled"><i class="glyphicon glyphicon-edit"></i>修改</button>';
                            }
                            if(attrs.uiBtnRemoveClick) {
                                topHtml += '<button ng-click="' + attrs.uiBtnRemoveClick + '" class="btn btn-default btn-table-remove" type="button" disabled="disabled"><i class="glyphicon glyphicon-floppy-remove"></i>删除</button>';
                            }

                            var wrapper = jQuery("#" + attrs.id + "_wrapper");
                            wrapper.find(".btn-table-top").append($compile(topHtml)(scope));
                            var addBtn = wrapper.find(".btn-table-add"),
                                editBtn = wrapper.find(".btn-table-edit"),
                                removeBtn = wrapper.find(".btn-table-remove");
                            if(attrs.uiBtnAddDisabled && attrs.uiBtnAddDisabled === "true") {
                                addBtn.attr("disabled", "disabled");
                            }
                            dtObj.on("draw.dt", function(){
                                wrapper.find("th.select-checkbox").removeClass("sorting_asc");               
                            });
                            dtObj.on("select.dt", function( e, dt, type, indexes ) {
                                if(wrapper.find("tr.selected").length == 1) {
                                    editBtn.removeAttr("disabled");
                                } else {
                                    editBtn.attr("disabled", "disabled");
                                }
                                removeBtn.removeAttr("disabled");
                            });
                            dtObj.on("deselect.dt", function( e, dt, type, indexes ) {
                                var sLength = wrapper.find("tr.selected").length;
                                if(sLength === 1){
                                    editBtn.removeAttr("disabled");
                                } else {
                                    editBtn.attr("disabled", "disabled");
                                }
                                if(sLength > 0){
                                    removeBtn.removeAttr("disabled");
                                } else {
                                    removeBtn.attr("disabled", "disabled");
                                }
                            });
                        }
                    } else if(attrs.uiJq === 'zTree') {
                        var linkOptions = getOptions();
                        // zTree树
                        var setting = {
                            custom: {
                                pid: 0
                            },
                            view: {
                                dblClickExpand: function(treeId, treeNode) {
                                    return treeNode.level > 0;
                                }
                            },
                            data:{
                                simpleData:{
                                    enable: true,
                                    idKey: "id",
                                    pIdKey: "pid",
                                    rootPId: -1
                                }
                            },
                            async: {
                                enable: true,
                                contentType: "application/json",
                                url: linkOptions[0].url,
                                dataType: "json",
                                type: "get"
                            },
                            callback: {}
                        };
                        if(attrs.uiNodeClick) {
                            setting.callback.onClick = function(event,treeId,treeNode){
                                setting.custom.pid = treeNode.id;
                                if(scope[attrs.uiNodeClick]) {
                                    scope[attrs.uiNodeClick](treeNode.id);
                                }
                            };
                        }
                        var channelTree = jQuery.fn.zTree.init(jQuery('#' + attrs.id), setting);
                    }
                  }, 0, false);
                }

                function refresh() {
                    // If ui-refresh is used, re-fire the the method upon every change
                    if (attrs.uiRefresh) {
                        scope.$watch(attrs.uiRefresh, function() {
                            callPlugin();
                        });
                    }
                }

                if ( JQ_CONFIG[attrs.uiJq] ) {
                    uiLoad.load(JQ_CONFIG[attrs.uiJq]).then(function() {
                        callPlugin();
                        refresh();
                    }).catch(function() {
                    
                    });
                } else {
                    callPlugin();
                    refresh();
                }
            };
        }
    };
}]);