 var app = angular.module("customer.controller", ['order.service', 'customer.service'])
/** customerController **/
.controller("customerController",['$scope','orderService', 'customerService',
                                  
      function($scope,orderService,customerService) {	
		
		 $scope.currentUser = customerService.currentUser;
		 
		 $scope.orders = {}
		
		 init()
		 
		 function init(){
			getOrders();
		 }
		 
		 /**
  	     * @return 					list of orders
  	     * @use 					orderService,serpicsServices
  	     */
  		 function getOrders() {	
  	    	orderService.getOrders().then( function( response ) {
  	    		console.log("customerController: getOrders(): ramo then");
  	    		$scope.orders = response;
  	    	})
  		 };
  		 
  		 $scope.updateUserData = function(userData) {
  			 customerService.updateUserData(userData).then( function( response ) {
  				console.log("customerController: updateUserData(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 } 		 
  		 
  		 $scope.updateContactAddress = function(contactAddress) {
  			 customerService.updateContactAddress(contactAddress).then( function( response ) {
  				console.log("customerController: updateContactAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateBillingAddress = function(billingAddress) {
  			 customerService.updateBillingAddress(billingAddress).then( function( response ) {
  				console.log("customerController: updateBillingAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateDestinationAddress = function(destinationAddress) {
  			 if ($scope.currentUser.destinationAddress[0].uuid != null){
	  			 customerService.updateDestinationAddress(destinationAddress).then( function( response ) {
	  				console.log("customerController: updateDestinationAddress(): ramo then");
					customerService.updateCurrentUser();
	  	    	})
  			 } else {
	  			 customerService.addDestinationAddress(destinationAddress).then( function( response ) {
		  				console.log("customerController: updateDestinationAddress(): ramo then");
						customerService.updateCurrentUser();
	  			 })
  			 }
  		 }
}])