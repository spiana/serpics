describe("Testing cart.service module:", function() {
	
	var mockedLogOff = {debug:function(){}};
//	var mockedLog = {debug:console.log};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('cart.service','http.cart.mocks','serpics.services', function($provide) {
			
	
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

	  it('cartService getCurrentCart() with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var cart =  {};
		  cartService.getCurrentCart().then(function(response){
			  cart = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cart).not.toBeNull();
		  
		  expect(cart.id).toEqual(1180);
		  
		  expect(cart.orderItems[0].id).toEqual(252);
		  
		  expect(cart.orderItems[0].product.code).toEqual('TASTIERA');
		  expect(cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		  expect(cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		  
		  expect(cart.orderItems[0].sku).toEqual('TASTIERA');
		  expect(cart.orderItems[0].skuCost).toEqual(0);
		  expect(cart.orderItems[0].skuNetPrice).toEqual(30.99);
		  expect(cart.orderItems[0].skuPrice).toEqual(30.99);
		  expect(cart.orderItems[0].shippingCost).toEqual(0);
		  expect(cart.orderItems[0].shippingAddressId).toEqual(0);
		  
		  
		  $log.debug("cartService Test getCurrentCart() Card id: "+cart.id);

	  }));

	
	  it('cartService deleteItem(itemId) with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var cartModification = {};
		  var itemId= '1';
		  
		  cartService.deleteItem(itemId).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  expect(cartModification.modificationStatus).toEqual('OK');
		  
	  
		  $log.debug("cartService Test deleteItem(itemId) cartModification : "+cartModification.modificationStatus);

	  }));
	  
	  it('cartService cartAdd(sku ,quantity) with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var sku =  'TASTIERA';
		  var quantity= 1;
		  var cartModification = {};
		  
		  cartService.cartAdd(sku ,quantity).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('TASTIERA');
		  expect(cartModification.cart.orderItems[0].quantity).toEqual(1);
		  
		  $log.debug("cartService Test cartAdd(sku ,quantity) CartModification cart id: "+cartModification.cart.id);

	  }));

	  
	 it('cartService cartUpdate(cartItem) with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var cartModification =  {};
		  
		  var cartItem = {"updated":"2015-12-30T12:05:37 GMT","created":"2015-12-30T12:05:37 GMT","uuid":"dce20718-a56a-438d-9efc-1a8419662fc2","id":254,"discountAmount":0,"discountPerc":0,"quantity":2,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0,"shippingAddressId":0}

		  
		  cartService.cartUpdate(cartItem).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].quantity).toEqual(2);
		  
		  $log.debug("cartService Test cartUpdate() CartModification cart id: "+cartModification.cart.id);//JSON.stringify()

	  }));
	  
	 it('cartService addBillingAddress(billingAddress) with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var billingAddress =  {"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","address2":"6","company":"stepfour","streetNumber":"8","city":"PANDINO","zipcode":"26025","email":"fpicinelli@stepfour.it","phone":"333333333","mobile":"333333333","fax":"333333333"};
		  var cartModification =  {};
		  
		  cartService.addBillingAddress(billingAddress).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].quantity).toEqual(2);
		  
		  $log.debug("cartService Test addBillingAddress(billingAddress) billingAddress: "+JSON.stringify(billingAddress));

	  }));
	 
	 it('cartService addShippingAddress(shippingAddress) with mocked getSessionId function', inject(function(cartService,$httpBackend) {
		  
		  var shippingAddress =  {"firstname":"Fede","lastname":"Pic","address1":"VIA LAGO GERUNDO","address2":"6","company":"stepfour","streetNumber":"13","city":"PANDINO","zipcode":"26025","email":"fpicinelli@stepfour.it","phone":"333333333","mobile":"333333333","fax":"333333333"};
		  var cartModification =  {};
		  
		  cartService.addShippingAddress(shippingAddress).then(function(response){
			  cartModification = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(cartModification).not.toBeNull();
		  
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].quantity).toEqual(2);
		  
		  $log.debug("cartService Test addShippingAddress(shippingAddress) shippingAddress: "+shippingAddress.name);

	  }));

});