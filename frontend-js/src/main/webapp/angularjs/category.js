var app = angular.module("category", ['AuthManager','ngCookies'])

.constant('api_endpoint', "http://localhost:8080/jax-rs/categoryService/")

 app.service("categoryService",
            function( $http, $q, api_endpoint) {
	 
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
                /**
                 * @param sessionId
                 * @param data
                 */
                function createCategory(sessionId, data ) {
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
                
                /**
                 * @param sessionId
                 * @param parent
                 * @param data
                 */
                function createParent(sessionId, parent , data ) {
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
                
                /**
                 * @param sessionId
                 * @param child
                 * @param parent
                 * @param data
                 * @return 
                 */
                function addParent(sessionId, child,parent,data ) {
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
                
                /**
                 * @param sessionId                
                 * @param data
                 * @return 
                 */
                function updateCategory(sessionId, data ) {
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
                
                /**
                 * @param sessionId
                 * @param categoryId            
                 * @return 
                 */
                function deleteCategory(sessionId,categoryId) {
                    var request = $http({
                        method: 'DELETE',
                        url: api_endpoint + 'delete/' + categoryId,
                        headers: {
                        	'ssid': authManager.getsessionId
                        }                        
                      });
                    return( request.then( handleSuccess, handleError ) );
                }
                
               
                /**
                 * @param sessionId                
                 * @return 
                 */     
                function getTop(sessionId) {
                	 var request = $http({
                         method: 'GET',
                         url	: 	api_endpoint + 'top',
                         headers: {
                         	'ssid': sessionId
                         }                            
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId
                 * @param categoryId                
                 * @return 
                 */      
                function getCategoryById(sessionId,categoryId) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint  + categoryId,
                         headers: {
                         	'ssid': authManager.getsessionId
                         }                         
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId
                 * @param code
                 * @param category                
                 * @return 
                 */      
                function getCategoryByCode(sessionId,code,category) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint +  code + '/' + category,
                         headers: {
                         	'ssid': authManager.getsessionId
                         }                         
                       });
                    return( request.then( handleSuccess, handleError ) );
                }
                
                /**
                 * @param sessionId               
                 * @param parent                 
                 * @return 
                 */      
                function getChild(sessionId,parent) {
                	 var request = $http({
                         method: 	'GET',
                         url: 		api_endpoint + 'getChild/' + parent,
                         headers: {
                         	'ssid': authManager.getsessionId
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
                         url: 	/** read **/ 	api_endpoint + 'findAll',
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
        );


app.controller("categoryController",['$scope','$cookies','authManagerService','categoryService', 
                                     
    function($scope,$cookies,authManagerService,categoryService) {	
 	
	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
		var category 	= []
	  
	    
	    /** implemented service category **/ 
	    
	    /**
	     * @param ssid 		a sessionId
	     * @return 			all category pather
	     * @use 			categoryService,authManagerService
	     */
		$scope.getTop = function(ssid) {		
	       	categoryService.getTop(sessionId).then( function( response ) {
            	/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 			a sessionId
	     * @param categoryId 	category id to be retrive
	     * @return 				a category by id
	     * @use 				categoryService,authManagerService
	     */
	    $scope.getCategoryById = function(sessionId,categoryId) {		
	       	categoryService.getCategoryById(sessionId,categoryId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 			a sessionId
	     * @param 				code 
	     * @param category 		category to retrieve
	     * @return 				a category by code
	     * @use 				categoryService,authManagerService
	     */
	    $scope.getCategoryByCode = function(sessionId,code,category) {		
	       	categoryService.getCategoryByCode(sessionId,code,category).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @param parent 	a parent category
	     * @return 			all category child
	     * @use 			categoryService,authManagerService
	     */
	    $scope.getChild = function(sessionId,parent) {		
	       	categoryService.getChild(sessionId,parent).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @return 			all category 
	     * @use 			categoryService,authManagerService
	     */
	    $scope.findAll = function(sessionId) {		
	       	categoryService.findAll(sessionId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @param data 		data to send
	     * @return 			new category
	     * @use 			categoryService,authManagerService
	     */
	    $scope.createCategory = function(sessionId,data) {		
	       	categoryService.createCategory(sessionId, data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @param parent 	a parent category
	     * @param data 		data to send
	     * @return 			add parent to category
	     * @use 			categoryService,authManagerService
	     */
	    $scope.createParent = function(sessionId,parent,data) {		
	       	categoryService.createParent(sessionId, parent , data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @param child 	a sessionId
	     * @param parent 	a parent category id
	     * @param data 		data to send
	     * @return 			add parent to category of id @child
	     * @use 			categoryService,authManagerService
	     */
	    $scope.addParent = function(sessionId, child,parent,data) {		
	       	categoryService.addParent(sessionId, child,parent,data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 		a sessionId
	     * @param data 		data to send
	     * @return 			a category updated with @data params
	     * @use 			categoryService,authManagerService
	     */
	    $scope.updateCategory = function(sessionId,data) {		
	       	categoryService.updateCategory(sessionId, data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param ssid 			a sessionId
	     * @param categoryId 	a category id
	     * @return delete 		a category with id @categoryId
	     * @use 				categoryService,authManagerService
	     */
	    $scope.deleteCategory = function(sessionId,categoryId) {		
	       	categoryService.deleteCategory(sessionId,categoryId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
}])
 