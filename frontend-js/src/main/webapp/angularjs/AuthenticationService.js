
var app = angular.module('serpics.Authentication',['serpics.config'])
  
.factory('authenticationService',  ['Base64', '$http', '$cookieStore', '$rootScope', '$q','serpicsServices','URL','ENDPOINT',
    function (Base64, $http, $cookieStore, $rootScope, $q, serpicsServices,URL,ENDPOINT) {
	
	
        var service = {        	
        		login:				login,
        		register:			register,
        		setCredential:		setCredential,
        		decodeCredential:	decodeCredential,
        		clearCredentials:	clearCredentials        		
        };
        
        return service;

       function login(username, password) {  
    	   
    	var serviceSSID = serpicsServices;
	       	return $q(function(resolve, reject) {
	       		
	       	serviceSSID.getSessionId().then(function(sessionId){	       		
	   	       $http({
	   	            method: 'GET',
	   	            url: URL + ENDPOINT +  'login' + '?username=' + username + '&password=' + password,
	   	            headers: {
	   	            	'ssid': sessionId
	   	            }
	   	        	}).then(handleSuccess, handleError).then(resolve, reject);	   				
	   			});   		
	       	});        	
 
        };
  
        function register(userData) {  
     	   
        	var serviceSSID = serpicsServices;
    	       	return $q(function(resolve, reject) {    	       		
    	       	serviceSSID.getSessionId().then(function(sessionId){	       		
    	   	       $http({
    	   	            method: 'POST',
    	   	            url: URL + ENDPOINT +  'register',
    	   	            headers: {
    	   	            	'ssid': sessionId
    	   	            },
    	   	            data:userData
    	   	        }).then(handleSuccess, handleError).then(resolve, reject);	   				
    	   		});   		
       		});        	
         };
            
        function setCredential(username, password,isLoggedIn) {
            var authdata = Base64.encode(username + ':' + password);
  
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    isLoggedIn:isLoggedIn,
                    authdata: authdata
                }
            };
  
            $cookieStore.put('globals', $rootScope.globals);
            $cookieStore.put('isLoggedIn', isLoggedIn);
        };
  
        
        function decodeCredential(authdata) {
        	
            var authdata = Base64.decode(authdata);
  
            var credential = authdata.split(':')
                 
            $rootScope.globals = {
                currentUser: {
                    username: credential[0],
                    password: credential[1]
                }
            };
       };
        
        function clearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $cookieStore.remove('isLoggedIn');
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
        }
        /** 
         * I transform the successful response, unwrapping the application data
         *from the API response payload.                
         */
        function handleSuccess( response ) {
            return( response.data.responseObject);
        }
  
    }])
  
.factory('Base64', function () {
  
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
  
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
  
            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
  
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
  
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
  
                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);
  
            return output;
        },
  
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
  
            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
  
            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));
  
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
  
                output = output + String.fromCharCode(chr1);
  
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
  
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
  
            } while (i < input.length);
  
            return output;
        }
    };  
});