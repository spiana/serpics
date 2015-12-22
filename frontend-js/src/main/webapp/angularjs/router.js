var routerApp = angular.module('serpics.router', ['ui.router','customer.service'])

routerApp.config(function($stateProvider, $urlRouterProvider,$locationProvider,$httpProvider) {
	
    $stateProvider
    
    .state('shop', {
    	abstract: true,
    	url: '',
        templateUrl: 'html/template/shop.html'

    })
    
     .state('shop.home', {
    	url: '/home',
        controller: function ($scope) {
        	$scope.name = 'Home Page';
        },
        templateUrl: 'html/template/home-central.html'
    })
    
    .state('shop.category', {
    	url: '/category/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.categoryId = $stateParams.id;
        },
        templateUrl: 'html/template/category-central.html'

    })
    
    .state('shop.brand', {
    	url: '/brand/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.brandId = $stateParams.id;
        },
        templateUrl: 'html/template/brand-central.html'
        
    })    

    .state('shop.product', {
    	url: '/product/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.productId = $stateParams.id;
        },
        templateUrl: 'html/template/product-central.html'
    })
    
    .state('shop.login', {
    	url: '/login', 
        templateUrl: 'html/template/login.html',   
        controller: 'loginController'
	    })
    
    .state('shop.cart', {
    	url: '/cart/',       
        templateUrl: 'html/template/cart.html',
        controller: 'cartController',
    })

	.state('shop.register', {
		url: '/register',
		templateUrl: 'html/template/register.html'
			
		})
	
	.state('shop.500', {
		url: '/500',
		templateUrl: 'html/template/500.html'
		
	})
	
	.state('shop.404', {
	   	url: '/404',	        
	    templateUrl: 'html/template/404.html'
	})
	
	.state('shop.403', {
	   	url: '/403',	        
	    templateUrl: 'html/template/403.html'
	})
	    	
	.state('checkout', {
    	abstract: true,
	   	url: '/checkout',	        
	    templateUrl: 'html/template/checkout.html', 
	    controller: 'checkoutController'
	})
	
	.state('shop.orderError', {    	
	   	url: '/orderError',	        
	    templateUrl: 'html/template/orderError.html', 
	})
	
	.state('shop.orderConfirm', {
	   	url: '/orderConfirm',	        
	    templateUrl: 'html/template/orderConfirm.html', 
	})
	
	.state('checkout.address', {
	   	url: '/address',	        
	    templateUrl: 'html/template/checkoutAddress.html',
	})
	
	.state('checkout.login', {
	   	url: '/login',	        
	    templateUrl: 'html/template/login.html',
//	    controller: 'loginController'
	})
	
	.state('checkout.register', {
	   	url: '/register',	        
	    templateUrl: 'html/template/register.html',
//	    controller: 'loginController'
	})
	
	.state('complete' , {
		url: '/complete',
		templateUrl: 'html/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
	$urlRouterProvider.otherwise("/home");
	    
})
