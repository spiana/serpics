var app = angular.module("cart",  [])

	.constant('api_endpoint', 	'http://localhost:8080/jax-rs/cartService/')

 app.service("cartService", function( $http, $q ,$cookies,$log,api_endpoint) {
	 
        /** Return public API. (like java interface) **/
      	var service =   ({
      		getCurrentCart		: getCurrentCart,
      		cartAdd				: cartAdd,
      		cartUpdate			: cartUpdate,
      		deleteItem			: deleteItem,
      		addBillingAddress	: addBillingAddress,
      		addShippingAddress	: addShippingAddress,
      		
        });                
        return service
        
        /** public methods**/
        /** create **/
        function getCurrentCart( data ) {
            var request = $http({
                method: 'GET',
                url: api_endpoint + 'getCurrentCart/'              
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /** create **/
        function cartAdd(  data ) {
            var request = $http({
                method: 'POST',
                url: api_endpoint +  'cartAdd',
                headers: {
                	'ssid': authManager.getsessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
       
        /** update **/
        function cartUpdate( data ) {
            var request = $http({
                method: 'PUT',
                url: api_endpoint + 'cartUpdate',
                headers: {
                	'ssid': authManager.getsessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /** delete **/
        function deleteItem(categoryId,data) {
            var request = $http({
                method: 'DELETE',
                url: api_endpoint + 'deleteItem/',
                headers: {
                	'ssid': authManager.getsessionId
                },
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
       
        /** read **/      
        function addBillingAddress(data) {
        	 var request = $http({
                 method: 'POST',
                 url	: 	api_endpoint + 'address/billing',
                 headers: {
                 	'ssid': authManager.getSessionId
                 } ,
                 data: data
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
        	console.log("send ssid to controller: " + response.data)
        	return( response.data );
        }
    }
);


