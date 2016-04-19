(function() {
	'use strict';

	/** brandController **/
	angular.module('brand.controller',['brand.service'])
	.controller('BrandController', brandController);
	
	brandController.$inject = [ '$scope','brandService','logger' ];
	
	/** @ngInject */
	function brandController($scope,brandService, logger) {

		/* jshint validthis: true */
		var vm = this;
		vm.brandData = getBrandList();

		/** implemented brand service **/

		/**
		 * @return 					all brands list
		 * @use 					brandService
		 */
		function getBrandList() {
			logger.debug('Brand Controller getBrandQ()');
			brandService
					.getBrandList()
					.then(
							function(response) {
								logger.debug('Brand Controller getBrandQ(): ramo then response' + response[0].brandProductNumber);
								vm.brandData = response;
							});
		}

		/**
		 * @param brandId 		   	brandId to search
		 * @return 					all brand by @param brandId
		 * @use 					brandService
		 */
		vm.findBrandById = function(brandId) {
			logger.debug('Brand Controller findBrandById: ' + brandId);
			brandService.findBrandById(brandId).then(function(response) {
				logger.debug('Brand Controller findBrandById(): ramo then');
				vm.brandData = response;
			});
		};

		/**
		 * @param name 				name of brand to retrieve
		 * @return 					all brand by @param code
		 * @use 					brandService
		 */
		vm.findBrandByCode = function(code) {
			logger.debug('Brand Controller findBrandByName: ' + code);
			brandService.findBrandByCode(code).then(function(response) {
				logger.debug('Brand Controller findBrandByCode(): ramo then');
				vm.brandData = response;
				});
		};

		/**
		 * @return 					all brand
		 * @use 					brandService
		 */
		vm.findAll = function(page, size) {
			logger.debug('Brand Controller findAll: page:' + page + ' size: '+ size);
			brandService.findAll(page, size).then(function(response) {
				logger.debug('Brand Controller findAll(): ramo then');
				vm.brandData = response;
				});
		};
	}
})();
