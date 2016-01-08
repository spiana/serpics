describe('Testing serpics.interceptor module:', function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		
	beforeEach(module('serpics.interceptor','http.serpics.interceptor.mocks','ngCookies','serpics.router','ui.router', function($provide) {
		
		// Do some provider configuration here
		$provide.value('$log', mockedLog);
  
		}));		
	
	beforeEach(inject(function(_$log_,_$rootScope_, _$state_, _$injector_, $templateCache,_$stateParams_,_$location_,$httpBackend) {
			
			$log = _$log_;
			$rootScope = _$rootScope_;
			$templateCache.put('html/template/shop.html', '');
			$templateCache.put('html/template/home-central.html', '');
			$templateCache.put('html/template/500.html', '');
			$templateCache.put('html/template/login.html', '');
			$templateCache.put('html/template/404.html', '');
			
			spyOn($rootScope, '$broadcast').and.callThrough();
			
//			.and.callFake(function() {
//				$log.debug('spyOn: $broadcast');
//			});
			
		}));
	

//		
//	beforeEach(inject( function($q,serpicsServices,$cookies){
//
//
//			spyOn(serpicsServices, "getSessionId").and.callThrough().and.callFake(function() {
//				$log.debug('spyOn: getSessionId');
//		        var deferred = $q.defer();
//		        deferred.resolve('mocked-SessionId');
//		        $log.debug('spyOn: getSessionId test'+JSON.stringify(deferred));
//		        return deferred.promise;
//		    });
//			
//			spyOn(serpicsServices, "setCookie").and.callThrough().and.callFake(function() {
//				$log.debug('spyOn: setCookie');
//	        	var lifeTime = new Date();
//	    		var now = new Date();
//	    		var cookieValue = 'spyOn-cookie';
//	    		var nameCookie = 'spyOn-nameCookie';
//	    		var expires = 20;
//
//	    		lifeTime.setTime(now.getTime() + (parseInt(expires) * 60000));
//	    		
//	    		$log.debug('spyOn: setCookie(nameCookie,cookieValue,expires) '+cookieValue);
//	    		$cookies.put(nameCookie, cookieValue,{
//	        		  expires: lifeTime.toGMTString() 
//	    		});
//		        
//		    });
//			
//		  }));
	
    //controllo dopo ogni test che non ci siano chiamate in sospeso
	afterEach(inject(function($httpBackend) {
		// this fails the test if any methods were not
		// flushed to the $http API
		$httpBackend.verifyNoOutstandingRequest();
		
	    // this fails the test if you fail to call the
	    // $http API with one of your expected URLs
	    $httpBackend.verifyNoOutstandingExpectation();
	    
	}));
	
	it('should return a 200 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		$http.get('http://localhost:8080/jax-rs/auth/connect/200').then(function(response){risposta=response;},function(response){});
		
		$httpBackend.flush();

	    expect(risposta.data).toBe('12345');
	    expect(risposta.status).toBe(200);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.home');
	    expect($state.current.url).toBe('/home');
	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
		
	}));
	
	it('should return a 500 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/jax-rs/auth/connect/500').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(500);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.500');
	    expect($state.current.url).toBe('/500');
	    expect($state.current.templateUrl).toBe('html/template/500.html');
	
	}));
	
	it('should return a 403 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/jax-rs/auth/connect/403').then(function(response){risposta=response;},function(response){risposta=response;});
		
		$httpBackend.flush();
		$log.debug(JSON.stringify(risposta));
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(403);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.403');
	    expect($state.current.url).toBe('/403');
	    expect($state.current.templateUrl).toBe('html/template/403.html');
	
	}));
	
	it('should return a 401 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/jax-rs/auth/connect/401').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(401);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.login');
	    expect($state.current.url).toBe('/login');
	    expect($state.current.templateUrl).toBe('html/template/login.html');
	
	}));
	
	it('should return a 402 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/jax-rs/auth/connect/402').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(402);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.home');
	    expect($state.current.url).toBe('/home');
	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
	
	}));
	
	it('should return a 404 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/jax-rs/auth/connect/404').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(404);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.404');
	    expect($state.current.url).toBe('/404');
	    expect($state.current.templateUrl).toBe('html/template/404.html');
	
	}));

});
