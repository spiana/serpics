 var app = angular.module("customer.controller", ['order.service', 'customer.service','geographic.service'])
/** customerController **/
.controller("customerController",['$scope','orderService', 'customerService','$log','geographicService',
                                  
      function($scope,orderService,customerService,$log,geographicService) {	
		
		 $scope.currentUser = customerService.currentUser;
		 
		 $scope.orders = {}
		 $scope.countries = {};
		 $scope.regions = {};
		 
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
  			 contactAddress.countryUuid = contactAddress.country.uuid;
  			 contactAddress.regionUuid = contactAddress.region.uuid;
  			 customerService.updateContactAddress(contactAddress).then( function( response ) {
  				$log.debug("customerController: updateContactAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateBillingAddress = function(billingAddress) {
  			 billingAddress.countryUuid = billingAddress.country.uuid;
  			 billingAddress.regionUuid = billingAddress.region.uuid;
  			 customerService.updateBillingAddress(billingAddress).then( function( response ) {
  				$log.debug("customerController: updateBillingAddress(): ramo then");
				customerService.updateCurrentUser();
  	    	})
  		 }
  		 
  		 $scope.updateDestinationAddress = function(destinationAddress) {
  			 	destinationAddress.countryUuid = destinationAddress.country.uuid;
  			 	destinationAddress.regionUuid = destinationAddress.region.uuid;
	  			 customerService.updateDestinationAddress(destinationAddress).then( function( response ) {
	  				$log.debug("customerController: updateDestinationAddress(): ramo then");
					customerService.updateCurrentUser();
	  	    	})
  		 }
  		 
  		$scope.addDestinationAddress = function(destinationAddress) {
			 	destinationAddress.countryUuid = destinationAddress.country.uuid;
  			 	destinationAddress.regionUuid = destinationAddress.region.uuid;
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
  		
	  	/**
	  	 * @param		 	
	  	 * @return 					country list
	  	 * @use 					geographicService,
	  	 */	  	 
		$scope.getCountryList = function (){
			geographicService.getCountryList().then(function(response){
	  			  $log.debug("customerController getCountryList(): ramo then");
	  			  $scope.countries = response;
	  		  })
	  	};
  		
	  	/**
	  	 * @param		 			countryId
	  	 * @return 					region list
	  	 * @use 					geographicService,
	  	 */	  	 
		$scope.getRegionByCountry = function (countryId){
			if (countryId != undefined){
				geographicService.getRegionByCountry(countryId).then(function(response){
		  			  $log.debug("customerController getRegionByCountry(countryId): ramo then");
		  			  $scope.regions = response;
		  		  })
			} else {
				$scope.regions = {};
			}
	  	};
	  	
}])