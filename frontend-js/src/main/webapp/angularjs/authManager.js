var app = angular.module("AuthManager",  ['ngCookies'])

	//.constant('api_endpoint', 	'http://localhost:8080/jax-rs/auth/connect/default-store')

 app.service("authManagerService",
        function( $http, $q ,$cookies,$log,api_endpoint) {
 
            /** Return public API. (interfaace public service) **/
          	var service =   ({
            	getSessionId: getSessionId
            });                
            return service
            
           	
            /** public methods**/
            /**
             * @param endpoint               
             * @return session id 
             */ 
            function getSessionId(endpoint) {
            	
                return $http({
                    method: 'GET',
                    url: endpoint 
                  });
            	
            }                
        
            /** helper method for cookie life cycle expires**/ 
            /**
             * @param nameCookie  	a name of a cookie
             * @param cookieValue 	a value of cookie
             * @param expires		life time of a cookie 
             * @param  
             */
            function setCookie(nameCookie,cookieValue,expires) {
            	
            	var lifeTime = new Date();
        		var now = new Date();
        		lifeTime.setTime(now.getTime() + (parseInt(expires) * 60000));
            	
        		$cookies.put(nameCookie, cookieValue,{
            		  expires: lifeTime.toGMTString() 
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
            	setCookie('ssid',response.data,30)  /** expire 30 minut **/             	
            	return response.data;
            }
        }
    )
        

