var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(['$stateProvider', '$urlRouterProvider','$locationProvider','$httpProvider', function($stateProvider, $urlRouterProvider,$locationProvider,$httpProvider) {
	
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
    
    .state('shop.search', {
    	url: "/search?:textSearch",
        controller: function ($stateParams, $scope) {
        	$scope.name = 'Search Page';
        	$scope.textSearch = $stateParams.textSearch;
        },
        templateUrl: 'html/template/search-central.html'
        
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
        controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        },
	 })
    
    .state('shop.cart', {
    	url: '/cart',       
        templateUrl: 'html/template/cart.html',
        controller: 'cartController',
    })

	.state('shop.register', {
		url: '/register',
		templateUrl: 'html/template/register.html',
		controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        }
	})
	
	.state('shop.personalArea', {
		url: '/personalArea',
		templateUrl: 'html/template/personalArea.html',
		controller: 'customerController'	
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
		
	.state('shop.orderError', {    	
	   	url: '/orderError',	        
	    templateUrl: 'html/template/orderError.html', 
	})
	
	.state('shop.orderConfirm', {
	   	url: '/orderConfirm',	        
	    templateUrl: 'html/template/orderConfirm.html', 
	})
	    	
	.state('checkout', {
    	abstract: true,
	   	url: '/checkout',	        
	    templateUrl: 'html/template/checkout.html', 
	    controller: 'cartController',
        params: {
        	complete: "complete",
        	shipping: "checkout.shipping",
        	shipmode: "checkout.shipmode",
        	payment: "checkout.payment",
        	paymentPayPal: "checkout.paymentPayPal"
        }
	})
	
	.state('checkout.address', {
	   	url: '/address',	        
	    templateUrl: 'html/template/checkoutAddress.html',
	})
	
	.state('checkout.shipping', {
	   	url: '/shipping',	        
	    templateUrl: 'html/template/checkoutShippingAddress.html',
	})
	
	.state('checkout.shipmode', {
	   	url: '/shipmode',	        
	    templateUrl: 'html/template/checkoutShipmode.html',
	})
	
	.state('checkout.payment', {
	   	url: '/payment?token',	        
	    templateUrl: 'html/template/checkoutPayment.html',
	})
	
	.state('checkout.paymentPayPal', {
	   	url: '/paymentPayPal',	        
	    templateUrl: 'html/template/checkoutPaymentPayPal.html'
	})
	
	.state('checkout.login', {
	   	url: '/login',	        
	    templateUrl: 'html/template/login.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('checkout.register', {
	   	url: '/register',	        
	    templateUrl: 'html/template/register.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('complete' , {
		url: '/complete',
		templateUrl: 'html/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
 		
 	.state('paid' , {
		url: '/paid?paymentId&token&PayerID',
		templateUrl: 'html/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
 	.state('cancel', {
		url: '/cancel',
		templateUrl: 'html/template/checkoutPayment.html'

	})
 	
	$urlRouterProvider.otherwise("/home");
	    
}])
