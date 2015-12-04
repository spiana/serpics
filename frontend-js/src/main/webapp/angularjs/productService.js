 var app = angular.module("product.service", ['serpics.config'])
 
app.service("productService",function( $http, $q, authManagerService, URL) {
	
	var endpoint = '/jax-rs/productService/';
	 
	     /** Return public API. (loki java interface)**/
	     var service =({
	    	 
	     		getProduct	  			: getProduct,                   
	     		getCategoryProduct 		: getCategoryProduct,
	     		getProductByName		: getProductByName,
	     		findByCategory			: findByCategory,
	     		findByBrand				: findByBrand,
	     		findAll			  		: findAll
	     });                
	     return service;
	     
	   /** public methods**/
	    
	    /**
	     * @param productId
	     * @return 
	     */      
	     function getProduct(productId) {
	    	 
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint +  productId,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	    /**
	     * @param categoryId
	     * @return 
	     */      
	     function getCategoryProduct(productId) {
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 'getCategory/'+ productId,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	    /**
	     * @param productName
	     * @return 
	     */              
	     function getProductByName(productName) {
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 	  'byCode/' + productName ,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	     /**
	     * @param categoryId
	     * @return 
	     */              
	     function findByCategory(categoryId) {
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: 	URL + endpoint +   'pageCategory/' + categoryId,
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
	     function findByBrand(brandId) {
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url:	URL + endpoint +   'pageBrand/' + brandId,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }

	   /**
	     * @return 
	     */         
	     function findAll() {
	    	 
	    	 var serviceSSID = authManagerService;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 console.log("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 'findAll',
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
            return( $q.reject( "An unknown error occurred." ) );
        }
        /** Otherwise, use expected error message.**/
        return( $q.reject( response.data.message ) );
    }
    /** 
     * I transform the successful response, unwrapping the application data
     * from the API response payload.                
     */
    function handleSuccess( response ) {
        return( response.data.responseObject);
    }
});
