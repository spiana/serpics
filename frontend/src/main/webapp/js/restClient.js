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
	this.executeGetCategory('categoryService',buildMenuCategoryLevelOne,error)
	this.executeGetChildCategory('categoryService','/getChild/4', buildSubMenuCategory,error)
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
	setSSIDInToCookie: function(ssid){
		this.ssid = sid
	},
	getSSIDFromToCookie: function(){
		return this.ssid
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
			}
		})       
		return ssid
	},

	executeRestFulGetMethod : function(service,params,callbackSuccess,callbackError) {				
		
		if (!this.ssid) {
			this.ssid = this.connect()
		}
	
		this.setRequestParam()	
				
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
				callbackSuccess(data)
			},
			error:function(){
				callbackError()
			}			
		})		
		
	},
	executeGetCategory : function(service,callbackSuccess,callbackError) {
		this.executeRestFulGetMethod(service,'/',callbackSuccess,callbackError) 
	},	
	executeGetChildCategory : function(service,params,callbackSuccess,callbackError) {
		this.executeRestFulGetMethod(service,params,callbackSuccess,callbackError) 
	},	
  }
