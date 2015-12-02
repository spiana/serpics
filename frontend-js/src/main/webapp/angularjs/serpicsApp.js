var app = angular.module("serpicsRestAPP", ['serpicsService','serpicsController','serpicsDirective','serpics.config'])

app.controller("serpicsController",['$scope','$rootScope','$cookies','authManagerService','$timeout','categoryService',
                                     
     function($scope,$rootScope,$cookies,authManagerService,$timeout,categoryService) {	
  	
			$scope.endpoint   	= 'http://localhost:8080/jax-rs/auth/connect/default-store' 
			$scope.title 		= "Serpics Platform Ecommerce";
			$scope.loadingText 	= "Serpics"     
			$scope.breadcrumbs 	= "Home"
			$scope.sessionId 	= ''
			$scope 				= $rootScope
	    /**
         * function to create the sessiion id , opening appliazione
         * session id is created and added to a hidden field , if we
         * were to recover . For each call a check is made on cookies 
         * and if there is regenerated. The hidden field is automatically
         * updated through bind with the directive ng-model
         */ 
			$scope.createSessionId = function(){
            if($cookies.get('ssid')){
            	$scope.sessionId = $cookies.get('ssid')  
                 console.log('Serpics Controller: read session id from cookie ->' + $scope.sessionId)
            }else {
                authManagerService.getSessionId($scope.endpoint).then(
                    function( response ) {                      
                    	$scope.sessionId = response   
                        console.log('Serpics Controller: create a new session -> ' + $scope.sessionId)                        
                })
            }          
        }   			
			
 }])