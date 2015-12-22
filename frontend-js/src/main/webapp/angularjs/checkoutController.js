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
	
	$scope.updateUser = function() {
		customerService.updateCurrentUser()
	}
	           
    /**
     * @param
     * @param
     * @use
     * @returns
     */
	$scope.login = function(loginUser) {				
        	customerService.login(loginUser.username, loginUser.password).then(function (response) {
        		customerService.updateCurrentUser()
        		$state.go('checkout.address')
        	})	   	 
	}
      
      		      
  /**
     * @param name
     * @param lastname
     * @param logonid (required)
     * @param password
     * @param email	            
     * @use
     * @returns new user and route to home
     */
	$scope.register = function(registerUser) {	  				
        	customerService.register(registerUser).then( function( response ) {
        		customerService.updateCurrentUser()
	        	showModalOnSuccess();						
        	})	        
      };		 
      
/**
 * show success message on modal angular with ngModal
 */
function showModalOnSuccess(){		    	  
	 	var dialog = ngDialog.open({
		    		  template: 'registerSuccessDialog',
		    		  keyboard: true,
		    		  className:'',
		    		  scope:    $scope  			  
		 });		
	    dialog.closePromise.then(function (response) {			    	     
	    	      $state.go('checkout.address')
	    	  })
}

      
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