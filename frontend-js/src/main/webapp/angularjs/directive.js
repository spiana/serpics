/**
 * all directive for SerpicsApp
 */
(function() {

	/**
	 * 
	 */
	var loaderDirective = function() {
		var loader = 	"<div class='loading-container'><div class='loading'></div><div id='loading-text'>" +
						"{{ loadingText }}</div></div>"	
			
			return {		
			replace: true,
			restrict : 'EA',
			template : loader,
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
	var breadCrumbsDirective = function() {
		var breadcrumbs = "<div class='container'><div class='row'><ul class='breadcrumb'>"+
        				  "<li><a href='#'>{{breadcrumbs()}}</a></li><li><a href='#'></a></li></ul></div></div>"	
			
			return {		
			replace: true,
			restrict : 'EA',
			template : breadcrumbs,
			controller : 'serpicsController'
		};
	};
	

	/**
	 * 
	 */
	var leftSidebarDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/left-sidebar.html",			
			controller : 'serpicsController'
		};
	};

	/**
	 * 
	 */
	var featuresDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/features.html",			
			controller : 'serpicsController'
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
	}

	/**
	 * 
	 */
	var productDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/product-home.html",			
			controller : 'serpicsController'
		}
	}

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
	var recommendedDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/recommended.html',
			controller : 'serpicsController'
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

	
	var app = angular.module('serpicsDirective', [])
	.directive('loaderDirective', 		loaderDirective)
	.directive('topHeaderDirective',	topHeaderDirective)
	.directive('middleHeaderDirective',	middleHeaderDirective)
	.directive('bottomHeaderDirective',	bottomHeaderDirective)
	.directive('slideDirective', 		slideDirective)
	.directive('breadCrumbsDirective', 	breadCrumbsDirective)	
	.directive('leftSidebarDirective', 	leftSidebarDirective)
	.directive('featuresDirective', 	featuresDirective)
	.directive('productDirective', 		productDirective)
	.directive('cartDirective', 		cartDirective)
	.directive('orderDirective',		orderDirective)
	.directive('loginDirective', 		loginDirective)
	.directive('registerDirective', 	registerDirective)
	.directive('recommendedDirective', 	recommendedDirective)
	.directive('footerDirective', 		footerDirective)
	
}());
