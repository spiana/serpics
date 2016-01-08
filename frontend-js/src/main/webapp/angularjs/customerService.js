
var app = angular.module('customer.service',['serpics.config','serpics.services'])

.factory('customerService',  [ '$http', '$q','serpicsServices','URL','$timeout', 'TIMEOUT',
    function ($http, $q, serpicsServices,URL,$timeout,TIMEOUT) {
	
		var customerService = {
				currentUser:{}
		};
		
		var endpoint = '/jax-rs/customerService/';
		
                 
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
          * @param AddressData
          * return 
          */
        customerService.updateContactAddress = function(contactAddress){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'PUT',
 	   	            url: URL + endpoint + "updateContactAddress",
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	       		data:contactAddress
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         /**
          * @param AddressData
          * return 
          */
        customerService.updateBillingAddress = function(billingAddress){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'PUT',
 	   	            url: URL + endpoint + "updateBillingAddress",
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	       		data:billingAddress
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         /**
          * @param AddressData
          * return 
          */
        customerService.updateDestinationAddress = function(destinationAddress){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'PUT',
 	   	            url: URL + endpoint + "updateDestinationAddress",
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	       		data:destinationAddress
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         /**
          * @param AddressData
          * return 
          */
        customerService.addDestinationAddress = function(destinationAddress){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'POST',
 	   	            url: URL + endpoint + "addDestinationAddress",
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
 	   	       		data:destinationAddress
 	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
 	   			});   		
 	       	});        	
  
         };
         
         /**
          * @param addressId
          * return 
          */
        customerService.deleteDestinationAddress = function(addressId){  
     	   
     	var serviceSSID = serpicsServices;
 	    return $q(function(resolve, reject) {
 	       		
 	       	serviceSSID.getSessionId().then(function(sessionId){	       		
 	   	       $http({
 	   	            method: 'DELETE',
 	   	            url: URL + endpoint + "deleteDestinationAddress" + "/" + addressId,
 	   	            headers: {
 	   	            	'ssid': sessionId
 	   	            },
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
            return( response.data.responseObject);
        }
  
         customerService.updateCurrentUser = function() {
	         customerService.getCurrentUser().then(function(data) {
	        	 angular.copy(data, customerService.currentUser)
	         })
         };
         
         customerService.timeout = function () {
             $timeout( function(){ customerService.updateCurrentUser(); customerService.timeout() }, TIMEOUT * 60000);
         }
         
         customerService.timeout()
         
         customerService.getCurrentUser().then(function(data) {
        	 angular.copy(data, customerService.currentUser)
         })
         
         return customerService;
    }
])