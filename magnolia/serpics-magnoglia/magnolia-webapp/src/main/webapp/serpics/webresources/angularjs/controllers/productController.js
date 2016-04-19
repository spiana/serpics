 var app = angular.module("product.controller", ['product.service', 'cart.service','ngSanitize'])
/** productController **/
.controller("productController",['$scope','productService','cartService','$log','ngDialog','$sce',
                                  
	      function($scope,productService,cartService,$log,ngDialog,$sce) {	
	   	
			var categoryId = $scope.categoryId;
			var brandId = $scope.brandId;
			var productId = $scope.productId;
			var textSearch = $scope.textSearch;
			var page = getPage();
			var size = getSize();
			
			$scope.breadcrumbCategories = [];
			$scope.defaultQuantity = 1;	
			
			$scope.trustAsHtml = $sce.trustAsHtml;
			
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
	  	     * @use 						productService
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
	  	     * @use 						productService    
	  	     */
	  	   function getCategory(productId) {		
	  	    	productService.getCategoryProduct(productId).then( function( response ) {
	  	    		$scope.productCategory 	= response;
	              })
	  	    };  	    
	  	    
	  	    /**
	  	     * @param productCode 			code of product to retrieve  	    
	  	     * @return 						product code equal @param productCode
	  	     * @use 						productService
	  	     */
	  	   function getProductByCode(productCode) {		
	  	    	productService.getProductByCode(productCode).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    $scope.getProductByCode = function(productCode) {	
	  	    	if (productCode != undefined){
	  	    		getProductByCode(productCode);
	  	    	}
	  	    }; 
	  	    
	  	    /**
	  	     * @param categoryId 			id of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService
	  	     */
	  	    function findByCategory(categoryId, page, size) {		
	  	    	productService.findByCategory(categoryId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	  	
	  	    
	  	    /**
	  	     * @param categoryCode 			code of category of product to retrieve  	    
	  	     * @return 						product with category equal @param categoryId
	  	     * @use 						productService
	  	     */
	  	    function findByCategoryCode(categoryCode, page, size) {		
	  	    	productService.findByCategoryCode(categoryCode, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  
	  	    
	  	    /**
	  	     * @param brandId 				id of brand of product to retrieve    
	  	     * @return 						product with brand equal @param brandId
	  	     * @use 						productService
	  	     */
	  	    function findByBrand(brandId, page, size) {		
	  	    	productService.findByBrand(brandId, page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };  	
	  	    
	  	    /**
	  	     * @return 						all product
	  	     * @use 						productService
	  	     */
	  	    function findAll(page, size) {		
	  	    	productService.findAll(page, size).then( function( response ) {
	  	    		$scope.product 	= response;
	              })
	  	    };
	  	    
	  	    /**
	  	     * @param textSearch 			text to seach   
	  	     * @return 						product with textSearch in code, name or description
	  	     * @use 						productService,sessionService
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
			  	    		brandService.brandProductsByIdPage(brandId, page, size).then(function(response) {
								$scope.product = response;
			  	    		});
			  	    	}
			  	    	if (categoryId && !brandId){
							categoryService.categoryProductsByIdPage(categoryId, page, size).then(function(response) {
								$scope.product = response;
							});
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
	  	    
	  	    $scope.findByCategory = function (categoryId, categoryCode, page, size) {
	  	    	if (page == undefined || size == undefined){
	  	    		page = getPage();
	  	    		size = getSize();
	  	    	}
	  	    	if (categoryId != "false"){
	  	    		findByCategory(categoryId, page, size);
	  	    	} else if (categoryCode != "false"){
	  	    		findByCategoryCode(categoryCode, page, size);
	  	    	}
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
	  	    
			$scope.openGalleryImageModal = function(imageUrl){
				$scope.imageUrl = imageUrl;
			 	var dialog = ngDialog.open({
				    		  template: 'galleryImageDialog',
				    		  keyboard: true,
				    		  className:'',
				    		  scope:    $scope,
				 });
		}
	  	    	
}])
