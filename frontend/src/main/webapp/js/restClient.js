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

RestClient.VERSION = '1.0.0.'

	RestClient.DEFAULTS = {
	protocol : [ 'http', 'https', 'ftp' ],
	method : [ 'GET', 'POST', 'PUT', 'DELETE' ],
	domain : 'localhost',
	port : '9090',
	defaultpath : '/jax-rs/auth/connect/',
	path : '/jax-rs/',
	defaultstore : 'default-store',
	timeout : '30'
}

/** RestClient * */

function RestClient(){
	this.executeGetCategory('Application/json','Application/json','categoryService')
	this.executeGetChildCategoryById('Application/json','Application/json','categoryService','/getChild/2')
}

RestClient.prototype = {
	constructor : RestClient,
	
	auth:{},
	headers:{},
	category:[],
	childCategory:[],
	method:null,	
	ssid:null,
	contentType:null,
	accept:null,
	response:null,

	getResponse: function(){
		return this.response
	},	
	setResponse: function(response){
		this.response = response
	},
	setRequestParam : function(contentType, accept) {

		$.ajaxSetup({
			beforeSend : function(xhrObj) {
				xhrObj.setRequestHeader("Content-Type", contentType);
				xhrObj.setRequestHeader("Accept", accept);
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
	makeServiceUrl : function(service) {

		return (RestClient.DEFAULTS.protocol[0].concat('://')
				.concat((RestClient.DEFAULTS.domain.concat(':')
						.concat(RestClient.DEFAULTS.port
								.concat(RestClient.DEFAULTS.path
										.concat(service))))))
	},
	makeServiceUrlWithParams : function(service, params) {

		return (RestClient.DEFAULTS.protocol[0].concat('://')
				.concat((RestClient.DEFAULTS.domain.concat(':')
						.concat(RestClient.DEFAULTS.port.concat(
								RestClient.DEFAULTS.path.concat(service))
								.concat(params)))))
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

	executeGetCategory : function(contentType, accept,service) {

		if (!this.ssid) {
			this.ssid = this.connect()
		}

		var randomSSId = this.ssid
		var categories = []
		this.setRequestParam(contentType, accept)	
				
			$.ajax({
			url : this.makeServiceUrl(service),
			type : RestClient.DEFAULTS.method[0],
			dataType : 'json',
			async : true,
			cache : false,
			context:this,
			headers : {
				"ssid" : randomSSId
			},success:function(data,status,jqXHR){
				categories = data.responseObject.content	
				categories.forEach(function(entry){
					buildNavSingleItem(entry.code).appendTo($('div.category-products'))
					
				})
			}			
		})
		
		this.category = categories
		
	},
	executeGetChildCategoryById : function(contentType, accept,service,params) {//per il momento dati mokkati

		if (!this.ssid) {
			this.ssid = this.connect()
		}

		var randomSSId = this.ssid
		var childCategories = []
		this.setRequestParam(contentType, accept)	
				
			$.ajax({
			url : this.makeServiceUrlWithParams(service,params),
			type : RestClient.DEFAULTS.method[0],
			dataType : 'json',
			async : true,
			cache : false,
			context:this,
			headers : {
				"ssid" : randomSSId
			},success:function(data,status,jqXHR){
				childCategories = data.responseObject	
				childCategories.forEach(function(entry){
					var i = 0;
					var anchor = $('#sportswear a')
					$( "#sportswear ul li a" ).each( function( index, element ){
					    $( this ).text(entry.code);					    
					});
				})			
			}			
		})
		
		this.childCategory = childCategories
	},
	executeGetAllCategories : function(contentType, accept,service,params) {

	}

	
}