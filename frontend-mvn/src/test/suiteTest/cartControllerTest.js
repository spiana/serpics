describe("Testing cart.controller module", function() {
	
	var cartController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('cart.controller','cart.service','http.cart.mocks','serpics.services', ['$provide',function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
		$provide.value('$stateParams', stateParams = { complete: "complete", shipping: "checkout.shipping"});
  
	}]));

    beforeEach(inject(function(_$controller_,_$rootScope_,_$stateParams_){
    	
    	$rootScope = _$rootScope_;
    	$scope = $rootScope.$new();
    	$controller = _$controller_;
    	cartController = $controller('cartController', { $scope: $scope, $stateParams: _$stateParams_ });
    	
    }));
    
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
	
	it('cartController is registered and runs init()', inject(function($httpBackend){
		
		var cart = {};
		var currentUser = {};
		
		expect(cartController).not.toBeNull();

		$httpBackend.flush();
		
		cart = $scope.cart;
		currentUser = $scope.currentUser;
		
		expect(cart).not.toBeNull();
		
		expect(currentUser).not.toBeNull();
		expect(currentUser.firstname).toEqual('Gabriele');
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
		  
		$log.debug("cartController Test $scope.cart id:"+$scope.cart.id);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('cartController $scope.getCurrentCart()', inject(function($httpBackend){
		
		var cart = {};
		
		expect(cartController).not.toBeNull();

		$scope.getCurrentCart();
		
		
		$httpBackend.flush();
		
		$scope.getCurrentCart();
		
		cart = $scope.cart;
		
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
		  
		$log.debug("cartController Test $scope.getCurrentCart() Cart Id:"+$scope.cart.id);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('cartController $scope.cartAdd(sku ,quantity) with mocked getSessionId function', inject(function($httpBackend){
		
		var sku =  'TASTIERA';
		var quantity= 1;
		
		$scope.cartAdd(sku ,quantity);
		
		$httpBackend.flush();
		
		cart = $scope.cart;	
		expect(cart).not.toBeNull();
		expect(cart.id).toEqual(1183);
		expect(cart.orderItems[0].id).toEqual(256);
		expect(cart.orderItems[0].product.code).toEqual('TASTIERA');
		expect(cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		expect(cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		expect(cart.orderItems[0].sku).toEqual('TASTIERA');
		expect(cart.orderItems[0].skuCost).toEqual(0);
		expect(cart.orderItems[0].skuNetPrice).toEqual(30.99);
		expect(cart.orderItems[0].skuPrice).toEqual(30.99);
		expect(cart.orderItems[0].shippingCost).toEqual(0);
		expect(cart.orderItems[0].shippingAddressId).toEqual(0);
				  
		$log.debug("cartController Test $scope.cartAdd(sku ,quantity) Cart id: "+$scope.cart.id);

	  }));	

	it('cartController $scope.deleteItem(itemId) with mocked getSessionId function', inject(function($httpBackend) {
		
		
		var itemId= '1';		  
		
		
		$scope.deleteItem(itemId);
		
		$httpBackend.flush();
		
		cart = $scope.cart;
		
		expect(cart).not.toBeNull();
		expect(cart.id).toEqual(1183);
		expect(cart.orderItems[0].id).toEqual(256);
		expect(cart.orderItems[0].product.code).toEqual('TASTIERA');
		expect(cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		expect(cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		expect(cart.orderItems[0].sku).toEqual('TASTIERA');
		expect(cart.orderItems[0].skuCost).toEqual(0);
		expect(cart.orderItems[0].skuNetPrice).toEqual(30.99);
		expect(cart.orderItems[0].skuPrice).toEqual(30.99);
		expect(cart.orderItems[0].shippingCost).toEqual(0);
		expect(cart.orderItems[0].shippingAddressId).toEqual(0);
		  
	  
		$log.debug("cartController Test deleteItem(itemId) Cart id: "+$scope.cart.id);

	  }));
	  
	it('cartController cartUpdate(cartItem) with mocked getSessionId function', inject(function($httpBackend) {
		 
		 
		 var cartItem = {"updated":"2015-12-30T12:05:37 GMT","created":"2015-12-30T12:05:37 GMT","uuid":"dce20718-a56a-438d-9efc-1a8419662fc2","id":254,"discountAmount":0,"discountPerc":0,"quantity":2,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0,"shippingAddressId":0}
		 		  
		 $scope.cartUpdate(cartItem);
			
		 $httpBackend.flush();
			
		 cart = $scope.cart;
			
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
		  
		 $log.debug("cartController Test cartUpdate() CartModification cart id: "+$scope.cart.id);//JSON.stringify()

	  }));
	  
	 it('cartController submitBillingForm(billingAddress,true) with mocked getSessionId function', inject(function($httpBackend) {
		  
		  var billingAddress =  {"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","address2":"6","company":"stepfour","streetNumber":"8","city":"PANDINO","zipcode":"26025","email":"fpicinelli@stepfour.it","phone":"333333333","mobile":"333333333","fax":"333333333"};
		  var shippingToBill =  true;	  	
		  var cartModification= {};
		  $scope.submitBillingForm(billingAddress,shippingToBill);
		  
		  $httpBackend.flush();
		  
		  $rootScope.$digest();
		  
		  cartModification = $scope.cart;
		  
		  
		  
		  expect(cartModification.cart).not.toBeNull();
		  
		  expect(cartModification.cart.id).toEqual(1181);
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		  expect(cartModification.cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		  expect(cartModification.cart.orderItems[0].sku).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].skuCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].skuNetPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].skuPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].shippingCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].shippingAddressId).toEqual(0);
		  
		  $log.debug("cartController Test submitBillingForm(billingAddress,shippingToBill) cart id: "+cartModification.cart.id);

	  }));
	 
	 it('cartController submitBillingForm(billingAddress,false) with mocked getSessionId function', inject(function($httpBackend) {
		  
		  var billingAddress =  {"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","address2":"6","company":"stepfour","streetNumber":"8","city":"PANDINO","zipcode":"26025","email":"fpicinelli@stepfour.it","phone":"333333333","mobile":"333333333","fax":"333333333"};
		  var shippingToBill =  false;	  	
		  var cartModification= {};
		  $scope.submitBillingForm(billingAddress,shippingToBill);
		  
		  $httpBackend.flush();
		  
		  cartModification = $scope.cart;
		  
		  expect(cartModification.cart).not.toBeNull();
		  
		  expect(cartModification.cart.id).toEqual(1181);
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		  expect(cartModification.cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		  expect(cartModification.cart.orderItems[0].sku).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].skuCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].skuNetPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].skuPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].shippingCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].shippingAddressId).toEqual(0);
		  
		  $log.debug("cartController Test submitBillingForm(billingAddress,shippingToBill) cart id: "+cartModification.cart.id);

	  }));
	 
	 it('cartController submitShippingForm(shippingAddress) with mocked getSessionId function', inject(function($httpBackend) {
		  
		  var shippingAddress =  {"firstname":"Fede","lastname":"Pic","address1":"VIA LAGO GERUNDO","address2":"6","company":"stepfour","streetNumber":"13","city":"PANDINO","zipcode":"26025","email":"fpicinelli@stepfour.it","phone":"333333333","mobile":"333333333","fax":"333333333"};
		  var cartModification =  {};
		  
		  
		  $scope.submitShippingForm(shippingAddress);
		  
		  $httpBackend.flush();
		  
		  cartModification = $scope.cart;
		  
		  expect(cartModification.cart).not.toBeNull();
		  
		  expect(cartModification.cart.id).toEqual(1181);
		  expect(cartModification.cart.orderItems[0].product.code).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].product.brand.name).toEqual('LOGITECH');
		  expect(cartModification.cart.orderItems[0].product.categories[0].code).toEqual('COMPUTER');
		  expect(cartModification.cart.orderItems[0].sku).toEqual('MOUSE');
		  expect(cartModification.cart.orderItems[0].skuCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].skuNetPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].skuPrice).toEqual(22.5);
		  expect(cartModification.cart.orderItems[0].shippingCost).toEqual(0);
		  expect(cartModification.cart.orderItems[0].shippingAddressId).toEqual(0);
		  
		  $log.debug("cartController Test submitShippingForm(shippingAddress) cart id: "+cartModification.cart.id);

	  }));
	
});