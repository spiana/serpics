 var app = angular.module("cart.controller", ['cart.service', 'customer.service'])
 
 /** cartController **/
.controller("cartController",['$state','$scope','customerService', 'cartService',
                                  
function($state,$scope,customerService,cartService) {
	
	$scope.cart = {}
	$scope.currentUser = customerService.currentUser;
  	    
  	    getCurrentCart();
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,
  	     */
  		$scope.getCurrentCart = function() {
  	    	getCurrentCart();
  	    };
  	    
  	    /**
  	     * @param sku 					sku to send
  	     * @param quantity 		        quantity to send
  	     * @return 						a new cartItemModification
  	     * @use 						cartService,
  	     */
  	    $scope.cartAdd = function(sku ,quantity) {
  	    	console.log("CartController cartAdd(sku ,quantity)");
  			cartService.cartAdd(sku ,quantity).then(function(response){
    			  console.log("CartController cartAdd(sku ,quantity): ramo then");
    			  $scope.cart = response.cart;
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
    			  $scope.cart = response.cart;
    		  })
  	    };
  	    
  	 $scope.submitBillingForm = function (billingAddress,shippingToBill){
  		 
  		cartService.addBillingAddress(billingAddress).then(function(response){
			  console.log("checkoutController addBillingAddress(billingAddress): ramo then");
			  if (shippingToBill) {
				  cartService.addShippingAddress(billingAddress).then(function(response){
	    			  console.log("checkoutController shippingAddress(shippingAddress): ramo then");
	    			  $scope.cart = response;
	    			  $state.go('complete')
				  })
			  } else {
				  $scope.cart = response;
    			  $state.go('checkout.shipping')
			  }
		 })
  	 };
		 
	$scope.submitShippingForm = function (shippingAddress){
  			cartService.addShippingAddress(shippingAddress).then(function(response){
  			  console.log("checkoutController shippingAddress(shippingAddress): ramo then");
  			  $scope.cart = response;
  			  $state.go('complete')
  		  })
  	};
  	
	    function getCurrentCart() {
  			console.log("CartController getCurrentCart()");
  			cartService.getCurrentCart().then(function(response){
    			  console.log("CartController getCurrentCart(): ramo then");
    			  $scope.cart = response;
    		  })
  	    }
  		
}])