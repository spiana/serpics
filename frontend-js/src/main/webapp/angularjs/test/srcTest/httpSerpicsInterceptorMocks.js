var httpSerpicsServicesMocks = angular.module('http.serpics.interceptor.mocks', ['ngMock']);

httpSerpicsServicesMocks.run(['$httpBackend',function($httpBackend) {
	
	var status200 = 200;
	var status500 = 500;
	var status403 = 403;
	var status401 = 401;
	var status402 = 402;
	var status404 = 404;
	
	
	var dataOk = "12345";
	var dataFault = "54321";
	
	
	var customer = {};
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/default-store').respond(function(method, url, data) {
	    return [status200,dataOk];
	  });

	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/200').respond(function(method, url, data) {
	    return [status200,dataOk];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/500').respond(function(method, url, data) {
	    return [status500,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/403').respond(function(method, url, data) {
	    return [status403,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/401').respond(function(method, url, data) {
	    return [status401,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/402').respond(function(method, url, data) {
	    return [status402,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/404').respond(function(method, url, data) {
	    return [status404,dataFault];
	  });
		
}]);