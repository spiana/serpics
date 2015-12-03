 var app = angular.module("category.controller", ['ngCookies','category.Service'])

 /** categoryController **/
.controller("categoryController",['$scope','$rootScope','$q','$cookies','authManagerService','categoryService','$timeout', 
                                     
         function($scope,$rootScope,$q,$cookies,authManagerService,categoryService,$timeout) {	
      	
			var deferred 			= $q.defer();
		 	$scope.categoryData 	= [];
		 	
	 		
//			getTop();
		 	getTopQ();

			 /** implemented category service **/ 
			
     	    /**
     	     * @param sessionId 		a sessionId
     	     * @return 					all category pather
     	     * @use 					categoryService,authManagerService
     	     */
		 	 function getTop(){	
					console.log("Category Controller: session id for top method:-> ");

                 	categoryService.getTop(endpoint).then( function( response ) {
                 	$scope.categoryData 	= response.data;                  	
                 })
     	    };
     	    function getTopQ(){
     	    	console.log("topQ");
     	    	categoryService.getTopQ().then(function(response){
     	    		console.log("topQ ramo then");
     	    		$scope.categoryData = response;
     	    	})
     	    };
     	    
     	    
     	    /**
     	     * @param endpoint 		    web service rest endpoint
     	     * @param sessionId 		a sessionId
     	     * @param data 				data to send
     	     * @return 					new category
     	     * @use 					categoryService,authManagerService
     	     */
     	    $scope.create = function(endpoint,data) {
     	       	categoryService.create(endpoint,$rootScope.sessionId, data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param parentId 				a parent category
     	     * @param data 					data to send
     	     * @return 						add parent to category
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.createParent = function(endpoint,parentId,data) {
     	       	categoryService.createParent(endpoint,$rootScope.sessionId, parentId , data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param childId 				a session id
     	     * @param parentId 				a parent id 
     	     * @param data 					data to send
     	     * @return 						add parent to category of id @param childId
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.addParent = function(endpoint, childId,parentId,data) {
     	    	$rootScope.createSessionId()
     	       	categoryService.addParent(endpoint,$rootScope.sessionId, childId,parentId,data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param data 					data to send
     	     * @return 						a category updated with @data params
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.updateCategory = function(endpoint,data) {
     	    	$rootScope.createSessionId()
     	       	categoryService.updateCategory(endpoint,$rootScope.sessionId, data ).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param categoryId 			a category id
     	     * @return delete 				a category with id @categoryId
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.deleteCategory = function(endpoint,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.deleteCategory(endpoint,$rootScope.sessionId,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param categoryId 			category id to be retrive
     	     * @return 						a category by id
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getCategoryById = function(endpoint,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.getCategoryById(endpoint,$rootScope.sessionId,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param code					code category
     	     * @param categoryId 			category id to retrieve
     	     * @return 						a category by code
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.getCategoryByCode = function(endpoint,code,categoryId) {
     	    	$rootScope.createSessionId()
     	       	categoryService.getCategoryByCode(endpoint,$rootScope.sessionId,code,categoryId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @param parentId 				a parent id category
     	     * @return 						all category child
     	     * @use 						categoryService,authManagerService
     	     */
     	    
     	    function getChild(parentId){
     	    	console.log("getChild");
     	    	categoryService.getChild(parentId).then(function(response){
     	    		console.log("getChild ramo then");
     	    		$scope.categoryData = response;
     	    	})
     	    };
//     	    $scope.getChild = function(parentId) {
//     	       	categoryService.getChild(endpoint,$rootScope.sessionId,x).then( function( response ) {
//     	       		/** do stuff with response **/
//                 })
//     	    };
     	    
     	    /**
     	     * @param endpoint 		    	web service rest endpoint
     	     * @param sessionId 			a sessionId
     	     * @return 						all category 
     	     * @use 						categoryService,authManagerService
     	     */
     	    $scope.findAll = function(endpoint) {		
     	       	categoryService.findAll(endpoint,$rootScope.sessionId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
 }])

