var app = angular.module('serpics.config', ['ngDialog'])
			.constant('APP_NAME','Serpics App')
			.constant('APP_VERSION','0.1')
			.constant('URL','http://localhost:8080/jax-rs')
			.constant('COOKIE_EXPIRES','20') //[minuti]
			.constant('STORE','default-store') //Store constant for Auth Connect
			.constant('TIMEOUT','15') //[minuti]
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