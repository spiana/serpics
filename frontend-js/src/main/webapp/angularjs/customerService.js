
var app = angular.module('customer.service',['serpics.config','serpics.services'])

.factory('customerService',  [ '$http', '$q','serpicsServices','URL',
    function ($http, $q, serpicsServices,URL) {
	
		var customerService = {}
		
		var endpoint = '/jax-rs/customerService/';
		
		customerService.currentUser = {}
                 
        /** public methods**/
        
        /**
         * @param username
         * @param passwordf
         * return 
         */
		customerService.login = function(username, password) {  
    	   
    	var serviceSSID = serpicsServices;
	       	return $q(function(resolve, reject) {
	       		
	       	serviceSSID.getSessionId().then(function(sessionId){	       		
	   	       $http({
	   	            method: 'GET',
	   	            url: URL + endpoint + 'login' + '?username=' + username + '&password=' + password,
	   	            headers: {
	   	            	'ssid': sessionId
	   	            }
	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
	   			});   		
	       	});        	
 
        };
  
        /**
         * @param userData 	data send to server
         * @returns 		new user
         */
        customerService.register = function(userData) {  
     	   
        	var serviceSSID = serpicsServices;
    	       	return $q(function(resolve, reject) {    	       		
    	       	serviceSSID.getSessionId().then(function(sessionId){	       		
    	   	       $http({
    	   	            method: 'POST',
    	   	            url: URL + endpoint + 'register',
    	   	            headers: {
    	   	            	'ssid': sessionId
    	   	            },
    	   	            data:userData
    	   	        }).then(handleSuccess, handleError).then(resolve, reject);	   				
    	   		});   		
       		});        	
         };
            
         
         /**
          * @param username
          * @param passwordf
          * return 
          */
        customerService.getCurrentUser = function(){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'GET',
 	   	            url: URL + endpoint + 'getCurrent' ,
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            }
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         
         /**
          * @param username
          * @param passwordf
          * return 
          */
        customerService.logout = function() {  
     	   
     	var serviceSSID = serpicsServices;
 	       	return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'POST',
 	   	            url: URL + endpoint + 'logout' ,
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	            data:sessionId
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         /**
          * @param userData
          * return 
          */
        customerService.updateUserData = function(userData){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'PUT',
 	   	            url: URL + endpoint,
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	       		data:userData
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
        
        /**
         * private method.
         * I transform the error response, unwrapping the application dta from
         * the API response payload.
         */                                
        
         function handleError( response ) {
            /**
             * The API response from the server should be returned in a
             * nomralized format. However, if the request was not handled by the
             * server (or what not handles properly - ex. server error), then we
             * may have to normalize it on our end, as best we can.
             */ 
            if (! angular.isObject( response.data ) || ! response.data.message ) {
                return( $q.reject( "An unknown error occurred." ) );
            }
            /** Otherwise, use expected error message.**/
            return( $q.reject( response.data.message ) );
        }
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
         
         function handleSuccess( response ) {
        	console.log(response.data.message)
            return( response.data.responseObject);
        }
  
         customerService.updateCurrentUser = function() {
	         customerService.getCurrentUser().then(function(data) {
	        	 angular.copy(data, customerService.currentUser)
	         })
         }
         
         customerService.getCurrentUser().then(function(data) {
        	 angular.copy(data, customerService.currentUser)
         })
         
         return customerService;
    }
])