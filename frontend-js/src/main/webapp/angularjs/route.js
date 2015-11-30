var app = angular.module("serpicsRoute", ['ngRoute'])


app.config(['$routeProvider',
  function($routeProvider) {
	$routeProvider.when('/view1',{
	    controller:'categoryController',
	    templateUrl:'html/template/left-sidebar.tpl' // The ng-template id
	})
}]);

