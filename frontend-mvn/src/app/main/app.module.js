(function() {
	'use strict';
	angular.module('serpics',[ 'ngDialog',
	                           'app.core',
	                           'serpics.run',
	                           'serpics.module', //Services
	                           'serpics.logger',
	                           'controllers.module', //Controllers
	                           'serpics.directive',
	                           'serpics.router',
	                           'serpics.interceptor'
	                           ]);

})();