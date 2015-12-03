var app = angular.module("serpicsController", ['ngCookies'])


/** categoryController **/
.controller("categoryController",['$scope','$rootScope','$q','$cookies','authManagerService','categoryService','$timeout', 
                                     
         function($scope,$rootScope,$q,$cookies,authManagerService,categoryService,$timeout) {	
      	
			var endpoint    		= 'http://localhost:8080/jax-rs/categoryService/'    
			var deferred 			= $q.defer();
		 	$scope.categoryData 	= [];
		 	$scope.subCategory		= []
		 	$scope.bool = false
		 	//auxiliary var
		 	var cache = {
		 			category:[],
		 			subCategory:[],	
		 			bool:null,
		 			data:''
		 	}
		 	
//			getTop();
		 	getTopQ();

			 /** implemented category service **/ 
			
     	    /**
     	     * @param sessionId 		a sessionId
     	     * @return 					all category pather
     	     * @use 					categoryService,authManagerService
     	     */
		 	 function getTop(){	
					console.log("Category Controller: session id for top method:-> ");
                 	categoryService.getTop(endpoint).then( function( response ) {                 	
                 		$scope.categoryData 	= response.data;                  	
                 })
     	    };     	    	         	   
     	    
     	   function getTopQ(){
     		  console.log("topQ");
     		   categoryService.getTopQ(endpoint).then(function(response){
     			for(var json in response){
     				if(response[json].childCategoryNumber)
     					cache.category.push(response[json])
     			}
     		   $scope.categoryData = cache.category;
     		   })
     	   };
     	   
     	    /**
     	     * @param endpoint 		    web service rest endpoint
     	     * @param sessionId 		a sessionId
     	     * @param data 				data to send
     	     * @return 					new category
     	     * @use 					categoryService,authManagerService
     	     */
     	    $scope.create = function(endpoint,data) {
     	       	categoryService.create(endpoint,$rootScope.sessionId, data ).then( function( response ) {
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
     	    $scope.createParent = function(endpoint,parentId,data) {
     	       	categoryService.createParent(endpoint,$rootScope.sessionId, parentId , data ).then( function( response ) {
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
     	    $scope.addParent = function(endpoint, childId,parentId,data) {
     	    	$rootScope.createSessionId()
     	       	categoryService.addParent(endpoint,$rootScope.sessionId, childId,parentId,data ).then( function( response ) {
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
     	    $scope.updateCategory = function(endpoint,data) {
     	    	$rootScope.createSessionId()
     	       	categoryService.updateCategory(endpoint,$rootScope.sessionId, data ).then( function( response ) {
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
     	    $scope.deleteCategory = function(endpoint,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.deleteCategory(endpoint,$rootScope.sessionId,categoryId).then( function( response ) {
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
     	    $scope.getCategoryById = function(endpoint,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.getCategoryById(endpoint,$rootScope.sessionId,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param code					code category
     	     * @param categoryId 			category id to retrieve
     	     * @return 						a category by code
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getCategoryByCode = function(endpoint,code,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.getCategoryByCode(endpoint,$rootScope.sessionId,code,categoryId).then( function( response ) {
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
     	    $scope.getChild = function(parentId) {     	 
     	     	       	categoryService.getChild(endpoint,parentId).then( function( response ) {
	     	        $scope.subCategory = response        
                 })
     	    	
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @return 						all category 
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.findAll = function(endpoint) {		
     	       	categoryService.findAll(endpoint,$rootScope.sessionId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };     	           
  	   
//     	   /** Simulate network latency with deferred resolution. **/
//            $timeout(
//                function() {
//                    deferred.resolve( data );
//                },
//                ( 2 * 1000 )
//            );
//            return( deferred.promise );


 }])

 /** brandController **/
.controller("brandController",['$scope','brandService','$timeout',
                                     
     function($scope,brandService,$timeout) {	
  	
		
 	    $scope.brandData 	= []
 	  
	 	getBrandQ();
 	    /** implemented brand service **/  	    
		
		/**
		 * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.getBrand = function(data) {	
			
			console.log("Brand Controller: session id for top method:-> " + $scope.sessionId)
 	    	brandService.getBrand(endpoint,$scope.sessionId).then( function( response ) {
 	    		$scope.brandData = response.content
 	    		})
 	    };
 	    
  	   function getBrandQ(){
  		  console.log("Controller BrandQ");
  		  brandService.getBrandQ().then(function(response){
  			  console.log("BrandQ ramo then");
  			  $scope.brandData = response;
  			  })
  			  };
		
 	    /**
 	     * @param endpoint 		   		web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.addBrand = function(endpoint,data ) {	
 	    	brandService.addBrand(endpoint,$rootScope.sessionId,data).then( function( response ) {
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
 	    $scope.updateBrand = function(endpoint, data) {		
 	    	brandService.updateBrand(endpoint,$rootScope.sessionId, data).then( function( response ) {
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
 	    $scope.deleteBrand = function(endpoint,brandId ) {		
 	    	brandService.deleteBrand(endpoint,$rootScope.sessionId,brandId ).then( function( response ) {
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
 	    $scope.findBrandById = function(endpoint,code,brandId) {		
 	    	brandService.findBrandById(endpoint,$rootScope.sessionId,code,brandId).then( function( response ) {
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
 	    $scope.findBrandByName = function(endpoint,name) {		
 	    	brandService.findBrandByName(endpoint,$rootScope.sessionId,name).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId         	    
 	     * @return 					all brand
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findAll = function(endpoint) {		
 	    	brandService.findAll(endpoint,sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /** execute function on view content load **/
// 	     $timeout($scope.getBrand)
 }])
 
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
  

