 var app = angular.module("brand.service", ['serpics.config','serpics.services'])
/**
 * brand service to handler rest call to brand service
 */
app.service("brandService",['$http', '$q', 'serpicsServices', 'URL', 'COOKIE_EXPIRES', '$cookies','$log', function( $http, $q, serpicsServices, URL, COOKIE_EXPIRES, $cookies,$log ) {
	
	var endpoint = '/api/v1/brandService/';
	 
        /** Return public API. (like java interface)**/
	    
        var service =({
        	findBrandById	: findBrandById,
        	findBrandByCode	: findBrandByCode,
        	findAll			: findAll,
        	getBrandList	: getBrandList
        });                
        return service;
        
        /** public methods**/
        
	    /**
	     * @param sessionId                
	     * @return 
	     */     
	    function getBrandList() {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("BrandService getBrandList() ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint + 'list',
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
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("BrandService findBrandById(brandId) ssid nel promise "+sessionId) ;
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
        	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("BrandService findBrandByName(name) ssid nel promise "+sessionId) ;
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
        	var serviceSSID = serpicsServices;
        	
        	var findAllUrl='';
        	if (arguments.length === 0 || arguments.length === 1 || typeof page === 'undefined') {
        		findAllUrl= URL + endpoint;
        	}else{
        		findAllUrl = URL + endpoint +  '?page=' + page + '&size=' +size
        	}
        	
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("BrandService findAll(page,size) ssid nel promise "+sessionId) ;
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
                return( $q.reject( "BrandService: An unknown error occurred." ) );
            }
            /** Otherwise, use expected error message.**/
            return( $q.reject( response.data.message ) );
        }
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
        function handleSuccess( response ) {
        	var serviceSSID = serpicsServices;
        	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES)  /** expire 20 minut **/ 
            return(response.data.responseObject);
        }
    
}]);
