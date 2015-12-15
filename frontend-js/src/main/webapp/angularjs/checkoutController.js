 var app = angular.module("checkout.controller", ['order.service', 'cart.service', 'authentication.service'])

 /** checkoutController **/
.controller("checkoutController",['$state','$scope','authenticationService', 'orderService', 'cartService',
                                  
    function($state,$scope,authenticationService,orderService,cartService) {
	
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
        	authenticationService.login(this.userData.login.username, this.userData.login.password).then( function( response ) {		        			       				 
	        		 authenticationService.setCredential(userData.login.username, userData.login.password,true)
	        		 getUser().then($state.go('checkout.address'))	        		      			    		        		
    	 	})		        			        
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
}])