var app = angular.module("login.controller", ['serpics.Authentication'])

/** customerController **/
.controller("loginController",['$scope','$rootScope', '$location','authenticationService',
                                  
      function($scope,$rootScope,$location,authenticationService) {	
   	
			
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
			
			$rootScope.globals = null;
			
			$scope.login = function() {	   
				if($rootScope.action.actionName == 'Login'){
		        if ($scope.form.username && $scope.form.password) {	         
		        	authenticationService.login(this.form.username, this.form.password).then( function( response ) {
		        		console.log('form submitted correctly with credential:\nusername: ' 
	       				 + $scope.form.username +'\npassword: '+ $scope.form.password)
	       				 
	       				 $rootScope.globals.currentUser = $scope.form.username 
	       				 
	       				 authenticationService.setCredential($scope.form.username, $scope.form.password)
	       				 $rootScope.action.actionName  = 'Logout'
	       				 $rootScope.action.actionClass = 'fa fa-sign-out'
	       				 $location.path('/');
		        		
		    	 	})
		        	}
		        }
		      };
		        	    
  	             	    
}])
  

