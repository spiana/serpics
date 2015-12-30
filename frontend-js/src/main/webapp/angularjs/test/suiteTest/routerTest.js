describe("Testing serpics.router module:", function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('serpics.router','http.customer.mocks','ui.router','customer.service', function($provide) {
			
	
	    	// Do some provider configuration here
			$provide.value('$log', mockedLog);
			
			$provide.value('$localStorage', mockStorage = { value3: []});
			$provide.value('$stateParams', stateParams = { param1: 1234, param2: "true"});
	  
		}));
	
    //controllo dopo ogni test che non ci siano chiamate in sospeso
	afterEach(inject(function($httpBackend) {
		// this fails the test if any methods were not
		// flushed to the $http API
		$httpBackend.verifyNoOutstandingRequest();
		
	    // this fails the test if you fail to call the
	    // $http API with one of your expected URLs
	    $httpBackend.verifyNoOutstandingExpectation();
	    
	}));
	
	beforeEach(inject(function(_$log_){
		
	    // The injector unwraps the underscores (_) from around the parameter names when matching
		$log = _$log_;
    
	}));

	beforeEach(inject( function($q,serpicsServices,$cookies){


		spyOn(serpicsServices, "getSessionId").and.callThrough().and.callFake(function() {
			$log.debug('spyOn: getSessionId');
	        var deferred = $q.defer();
	        deferred.resolve('mocked-SessionId');
	        $log.debug('spyOn: getSessionId test'+JSON.stringify(deferred));
	        return deferred.promise;
		});
		
		spyOn(serpicsServices, "setCookie").and.callThrough().and.callFake(function() {
			$log.debug('spyOn: setCookie');
        	var lifeTime = new Date();
    		var now = new Date();
    		var cookieValue = 'spyOn-cookie';
    		var nameCookie = 'spyOn-nameCookie';
    		var expires = 20;

    		lifeTime.setTime(now.getTime() + (parseInt(expires) * 60000));
    		
    		$log.debug('spyOn: setCookie(nameCookie,cookieValue,expires) '+cookieValue);
    		$cookies.put(nameCookie, cookieValue,{
        		  expires: lifeTime.toGMTString() 
    		});
	        
	    });
		
	  }));

	xit('serpics.router with mocked getSessionId function', inject(function($httpBackend)  {
	       mockStorage.assetInfo[stateParams.value3] = 'resolved-data';
	       var s = state.get('stateB.details');
	       expect(injector.invoke(s.resolve.value3)).toEqual('resolved-data');
	   }));
	
});



   
   
   