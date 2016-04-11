(function() {
	 'use strict';
	 angular.module('serpics.config', [])
			.constant('URL','http://localhost:8080/jax-rs')
			.constant('COOKIE_EXPIRES','20') //[minuti]
			.constant('STORE','default-store') //Store constant for Auth Connect
			.config(config);
	 
	 config.$inject = ['$httpProvider'];
	 
	 /** @ngInject */
	 function config ($httpProvider){
		 
 
		 $httpProvider.interceptors.push('serpicsInterceptor');
	 }

 })();