 var app = angular.module("category.controller", ['ngCookies','category.service'])

 /** categoryController **/
.controller("categoryController",['$scope','categoryService', 
                                     
         function($scope,categoryService) {
      	
		 	$scope.categoryData 	= [];
		 	$scope.subCategory		= [];
		 	$scope.bool = false;
		 	//auxiliary var
		 	var cache = {
		 			category:[],		 			
		 			bool:null,
		 			isAdded:''
		 	};
		 	
	 		
//			getTop();
		 	getTopQ();

			 /** implemented category service **/ 
			
     	    /**
     	     * @param sessionId 		a sessionId
     	     * @return 					all category pather
     	     * @use 					categoryService,
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
     	    		
     	    		for(var json in response){
     	    			if(response[json].childCategoryNumber)
     	    				cache.category.push(response[json])
     	    				}
     	    		$scope.categoryData = cache.category;
     	    	})
     	    		
     	    };
     	    
     	    
     	    /**
     	     * @param endpoint 		    web service rest endpoint
     	     * @param sessionId 		a sessionId
     	     * @param data 				data to send
     	     * @return 					new category
     	     * @use 					categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
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
     	     * @use 						categoryService,
     	     */
     	    
     	   $scope.getChild = function(parentId){
     	    	console.log("getChild");
     	    	if(cache.isAdded.indexOf(parentId)!=-1){
     	    		return;
     	    	}else{
     	    	categoryService.getChild(parentId).then(function(response){
     	    		console.log("getChild ramo then");
     	    		$scope.subCategory = response;
     	    		cache.isAdded += '#' + parentId
     	    	})
     	     }
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
     	     * @use 						categoryService,
     	     */
     	    $scope.findAll = function(endpoint) {		
     	       	categoryService.findAll(endpoint,$rootScope.sessionId).then( function( response ) {
     	       		/** do stuff with response **/
                 })
     	    };
 }])

