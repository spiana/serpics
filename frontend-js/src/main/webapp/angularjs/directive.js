/**
 * all directive for SerpicsApp
 */
(function() {

	/**
	 * 
	 */
	var loaderDirective = function() {
		var loader = "<div class='loading-container'><div class='loading'></div>"+
					+ "<div id='loading-text'>{{ loadingText }}</div></div>"
		return {
			replace: true,
			restrict : 'EA',
			templateUrl : loader,
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var topHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/top-header.html',
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var middleHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/middle-header.html',
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var bottomHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/bottom-header.html',
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var slideDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/slide.html',
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var footerDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/footer.html',
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var categoryDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/left-sidebar.html",
			controller : 'categoryController'
		};
	};

	/**
	 * 
	 */
	var brandDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'
		};
	};

	/**
	 * 
	 */
	var productDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'

		};
	};

	/**
	 * 
	 */
	var cartDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'

		};
	};

	/**
	 * 
	 */
	var orderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'

		};
	};

	/**
	 * 
	 */
	var loginDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'

		};
	};

	/**
	 * 
	 */
	var registerDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/brand.html'

		};
	};

	var app = angular.module('serpicsDirective', [])
	.directive('loaderDirective', 		loaderDirective)
	.directive('topHeaderDirective',	topHeaderDirective)
	.directive('middleHeaderDirective',	middleHeaderDirective)
	.directive('bottomHeaderDirective',	bottomHeaderDirective)
	.directive('slideDirective', 		slideDirective)
	.directive('footerDirective', 		footerDirective)
	.directive('categoryDirective', 	categoryDirective)
	.directive('brandDirective', 		brandDirective)
	.directive('productDirective', 		productDirective)
	.directive('cartDirective', 		cartDirective)
	.directive('orderDirective',		orderDirective)
	.directive('loginDirective', 		loginDirective)
	.directive('registerDirective', 	registerDirective)
}());
