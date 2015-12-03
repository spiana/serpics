 var app = angular.module("product.service", ['serpics.config'])
 
app.service("productService",function( $http, $q, authManagerService, URL) {
	
	var endpoint = '/jax-rs/productService/';
	 
	     /** Return public API. (loki java interface)**/
	     var service =({
	     		insert	  			: insert,
	     		insertCategory	  	: createParent,
	     		insertBrand 		: addParent,
	     		update  			: updateCategory,
	     		getProduct	  		: deleteCategory,                   
	     		deleteProduct	  	: getCategoryById,
	     		getCategory 		: getCategoryByCode,
	     		addBrand			: getTop,
	     		addCategory		  	: getChild,
	     		addprice			: addPrice,
	     		getProductByName	: getProductByName,
	     		findByCategory		: findByCategory,
	     		findByBrand			: findByBrand,
	     		findAll			  	: findAll
	     });                
	     return service;
	     
	   /** public methods**/
	    
	   /**
	    * @param endpoint
	     * @param sessionId  
	     * @param categoryId  
	     * @param brandId               
	     * @param data
	     * @return 
	     */
	     function insert( endpoint,sessionId,categoryId,brandId,data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   categoryId + '/' + brandId,
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
	     * @param data
	     * @return 
	     */
	     function insertCategory(endpoint, sessionId, categoryId , data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   'category/' + categoryId,
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
	     * @param brandId               
	     * @param data
	     * @return 
	     */
	     function insertBrand( endpoint,sessionId, brandId,data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   'brand/' + brandId ,
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
	     function update( endpoint,sessionId, data ) {
	         var request = $http({
	             method: 'PUT',
	             url: endpoint +   'update',
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
	     * @param productId
	     * @return 
	     */
	     function deleteProduct(endpoint,sessionId, productId) {
	         var request = $http({
	             method: 'DELETE',
	             url: endpoint +   'delete/' + productId,
	             headers: {
	             	'ssid': sessionId
	             }                        
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    
	    /**
		 * @param endpoint
	     * @param sessionId                       
	     * @param productId
	     * @return 
	     */      
	     function getProduct(endpoint,sessionId, productId) {
	     	 var request = $http({
	              method: 'GET',
	              url: endpoint +  productId,
	              headers: {
	              	'ssid': sessionId
	              }                            
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
		 * @param endpoint
	     * @param sessionId                       
	     * @param categoryId
	     * @return 
	     */      
	     function getCategory(endpoint,sessionId, categoryId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint + 'getCategory/'+ categoryId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId 
	     * @param productId                       
	     * @param brandId
	     * @return 
	     */         
	     function addBrand(endpoint,sessionId, productId,brandId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +   'addBrand/' + productId + '/' + brandId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param productId                       
	     * @param categoryId
	     * @return 
	     */           
	     function addCategory(endpoint,sessionId, productId,category) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +  'addCategory/' + productId + '/' + category,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param productId
	     * @param data
	     * @return 
	     */             
	     function addPrice(endpoint,sessionId, productId,data) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +  'addPrice/' + productId,
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
	     * @param productName
	     * @return 
	     */              
	     function getProductByName(endpoint,sessionId, productName) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint + 	  'byCode/' + productName ,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	     /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param categoryId
	     * @return 
	     */              
	     function findByCategory(endpoint,sessionId, categoryId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +   'pageCategory/' + categoryId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	   /**
	     * @param brandId
	     * @return 
	     */         
	     function findByBrand(brandId) {
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
	              url: endpoint + 'findAll',
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
     * from the API response payload.                
     */
    function handleSuccess( response ) {
        return( response.data.responseObject);
    }
});
