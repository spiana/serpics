/**
 * all directive for SerpicsApp
 */
(function() {

	/**
	 * 
	 */
	var loaderDirective = function() {
		var loader = 	"<div class='loading-container'><div class='loading'></div><div id='loading-text'>{{ loadText }}</div></div>"				
			return {		
			replace: true,
			restrict : 'EA',
			template : loader,
			scope: {
				loadText: '@textLoading'
			},
			link: function($scope, $elem, attrs){
		           $(window).load(function() {
		        	   $(".loading-container").fadeOut(3000);
		       		   $(".loading,loading-text").delay(100).fadeOut("slow");
		           });
		       }
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
        				  "<li><a href='#'>Home</a></li><li><a href='#'></a></li></ul></div></div>"	
			
			return {		
			replace: true,
			restrict : 'EA',
			template : breadcrumbs,
			
		};
	};
	
	/**
	 * 
	 */
	var modalDirective = function() {
		return {
			restrict 	:	'EA',
			templateUrl :	"html/template/modal.html",	
			templateUrl :	"html/template/modal.html",
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
	var productListDirective = function() {
		return {
			restrict : 'EA',
			templateUrl : "html/template/product-list.html",
			controller: 'productController',
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
			templateUrl : 'html/template/register.html',
			controller:'loginController'
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
		}
	}
	
	var detailProductDirective = function() {
		return {
			restrict: 'EA',
			templateUrl: 'html/template/detail-product.html',
			controller: 'productController',
		}
	}

	
	var app = angular.module('serpics.directive', ['serpics.config'])		
	.directive('loaderDirective', 		loaderDirective)
	.directive('modalDirective', 		modalDirective)
	.directive('topHeaderDirective',	topHeaderDirective)
	.directive('middleHeaderDirective',	middleHeaderDirective)
	.directive('bottomHeaderDirective',	bottomHeaderDirective)
	.directive('slideDirective', 		slideDirective)
	.directive('breadCrumbsDirective', 	breadCrumbsDirective)	
	.directive('brandDirective', 		brandDirective)
	.directive('leftSidebarDirective', 	leftSidebarDirective)
	.directive('productListDirective', 	productListDirective)
	.directive('cartDirective', 		cartDirective)
	.directive('orderDirective',		orderDirective)
	.directive('loginDirective', 		loginDirective)
	.directive('registerDirective', 	registerDirective)
	.directive('recommendedDirective', 	recommendedDirective)
	.directive('footerDirective', 		footerDirective)
	.directive('singleProductDirective',singleProductDirective)	
	.directive('detailProductDirective',detailProductDirective)	
	
	
}());
