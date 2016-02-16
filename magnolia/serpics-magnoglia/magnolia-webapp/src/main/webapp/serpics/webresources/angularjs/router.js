var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(['$stateProvider', '$urlRouterProvider','$locationProvider','$httpProvider', function($stateProvider, $urlRouterProvider,$locationProvider,$httpProvider) {
	
    $stateProvider
    
    .state('shop', {
    	abstract: true,
    	url: '',
        templateUrl: '.resources/serpics/webresources/html/template/shop.html'

    })
    
     .state('shop.home', {
    	url: '/home',
        controller: function ($scope) {
        	$scope.name = 'Home Page';
        },
        templateUrl: '.resources/serpics/webresources/html/template/home-central.html'
    })
    
    .state('shop.category', {
    	url: '/category/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.categoryId = $stateParams.id;
        },
        templateUrl: '.resources/serpics/webresources/html/template/category-central.html'

    })
    
    .state('shop.brand', {
    	url: '/brand/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.brandId = $stateParams.id;
        },
        templateUrl: '.resources/serpics/webresources/html/template/brand-central.html'
        
    })
    
    .state('shop.search', {
    	url: "/search?:textSearch",
        controller: function ($stateParams, $scope) {
        	$scope.name = 'Search Page';
        	$scope.textSearch = $stateParams.textSearch;
        },
        templateUrl: '.resources/serpics/webresources/html/template/search-central.html'
        
    })  

    .state('shop.product', {
    	url: '/product/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.productId = $stateParams.id;
        },
        templateUrl: '.resources/serpics/webresources/html/template/product-central.html'
    })
    
    .state('shop.login', {
    	url: '/login', 
        templateUrl: '.resources/serpics/webresources/html/template/login.html',   
        controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        },
	 })
    
    .state('shop.cart', {
    	url: '/cart/',       
        templateUrl: '.resources/serpics/webresources/html/template/cart.html',
        controller: 'cartController',
    })

	.state('shop.register', {
		url: '/register',
		templateUrl: '.resources/serpics/webresources/html/template/register.html',
		controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        }
	})
	
	.state('shop.personalArea', {
		url: '/personalArea',
		templateUrl: '.resources/serpics/webresources/html/template/personalArea.html',
		controller: 'customerController'	
	})
	
	.state('shop.500', {
		url: '/500',
		templateUrl: '.resources/serpics/webresources/html/template/500.html'
		
	})
	
	.state('shop.404', {
	   	url: '/404',	        
	    templateUrl: '.resources/serpics/webresources/html/template/404.html'
	})
	
	.state('shop.403', {
	   	url: '/403',	        
	    templateUrl: '.resources/serpics/webresources/html/template/403.html'
	})	
		
	.state('shop.orderError', {    	
	   	url: '/orderError',	        
	    templateUrl: '.resources/serpics/webresources/html/template/orderError.html', 
	})
	
	.state('shop.orderConfirm', {
	   	url: '/orderConfirm',	        
	    templateUrl: '.resources/serpics/webresources/html/template/orderConfirm.html', 
	})
	    	
	.state('checkout', {
    	abstract: true,
	   	url: '/checkout',	        
	    templateUrl: '.resources/serpics/webresources/html/template/checkout.html', 
	    controller: 'cartController',
        params: {
        	complete: "complete",
        	shipping: "checkout.shipping",
        	shipmode: "checkout.shipmode",
        	payment: "checkout.payment"
        }
	})
	
	.state('checkout.address', {
	   	url: '/address',	        
	    templateUrl: '.resources/serpics/webresources/html/template/checkoutAddress.html',
	})
	
	.state('checkout.shipping', {
	   	url: '/shipping',	        
	    templateUrl: '.resources/serpics/webresources/html/template/checkoutShippingAddress.html',
	})
	
	.state('checkout.shipmode', {
	   	url: '/shipmode',	        
	    templateUrl: '.resources/serpics/webresources/html/template/checkoutShipmode.html',
	})
	
	.state('checkout.payment', {
	   	url: '/payment',	        
	    templateUrl: '.resources/serpics/webresources/html/template/checkoutPayment.html',
	})
	
	.state('checkout.login', {
	   	url: '/login',	        
	    templateUrl: '.resources/serpics/webresources/html/template/login.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('checkout.register', {
	   	url: '/register',	        
	    templateUrl: '.resources/serpics/webresources/html/template/register.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('complete' , {
		url: '/complete',
		templateUrl: '.resources/serpics/webresources/html/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
	$urlRouterProvider.otherwise("/home");
	    
}])
