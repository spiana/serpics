 var app = angular.module("category.Service", ['ngCookies','serpics.config'])
 /**
 * category service to handler rest call to category service
 */
 app.service("categoryService", function( $http, $q,authManagerService,URL) {
	 
	 var endpoint   	= '/jax-rs/categoryService/'
 
	    /** Return public API. (like java interface)**/
	    var service = ({
	    	create	  			: create,
	    	createParent	  	: createParent,
	    	addParent 		  	: addParent,
	    	updateCategory	  	: updateCategory,
	    	deleteCategory	  	: deleteCategory,                   
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
	     * @param data
	     */
	    function create(data ) {
	    	var defer = $q.defer;
	    	authManagerService.getSessionId().then(function(idSessione){
	    		
	    		var request = $http({
		            method: 'POST',
		            url: URL + endpoint + 'create',
		            headers: {
		            	'ssid': sessionId
		            },   
		            data: data
		          }).then(function(response){
		        	  defer.resolve(reponse.data);
		          });
	    	});
	    	
	    	return defer.promise;
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param parentId
	     * @param data
	     */
	    function createParent(endpoint,sessionId, parentId , data ) {
	        var request = $http({
	            method: 'POST',
	            url: URL + endpoint +  parentId,
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param childId
	     * @param parentId
	     * @param data
	     * @return 
	     */
	    function addParent(endpoint,sessionId, childId,parentId,data ) {
	        var request = $http({
	            method: 'POST',
	            url: URL + endpoint + 'addParent/' + childId +'/' + parentId,
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId                
	     * @param data
	     * @return 
	     */
	    function updateCategory(endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'PUT',
	            url: URL + endpoint + 'update',
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param categoryId            
	     * @return 
	     */
	    function deleteCategory(endpoint,sessionId,categoryId) {
	        var request = $http({
	            method: 'DELETE',
	            url: URL + endpoint + 'delete/' + categoryId,
	            headers: {
	            	'ssid': sessionId
	            }                        
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	   
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
//	    	var defer = $q.defer;
//	    	authManagerService.getSessionId().then(function(idSessione){
//	    		
//	    		var request = $http({
//		             method: 'GET',
//		             url: 	endpoint +  'top',
//		             headers: {
//		             	'ssid': sessionId
//		            }
//		          }).then(function(response){
//		        	  defer.resolve(reponse.data);
//		          });
//	    	});
//	    	
//	    	return defer.promise;
//	    }
	    		    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param categoryId                
	     * @return 
	     */      
	    function getCategoryById(endpoint,sessionId,categoryId) {
	    	 var request = $http({
	             method: 	'GET',
	             url: URL + endpoint + categoryId,
	             headers: {
	             	'ssid': sessionId
	             }                         
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param code
	     * @param category                
	     * @return 
	     */      
	    function getCategoryByCode(sessionId,code,category) {
	    	 var request = $http({
	             method: 	'GET',
	             url: URL + endpoint +  code + '/' + category,
	             headers: {
	             	'ssid': sessionId
	             }                         
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param parentId                 
	     * @return 
	     */      
	    function getChild(parentId) {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("session Id nel promise"+sessionId) ;
	    			$http({
	    				method: 	'GET',
	    				url: URL + endpoint +   'pageBrand/' + brandId,
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
	     * @return 
	     */     
	    function findAll(endpoint,sessionId) {
	    	 var request = $http({
	             method: 	'GET',
	             url: URL +  endpoint +  'findAll',
	             headers: {
	             	'ssid': sessionId
	             }                         
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
	}
);
