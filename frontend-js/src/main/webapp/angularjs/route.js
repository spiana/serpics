var app = angular.module("serpicsController", ['ngCookies'])


app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'html/index.html',
        controller: 'serpicsController'
     })
    .when('/login', {
        templateUrl: 'login.html',
        controller: 'LoginController'
    })
    .when('/register', {
        templateUrl: 'register.html',
        controller: 'RegisterController'
      })     
   .when('/profile', {
       templateUrl: 'views/profile.html',
       controller: 'profileController'
    })
    .when('/category', {
       templateUrl: 'html/category.html',
       controller: 'categoryController'
    })
        .otherwise({
       redirectTo: '/login'
    });
}]);