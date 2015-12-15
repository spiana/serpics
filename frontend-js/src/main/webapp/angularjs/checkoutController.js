 var app = angular.module("checkout.controller", ['cart.service', 'authentication.service'])

 /** checkoutController **/
.controller("checkoutController",['$state','$scope','authenticationService', 'cartService',
                                  
    function($state,$scope,authenticationService,cartService) {
	
	$scope.cart = {}
	$scope.currentUser = {}
	
	init()
	
	function init() {
		
		getCart();
		getUser()
  		
	}
	
	function getCart() {
		cartService.getCurrentCart().then(function(response){
			  console.log("checkoutController getCart(): ramo then");
			  $scope.cart = response;
		  })
	}
	
	function getUser() {
  		authenticationService.checkCurrentUser().then( function( response ) {
  			console.log("checkoutController getCurrentCart(): ramo then");
  			$scope.currentUser = response;
  		})
	}
	
    /**
     * @param
     * @param
     * @use
     * @returns
     */
	$scope.login = function() {
        	authenticationService.login(this.userData.login.username, this.userData.login.password).then(function(response) {
        		getUser()
        	}    			       				 
////	        		 authenticationService.setCredential(userData.login.username, userData.login.password,true).then(
//	        		 getUser().then($state.go('checkout.address')))	        		      			    		        		
    	 	)		        			        
      };		          		      
      
  /**
     * @param name
     * @param lastname
     * @param logonid (required)
     * @param password
     * @param email	            
     * @use
     * @returns new user and route to home
     */
	$scope.register = function() {	  				
		var userData = {									
					logonid:	$scope.userData.register.logonid,
					firstname:	$scope.userData.register.firstname,	
					lastname:	$scope.userData.register.lastname,
					password:	$scope.userData.register.password,
					email:		$scope.userData.register.email,
				}
        	authenticationService.register(userData).then( function( response ) {			        	
   				 $state.go('checkout.address')			        		
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
//  		if ($rootScope.currentUser.userType == 'REGISTERED'){
//  			
//  		}
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