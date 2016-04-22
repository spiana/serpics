(function() {
	angular.module('category.service', [])

	/**
	 * category service to handler rest call to category service
	 */
	.service('categoryService', categoryService);

	categoryService.$inject = [ '$http', '$q', 'sessionService', 'URL',
			'COOKIE_EXPIRES', '$cookies', '$log' ];

	function categoryService($http, $q, sessionService, URL, COOKIE_EXPIRES,
			$cookies, $log) {

		var endpoint = '/api/v1/categories/';

		/** Return public API. (like java interface)**/
		/** public methods**/
		var service = ({
			etCategoryById : getCategoryById,
			getCategoryByCode : getCategoryByCode,
			getTopQ : getTopQ,
			getChild : getChild,
			getChildByCode : getChildByCode,
			findAll : findAll,
			categoryProductsByCodePage: categoryProductsByCodePage,
			categoryProductsByIdPage: categoryProductsByIdPage
		});
		return service;
		/////////////////////

		
//		/**
//		 * @param endpoint
//		 * @param sessionId                
//		 * @return 
//		 */
//		function getTop() {
//			var defer = $q.defer();
//			var response = sessionService.getSessionId();
//			response.then(function(idSessione) {
//
//				var request = $http({
//					method : 'GET',
//					url : URL + endpoint + 'top',
//					headers : {
//						'ssid' : idSessione
//					}
//				}).then(function(response) {
//					defer.resolve(response.data.responseObject);
//				});
//			});
//
//			return defer.promise;
//		}

		/**
		 * @param endpoint
		 * @param sessionId                
		 * @return 
		 */
		function getTopQ() {
			var serviceSSID = sessionService;
			return $q(function(resolve, reject) {

				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log
											.debug('CategoryService getTopQ() ssid nel promise' + sessionId);
									$http({
										method : 'GET',
										url : URL + endpoint + 'top',
										headers : {
											'ssid' : sessionId
										}
									}).then(handleSuccess, handleError).then(
											resolve, reject);
								});

			});
		}

		/**
		 * @param categoryId                
		 * @return 
		 */
		function getCategoryById(categoryId) {
			var serviceSSID = sessionService;
			return $q(function(resolve, reject) {
				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log.debug('CategoryService getCategoryById(categoryId) ssid nel promise'+ sessionId);
									$http({
												method : 'GET',
												url : URL + endpoint + 'id/'+ categoryId,
												headers : {
													'ssid' : sessionId
												}
											}).then(handleSuccess, handleError)
											.then(resolve, reject);
								});
			});
		}

		/**
		 * @param code
		 * @param category                
		 * @return 
		 */
		function getCategoryByCode(code) {
			var serviceSSID = sessionService;
			return $q(function(resolve, reject) {
				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log.debug('CategoryService getCategoryByCode(code) ssid nel promise'+ sessionId);
									$http({
										method : 'GET',
										url : URL + endpoint + 'code/' + code,
										headers : {
											'ssid' : sessionId
										}
									}).then(handleSuccess, handleError).then(
											resolve, reject);
								});
			});
		}

		/**
		 * @param parentId                 
		 * @return 
		 */
		function getChild(parentId) {
			$log.debug('getChild(parentId): ' + parentId);
			var serviceSSID = sessionService;
			return $q(function(resolve, reject) {
				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log
											.debug('CategoryService getChild(parentId) ssid nel promise' +
													 sessionId);
									$http(
											{
												method : 'GET',
												url : URL + endpoint + 'id/'+ parentId + '/childs',
												headers : {
													'ssid' : sessionId
												}
											}).then(handleSuccess, handleError)
											.then(resolve, reject);
								});
			});
		}

		/**
		 * @param parentCode                 
		 * @return 
		 */
		function getChildByCode(parentCode) {
			$log.debug('getChildByCode(parentCode): ' + parentCode);
			var serviceSSID = sessionService;
			return $q(function(resolve, reject) {
				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log
											.debug('CategoryService getChildByCode(parentCode) ssid nel promise'+
													 sessionId);
									$http(
											{
												method : 'GET',
												url : URL + endpoint + 'code/'+ parentCode + '/childs',
												headers : {
													'ssid' : sessionId
												}
											}).then(handleSuccess, handleError)
											.then(resolve, reject);
								});
			});
		}
		
		function categoryProductsByCodePage(categoryCode, page, size){
			var serviceSSID = sessionService;

			var findAllUrl = '';
			if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
				findAllUrl = URL + endpoint + 'code/' + categoryCode;
			} else {
				findAllUrl = URL + endpoint + 'code/' + categoryCode + '/products/page?page=' + page + '&size=' + size;
			}

			return $q(function(resolve, reject) {
				serviceSSID.getSessionId().then(
						function(sessionId) {
							$log.debug('CategoryService categoryProductsByCodePage() ssid nel promise'+
									sessionId);
							$http({
								method : 'GET',
								url : findAllUrl,
								headers : {
									'ssid' : sessionId
									}
							}).then(handleSuccess, handleError).then(
									resolve, reject);
							});
				});
			
		}
		
		function categoryProductsByIdPage(categoryId, page, size){
			var serviceSSID = sessionService;

			var findAllUrl = '';
			if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
				findAllUrl = URL + endpoint + 'id/' + categoryId;
			} else {
				findAllUrl = URL + endpoint + 'id/' + categoryId + '/products/page?page=' + page + '&size=' + size;
			}

			return $q(function(resolve, reject) {
				serviceSSID.getSessionId().then(
						function(sessionId) {
							$log.debug('CategoryService categoryProductsByCodePage() ssid nel promise'+
									sessionId);
							$http({
								method : 'GET',
								url : findAllUrl,
								headers : {
									'ssid' : sessionId
									}
							}).then(handleSuccess, handleError).then(
									resolve, reject);
							});
				});
			
		}

		/**
		 * @return 
		 */
		function findAll(page, size) {
			var serviceSSID = sessionService;

			var findAllUrl = '';
			if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
				findAllUrl = URL + endpoint;
			} else {
				findAllUrl = URL + endpoint + '?page=' + page + '&size=' + size;
			}

			return $q(function(resolve, reject) {
				serviceSSID
						.getSessionId()
						.then(
								function(sessionId) {
									$log
											.debug('CategoryService findAll() ssid nel promise'+
													 sessionId);
									$http({
										method : 'GET',
										url : findAllUrl,
										headers : {
											'ssid' : sessionId
										}
									}).then(handleSuccess, handleError).then(
											resolve, reject);
								});
			});
		}

		/**
		 * private method.
		 * I transform the error response, unwrapping the application dta from
		 * the API response payload.
		 */

		function handleError(response) {
			/**
			 * The API response from the server should be returned in a
			 * nomralized format. However, if the request was not handled by the
			 * server (or what not handles properly - ex. server error), then we
			 * may have to normalize it on our end, as best we can.
			 */
			if (!angular.isObject(response.data) || !response.data.message) {
				return ($q
						.reject('CategoryService: An unknown error occurred.'));
			}
			/** Otherwise, use expected error message.**/
			return ($q.reject(response.data.message));
		}
		/** 
		 * I transform the successful response, unwrapping the application data
		 *from the API response payload.                
		 */
		function handleSuccess(response) {
			var serviceSSID = sessionService;
			serviceSSID.setCookie('ssid', $cookies.get('ssid'), COOKIE_EXPIRES);
			/** expire 20 minut **/
			return (response.data.responseObject);
		}
	}
})();
