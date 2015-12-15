var app = angular.module("login.controller", ['authentication.service'])

.controller("loginController",['$rootScope','$rootScope', '$location','authenticationService','$timeout','$cookieStore','$state','$log',
                                  
      function($rootScope,$scope,$location,authenticationService,$timeout,$cookieStore,$state,$log) {	
   	
	
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
			
			checkLoggedUser()
			
				 /**
	             * @param
	             * @param
	             * @use authenticationService
	             * @returns
	             */
              function checkLoggedUser() {	 
            	 if($cookieStore.get('isLoggedIn')){            		 
            		authenticationService.checkCurrentUser().then( function( response ) {//from rest customer service            			
            		$rootScope.message = 'Welcome ' + response.logonid + ' ' + response.lastname            		            		
            		$rootScope.action.actionName  = 'Logout'
	       			$rootScope.action.actionClass = 'fa fa-sign-out'
	       			$rootScope.action.dropMenuClass = 'visible'
	       			debugUserLogged(response)//only for the test
	       			$state.go('shop.home');
            		 })
            	 }
            }
			
			 /**
             * @param 	sessionId             
             * @use 	authenticationService
             * @returns logout message
             */
			function logout(sessionId){
				authenticationService.logout().then( function( response ) {   
					$state.go('shop.home');
				})
			}
			           
            /**
             * @param
             * @param
             * @use
             * @returns
             */
			$rootScope.login = function() {	   				
		        	authenticationService.login(this.userData.login.username, this.userData.login.password).then( function( response ) {		        			       				 
			        		 authenticationService.setCredential($rootScope.userData.login.username, $rootScope.userData.login.password,true)
		       					    checkLoggedUser()    		        		
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
		        	$state.go('shop.home');	
		        	})	        
		      };		
		      
		      /***
		       * debug current user for test
		       */
		      function debugUserLogged(response){
          		$log.debug(
          				"Found Current User id[[" 	+ response.id + "]]--> " 
          				+ "{{  name-> [" 			+ response.firstname
						+ ']  lastname-> [' 		+ response.lastname
						+ ']  email-> [' 			+ response.email
						+ ']  created: ['  			+ response.created						
						+ ']  userType: [' 			+ response.userType
						+ ']  logonid: ['  			+ response.logonid +'] }}');    }
		  
}])
  

