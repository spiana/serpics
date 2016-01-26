 var app = angular.module("cart.controller", ['cart.service', 'customer.service','serpics.router'])
 
 /** cartController **/
.controller("cartController",['$state','$scope','customerService', 'cartService','$log','$stateParams',
                                  
function($state,$scope,customerService,cartService,$log,$stateParams) {
		
		$scope.$state = $state;
		$scope.cart = getCurrentCart();
		$scope.currentUser = customerService.currentUser;
		$scope.shipmodeList = {};
		$scope.paymethodList = {};
	  	
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
  	    
  	    /**
  	     * @param billingAddress 	billingAddress to add
  	     * @return 					a cart update with @param data
  	     * @use 					cartService,
  	     */
	  	 $scope.submitBillingForm = function (billingAddress,shippingToBill){
	  		 
	  		cartService.addBillingAddress(billingAddress).then(function(response){
				  $log.debug("cartController addBillingAddress(billingAddress): ramo then1");
				  var complete = $stateParams.complete;
				  if (shippingToBill) {
					  cartService.addShippingAddress(billingAddress).then(function(response){
		    			  $log.debug("cartController billingAddress(billingAddress): ramo then");
		    			  $scope.cart = response;
		    			  $state.go($stateParams.shipmode)
					  })
				  } else {
					  $scope.cart = response;
					  $log.debug("cartController billingAddress(billingAddress): ramo else"+JSON.stringify($stateParams));
	    			  $state.go($stateParams.shipping)
				  }
			 })
	  	 };
		
	  	/**
	  	 * @param shippingAddress 	shippingAddress to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.submitShippingForm = function (shippingAddress){
	  			cartService.addShippingAddress(shippingAddress).then(function(response){
	  			  $log.debug("cartController shippingAddress(shippingAddress): ramo then");
	  			  $scope.cart = response;
	  			  $state.go($stateParams.shipmode)
	  		  })
	  	};
	  	
	  	/**
	  	 * @param shipMode		 	shipMode to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.addShipmode = function (shipmode){
	  			cartService.addShipmode(shipmode).then(function(response){
	  			  $log.debug("cartController addShipmode(shipMode): ramo then");
	  			  $scope.cart = response;
	  			  $state.go($stateParams.payment)
	  		  })
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					a list of paymethod available for current store
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.getPaymethodList = function (){
	  			cartService.getPaymethodList().then(function(response){
	  			  $log.debug("cartController getPaymethodList(): ramo then");
	  			  $scope.paymethodList = response;
	  		  })
	  	};
	  	
	  	/**
	  	 * @param shipMode		 	shipMode to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.addPaymethod = function (paymethod){
	  			cartService.addPaymethod(paymethod).then(function(response){
	  			  $log.debug("cartController addPaymethod(shipMode): ramo then");
	  			  $scope.cart = response;
	  			  $state.go($stateParams.payment)
	  		  })
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					a list of shipmode available for currentCart
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.getShipmodeList = function (){
	  			cartService.getShipmodeList().then(function(response){
	  			  $log.debug("cartController getShipmodeList(): ramo then");
	  			  $scope.shipmodeList = response;
	  		  })
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					new sessionCart
	  	 * @use 					cartService,
	  	 */	  	 
		$scope.deleteCart = function (){
	  			cartService.deleteCart().then(function(response){
	  			  $log.debug("cartController deleteCart(): ramo then");
	  			  $scope.cart = response;
	  		  })
	  	};
  		
}])