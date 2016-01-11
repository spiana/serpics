describe("Testing customer.controller module", function() {
	
	var customerController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('customer.controller','customer.service','http.customer.mocks','order.service','serpics.services','http.order.mocks', function($provide) {
		
//		beforeEach(module('category.controller','category.service','http.customer.mocks','serpics.services', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
  
	}));

    beforeEach(inject(function(_$controller_){
    	
    	$controller = _$controller_;
    	customerController = $controller('customerController', { $scope: $scope });
    	
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
	
	it('customerController is registered and runs init()', inject(function($httpBackend){
		
		var orders = [];
		expect(customerController).not.toBeNull();

		$httpBackend.flush();
		
		//orders = $scope.orders;
		//expect(orders.length).toEqual(9);
		//(orders[0].id).toEqual(4);
		  
		$log.debug("customerController Test $scope.orders:"+$scope.orders);//+JSON.stringify($scope.categoryData));
	}));
	
	
	it('customerController init() customer test', inject(function($httpBackend){
		
		var currentUser = {};
		var orders = [];
		
		expect(customerController).not.toBeNull();

	
		$httpBackend.flush();
		
		orders = $scope.orders;
		  
		expect(orders).not.toBeNull();
		  

		  
		$log.debug("customerController Test $scope.orders:"+$scope.orders);//+JSON.stringify($scope.categoryData));
	}));
	
	it('customerController $scope.updateUserData(userData) customer test', inject(function($httpBackend){
		
		var currentUser = {};
		var userData = {};
		
		expect(customerController).not.toBeNull();

		$scope.updateUserData(userData);
	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("customerController Test $scope.updateUserData(userData): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	it('customerController $scope.updateContactAddress(contactAddress) customer test', inject(function($httpBackend){
		
		var currentUser = {};
		var contactAddress = {};
		
		expect(customerController).not.toBeNull();
		
		$scope.updateContactAddress(contactAddress);

	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("customerController Test $scope.updateContactAddress(contactAddress): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	it('customerController $scope.updateBillingAddress(billingAddress) customer test', inject(function($httpBackend){
		
		var currentUser = {};
		var billingAddress = {};
		
		expect(customerController).not.toBeNull();
		
		$scope.updateContactAddress(billingAddress);

	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("customerController Test $scope.updateBillingAddress(billingAddress): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	it('customerController $scope.updateDestinationAddress(destinationAddress) customer test', inject(function($httpBackend){
		
		var currentUser = {};
		var destinationAddress = {};
		
		expect(customerController).not.toBeNull();
		
		$scope.updateDestinationAddress(destinationAddress);

		//addDestinationAddress to Test
	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("customerController Test $scope.updateDestinationAddress(destinationAddress): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	it('customerController $scope.deleteDestinationAddress(addressId) customer test', inject(function($httpBackend){
		
		var addressId = '123456';
		
		expect(customerController).not.toBeNull();
		
		$scope.deleteDestinationAddress(addressId);
	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("customerController Test $scope.deleteDestinationAddress(destinationAddress): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	
});