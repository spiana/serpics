(function(){
	angular.module('error.controller', [ ])
	
	.controller('ErrorController',errorController);
	
	errorController.$inject = ['$state','$stateParams','logger'];
	
	/** @ngInject */
	function errorController($state,$stateParams,logger) {
		
		var vm = this;
		
		activate();
		
		function activate(){
			logger.debug('errorController:  activate: stateParams '+ angular.toJson($stateParams));
			if ($stateParams.error == null){
				$state.go('shop.home');
				
			}else{
				vm.errorMessage=$stateParams.error;
			}
		}
		
		vm.goHome = function(){
			logger.debug('errorController:  goHome function '+ angular.toJson($stateParams));
			vm.errorMessage= {};
			vm.errorStatus= {};
			$state.go('shop.home');
			};
	
	}

})();