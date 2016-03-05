var httpSerpicsServicesMocks = angular.module('http.serpics.interceptor.mocks', ['ngMock']);

httpSerpicsServicesMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var status200 = 200;
	var status500 = 500;
	var status403 = 403;
	var status401 = 401;
	var status402 = 402;
	var status404 = 404;
	
	
	var customer= {"responseObject":{"updated":"2016-01-04T13:04:51 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"a0f4e309-83ca-4a80-964c-6fd682357310","id":17,
		"firstname":"Gabriele","lastname":"Gabrieles","email":"gabri@gabri.it","userType":"REGISTERED","logonid":"gabri",
		"contactAddress":{"updated":"2015-12-16T09:25:15 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"432a17ce-dcb4-493b-a8be-eda9572befd3"},
		"destinationAddress":[]}};
	
	var dataOk = "12345";
	var dataFault = "54321";
	
	
	var customer = {};
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status200,dataOk];
	  });

	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/200').respond(function(method, url, data) {
	    return [status200,dataOk];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/500').respond(function(method, url, data) {
	    return [status500,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/403').respond(function(method, url, data) {
	    return [status403,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/401').respond(function(method, url, data) {
	    return [status401,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/402').respond(function(method, url, data) {
	    return [status402,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/404').respond(function(method, url, data) {
	    return [status404,dataFault];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/customerService/getCurrent').respond(function(method, url, data) {
	    return [status,customer];
	  });

}]);