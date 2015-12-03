var app = angular.module("serpics.Services", ['ngCookies','serpics.config'])

app.service("authManagerService", function( $http, $q ,$cookies,URL,COOKIE_EXIPES) {
 
	var promiseSession = null;
    var endpoint   	= '/jax-rs/auth/connect/default-store' 
    	
        /** Return public API. (interface public service) **/
      	var service =   ({
        	getSessionId: getSessionId
        });                
        return service
        
        
        
        function getSessionId(){
        	
        	var sessionCookie=getcookie();
    	    if(sessionCookie===null){
    	    	console.log('ssid non presente nel cookie');
    	    	console.log("sessioid "+this.idendpoint+this.promiseSession);
    	    	console.log("sessioid test "+(promiseSession==null));
    	    	
    	    	if(promiseSession===null){
    	    		console.log("sessioid prima della chiamata "+promiseSession);
    	    		promiseSession = getCallSessionId();
    	    		console.log("sessioid dopo chiamata getcallsessionid "+promiseSession);
    	    		return promiseSession;
    	    	}else{
    	    		console.log('ssid gia richiesto al server');
    	    		return promiseSession;
    	    	}
    	    }else{
    	    	console.log('ssid presente nel cookie'+sessionCookie);
    	    	var defer = $q.defer();
    	    	defer.resolve(sessionCookie);
    	    	return defer.promise;
    	    }
        }
        
       
        

	        function getcookie() {
	        	var sessionId = null;
	        	if ($cookies.get('ssid')) {
	        		sessionId = $cookies.get('ssid');
	        		console.log('Serpics Controller: read session id from cookie ->'+ sessionId);
	        		}
	        	return sessionId;
	        	}
        
        
        
        /** public methods* */
        /**
         * @param endpoint               
         * @return session id 
         */ 
        function getCallSessionId() {
        	 var request = $http({
                 method: 'GET',
                 url: URL + endpoint                                     
               });
        	 return( request.then( handleSuccess, handleError ) );
        }                
    
        /** helper method for cookie life cycle expires**/ 
        /**
         * @param nameCookie  	a name of a cookie
         * @param cookieValue 	a value of cookie
         * @param expires		life time of a cookie 
         * @param  
         */
        function setCookie(nameCookie,cookieValue,expires) {
        	
        	var lifeTime = new Date();
    		var now = new Date();
    		lifeTime.setTime(now.getTime() + (parseInt(expires) * 60000));
        	
    		$cookies.put(nameCookie, cookieValue,{
        		  expires: lifeTime.toGMTString() 
        	});
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
        	setCookie('ssid',response.data,COOKIE_EXIPES)  /** expire 30 minut **/    
        	promiseSession = null;
        	return response.data;
            }
        })
        




app.service("orderService", function( $http, $q) {
	 
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getOrders: getOrders,
	  		addPayment:addPayment
	    	
	    });                
	    return service
	    
	    /** public methods**/
	    
	    /**
	     * @param endpoint
	     * @param sessionId      
	     * @return 
	     */
	    function getOrders(endpoint,sessionId) {
	    	
	        var request = $http({
	            method: 'GET',
	            url: endpoint +  'getOrders',
	            headers: {
	            	'ssid': sessionId
	            }
	          });
	    	
	        return( request.then( handleSuccess, handleError ) );
	    }                
	
	    /**
	     * @param endpoint
	     * @param sessionId    
	     * @param order 
	     * @param data   
	     * @return 
	     */
	    function addPayment(endpoint,sessionId,order,data) {
	    	
	        var request = $http({
	            method: 'POST',
	            url: endpoint +  '/addPayment/'+ order,
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
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


/**
 * CustomerService to handler rest call to customerservice
 */
app.service("customerService", function( $http, $q) {
	 
    /** Return public API. (like java interface) **/
  	var service =   ({
  			create:							create,
  			update: 						update,
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
            url: endpoint,
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
    function create(endpoint,sessionId,user) {
    	
        var request = $http({
            method: 'POST',
            url: endpoint +  '/register',
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
     * @param user
     * @return 
     */
    function updateCustomer(endpoint,sessionId, user ) {
        var request = $http({
            method: 'PUT',
            url: endpoint,
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
    function login(endpoint,sessionId, username, passoword) {
    	
        var request = $http({
            method: 'GET',
            url: endpoint +  '/login' + '?username=' + username + '&passoword=' + passoword,
            headers: {
            	'ssid': sessionId
            }
          });
    	
        return( request.then( handleSuccess, handleError ) );
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
            url: endpoint + '/updateContactAddress',
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
            url: endpoint + '/updateBillingAddress',
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
            url: endpoint + '/updateDestinationAddress',
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
            url: endpoint +  '/addDestinationAddress',
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
            url: endpoint +  '/deleteDestinationAddress',
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