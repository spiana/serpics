var app = angular.module("customer.controller", ['customer.service'])

/** customerController **/
.controller("customerController",['$scope','customerService', 
                                  
      function($scope,customerService) {	
   	
			$scope.form = {
						logonid:'',
				  		username:'',
				  		password:'',
				  		email:'',
				  		status:'',
				  		member_id:''
		    }
	 
  	    /** implemented customer service **/ 
  	    
  	    
//  	  /**
// 	     * @param sessionId 		a sessionId
// 	     * @return 					all category pather
// 	     * @use 					categoryService,
// 	     */
//	 	 function getCurrent(){	
//				console.log("Category Controller: session id for top method:-> ");
//             	categoryService.getTop(endpoint).then( function( response ) {
//             	$scope.categoryData 	= response.data;                 	
//             })
// 	    };
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
	      $scope.login = function() {	    	
	        if ($scope.username && $scope.password) {	         
	    	 customerService.login(this.username, this.password).then( function( response ) {
       		 console.log('form submitted correctly with credential:\nusername: ' 
       				 + $scope.username +'\npassword: '+ $scope.password)
       				 /** do stuff with response 200**/
	    	 	})
	        }
	      };

	      
	      /**
	  	     * @param endpoint 		    	web service rest endpoint
	  	     * @param sessionId 			a sessionId
	  	     * @param username			    username for login
	  	     * @param password			    password for login
	  	     * @return 						void
	  	     * @use 						customerService,
	  	     */      
		      $scope.register = function() {
		    	  $scope.user ={
		    			  username:$scope.username,
		    			  password:$scope.password,
		    			  email:$scope.email,
		    			  status:'ACTIVE',
		    			  member_id:'10'
		    	  }
		        if ($scope.user!=null) {	         
		    	 customerService.create($scope.user).then( function( response ) {
	       		 console.log('create new user with credential:\nusername: ' 
	       				 + $scope.username +'\npassword: '+ $scope.password +'\nemail: '+ $scope.email)
	       				 /** do stuff with response 200**/
		    	 	})
		        }
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
  

