var app = angular.module("customer.controller", ['customer.service'])

/** customerController **/
.controller("customerController",['$scope','customerService', 
                                  
      function($scope,customerService) {	
   	
  	    $scope.user 	= [];
  	    
  	    /** implemented customer service **/ 
  	    
  	    
  	  /**
 	     * @param sessionId 		a sessionId
 	     * @return 					all category pather
 	     * @use 					categoryService,
 	     */
	 	 function getCurrent(){	
				console.log("Category Controller: session id for top method:-> ");

             	categoryService.getTop(endpoint).then( function( response ) {
             	$scope.categoryData 	= response.data;                  	
             })
 	    };
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @return 					current Customer from session
  	     * @use 					customerService,
  	     */
  		$scope.getCurrent = function(endpoint) {	
  	    	customerService.getCurrent(endpoint,$rootScope.sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param user 				    create user
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.create = function(endpoint, user) {		
  	    	customerService.create(endpoint,$rootScope.sessionId,user).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param user 				    update user
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.updateCustomer = function(endpoint, user) {		
  	    	customerService.updateCustomer(endpoint,$rootScope.sessionId,user).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param username			    username for login
  	     * @param password			    password for login
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.login = function(endpoint, username, passoword) {		
  	    	customerService.login(endpoint,$rootScope.sessionId, username, passoword).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update contact address
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.updateContactAddress = function(endpoint, address) {		
  	    	customerService.updateContactAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update billing address
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.updateBillingAddress = function(endpoint, address) {		
  	    	customerService.updateBillingAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    update destination address
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.updateDestinationAddress = function(endpoint, address) {		
  	    	customerService.updateDestinationAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    add destination address
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.addDestinationAddress = function(endpoint, address) {		
  	    	customerService.addDestinationAddress(endpoint,$rootScope.sessionId, address).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param address			    delete addressuid
  	     * @return 						void
  	     * @use 						customerService,
  	     */
  	    $scope.deleteDestinationAddress = function(endpoint, addressid) {		
  	    	customerService.deleteDestinationAddress(endpoint,$rootScope.sessionId, addressid).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	             	    
}])
  

