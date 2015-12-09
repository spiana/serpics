 var app = angular.module("product.controller", ['product.service'])
/** productController **/
.controller("productController",['$scope','authManagerService','productService', 
                                  
	      function($scope,authManagerService,productService) {	
	   	
			var categoryId = $scope.categoryId;
			var brandId = $scope.brandId;
			var productId = $scope.productId;
			var page = getPage();
			var size = getSize();
	
	  	    $scope.product 	= [];
	  	    
	  	    findAllQ(page, size);
	  	    
	  	   	function getPage(){
					if ($scope.product) {
						return $scope.product.number;
					} else {
						return 0;
					}
			};
			
	  	   	function getSize(){
				if ($scope.product) {
					return $scope.product.size;
				} else {
					return 10;
				}
	  	   	};
	  	   	
	  	  $scope.range = function(totalPages){
	  	   		var input = [];
	  	      for (var i=0; i<totalPages; i++) {
	  	        input.push(i);
	  	      }

	  	      return input;
	  	   	}
	  	 
	  	    /** implemented order service **/ 
	  	    
	  	    
	  	    /**
	  	     * @param productId 			id of product 	  
	  	     * @return 						product with id equal @param productId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function getProduct(productId) {		
	  	    	productService.getProduct(productId).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	   	  
	  	    
	  	    /**
	  	     * @param productId 			id of product 
	  	     * @return 						product's main category
	  	     * @use 						productService,authManagerSer    
	  	     */
	  	   function getCategory(productId) {		
	  	    	productService.getCategoryProduct(productId).then( function( response ) {
	  	    		$scope.productCategory 	= response;
	              })
	  	    };  	    
	  	    
	  	    /**
	  	     * @param productName 			name of product to retrieve  	    
	  	     * @return 						product name equal @param productName
	  	     * @use 						productService,authManagerService
	  	     */
	  	   function getProductByName(productName) {		
	  	    	productService.getProductByName(productName).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param categoryId 			id of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function findByCategory(categoryId, page, size) {		
	  	    	productService.findByCategory(categoryId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  	
	  	    
	  	    /**
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function findByBrand(brandId, page, size) {		
	  	    	productService.findByBrand(brandId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @return 						all product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function findAll(page, size) {		
	  	    	productService.findAll(page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    function findAllQ(page, size){
	  	    	console.log("Controller ProductQ");
	  	    	if (productId){
	  	    		getProduct(productId);
	  	    	} else {
		  	    	if (!categoryId && brandId){
		  	    		findByBrand(brandId, page, size);
		  	    	}
		  	    	if (categoryId && !brandId){
		  	    		findByCategory(categoryId, page, size);
		  	    	}
		  	    	if (!categoryId && !brandId){
		  	    		findAll(page, size);
		  	    	}
	  	    	}
	  	    };
	  	    
	  	    $scope.findAllQ = function (page,size) {
	  	    	findAllQ(page,size);
	  	    }
	  	    	
}])
