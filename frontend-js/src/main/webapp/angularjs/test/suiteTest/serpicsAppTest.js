describe('Testing serpics.App module:', function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		
	beforeEach(module('serpics.App','http.serpics.interceptor.mocks','serpics.services','serpics.interceptor', function($provide) {
		
		// Do some provider configuration here
		$provide.value('$log', mockedLog);

		}));		
	
	beforeEach(inject(function(_$log_,_$rootScope_, _$state_, _$injector_, $templateCache,_$stateParams_,_$location_,$httpBackend,$q) {
			
			$log = _$log_;
			$rootScope = _$rootScope_;
			$templateCache.put('html/template/shop.html', '');
			$templateCache.put('html/template/home-central.html', '');
			$templateCache.put('html/template/500.html', '');
			$templateCache.put('html/template/login.html', '');
			$templateCache.put('html/template/404.html', '');
			
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
	
		
    //controllo dopo ogni test che non ci siano chiamate in sospeso
	afterEach(inject(function($httpBackend) {
		// this fails the test if any methods were not
		// flushed to the $http API
		$httpBackend.verifyNoOutstandingRequest();
		
	    // this fails the test if you fail to call the
	    // $http API with one of your expected URLs
	    $httpBackend.verifyNoOutstandingExpectation();
	    
	}));
	
	it('should return a $scope.$on on event $rootScope.$broadcast(\'event:sessiondId-expired\', risposta)', inject(function($rootScope,serpicsServices) {
		
		var risposta= {};
				
		$rootScope.$broadcast('event:sessiondId-expired', risposta);
		
		//$log.debug('risposta'+JSON.stringify(risposta));
	    
	    expect(serpicsServices.getSessionId).toHaveBeenCalled();


	
	}));

});