(function() {
	 'use strict';
	 angular.module('service.config', [])
			.constant('URL','http://serpicsframework.com/jax-rs')
			.constant('COOKIE_EXPIRES','20') //[minuti]
			.constant('STORE','default-store') //Store constant for Auth Connect
			.constant('SERVICE_DEBUG', false) //Enable debug console.log into services
			.config(config);
	 
	 config.$inject = ['$logProvider','SERVICE_DEBUG'];
	 
	 /** @ngInject */
	 function config ($logProvider, SERVICE_DEBUG){
		 //disabilito i log per i services
		 $logProvider.debugEnabled(SERVICE_DEBUG);
		 
		  }

 })();