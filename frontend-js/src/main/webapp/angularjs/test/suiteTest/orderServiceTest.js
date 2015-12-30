describe("Testing order.service module:", function() {
	
	var mockedLogOff = {debug:function(){}};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('order.service','http.order.mocks','serpics.services', function($provide) {
			
	
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

	  xit('orderService getOrders() with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var orders =  [];
		  orderService.getOrders().then(function(response){
			  orders = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(orders).not.toBeNull();
		  
		  expect(orders.content[0].name).toEqual('PHILIPHS');
		  expect(orders.content[0].logo).toEqual('Logo');
		  
		  $log.debug("orderService getOrders() Order: "+orders.content[0].name);

	  }));

	
	  xit('orderService addPayment() with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var brand =  {};
		  var brandId= '1';
		  
		  orderService.addPayment(brandId).then(function(response){
				  brand = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brand).not.toBeNull();
		  
		  expect(brand.name).toEqual('PHILIPHS');
		  expect(brand.logo).toEqual('Logos');
		  
		  $log.debug("orderService addPayment() Order: "+order);

	  }));
	  
	  xit('orderService placeOrder() with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var order =  {};
		  
		  orderService.placeOrder().then(function(response){
				  brand = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(order).not.toBeNull();
		  

		  
		  $log.debug("orderService placeOrder() Order: "+order);

	  }));
	
});