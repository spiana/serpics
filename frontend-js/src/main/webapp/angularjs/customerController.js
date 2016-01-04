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
  				console.log("customerController: update(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }

}])