 var app = angular.module("product.controller", ['product.service'])
/** productController **/
.controller("productController",['$scope','authManagerService','productService', 
                                  
	      function($scope,authManagerService,productService) {	
	   	
			var categoryId = $scope.categoryId;
			var brandId = $scope.brandId;
	
	  	    $scope.product 	= [];
	  	    
	  	    findAllQ();
	  	   	  
	  	 
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
	  	    function findByCategory(categoryId) {		
	  	    	productService.findByCategory(categoryId).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  	
	  	    
	  	    /**
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function findByBrand(brandId) {		
	  	    	productService.findByBrand(brandId).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @return 						all product
	  	     * @use 						productService,authManagerService
	  	     */
	  	    function findAll() {		
	  	    	productService.findAll().then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    function findAllQ($scope){
	  	    	console.log("Controller ProductQ");
	  	    	if (!categoryId && brandId){
	  	    		findByBrand(brandId);
	  	    	}
	  	    	if (categoryId && !brandId){
	  	    		findByCategory(categoryId);
	  	    	}
	  	    	if (!categoryId && !brandId){
	  	    		findAll(brandId);
	  	    	}
	  	    };
	  	    	
}])
