var app = angular.module("login.controller", ['customer.service','ngDialog'])

.controller("loginController",['$scope', '$location','customerService','$timeout','$cookieStore','$state','$log','ngDialog',
                                  
      function($scope,$location,customerService,$timeout,$cookieStore,$state,$log,ngDialog) {	   	
	
			$scope.currentUser = customerService.currentUser;
			
			$scope.updateUser = function() {
				customerService.updateCurrentUser()
			}
			
			 /**
             * @param 	sessionId             
             * @use 	customerService
             * @returns logout message
             */
			$scope.logout = function(){
				customerService.logout().then(function (response) {
					customerService.updateCurrentUser();
            		$state.go('shop.home')})	   	 
			}
			           
		    /**
		     * @param
		     * @param
		     * @use
		     * @returns
		     */
			$scope.login = function(loginUser) {				
		        	customerService.login(loginUser.username, loginUser.password).then(function (response) {
		        		customerService.updateCurrentUser()
		        		$state.go('shop.home')
		        	})	   	 
			}
		      
		      		      
		  /**
		     * @param name
		     * @param lastname
		     * @param logonid (required)
		     * @param password
		     * @param email	            
		     * @use
		     * @returns new user and route to home
		     */
			$scope.register = function(registerUser) {	  				
		        	customerService.register(registerUser).then( function( response ) {
		        		customerService.updateCurrentUser()
			        	showModalOnSuccess();						
		        	})	        
		      };		 
		      
		/**
		 * show success message on modal angular with ngModal
		 */
		function showModalOnSuccess(){		    	  
			 	var dialog = ngDialog.open({
				    		  template: 'registerSuccessDialog',
				    		  keyboard: true,
				    		  className:'',
				    		  scope:    $scope  			  
				 });		
			    dialog.closePromise.then(function (response) {			    	     
			    	      $state.go('shop.home')
			    	  })
		}

}])
  

