var httpCustomerMocks = angular.module('http.customer.mocks', ['ngMock']);

httpCustomerMocks.run(function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var customer = {};

	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });

	
	//***CustomerService rest-api endpoint***//
	
	//getCurrent
	$httpBackend.whenGET('http://localhost:8080/jax-rs/customerService/getCurrent').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	
	//login endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/customerService/login?username=user&password=password').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//logout endpoint
	$httpBackend.whenPOST('http://localhost:8080/jax-rs/customerService/logout').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
});




