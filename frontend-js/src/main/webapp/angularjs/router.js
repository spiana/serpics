var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(function($stateProvider, $urlRouterProvider,$locationProvider) {
	
    $stateProvider
    
     .state('home', {
    	url: '/',
        controller: function ($scope) {
        	$scope.name = 'Home Page';
        },
        templateUrl: 'html/template/home-central.html'

    })
    
    .state('category', {
    	url: '/category/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.categoryId = $stateParams.id;
        },
        templateUrl: 'html/template/category-central.html'

    })
    
    .state('brand', {
    	url: '/brand/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.brandId = $stateParams.id;
        },
        templateUrl: 'html/template/brand-central.html'
        
    })    

    .state('product', {
    	url: '/product/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.productId = $stateParams.id;
        },
        templateUrl: 'html/template/product-central.html'
    })
    
    .state('login', {
    	url: '/login', 
        templateUrl: 'html/template/login.html' ,        	 
        controller: function ($rootScope, $location, $cookieStore) {        	
            $rootScope.globals = $cookieStore.get('globals') || {};// keep user logged in after page refresh
            if ($rootScope.globals.currentUser) {            
            	$location.path('/logout'); 
            } else {
            	$rootScope.$on('$locationChangeStart', function (event, next, current) {                   
                    if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {  // redirect to login page if not logged in
                        $location.path('/login');                       
                    }
	                });
	            }
	         }
	    })
    
    .state('logout', {
    	url: '/logout',        
        templateUrl: 'html/template/home-central.html',
        controller: function ($rootScope, $cookieStore) {
        	$rootScope.message = 'Guest Access' 
            $cookieStore.remove('globals');
            $cookieStore.remove('isLoggedIn');
        	$rootScope.action = {
					actionName:'Login',
					actionClass:'fa fa-lock',
					dropMenuClass:'hidden'
				}
	        }
	    })
    
    .state('cart', {
    	url: '/cart/',       
        templateUrl: 'html/template/cart.html',
        controller: 'cartController',
    })

	.state('register', {
	    	url: '/register',	        
	        templateUrl: 'html/template/register.html'
	})
	
	$urlRouterProvider.otherwise("/");
	    
})
   
    
