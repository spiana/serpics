var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(function($stateProvider, $urlRouterProvider,$locationProvider) {
	
	 
	
    $urlRouterProvider.otherwise(function ($injector, $location) {
    
        //what this function returns will be set as the $location.url
         var path = $location.path(), normalized = path.toLowerCase();
         if (path != normalized) {
             //instead of returning a new url string, I'll just change the $location.path directly so I don't have to worry about constructing a new url string and so a new state change is not triggered
             $location.replace().path(normalized);
         }
         $locationProvider.html5Mode({
        	    enabled: true,
        	  });
     });
   
   
    $stateProvider
    
     .state('home', {
    	url: '/',
        controller: function ($scope) {
        	$scope.name = 'Home Page';
        },
        templateUrl: 'html/template/home-central.html'

    })
    
    .state('category', {
    	url: 'category/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.categoryId = $stateParams.id;
        },
        templateUrl: 'html/template/category-central.html'

    })
    
    .state('brand', {
    	url: 'brand/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.brandId = $stateParams.id;
        },
        templateUrl: 'html/template/brand-central.html'
        
    })    

    .state('product', {
    	url: 'product/:name/:id',
        controller: function ($stateParams, $scope) {
        	$scope.name = $stateParams.name;
        	$scope.productId = $stateParams.id;
        },
        templateUrl: 'html/template/product-central.html'
    })
    
    .state('login', {
    	url: 'login/',       
        templateUrl: 'html/template/login.html'
    })
    
    .state('register', {
    	url: 'register/',        
        templateUrl: 'html/template/register.html'
    })
 
    });
    
