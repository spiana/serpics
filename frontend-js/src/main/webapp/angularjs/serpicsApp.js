var app = angular.module("serpics.App", ['serpics.Services','category.controller','brand.controller','product.controller','serpics.directive','serpics.config'])

app.controller("serpicsAppController",['$scope','TITLE','BREADCRUMBS','LOADINGTEXT',
                                     
     function($scope,TITLE,BREADCRUMBS,LOADINGTEXT) {	
  	
			$scope.title 		= TITLE;
			$scope.loadingText 		= LOADINGTEXT;     
			$scope.breadcrumbs 	= BREADCRUMBS;

 }])