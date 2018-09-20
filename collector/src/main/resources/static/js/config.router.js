'use strict';

/**
 * Config for the router
 */
angular.module('app')

  .run([
    '$rootScope', 
    '$state', 
    '$stateParams',
    function ($rootScope, $state, $stateParams) {
      $rootScope.$state = $state;
      $rootScope.$stateParams = $stateParams;        
  }])

  .config([
    '$stateProvider', 
    '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider
        .otherwise('/app/index');

      $stateProvider
        .state('app', {
            abstract: true,
            url: '/app',
            templateUrl: 'tpl/app.html'
        })
        .state('app.index', {
            url: '/index',
            templateUrl: 'src/html/index.html'
        })
        
                .state('app.bindCard', {
            url: '/bindCard',
            templateUrl: 'src/html/bindCard/bindCard_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.capitalAccount', {
            url: '/capitalAccount',
            templateUrl: 'src/html/capitalAccount/capitalAccount_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.capitalFlow', {
            url: '/capitalFlow',
            templateUrl: 'src/html/capitalFlow/capitalFlow_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.capitalFlowExtend', {
            url: '/capitalFlowExtend',
            templateUrl: 'src/html/capitalFlowExtend/capitalFlowExtend_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.frozenCapital', {
            url: '/frozenCapital',
            templateUrl: 'src/html/frozenCapital/frozenCapital_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.paymentOrder', {
            url: '/paymentOrder',
            templateUrl: 'src/html/paymentOrder/paymentOrder_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.publisher', {
            url: '/publisher',
            templateUrl: 'src/html/publisher/publisher_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.withdrawalsOrder', {
            url: '/withdrawalsOrder',
            templateUrl: 'src/html/withdrawalsOrder/withdrawalsOrder_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.buyRecord', {
            url: '/buyRecord',
            templateUrl: 'src/html/buyRecord/buyRecord_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.deferredRecord', {
            url: '/deferredRecord',
            templateUrl: 'src/html/deferredRecord/deferredRecord_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.settlement', {
            url: '/settlement',
            templateUrl: 'src/html/settlement/settlement_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.offlineStockOptionTrade', {
            url: '/offlineStockOptionTrade',
            templateUrl: 'src/html/offlineStockOptionTrade/offlineStockOptionTrade_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.stockOptionTrade', {
            url: '/stockOptionTrade',
            templateUrl: 'src/html/stockOptionTrade/stockOptionTrade_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.strategyType', {
            url: '/strategyType',
            templateUrl: 'src/html/strategyType/strategyType_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.stockOptionCycle', {
            url: '/stockOptionCycle',
            templateUrl: 'src/html/stockOptionCycle/stockOptionCycle_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.organization', {
            url: '/organization',
            templateUrl: 'src/html/organization/organization_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.organizationAccount', {
            url: '/organizationAccount',
            templateUrl: 'src/html/organizationAccount/organizationAccount_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.organizationAccountFlow', {
            url: '/organizationAccountFlow',
            templateUrl: 'src/html/organizationAccountFlow/organizationAccountFlow_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.organizationPublisher', {
            url: '/organizationPublisher',
            templateUrl: 'src/html/organizationPublisher/organizationPublisher_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.domain', {
            url: '/domain',
            templateUrl: 'src/html/domain/domain_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.domainTable', {
            url: '/domainTable',
            templateUrl: 'src/html/domainTable/domainTable_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.domainTableUpdateIndex', {
            url: '/domainTableUpdateIndex',
            templateUrl: 'src/html/domainTableUpdateIndex/domainTableUpdateIndex_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })
        .state('app.domainTableReceiveError', {
            url: '/domainTableReceiveError',
            templateUrl: 'src/html/domainTableReceiveError/domainTableReceiveError_admin.html',
            resolve: {
                deps: ['$ocLazyLoad',
                  function( $ocLazyLoad){
                    return $ocLazyLoad.load(['toaster']);
                }]
            }
        })

      }
    ]
  );