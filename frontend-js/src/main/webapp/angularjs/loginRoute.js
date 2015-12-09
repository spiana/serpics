(function () {

    'use strict';


    angular

        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];

    function config($routeProvider, $locationProvider) {

        $routeProvider
            
            .when('/login', {

                controller: 'customerController',
                templateUrl: 'html/template/login.html',
                controllerAs: 'login'

            })


            .when('/register', {

                controller: 'customerController',
                templateUrl: 'html/template/login.html',
                controllerAs: 'register'

            })

            .otherwise({ redirectTo: '/login' });

    }


    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];

    function run($rootScope, $location, $cookieStore, $http) {

        // keep user logged in after page refresh

        $rootScope.globals = $cookieStore.get('globals') || {};//from AuthenticationService

       
        $rootScope.$on('$locationChangeStart', function (event, next, current) {

            // redirect to login page if not logged in and trying to access a restricted page

            var redirect = $.inArray($location.path(), ['/login', '/register']) === -1;

            var loggedIn = $rootScope.globals.currentUser;

            if (redirect && !loggedIn) {

                $location.path('/login');//the path at this moment is not present

            }

        });

    }


})();

                

