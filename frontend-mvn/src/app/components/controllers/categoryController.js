(function() {
	'use strict';
	/** categoryController **/
	angular.module('category.controller',[])
	.controller('CategoryController', categoryController);
	
	categoryController.$inject = ['categoryService', 'logger'];
	
	/** @ngInject */
	function categoryController(categoryService, logger) {

		logger.debug('Category');
		/* jshint validthis: true */
		var vm = this;
		vm.categoryData = getTopQ();
		//auxiliary var
		var cache = {
			isAdded : ''
		};

		/** implemented category service **/

		/**
		 * @return 					all category pather
		 * @use 					categoryService,
		 */
//		function getTop() {
//			logger.debug('Category Controller getTop()');
//			categoryService.getTop().then(function(response) {
//				logger.debug('Category Controller getTop() ramo then');
//				vm.categoryData = response.data;
//			})
//		}


		/**
		 * @return 					all top category
		 * @use 					categoryService,
		 */

		function getTopQ() {
			logger.debug('Category Controller getTopQ()');
			categoryService.getTopQ().then(function(response) {
				logger.debug('Category Controller getTopQ() ramo then');
				vm.categoryData = response;
				
			});

		}


		/**
		 * @param categoryId 			category id to be retrive
		 * @return 						a category by id
		 * @use 						categoryService,
		 */
		vm.getCategoryById = function(categoryId) {
			logger.debug('Category Controller getCategoryById(categoryId)' + categoryId);
			categoryService
					.getCategoryById(categoryId)
					.then(
							function(response) {
								logger.debug('Category Controller getCategoryById(categoryId) ramo then');
								vm.categoryData = response;
							});
		};

		/**
		 * @param code					code category
		 * @return 						a category by code
		 * @use 						categoryService,
		 */
		vm.getCategoryByCode = function(code) {
			logger.debug('Category Controller getCategoryByCode(code)' + code);
			categoryService
					.getCategoryByCode(code)
					.then(function(response) {
								logger.debug('Category Controller getCategoryByCode(code) ramo then');
								vm.categoryData = response;
							});
		};

		/**
		 * @param parentId 				a parent id category
		 * @param index 				index of categoryDataArray
		 * @param category 				category
		 * @return 						all category child
		 * @use 						categoryService,
		 */

		vm.getChild = function(parentId, index, category) {
			category.active = !category.active;
			logger.debug('Category Controller getChild(parentId,index,category) Category index: '	+ index);
			if (cache.isAdded.indexOf(parentId) !== -1) {
				logger.debug('Request gia\' effettuata: ' + ' Collapsed: '+ 
								category.active + ' Category Added: '+ 
								cache.isAdded);
			} else {
				categoryService.getChild(parentId).then(function(response) {
									logger.debug('getChild(parentId,index,category) ramo then');
									cache.isAdded += '#' + parentId;
									vm.categoryData[index].subCategory = response;
									logger.debug('Request effettuata '+ vm.categoryData[index].active);
								});
			}
		};

		/**
		 * @return 						all category 
		 * @use 						categoryService,
		 */
		vm.findAll = function(page, size) {
			logger.debug('Category Controller findAll()');
			categoryService.findAll(page, size).then(function(response) {
				logger.debug('Category Controller findAll() ramo then');
				vm.categoryData = response;
			});
		};
	}
})();
