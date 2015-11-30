var app = angular.module("serpicsRestAPP", ['serpicsService','serpicsController','serpicsDirective'])

app.controller("serpicsController",['$scope','$rootScope','$cookies','authManagerService','$timeout', 
                                     
     function($scope,$rootScope,$cookies,authManagerService,$timeout) {	
  	
	      endpoint   		 = 'http://localhost:8080/jax-rs/auth/connect/default-store'    
	  	  $scope.title 		 = "Serpics Platform Ecommerce";
	  	  $scope.loadingText = "Serpics";

	     
	  	
	    /**
         * function to create the sessiion id , opening appliazione
         * session id is created and added to a hidden field , if we
         * were to recover . For each call a check is made on cookies 
         * and if there is regenerated. The hidden field is automatically
         * updated through bind with the directive ng-model
         */ 
	  	$rootScope.createSessionId = function(){
            if($cookies.get('ssid')){
            	$rootScope.sessionId = $cookies.get('ssid')  
                 console.log('read session id from cookie not connect executed')
            }else {
                authManagerService.getSessionId(endpoint).then(
                    function( response ) {                      
                    	$rootScope.sessionId = response   
                        console.log('create a new session id from SerpicsCtrl connect executed')                        
                })
            }          
        }       

	  	 /** 
	  	  * function to monitor the session id , is generated when 
	  	  * a new session id , the variable of the model page will 
	  	  * call this function that will only log the new session id
	  	  */ 
	  	
	  	/** create session id on view content load **/
	  	$timeout($rootScope.createSessionId)
       
}])


