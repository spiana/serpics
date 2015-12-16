var app = angular.module("login.controller", ['authentication.service','ngDialog'])

.controller("loginController",['$rootScope','$rootScope', '$location','authenticationService','$timeout','$cookieStore','$state','$log','ngDialog',
                                  
      function($rootScope,$scope,$location,authenticationService,$timeout,$cookieStore,$state,$log,ngDialog) {	
   	
	
			$rootScope.action = {
					actionName:'Login',
					actionRoute:'shop.login',									
			};
					
			$rootScope.currentUser = {
						id:			null,
						username:	null,
				  		password:	null,					
						firstname:	null,
						lastname:	null,						
						email:		null,
						created:	null,
						userType:   null,
						message:	null,
						successRegister:null	
			       		}
			
		   
			
			checkLoggedUser()
			
				 /**
	             * @param
	             * @param
	             * @use authenticationService
	             * @returns
	             */
              function checkLoggedUser() {	            	   		 
            		authenticationService.getCurrentUser().then( function( response ) { //from rest customer service              			
            		setCredential(response)  
            		if($rootScope.currentUser.userType == 'REGISTERED'){
            			$rootScope.action.actionName ='Logout'
                		$rootScope.action.functionName ='logout()'
            		}
            				$state.go('shop.home');
            	})            	
            }
			
			 /**
             * @param 	sessionId             
             * @use 	authenticationService
             * @returns logout message
             */
			$rootScope.logout = function(){
				authenticationService.logout().then( function( response ) {
					$rootScope.action.actionName ='Login'
                	$rootScope.action.functionName =''
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
					var loginData = {
							username:$rootScope.currentUser.username,
							password:$rootScope.currentUser.password
					}   				
		        	authenticationService.login(loginData.username, loginData.password).then( function( response ) { 
		        		checkLoggedUser()		        		
		        	$state.go('shop.home');
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
							logonid:	$rootScope.currentUser.username,
							firstname:	$rootScope.currentUser.firstname,	
							lastname:	$rootScope.currentUser.lastname,
							password:	$rootScope.currentUser.password,
							email:		$rootScope.currentUser.email,
						}
		        	
		        			 showModalOnSuccess()
		        	$state.go('shop.home');	
		        	        
		      };		
		      
		      /**
		       * 
		       */
		      function setCredential(response){
		    	  $rootScope.currentUser = {	
		    			  	id:			response.id,
							username:	response.logonid,							
							firstname:	response.firstname,
							lastname:	response.lastname,								
							email:		response.email,
							userType:   response.userType,
							created: 	response.created,
				       	}		    	  
		    	  $log.debug('Found Current User -> ',  $rootScope.currentUser)
		      }    
		      
		      /**
		       * show success message on modal angular
		       */
		      function showModalOnSuccess(){		    	  
		    	  ngDialog.open({
		    		  template: 'successDialog',
		    		  scope:    $rootScope  			  
		    		  });		    	 
		      }
		      
}])
  

