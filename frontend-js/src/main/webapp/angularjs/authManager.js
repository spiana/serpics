var app = angular.module("AuthManager", ['ngLoad','ngCookies'])

	.constant('api_endpoint', 	'http://localhost:8080/jax-rs/auth/connect/default-store')

 app.service("authManager","$cookie","ngLoad",
            function( $http, $q ,$cookie,authManager,ngLoad,api_endpoint) {
	 
                /** Return public API. **/
                var service =({
                	getSessionId: getSessionId
                });                
                return service;
                
                /** public methods**/
                /** create **/
                function getSessionId() {
                	if(!$cookie.get('ssid')){
                    var request = $http({
                        method: 'GET',
                        url: api_endpoint 
                      });
                	}
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
                	$cookie.put('ssid',response.data);
                	return( response.data );
                }
            }
        );





