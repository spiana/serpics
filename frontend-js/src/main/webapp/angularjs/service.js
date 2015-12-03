var app = angular.module("serpics.Services", ['ngCookies','serpics.config'])

app.service("authManagerService", function( $http, $q ,$cookies,URL,COOKIE_EXIPES) {
 
	var promiseSession = null;
    var endpoint   	= '/jax-rs/auth/connect/default-store' 
    	
        /** Return public API. (interface public service) **/
      	var service =   ({
        	getSessionId: getSessionId
        });                
        return service
        
        
        
        function getSessionId(){
        	
        	var sessionCookie=getcookie();
    	    if(sessionCookie===null){
    	    	console.log('ssid non presente nel cookie');
    	    	console.log("sessioid "+this.idendpoint+this.promiseSession);
    	    	console.log("sessioid test "+(promiseSession==null));
    	    	
    	    	if(promiseSession===null){
    	    		console.log("sessioid prima della chiamata "+promiseSession);
    	    		promiseSession = getCallSessionId();
    	    		console.log("sessioid dopo chiamata getcallsessionid "+promiseSession);
    	    		return promiseSession;
    	    	}else{
    	    		console.log('ssid gia richiesto al server');
    	    		return promiseSession;
    	    	}
    	    }else{
    	    	console.log('ssid presente nel cookie'+sessionCookie);
    	    	var defer = $q.defer();
    	    	defer.resolve(sessionCookie);
    	    	return defer.promise;
    	    }
        }
        
       
        

	        function getcookie() {
	        	var sessionId = null;
	        	if ($cookies.get('ssid')) {
	        		sessionId = $cookies.get('ssid');
	        		console.log('Serpics Controller: read session id from cookie ->'+ sessionId);
	        		}
	        	return sessionId;
	        	}
        
        
        
        /** public methods* */
        /**
         * @param endpoint               
         * @return session id 
         */ 
        function getCallSessionId() {
        	 var request = $http({
                 method: 'GET',
                 url: URL + endpoint                                     
               });
        	 return( request.then( handleSuccess, handleError ) );
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
        	setCookie('ssid',response.data,COOKIE_EXIPES)  /** expire 30 minut **/    
        	promiseSession = null;
        	return response.data;
            }
        })
        



/**
 * cart service to handler rest call to cart service
 */
