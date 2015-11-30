var app = angular.module("serpicsController", ['ngCookies'])


app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'html/index.html',
        controller: 'serpicsController'
     })
    .when('/login', {
        templateUrl: 'html/login.html',
        controller: 'LoginController'
    })      
   .when('/contact', {
       templateUrl: 'views/contact-us.html',
       controller: 'profileController'
    })
    .when('/category', {
       templateUrl: 'html/category.html',
       controller: 'categoryController'
    })
     .when('/product-details.html', {
       templateUrl: 'html/category.html',
       controller: 'productController'
    })
        .otherwise({
       redirectTo: '/'
    });
}]);