var httpSerpicsServicesMocks = angular.module('http.serpics.services.mocks', ['ngMock']);

httpSerpicsServicesMocks.run(function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var customer = {};
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });

});