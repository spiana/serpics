 var app = angular.module("customer.service", ['serpics.config'])
/**
 * CustomerService to handler rest call to customerservice
 */
app.service("customerService", function( $http, $q, serpicsServices,URL) {
	
	var endpoint = '/jax-rs/customerService/';
	 
    /** Return public API. (like java interface) **/
  	var service =   ({
  			create:							create,
  			updateCustomer: 				updateCustomer,
  			login:							login,
  			getCurrent: 					getCurrent,
  			updateContactAddress: 			updateContactAddress,
  			updateBillingAddress:			updateBillingAddress,
  			updateDestinationAddress: 		updateDestinationAddress,
  			addDestinationAddress:			addDestinationAddress,
  			deleteDestinationAddress:		deleteDestinationAddress   	
    });                
    return service
    
    /** public methods**/
    
    /**
     * @param endpoint
     * @param sessionId      
     * @return 
     */
    function getCurrent(endpoint,sessionId) {
    	
        var request = $http({
            method: 'GET',
            url: URL + endpoint,
            headers: {
            	'ssid': sessionId
            }
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }                

    /**
     * @param endpoint
     * @param sessionId    
     * @param user 
     * @return 
     */
    function create(user) {
    	var serviceSSID = getCallSessionId;
    	return $q(function(resolve, reject) {
    		
    	serviceSSID.getSessionId().then(function(sessionId){
    	
	       $http({
	            method: 'POST',
	            url: URL + endpoint +  'register',
	            headers: {
	            	'ssid': sessionId
	            },   
	            user: user
       }).then(handleSuccess, handleError).then(resolve, reject);
		
			});
		
		});
	}
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param user
     * @return 
     */
    function updateCustomer(endpoint,sessionId, user ) {
        var request = $http({
            method: 'PUT',
            url: URL + endpoint,
            headers: {
            	'ssid': sessionId
            },   
            user: user
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param username
     * @param password  
     * @return 
     */
   
    function login(username, password) {
    	
    	var serviceSSID = getCallSessionId;
    	return $q(function(resolve, reject) {
    		
    	serviceSSID.getSessionId().then(function(sessionId){
    		
	       $http({
	            method: 'GET',
	            url: URL + endpoint +  'login' + '?username=' + username + '&password=' + password,
	            headers: {
	            	'ssid': sessionId
	            }
	        }).then(handleSuccess, handleError).then(resolve, reject);
				
			});
		
		});
	}
	    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateContactAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: URL + endpoint + '/updateContactAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateBillingAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: URL + endpoint + '/updateBillingAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateDestinationAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: URL + endpoint + '/updateDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param user
     * @return 
     */
    function addDestinationAddress(endpoint,sessionId,address) {
    	
        var request = $http({
            method: 'POST',
            url: URL + endpoint +  '/addDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param addressuid
     * @return 
     */
    function deleteDestinationAddress(endpoint,sessionId,addressuid) {
    	
        var request = $http({
            method: 'POST',
            url: URL + endpoint +  '/deleteDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            addressuid: addressuid
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }
    
    
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
});