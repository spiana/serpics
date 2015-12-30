describe("Testing customer.service module:", function() {
	
	var mockedLogOff = {debug:function(){}};
//	var mockedLog = {debug:console.log};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('customer.service','http.customer.mocks','serpics.services', function($provide) {
			
	
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
		
	  }))

	  xit('customerService login(username, password) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var login =  {};
		  var username = 'user';
		  var password = 'password'; 
		  
		  customerService.login(username, password).then(function(response){
			  login = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(login).not.toBeNull();
		  
		  expect(login.id).toEqual(1180);
		  
		  expect(login.orderItems[0].id).toEqual(252);
		  
	  
		  
		  $log.debug("customerService login(username, password) login: "+login);

	  }));

	
	  it('customerService logout() with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var logout = {};
		  
		  customerService.logout().then(function(response){
			  cart = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cart).not.toBeNull();
		  
	  
		  $log.debug("customerService deleteItem(itemId) Cart : "+JSON.stringify(cart));

	  }));
	  
	  xit('customerService cartAdd(sku ,quantity) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var sku =  'TASTIERA';
		  var quantity= 1;
		  var cartModification = {};
		  
		  customerService.cartAdd(sku ,quantity).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification).toEqual('PHILIPHS');
		  expect(cartModification).toEqual('Logos');
		  expect(cartModification).toEqual(1);
		  
		  $log.debug("customerService cartAdd(sku ,quantity) CartModification Status: "+cartModification.status);

	  }));

	  
	 xit('customerService cartUpdate(cartItem) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var cartModification =  {};
		  
		  var cartItem = {"updated":"2015-12-30T12:05:37 GMT","created":"2015-12-30T12:05:37 GMT","uuid":"dce20718-a56a-438d-9efc-1a8419662fc2","id":254,"discountAmount":0,"discountPerc":0,"quantity":2,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0,"shippingAddressId":0}

		  
		  customerService.cartUpdate(cartItem).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification).toEqual('PHILIPHS');
		  expect(cartModification).toEqual('Logo');
		  expect(cartModification).toEqual(1);
		  
		  $log.debug("customerService cartUpdate() CartModification: "+cartModification);

	  }));
	  
	  xit('customerService addBillingAddress(billingAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var billingAddress =  {};
		  
		  customerService.addBillingAddress(billingAddress).then(function(response){
			  billingAddress = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(billingAddress).not.toBeNull();
		  
		  expect(addBillingAddress.content.length).toEqual(3);
		  expect(addBillingAddress.content[0].name).toEqual('PHILIPHS');
		  expect(addBillingAddress.content[0].logo).toEqual('Logo');
		  expect(addBillingAddress.content[0].id).toEqual(1);
		  
		  $log.debug("customerService addBillingAddress(billingAddress) billingAddress: "+billingAddress.content[0].name);

	  }));
	  
	  xit('customerService addShippingAddress(shippingAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var shippingAddress =  {};

		  
		  customerService.addShippingAddress(shippingAddress).then(function(response){
			  shippingAddress = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(shippingAddress).not.toBeNull();
		  
		  expect(shippingAddress.content.length).toEqual(3);
		  expect(shippingAddress.content[0].name).toEqual('PHILIPHS');
		  expect(shippingAddress.content[0].logo).toEqual('Logo');
		  expect(shippingAddress.content[0].id).toEqual(1);
		  
		  $log.debug("customerService addShippingAddress(shippingAddress) shippingAddress: "+billingAddress.content[0].name);

	  }));

});