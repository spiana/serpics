(function() {
	'use strict';
	angular.module('serpics',[ 
	                          'serpics.core',
	                          'serpics.run',
	                          'serpics.services', //Services
	                          'serpics.logger',
	                          'serpics.controllers', //Controllers
	                          'serpics.directive',
	                          'serpics.router',
	                          'serpics.interceptor'
	                          ]);

})();