var app = angular.module("serpics.App", ['serpics.Services','category.controller','brand.controller','serpicsDirective','serpics.config'])

app.controller("serpicsAppController",['$scope','$timeout','TITLE','BREADCRUMBS','LOADINGTEXT',
                                     
     function($scope,$timeout,TITLE,BREADCRUMBS,LOADINGTEXT) {	
  	
			$scope.title 		= TITLE;
			$scope.loadingText 		= LOADINGTEXT;     
			$scope.breadcrumbs 	= BREADCRUMBS;

 }])