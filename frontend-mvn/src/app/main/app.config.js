(function() {
	 'use strict';
	 angular.module('app.config', [])
			.constant('APP_NAME','Serpics App')
			.constant('APP_VERSION','2.0')
			.constant('TIMEOUT','15') //[minuti]
			.constant('DEBUG', false)// attivo o disattivo il logger.service per i controllers -> angular.$log
			.config(config);
	 
	 config.$inject = ['$httpProvider'];
	 
	 /** @ngInject */
	 function config ($httpProvider){
		 
		 $httpProvider.interceptors.push('serpicsInterceptor');
		 
		  }
	 
	  })();
	 
//(function() {


//"ng-dialog": "0.2.9"
//	 'use strict';
//	 angular.module('serpics.Modal', [])
//	 .config(config);
//	 
//	 config.$inject = ['$httpProvider','ngDialogProvider'];
//	 /** @ngInject */
//	 function config(ngDialogProvider) {
//		    ngDialogProvider.setDefaults({
//		        className: 'ngdialog-theme-defaulted',
//		        plain: true,
//		        showClose: true,
//		        closeByDocument: true,
//		        closeByEscape: true
//		  });
//}
//
//
//		 
//})();
