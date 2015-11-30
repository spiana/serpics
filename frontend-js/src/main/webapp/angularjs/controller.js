var app = angular.module("serpicsController", ['ngCookies'])


/** categoryController **/
.controller("categoryController",['$scope','$rootScope','$cookies','authManagerService','categoryService','$timeout', 
                                     
         function($scope,$rootScope,$cookies,authManagerService,categoryService,$timeout) {	
      	
			var endpoint    	= 'http://localhost:8080/jax-rs/categoryService/'    

			$rootScope.category 	= []	 
	 		
			
			 /** implemented category service **/ 
			
     	    /**
     	     * @param sessionId 		a sessionId
     	     * @return 					all category pather
     	     * @use 					categoryService,authManagerService
     	     */
     		$scope.getTop = function() {	
				$rootScope.createSessionId()
                 	categoryService.getTop(endpoint,$rootScope.sessionId).then( function( response ) {
                 	$rootScope.category 	= response                  	
                 })
     	    };
     	    	         	   
     	    
     	    /**
     	     * @param endpoint 		    web service rest endpoint
     	     * @param sessionId 		a sessionId
     	     * @param data 				data to send
     	     * @return 					new category
     	     * @use 					categoryService,authManagerService
     	     */
     	    $scope.create = function(sessionId,data) {		
     	       	categoryService.create(endpoint,sessionId, data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param parentId 				a parent category
     	     * @param data 					data to send
     	     * @return 						add parent to category
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.createParent = function(sessionId,parentId,data) {		
     	       	categoryService.createParent(endpoint,sessionId, parentId , data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param childId 				a session id
     	     * @param parentId 				a parent id 
     	     * @param data 					data to send
     	     * @return 						add parent to category of id @param childId
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.addParent = function(sessionId, childId,parentId,data) {		
     	       	categoryService.addParent(endpoint,sessionId, childId,parentId,data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param data 					data to send
     	     * @return 						a category updated with @data params
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.updateCategory = function(sessionId,data) {		
     	       	categoryService.updateCategory(endpoint,sessionId, data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param categoryId 			a category id
     	     * @return delete 				a category with id @categoryId
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.deleteCategory = function(sessionId,categoryId) {		
     	       	categoryService.deleteCategory(endpoint,sessionId,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param categoryId 			category id to be retrive
     	     * @return 						a category by id
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getCategoryById = function(sessionId,categoryId) {		
     	       	categoryService.getCategoryById(endpoint,sessionId,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param code					code 
     	     * @param categoryId 			category id to retrieve
     	     * @return 						a category by code
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getCategoryByCode = function(sessionId,code,categoryId) {		
     	       	categoryService.getCategoryByCode(endpoint,sessionId,code,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param parentId 				a parent id category
     	     * @return 						all category child
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getChild = function(sessionId,parentId) {		
     	       	categoryService.getChild(endpoint,sessionId,parentId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @return 						all category 
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.findAll = function(sessionId) {		
     	       	categoryService.findAll(endpoint,sessionId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };     	         
     	    
     	    /** execute function on view content load **/
     	   	$timeout($scope.getTop)

 }])

 /** brandController **/
.controller("brandController",['$scope','$rootScope','$cookies','authManagerService','brandService','$timeout', 
                                     
     function($scope,$rootScope,$cookies,authManagerService,brandService,$timeout) {	
  	
		var endpoint    	= 'http://localhost:8080/jax-rs/brandService/'    
 	    	
 	    $rootScope.brand 	= []
 	  
 	    /** implemented brand service **/  	    
		
		/**
		 * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.getBrand = function(sessionId,data ) {	
			$rootScope.createSessionId()
 	    	brandService.getBrand(endpoint,$rootScope.sessionId).then( function( response ) {
 	    		$rootScope.brand = response.content
 	    		})
 	    };
		
 	    /**
 	     * @param endpoint 		   		web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.addBrand = function(sessionId,data ) {	
 	    	brandService.addBrand(endpoint,sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @param data 					data to send
 	     * @return 						a brand update with @param data
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.updateBrand = function(sessionId, data) {		
 	    	brandService.updateBrand(endpoint,sessionId, data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @param brandId    			id of brand to be deleted
 	     * @return 				
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.deleteBrand = function(sessionId,brandId ) {		
 	    	brandService.deleteBrand(endpoint,sessionId,brandId ).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 		a sessionId
 	     * @param code 	    
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandById = function(sessionId,code,brandId) {		
 	    	brandService.findBrandById(endpoint,sessionId,code,brandId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 		a sessionId
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param name
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandByName = function(sessionId,name) {		
 	    	brandService.findBrandByName(endpoint,sessionId,name).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId         	    
 	     * @return 					all brand
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findAll = function(sessionId) {		
 	    	brandService.findAll(endpoint,sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /** execute function on view content load **/
 	   //	$timeout($scope.getBrand)
 }])
 
 /** cartController **/
.controller("cartController",['$scope','$rootScope','$cookies','authManagerService','cartService', '$timeout',
                                  
      function($scope,$rootScope,$cookies,authManagerService,cartService,$timeout) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
  	    	
  	    $rootScope.cart 	= []
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,authManagerService
  	     */
  		$scope.getCurrentCart = function(sessionId,data ) {	
  	    	cartService.getCurrentCart(endpoint,sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						cartService,authManagerService
  	     */
  	    $scope.cartAdd = function(sessionId, data) {		
  	    	cartService.cartAdd(endpoint,sessionId, data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	   /**
  	    * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param data    				data to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,authManagerService
  	     */
  	    $scope.cartUpdate = function(sessionId, data ) {		
  	    	cartService.cartUpdate(endpoint,sessionId,data ).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param code 	    
  	     * @return 					all brand by @param brandId
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.deleteItem = function(sessionId,data) {		
  	    	cartService.deleteItem(endpoint,sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.addBillingAddress = function(sessionId,data) {		
  	    	cartService.addBillingAddress(endpoint,sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	  /**
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.addShippingAddress = function(sessionId,data) {		
  	    	cartService.addShippingAddress(endpoint,sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	             	    
}])

/** productController **/
.controller("productController",['$scope','$cookies','authManagerService','productService','$timeout', 
                                  
	      function($scope,$cookies,authManagerService,productService,$timeout) {	
	   	
	  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
	  	    	
	  	    $rootScope.product 	= []
	  	   	  
	  	 
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
	  		$scope.insert = function( sessionId,categoryId,brandId,data ) {	
	 			productService.insert(endpoint, sessionId,categoryId,brandId,data).then( function( response ) {
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
	  	    $scope.insertCategory = function(sessionId, categoryId , data) {		
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
	  	    $scope.insertBrand = function(sessionId, brandId,data) {		
	  	    	productService.insertBrand(endpoint,sessionId, brandId,data).then( function( response ) {
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
	  	    $scope.update = function(sessionId, data) {		
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
	  	    	productService.getProduct(endpoint,sessionId, productId).then( function( response ) {
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
	  	    $scope.deleteProduct = function(sessionId, productId) {		
	  	    	productService.deleteProduct(endpoint,sessionId, productId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param categoryId 			id of category 
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerSer  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'  	    	
vice
	  	     */
	  	    $scope.getCategory = function(sessionId, categoryId) {		
	  	    	productService.getCategory(sessionId, categoryId).then( function( response ) {
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
	  	    $scope.addBrand = function(sessionId, productId,brandId) {		
	  	    	productService.addBrand(endpoint,sessionId, productId,brandId).then( function( response ) {
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
	  	    $scope.addCategory = function(sessionId, productId,categoryId) {		
	  	    	productService.addCategory(endpoint,sessionId, productId,categoryId).then( function( response ) {
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
	  	    $scope.addPrice = function(sessionId, productId, data) {		
	  	    	productService.addPrice(endpoint,sessionId, productId, data).then( function( response ) {
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
	  	    $scope.getProductByName = function(sessionId, productName) {		
	  	    	productService.getProductByName(endpoint,sessionId, productName).then( function( response ) {
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
	  	    $scope.findByCategory = function(sessionId, categoryId) {		
	  	    	productService.findByCategory(endpoint,sessionId, categoryId).then( function( response ) {
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
	  	    $scope.findByBrand = function(sessionId, brandId) {		
	  	    	productService.findByBrand(endpoint,sessionId, brandId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId  	   
	  	     * @return 						all product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    $scope.findAll = function(sessionId) {		
	  	    	productService.findAll(endpoint,sessionId).then( function( response ) {
	  	       		/** do stuff with response **/
	              })
	  	    };  	
}])

/** orderController **/
.controller("orderController",['$scope','$rootScope','$cookies','authManagerService','orderService','$timeout', 
                                  
      function($scope,$rootScope,$cookies,authManagerService,orderService,$timeout) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'  	    	
  	    
  	    $rootScope.order 	= []
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					orderService,authManagerService
  	     */
  		$scope.getOrders = function(sessionId) {	
  	    	orderService.getOrders(endpoint,sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param order 				add payment for @param order
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						orderService,authManagerService
  	     */
  	    $scope.addPayment = function(sessionId, order, data) {		
  	    	orderService.addPayment(endpoint,sessionId,order,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };  	   
  	             	    
}])
  

