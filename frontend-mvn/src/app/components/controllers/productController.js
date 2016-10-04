(function() {
	angular.module('product.controller',[])

	/** productController **/
	.controller('ProductController', productController);

	productController.$inject = [ '$scope', 'productService', '$state', '$stateParams', 'cartService', 'categoryService', 'brandService', 'logger', '$sce',
			'ngDialog' ];

	/** @ngInject */
	function productController($scope, productService, $state, $stateParams, cartService, categoryService, brandService,logger, $sce, ngDialog) {

		
		//console.log('stateParams:'+angular.toJson($stateParams));
		var categoryId = $stateParams.categoryId;
		var brandId = $scope.brandId;
		var productId = $scope.productId;
		var textSearch = $scope.textSearch;
		var page = getPage();
		var size = getSize();

		$scope.trustAsHtml = $sce.trustAsHtml;

		$scope.defaultQuantity = 1;

		$scope.product = findAllQ(page, size);

		function getPage() {
			if ($scope.product) {
				return $scope.product.number;
			} else {
				return 0;
			}
		}

		function getSize() {
			if ($scope.product) {
				return $scope.product.size;
			} else {
				return 9;
			}
		}

		$scope.range = function(totalPages) {
			var input = [];
			for (var i = 0; i < totalPages; i++) {
				input.push(i);
			}

			return input;
		};

		$scope.addToCart = function(sku, quantity) {
			logger.debug('ProductController cartAdd(sku ,quantity)');
			cartService.cartAdd(sku, quantity).then(function() {
								logger.debug('ProductController cartAdd(sku ,quantity): ramo then');
								$state.go('shop.cart');
							});
		};

		/** implemented order service **/

		/**
		 * @param productId 			id of product 	  
		 * @return 						product with id equal @param productId
		 * @use 						productService
		 */
		function getProduct(productId) {
			productService.getProduct(productId).then(
					function(response) {
						logger.debug('ProductController getProduct(productId): ramo then');
						$scope.product = response;
						});
			}

		/**
		 * @param productId 			id of product 
		 * @return 						product's main category
		 * @use 						productService  
		 */
//		function getCategory(productId) {
//			productService.getCategoryProduct(productId).then(
//					function(response) {
//						$scope.productCategory = response;
//					});
//		}

		/**
		 * @param productName 			name of product to retrieve  	    
		 * @return 						product name equal @param productName
		 * @use 						productService
		 */
//		function getProductByName(productName) {
//			productService.getProductByName(productName).then(
//					function(response) {
//						$scope.product = response;
//					});
//		}

		/**
		 * @param categoryId 			id of category of product to retrieve  	    
		 * @return 						product with category equal @param categoryId
		 * @use 						productService
		 */
//		function findByCategory(categoryId, page, size) {
//			productService.findByCategory(categoryId, page, size).then(
//					function(response) {
//						$scope.product = response;
//					});
//		}

		/**
		 * @param brandId 				id of brand of product to retrieve    
		 * @return 						product with brand equal @param brandId
		 * @use 						productService
		 */
//		function findByBrand(brandId, page, size) {
//			productService.findByBrand(brandId, page, size).then(
//					function(response) {
//						$scope.product = response;
//					});
//		}

		/**
		 * @return 						all product
		 * @use 						productService
		 */
		function findAll(page, size) {
			productService.findAll(page, size).then(function(response) {
				$scope.product = response;
			});
		}

		/**
		 * @param textSearch 			text to seach   
		 * @return 						product with textSearch in code, name or description
		 * @use 						productService
		 */
		function findBySearch(searchText, page, size) {
			productService.findBySearch(searchText, page, size).then(
					function(response) {
						$scope.product = response;
					});
		}

		function findAllQ(page, size) {
			logger.debug('Controller ProductQ');
			if (productId) {
				getProduct(productId);
			} else {
				if (textSearch) {
					findBySearch(textSearch, page, size);
				} else {
					if (!$scope.categoryId && $scope.brandId) {
						brandService.brandProductsByIdPage($scope.brandId, page, size).then(function(response) {
							$scope.product = response;
						});
					}
					if ($scope.categoryId && !$scope.brandId) {
						categoryService.categoryProductsByIdPage($scope.categoryId, page, size).then(function(response) {
							$scope.product = response;
						});
					}
					if (!$scope.categoryId && !$scope.brandId) {
						findAll(page, size);
					}
				}
			}
		}

		$scope.findAllQ = function(page, size) {
			findAllQ(page, size);
		};

		$scope.openGalleryImageModal = function(imageUrl) {

			ngDialog.open({
				template : 'galleryImageDialog',
				keyboard : true,
				className : 'xxx',
				data: {
					imageUrl: imageUrl
                  }
			});
		};
	}
})();
