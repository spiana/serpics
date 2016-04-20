(function() {
	'use strict';
	
	angular.module('serpics.directive', [])
	.directive('loaderDirective', 		loaderDirective)
	.directive('registerSuccessModalDirective', 		registerSuccessModalDirective)
	.directive('topHeaderDirective',	topHeaderDirective)
	.directive('middleHeaderDirective',	middleHeaderDirective)
	.directive('bottomHeaderDirective',	bottomHeaderDirective)
	.directive('slideDirective', 		slideDirective)
	.directive('breadCrumbsDirective', 	breadCrumbsDirective)
	.directive('brandDirective', 		brandDirective)
	.directive('leftSidebarDirective', 	leftSidebarDirective)
	.directive('productListDirective', 	productListDirective)
	.directive('loginDirective', 		loginDirective)
	.directive('registerDirective', 	registerDirective)
	.directive('recommendedDirective', 	recommendedDirective)
	.directive('footerDirective', 		footerDirective)
	.directive('singleProductDirective',singleProductDirective)
	.directive('detailProductDirective',detailProductDirective);
	
	/**
	 * LoaderDirective turnBalls
	 */
	function loaderDirective() {
		var loader = '<div class=\'loading-container\'><div class=\'loading\'></div>'+
			'<div id=\'loading-text\'>{{ loadText }}</div></div>';	
			return {		
			replace: true,
			restrict : 'EA',
			template : loader,
			scope: {
				loadText: '@textLoading'
			},
			link: function($scope, $element, $attrs){
				$(window).load(function() {
		        	   $(".loading-container").fadeOut(3000);
		       		   $(".loading,loading-text").delay(100).fadeOut("slow");
		           });
		       }
		};
	}
		
		
	/**
	 * 
	 */
	function topHeaderDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/top-header.html'
		};
	}

	
	function middleHeaderDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/middle-header.html'
		};
	}

	
	function bottomHeaderDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/bottom-header.html'
		};
	}

	
	/**
	 * 
	 */
	function slideDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/slide.html'
		};
	}

	/**
	 * 
	 */
	function breadCrumbsDirective() {			
			return {		
			replace: true,
			restrict : 'EA',
			templateUrl : 'app/template/breadcrumbs.html'	
		};
	}
	
	/**
	 * 
	 */
	function registerSuccessModalDirective() {
		return {
			restrict 	:	'EA',
			templateUrl :	'app/template/registerSuccessModal.html'
		};
	}
	
	
	/**
	 * 
	 */
	function leftSidebarDirective() {
		return {
			restrict 	:	'EA',
			templateUrl :	'app/template/left-sidebar.html',
			controller	:	'',
			controllerAs:   ''
		};
	}

		
	/**
	 * 
	 */
	function productListDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/product-list.html',
			controller: 'ProductController'
		};
	}
	
	/**
	 * 
	 */
	function brandDirective() {
		return {
			restrict 	: 'EA',
			templateUrl : 'app/template/brand-menu.html',
			controller 	: 'BrandController',
			controllerAs: 'brandVm'

		};
	}

	/**
	 * 
	 */
	function loginDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/login.html',
			controller:'LoginController'
		};
	}

	/**
	 * 
	 */
	function recommendedDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/recommended.html'
		};
	}
	
	/**
	 * 
	 */
	function registerDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/register.html',
			controller:'LoginController'
		};
	}

	/**
	 * 
	 */
	function footerDirective() {
		return {
			restrict : 'EA',
			templateUrl : 'app/template/footer.html'		
		};
	}
	
	function singleProductDirective() {
		return {
			restrict: 'EA',
			templateUrl: 'app/template/single-product.html'
		};
	}
	
	function detailProductDirective() {
		return {
			restrict: 'EA',
			templateUrl: 'app/template/detail-product.html',
			controller: 'ProductController'
		};
	}

	

	
}());
