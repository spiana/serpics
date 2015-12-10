var app = angular.module("serpics.App", ['serpics.Services','category.controller','brand.controller','product.controller','order.controller','login.controller','cart.controller','serpics.directive','serpics.config','serpics.router']);

app.controller("serpicsAppController",['$scope','TITLE','BREADCRUMBS','LOADINGTEXT',
                                     
     function($scope,TITLE,BREADCRUMBS,LOADINGTEXT) {	
  	
			$scope.title 		= TITLE;
			$scope.loadingText 	= LOADINGTEXT;     
			$scope.breadcrumbs 	= BREADCRUMBS;
			
}]);