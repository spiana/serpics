var app = angular.module("category", ['AuthManager'])

.constant('api_endpoint', "http://localhost:8080/jax-rs/categoryService/")

 app.service("categoryService",['authManager',
            function( $http, $q ,authManager,api_endpoint) {
	 
                /** Return public API. (like java interface)**/
                var service = ({
                	createCategory	  : createCategory,
                	createParent	  : createParent,
                	addParent 		  : addParent,
                	updateCategory	  : updateCategory,
                	deleteCategory	  : deleteCategory,                   
                    getCategoryById	  : getCategoryById,
                    getCategoryByCode : getCategoryByCode,
                    getTop			  : getTop,
                    getChild		  : getChild,
                    findAll			  :findAll
                });                
                return service;
                
                /** public methods**/
                /** create **/
                function createCategory( data ) {
                    var request = $http({
                        method: 'POST',
                        url: api_endpoint + 'create',
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** create **/
                function createParent( parent , data ) {
                    var request = $http({
                        method: 'POST',
                        url: api_endpoint + parent,
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** create **/
                function addParent( child,parent,data ) {
                    var request = $http({
                        method: 'POST',
                        url: api_endpoint + 'addParent/' + child +'/' + parent,
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** update **/
                function updateCategory( data ) {
                    var request = $http({
                        method: 'PUT',
                        url: api_endpoint + 'update',
                        headers: {
                        	'ssid': authManager.getsessionId
                        },   
                        data: data
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** delete **/
                function deleteCategory(categoryId) {
                    var request = $http({
                        method: 'DELETE',
                        url: api_endpoint + 'delete/' + categoryId,
                        headers: {
                        	'ssid': authManager.getsessionId
                        }                        
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
               
                /** read **/      
                function getTop() {
                	 var request = $http({
                         method: 'GET',
                         url	: 	api_endpoint + 'top',
                         headers: {
                         	'ssid': authManager.getSessionId
                         }                            
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function getCategoryById(categoryId) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint  + categoryId,
                         headers: {
                         	'ssid': authManager.getsessionId
                         }                         
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function getCategoryByCode(code,category) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint +  code + '/' + category,
                         headers: {
                         	'ssid': authManager.getsessionId
                         }                         
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /** read **/      
                function getChild(parent) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint + 'getChild/' + parent,
                         headers: {
                         	'ssid': authManager.getsessionId
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