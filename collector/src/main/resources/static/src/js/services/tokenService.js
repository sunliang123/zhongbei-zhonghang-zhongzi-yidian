angular.module('app').service('tokenService', function($localStorage, toaster) {
    this.getToken = function() {
        return "";
    	return "eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IiIsInVzZXJJZCI6Mywic3ViIjoiMTM5Mjg5NTIyNTQiLCJleHAiOjQ0NzAwMDA2OTY1fQ._r485hISBsKmopDQ8JysYg3O9j85ae_ulpLDt1BnKqUq3XvpNjUWsS_YoL9KTH9Vnhy2_EPJeCj83ADBKyV4bg";
    	if ( angular.isDefined($localStorage.token) && $localStorage.token ) {
	        return $localStorage.token;
	    } else {
			return null;
	    }
    }

    this.getKey = function() {
        return "Authorization";
    }

    this.setToken = function(token) {
    	$localStorage.token = token;
    }

    this.handleHttpFailed = function(response) {
        if(response.status === 403) {
            alert('请登陆后再进行操作');
        } else {
            console.log('Unpredictable Exception' + response.status);
        }
    }
});