 var app = angular.module("customer.controller", ['order.service', 'customer.service'])
/** customerController **/
.controller("customerController",['$scope','orderService', 'customerService','$log',
                                  
      function($scope,orderService,customerService,$log) {	
		
		 $scope.currentUser = customerService.currentUser;
		 
		 $scope.orders = {}
		 
		 /**
  	     * @return 					list of orders
  	     * @use 					orderService,serpicsServices
  	     */
		 $scope.getOrders = function() {	
  	    	orderService.getOrders().then( function( response ) {
  	    		$log.debug("customerController: getOrders(): ramo then");
  	    		$scope.orders = response;
  	    	})
  		 };
  		 
  		 $scope.updateUserData = function(userData) {
  			 customerService.updateUserData(userData).then( function( response ) {
  				$log.debug("customerController: updateUserData(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 } 		 
  		 
  		 $scope.updateContactAddress = function(contactAddress) {
  			 customerService.updateContactAddress(contactAddress).then( function( response ) {
  				$log.debug("customerController: updateContactAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateBillingAddress = function(billingAddress) {
  			 customerService.updateBillingAddress(billingAddress).then( function( response ) {
  				$log.debug("customerController: updateBillingAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateDestinationAddress = function(destinationAddress) {
	  			 customerService.updateDestinationAddress(destinationAddress).then( function( response ) {
	  				$log.debug("customerController: updateDestinationAddress(): ramo then");
					customerService.updateCurrentUser();
	  	    	})
  		 }
  		 
  		$scope.addDestinationAddress = function(destinationAddress) {
	  			 customerService.addDestinationAddress(destinationAddress).then( function( response ) {
		  				$log.debug("customerController: updateDestinationAddress(): ramo then");
						customerService.updateCurrentUser();
	  			 })
 		 }
  		
  		$scope.deleteDestinationAddress = function(addressId) {
 			 customerService.deleteDestinationAddress(addressId).then( function( response ) {
	  				$log.debug("customerController: deleteDestinationAddress(): ramo then");
					customerService.updateCurrentUser();
 			 })
	 }
}])