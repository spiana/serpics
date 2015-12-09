var app = angular.module("serpics.App", ['serpics.Services','login.controller','category.controller','brand.controller','product.controller','order.controller','customer.controller','serpics.directive','serpics.config','serpics.router']);

app.controller("serpicsAppController",['$scope','TITLE','BREADCRUMBS','LOADINGTEXT',
                                     
     function($scope,TITLE,BREADCRUMBS,LOADINGTEXT) {	
  	
			$scope.title 		= TITLE;
			$scope.loadingText 	= LOADINGTEXT;     
			$scope.breadcrumbs 	= BREADCRUMBS;
			
}]);