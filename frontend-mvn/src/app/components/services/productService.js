 (function() {
	 'use strict';
	 angular.module('product.service', [])
	 .service('productService',productService);
	
	 productService.$inject = ['$http', '$q', 'sessionService', 'URL','COOKIE_EXPIRES', '$cookies','$log'];
	 
	 /** @ngInject */
	 function productService( $http, $q, sessionService, URL,COOKIE_EXPIRES, $cookies,$log) {
	
	var endpoint = '/api/v1/products/';
	 
	     /** Return public API. (java interface)**/
	     var service =({
	    	 
	     		getProduct	  			: getProduct,
	     		getProductByCode		: getProductByCode,
	     		findByCategoryCode		: findByCategoryCode,
	     		getCategoryProduct 		: getCategoryProduct,
	     		getProductByName		: getProductByName,
	     		findByCategory			: findByCategory,
	     		findByBrand				: findByBrand,
	     		findBySearch			: findBySearch,
	     		findAll			  		: findAll
	     });                
	     return service;
	     
	   /** public methods**/
	    
	    /**
	     * @param productId
	     * @return 
	     */      
	     function getProduct(productId) {
	    	 
	    	 var serviceSSID = sessionService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 'id/' + productId,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	    /**
	     * @param categoryId
	     * @return 
	     */      
	     function getCategoryProduct(productId) {
	    	 var serviceSSID = sessionService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 'category/'+ productId,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	    /**
	     * @param productName
	     * @return 
	     */              
	     function getProductByName(productName) {
	    	 var serviceSSID = sessionService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 	  'name/' + productName ,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
		    /**
		     * @param productCode
		     * @return 
		     */      
		     function getProductByCode(productCode) {
		    	 
		    	 var serviceSSID = sessionService;
		    	 return $q(function(resolve, reject) {
		    		 serviceSSID.getSessionId().then(function(sessionId){
		    			 $log.debug('session Id nel promise '+sessionId) ;
		    			 $http({
		    				 method: 	'GET',
		    				 url: URL + endpoint + 'code/' + productCode,
		    				 headers: {
		    					 'ssid': sessionId
		    					 }
		    			 }).then(handleSuccess, handleError).then(resolve, reject);
		    		 });
		    	 });
		     }
	     
	     /**
	     * @param categoryId
	     * @return 
	     */              
	     function findByCategory(categoryId, page, size) {
	    	 var serviceSSID = sessionService;
	    	 var findByCategoryUrl='';
	    	 if (arguments.length === 0 || arguments.length === 1 ) {
	    		 findByCategoryUrl= URL + endpoint +   'pageCategory/' + categoryId;
	    		 }else{
	    			 findByCategoryUrl = URL + endpoint +   'pageCategory/' + categoryId + '?page=' + page + '&size=' + size;
	    		 }
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: 	findByCategoryUrl,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	     	/**
		     * @param categoryCode
		     * @return 
		     */              
		     function findByCategoryCode(categoryCode, page, size) {
		    	 var serviceSSID = sessionService;
		    	 var findByCategoryUrl='';
		    	 if (arguments.length === 0 || arguments.length === 1 ) {
		    		 findByCategoryUrl= URL + endpoint +   'pageCategory/code/' + categoryCode;
		    		 }else{
		    			 findByCategoryUrl = URL + endpoint +   'pageCategory/code/' + categoryCode + '?page=' + page + '&size=' + size;
		    		 }
		    	 return $q(function(resolve, reject) {
		    		 serviceSSID.getSessionId().then(function(sessionId){
		    			 $log.debug('session Id nel promise '+sessionId) ;
		    			 $http({
		    				 method: 	'GET',
		    				 url: 	findByCategoryUrl,
		    				 headers: {
		    					 'ssid': sessionId
		    					 }
		    			 }).then(handleSuccess, handleError).then(resolve, reject);
		    		 });
		    	 });
		     }
	     
	   /**
	     * @param searchText
	     * @return 
	     */         
	     function findBySearch(searchText, page, size) {
	    	 var serviceSSID = sessionService;
	    	 var findBySearchUrl='';
	    	 if (arguments.length === 1 || arguments.length === 2 ) {
	    		 findBySearchUrl= URL + endpoint + 'search';
	    		 }else{
	    			 findBySearchUrl = URL + endpoint + 'search' + '?searchText=' + searchText +
	    			 '&page=' + page + '&size=' + size;
	    		 }
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: findBySearchUrl,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
		   /**
		     * @param brandId
		     * @return 
		     */         
		     function findByBrand(brandId, page, size) {
		    	 var serviceSSID = sessionService;
		    	 var findByBrandUrl='';
		    	 if (arguments.length === 1 || arguments.length === 2 ) {
		    		 findByBrandUrl= URL + endpoint + 'pageBrand/' + brandId;
		    		 }else{
		    			 findByBrandUrl = URL + endpoint + 'pageBrand/' + brandId + '?page=' + page + '&size=' + size;
		    		 }
		    	 return $q(function(resolve, reject) {
		    		 serviceSSID.getSessionId().then(function(sessionId){
		    			 $log.debug('session Id nel promise'+sessionId) ;
		    			 $http({
		    				 method: 	'GET',
		    				 url: findByBrandUrl,
		    				 headers: {
		    					 'ssid': sessionId
		    					 }
		    			 }).then(handleSuccess, handleError).then(resolve, reject);
		    		 });
		    	 });
		     }

	   /**
	     * @return 
	     */         
	     function findAll(page, size) {
	    	 
	    	 var serviceSSID = sessionService;
	    	 var findAllUrl='';
	    	 if (arguments.length === 0 || arguments.length === 1 ) {
	    		 findAllUrl= URL + endpoint;
	    		 }else{
	    			 findAllUrl = URL + endpoint +  'page/?page=' + page + '&size=' +size;
	    		 }
	    	 
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug('session Id nel promise'+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: findAllUrl,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	        

    /**
     * private method.
     * I transform the error response, unwrapping the application dta from
     * the API response payload.
     */                
  
                   
    function handleError( response ) {
        /**
         * The API response from the server should be returned in a
         * nomralized format. However, if the request was not handled by the
         * server (or what not handles properly - ex. server error), then we
         * may have to normalize it on our end, as best we can.
         */ 
        if (! angular.isObject( response.data ) || ! response.data.message ) {
            return( $q.reject( 'An unknown error occurred.' ) );
        }
        /** Otherwise, use expected error message.**/
        return( $q.reject( response.data.message ) );
    }
    /** 
     * I transform the successful response, unwrapping the application data
     * from the API response payload.                
     */
    function handleSuccess( response ) {
    	var serviceSSID = sessionService;
    	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES);  /** expire 20 minut **/
        return( response.data.responseObject);
    }
}

 })();
