(function(){
	'use strict';
	angular.module('cart.service', [])
	/**
	 * cart service to handler rest call to cart service
	 */
	
	.service('cartService', cartService);
	
	cartService.$inject = ['$http', '$q', 'sessionService', 'URL', '$cookies', 'COOKIE_EXPIRES','$log'];
	
	/** @ngInject */
	function cartService( $http, $q, sessionService, URL, $cookies,COOKIE_EXPIRES,$log) {
	
	var endpoint = '/api/v1/cart/';
	 
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getCurrentCart		: getCurrentCart,
	  		cartAdd				: cartAdd,
	  		cartUpdate			: cartUpdate,
	  		deleteItem			: deleteItem,
	  		addBillingAddress	: addBillingAddress,
	  		addShippingAddress	: addShippingAddress,

	  		getShipmodeList		: getShipmodeList,
	  		addShipmode			: addShipmode,
	  		getPaymethodList	: getPaymethodList,
	  		addPaymethod		: addPaymethod,
	  		createPayment		: createPayment,
	    	addPaymentInfo		: addPaymentInfo, 
	  		deleteCart			: deleteCart
	    });                
	    return service;
	
	    /** public methods**/
	    
	    /**
	     * @return 
	     */
	    function getCurrentCart() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService getCurrentCart() ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: URL + endpoint , 
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param sku 					sku to send
  	     * @param quantity 		        quantity to send
	     * @return 
	     */
	    function cartAdd(sku ,quantity) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService cartAdd(sku ,quantity) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint +'cartItem/', 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/x-www-form-urlencoded'
			            },
			            data: 'sku='+sku+'&qty='+quantity
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	   
	    /**
	     * @param cartItem
	     * @return 
	     */
	    function cartUpdate( cartItem ) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService cartUpdate(cartItem) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'PUT',
			             url: URL + endpoint + 'cartItem/' + cartItem.id,
			             data: cartItem,
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param itemId
	     * @return 
	     */
	    function deleteItem(itemId) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService deleteItem(itemId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'DELETE',
			             url: URL + endpoint + 'cartitem/' + itemId,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	   
	    /**
	     * @param billingAddress
	     * @return 
	     */     
	    function addBillingAddress(billingAddress) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addBillingAddress(billingAddress) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + 'address/billing',
			             data: billingAddress, 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param shippingAddress
	     * @return 
	     */     
	    function addShippingAddress(shippingAddress) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addShippingAddress(shippingAddress) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + 'address/shipping',
			             data: shippingAddress, 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }

	    
	    /**
	     * @param shipmodeId
	     * @return 
	     */     
	    function addShipmode(shipmodeName) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addShipmode(shipmodeId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'PUT',
			             url: URL + endpoint + 'shipmode/' + shipmodeName,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @return list of shipmode
	     */     
	    function getShipmodeList() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService getShipmodeList() ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: URL + endpoint + 'shipmode',
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param paymethodId
	     * @return 
	     */     
	    function addPaymethod(paymethodName) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addPaymethod(paymethodId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'PUT',
			             url: URL + endpoint + 'paymethod/' + paymethodName,
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
	    function createPayment() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addPaymethod(paymethodId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + 'payment',
			             data:{},
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
	    function addPaymentInfo(paidData) {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService addPaymethod(paymethodId) ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'PUT',
			             url: URL + endpoint + 'payment',
			             data: paidData,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @return list of paymethod
	     */     
	    function getPaymethodList() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService getPaymethodList() ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: URL + endpoint + 'paymethod',
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }	    
	    
	    /**
	     * @return a new currentCart
	     */     
	    function deleteCart() {
	    	var serviceSSID = sessionService;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug('cartService deleteCart() ssid nel promise '+sessionId) ;
	    			$http({
			             method: 'DELETE',
			             url: URL + endpoint,
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
	            return( $q.reject( 'An unknown error occurred.' ) );
	        }
	        /** Otherwise, use expected error message.**/
	        return( $q.reject( response.data.message ) );
	    }
	    /** 
	     * I transform the successful response, unwrapping the application data
	     *from the API response payload.                
	     */
	    function handleSuccess( response ) {
        	var serviceSSID = sessionService;
        	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES);  /** expire 20 minut **/
	        return( response.data.responseObject);
	    }
}
})();