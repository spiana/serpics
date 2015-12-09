 var app = angular.module("brand.controller", ['brand.service'])
 /** brandController **/
.controller("brandController",['$scope','brandService',
                                     
     function($scope,brandService) {	
  	
		
 	    $scope.brandData 	= [];
 	  
	 	getBrandQ();
 	    
 	    
 	    /** implemented brand service **/  	    
		
		/**
		 * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,serpicsServices
 	     */
 		$scope.getBrand = function(data) {	
			
			console.log("Brand Controller: session id for top method:-> " + $scope.sessionId)
 	    	brandService.getBrand(endpoint,$scope.sessionId).then( function( response ) {
 	    		$scope.brandData = response.content
 	    		})
 	    };
 	    
		/**
 	     * @return 						all brands list
 	     * @use 						brandService
 	     */
  	   function getBrandQ(){
  		  console.log("Brand Controller getBrandQ()");
  		  brandService.getBrandQ().then(function(response){
  			  console.log("Brand Controller getBrandQ(): ramo then");
  			  $scope.brandData = response;
  		  })
  	   };
		
	    
 	    /**
 	     * @param brandId 		   	brandId to search
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService
 	     */
 	    $scope.findBrandById = function(brandId)  {
 	    	console.log("Brand Controller findBrandById: "+brandId);
 	    	brandService.findBrandById(brandId).then(function(response){
 	    		console.log("Brand Controller findBrandById(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param name
 	     * @use 					brandService,serpicsServices
 	     */
 	    $scope.findBrandByName = function(name) {
 	    	console.log("Brand Controller findBrandByName: "+name);
 	    	brandService.findBrandByName(name).then(function(response){
 	    		console.log("Brand Controller findBrandByName(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @return 					all brand
 	     * @use 					brandService,serpicsServices
 	     */
 	    $scope.findAll = function(page,size) {
 	    	console.log("Brand Controller findAll: page:"+page+" size: "+size);
 	    	brandService.findAll(page,size).then(function(response){
 	    		console.log("Brand Controller findAll(): ramo then");
 	    		$scope.brandData = response;
 	    	});
 	    };
 	    
 }])