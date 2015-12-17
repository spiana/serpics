var app = angular.module('serpics.config', ['ngDialog'])
			.constant('APP_NAME','Serpics App')
			.constant('APP_VERSION','0.1')
			.constant('URL','http://localhost:8080')
			.constant('ENDPOINT','/jax-rs/')
			.constant('COOKIE_EXPIRES','20') //[minuti]
			.constant('BREADCRUMBS','Home') //[serpicsApp constant]
			.constant('LOADINGTEXT','Serpics') //[rotating loadingtext label]
			.constant('TITLE','Serpics Platform Ecommerce') //[rotating loadingtext label]
			.constant('WELCOME','Welcome') //welcome for user logged in
			.constant('ANONYMOUS','Guest Access') //Welcome message for anonymous account
			.constant('STORE','default-store') //Store constant for Auth Connect
			.config(function($logProvider){
				$logProvider.debugEnabled(true);
				})
				
var modal = angular.module('serpics.Modal', ['ngDialog']);			
		modal.config(['ngDialogProvider', function (ngDialogProvider) {
		    ngDialogProvider.setDefaults({
		        className: 'ngdialog-theme-default',
		        plain: true,
		        showClose: true,
		        closeByDocument: true,
		        closeByEscape: true
		  });
}]);