 (function() {
	 'use strict';
	 
	 angular.module('serpics.interceptor', [])
	 .factory('serpicsHttpBuffer', serpicsHttpBuffer)

	 .factory('serpicsInterceptor',serpicsInterceptor);
	 
	 serpicsHttpBuffer.$inject = ['$injector','$cookies'];
	 serpicsInterceptor.$inject = ['serpicsHttpBuffer','$location', '$injector', 
	                               '$q','$rootScope','logger','$stateParams' ];
	 
	 /** @ngInject */
	 function serpicsInterceptor(serpicsHttpBuffer,$location, $injector, $q,$rootScope,logger,$stateParams){
		 
		 
		 
		 return {
		
//		'request' : function(response){
//			logger.debug('intercept request 200: ', response.url,response)
//			// Your token shall be retreive in this part
//			logger.debug('intercept request: uguale ssid', response.headers.ssid,$cookies.get('ssid'))
//			if (response.headers.ssid===null ||  response.headers.ssid===$cookies.get('ssid')){
//				
//				logger.debug('intercept request: 200 ssid detected ', response.headers.ssid,response,$cookies.get('ssid'));
//			}else{
//					
//				response.headers.ssid=$cookies.get('ssid');
//					
//			}
//			return response
//		},
			
		'response' : function(response){
			//$myService= $myService|| $injector.get('$myService'); // inject the service manually if constant is undefined
			logger.debug('Intercept Response: '+response.status+' - Response Url: '+response.url);
			// Your token shall be retreive in this part
			return response;
			
		},
		'responseError': function(rejection) {
			logger.debug('Interceptor Rocks!!');
			logger.debug('ResponseError Intercepted: Response Status: '+rejection.status+
					' - Response Url: '+rejection.config.url);
			if (rejection.status === 500){
				
				logger.debug('ResponseError Intercepted: 500: '+ rejection.data.message);
				
				var stato500=$injector.get('$state');
				stato500.transitionTo('shop.500',{error: rejection.data.message, errorStatus: '500'});
				
				return $q.reject(rejection);
				
			}
			if (rejection.status === 403){
				logger.debug('ResponseError intercepted: 403: '+ rejection);
				
				var deferred = $q.defer();
				
				serpicsHttpBuffer.append(rejection.config, deferred);
				//Scateno l'evento per rinnovare il sessionId
				$rootScope.$broadcast('event:sessiondId-expired', rejection);
				
				return deferred.promise;
				
			}
			if (rejection.status === 401) {
				
				logger.debug('ResponseError Intercepted: 401: '+ rejection);
				logger.debug('Messaggio d\'errore 401: %s',rejection.data.message);
				
				var stato401=$injector.get('$state');
				//stato.transitionTo('shop.login');
				logger.debug('login: intercepted '+angular.toJson($stateParams));
				$rootScope.$broadcast('event:sessiondId-expired', rejection);
				stato401.go('shop.login',{error: rejection.data.message});
				
				return $q.reject(rejection);
				
			} else if (rejection.status === 402) {
					
				return $q.reject(rejection);
					
			}else  if  (rejection.status === 404){
						
				logger.debug('ResponseError Intercepted: 404: '+ rejection);
						
				var stato404=$injector.get('$state');
				
				stato404.go('shop.404');
						
				return $q.reject(rejection);
			} else if (rejection.status === 405){
				
				logger.debug('ResponseError Intercepted: 405: '+ rejection);
				
				var stato405=$injector.get('$state');
				
				stato405.go('shop.405');
						
				return $q.reject(rejection);
				
			} else if (rejection.status === 406){
				
				logger.debug('ResponseError Intercepted: 406: '+ rejection.data.message);
				
				var stato406=$injector.get('$state');
				
				stato406.go('shop.406',{error: rejection.data.message, errorStatus: '406'});
						
				return $q.reject(rejection);
				
			}
			return $q.reject(rejection);
		}
	};
	
	 }
			

	/** @ngInject */
	function serpicsHttpBuffer($injector,$cookies) {
	
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
    			for(var i = 0; i<buffer.length; i++){
    			//for (var i in buffer) {
    				buffer[i].deferred.reject(reason);
    			}
    		}
    		buffer = [];
    	},
    	
    	/**
    	 * Retries all the buffered requests clears the buffer.
    	 */
    	retryAll: function(updater) {
    		for(var i = 0; i<buffer.length; i++){
    		//for (var i in buffer) {
    			buffer[i].config.headers.ssid=$cookies.get('ssid');
    			retryHttpRequest(updater(buffer[i].config), buffer[i].deferred);
    		}
    		buffer = [];
    	}
    };
  }
	 
 })();