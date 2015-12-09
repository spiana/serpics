var app = angular.module("serpicsController", ['ngCookies'])

/** customerController **/
.controller("customerController",['$scope','$rootScope','$cookies','serpicsServices','customerService','$timeout', 
                                  
      function($scope,$rootScope,$cookies,serpicsServices,orderService) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/customerService'  	    	
  	    
  	    $rootScope.user 	= []
  	    
  	    /** implemented customer service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @return 					current Customer from session
  	     * @use 					customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
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
  	     * @use 						customerService,serpicsServices
  	     */
  	    $scope.deleteDestinationAddress = function(endpoint, addressid) {		
  	    	customerService.deleteDestinationAddress(endpoint,$rootScope.sessionId, addressid).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	             	    
}])
  

