var app= angular.module('serpics.interceptor', [])

.factory("serpicsHttpBuffer", ['$injector','$cookies', function($injector,$cookies) {
	
	/** Holds all the requests, so they can be re-requested in future. */
	var buffer = [];

    /** Service initialized later because of circular dependency problem. */
    var $http;

    function retryHttpRequest(config, deferred) {
    	
    	function successCallback(response) {
    		deferred.resolve(response);
    	}
    	function errorCallback(response) {
    		deferred.reject(response);
    	}
    	$http = $http || $injector.get('$http');
    	$http(config).then(successCallback, errorCallback);
    	
    }
    
    return {
    	/**
    	 * Appends HTTP request configuration object with deferred response attached to buffer.       
    	 */
    	append: function(config, deferred) {
    		buffer.push({
    			config: config,
    			deferred: deferred
    		});
    	},
    	
    	/**
    	 * Abandon or reject (if reason provided) all the buffered requests.
    	 */
    	rejectAll: function(reason) {
    		if (reason) {
    			for (var i in buffer) {
    				buffer[i].deferred.reject(reason);
    			}
    		}
    		buffer = [];
    	},
    	
    	/**
    	 * Retries all the buffered requests clears the buffer.
    	 */
    	retryAll: function(updater) {
    		for (var i in buffer) {
    			buffer[i].config.headers.ssid=$cookies.get('ssid');
    			retryHttpRequest(updater(buffer[i].config), buffer[i].deferred);
    		}
    		buffer = [];
    	}
    };
  }])

.factory('serpicsInterceptor',['serpicsHttpBuffer','$location', '$injector', '$q','$rootScope','$log','$cookies', function(serpicsHttpBuffer,$location, $injector, $q,$rootScope,$log,$cookies){

	return {
		
		
//		'request' : function(response){
//			$log.debug("intercept request 200: ", response.url,response)
//			// Your token shall be retreive in this part
//			$log.debug("intercept request: uguale ssid", response.headers.ssid,$cookies.get('ssid'))
//			if (response.headers.ssid===null ||  response.headers.ssid===$cookies.get('ssid')){
//				
//				$log.debug("intercept request: 200 ssid detected ", response.headers.ssid,response,$cookies.get('ssid'));
//			}else{
//					
//				response.headers.ssid=$cookies.get('ssid');
//					
//			}
//			return response
//		},
			
		'response' : function(response){
			//$myService= $myService|| $injector.get('$myService'); // inject the service manually if constant is undefined
			$log.debug("Intercept Response: %s - Response Url: %s",response.status,response.url);
			// Your token shall be retreive in this part
			return response
			
		},
		'responseError': function(rejection) {
			$log.debug("Interceptor Rocks!!");
			$log.debug("ResponseError Intercepted: Response Status: %s - Response Url: %s",rejection.status,rejection.config.url);
			if (rejection.status === 500){
				
				$log.debug("ResponseError Intercepted: 500: %s" , rejection);
				
				var stato=$injector.get('$state');
				stato.transitionTo('shop.500');
				
				return $q.reject(rejection);
				
			}
			if (rejection.status === 403){
				$log.debug("ResponseError intercepted: 403: %s" , rejection);
				
				var deferred = $q.defer();
				
				serpicsHttpBuffer.append(rejection.config, deferred);
				//Scateno l'evento per rinnovare il sessionId
				$rootScope.$broadcast('event:sessiondId-expired', rejection);
				
				return deferred.promise;
				
			}
			if (rejection.status === 401) {
				
				$log.debug("ResponseError Intercepted: 401: %s" , rejection);
				$rootScope.error.message = rejection.data.message;
				$log.debug("Messaggio d'errore 403: %s",rejection.data.message);
				
				$rootScope.userData.login.username = '';
				$rootScope.userData.login.password  ='';
				
				var stato=$injector.get('$state');
				stato.transitionTo('shop.login');
				
				return $q.reject(rejection);
				
			} else if (rejection.status === 402) {
					
				return $q.reject(rejection);
					
			}else  if  (rejection.status === 404){
						
				$log.debug("ResponseError Intercepted: 404: %s" , rejection);
						
				var stato=$injector.get('$state');
						
				stato.transitionTo('shop.404');
						
				return $q.reject(rejection);
			}
			return $q.reject(rejection);
		}
	};
			

}])
.config(function($logProvider){
	$logProvider.debugEnabled(true);
	})
.config(['$httpProvider',function($httpProvider) {   
	    $httpProvider.interceptors.push('serpicsInterceptor');
	}]);