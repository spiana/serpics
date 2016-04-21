(function(){
	angular.module('login.controller', [ ])
	
	.controller('LoginController',loginController);
	
	loginController.$inject = ['customerService','$state','ngDialog','$stateParams','logger'];
	
	/** @ngInject */
	function loginController(customerService,$state,ngDialog,$stateParams,logger) {
	
		/* jshint validthis: true */
		var vm= this;
			vm.currentUser = customerService.currentUser;
			vm.image = 'imagessss';
			
			vm.updateUser = function() {
				customerService.updateCurrentUser();
			};
			
			activate();
			function activate(){
				logger.debug('LoginController:  activate: stateParams '+ angular.toJson($stateParams));
				vm.errorMessage=$stateParams.error;
			}
			 /**
             * @param 	sessionId             
             * @use 	customerService
             * @returns logout message
             */
			vm.logout = function(){
				customerService.logout().then(function () {
					customerService.updateCurrentUser();
				});	   	 
			};
			           
		    /**
		     * @param
		     * @use
		     * @returns
		     */
			vm.login = function(loginUser) {				
		        	customerService.login(loginUser.username, loginUser.password).then(function () {
		        		customerService.updateCurrentUser();
		        		logger.debug('StateParams'+angular.toJson($stateParams));
		        		$stateParams.error={};
		        		vm.errorMessage={};
		        		$state.go($stateParams.home);
		        	},failedLogin);	   	 
			};
		           
		  /**
		     * @param registerUser	            
		     * @use
		     * @returns new user and route to home
		     */
			vm.register = function(registerUser) {	  				
		        	customerService.register(registerUser).then( successRegister, failedRegister);        
		      };
		      
		      function successRegister(){
		    	  customerService.updateCurrentUser();
		    	  showModalOnSuccess();
		      }
		      
		      function failedRegister(error){
		    	  showModalOnFailed(error,$stateParams.register);
		      }
		      
		      function failedLogin(error){
		    	  showModalOnFailed(error,$stateParams.login);
		      }
		      
		      /**
		       * show success message on modal angular with ngModal
		       */
		      function showModalOnSuccess(){
		    	  var dialog = ngDialog.open({
		    		  template : 'registerSuccessDialog',
		    		  keyboard : true,
		    		  className : 'xxx',
		    		  data: {
		    			  message: 'User Registered Succesfully!!!. Back To Home Or Login Please!!!'
		    		  }
		    	  });
		    	  dialog.closePromise.then(function () {
		    		  $state.go($stateParams.login);
		    		  });
		      }
		      
		      function showModalOnFailed(error,stateParams){
		    	  var dialog = ngDialog.open({
		    		  template : 'registerSuccessDialog',
		    		  keyboard : true,
		    		  className : 'xxx',
		    		  data: {
		    			  message: error
		    		  }
		    	  });
		    	  dialog.closePromise.then(function () {
		    		  $state.go(stateParams);
		    		  });
		      }
}
})();
  

