var app = angular.module("serpicsController", ['ngCookies'])


 /** cartController **/
.controller("cartController",['$scope','$rootScope','$cookies','authManagerService','cartService', '$timeout',
                                  
      function($scope,$rootScope,$cookies,authManagerService,cartService,$timeout) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/cartService'
  	    	
  	    $rootScope.cart 	= []
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,authManagerService
  	     */
  		$scope.getCurrentCart = function(endpoint,data ) {	
  	    	cartService.getCurrentCart(endpoint,$rootScope.sessionId,data).then( function( response ) {
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
  	    $scope.cartAdd = function(endpoint, data) {		
  	    	cartService.cartAdd(endpoint,$rootScope.sessionId, data).then( function( response ) {
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
  	    $scope.cartUpdate = function(endpoint, data ) {		
  	    	cartService.cartUpdate(endpoint,$rootScope.sessionId,data ).then( function( response ) {
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
  	    $scope.deleteItem = function(endpoint,data) {		
  	    	cartService.deleteItem(endpoint,$rootScope.sessionId,data).then( function( response ) {
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
  	    $scope.addBillingAddress = function(endpoint,data) {		
  	    	cartService.addBillingAddress(endpoint,$rootScope.sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	  /**
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.addShippingAddress = function(endpoint,data) {		
  	    	cartService.addShippingAddress(endpoint,sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	             	    
}])

/** productController **/
.controller("productController",['$scope','$cookies','$rootScope','authManagerService','productService','$timeout', 
                                  
	      function($scope,$cookies,$rootScope,authManagerService,productService,$timeout) {	
	   	
	  	    var endpoint    = 'http://localhost:8080/jax-rs/productService'
	  	    	
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
	  	    $scope.findByBrand = function(endpoint, brandId) {		
	  	    	productService.findByBrand(endpoint,$rootScope.sessionId, brandId).then( function( response ) {
	  	       		/** do stuff with response **/
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

/** orderController **/
.controller("orderController",['$scope','$rootScope','$cookies','authManagerService','orderService','$timeout', 
                                  
      function($scope,$rootScope,$cookies,authManagerService,orderService,$timeout) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/orderService'  	    	
  	    
  	    $rootScope.order 	= []
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					orderService,authManagerService
  	     */
  		$scope.getOrders = function(endpoint) {	
  	    	orderService.getOrders(endpoint,$rootScope.sessionId).then( function( response ) {
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
  	    $scope.addPayment = function(endpoint, order, data) {		
  	    	orderService.addPayment(endpoint,$rootScope.sessionId,order,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };  	   
  	             	    
}])

/** customerController **/
.controller("customerController",['$scope','$rootScope','$cookies','authManagerService','customerService','$timeout', 
                                  
      function($scope,$rootScope,$cookies,authManagerService,orderService,$timeout) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/customerService'  	    	
  	    
  	    $rootScope.user 	= []
  	    
  	    /** implemented customer service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @return 					current Customer from session
  	     * @use 					customerService,authManagerService
  	     */
  		$scope.getCurrent = function(endpoint) {	
  	    	customerService.getCurrent(endpoint,$rootScope.sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param user 				    create user
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.create = function(endpoint, user) {		
  	    	customerService.create(endpoint,$rootScope.sessionId,user).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param user 				    update user
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.updateCustomer = function(endpoint, user) {		
  	    	customerService.updateCustomer(endpoint,$rootScope.sessionId,user).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param username			    username for login
  	     * @param password			    password for login
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.login = function(endpoint, username, passoword) {		
  	    	customerService.login(endpoint,$rootScope.sessionId, username, passoword).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update contact address
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.updateContactAddress = function(endpoint, address) {		
  	    	customerService.updateContactAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update billing address
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.updateBillingAddress = function(endpoint, address) {		
  	    	customerService.updateBillingAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update destination address
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.updateDestinationAddress = function(endpoint, address) {		
  	    	customerService.updateDestinationAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    add destination address
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.addDestinationAddress = function(endpoint, address) {		
  	    	customerService.addDestinationAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    delete addressuid
  	     * @return 						void
  	     * @use 						customerService,authManagerService
  	     */
  	    $scope.deleteDestinationAddress = function(endpoint, addressid) {		
  	    	customerService.deleteDestinationAddress(endpoint,$rootScope.sessionId, addressid).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	             	    
}])
  

