var app = angular.module("brand", ['AuthManager','ngCookies'])

.constant('api_endpoint', 			'http://localhost:8080/jax-rs/brandService/')

 app.service("brandService",['authManager',
            function( $http, $q ,api_endpoint) {
	 
                /** Return public API. (like java interface)**/
                var service =({
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
                 * @param sessionId               
                 * @param data
                 * @return 
                 */
                function addBrand(sessionId,data ) {
                    var request = $http({
                        method: 'POST',
                        url: api_endpoint + 'addBrand',
                        headers: {
                        	'ssid': sessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId               
                 * @param data
                 * @return 
                 */
                function updateBrand(sessionId, data ) {
                    var request = $http({
                        method: 'PUT',
                        url: api_endpoint +'updateBrand',
                        headers: {
                        	'ssid': sessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId               
                 * @param brandId
                 * @return 
                 */
                function deleteBrand(sessionId,brandId ) {
                    var request = $http({
                        method: 'DELETE',
                        url: api_endpoint + 'deleteBrand/' + id,
                        headers: {
                        	'ssid': sessionId
                        }                        
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId               
                 * @param code
                 * @param brandId
                 * @return 
                 */      
                function findBrandById(sessionId,code,brandId) {
                    var request = $http({
                        method: 'GET',
                        url: 	api_endpoint + code + '/' + brandId,
                        headers: {
                        	'ssid': auurlthManager.getsessionId
                        }                         
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId               
                 * @param name
                 * @return 
                 */      
                function findBrandByName(sessionId,name) {
                	 var request = $http({
                         method: 'GET',
                         url	: 	api_endpoint + name,
                         headers: {
                         	'ssid': sessionId
                         }                            
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId          
                 * @return 
                 */      
                function findAll(sessionId) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint + 'findAll',
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
                    return( response.data );
                }
            }
        ]);

app.controller("brandController",['$scope','$cookies','authManagerService','brandService', 
                                     
     function($scope,$cookies,authManagerService,brandService) {	
  	
 	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
 	    	
 	    $scope.data 	= {
	    		sessionId	: '',
	    		brand		: []
	    }
 	  
 	  
 	  
 	    /** implemented brand service **/ 
 	    
 	    /**
 	     * @param sessionId 		a sessionId
 	     * @return 					new brand
 	     * @use 					brandService,authManagerService
 	     */
 		$scope.addBrand = function(sessionId,data ) {	
 	    	brandService.addBrand(sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 			a sessionId
 	     * @param data 					data to send
 	     * @return 						a brand update with @param data
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.updateBrand = function(sessionId, data) {		
 	    	brandService.updateBrand(sessionId, data).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	   /**
 	     * @param sessionId 			a sessionId
 	     * @param brandId    			id of brand to be deleted
 	     * @return 				
 	     * @use 						brandService,authManagerService
 	     */
 	    $scope.deleteBrand = function(sessionId,brandId ) {		
 	    	brandService.deleteBrand(sessionId,brandId ).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId
 	     * @param code 	    
 	     * @return 					all brand by @param brandId
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandById = function(sessionId,code,brandId) {		
 	    	brandService.findBrandById(sessionId,code,brandId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId
 	     * @param name 				name of brand to retrieve
 	     * @return 					all brand by @param name
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findBrandByName = function(sessionId,name) {		
 	    	brandService.findBrandByName(sessionId,name).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	    
 	    /**
 	     * @param sessionId 		a sessionId         	    
 	     * @return 					all brand
 	     * @use 					brandService,authManagerService
 	     */
 	    $scope.findAll = function(sessionId) {		
 	    	brandService.findAll(sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
 	    };
 	             	    
 }])
  