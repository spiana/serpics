 var app = angular.module("product.service", ['serpics.config'])
 
app.service("productService",['$http', '$q', 'serpicsServices', 'URL', 'COOKIE_EXPIRES', '$cookies','$log', function( $http, $q, serpicsServices, URL,COOKIE_EXPIRES, $cookies,$log) {
	
	var endpoint = '/jax-rs/productService/';
	 
	     /** Return public API. (loki java interface)**/
	     var service =({
	    	 
	     		getProduct	  			: getProduct,                   
	     		getCategoryProduct 		: getCategoryProduct,
	     		getProductByName		: getProductByName,
	     		findByCategory			: findByCategory,
	     		findByBrand				: findByBrand,
	     		findBySearch			: findBySearch,
	     		findAll			  		: findAll
	     });                
	     return service;
	     
	   /** public methods**/
	    
	    /**
	     * @param productId
	     * @return 
	     */      
	     function getProduct(productId) {
	    	 
	    	 var serviceSSID = serpicsServices;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint +  +'product/' + productId,
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
	    	 var serviceSSID = serpicsServices;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 'category/'+ productId,
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
	    	 var serviceSSID = serpicsServices;
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: URL + endpoint + 	  'name/' + productName ,
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
	     function findByCategory(categoryId, page, size) {
	    	 var serviceSSID = serpicsServices;
	    	 var findByCategoryUrl='';
	    	 if (arguments.length === 0 || arguments.length === 1 ) {
	    		 findByCategoryUrl= URL + endpoint +   'pageCategory/' + categoryId;
	    		 }else{
	    			 findByCategoryUrl = URL + endpoint +   'pageCategory/' + categoryId + '?page=' + page + '&size=' + size;
	    		 }
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: 	findByCategoryUrl,
	    				 headers: {
	    					 'ssid': sessionId
	    					 }
	    			 }).then(handleSuccess, handleError).then(resolve, reject);
	    		 });
	    	 });
	     }
	     
	   /**
	     * @param searchText
	     * @return 
	     */         
	     function findBySearch(searchText, page, size) {
	    	 var serviceSSID = serpicsServices;
	    	 var findBySearchUrl='';
	    	 if (arguments.length === 1 || arguments.length === 2 ) {
	    		 findBySearchUrl= URL + endpoint + 'search';
	    		 }else{
	    			 findBySearchUrl = URL + endpoint + 'search' + '?searchText=' + searchText + '&page=' + page + '&size=' + size;
	    		 }
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: findBySearchUrl,
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
		     function findByBrand(brandId, page, size) {
		    	 var serviceSSID = serpicsServices;
		    	 var findByBrandUrl='';
		    	 if (arguments.length === 1 || arguments.length === 2 ) {
		    		 findByBrandUrl= URL + endpoint + 'pageBrand/' + brandId;
		    		 }else{
		    			 findByBrandUrl = URL + endpoint + 'pageBrand/' + brandId + '?page=' + page + '&size=' + size;
		    		 }
		    	 return $q(function(resolve, reject) {
		    		 serviceSSID.getSessionId().then(function(sessionId){
		    			 $log.debug("session Id nel promise"+sessionId) ;
		    			 $http({
		    				 method: 	'GET',
		    				 url: findByBrandUrl,
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
	     function findAll(page, size) {
	    	 
	    	 var serviceSSID = serpicsServices;
	    	 var findAllUrl='';
	    	 if (arguments.length === 0 || arguments.length === 1 ) {
	    		 findAllUrl= URL + endpoint;
	    		 }else{
	    			 findAllUrl = URL + endpoint +  '?page=' + page + '&size=' +size;
	    		 }
	    	 
	    	 return $q(function(resolve, reject) {
	    		 serviceSSID.getSessionId().then(function(sessionId){
	    			 $log.debug("session Id nel promise"+sessionId) ;
	    			 $http({
	    				 method: 	'GET',
	    				 url: findAllUrl,
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
    	var serviceSSID = serpicsServices;
    	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES)  /** expire 20 minut **/
        return( response.data.responseObject);
    }
}]);
