describe("Testing serpics.services module:", function() {
	
	'use strict';
	
//	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	var $log;

	//1 Dichiaro i moduli che mi servono per i test
	beforeEach(module('ngCookies','serpics.services','http.serpics.services.mocks', function($provide) {
    
		// Do some provider configuration here
		
		$provide.value('$log', mockedLog);
  
	}));
	
	//2 inietto il servizio da testare
	beforeEach(inject(function(_$log_){
	
	    // The injector unwraps the underscores (_) from around the parameter names when matching
		$log = _$log_;

	}));
	


	//3 controllo dopo ogni test che non ci siano chiamate in sospeso
	afterEach(inject(function($httpBackend) {
		// this fails the test if any methods were not
		// flushed to the $http API
		$httpBackend.verifyNoOutstandingRequest();
		
	    // this fails the test if you fail to call the
	    // $http API with one of your expected URLs
	    $httpBackend.verifyNoOutstandingExpectation();
	    
//	    Resets all request expectations, but preserves all backend definitions.
//	    Typically, you would call resetExpectations during a multiple-phase 
//	    test when you want to reuse the same instance of $httpBackend mock.
	    $httpBackend.resetExpectations
	    
	}));
	
	
	it('serpicsServices getSessionId() without sessionId into cookie',inject(function(serpicsServices,$cookies,$httpBackend){
		
		$cookies.remove('ssid');
		
		expect(serpicsServices.getSessionId()).not.toBeNull();
		
		var ssidExpected='12345';
		
		serpicsServices.getSessionId().then(function(response){
			$log.debug('serpicsServices getSessionId() test without sessionId into cookie Response JSON.stringify(response)'+JSON.stringify(response));
//			expect(response).toBeDefined();
//			expect(response).toEqual("12345");
			expect(response).toEqualMessage(ssidExpected,"il valore del sessionid deve essere uguale a "+response);
			
		})
//		.catch(function(error){
//			$log.debug('error response'+JSON.stringify(error));
//			expect(error).not.toBeNull();
//			
//		});
		
		$httpBackend.flush();

	}));
		
	
	it('serpicsServices getSessionId() toHaveBeenCalled() ',inject(function(serpicsServices){
			
		// Jasmine spy over the serpicsServices service.
		// Since we provided a fake response already we can just call through.
		//*Test promise
		spyOn(serpicsServices, 'getSessionId').and.callThrough();
		serpicsServices.getSessionId();
		//*Test promise
		expect(serpicsServices.getSessionId).toHaveBeenCalled();

	}));
	
	it('serpicsServices setCookie(nameCookie,cookieValue,expires) and removeCookie(nameCookie)',inject(function(serpicsServices,$cookies){
		
		var ssid='ssid';
		var ssidValue='123456'
		var min = '20';

		serpicsServices.setCookie(ssid,ssidValue,min);
		
		
		expect($cookies.get(ssid)).toEqual('123456');
		
		serpicsServices.removeCookie(ssid);
		
		expect($cookies.get(ssid)).not.toBeDefined();
		
	}));
	
	it('serpicsServices getSessionId() with sessionId into cookie',inject(function(serpicsServices,$cookies,$httpBackend){
		
	    var lifeTime = new Date();
		var now = new Date();
		var ssid='ssid';
		var ssidValue='ssidpresentenelcookie'
		var min = '20';
		
		lifeTime.setTime(now.getTime() + (parseInt(min) * 60000));
    	
		$cookies.put('ssid', ssidValue,{expires: lifeTime.toGMTString()});
		
		
		expect(serpicsServices.getSessionId()).not.toBeNull();
		
		spyOn($httpBackend, 'whenGET').and.callThrough();
		
		
		serpicsServices.getSessionId().then(function(response){
			$log.debug('serpicsServices getSessionId() test with sessionId into cookie Response JSON.stringify(response)'+JSON.stringify(response));
			expect(response).toEqualMessage(ssidValue,"il valore del sessionid deve essere uguale a "+response);
			
		})
//		.catch(function(error){
//			$log.debug('Error Response'+JSON.stringify(error))
//			expect(error).not.toBeNull();
//		});

		//*Test $httpBackend
		expect($httpBackend.whenGET).not.toHaveBeenCalled();
		
		//not called
//		$httpBackend.flush();

	}));
	
	
	it('serpicsServices getSessionId() without sessionId into cookie simulate 400 response',inject(function(serpicsServices,$cookies,$httpBackend){
		

		var status = 400;
		var response = "connection timeout";		
		
		$cookies.remove('ssid');
		
		
		expect(serpicsServices.getSessionId()).not.toBeNull();
		
		spyOn($httpBackend, 'expectGET').and.callThrough();
		
		
		$httpBackend.expectGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
			return [400];
			
		});
		
		
		serpicsServices.getSessionId().then(function(response){
			$log.debug('serpicsServices getSessionId() test with sessionId into cookie simulate 400 response (response)'+JSON.stringify(response));
			expect(response).toBeNull();
			
		})
//		.catch(function(error){
//			$log.debug('Error Response'+JSON.stringify(error));
//			expect(error).not.toBeNull();
//		});

		$httpBackend.flush();
		
		//*Test $httpBackend
		expect($httpBackend.expectGET).toHaveBeenCalled();
		
		

	}));
	
});