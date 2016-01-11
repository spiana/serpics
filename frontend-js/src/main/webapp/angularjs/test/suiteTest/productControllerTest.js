describe("Testing product.controller module", function() {
	
	var productController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('product.controller','product.service','http.product.mocks','serpics.services', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
  
	}));

    beforeEach(inject(function(_$controller_,$rootScope){
    	
    	$controller = _$controller_;
    	$scope = $rootScope.$new();
    	productController = $controller('productController', { $scope: $scope });
    	
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
	
	it('productController is registered and runs findAllQ(page, size)', inject(function($httpBackend){
		
		var products = [];
		expect(productController).not.toBeNull();

		$httpBackend.flush();
		
		products = $scope.product;
		expect(products.content.length).toEqual(3);
		expect(products.content[0].code).toEqual('USB HARD DISK');
		expect(products.content[0].id).toEqual(776);
		expect(products.content[0].price.currentPrice).toEqual(89.99);
		  
		  
		$log.debug("productController Test findAllQ(page, size) Product id:"+$scope.product.content[0].id);
	}));
	
	
	it('productController $scope.range(totalPages)', inject(function($httpBackend,$rootScope){
		
		var totalPages= 4;
		var range = [];
		var products = [];
		
		expect(productController).not.toBeNull();

		range = $scope.range(totalPages);
		
		
		$httpBackend.flush();
		
	  
		expect(range).not.toBeNull();

		expect(range.length).toEqual(totalPages);

		  
		$log.debug("productController Test $scope.range(totalPages):"+range);//+JSON.stringify($scope.product));
	}));
	
	
	it('productController $scope.addToCart(sku,quantity)', inject(function($httpBackend){
		
		var sku =  'TASTIERA';
		var quantity= 1;

		$scope.addToCart(sku,quantity);
		
		$httpBackend.flush();
		
		products = $scope.product;
		expect(products.content.length).toEqual(3);
		expect(products.content[0].code).toEqual('USB HARD DISK');
		expect(products.content[0].id).toEqual(776);
		expect(products.content[0].price.currentPrice).toEqual(89.99);
				  
		  
		$log.debug("productController Test $scope.addToCart(sku,quantity):");//+JSON.stringify($scope.brandData));
	}));	
	
});