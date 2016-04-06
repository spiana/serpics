 var app = angular.module("category.controller", ['ngCookies','category.service'])

 /** categoryController **/
.controller("categoryController",['$scope','categoryService','$log', 
                                     
         function($scope,categoryService,$log) {
      	
		 	$scope.categoryData 	= getTopQ();;
		 	//auxiliary var
		 	var cache = {
		 			isAdded:''
		 	};
		 	
	 		
//			getTop();
//		 	getTopQ();

			 /** implemented category service **/ 
			
     	    /**
     	     * @return 					all category pather
     	     * @use 					categoryService,
     	     */
		 	function getTop(){	
		 		$log.debug("Category Controller getTop()");
                 	categoryService.getTop().then( function( response ) {
                 		$log.debug("Category Controller getTop() ramo then");
                 		$scope.categoryData 	= response.data;                  	
                 })
     	    };
     	    

     	    /**
     	     * @return 					all top category
     	     * @use 					categoryService,
     	     */

     	    function getTopQ(){
     	    	$log.debug("Category Controller getTopQ()");
     	    	categoryService.getTopQ().then(function(response){
     	    		$log.debug("Category Controller getTopQ() ramo then");
     	    		$scope.categoryData = response
     	    	})
     	    		
     	    };
     	    
     	    /**
     	     * @param categoryId 			category id to be retrive
     	     * @return 						a category by id
     	     * @use 						categoryService,
     	     */
     	    $scope.getCategoryById = function(categoryId) {
     	    	$log.debug("Category Controller getCategoryById(categoryId)"+categoryId);
     	    	categoryService.getCategoryById(categoryId).then(function(response){
     	    		$log.debug("Category Controller getCategoryById(categoryId) ramo then");
     	    		$scope.categoryData = response;
     	    	})
    	    };
     	    
     	    /**
     	     * @param code					code category
     	     * @return 						a category by code
     	     * @use 						categoryService,
     	     */
     	    $scope.getCategoryByCode = function(code) {
    	    	$log.debug("Category Controller getCategoryByCode(code)"+code);
     	    	categoryService.getCategoryByCode(code).then(function(response){
     	    		$log.debug("Category Controller getCategoryByCode(code) ramo then");
     	    		$scope.categoryData = response;
     	    	})
     	    };
     	    
     	    /**
     	     * @param parentId 				a parent id category
     	     * @param index 				index of categoryDataArray
     	     * @param category 				category
     	     * @return 						all category child
     	     * @use 						categoryService,
     	     */
     	    
     	   $scope.getChild = function(parentId,index,category){
     		   category.active=!category.active;
     		   $log.debug("Category Controller getChild(parentId,index,category) Category index: "+index);
     		   if(cache.isAdded.indexOf(parentId)!=-1){
     			   $log.debug("Request gia' effettuata: "+" Collapsed: "+category.active+" Category Added: "+cache.isAdded);
     			   }else{
     				   categoryService.getChild(parentId).then(function(response){
     					   $log.debug("getChild(parentId,index,category) ramo then");
     					   cache.isAdded += '#' + parentId;
     					   $scope.categoryData[index].subCategory=response;
     					   $log.debug("Request effettuata "+$scope.categoryData[index].active);
     				   });
     			   }
     	    };
     	    
     	    /**
     	     * @return 						all category 
     	     * @use 						categoryService,
     	     */
     	    $scope.findAll = function(page,size) {
     	    	$log.debug("Category Controller findAll()");
     	    	categoryService.findAll(page,size).then(function(response){
     	    		$log.debug("Category Controller findAll() ramo then");
     	    		$scope.categoryData = response;
     	    	})
     	    };
 }]);
 


