var httpCustomerMocks = angular.module('http.customer.mocks', ['ngMock']);

httpCustomerMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var customer= {"responseObject":{"updated":"2016-01-04T13:04:51 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"a0f4e309-83ca-4a80-964c-6fd682357310","id":17,
		"firstname":"Gabriele","lastname":"Gabrieles","email":"gabri@gabri.it","userType":"REGISTERED","logonid":"gabri",
		"contactAddress":{"updated":"2015-12-16T09:25:15 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"432a17ce-dcb4-493b-a8be-eda9572befd3"},
		"destinationAddress":[]}};
	
	var customerLogged = {"responseObject":{"updated":"2016-01-04T13:04:51 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"a0f4e309-83ca-4a80-964c-6fd682357310","id":16,
		"firstname":"Gabri","lastname":"Gabriele","email":"gabri@gabri.it","userType":"REGISTERED","logonid":"gabri",
		"contactAddress":{"updated":"2015-12-16T09:25:15 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"432a17ce-dcb4-493b-a8be-eda9572befd3"},
		"destinationAddress":[]}};
	
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
	    return [status,customerLogged];
	  });
	
	//logout endpoint
	$httpBackend.whenPOST('http://localhost:8080/jax-rs/customerService/logout').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//register endpoint
	$httpBackend.whenPOST('http://localhost:8080/jax-rs/customerService/register').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//updateUserData endpoint
	$httpBackend.whenPUT('http://localhost:8080/jax-rs/customerService/').respond(function(method, url, data) {
	    return [status,customer];
	  });

	//updateContactAddress endpoint
	$httpBackend.whenPUT('http://localhost:8080/jax-rs/customerService/updateContactAddress').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//updateBillingAddress endpoint
	$httpBackend.whenPUT('http://localhost:8080/jax-rs/customerService/updateBillingAddress').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//updateDestinationAddress endpoint
	$httpBackend.whenPUT('http://localhost:8080/jax-rs/customerService/updateDestinationAddress').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//addDestinationAddress endpoint
	$httpBackend.whenPOST('http://localhost:8080/jax-rs/customerService/addDestinationAddress').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//deleteDestinationAddress endpoint
	$httpBackend.whenDELETE('http://localhost:8080/jax-rs/customerService/deleteDestinationAddress/123456').respond(function(method, url, data) {
	    return [status,customer];
	  });

	
}]);



