describe("Testing serpics.router module:", function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('serpics.router','ui.router', function($provide) {
			
	
	    	// Do some provider configuration here
			$provide.value('$log', mockedLog);
  
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
	
	beforeEach(inject(function(_$log_,_$rootScope_, _$state_, _$injector_, $templateCache,_$stateParams_,_$location_) {
	      
		$rootScope = _$rootScope_;
		$state = _$state_;
		$stateParams= _$stateParams_
		$injector = _$injector_;
		$location = _$location_
//		$urlRouterProvider=_$urlRouterProvider_;
		
	      // We need add the template entry into the templateCache if we ever
	      // specify a templateUrl
	    $templateCache.put('html/template/shop.html', '');//abstract template put to shop.home
	    $templateCache.put('html/template/home-central.html', '');
	    $templateCache.put('html/template/product-central.html', '');
	    $templateCache.put('html/template/register.html', '');
	    $templateCache.put('html/template/category-central.html', '');
	    $templateCache.put('html/template/brand-central.html', '');
	    
	    
		
	    // The injector unwraps the underscores (_) from around the parameter names when matching
		$log = _$log_;
    
	}));
	
	it('serpics.router should respond to URL shop with abstract #', function() {
		var state = 'shop';
		
		//abstract template put to shop.home
	    expect($state.href(state)).toEqual('#');
	});
	    
	it('serpics.router should respond to URL shop with abstract #', function() {
	   
		var state = 'shop.home';
	    
	    expect($state.href(state)).toEqual('#/home');
	    expect($state.current.name).toBe('');
	    expect($state.current.url).toBe('^');
	    expect($state.current.abstract).toBe(true);
	    expect($state.current.views).toBe(null);
	    
//	    $log.debug(JSON.stringify($state.current))
	  
	});
	    
	it('serpics.router should respond to URL shop  with state.go ', function() {
			
		var state = 'shop.home';
	    
	    $state.go(state);
	    $rootScope.$digest();

	    
	    expect($state.current.name).toBe('shop.home');
	    expect($state.current.url).toBe('/home');
	    expect($state.current.templateUrl).toBe('html/template/home-central.html');
	    
	 });
	
	it('serpics.router should respond to URL nonShopPage  with  $location.path ', function() {
	    
		var state = '/nonShopPage';
        
		$location.path(state);
        
        $rootScope.$emit("$locationChangeSuccess");
        //$log.debug(JSON.stringify($location));
        expect($location.path()).toBe("/home");

	  });

	it('serpics.router should respond to URL /product/:name/:id with #/product/MOUSE/6 with $state.go', function() {
		
		var state ='shop.product';
		var name = 'MOUSE';
		var id = 6;

		
	    expect($state.href(state,{name: name, id: id})).toEqual('#/product/MOUSE/6');
	    
	    $state.go(state,{name: name, id: id});
	    
	    $rootScope.$digest();
	    
	    //$log.debug(JSON.stringify($state.current)+JSON.stringify($stateParams));
	    expect($stateParams.name).toBe('MOUSE');
	    expect($stateParams.id).toBe('6');
	    expect($state.current.name).toBe('shop.product');
	    expect($state.current.url).toBe('/product/:name/:id');
	    expect($state.current.templateUrl).toBe('html/template/product-central.html');

	});
	
	it('serpics.router should respond to URL /category/:code/:id with #/category/COMPUTER/19 with state.href', function() {
		
		var state ='shop.category';
		var name = 'COMPUTER';
		var id = 19;

		
	    expect($state.href(state,{name: name, id: id})).toEqual('#/category/COMPUTER/19');
	    
	});
	    
	it('serpics.router should respond to URL /category/:code/:id with #/category/COMPUTER/19 with $state.go', function() {
		
		var state ='shop.category';
		var name = 'COMPUTER';
		var id = 19;
		
		$state.go(state,{name: name, id: id});
	    
	    $rootScope.$digest();

	    expect($stateParams.name).toBe('COMPUTER');
	    expect($stateParams.id).toBe('19');
	    expect($state.current.name).toBe('shop.category');
	    expect($state.current.url).toBe('/category/:name/:id');
	    expect($state.current.templateUrl).toBe('html/template/category-central.html');

	});
	
	it('serpics.router should respond to URL shop.register with #shop.register with $state.href', function() {
	    var state ='shop.register';

		
	    expect($state.href(state)).toEqual('#/register');
		  
	});
	    
		
	it('serpics.router should respond to URL shop.register with #shop.register with $state.href', function() {
		    
		var state ='shop.register';
		
	    $state.go(state);
	    
	    $rootScope.$digest();
	    
	    expect($stateParams.login).toBe('shop.home');
	    expect($stateParams.register).toBe('shop.login');
	    expect($state.current.name).toBe('shop.register');
	    expect($state.current.url).toBe('/register');
	    expect($state.current.controller).toBe('loginController');
	    expect($state.current.params.login).toBe('shop.home');
	    expect($state.current.params.register).toBe('shop.login');
	    expect($state.current.templateUrl).toBe('html/template/register.html');
	  
	});
	
	it('serpics.router should respond to URL /brand/:name/:id with #/brand/NOKIA/9with $state.href', function() {
		
		var state ='shop.brand';
		var name = 'NOKIA';
		var id = 9;

		
	    expect($state.href(state,{name: name, id: id})).toEqual('#/brand/NOKIA/9');
	    
	});
	    
	it('serpics.router should respond to URL /brand/:name/:id with #/brand/NOKIA/9 with $state.go', function() {
		
		var state ='shop.brand';
		var name = 'NOKIA';
		var id = 9;
		
		$state.go(state,{name: name, id: id});
	    
	    $rootScope.$digest();

	    expect($stateParams.name).toBe('NOKIA');
	    expect($stateParams.id).toBe('9');
	    expect($state.current.name).toBe('shop.brand');
	    expect($state.current.url).toBe('/brand/:name/:id');
	    expect($state.current.templateUrl).toBe('html/template/brand-central.html');

	});
	

});



   
   
   