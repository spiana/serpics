var app = angular.module("brand", ['AuthManager','ngload'])

.constant('api_endpoint', 			'http://localhost:8080/jax-rs/brandService/')

 app.service("brandService",['authManager','ngload',
            function( $http, $q ,authManager,ngload,api_endpoint) {
	 
                /** Return public API. **/
                var service =({
                	addBrand		: addBrand,
                	updateBrand		: updateBrand,
                	deleteBrand		: deleteBrand,
                	findBrandById	: findBrandById,
                	findBrandByName	: findBrandByName,
                	findAll			:findAll
                });                
                return service;
                
                /** public methods**/
                /** create **/
                function addBrand(data ) {
                    var request = $http({
                        method: 'POST',
                        url: api_endpoint + 'addBrand',
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** update **/
                function updateBrand( data ) {
                    var request = $http({
                        method: 'PUT',
                        url: api_endpoint +'updateBrand',
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** delete **/
                function deleteBrand(brandId ) {
                    var request = $http({
                        method: 'DELETE',
                        url: api_endpoint + 'deleteBrand/' + id,
                        headers: {
                        	'ssid': authManager.getsessionId
                        }                        
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function findBrandById(code,brandId) {
                    var request = $http({
                        method: 'GET',
                        url: 	api_endpoint + code + '/' + brandId,
                        headers: {
                        	'ssid': auurlthManager.getsessionId
                        }                         
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function findBrandByName(name) {
                	 var request = $http({
                         method: 'GET',
                         url	: 	api_endpoint + name,
                         headers: {
                         	'ssid': authManager.getSessionId
                         }                            
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function findAll() {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint + 'findAll',
                         headers: {
                         	'ssid': authManager.getsessionId
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