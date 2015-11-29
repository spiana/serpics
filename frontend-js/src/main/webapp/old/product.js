var app = angular.module("product", ['AuthManager'])

.constant('api_endpoint', 			'http://localhost:8080/jax-rs/productService/')

 app.service("productService",function( $http, $q ,api_endpoint) {
	        		 
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
              * @param sessionId  
              * @param categoryId  
              * @param brandId               
              * @param data
              * @return 
              */
      	     function insert( sessionId,categoryId,brandId,data ) {
      	         var request = $http({
      	             method: 'POST',
      	             url: api_endpoint + categoryId + '/' + brandId,
      	             headers: {
      	             	'ssid': sessionId
      	             },   
      	             data: data
      	           });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId  
              * @param categoryId                         
              * @param data
              * @return 
              */
      	     function insertCategory( sessionId, categoryId , data ) {
      	         var request = $http({
      	             method: 'POST',
      	             url: api_endpoint + 'category/' + categoryId,
      	             headers: {
      	             	'ssid': sessionId
      	             },   
      	             data: data
      	           });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                
              * @param brandId               
              * @param data
              * @return 
              */
      	     function insertBrand( sessionId, brandId,data ) {
      	         var request = $http({
      	             method: 'POST',
      	             url: api_endpoint + 'brand/' + brandId ,
      	             headers: {
      	             	'ssid': sessionId
      	             },   
      	             data: data
      	           });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                        
              * @param data
              * @return 
              */
      	     function update( sessionId, data ) {
      	         var request = $http({
      	             method: 'PUT',
      	             url: api_endpoint + 'update',
      	             headers: {
      	             	'ssid': sessionId
      	             },   
      	             data: data
      	           });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                      
              * @param productId
              * @return 
              */
      	     function deleteProduct(sessionId, productId) {
      	         var request = $http({
      	             method: 'DELETE',
      	             url: api_endpoint + 'delete/' + productId,
      	             headers: {
      	             	'ssid': sessionId
      	             }                        
      	           });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	    
      	     /**
              * @param sessionId                       
              * @param productId
              * @return 
              */      
      	     function getProduct(sessionId, productId) {
      	     	 var request = $http({
      	              method: 'GET',
      	              url	: 	api_endpoint + productId,
      	              headers: {
      	              	'ssid': sessionId
      	              }                            
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                       
              * @param categoryId
              * @return 
              */      
      	     function getCategory(sessionId, categoryId) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint  + 'getCategory/'+ categoryId,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId 
              * @param productId                       
              * @param brandId
              * @return 
              */         
      	     function addBrand(sessionId, productId,brandId) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'addBrand/' + productId + '/' + brandId,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId
              * @param productId                       
              * @param categoryId
              * @return 
              */           
      	     function addCategory(sessionId, productId,category) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'addCategory/' + productId + '/' + category,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                       
              * @param productId
              * @param data
              * @return 
              */             
      	     function addPrice(sessionId, productId,data) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'addPrice/' + productId,
      	              headers: {
      	              	'ssid': sessionId
      	              }, 
      	     	 	data: data
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                       
              * @param productName
              * @return 
              */              
      	     function getProductByName(sessionId, productName) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'byCode/' + productName ,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                       
              * @param categoryId
              * @return 
              */              
      	     function findByCategory(sessionId, categoryId) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'pageCategory/' + categoryId,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId                       
              * @param brandId
              * @return 
              */         
      	     function findByBrand(sessionId, brandId) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint +  'pageBrand/' + brandId,
      	              headers: {
      	              	'ssid': sessionId
      	              }                         
      	            });
      	         return( request.then( handleSuccess, handleError ) );
      	     }
      	     
      	     /**
              * @param sessionId      
              * @return 
              */         
      	     function findAll(sessionId) {
      	     	 var request = $http({
      	              method: 	'GET',
      	              url: 		api_endpoint + 'findAll',
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
 		}
);

app.controller("productController",['$scope','$cookies','authManagerService','productService', 
                                  
	      function($scope,$cookies,authManagerService,productService) {	
	   	
	  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
	  	    	
	  	    $scope.data = {
	  	    		sessionIn	: '',
	  	    		pruduct		: []
	  	    }	
	  	   	  
	  	 
	  	    /** implemented order service **/ 
	  	    
	  	    /**
	  	     * @param sessionId 		a sessionId
	  	     * @param categoryId 		id of category to add
	  	     * @param brandId 			id of brand to add
	  	     * @param data				data to send
	  	     * @return 					product with new brand and new category equal @param brandId, @param categoryId
	  	     * @use 					productService,authManagerService
	  	     */
	  		$scope.insert = function( sessionId,categoryId,brandId,data ) {	
	 			productService.insert( sessionId,categoryId,brandId,data).then( function( response ) {
	 	       		/** do stuff with response **/
	             })
	  	    };
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category to add
	  	     * @param data 					data to send
	  	     * @return 						product with new category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.insertCategory = function(sessionId, categoryId , data) {		
	  	    	productService.insertCategory(sessionId, categoryId , data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	   
	  	             	
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param brandId 				id of brand to add
	  	     * @param data 					data to send
	  	     * @return 						product with new brand equal @param brandId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.insertBrand = function(sessionId, brandId,data) {		
	  	    	productService.insertBrand(sessionId, brandId,data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId  	    
	  	     * @param data 					data to send
	  	     * @return 						product update with data equal @param data 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.update = function(sessionId, data) {		
	  	    	productService.update(sessionId,order,data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product 	  
	  	     * @return 						product with id equal @param productId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.getProduct = function(sessionId, productId) {		
	  	    	productService.getProduct(sessionId, productId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to be deleted	   
	  	     * @return 						delete product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.deleteProduct = function(sessionId, productId) {		
	  	    	productService.deleteProduct(sessionId, productId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category 
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.getCategory = function(sessionId, categoryId) {		
	  	    	productService.getCategory(sessionId, categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add brand
	  	     * @param brandId 				if of brand
	  	     * @return 						new brand for product with productId equal @param productId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addBrand = function(sessionId, productId,brandId) {		
	  	    	productService.addBrand(sessionId, productId,brandId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add category
	  	     * @param categoryId 			id of category 
	  	     * @return 						new category for product with productId equal @param productId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addCategory = function(sessionId, productId,categoryId) {		
	  	    	productService.addCategory(sessionId, productId,categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add price
	  	     * @param data 					data to send
	  	     * @return 						product with price equal @param data
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addPrice = function(sessionId, productId, data) {		
	  	    	productService.addPrice(sessionId, productId, data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param productName 			name of product to retrieve  	    
	  	     * @return 						product name equal @param productName
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.getProductByName = function(sessionId, productName) {		
	  	    	productService.getProductByName(sessionId, productName).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findByCategory = function(sessionId, categoryId) {		
	  	    	productService.findByCategory(sessionId, categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findByBrand = function(sessionId, brandId) {		
	  	    	productService.findByBrand(sessionId, brandId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @param sessionId 			a sessionId  	   
	  	     * @return 						all product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findAll = function(sessionId) {		
	  	    	productService.findAll(sessionId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
}])
  
