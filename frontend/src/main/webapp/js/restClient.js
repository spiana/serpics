
var restClient = (function(document,window){
 
var instance;
return {
    getInstance: function() {
        if (!instance) {
            instance = { 
            		
		VERSION : '1.0.0.',

		DEFAULTS : {
		protocol : 			[ 'http', 'https', 'ftp' ],
		method : 			[ 'GET', 'POST', 'PUT', 'DELETE' ],
		domain : 			'localhost',
		port : 				'8080',
		defaultpath : 		'/jax-rs/auth/connect/',
		path : 				'/jax-rs/',
		defaultstore : 		'default-store',
		contentType:		'Application/json',
		accept:				'Application/json',
		dataType:			'json',
		timeout : 			'30'
	}, 
	setRequestParam : function() {

		$.ajaxSetup({
			beforeSend : function(xhrObj) {
				xhrObj.setRequestHeader("Content-Type",this.DEFAULTS.contentType);
				xhrObj.setRequestHeader("Accept", this.DEFAULTS.accept);
			}
		})
	},
	
	makeConnectUrl : function() {

		return (this.DEFAULTS.protocol[0].concat('://')
				.concat((this.DEFAULTS.domain.concat(':')
				.concat(this.DEFAULTS.port
				.concat(this.DEFAULTS.defaultpath
				.concat(this.DEFAULTS.defaultstore))))))
	},			
	makeServiceUrlWithParams : function(service, params) {

		return (this.DEFAULTS.protocol[0]
				.concat('://')
				.concat((this.DEFAULTS.domain
				.concat(':')
				.concat(this.DEFAULTS.port
				.concat(this.DEFAULTS.path
				.concat(service))
				.concat(params)))))
	},
	setPropertyInToCookie: function(nameCookie,cookieValue,expires){
		var lifeTime = new Date();
		var now = new Date();
		lifeTime.setTime(now.getTime() + (parseInt(expires) * 60000));
		document.cookie = nameCookie + '=' + escape(cookieValue) + '; expires=' + lifeTime.toGMTString() + '; path=/frontend/';
	    this.ssid = document.cookie
	},
	getPropertyFromCookie: function(nameCookie){
		
		if (document.cookie.length > 0) {
		    var init = document.cookie.indexOf(nameCookie + "=");
		    if (init != -1){
		      init = init + nameCookie.length + 1;
		      var end = document.cookie.indexOf(";",init);
		      if (end == -1)
		    	  end = document.cookie.length;
		      return unescape(document.cookie.substring(init,end));
		    }else{
		       return "";
		    }
		  }
		  return "";
	},
	supportsHTML5Storage:function() {
	    try {
	        return 'sessionStorage' in window && window['sessionStorage'] !== null;
	    } catch (e) {
	        return false;
	    }
	},
	setSessionStorageProperty: function(name,value){
		sessionStorage.setItem(name, value)
	},	
	connect : function() {        			
		
		var ssid = null
		
		$.ajax({
			url : this.makeConnectUrl(),
			type : this.DEFAULTS.method[0],
			dataType : 'text',
			async : false,
			cache : false,
			success : function(data) {				
				ssid = data
			}
		})       
		
		this.setPropertyInToCookie('ssid',ssid,30)		
	},	
	executeRestFulGetMethod : function(service,params,callbackSuccess,callbackError,other) {				
		
		if (this.getPropertyFromCookie('ssid').length < 1) {
			alert('coockie scaduto')
			this.ssid = this.connect()
		}		
		
		this.ssid = this.getPropertyFromCookie('ssid')	
		this.setRequestParam()	
		var patherId = null
		
			$.ajax({
			url : 		this.makeServiceUrlWithParams(service,params),
			type : 		this.DEFAULTS.method[0],
			dataType : 	this.DEFAULTS.dataType,
			async : 	true,
			cache : 	false,
			context:	this,
			headers : {
				"ssid" : this.ssid
    				},
    				success:function(data,status,jqXHR){
    					callbackSuccess(data,other)
    				},
    				error:function(){
    					callbackError()
    				}			
    			})		
    			
    		},
    		executeGetCategory : function(service,params,callbackSuccess,callbackError) {
    			this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError) 
    		},	
    		executeGetChildCategory : function(service,params,callbackSuccess,callbackError,other) {		
    			this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError,other) 
    		},	
    		executeGetBrand : function(service,params,callbackSuccess,callbackError) {		
    			this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError) 
    		},	
    		executeGetProduct : function(service,params,callbackSuccess,callbackError) {		
    			this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError) 
    		},	
    		executeGetProductById : function(service,params,callbackSuccess,callbackError) {		
    			this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError) 
    		},	
    
    }; 
            
} 
            return instance; /** unique instance **/
        }
    };
})(document,window);

/** instance of singleton restClient**/
var rest = restClient.getInstance();