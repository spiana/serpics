(function() {
	'use strict';


	angular.module('serpics.run',[])
	.run(runBlock);
	
	runBlock.$inject = [ 'serpicsHttpBuffer', 'logger',
			'$rootScope', '$timeout', 'TIMEOUT', 'customerService', 'sessionService' ];
	/** @ngInject */
	function runBlock( httpBuffer, logger, $rootScope, $timeout,
			TIMEOUT, customerService, sessionService) {

		/* jshint validthis: true */
		var scope = this;
		var counter = 0;
		timeoutUser();

		scope = $rootScope.$new();
		scope.$on('event:sessiondId-expired', function() {
			if (counter !== 0) {
				logger.debug('runBlock - Evento scatenato: sessiondId-expired' + counter);
			} else {
				logger.debug('runBlock - Evento scatenato: sessiondId-expired ramo else'+
						counter);
				counter += 1;
				sessionService.removeCookie('ssid');
				sessionService.getSessionId().then(
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