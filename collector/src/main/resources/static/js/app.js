'use strict';

angular.module('app', [
	'ngAnimate',
	'ngCookies',
	'ngResource',
	'ngSanitize',
	'ngTouch',
	'ngStorage',
	'ui.router',
	'ui.bootstrap',
	'ui.load',
	'ui.jq',
	'ui.validate',
	'oc.lazyLoad',
	'pascalprecht.translate', 
    
	'index'
    	, 'bindCardAdmin'
	, 'capitalAccountAdmin'
	, 'capitalFlowAdmin'
	, 'capitalFlowExtendAdmin'
	, 'frozenCapitalAdmin'
	, 'paymentOrderAdmin'
	, 'publisherAdmin'
	, 'withdrawalsOrderAdmin'
	, 'buyRecordAdmin'
	, 'deferredRecordAdmin'
	, 'settlementAdmin'
	, 'offlineStockOptionTradeAdmin'
	, 'stockOptionTradeAdmin'
	, 'strategyTypeAdmin'
	, 'stockOptionCycleAdmin'
	, 'organizationAdmin'
	, 'organizationAccountAdmin'
	, 'organizationAccountFlowAdmin'
	, 'organizationPublisherAdmin'
	, 'domainAdmin'
	, 'domainTableAdmin'
	, 'domainTableUpdateIndexAdmin'
	, 'domainTableReceiveErrorAdmin'
]);

angular.module('index', [])
    .controller('indexController', indexControllerFn);

function indexControllerFn($scope) {
    
}