app.service("cartService", function( $http, $q) {
	 
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
	    
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function getCurrentCart(endpoint, sessionId, data ) {
	        var request = $http({
	            method: 'GET',
	            url: endpoint +   'getCurrentCart/' , 
	            headers: {
	            	'ssid': sessionId
	            }
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function cartAdd( endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'POST',
	            url: endpoint +    'cartAdd',
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	   
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function cartUpdate(endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'PUT',
	            url: endpoint +   'cartUpdate',
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId                     
	     * @param data
	     * @return 
	     */
	    function deleteItem(endpoint,sessionId,data) {
	        var request = $http({
	            method: 'DELETE',
	            url: endpoint +   'deleteItem/',
	            headers: {
	            	'ssid': sessionId
	            },
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	   
	    /**
	     * @param endpoint
	     * @param sessionId                   
	     * @param data
	     * @return 
	     */     
	    function addBillingAddress(endpoint,sessionId,data) {
	    	 var request = $http({
	             method: 'POST',
	             url: 	endpoint +  'address/billing',
	             headers: {
	             	'ssid': sessionId
	             } ,
	             data: data
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId                   
	     * @param data
	     * @return 
	     */     
	    function addShippingAddress(endpoint,sessionId,data) {
	    	 var request = $http({
	             method: 'POST',
	             url:  endpoint +  'address/shipping',
	             headers: {
	             	'ssid': sessionId
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
	        return( response.data.responseObject);
	    }
});

app.service("productService",function( $http, $q) {
	 
	     /** Return public API. (loki java interface)**/
	     var service =({
	     		insert	  			: insert,
	     		insertCategory	  	: createParent,
	     		insertBrand 		: addParent,
	     		update  			: updateCategory,
	     		getProduct	  		: deleteCategory,                   
	     		deleteProduct	  	: getCategoryById,
	     		getCategory 		: getCategoryByCode,
	     		addBrand			: getTop,
	     		addCategory		  	: getChild,
	     		addprice			: addPrice,
	     		getProductByName	: getProductByName,
	     		findByCategory		: findByCategory,
	     		findByBrand			: findByBrand,
	     		findAll			  	: findAll
	     });                
	     return service;
	     
	   /** public methods**/
	    
	   /**
	    * @param endpoint
	     * @param sessionId  
	     * @param categoryId  
	     * @param brandId               
	     * @param data
	     * @return 
	     */
	     function insert( endpoint,sessionId,categoryId,brandId,data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   categoryId + '/' + brandId,
	             headers: {
	             	'ssid': sessionId
	             },   
	             data: data
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId  
	     * @param categoryId                         
	     * @param data
	     * @return 
	     */
	     function insertCategory(endpoint, sessionId, categoryId , data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   'category/' + categoryId,
	             headers: {
	             	'ssid': sessionId
	             },   
	             data: data
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                
	     * @param brandId               
	     * @param data
	     * @return 
	     */
	     function insertBrand( endpoint,sessionId, brandId,data ) {
	         var request = $http({
	             method: 'POST',
	             url: endpoint +   'brand/' + brandId ,
	             headers: {
	             	'ssid': sessionId
	             },   
	             data: data
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                        
	     * @param data
	     * @return 
	     */
	     function update( endpoint,sessionId, data ) {
	         var request = $http({
	             method: 'PUT',
	             url: endpoint +   'update',
	             headers: {
	             	'ssid': sessionId
	             },   
	             data: data
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                      
	     * @param productId
	     * @return 
	     */
	     function deleteProduct(endpoint,sessionId, productId) {
	         var request = $http({
	             method: 'DELETE',
	             url: endpoint +   'delete/' + productId,
	             headers: {
	             	'ssid': sessionId
	             }                        
	           });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    
	    /**
		 * @param endpoint
	     * @param sessionId                       
	     * @param productId
	     * @return 
	     */      
	     function getProduct(endpoint,sessionId, productId) {
	     	 var request = $http({
	              method: 'GET',
	              url: endpoint +  productId,
	              headers: {
	              	'ssid': sessionId
	              }                            
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
		 * @param endpoint
	     * @param sessionId                       
	     * @param categoryId
	     * @return 
	     */      
	     function getCategory(endpoint,sessionId, categoryId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint + 'getCategory/'+ categoryId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId 
	     * @param productId                       
	     * @param brandId
	     * @return 
	     */         
	     function addBrand(endpoint,sessionId, productId,brandId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +   'addBrand/' + productId + '/' + brandId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param productId                       
	     * @param categoryId
	     * @return 
	     */           
	     function addCategory(endpoint,sessionId, productId,category) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +  'addCategory/' + productId + '/' + category,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param productId
	     * @param data
	     * @return 
	     */             
	     function addPrice(endpoint,sessionId, productId,data) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +  'addPrice/' + productId,
	              headers: {
	              	'ssid': sessionId
	              }, 
	     	 	data: data
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	    /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param productName
	     * @return 
	     */              
	     function getProductByName(endpoint,sessionId, productName) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint + 	  'byCode/' + productName ,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	     /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param categoryId
	     * @return 
	     */              
	     function findByCategory(endpoint,sessionId, categoryId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +   'pageCategory/' + categoryId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	   /**
	     * @param endpoint
	     * @param sessionId                       
	     * @param brandId
	     * @return 
	     */         
	     function findByBrand(endpoint,sessionId, brandId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint +   'pageBrand/' + brandId,
	              headers: {
	              	'ssid': sessionId
	              }                         
	            });
	         return( request.then( handleSuccess, handleError ) );
	     }
	     
	   /**
	     * @param endpoint
	     * @param sessionId      
	     * @return 
	     */         
	     function findAll(endpoint,sessionId) {
	     	 var request = $http({
	              method: 	'GET',
	              url: endpoint + 'findAll',
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
     * from the API response payload.                
     */
    function handleSuccess( response ) {
        return( response.data.responseObject);
    }
});

app.service("orderService", function( $http, $q) {
	 
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getOrders: getOrders,
	  		addPayment:addPayment
	    	
	    });                
	    return service
	    
	    /** public methods**/
	    
	    /**
	     * @param endpoint
	     * @param sessionId      
	     * @return 
	     */
	    function getOrders(endpoint,sessionId) {
	    	
	        var request = $http({
	            method: 'GET',
	            url: endpoint +  'getOrders',
	            headers: {
	            	'ssid': sessionId
	            }
	          });
	    	
	        return( request.then( handleSuccess, handleError ) );
	    }                
	
	    /**
	     * @param endpoint
	     * @param sessionId    
	     * @param order 
	     * @param data   
	     * @return 
	     */
	    function addPayment(endpoint,sessionId,order,data) {
	    	
	        var request = $http({
	            method: 'POST',
	            url: endpoint +  '/addPayment/'+ order,
	            headers: {
	            	'ssid': sessionId
	            },   
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
	        return( response.data.responseObject);
	    }
});


/**
 * CustomerService to handler rest call to customerservice
 */
app.service("customerService", function( $http, $q) {
	 
    /** Return public API. (like java interface) **/
  	var service =   ({
  			create:							create,
  			update: 						update,
  			getCurrent: 					getCurrent,
  			updateContactAddress: 			updateContactAddress,
  			updateBillingAddress:			updateBillingAddress,
  			updateDestinationAddress: 		updateDestinationAddress,
  			addDestinationAddress:			addDestinationAddress,
  			deleteDestinationAddress:		deleteDestinationAddress   	
    });                
    return service
    
    /** public methods**/
    
    /**
     * @param endpoint
     * @param sessionId      
     * @return 
     */
    function getCurrent(endpoint,sessionId) {
    	
        var request = $http({
            method: 'GET',
            url: endpoint,
            headers: {
            	'ssid': sessionId
            }
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }                

    /**
     * @param endpoint
     * @param sessionId    
     * @param user 
     * @return 
     */
    function create(endpoint,sessionId,user) {
    	
        var request = $http({
            method: 'POST',
            url: endpoint +  '/register',
            headers: {
            	'ssid': sessionId
            },   
            user: user
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param user
     * @return 
     */
    function updateCustomer(endpoint,sessionId, user ) {
        var request = $http({
            method: 'PUT',
            url: endpoint,
            headers: {
            	'ssid': sessionId
            },   
            user: user
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param username
     * @param password  
     * @return 
     */
    function login(endpoint,sessionId, username, passoword) {
    	
        var request = $http({
            method: 'GET',
            url: endpoint +  '/login' + '?username=' + username + '&passoword=' + passoword,
            headers: {
            	'ssid': sessionId
            }
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }  
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateContactAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: endpoint + '/updateContactAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateBillingAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: endpoint + '/updateBillingAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId                
     * @param address
     * @return 
     */
    function updateDestinationAddress(endpoint,sessionId, address ) {
        var request = $http({
            method: 'PUT',
            url: endpoint + '/updateDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param user
     * @return 
     */
    function addDestinationAddress(endpoint,sessionId,address) {
    	
        var request = $http({
            method: 'POST',
            url: endpoint +  '/addDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            address: address
          });
    	
        return( request.then( handleSuccess, handleError ) );
    }
    
    /**
     * @param endpoint
     * @param sessionId
     * @param addressuid
     * @return 
     */
    function deleteDestinationAddress(endpoint,sessionId,addressuid) {
    	
        var request = $http({
            method: 'POST',
            url: endpoint +  '/deleteDestinationAddress',
            headers: {
            	'ssid': sessionId
            },   
            addressuid: addressuid
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
});