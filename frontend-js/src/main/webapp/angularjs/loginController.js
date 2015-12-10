var app = angular.module("login.controller", ['serpics.Authentication'])

.controller("loginController",['$scope','$rootScope', '$location','authenticationService','$timeout','$cookieStore',
                                  
      function($scope,$rootScope,$location,authenticationService,$timeout,$cookieStore) {	
   	
	
			$rootScope.message = 'Guest Access'				
			
			$rootScope.action = {
					actionName:'Login',
					actionClass:'fa fa-lock',
					dropMenuClass:'hidden'
			}
			
			$scope.userData ={
					login:{
						username:	null,
				  		password:	null,		
					},
					register:{
						logonid:	null,
						firstname:	null,
						lastname:	null,						
						password:	null,
						email:		null
					}
				}
			
			$rootScope.globals = $cookieStore.get('globals') || {};// keep user logged in after page refresh          
            
			 /**
             * @param
             * @param
             * @use
             * @returns
             */
            $scope.checkLoggedUser = function() {	 
            	 if($cookieStore.get('isLoggedIn')){
            		 $rootScope.message = 'Welcome ' + $rootScope.globals.currentUser.username
            		 $rootScope.action.actionName  = 'Logout'
	       			 $rootScope.action.actionClass = 'fa fa-sign-out'
	       			 $rootScope.action.dropMenuClass = 'visible'
	       			 $location.path('/');
            	 }
            }
            
            /**
             * @param
             * @param
             * @use
             * @returns
             */
			$scope.login = function() {	   				
		        	authenticationService.login(this.userData.login.username, this.userData.login.password).then( function( response ) {		        			       				 
			        		 $rootScope.globals.currentUser = $scope.userData.login.username 
		       				 $rootScope.message = 'Welcome ' + $rootScope.globals.currentUser
		       				 authenticationService.setCredential($scope.userData.login.username, $scope.userData.login.password,true)
		       				 $rootScope.action.actionName  = 'Logout'
		       				 $rootScope.action.actionClass = 'fa fa-sign-out'
		    	       		 $rootScope.action.dropMenuClass = 'visible'
		       				 $location.path('/');
			        		        		
		    	 	})		        	
		        
		      };		          		      
		      
	      /**
	         * @param name
	         * @param lastname
	         * @param logonid (required)
	         * @param password
	         * @param email	            
	         * @use
	         * @returns new user and route to home
	         */
			$scope.register = function() {	  				
				var userData = {									
							logonid:	$scope.userData.register.logonid,
							firstname:	$scope.userData.register.firstname,	
							lastname:	$scope.userData.register.lastname,
							password:	$scope.userData.register.password,
							email:		$scope.userData.register.email,
						}
		        	authenticationService.register(userData).then( function( response ) {			        	
	       				 $location.path('/');			        		
		    	 	})	        
		      };		
		      
		      $timeout($scope.checkLoggedUser)
		  
}])
  

