describe("Testing category.controller module", function() {
	
	var categoryController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('category.controller','category.service','http.category.mocks','serpics.services', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
  
	}));

    beforeEach(inject(function(_$controller_){
    	
    	$controller = _$controller_;
    	categoryController = $controller('categoryController', { $scope: $scope });
    	
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
	
	it('categoryController is registered and runs getTopQ()', inject(function($httpBackend){
		
		var categories = [];
		expect(categoryController).not.toBeNull();

		$httpBackend.flush();
		
		categories = $scope.categoryData;
		expect(categories.length).toEqual(9);
		expect(categories[0].id).toEqual(4);
		  
		$log.debug("categoryController Test $scope.categoryData Category Code:"+$scope.categoryData[0].code);//+JSON.stringify($scope.categoryData));
	}));
	
	
	it('categoryController $scope.getCategoryById(categoryId)', inject(function($httpBackend){
		
		var category = {};
		var categoryId= '4';
		
		expect(categoryController).not.toBeNull();

		$scope.getCategoryById(categoryId);
		
		
		$httpBackend.flush();
		
		category = $scope.categoryData;
		  
		expect(category).not.toBeNull();
		  
		expect(category.code).toEqual('GUESS');
		expect(category.id).toEqual(4);
		  
		$log.debug("categoryController Test $scope.categoryData.code Category Name:"+$scope.categoryData.code);//+JSON.stringify($scope.categoryData));
	}));
	
	
	it('categoryController $scope.$scope.getCategoryByCode(code)', inject(function($httpBackend){
		
		var category = {};
		var code= 'GUESS';
		
		expect(categoryController).not.toBeNull();

		$scope.getCategoryByCode(code);
		
		
		$httpBackend.flush();
		
		category = $scope.categoryData;
		  
		expect(category).not.toBeNull();
		  
		expect(category.code).toEqual('GUESS');
		expect(category.id).toEqual(4);
		  
		$log.debug("categoryController Test $scope.categoryData Category Code:"+$scope.categoryData.code);//+JSON.stringify($scope.categoryData));
	}));
	
	
	it('categoryController $scope.findAll()', inject(function($httpBackend){
		
		var categories = {};
		
		expect(categoryController).not.toBeNull();

		$scope.findAll();

		$httpBackend.flush();
		
		categories = $scope.categoryData;
		  
		expect(categories).not.toBeNull();
		
		expect(categories.content.length).toEqual(10);
		expect(categories.content[0].code).toEqual('GUESS');
		expect(categories.content[0].id).toEqual(4);
		  
		$log.debug("categoryController Test $scope.categoryData Category Code:"+$scope.categoryData.content[0].code);//+JSON.stringify($scope.categoryData));
	}));
	
	it('categoryController $scope.findAll(page,size)', inject(function($httpBackend){
		
		var page = 0;
		var size= 3;
		var categories = {};
		
		expect(categoryController).not.toBeNull();

		$scope.findAll(page,size);

		$httpBackend.flush();
		
		categories = $scope.categoryData;
		  
		expect(categories).not.toBeNull();
		
		expect(categories.content.length).toEqual(3);
		expect(categories.content[0].code).toEqual('GUESS');
		expect(categories.content[0].id).toEqual(4);
		  
		$log.debug("categoryController Test $scope.categoryData Category Code:"+$scope.categoryData.content[0].code);//+JSON.stringify($scope.categoryData));
	}));
	
	
	
});