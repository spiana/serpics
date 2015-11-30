var app = angular.module("serpicsService", ['ngCookies'])

app.service("authManagerService", function( $http, $q ,$cookies) {
 
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
        	 var request = $http({
                 method: 'GET',
                 url: endpoint                                     
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
        	setCookie('ssid',response.data,1)  /** expire 30 minut **/             	
        	return response.data;
            }
        })
        

/**
 * category service to handler rest call to category service
 */
 app.service("categoryService", function( $http, $q) {
 
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
	     * @param endpoint
	     * @param sessionId
	     * @param data
	     */
	    function create(endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'POST',
	            url: endpoint + 'create',
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
	     * @param parentId
	     * @param data
	     */
	    function createParent(endpoint,sessionId, parentId , data ) {
	        var request = $http({
	            method: 'POST',
	            url: endpoint +  parentId,
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
	     * @param childId
	     * @param parentId
	     * @param data
	     * @return 
	     */
	    function addParent(endpoint,sessionId, childId,parentId,data ) {
	        var request = $http({
	            method: 'POST',
	            url: endpoint + 'addParent/' + childId +'/' + parentId,
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
	    function updateCategory(endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'PUT',
	            url: endpoint + 'update',
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
	     * @return 
	     */
	    function deleteCategory(endpoint,sessionId,categoryId) {
	        var request = $http({
	            method: 'DELETE',
	            url: endpoint + 'delete/' + categoryId,
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
	    function getTop(endpoint,sessionId) {
	    	 var request = $http({
	             method: 'GET',
	             url: 	endpoint +  'top',
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
	    function getCategoryById(endpoint,sessionId,categoryId) {
	    	 var request = $http({
	             method: 	'GET',
	             url: endpoint + categoryId,
	             headers: {
	             	'ssid': sessionId
	             }                         
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId
	     * @param code
	     * @param category                
	     * @return 
	     */      
	    function getCategoryByCode(sessionId,code,category) {
	    	 var request = $http({
	             method: 	'GET',
	             url: endpoint +  code + '/' + category,
	             headers: {
	             	'ssid': sessionId
	             }                         
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param parentId                 
	     * @return 
	     */      
	    function getChild(endpoint,sessionId,parentId) {
	    	 var request = $http({
	             method: 	'GET',
	             url: endpoint +  'getChild/' + parentId,
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
	             url: endpoint +  'findAll',
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

/**
 * brand service to handler rest call to brand service
 */
app.service("brandService", function( $http, $q ) {
	 
        /** Return public API. (like java interface)**/
        var service =({
        	getBrand		: getBrand,
        	addBrand		: addBrand,
        	updateBrand		: updateBrand,
        	deleteBrand		: deleteBrand,
        	findBrandById	: findBrandById,
        	findBrandByName	: findBrandByName,
        	findAll			: findAll
        });                
        return service;
        
        /** public methods**/
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param data
         * @return 
         */
        function getBrand(endpoint,sessionId) {
            var request = $http({         
            	method:'GET',
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
         * @param data
         * @return 
         */
        function addBrand(endpoint,sessionId,data ) {
            var request = $http.post({              
                url: endpoint +   'addBrand',
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
        function updateBrand(endpoint,sessionId, data ) {
            var request = $http.put({               
                url: endpoint +  'updateBrand',
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
         * @return 
         */
        function deleteBrand(endpoint,sessionId,brandId ) {
            var request = $http({
                method: 'DELETE',
                url: endpoint +   'deleteBrand/' + id,
                headers: {
                	'ssid': sessionId
                }                        
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param code
         * @param brandId
         * @return 
         */      
        function findBrandById(endpoint,sessionId,code,brandId) {
            var request = $http.get({                
                url: endpoint +   code + '/' + brandId,
                headers: {
                	'ssid': auurlthManager.getsessionId
                }                         
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId               
         * @param name
         * @return 
         */      
        function findBrandByName(endpoint,sessionId,name) {
        	 var request = $http.get({                
                 url: 	endpoint +  name,
                 headers: {
                 	'ssid': sessionId
                 }                            
               });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param endpoint
         * @param sessionId 
         * @param page
         * @param size        
         * @return 
         */      
        function findAll(endpoint,sessionId,page,size) {
        	 var request = $http.get({                
                 url: endpoint +  'findAll?page=' + page + '&size=' +size,
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