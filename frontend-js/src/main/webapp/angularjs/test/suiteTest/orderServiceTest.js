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

	  it('orderService getOrders() with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var orders =  [];
		  orderService.getOrders().then(function(response){
			  orders = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(orders).not.toBeNull();
		  
		  expect(orders.length).toEqual(4);
		  expect(orders[0].id).toEqual(01);
		  expect(orders[0].totalTax).toEqual(3);
		  expect(orders[0].totalProduct).toEqual(123.96);
		  
		  $log.debug("orderService Test getOrders() Orders length: "+orders.length);

	  }));

	
	  it('orderService addPayment(orderId,paymentData) with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var orderId =  01;
		  var paymentData= '1';
		  
		  orderService.addPayment(orderId,paymentData).then(function(response){
			  order = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(order).not.toBeNull();
		  
		  expect(order.id).toEqual(1146);
		  expect(order.totalProduct).toEqual(0);
		  expect(order.totalShipping).toEqual(0);
		  
		  $log.debug("orderService Test addPayment(orderId,paymentData) Order id: "+order.id);//+JSON.stringify(order)

	  }));
	  
	  it('orderService placeOrder() with mocked getSessionId function', inject(function(orderService,$httpBackend) {
		  
		  var order =  {};
		  
		  orderService.placeOrder().then(function(response){
			  order = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(order).not.toBeNull();
		  expect(order.id).toEqual(1146);
		  expect(order.totalProduct).toEqual(0);
		  expect(order.totalShipping).toEqual(0);
		  

		  $log.debug("orderService Test placeOrder() Order id: "+order.id);

	  }));
	
});