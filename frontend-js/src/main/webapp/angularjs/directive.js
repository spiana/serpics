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
	
	var actionUserDirective = function() {
		var user = 	"<a ui-sref='login'><i class='{{action.actionClass}}'></i> {{action.actionName}}</a>"				
			return {		
			restrict : 'EA',
			template : user,
			controller	:'loginController'
		};
	};
	
	/**
	 * 
	 */
	var welcomeUserDirective = function() {
		var user = 	"<span class='current-user'>Welcome  <span ng-bind='globals.currentUser.username'>  {{globals.currentUser.username}}</span></span>"				
			return {		
			restrict : 'EA',
			template : user,
			controller	:'loginController'
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
			templateUrl : 'html/template/login.html',
			controller:'loginController'
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
	.directive('actionUserDirective', 	actionUserDirective)
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
