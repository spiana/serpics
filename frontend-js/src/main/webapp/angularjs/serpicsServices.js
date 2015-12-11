var app = angular.module("serpics.Services", ['ngCookies','serpics.config','serpics.authentication'])

app.service("serpicsServices", function( $http, $q ,$cookies,URL,COOKIE_EXPIRES,STORE) {
 
	var promiseSession = null;
    var endpoint   	= '/jax-rs/auth/connect/' 
    	
        /** Return public API. (interface public service) **/
      	var service =   ({
        	getSessionId:	getSessionId,
        	setCookie:		setCookie,
        	removeCookie:	removeCookie
        });                
        return service
        
        
        /**
         * @return Promise of sessionId 
         */ 
        function getSessionId(){
        	
        	var sessionCookie=getcookie();
    	    if(sessionCookie===null){
    	    	console.log('serpicsServices getSessionId(): ssid non presente nel cookie');
    	    	console.log("serpicsServices getSessionId(): this.promiseSession: "+this.promiseSession);
    	    	console.log("serpicsServices getSessionId(): promiseSession test=null: "+(promiseSession==null));
    	    	
    	    	if(promiseSession===null){
    	    		console.log("serpicsServices getSessionId(): ssid prima della chiamata getCallSessionId() "+promiseSession);
    	    		promiseSession = getCallSessionId();
    	    		console.log("serpicsServices getSessionId(): ssid dopo chiamata getCallSessionId() "+promiseSession);
    	    		return promiseSession;
    	    	}else{
    	    		console.log('serpicsServices getSessionId(): ssid gia richiesto al server');
    	    		return promiseSession;
    	    		}
    	    }else{
    	    	console.log('serpicsServices getSessionId(): ssid presente nel cookie'+sessionCookie);
    	    	var defer = $q.defer();
    	    	defer.resolve(sessionCookie);
    	    	return defer.promise;
    	    	}
    	    
        };
        
        
        function getcookie() {
        	var sessionId = null;
        	if ($cookies.get('ssid')) {
        		sessionId = $cookies.get('ssid');
        		console.log('serpicsServices getcookie(): ssid from cookie ->'+ sessionId);
        		}
        	return sessionId;
        	
        };
        
        /**
         * @param endpoint               
         * @return session id 
         */ 
        function getCallSessionId() {
        	var request = $http({
        		method: 'GET',
        		url: URL + endpoint + STORE
        		});
        	return( request.then( handleSuccess, handleError ) );
        	
        };          
    
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
        };
        
        /** helper method for cookie remove**/ 
        /**
         * @param nameCookie  	a name of a cookie
         * @param  
         */
        function removeCookie(nameCookie) {
        	
    		$cookies.remove(nameCookie);
        };
        
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
        };
        
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
        function handleSuccess( response ) {
        	setCookie('ssid',response.data,COOKIE_EXPIRES)  /** expire 20 minut **/    
        	promiseSession = null;
        	return response.data;
            }
        });