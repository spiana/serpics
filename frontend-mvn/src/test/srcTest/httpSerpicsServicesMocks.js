var httpSerpicsServicesMocks = angular.module('http.serpics.services.mocks', ['ngMock']);

httpSerpicsServicesMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var customer = {};
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });

}]);