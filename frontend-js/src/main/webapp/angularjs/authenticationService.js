
var app = angular.module('authentication.service',['serpics.config'])
  
.factory('authenticationService',  [ '$http',  '$rootScope', '$q','serpicsServices','URL','ENDPOINT',
    function ($http, $rootScope, $q, serpicsServices,URL,ENDPOINT) {
	
	
			
		/** public interface **/
        var service = {        	
        		login:				login,
        		logout:				logout,
        		register:			register,
        		getCurrentUser:	    getCurrentUser        		       		
        };
        
                 
        /** public methods**/
        
        /**
         * @param username
         * @param passwordf
         * return 
         */
       function login(username, password) {  
    	   
    	var serviceSSID = serpicsServices;
	       	return $q(function(resolve, reject) {
	       		
	       	serviceSSID.getSessionId().then(function(sessionId){	       		
	   	       $http({
	   	            method: 'GET',
	   	            url: URL + ENDPOINT +  'customerService/login' + '?username=' + username + '&password=' + password,
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
        function register(userData) {  
     	   
        	var serviceSSID = serpicsServices;
    	       	return $q(function(resolve, reject) {    	       		
    	       	serviceSSID.getSessionId().then(function(sessionId){	       		
    	   	       $http({
    	   	            method: 'POST',
    	   	            url: URL + ENDPOINT +  'customerService/register',
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
        function getCurrentUser() {  
     	   
     	var serviceSSID = serpicsServices;
 	       	return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'GET',
 	   	            url: URL + ENDPOINT +  'customerService/getCurrent' ,
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
        function logout() {  
     	   
     	var serviceSSID = serpicsServices;
 	       	return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'POST',
 	   	            url: URL + ENDPOINT +  'customerService/logout' ,
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	            data:sessionId
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
                 
                
        
        /** return the service **/
        return service;
        
        
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
  
    }]) 
 