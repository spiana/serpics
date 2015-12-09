var routerApp = angular.module('serpics.router', ['ui.router'])

routerApp.config(function($stateProvider, $urlRouterProvider) {
	
    $urlRouterProvider.otherwise('/');

	
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
        controller:  function ($stateParams, $scope) {
        	
        },
        templateUrl: 'html/template/login.html'
    })
    
    .state('register', {
    	url: 'register/',
        controller: function ($stateParams, $scope) {
        	
        },
        templateUrl: 'html/template/register.html'
    })
    });
    
