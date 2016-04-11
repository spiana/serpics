describe("Testing login.controller module", function() {
	
	var loginController;
    var $log;
	var $controller;
    var $scope = {};
	var mockedLog = {debug:function(log){console.log(log)}}; 

	
	beforeEach(module('login.controller','customer.service','http.login.mocks','serpics.services','serpics.router', function($provide) {
    
		// Do some other stuff before each test run if you want...
		$provide.value('$log', mockedLog);
		$provide.value('$stateParams', stateParams = { login: "shop.home", register: "shop.login"});
  
	}));

    beforeEach(inject(function(_$controller_,$rootScope){
    	
    	$controller = _$controller_;
    	$scope = $rootScope.$new();
    	loginController = $controller('loginController', { $scope: $scope });
    	
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
	
	it('loginController is registered and runs $scope.currentUser', inject(function($httpBackend){
		
		
		var currentUser = {};
		expect(loginController).not.toBeNull();

		$httpBackend.flush();
		
		currentUser= $scope.currentUser;
		
		expect(currentUser).not.toBeNull();
		expect(currentUser.firstname).toEqual('Gabriele');
		expect(currentUser.lastname).toEqual('Gabrieles');
		  
		$log.debug("loginController Test currentUser.firstname: "+currentUser.firstname);//+JSON.stringify());
	}));
	
	
	it('loginController $scope.logout()', inject(function($httpBackend){
		
		var currentUser = {};
		var orders = [];
		
		expect(loginController).not.toBeNull();

	
		$httpBackend.flush();
		
		$scope.logout();
		
		currentUser= $scope.currentUser;
		
		expect(currentUser).not.toBeNull();
		expect(currentUser.firstname).toEqual('Gabriele');
		expect(currentUser.lastname).toEqual('Gabrieles');

		$log.debug("loginController Test currentUser.firstname: "+currentUser.firstname);//+JSON.stringify());
	}));
	
	it('loginController $scope.login(loginUser)', inject(function($httpBackend){
		
		var currentUser = {};
		var loginUser = {
				username:'gabri',
				password:'1234'
					};
		
		expect(loginController).not.toBeNull();

		$scope.login(loginUser);
	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("loginController Test $scope.login(userData): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));
	
	it('loginController $scope.register(registerUser)', inject(function($httpBackend){
		
		var currentUser = {};
		var contactAddress = {};
		
		var registerUser = {
				username:'gabri',
				password:'1234'
					};
		
		expect(loginController).not.toBeNull();
		
		$scope.register(registerUser);

	
		$httpBackend.flush();
		
		currentUser = $scope.currentUser;
		  
		expect(currentUser).not.toBeNull();
		  
		$log.debug("loginController Test $scope.register(registerUser): $scope.currentUser "+$scope.currentUser);//+JSON.stringify($scope.categoryData));
	}));	
	
});