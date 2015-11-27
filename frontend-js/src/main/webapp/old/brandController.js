var app = angular.module("product", ['AuthManager','ngload'])

.constant('api_endpoint', 			'http://localhost:8080/jax-rs/productService/')

 app.service("productService",['authManager','ngload',function( $http, $q ,authManager,api_endpoint) {
	        		 
	      	     /** Return public API. **/
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
	      	     /** create **/
	      	     function insert( category,brand,data ) {
	      	         var request = $http({
	      	             method: 'POST',
	      	             url: api_endpoint + category + '/' + brand,
	      	             headers: {
	      	             	'ssid': authManager.getsessionId
	      	             },   
	      	             data: data
	      	           });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** create **/
	      	     function insertCategory( category , data ) {
	      	         var request = $http({
	      	             method: 'POST',
	      	             url: api_endpoint + 'category/' + category,
	      	             headers: {
	      	             	'ssid': authManager.getsessionId
	      	             },   
	      	             data: data
	      	           });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** create **/
	      	     function insertBrand( brand,data ) {
	      	         var request = $http({
	      	             method: 'POST',
	      	             url: api_endpoint + 'brand/' + brand ,
	      	             headers: {
	      	             	'ssid': authManager.getsessionId
	      	             },   
	      	             data: data
	      	           });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** update **/
	      	     function update( data ) {
	      	         var request = $http({
	      	             method: 'PUT',
	      	             url: api_endpoint + 'update',
	      	             headers: {
	      	             	'ssid': authManager.getsessionId
	      	             },   
	      	             data: data
	      	           });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** delete **/
	      	     function deleteProduct(product) {
	      	         var request = $http({
	      	             method: 'DELETE',
	      	             url: api_endpoint + 'delete/' + product,
	      	             headers: {
	      	             	'ssid': authManager.getsessionId
	      	             }                        
	      	           });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	    
	      	     /** read **/      
	      	     function getProduct(product) {
	      	     	 var request = $http({
	      	              method: 'GET',
	      	              url	: 	api_endpoint + product,
	      	              headers: {
	      	              	'ssid': authManager.getSessionId
	      	              }                            
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/      
	      	     function getCategory(categoryId) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint  + 'getCategory/'+ categoryId,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/      
	      	     function addBrand(product,brand) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'addBrand/' + product + '/' + brand,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/         
	      	     function addCategory(product,category) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'addCategory/' + product + '/' + category,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/          
	      	     function addPrice(product) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'addPrice/' + product,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	      /** read **/           
	      	     function getProductByName(product) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'byCode/' + product ,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	      /** read **/           
	      	     function findByCategory(category) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'pageCategory/' + category,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/       
	      	     function findByBrand(brand) {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint +  'pageBrand/' + brand,
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
	      	              }                         
	      	            });
	      	         return( request.then( handleSuccess, handleError ) );
	      	     }
	      	     
	      	     /** read **/      
	      	     function findAll() {
	      	     	 var request = $http({
	      	              method: 	'GET',
	      	              url: 		api_endpoint + 'findAll',
	      	              headers: {
	      	              	'ssid': authManager.getsessionId
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
	         return( response.data );
	     }
	 }
	]);