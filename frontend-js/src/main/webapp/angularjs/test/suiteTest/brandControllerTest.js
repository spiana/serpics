describe("Testing brand.controller module", function() {
	
	var brandController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('brand.controller','brand.service','http.brand.mocks','serpics.services', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
  
	}));

    beforeEach(inject(function(_$controller_){
    	
    	$controller = _$controller_;
    	brandController = $controller('brandController', { $scope: $scope });
    	
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
	
	it('brandController is registered and runs getBrandQ()', inject(function($httpBackend){
		
		var brands = [];
		expect(brandController).not.toBeNull();

		$httpBackend.flush();
		
		brands = $scope.brandData;
		expect(brands.content.length).toEqual(10);
		expect(brands.content[0].name).toEqual('PHILIPHS');
		expect(brands.content[0].logo).toEqual('Logo');
		expect(brands.content[0].id).toEqual(1);
		  
		$log.debug("brandController Test $scope.brandData Brand Name:"+$scope.brandData.content[0].name);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('brandController $scope.findBrandById(brandId)', inject(function($httpBackend){
		
		var brand = {};
		var brandId= '1';
		
		expect(brandController).not.toBeNull();

		$scope.findBrandById(brandId);
		
		
		$httpBackend.flush();
		
		brand = $scope.brandData;
		  
		expect(brand).not.toBeNull();
		  
		expect(brand.name).toEqual('PHILIPHS');
		expect(brand.logo).toEqual('Logos');
		expect(brand.id).toEqual(1);
		  
		$log.debug("brandController Test $scope.brandData Brand Name:"+$scope.brandData.name);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('brandController $scope.$scope.findBrandByName(brandName)', inject(function($httpBackend){
		
		var brand = {};
		var brandName= 'PHILIPHS';
		
		expect(brandController).not.toBeNull();

		$scope.findBrandByName(brandName);
		
		
		$httpBackend.flush();
		
		brand = $scope.brandData;
		  
		expect(brand).not.toBeNull();
		  
		expect(brand.name).toEqual('PHILIPHS');
		expect(brand.logo).toEqual('Logos');
		expect(brand.id).toEqual(1);
		  
		$log.debug("brandController Test $scope.brandData Brand Name:"+$scope.brandData.name);//+JSON.stringify($scope.brandData));
	}));
	
	
	it('brandController $scope.findAll()', inject(function($httpBackend){
		
		var brands = {};
		
		expect(brandController).not.toBeNull();

		$scope.findAll();

		$httpBackend.flush();
		
		brands = $scope.brandData;
		  
		expect(brands).not.toBeNull();
		
		expect(brands.content.length).toEqual(10);
		expect(brands.content[0].name).toEqual('PHILIPHS');
		expect(brands.content[0].logo).toEqual('Logo');
		expect(brands.content[0].id).toEqual(1);
		  
		$log.debug("brandController Test $scope.brandData Brand Name:"+$scope.brandData.content[0].name);//+JSON.stringify($scope.brandData));
	}));
	
	it('brandController $scope.findAll(page,size)', inject(function($httpBackend){
		
		var page = 0;
		var size= 3;
		var brands = {};
		
		expect(brandController).not.toBeNull();

		$scope.findAll(page,size);

		$httpBackend.flush();
		
		brands = $scope.brandData;
		  
		expect(brands).not.toBeNull();
		
		expect(brands.content.length).toEqual(3);
		expect(brands.content[0].name).toEqual('PHILIPHS');
		expect(brands.content[0].logo).toEqual('Logo');
		expect(brands.content[0].id).toEqual(1);
		  
		$log.debug("brandController Test $scope.brandData Brand Name:"+$scope.brandData.content[0].name);//+JSON.stringify($scope.brandData));
	}));
	
	
	
});