var app = angular.module("login.controller", ['serpics.Authentication'])

.controller("loginController",['$scope','$rootScope', '$location','authenticationService','$timeout','$cookieStore',
                                  
      function($scope,$rootScope,$location,authenticationService,$timeout,$cookieStore) {	
   	
	
			$rootScope.messagge = 'Guest Access'
				
				
			
			$rootScope.action = {
					actionName:'Login',
					actionClass:'fa fa-lock'					
			}
				
	
			$scope.form = {
						logonid:null,
				  		username:null,
				  		password:null,
				  		email:null,
				  		status:null,
				  		member_id:null
		    }
			
            $rootScope.globals = $cookieStore.get('globals') || {};// keep user logged in after page refresh
			
           
            
            $scope.checkLoggedUser = function() {	 
            	 if(!$rootScope.globals.isLoggedIn){
            		 $rootScope.messagge = 'Welcome ' + $rootScope.globals.currentUser.username
            		 $rootScope.action.actionName  = 'Logout'
	       			 $rootScope.action.actionClass = 'fa fa-sign-out'
	       			 $location.path('/');
            	 }
            }
            
			$scope.login = function() {	   				
		        if ($scope.form.username && $scope.form.password) {	         
		        	authenticationService.login(this.form.username, this.form.password).then( function( response ) {
		        		console.log('form submitted correctly with credential:\nusername: ' 
	       				 + $scope.form.username +'\npassword: '+ $scope.form.password)	       				 
	       				 $rootScope.globals.currentUser = $scope.form.username 
	       				 $rootScope.globals.isLoggedIn 	= true
	       				 $rootScope.messagge = 'Welcome'
	       				 authenticationService.setCredential($scope.form.username, $scope.form.password)
	       				 $rootScope.action.actionName  = 'Logout'
	       				 $rootScope.action.actionClass = 'fa fa-sign-out'
	       				 $location.path('/');
		        		
		    	 	})
		        	
		        }
		      };
		      
		      
		      
		      
		      $timeout($scope.checkLoggedUser)
		  
}])
  

