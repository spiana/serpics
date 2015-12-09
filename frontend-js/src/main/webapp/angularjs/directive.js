/**
 * all directive for SerpicsApp
 */
(function() {

	/**
	 * 
	 */
	var loaderDirective = function() {
		var loader = 	"<div class='loading-container'><div class='loading'></div><div id='loading-text'>{{ loadingText }}</div></div>"				
			return {		
			replace: true,
			restrict : 'EA',
			template : loader,
		};
	};
	
	/**
	 * 
	 */
	var welcomeUserDirective = function() {
		var user = 	"<span class='current-user'>Welcome  <span ng-bind='currentUser'>  {{currentUser}}</span></span>"				
			return {		
			restrict : 'EA',
			template : user,
			controller	:	'customerController'
		};
	};

	/**
	 * 
	 */
	var topHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/top-header.html',
		};
	};

	
	var middleHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/middle-header.html',
		};
	};

	
	var bottomHeaderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/bottom-header.html',
		};
	};

	
	/**
	 * 
	 */
	var slideDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/slide.html',
		};
	};

	/**
	 * 
	 */
	var breadCrumbsDirective = function() {
		var breadcrumbs = "<div class='container'><div class='row'><ul class='breadcrumb'>"+
        				  "<li><a href='#'>{{breadcrumbs}}</a></li><li><a href='#'></a></li></ul></div></div>"	
			
			return {		
			replace: true,
			restrict : 'EA',
			template : breadcrumbs,
			
		};
	};
	
	/**
	 * 
	 */
	var leftSidebarDirective = function() {
		return {
			restrict 	:	'EA',
			templateUrl :	"html/template/left-sidebar.html",
			controller	:	'categoryController'
		};
	};

		
	/**
	 * 
	 */
	var featuresDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/features.html",			
		};
	};
	
	/**
	 * 
	 */
	var brandDirective = function() {
		return {
			restrict 	: 'EA',
			templateUrl : 'html/template/brand-menu.html',
			controller 	: 'brandController'

		};
	}

	/**
	 * 
	 */
	var productDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/product-home.html",			
		}
	}

	/**
	 * 
	 */
	var cartDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/.html'
		};
	};

	/**
	 * 
	 */
	var orderDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/.html'

		};
	};

	/**
	 * 
	 */
	var loginDirective = function() {
		return {
			restrict : 'EA',
			template : "<form action='#'><input type='text' placeholder='Name' name='username' />" +
						"<input type='password' name='password'	placeholder='password' />"+
						"<span><input	type='checkbox' class='checkbox'> Keep me signed in	</span>"+
						"<button type='submit' class='btn btn-default'>Login</button></form>"

		};
	};

	/**
	 * 
	 */
	var recommendedDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/recommended.html',
		};
	};
	
	/**
	 * 
	 */
	var registerDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/.html'
		};
	};

	/**
	 * 
	 */
	var footerDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : 'html/template/footer.html',			
		};
	};
	
	var singleProductDirective = function() {
		return {
			restrict: 'EA',
			templateUrl: 'html/template/single-product.html',
			controller: 'productController',
		}
	}
	
	var detailProductDirective = function() {
		return {
			restrict: 'EA',
			templateUrl: 'html/template/detail-product.html',
			controller: 'productController',
		}
	}

	
	var app = angular.module('serpics.directive', [])	
	.directive('welcomeUserDirective', 	welcomeUserDirective)
	.directive('loaderDirective', 		loaderDirective)
	.directive('topHeaderDirective',	topHeaderDirective)
	.directive('middleHeaderDirective',	middleHeaderDirective)
	.directive('bottomHeaderDirective',	bottomHeaderDirective)
	.directive('slideDirective', 		slideDirective)
	.directive('breadCrumbsDirective', 	breadCrumbsDirective)	
	.directive('brandDirective', 		brandDirective)
	.directive('leftSidebarDirective', 	leftSidebarDirective)
	.directive('featuresDirective', 	featuresDirective)
	.directive('productDirective', 		productDirective)
	.directive('cartDirective', 		cartDirective)
	.directive('orderDirective',		orderDirective)
	.directive('loginDirective', 		loginDirective)
	.directive('registerDirective', 	registerDirective)
	.directive('recommendedDirective', 	recommendedDirective)
	.directive('footerDirective', 		footerDirective)
	.directive('singleProductDirective', 		singleProductDirective)	
	.directive('detailProductDirective', 		detailProductDirective)	
	
	
}());
