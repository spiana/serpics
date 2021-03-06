var app = angular.module('serpics.configs', ['ngDialog'])
			.constant('APP_NAME','Serpics App')
			.constant('APP_VERSION','0.1')
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