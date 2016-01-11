describe("Testing order.controller module", function() {
	
	var orderController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('order.controller','order.service','http.order.mocks','serpics.services', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
  
	}));

    beforeEach(inject(function(_$controller_){
    	
    	$controller = _$controller_;
    	orderController = $controller('orderController', { $scope: $scope });
    	
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
	
	it('orderController is registered and runs placeOrder()', inject(function($httpBackend){
		
		var order = {};
		expect(orderController).not.toBeNull();

		$httpBackend.flush();
		
		order = $scope.order;
		expect(order).not.toBeNull();
		expect(order.id).toEqual(1146);
		expect(order.totalProduct).toEqual(0);
		expect(order.totalShipping).toEqual(0);
		  
		$log.debug("orderController Test $scope.order Order id:"+$scope.order.id);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('orderController $scope.addPayment(orderId, paymentData)', inject(function($httpBackend){
		
		var orderId = 01;
		var paymentData= '1';
		
		expect(orderController).not.toBeNull();

		$scope.addPayment(orderId, paymentData);
		
		
		$httpBackend.flush();
		
		order = $scope.order;
		  
		expect(order).not.toBeNull();
		  
		expect(order.id).toEqual(1146);
		expect(order.totalProduct).toEqual(0);
		expect(order.totalShipping).toEqual(0);
		  
		$log.debug("orderController Test $scope.order Order id:"+$scope.order.id);//+JSON.stringify($scope.brandData));
	}));
	
});