(function() {
	'use strict';

	angular.module('customer.controller',
			[ 'order.service', 'customer.service', 'geographic.service' ])

	/** customerController **/
	.controller('CustomerController', customerController);
	customerController.$inject = [ '$scope','orderService', 'customerService', '$log','geographicService' ];

	/** @ngInject */
	function customerController($scope,orderService, customerService, $log,
			geographicService) {
		
		//TODOJS
		/* jshint validthis: true */
		var vm = this;

		vm.prova = 'provaprovata';
		
		$scope.currentUser = customerService.currentUser;

		$scope.orders = {};
		$scope.countries = {};
		$scope.regions = {};
		$scope.districts = {};

		/**
		 * @return 					list of orders
		 * @use 					orderService,serpicsServices
		 */
		$scope.getOrders = function() {
			orderService.getOrders().then(function(response) {
				$log.debug('customerController: getOrders(): ramo then');
				$scope.orders = response;
			});
		};

		$scope.updateUserData = function(userData) {
			customerService.updateUserData(userData).then(function(response) {
				$log.debug('customerController: updateUserData(): ramo then');
				customerService.updateCurrentUser();
			});
		};

		$scope.updateContactAddress = function(contactAddress) {
			if (contactAddress.country !== null) {
				contactAddress.countryIso3Code = contactAddress.country.iso3Code;
			}
			if (contactAddress.district !== null) {
				contactAddress.districtIsoCode = contactAddress.district.isoCode;
			}
			if (contactAddress.region !== null) {
				contactAddress.regionIsoCode = contactAddress.region.isoCode;
			}
			customerService.updateContactAddress(contactAddress).then(function(response) {
				$log.debug('customerController: updateContactAddress(): ramo then');
				customerService.updateCurrentUser();
				});
		};

		$scope.updateBillingAddress = function(billingAddress) {
			if (billingAddress.country !== null) {
				billingAddress.countryIso3Code = billingAddress.country.iso3Code;
			}
			if (billingAddress.region !== null) {
				billingAddress.regionName = billingAddress.region.name;
			}
			if (billingAddress.district !== null) {
				billingAddress.districtIsoCode = billingAddress.district.isoCode;
			}
			customerService.updateBillingAddress(billingAddress).then(function(response) {
								$log.debug('customerController: updateBillingAddress(): ramo then');
								customerService.updateCurrentUser();
							});
		};

		$scope.updateDestinationAddress = function(destinationAddress) {
			if (destinationAddress.country !== null) {
				destinationAddress.countryIso3Code = destinationAddress.country.iso3Code;
			}
			if (destinationAddress.region !== null) {
				destinationAddress.regionName = destinationAddress.region.name;
			}
			if (destinationAddress.district !== null) {
				destinationAddress.districtIsoCode = destinationAddress.district.isoCode;
			}
			customerService.updateDestinationAddress(destinationAddress).then(
					function(response) {
						$log.debug('customerController: updateDestinationAddress(): ramo then');
						customerService.updateCurrentUser();
						});
		};

		$scope.addDestinationAddress = function(destinationAddress) {
			if (destinationAddress.country !== null) {
				destinationAddress.countryIso3Code = destinationAddress.country.iso3Code;
			}
			if (destinationAddress.region !== null) {
				destinationAddress.regionName = destinationAddress.region.name;
			}
			if (destinationAddress.district !== null) {
				destinationAddress.districtIsoCode = destinationAddress.district.isoCode;
			}
			customerService
					.addDestinationAddress(destinationAddress)
					.then(function(response) {
								$log.debug('customerController: updateDestinationAddress(): ramo then');
								customerService.updateCurrentUser();
							});
		};

		$scope.deleteDestinationAddress = function(addressId) {
			customerService
					.deleteDestinationAddress(addressId)
					.then(function(response) {
								$log.debug('customerController: deleteDestinationAddress(): ramo then');
								customerService.updateCurrentUser();
							});
		};

		/**
		 * @param		 	
		 * @return 					country list
		 * @use 					geographicService,
		 */
		$scope.getCountryList = function() {
			geographicService.getCountryList().then(function(response) {
				$log.debug('customerController getCountryList(): ramo then');
				$scope.countries = response;
			});
		};

		/**
		 * @param		 			countryId
		 * @return 					region list
		 * @use 					geographicService,
		 */
		$scope.getRegionByCountry = function(countryId) {
			if (countryId !== angular.isUndefined ) {
				geographicService
						.getRegionByCountry(countryId)
						.then(function(response) {
							$log.debug('customerController getRegionByCountry(countryId): ramo then');
							$scope.regions = response;
								});
			} else {
				$scope.regions = {};
			}
		};

		/**
		 * @param		 			countryId
		 * @return 					district list
		 * @use 					geographicService,
		 */
		$scope.getDistrictByCountry = function(countryId) {
			if (countryId !== angular.isUndefined ) {
				geographicService.getDistrictByCountry(countryId)
						.then(function(response) {
							$log.debug('customerController getDistrictByCountry(countryId): ramo then');
							$scope.districts = response;
								});
			} else {
				$scope.districts = {};
			}
		};
	}
})();
