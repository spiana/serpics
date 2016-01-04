var app = angular.module("serpics.App", ['category.controller','brand.controller','product.controller','order.controller','login.controller','cart.controller','customer.controller','serpics.directive','serpics.config','serpics.router','serpics.interceptor']);

app.controller("serpicsAppController",['$scope','serpicsServices','serpicsHttpBuffer','$log',
                                     
     function($scope,serpicsServices,httpBuffer,$log) {	
  	
			
		 	var counter=0;
			
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
