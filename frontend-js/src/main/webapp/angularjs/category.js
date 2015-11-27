var app = angular.module("category", ['AuthManager','ngCookies'])

.constant('api_endpoint', "http://localhost:8080/jax-rs/categoryService/")

 app.service("categoryService", function( $http, $q, api_endpoint) {
 
        /** Return public API. (like java interface)**/
        var service = ({
        	create	  			: create,
        	createParent	  	: createParent,
        	addParent 		  	: addParent,
        	updateCategory	  	: updateCategory,
        	deleteCategory	  	: deleteCategory,                   
            getCategoryById	  	: getCategoryById,
            getCategoryByCode 	: getCategoryByCode,
            getTop			  	: getTop,
            getChild		  	: getChild,
            findAll			  	:findAll
        });                
        return service;
        
        /** public methods**/
        
        /**
         * @param sessionId
         * @param data
         */
        function create(sessionId, data ) {
            var request = $http({
                method: 'POST',
                url: api_endpoint + 'create',
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId
         * @param parentId
         * @param data
         */
        function createParent(sessionId, parentId , data ) {
            var request = $http({
                method: 'POST',
                url: api_endpoint + parentId,
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId
         * @param childId
         * @param parentId
         * @param data
         * @return 
         */
        function addParent(sessionId, childId,parentId,data ) {
            var request = $http({
                method: 'POST',
                url: api_endpoint + 'addParent/' + childId +'/' + parentId,
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
        function updateCategory(sessionId, data ) {
            var request = $http({
                method: 'PUT',
                url: api_endpoint + 'update',
                headers: {
                	'ssid': sessionId
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
                	'ssid': sessionId
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
                 	'ssid': sessionId
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
                 	'ssid': sessionId
                 }                         
               });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId               
         * @param parentId                 
         * @return 
         */      
        function getChild(sessionId,parentId) {
        	 var request = $http({
                 method: 	'GET',
                 url: 		api_endpoint + 'getChild/' + parentId,
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
                 url: 	api_endpoint + 'findAll',
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


app.controller("categoryController",['$scope','$cookies','authManagerService','categoryService', 
                                     
    function($scope,$cookies,authManagerService,categoryService) {	
 	
	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
	    	
			    
	    /**
	     * @param sessionId 		a sessionId
	     * @return 					all category pather
	     * @use 					categoryService,authManagerService
	     */
		$scope.getTop = function( ) {	
	    	authManagerService.getSessionId(endpoint)
            .then( function( response ) {             
            		categoryService.getTop(response).then( function( response ) {
            			$scope.category 	= response         			
            	})
            })
	    };
	    	    
	    
	    /** implemented category service **/ 
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @param data 				data to send
	     * @return 					new category
	     * @use 					categoryService,authManagerService
	     */
	    $scope.create = function(sessionId,data) {		
	       	categoryService.create(sessionId, data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @param parentId 			a parent category
	     * @param data 				data to send
	     * @return 					add parent to category
	     * @use 					categoryService,authManagerService
	     */
	    $scope.createParent = function(sessionId,parentId,data) {		
	       	categoryService.createParent(sessionId, parentId , data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @param childId 			a session id
	     * @param parentId 			a parent id 
	     * @param data 				data to send
	     * @return 					add parent to category of id @param childId
	     * @use 					categoryService,authManagerService
	     */
	    $scope.addParent = function(sessionId, childId,parentId,data) {		
	       	categoryService.addParent(sessionId, childId,parentId,data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @param data 				data to send
	     * @return 					a category updated with @data params
	     * @use 					categoryService,authManagerService
	     */
	    $scope.updateCategory = function(sessionId,data) {		
	       	categoryService.updateCategory(sessionId, data ).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 			a sessionId
	     * @param categoryId 			a category id
	     * @return delete 				a category with id @categoryId
	     * @use 						categoryService,authManagerService
	     */
	    $scope.deleteCategory = function(sessionId,categoryId) {		
	       	categoryService.deleteCategory(sessionId,categoryId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    
	    /**
	     * @param sessionId 			a sessionId
	     * @param categoryId 			category id to be retrive
	     * @return 						a category by id
	     * @use 						categoryService,authManagerService
	     */
	    $scope.getCategoryById = function(sessionId,categoryId) {		
	       	categoryService.getCategoryById(sessionId,categoryId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 			a sessionId
	     * @param code					code 
	     * @param categoryId 			category id to retrieve
	     * @return 						a category by code
	     * @use 						categoryService,authManagerService
	     */
	    $scope.getCategoryByCode = function(sessionId,code,categoryId) {		
	       	categoryService.getCategoryByCode(sessionId,code,categoryId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @param parentId 			a parent id category
	     * @return 					all category child
	     * @use 					categoryService,authManagerService
	     */
	    $scope.getChild = function(sessionId,parentId) {		
	       	categoryService.getChild(sessionId,parentId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    /**
	     * @param sessionId 		a sessionId
	     * @return 					all category 
	     * @use 					categoryService,authManagerService
	     */
	    $scope.findAll = function(sessionId) {		
	       	categoryService.findAll(sessionId).then( function( response ) {
	       		/** do stuff with response **/
            })
	    };
	    
	    
	    
	   
}])
 