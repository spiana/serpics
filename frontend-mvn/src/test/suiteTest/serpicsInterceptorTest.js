describe('Testing serpics.interceptor module:', function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		
	beforeEach(module('serpics.interceptor','http.serpics.interceptor.mocks','ngCookies','serpics.router','ui.router', function($provide) {
		
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
		
    //controllo dopo ogni test che non ci siano chiamate in sospeso
	afterEach(inject(function($httpBackend) {
		// this fails the test if any methods were not
		// flushed to the $http API
		$httpBackend.verifyNoOutstandingRequest();
		
	    // this fails the test if you fail to call the
	    // $http API with one of your expected URLs
	    $httpBackend.verifyNoOutstandingExpectation();
	    
	}));
	
	it('serpicsInterceptor should return a 200 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		$http.get('http://localhost:8080/api/v1/auth/connect/200').then(function(response){risposta=response;},function(response){});
		
		$httpBackend.flush();

	    expect(risposta.data).toBe('12345');
	    expect(risposta.status).toBe(200);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.home');
	    expect($state.current.url).toBe('/home');
	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
		
	}));
	
	it('serpicsInterceptor should return a 500 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/api/v1/auth/connect/500').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(500);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.500');
	    expect($state.current.url).toBe('/500');
	    expect($state.current.templateUrl).toBe('html/template/500.html');
	
	}));
	
	it('serpicsInterceptor should return a 403 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		$scope= $rootScope.$new();
		var called = false;
		
		$scope.$on('event:sessiondId-expired', function() {
			$log.debug('mocked serpicsApp');
			called= true;
			
		});
		
		spyOn($rootScope, '$broadcast').and.callThrough();
		
		
		var risposta= {};
		
		$http.get('http://localhost:8080/api/v1/auth/connect/403').then(function(response){risposta=response;},function(response){risposta=response;});
		
		$httpBackend.flush();
		
		expect($rootScope.$broadcast).toHaveBeenCalled();
		
		expect(called).toEqual(true);

	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
	
	}));
	
	it('serpicsInterceptor should return a 401 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/api/v1/auth/connect/401').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(401);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.login');
	    expect($state.current.url).toBe('/login');
	    expect($state.current.templateUrl).toBe('html/template/login.html');
	
	}));
	
	it('serpicsInterceptor should return a 402 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/api/v1/auth/connect/402').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(402);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.home');
	    expect($state.current.url).toBe('/home');
	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
	
	}));
	
	it('serpicsInterceptor should return a 404 status', inject(function(serpicsInterceptor,$httpBackend,$http,$state) {
		
		var risposta= {};
		
		$http.get('http://localhost:8080/api/v1/auth/connect/404').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(404);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.404');
	    expect($state.current.url).toBe('/404');
	    expect($state.current.templateUrl).toBe('html/template/404.html');
	
	}));
	
	it('serpicsInterceptor httpbuffer testing', inject(function(serpicsInterceptor,$httpBackend,$http,$state,serpicsHttpBuffer,$q) {
		
		var risposta= {};
		var buffer = {};
		var deferred = $q.defer();
		
		$http.get('http://localhost:8080/api/v1/auth/connect/404').then(function(response){},function(response){risposta=response;});
		
		$httpBackend.flush();
		
    	buffer = serpicsHttpBuffer.append(risposta.config, deferred);
    	
    	$log.debug('buffer'+JSON.stringify(buffer));
		
	    expect(risposta.data).toBe('54321');
	    expect(risposta.status).toBe(404);
	    expect(risposta.config.method).toBe('GET');
	    
	    expect($state.current.name).toBe('shop.404');
	    expect($state.current.url).toBe('/404');
	    expect($state.current.templateUrl).toBe('html/template/404.html');
	
	}));

});
