 var app = angular.module("brand.controller", ['brand.Service'])
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
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.getBrand = function(data) {	
			
			console.log("Brand Controller: session id for top method:-> " + $scope.sessionId)
 	    	brandService.getBrand(endpoint,$scope.sessionId).then( function( response ) {
 	    		$scope.brandData = response.content
 	    		})
 	    };
 	    
  	   function getBrandQ(){
  		  console.log("Controller BrandQ");
  		  brandService.getBrandQ().then(function(response){
  			  console.log("BrandQ ramo then");
  			  $scope.brandData = response;
  		  })
  	   };
		
 	    /**
 	     * @param endpoint 		   		web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @return 						new brand
 	     * @use 						brandService,authManagerService
 	     */
 		$scope.addBrand = function(endpoint,data ) {	
 	    	brandService.addBrand(endpoint,$rootScope.sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @param data 					data to send
 	     * @return 						a brand update with @param data
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.updateBrand = function(endpoint, data) {		
 	    	brandService.updateBrand(endpoint,$rootScope.sessionId, data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 			a sessionId
 	     * @param brandId    			id of brand to be deleted
 	     * @return 				
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.deleteBrand = function(endpoint,brandId ) {		
 	    	brandService.deleteBrand(endpoint,$rootScope.sessionId,brandId ).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 		a sessionId
 	     * @param code 	    
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandById = function(endpoint,code,brandId) {		
 	    	brandService.findBrandById(endpoint,$rootScope.sessionId,code,brandId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param endpoint 		    	web service rest endpoint
 	     * @param sessionId 		a sessionId
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param name
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandByName = function(endpoint,name) {		
 	    	brandService.findBrandByName(endpoint,$rootScope.sessionId,name).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId         	    
 	     * @return 					all brand
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findAll = function(endpoint) {		
 	    	brandService.findAll(endpoint,sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /** execute function on view content load **/

 }])