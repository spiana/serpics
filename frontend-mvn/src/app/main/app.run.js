(function() {
	'use strict';


	angular.module('serpics.run',['customer.service'])
	.run(runBlock);
	
	runBlock.$inject = [ 'serpicsServices', 'serpicsHttpBuffer', 'logger',
			'$rootScope', '$timeout', 'TIMEOUT', 'customerService' ];
	/** @ngInject */
	function runBlock(serpicsServices, httpBuffer, logger, $rootScope, $timeout,
			TIMEOUT, customerService) {

		/* jshint validthis: true */
		var scope = this;
		var counter = 0;
		timeoutUser();

		scope = $rootScope.$new();
		scope.$on('event:sessiondId-expired', function() {
			if (counter !== 0) {
				logger.debug('Evento scatenato: sessiondId-expired' + counter);
			} else {
				logger.debug('Evento scatenato: sessiondId-expired ramo else'+
						counter);
				counter += 1;
				serpicsServices.removeCookie('ssid');
				serpicsServices.getSessionId().then(
						function(data, configUpdater) {
							var updater = configUpdater || function(config) {
								return config;
							};
							// $rootScope.$broadcast('event:auth-loginConfirmed', data);
							httpBuffer.retryAll(updater);
						});
			}
		});
		
		function timeoutUser() {
			logger.debug('runBlock - Timeout function');
			$timeout(function() {
				customerService.updateCurrentUser();
				timeoutUser();
			}, TIMEOUT * 60000);
		}

	}

})();