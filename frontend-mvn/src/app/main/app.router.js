var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(['$stateProvider', '$urlRouterProvider','$locationProvider','$httpProvider', function($stateProvider, $urlRouterProvider,$locationProvider,$httpProvider) {
	
    $stateProvider
    
    .state('shop', {
    	abstract: true,
    	url: '',
        templateUrl: 'app/template/shop.html'

    })
    
     .state('shop.home', {
    	url: '/home',
        controller: function ($scope) {
        	$scope.name = 'Home Page';
        },
        templateUrl: 'app/template/home-central.html'
    })
    
    .state('shop.category', {
    	url: '/category/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.categoryId = $stateParams.id;
        },
        templateUrl: 'app/template/category-central.html'

    })
    
    .state('shop.brand', {
    	url: '/brand/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.brandId = $stateParams.id;
        },
        templateUrl: 'app/template/brand-central.html'
        
    })
    
    .state('shop.search', {
    	url: "/search?:textSearch",
        controller: function ($stateParams, $scope) {
        	$scope.name = 'Search Page';
        	$scope.textSearch = $stateParams.textSearch;
        },
        templateUrl: 'app/template/search-central.html'
        
    })  

    .state('shop.product', {
    	url: '/product/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.productId = $stateParams.id;
        },
        templateUrl: 'app/template/product-central.html'
    })
    
    .state('shop.login', {
    	url: '/login', 
        templateUrl: 'app/template/login.html',   
        controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        },
	 })
    
    .state('shop.cart', {
    	url: '/cart',       
        templateUrl: 'app/template/cart.html',
        controller: 'cartController',
    })

	.state('shop.register', {
		url: '/register',
		templateUrl: 'app/template/register.html',
		controller: 'loginController',
        params: {
        	login: "shop.home",
        	register: "shop.login"
        }
	})
	
	.state('shop.personalArea', {
		url: '/personalArea',
		templateUrl: 'app/template/personalArea.html',
		controller: 'customerController'	
	})
	
	.state('shop.500', {
		url: '/500',
		templateUrl: 'app/template/500.html'
		
	})
	
	.state('shop.404', {
	   	url: '/404',	        
	    templateUrl: 'app/template/404.html'
	})
	
	.state('shop.403', {
	   	url: '/403',	        
	    templateUrl: 'app/template/403.html'
	})	
		
	.state('shop.orderError', {    	
	   	url: '/orderError',	        
	    templateUrl: 'app/template/orderError.html', 
	})
	
	.state('shop.orderConfirm', {
	   	url: '/orderConfirm',	        
	    templateUrl: 'app/template/orderConfirm.html', 
	})
	    	
	.state('checkout', {
    	abstract: true,
	   	url: '/checkout',	        
	    templateUrl: 'app/template/checkout.html', 
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
	    templateUrl: 'app/template/checkoutAddress.html',
	})
	
	.state('checkout.shipping', {
	   	url: '/shipping',	        
	    templateUrl: 'app/template/checkoutShippingAddress.html',
	})
	
	.state('checkout.shipmode', {
	   	url: '/shipmode',	        
	    templateUrl: 'app/template/checkoutShipmode.html',
	})
	
	.state('checkout.payment', {
	   	url: '/payment?token',	        
	    templateUrl: 'app/template/checkoutPayment.html',
	})
	
	.state('checkout.paymentPayPal', {
	   	url: '/paymentPayPal',	        
	    templateUrl: 'app/template/checkoutPaymentPayPal.html'
	})
	
	.state('checkout.login', {
	   	url: '/login',	        
	    templateUrl: 'app/template/login.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('checkout.register', {
	   	url: '/register',	        
	    templateUrl: 'app/template/register.html',
	    controller: 'loginController',
        params: {
        	login: "checkout.address",
        	register: "checkout.login"
        }
	})
	
	.state('complete' , {
		url: '/complete',
		templateUrl: 'app/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
 		
 	.state('paid' , {
		url: '/paid?paymentId&token&PayerID',
		templateUrl: 'app/template/orderComplete.html',
		controller: 'orderController'
 	})
 	
 	.state('cancel', {
		url: '/cancel',
		templateUrl: 'app/template/checkoutPayment.html'

	})
 	
	$urlRouterProvider.otherwise("/home");
	    
}])
