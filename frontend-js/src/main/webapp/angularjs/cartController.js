 var app = angular.module("cart.controller", ['cart.service'])
 /** cartController **/
.controller("cartController",['$scope','cartService',
                                  
      function($scope,cartService) {	
   	
  	    $scope.cart 	= [];
  	    $scope.cartItemModification =[];
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,
  	     */
  		$scope.getCurrentCart = function() {
  			console.log("CartController getCurrentCart()");
  			cartService.getCurrentCart().then(function(response){
    			  console.log("CartController getCurrentCart(): ramo then");
    			  $scope.cart = response;
    		  })
  	    };
  	    
  	    /**
  	     * @param sku 					sku to send
  	     * @param quantity 		        quantity to send
  	     * @return 						a new cart
  	     * @use 						cartService,
  	     */
  	    $scope.cartAdd = function(sku ,quantity) {
  	    	console.log("CartController cartAdd(sku ,quantity)");
  			cartService.cartAdd(sku ,quantity).then(function(response){
    			  console.log("CartController cartAdd(sku ,quantity): ramo then");
    			  $scope.cart = response;
    		  })
  	    };
  	    
  	   /**
  	     * @param cartItem    			cartItem to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,
  	     */
  	    $scope.cartUpdate = function( cartItem ) {		
  	    	console.log("CartController cartUpdate(cartItem)");
  			cartService.cartUpdate(cartItem).then(function(response){
    			  console.log("CartController cartUpdate(cartItem): ramo then");
    			  $scope.cart = response;
    		  })
  	    };
  	    
  	    /**
  	     * @param itemId 	    	itemId to delete
  	     * @return 					cartItemModification
  	     * @use 					cartService,
  	     */
  	    $scope.deleteItem = function(itemId) {		
  	    	console.log("CartController deleteItem(itemId)");
  			cartService.deleteItem(itemId).then(function(response){
    			  console.log("CartController deleteItem(itemId): ramo then");
    			  $scope.cartItemModification = response;
    		  })
  	    };
  	    
  	    /**
  	     * @param billingAddress    billingAddress to send
  	     * @return 					cartData
  	     * @use 					cartService,
  	     */
  	    $scope.addBillingAddress = function(billingAddress) {		
  	    	console.log("CartController addBillingAddress(billingAddress)");
  			cartService.addBillingAddress(billingAddress).then(function(response){
    			  console.log("CartController addBillingAddress(billingAddress): ramo then");
    			  $scope.cart = response;
    		  })
  	    };
  	    
  	  /**
  	     * @param shippingAddress 	shippingAddress to send
  	     * @return 					cartData
  	     * @use 					cartService,
  	     */
  	    $scope.addShippingAddress = function(shippingAddress) {		
  	    	console.log("CartController addShippingAddress(shippingAddress)");
  			cartService.addShippingAddress(shippingAddress).then(function(response){
    			  console.log("CartController shippingAddress(shippingAddress): ramo then");
    			  $scope.cart = response;
    		  })
  	    };
  	             	    
}])
