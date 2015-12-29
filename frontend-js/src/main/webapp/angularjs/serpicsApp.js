var app = angular.module("serpics.App", ['serpics.services','category.controller','brand.controller','product.controller','order.controller','login.controller','cart.controller','customer.controller','serpics.directive','serpics.config','serpics.router','serpics.interceptor']);

app.controller("serpicsAppController",['$scope','serpicsServices','serpicsHttpBuffer',
                                     
     function($scope,serpicsServices,httpBuffer) {	
  	
			
		 	var counter=0;
			
	        $scope.$on('event:sessiondId-expired', function() {
	        	if(counter!=0){
	        		console.log("Evento scatenato: sessiondId-expired"+counter);
	        	}else{
	        		console.log("Evento scatenato: sessiondId-expired ramo else"+counter);
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
