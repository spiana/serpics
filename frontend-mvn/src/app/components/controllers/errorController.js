(function(){
	angular.module('error.controller', ['serpics.router'])
	
	.controller('ErrorController',errorController);
	
	errorController.$inject = ['$state','$stateParams','$log'];
	
	/** @ngInject */
	function errorController($state,$stateParams,$log) {
		
		var vm = this;
		
		activate();
		
		function activate(){
			$log.debug('errorController:  activate: stateParams '+ angular.toJson($stateParams));
			if ($stateParams.error == null){
				$state.go('shop.home');
				
			}else{
				vm.errorMessage=$stateParams.error;
			}
		}
		
		vm.goHome = function(){
			$log.debug('errorController:  goHome function '+ angular.toJson($stateParams));
			vm.errorMessage= {}
			$state.go('shop.home');
			}
	
	}

})();