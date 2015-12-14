var app= angular.module('serpics.interceptor', [])

  .factory("serpicsHttpBuffer", ['$injector', function($injector) {
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
          retryHttpRequest(updater(buffer[i].config), buffer[i].deferred);
        }
        buffer = [];
      }
    };
  }])
  

.factory('serpicsInterceptor',['serpicsHttpBuffer','$location', '$injector', '$q','$rootScope','$log','$cookies', function(serpicsHttpBuffer,$location, $injector, $q,$rootScope,$log,$cookies){

	//var $categoryService; // Add a constant that store the service

                             return {
                            	 

                               'request' : function(response){
                                 $log.debug("intercept request", response.url,response)
                                 // Your token shall be retreive in this part
                                 $log.debug("intercept request: uguale ssid", response.headers.ssid,$cookies.get('ssid'))
                                 if (response.headers.ssid===$cookies.get('ssid')){
                                	 $log.debug("intercept request: uguale ssid", response.headers.ssid,response,$cookies.get('ssid'));
                                 }else{
                                	 response.headers.ssid=$cookies.get('ssid');
                                 }
                                 return response
                               },
                               'response' : function(response){
                                 //$myService= $myService|| $injector.get('$myService'); // inject the service manually if constant is undefined
                                 $log.debug("intercept response:", response.status);
                                 // Your token shall be retreive in this part

                                 return response
                               },
                                 'responseError': function(rejection) {
                                	 $log.debug("interceptor rocks!!");
                                     $log.debug("responseError intercepted:" , rejection);
                                     
                                     if (rejection.status === 500){
                                    	 $log.debug("responseError intercepted: 500: " , rejection);
                                    	 var stato=$injector.get('$state');
                                    	 stato.transitionTo('shop.500');
                                    	 return $q.reject(rejection);
                                    	 
                                     }
                                     
                                       if (rejection.status === 503){
                                    	   $log.debug("responseError intercepted: 503: " , rejection);
                                       	   var deferred = $q.defer();
                                       	serpicsHttpBuffer.append(rejection.config, deferred);
                                           $rootScope.$broadcast('event:sessiondId-expired', rejection);                                           
                                           return deferred.promise;
                                    	   return $q.reject(rejection);
                                       	
                                       }
                                      if (rejection.status === 401) {
                                    	  $log.debug("responseError intercepted: 401: " , rejection);
                                    	  
                                    	  $rootScope.error.message = rejection.data.message;
//                                    	  $rootScope.resetformOnIntercept401Error();
                                    	  $rootScope.userData.login.username = '';
                                          $rootScope.userData.login.password  ='';
                                    	  $log.debug(rejection.data.message);               
                                    	  $log.debug("Response Error 401",rejection);                

                                    	  
                                    	  var stato=$injector.get('$state');
                                    		  stato.transitionTo('shop.login');
//                                            $location.path('/login').search('returnTo', $location.path());

                                    	  return $q.reject(rejection);

                                     } else if (rejection.status === 402) {

                                         return $q.reject(rejection);
                                     }else  if  (rejection.status === 404){
                                    	 $log.debug("responseError intercepted: 404: " , rejection);
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