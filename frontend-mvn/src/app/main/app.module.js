(function() {
    'use strict';
    
    angular.module("serpics", ['category.controller','brand.controller','product.controller','order.controller','login.controller','cart.controller','customer.controller','serpics.directive','serpics.router','serpics.interceptor','customer.service','ngSanitize'])
    .run(runBlock);
    		
    runBlock.$inject = ['serpicsServices','serpicsHttpBuffer','$log','$rootScope','$timeout','TIMEOUT','customerService'];
    /** @ngInject */
    function runBlock(serpicsServices,httpBuffer,$log,$rootScope,$timeout,TIMEOUT,customerService) {	
	        
	        timeoutUser();
		 	
	        var counter=0;
	        var vm = this;
		 	
		 	vm= $rootScope.$new();
	        vm.$on('event:sessiondId-expired', function() {
	        	if(counter!=0){
	        		$log.debug('Evento scatenato: sessiondId-expired'+counter);
	        	}else{
	        		$log.debug('Evento scatenato: sessiondId-expired ramo else'+counter);
	        		counter+=1;
	        		serpicsServices. removeCookie('ssid');
	        		serpicsServices.getSessionId().then(function(data, configUpdater) {
	        	        var updater = configUpdater || function(config) {return config;};
// $rootScope.$broadcast('event:auth-loginConfirmed', data);
	        	        httpBuffer.retryAll(updater);
	        	      });
	        	}
	        });
	        
	        function timeoutUser() {
	        	 $log.debug('Timeout function');
	             $timeout( function(){ customerService.updateCurrentUser(); timeoutUser() }, TIMEOUT * 60000);
	         }
	         
	    }

})();