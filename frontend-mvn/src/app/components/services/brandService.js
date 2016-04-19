 (function() {
	 'use strict';

	angular.module('brand.service', [])
/**
 * brand service to handler rest call to brand service
 */
	.service('brandService',brandService);
	
	 brandService.$inject = ['$http', '$q', 'sessionService', 'URL', 'COOKIE_EXPIRES', '$cookies','$log'];
	 
	 /** @ngInject */
	function brandService( $http, $q, sessionService, URL, COOKIE_EXPIRES, $cookies,$log ) {
		
		var endpoint = '/api/v1/brands/';
	 
        /** Return public API. (like java interface)**/
	    
       var service =({
        	findBrandById	: findBrandById,
        	findBrandByCode	: findBrandByCode,
        	findAll			: findAll,
        	getBrandList	: getBrandList,
        	brandProductsByIdPage: brandProductsByIdPage,
        	brandProductsByCodePage: brandProductsByCodePage
        });
       return service;
        
        /** public methods**/
        
	    /**
	     * @param sessionId                
	     * @return 
	     */     
	 function getBrandList() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService getBrandList() ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint ,
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
        function findBrandById(brandId) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService findBrandById(brandId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: URL + endpoint +   'id/' + brandId,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
        }
        
        /**
         * @param name
         * @return 
         */      
        function findBrandByCode(brandCode) {
        	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService findBrandByName(name) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint +  'code/' + brandCode,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
        }
        
        /**
         * @param page
         * @param size        
         * @return 
         */      
        function findAll(page,size) {
        	var serviceSSID = sessionService;
        	
        	var findAllUrl='';
        	if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
        		findAllUrl= URL + endpoint;
        	}else{
        		findAllUrl = URL + endpoint +  '?page=' + page + '&size=' +size;
        	}
        	
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService findAll(page,size) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: findAllUrl,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
        }
        
         /**
         * @param page
         * @param size        
         * @return 
         */      
        function brandProductsByIdPage(brandId,page,size) {
        	var serviceSSID = sessionService;
        	
        	var findAllUrl='';
        	if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
        		findAllUrl= URL + endpoint;
        	}else{
        		findAllUrl = URL + endpoint +  'id/' + brandId + '/products/page?page=' + page + '&size=' +size;
        	}
        	
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService brandProductsByIdPage(page,size) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: findAllUrl,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
        }
        
        
        /**
         * @param page
         * @param size        
         * @return 
         */      
        function brandProductsByCodePage(brandCode,page,size) {
        	var serviceSSID = sessionService;
        	
        	var findAllUrl='';
        	if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
        		findAllUrl= URL + endpoint;
        	}else{
        		findAllUrl = URL + endpoint +  'code/' + brandCode + '/products/page?page=' + page + '&size=' +size;
        	}
        	
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('BrandService brandProductsByCodePage(page,size) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
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
                return( $q.reject( 'BrandService: An unknown error occurred.' ) );
            }
            /** Otherwise, use expected error message.**/
            return( $q.reject( response.data.message ) );
        }
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
        function handleSuccess( response ) {
        	var serviceSSID = sessionService;
//        	$log.debug(response.data.responseObject);
        	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES);  /** expire 20 minut **/ 
            return(response.data.responseObject);
        }
    
}

 })();
