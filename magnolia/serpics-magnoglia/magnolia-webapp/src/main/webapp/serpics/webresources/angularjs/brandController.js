 var app = angular.module("brand.controller", ['brand.service'])
 /** brandController **/
.controller("brandController",['$scope','brandService','$log',
                                     
     function($scope,brandService,$log) {	
  	
		
 	    $scope.brandData = getBrandList();
 	  
 	    
 	    /** implemented brand service **/  	    
		
		/**
 	     * @return 					all brands list
 	     * @use 					brandService
 	     */
  	   function getBrandList(){
  		  $log.debug("Brand Controller getBrandQ()");
  		  brandService.getBrandList().then(function(response){
  			  $log.debug("Brand Controller getBrandQ(): ramo then");
  			  $scope.brandData = response;
  		  })
  	   };
		
 	    /**
 	     * @param brandId 		   	brandId to search
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService
 	     */
 	    $scope.findBrandById = function(brandId)  {
 	    	$log.debug("Brand Controller findBrandById: "+brandId);
 	    	brandService.findBrandById(brandId).then(function(response){
 	    		$log.debug("Brand Controller findBrandById(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param code
 	     * @use 					brandService,serpicsServices
 	     */
 	    $scope.findBrandByCode = function(code) {
 	    	$log.debug("Brand Controller findBrandByName: "+code);
 	    	brandService.findBrandByCode(code).then(function(response){
 	    		$log.debug("Brand Controller findBrandByCode(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @return 					all brand
 	     * @use 					brandService,serpicsServices
 	     */
 	    $scope.findAll = function(page,size) {
 	    	$log.debug("Brand Controller findAll: page:"+page+" size: "+size);
 	    	brandService.findAll(page,size).then(function(response){
 	    		$log.debug("Brand Controller findAll(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 }])