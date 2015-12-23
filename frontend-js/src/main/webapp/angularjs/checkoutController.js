 var app = angular.module("checkout.controller", ['cart.service', 'customer.service'])

 /** checkoutController **/
.controller("checkoutController",['$state','$scope','customerService', 'cartService',
                                  
    function($state,$scope,customerService,cartService) {
	
	$scope.cart = {}
	$scope.currentUser = customerService.currentUser;
	
	init()
			
	function init() {		
		getCart(); 		
	}
	
	function getCart() {
		cartService.getCurrentCart().then(function(response){
			  console.log("checkoutController getCart(): ramo then");
			  $scope.cart = response;
		  })
	}
	
	$scope.currentUser = customerService.currentUser;
	           
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
      
	$scope.addBillingAddress = function(billingAddress) {		
  	    	console.log("CartController addBillingAddress(billingAddress)");
  			cartService.addBillingAddress(billingAddress).then(function(response){
    			  console.log("CartController addBillingAddress(billingAddress): ramo then");
    			  $scope.cart = response;
    		  })
  	 };
  	    
  	 $scope.submitForm = function (billingAddress,shippingToBill){
  		 
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
  		
}])