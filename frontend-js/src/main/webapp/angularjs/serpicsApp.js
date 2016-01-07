var app = angular.module("serpics.App", ['category.controller','brand.controller','product.controller','order.controller','login.controller','cart.controller','customer.controller','serpics.directive','serpics.config','serpics.router','serpics.interceptor']);

app.run(['serpicsServices','serpicsHttpBuffer','$log','$rootScope',
                                     
     function(serpicsServices,httpBuffer,$log,$rootScope) {	
  	
		 	var counter=0;
		 	
		 	$scope= $rootScope.$new();
			
	        $scope.$on('event:sessiondId-expired', function() {
	        	if(counter!=0){
	        		$log.debug("Evento scatenato: sessiondId-expired"+counter);
	        	}else{
	        		$log.debug("Evento scatenato: sessiondId-expired ramo else"+counter);
	        		counter+=1;
	        		serpicsServices. removeCookie('ssid');
	        		serpicsServices.getSessionId().then(function(data, configUpdater) {
	        	        var updater = configUpdater || function(config) {return config;};
//	        	        $rootScope.$broadcast('event:auth-loginConfirmed', data);
	        	        httpBuffer.retryAll(updater);
	        	      });
	        	}
	        });
}]);
