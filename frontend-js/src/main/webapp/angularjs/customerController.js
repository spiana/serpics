 var app = angular.module("customer.controller", ['order.service', 'customer.service'])
/** customerController **/
.controller("customerController",['$scope','orderService', 'customerService',
                                  
      function($scope,orderService,customerService) {	
		
		 $scope.currentUser = customerService.currentUser;
		 
		 $scope.orders = {}
		
		 init()
		 
		 function init(){
			if ($scope.currentUser.userType == "REGISTERED"){
				getOrders();
			}
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

}])