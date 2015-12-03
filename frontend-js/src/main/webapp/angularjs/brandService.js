 var app = angular.module("brand.Service", ['serpics.config'])
/**
 * brand service to handler rest call to brand service
 */
app.service("brandService", function( $http, $q, authManagerService,URL ) {
	
	var endpoint = '/jax-rs/brandService/';
	 
        /** Return public API. (like java interface)**/
	    
        var service =({
        	getBrand		: getBrand,
        	getBrandQ		: getBrandQ,
        	addBrand		: addBrand,
        	updateBrand		: updateBrand,
        	deleteBrand		: deleteBrand,
        	findBrandById	: findBrandById,
        	findBrandByName	: findBrandByName,
        	findAll			: findAll
        });                
        return service;
        
        /** public methods**/
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param data
         * @return 
         */
        function getBrand(endpoint,sessionId) {
            var request = $http({         
            	method:'GET',
                url: URL + endpoint,
                headers: {
                	'ssid': sessionId
                	} 
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
	    /**
	     * @param endpoint
	     * @param sessionId                
	     * @return 
	     */     
	    function getBrandQ() {
	    	var serviceSSID = authManagerService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("getbrandQService session Id nel promise"+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    			
	    		});
    		
	    	});
	    }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param data
         * @return 
         */
        function addBrand(endpoint,sessionId,data ) {
            var request = $http.post({              
                url: endpoint +   'addBrand',
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param data
         * @return 
         */
        function updateBrand(endpoint,sessionId, data ) {
            var request = $http.put({               
                url: endpoint +  'updateBrand',
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param brandId
         * @return 
         */
        function deleteBrand(endpoint,sessionId,brandId ) {
            var request = $http({
                method: 'DELETE',
                url: endpoint +   'deleteBrand/' + id,
                headers: {
                	'ssid': sessionId
                }                        
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param code
         * @param brandId
         * @return 
         */      
        function findBrandById(endpoint,sessionId,code,brandId) {
            var request = $http.get({                
                url: endpoint +   code + '/' + brandId,
                headers: {
                	'ssid': auurlthManager.getsessionId
                }                         
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param name
         * @return 
         */      
        function findBrandByName(endpoint,sessionId,name) {
        	 var request = $http.get({                
                 url: 	endpoint +  name,
                 headers: {
                 	'ssid': sessionId
                 }                            
               });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId 
         * @param page
         * @param size        
         * @return 
         */      
        function findAll(endpoint,sessionId,page,size) {
        	 var request = $http.get({                
                 url: endpoint +  'findAll?page=' + page + '&size=' +size,
                 headers: {
                 	'ssid': sessionId
                 }                         
               });
            return( request.then( handleSuccess, handleError ) );
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
                return( $q.reject( "An unknown error occurred." ) );
            }
            /** Otherwise, use expected error message.**/
            return( $q.reject( response.data.message ) );
        }
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
        function handleSuccess( response ) {
            return( response.data.responseObject);
        }
    }
);
