/* ========================================================================
 * SERPICS LIBRARY: v1.0.0
 * @author:
 * @author link:
 * @description
 * ========================================================================
 * Copyright 2011-2015 SERPICS s.r.l.
 * Licensed under 
 * ======================================================================== */

	

'use strict';
if (typeof jQuery === 'undefined') {
	throw new Error('jQuery is required')
}

/** RestClient * */

function RestClient(){
	
}
			

	RestClient.VERSION = '1.0.0.'

	RestClient.DEFAULTS = {
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
}


RestClient.prototype = {	   
			
	constructor : RestClient,
	
	auth:{},
	headers:{},	
	method:null,	
	ssid:null,
	contentType:null,
	accept:null,
	response:null,	
    instance:null,
	
    /** singleton to prevent multiple instance of RestClient Object **/
    getInstance: function(){
		if(this.instance == null){
				this.instance = new RestClient()
				return this.instance
		}else
			return this.instance
	},
	
	setRequestParam : function() {

		$.ajaxSetup({
			beforeSend : function(xhrObj) {
				xhrObj.setRequestHeader("Content-Type", RestClient.DEFAULTS.contentType);
				xhrObj.setRequestHeader("Accept", RestClient.DEFAULTS.accept);
			}
		})
	},
	
	makeConnectUrl : function() {

		return (RestClient.DEFAULTS.protocol[0].concat('://')
				.concat((RestClient.DEFAULTS.domain.concat(':')
						.concat(RestClient.DEFAULTS.port
								.concat(RestClient.DEFAULTS.defaultpath
										.concat(RestClient.DEFAULTS.defaultstore))))))
	},			
	makeServiceUrlWithParams : function(service, params) {

		return (RestClient.DEFAULTS.protocol[0].concat('://')
				.concat((RestClient.DEFAULTS.domain.concat(':')
						.concat(RestClient.DEFAULTS.port.concat(
								RestClient.DEFAULTS.path.concat(service))
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
	connect : function() {
		
		var ssid = ''

		$.ajax({
			url : this.makeConnectUrl(),
			type : RestClient.DEFAULTS.method[0],
			dataType : 'text',
			async : false,
			cache : false,
			success : function(data) {				
				ssid = data
				return ssid
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
			url : this.makeServiceUrlWithParams(service,params),
			type : RestClient.DEFAULTS.method[0],
			dataType : RestClient.DEFAULTS.dataType,
			async : true,
			cache : false,
			context:this,
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
  }

/** unique instance for all application **/
var rest = RestClient.prototype.getInstance()