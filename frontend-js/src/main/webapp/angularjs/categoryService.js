 var app = angular.module("category.service", ['ngCookies','serpics.config'])
 /**
 * category service to handler rest call to category service
 */
 app.service("categoryService", function( $http, $q,authManagerService,URL) {
	 
	 var endpoint   	= '/jax-rs/categoryService/'
 
	    /** Return public API. (like java interface)**/
	    var service = ({
	        getCategoryById	  	: getCategoryById,
	        getCategoryByCode 	: getCategoryByCode,
	        getTop			  	: getTop,
	        getTopQ			  	: getTopQ,
	        getChild		  	: getChild,
	        findAll			  	: findAll
	    });                
	    return service;
	    
	    /** public methods**/
	   
	    /**
	     * @param endpoint
	     * @param sessionId                
	     * @return 
	     */     
	    function getTop() {
	    	var defer = $q.defer();
	    	var response = authManagerService.getSessionId();
	    	response.then(function(idSessione){
	    		
	    		var request = $http({
		             method: 'GET',
		             url: 	URL + endpoint +  'top',
		             headers: {
		             	'ssid': idSessione
		            }
		          }).then(function(response){
		        	  defer.resolve(response.data);
		          });
	    	});
	    	
	    	return defer.promise;
	    }
	    
	    
	    /**
	     * @param endpoint
	     * @param sessionId                
	     * @return 
	     */     
	    function getTopQ() {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint +  'top',
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    			
	    		});
    		
	    	});
	    }
	    		    
	    /**
	     * @param categoryId                
	     * @return 
	     */      
	    function getCategoryById(categoryId) {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
	    				method: 	'GET',
	    				url: URL + endpoint + categoryId,
	    				headers: {
	    					'ssid': sessionId
	    					}
	    			}).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param code
	     * @param category                
	     * @return 
	     */      
	    function getCategoryByCode(code,category) {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
	    				method: 	'GET',
	    				url: URL + endpoint +  code + '/' + category,
	    				headers: {
	    					'ssid': sessionId
	    					}
	    			}).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param parentId                 
	     * @return 
	     */      
	    function getChild(parentId) {
	    	console.log("getChild(parentId): "+parentId) ;
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
	    				method: 	'GET',
	    				url: URL + endpoint +   'getChild/' + parentId,
	    				headers: {
	    					'ssid': sessionId
	    					}
	    			}).then(handleSuccess, handleError).then(resolve, reject);
    			 });
    		 });
    	 }
	    
	    /**
	     * @param sessionId               
	     * @return 
	     */     
	    function findAll(sessionId) {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
	    				method: 	'GET',
	    				url: URL +  endpoint +  'findAll',
	    				headers: {
	    					'ssid': sessionId
	    					}
	    			}).then(handleSuccess, handleError).then(resolve, reject);
	    		});
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
	        return( response.data.responseObject);
	    }
	}
);
