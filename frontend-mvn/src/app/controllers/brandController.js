 (function() {
	 'use strict';

	 angular.module("brand.controller", ['brand.service'])
	 .controller("brandController",brandController);
	 
//	 brandController.$inject = ['brand.service'];
	 
//['$scope','brandService','$log',
	 /** brandController **/               
	 /** @ngInject */
     function brandController(brandService,$log) {
    	 
    	 var vm = this;
    	 vm.brandData = getBrandList();
 	  
 	    /** implemented brand service **/  	    
		
		/**
 	     * @return 					all brands list
 	     * @use 					brandService
 	     */
  	   function getBrandList(){
  		  $log.debug("Brand Controller getBrandQ()");
  		  brandService.getBrandList().then(function(response){
  			  $log.debug("Brand Controller getBrandQ(): ramo then response"+response[0].brandProductNumber);
  			  vm.brandData = response;
  			  
  		  })
  	   };
		
 	    /**
 	     * @param brandId 		   	brandId to search
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService
 	     */
 	    vm.findBrandById = function(brandId)  {
 	    	$log.debug("Brand Controller findBrandById: "+brandId);
 	    	brandService.findBrandById(brandId).then(function(response){
 	    		$log.debug("Brand Controller findBrandById(): ramo then");
 	    		vm.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param code
 	     * @use 					brandService,serpicsServices
 	     */
 	    vm.findBrandByCode = function(code) {
 	    	$log.debug("Brand Controller findBrandByName: "+code);
 	    	brandService.findBrandByCode(code).then(function(response){
 	    		$log.debug("Brand Controller findBrandByCode(): ramo then");
 	    		vm.brandData = response;
 	    	});
 	    };
 	    
 	    /**
 	     * @return 					all brand
 	     * @use 					brandService,serpicsServices
 	     */
 	    vm.findAll = function(page,size) {
 	    	$log.debug("Brand Controller findAll: page:"+page+" size: "+size);
 	    	brandService.findAll(page,size).then(function(response){
 	    		$log.debug("Brand Controller findAll(): ramo then");
 	    		vm.brandData = response;
 	    	});
 	    };
 	    
 };
 })();
