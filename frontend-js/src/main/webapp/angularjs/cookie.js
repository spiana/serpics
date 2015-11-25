var app = angular.module("Cookie", [ 'ngCookies', 'AuthManager' ])

app.service("cookieService", [ '$cookies', 'AuthManager',
		function($cookies, $q, authManager) {

		}

]);