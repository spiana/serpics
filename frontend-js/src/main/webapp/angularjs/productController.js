 var app = angular.module("product.controller", ['product.service'])
/** productController **/
.controller("productController",['$scope','authManagerService','productService', 
                                  
	      function($scope,authManagerService,productService) {	
	   	
	  	    $scope.product 	= [];
	  	   	  
	  	 
	  	    /** implemented order service **/ 
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 		a sessionId
	  	     * @param categoryId 		id of category to add
	  	     * @param brandId 			id of brand to add
	  	     * @param data				data to send
	  	     * @return 					product with new brand and new category equal @param brandId, @param categoryId
	  	     * @use 					productService,authManagerService
	  	     */
	  		$scope.insert = function( endpoint,categoryId,brandId,data ) {	
	 			productService.insert(endpoint, $rootScope.sessionId,categoryId,brandId,data).then( function( response ) {
	 	       		/** do stuff with response **/
	             })
	  	    };
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category to add
	  	     * @param data 					data to send
	  	     * @return 						product with new category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.insertCategory = function(endpoint, categoryId , data) {		
	  	    	productService.insertCategory(endpoint,sessionId, categoryId , data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	   
	  	             	
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param brandId 				id of brand to add
	  	     * @param data 					data to send
	  	     * @return 						product with new brand equal @param brandId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.insertBrand = function(endpoint, brandId,data) {		
	  	    	productService.insertBrand(endpoint,$rootScope.sessionId, brandId,data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId  	    
	  	     * @param data 					data to send
	  	     * @return 						product update with data equal @param data 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.update = function(endpoint, data) {		
	  	    	productService.update(endpoint,sessionId,data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product 	  
	  	     * @return 						product with id equal @param productId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.getProduct = function(sessionId, productId) {		
	  	    	productService.getProduct(endpoint,$rootScope.sessionId, productId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to be deleted	   
	  	     * @return 						delete product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.deleteProduct = function(endpoint, productId) {		
	  	    	productService.deleteProduct(endpoint,$rootScope.sessionId, productId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category 
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerSer    
	  	     */
	  	    $scope.getCategory = function(endpoint, categoryId) {		
	  	    	productService.getCategory($rootScope.sessionId, categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add brand
	  	     * @param brandId 				if of brand
	  	     * @return 						new brand for product with productId equal @param productId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addBrand = function(endpoint, productId,brandId) {		
	  	    	productService.addBrand(endpoint,$rootScope.sessionId, productId,brandId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add category
	  	     * @param categoryId 			id of category 
	  	     * @return 						new category for product with productId equal @param productId 
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addCategory = function(endpoint, productId,categoryId) {		
	  	    	productService.addCategory(endpoint,$rootScope.sessionId, productId,categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productId 			id of product to add price
	  	     * @param data 					data to send
	  	     * @return 						product with price equal @param data
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.addPrice = function(endpoint, productId, data) {		
	  	    	productService.addPrice(endpoint,$rootScope.sessionId, productId, data).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param productName 			name of product to retrieve  	    
	  	     * @return 						product name equal @param productName
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.getProductByName = function(endpoint, productName) {		
	  	    	productService.getProductByName(endpoint,$rootScope.sessionId, productName).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findByCategory = function(endpoint, categoryId) {		
	  	    	productService.findByCategory(endpoint,$rootScope.sessionId, categoryId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findByBrand = function(brandId) {		
	  	    	productService.findByBrand(brandId).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId  	   
	  	     * @return 						all product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findAll = function(endpoint) {		
	  	    	productService.findAll(endpoint,$rootScope.sessionId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
}])
