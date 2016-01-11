var app = angular.module("login.controller", ['customer.service','ngDialog','serpics.router'])

.controller("loginController",['$scope','customerService','$state','$log','ngDialog','$stateParams','$log',
                                  
      function($scope,customerService,$state,$log,ngDialog,$stateParams,$log) {
	
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
				})	   	 
			}
			           
		    /**
		     * @param
		     * @use
		     * @returns
		     */
			$scope.login = function(loginUser) {				
		        	customerService.login(loginUser.username, loginUser.password).then(function (response) {
		        		customerService.updateCurrentUser();
		        		$state.go($stateParams.login);
		        	})	   	 
			}
		           
		  /**
		     * @param registerUser	            
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
			    	      $state.go($stateParams.register)
			    	  })
		}

}])
  

