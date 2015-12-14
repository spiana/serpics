var app = angular.module("login.controller", ['serpics.authentication'])

.controller("loginController",['$rootScope','$rootScope', '$location','authenticationService','$timeout','$cookieStore',
                                  
      function($rootScope,$scope,$location,authenticationService,$timeout,$cookieStore) {	
   	
	
			$rootScope.message = 'Guest Access'						
				
			$rootScope.action = {
					actionName:'Login',
					actionClass:'fa fa-lock',
					dropMenuClass:'hidden'
			};
			
			$rootScope.error ={					
						statusText:null,
			       		message:null
							       		
		    }
			 /**
             * @param
             * @param
             * @use
             * @returns
             */
            $rootScope.checkLoggedUser = function() {	 
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
			$rootScope.login = function() {	   				
		        	authenticationService.login(this.userData.login.username, this.userData.login.password).then( function( response ) {		        			       				 
			        		 $rootScope.globals.currentUser = $rootScope.userData.login.username 
		       				 $rootScope.message = 'Welcome ' + $rootScope.globals.currentUser
		       				 authenticationService.setCredential($rootScope.userData.login.username, $rootScope.userData.login.password,true)
		       				 $rootScope.error.message = null
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
			$rootScope.register = function() {	  				
				var userData = {									
							logonid:	$rootScope.userData.register.logonid,
							firstname:	$rootScope.userData.register.firstname,	
							lastname:	$rootScope.userData.register.lastname,
							password:	$rootScope.userData.register.password,
							email:		$rootScope.userData.register.email,
						}
		        	authenticationService.register(userData).then( function( response ) {			        	
	       				 $location.path('/');			        		
		    	 	})	        
		      };		
		      
		      $timeout($rootScope.checkLoggedUser)
		  
}])
  

