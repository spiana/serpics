var app = angular.module("login.controller", ['authentication.service','ngDialog'])

.controller("loginController",['$rootScope','$rootScope', '$location','authenticationService','$timeout','$cookieStore','$state','$log','ngDialog',
                                  
      function($rootScope,$scope,$location,authenticationService,$timeout,$cookieStore,$state,$log,ngDialog) {	
   	
	
			$rootScope.currentUser = {
						id:			null,
				  		password:	null,
				  		lastname:   null,
						firstname:	null,
						logonid:	null,						
						email:		null,
						created:	null,
						userType:   null,
						message:	null,
						registerMesage:null
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
							logonid:$rootScope.currentUser.logonid,
							password:$rootScope.currentUser.password,
					}   				
		        	authenticationService.login(loginData.logonid, loginData.password).then( function( response ) { 
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
							logonid:	$rootScope.currentUser.logonid,
							firstname:	$rootScope.currentUser.firstname,	
							lastname:	$rootScope.currentUser.lastname,
							password:	$rootScope.currentUser.password,
							email:		$rootScope.currentUser.email,
						}
		        	authenticationService.register(userData).then( function( response ) {
		        		showModalOnSuccess();						
		        	})	        
		      };		
		      
		      /**
		       * 
		       */
		      function setCredential(response){
		    	  $rootScope.currentUser = {	
		    			  	id:			response.id,
		    			  	logonid:	response.logonid,							
							firstname:	response.firstname,
							lastname:	response.lastname,								
							email:		response.email,
							userType:   response.userType,
							created: 	response.created,
				       	}		    	  
		    	  $log.debug('Found Current User -> ',  $rootScope.currentUser)
		      }    
		      
			      /**
			       * show success message on modal angular with ngModal
			       */
			      function showModalOnSuccess(){		    	  
			    	  var dialog = ngDialog.open({
				    		  template: 'registerSuccessDialog',
				    		  keyboard: true,
				    		  className:'',
				    		  scope:    $rootScope  			  
				    		  });		

			    	  dialog.closePromise.then(function (data) {			    	     
			    	      $state.go('shop.home')
			    	  })
			      }
			      
			  
			      		      
}])
  

