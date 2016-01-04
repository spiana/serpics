 var app = angular.module("cart.controller", ['cart.service', 'customer.service'])
 
 /** cartController **/
.controller("cartController",['$state','$scope','customerService', 'cartService','$log',
                                  
function($state,$scope,customerService,cartService,$log) {
		
		$scope.cart = {}
		$scope.currentUser = customerService.currentUser;
	  	
		init()		
		
		function init() {
	  	    getCurrentCart();
		}
		
	    function getCurrentCart() {
  			$log.debug("CartController getCurrentCart()");
  			cartService.getCurrentCart().then(function(response){
    			  $log.debug("CartController getCurrentCart(): ramo then");
    			  $scope.cart = response;
    		  })
  	    }

  	  
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
  	    	$log.debug("CartController cartAdd(sku ,quantity)");
  			cartService.cartAdd(sku ,quantity).then(function(response){
    			  $log.debug("CartController cartAdd(sku ,quantity): ramo then");
    			  $scope.cart = response.cart;
    		  })
  	    };
  	    
  	   /**
  	     * @param cartItem    			cartItem to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,
  	     */
  	    $scope.cartUpdate = function( cartItem, quantity ) {
  	    	cartItem.quantity = quantity;
  	    	$log.debug("CartController cartUpdate(cartItem)");
  			cartService.cartUpdate(cartItem).then(function(response){
    			  $log.debug("CartController cartUpdate(cartItem): ramo then");
    			  getCurrentCart();
    		  })
  	    };
  	    
  	    /**
  	     * @param itemId 	    	itemId to delete
  	     * @return 					cartItemModification
  	     * @use 					cartService,
  	     */
  	    $scope.deleteItem = function(itemId) {		
  	    	$log.debug("CartController deleteItem(itemId)");
  			cartService.deleteItem(itemId).then(function(response){
    			  $log.debug("CartController deleteItem(itemId): ramo then");
    			  $scope.cart = response.cart;
    		  })
  	    };
  	    
	  	 $scope.submitBillingForm = function (billingAddress,shippingToBill){
	  		 
	  		cartService.addBillingAddress(billingAddress).then(function(response){
				  $log.debug("cartController addBillingAddress(billingAddress): ramo then");
				  if (shippingToBill) {
					  cartService.addShippingAddress(billingAddress).then(function(response){
		    			  $log.debug("cartController billingAddress(billingAddress): ramo then");
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
	  			  $log.debug("cartController shippingAddress(shippingAddress): ramo then");
	  			  $scope.cart = response;
	  			  $state.go('complete')
	  		  })
	  	};
  		
}])