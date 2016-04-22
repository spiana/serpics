 var app = angular.module("category.controller", ['category.service'])

 /** categoryController **/
.controller("categoryController",['$scope','categoryService','$log', 
                                     
         function($scope,categoryService,$log) {
	
      		$scope.category = {};
		 	$scope.categoryData = {};
		 	//auxiliary var
		 	var cache = {
		 			isAdded:''
		 	};
		 	

			 /** implemented category service **/ 

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
     	    
     	    $scope.getTop = function(){
     	    	getTopQ();
     	    }
     	    
     	    /**
     	     * @param categoryId 			category id to be retrive
     	     * @return 						a category by id
     	     * @use 						categoryService,
     	     */
     	    $scope.getCategoryById = function(categoryId) {
     	    	$log.debug("Category Controller getCategoryById(categoryId)"+categoryId);
     	    	categoryService.getCategoryById(categoryId).then(function(response){
     	    		$log.debug("Category Controller getCategoryById(categoryId) ramo then");
     	    		$scope.category = response;
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
     	    		$scope.category = response;
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
     	    
      	   $scope.getChildData = function(parentId){
      		   if (parentId != ""){
     				   categoryService.getChild(parentId).then(function(response){
     					   $scope.categoryData=response;
     				   });
      		   }
     	    };
     	    
       	   $scope.getChildDataByCode = function(parentCode){
      		   if (parentCode != undefined){
     				   categoryService.getChildByCode(parentCode).then(function(response){
     					   $scope.categoryData=response;
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
     	    
     	    $scope.openParentCategory = function (categoryId){
     	    	if (categoryId != null && categoryId != undefined && categoryId != ""){
         	    	categoryService.getCategoryById(categoryId).then(function(response){
         	    		if (response.parentCategories[0] != undefined){
         	    			$scope.categoryData.forEach(function(cat){
         	    				if (response.parentCategories[0].id == cat.id){
         	    	     		   cat.active=!cat.active;
         	     				   categoryService.getChild(cat.id).then(function(response){
         	     					   $log.debug("openParentCategory(categoryId) ramo then");
         	     					   cache.isAdded += '#' + cat.id;
         	     					   cat.subCategory=response;
         	     					   $log.debug("Request effettuata "+cat.active);
         	     				   })}
         	    			})
         	    		}
         	    	})
     	    	}
     	    }
 }]);
 


