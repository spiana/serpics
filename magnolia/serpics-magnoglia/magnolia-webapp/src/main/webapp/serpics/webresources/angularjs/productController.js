 var app = angular.module("product.controller", ['product.service', 'cart.service','serpics.services'])
/** productController **/
.controller("productController",['$scope','serpicsServices','productService','cartService','$log',
                                  
	      function($scope,serpicsServices,productService,cartService,$log) {	
	   	
			var categoryId = $scope.categoryId;
			var brandId = $scope.brandId;
			var productId = $scope.productId;
			var textSearch = $scope.textSearch;
			var page = getPage();
			var size = getSize();
			
			$scope.breadcrumbCategories = [];
			$scope.defaultQuantity = 1;
	
//	  	    $scope.product 	= findAllQ(page, size);
	  	    
	  	    
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
					return 9;
				}
	  	   	};
	  	   	
	  	  $scope.range = function(totalPages){
	  	   		var input = [];
	  	      for (var i=0; i<totalPages; i++) {
	  	        input.push(i);
	  	      }

	  	      return input;
	  	   	}
	  	  
	  	  $scope.addToCart = function(sku,quantity){
	  	    	$log.debug("ProductController cartAdd(sku ,quantity)");
	  			cartService.cartAdd(sku ,quantity).then(function(response){
	    			  $log.debug("ProductController cartAdd(sku ,quantity): ramo then");
	    			  location.href = $scope.cartUrl;	    			  
	  		});
	  	  }
	  	 
	  	    /** implemented order service **/ 
	  	    
	  	    
	  	    /**
	  	     * @param productId 			id of product 	  
	  	     * @return 						product with id equal @param productId
	  	     * @use 						productService,serpicsServices
	  	     */
	  	    function getProduct(productId) {		
	  	    	productService.getProduct(productId).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    $scope.getProduct = function(productId) {	
	  	    	if (productId != undefined){
	  	    		getProduct(productId);
	  	    	}
	  	    }; 
	  	    
	  	    /**
	  	     * @param productId 			id of product 
	  	     * @return 						product's main category
	  	     * @use 						productService,serpicsServices    
	  	     */
	  	   function getCategory(productId) {		
	  	    	productService.getCategoryProduct(productId).then( function( response ) {
	  	    		$scope.productCategory 	= response;
	              })
	  	    };  	    
	  	    
	  	    /**
	  	     * @param productName 			name of product to retrieve  	    
	  	     * @return 						product name equal @param productName
	  	     * @use 						productService,serpicsServices
	  	     */
	  	   function getProductByName(productName) {		
	  	    	productService.getProductByName(productName).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  
	  	    
	  	    /**
	  	     * @param categoryId 			id of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService,serpicsServices
	  	     */
	  	    function findByCategory(categoryId, page, size) {		
	  	    	productService.findByCategory(categoryId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  	
	  	    
	  	    /**
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService,serpicsServices
	  	     */
	  	    function findByBrand(brandId, page, size) {		
	  	    	productService.findByBrand(brandId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @return 						all product
	  	     * @use 						productService,serpicsServices
	  	     */
	  	    function findAll(page, size) {		
	  	    	productService.findAll(page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    /**
	  	     * @param textSearch 			text to seach   
	  	     * @return 						product with textSearch in code, name or description
	  	     * @use 						productService,serpicsServices
	  	     */
	  	    function findBySearch(searchText, page, size) {		
	  	    	productService.findBySearch(searchText, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    }; 
	  	    
	  	    function findAllQ(page, size){
	  	    	$log.debug("Controller ProductQ");
	  	    	if (productId){
	  	    		getProduct(productId);
	  	    	} else {
	  	    		if(textSearch){
	  	    			findBySearch(textSearch, page, size);
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
	  	    	}
	  	    };
	  	    
	  	    $scope.findAllQ = function (page,size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	findAllQ(page,size);
	  	    }
	  	    
	  	    $scope.findAll = function (page,size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	findAll(page,size);
	  	    }
	  	    
	  	    $scope.findByBrand = function (brandId, page, size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	findByBrand(brandId, page, size);
	  	    }
	  	    
	  	    $scope.findByCategory = function (categoryId, page, size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	findByCategory(categoryId, page, size);
	  	    }
	  	    
	  	    $scope.findBySearch = function (text, page, size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	findBySearch(text, page, size);
	  	    }
	  	    
	  	    $scope.getBreadcrumb = function (){
	  	    	if ($scope.product.categories != undefined){
	  	    		parent = $scope.product.categories[0];
	  	    		i = 0;
	  	    		while (parent != undefined){
		  	    		$scope.breadcrumbCategories[i] = parent;
		  	    		parent = $scope.breadcrumbCategories[i].parentCategories[0];
		  	    		i = i+1;
	  	    		}
	  	    	}
	  	    }
	  	    	
}])
