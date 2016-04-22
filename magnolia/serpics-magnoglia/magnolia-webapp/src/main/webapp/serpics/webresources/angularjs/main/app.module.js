var app = angular.module("serpics", [
                                     'serpics.core',
                                     'serpics.logger',
                                     'serpics.controllers',
                                     'serpics.interceptor',
                                     'customer.service',
                                     'serpics.services',
                                     ]);

app.run(['sessionService','serpicsHttpBuffer','$log','$rootScope','$timeout','TIMEOUT','customerService',
         function(sessionService,httpBuffer,$log,$rootScope,$timeout,TIMEOUT,customerService) {	
	        
	        timeoutUser();
		 	
	        var counter=0;
		 	
		 	$scope= $rootScope.$new();
	        $scope.$on('event:sessiondId-expired', function() {
	        	if(counter!=0){
	        		$log.debug('Evento scatenato: sessiondId-expired'+counter);
	        	}else{
	        		$log.debug('Evento scatenato: sessiondId-expired ramo else'+counter);
	        		counter+=1;
	        		sessionService. removeCookie('ssid');
	        		sessionService.getSessionId().then(function(data, configUpdater) {
	        	        var updater = configUpdater || function(config) {return config;};
//	        	        $rootScope.$broadcast('event:auth-loginConfirmed', data);
	        	        httpBuffer.retryAll(updater);
	        	      });
	        	}
	        });
	        
	        function timeoutUser() {
	        	 $log.debug('Timeout function');
	             $timeout( function(){ customerService.updateCurrentUser(); timeoutUser() }, TIMEOUT * 60000);
	         }
	         
	    }

]);
