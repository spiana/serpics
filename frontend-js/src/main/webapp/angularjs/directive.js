/**
 * all directive for SerpicsApp
 */
(function () {

    var brandDirective = function () {
        return {
        	
        	restrict: 'EA',
            templateUrl: 'html/brand.html'

        };
    };
    
    var categoryDirective = function () {
        return {
        	
        	restrict: 'EA',
            templateUrl: 'html/category.html'

        };
    };

    var app = angular.module('serpicsDirective', [ ]);
    app.directive('brandDirective', brandDirective);
    app.directive('categoryDirective', categoryDirective);

}());
