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

	  it('customerService login(username, password) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var currentUser =  {};
		  var username = 'user';
		  var password = 'password'; 
		  
		  customerService.login(username, password).then(function(response){
			  currentUser = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(currentUser).not.toBeNull();
		  
		  expect(currentUser.id).toEqual(16);
		  
		  expect(currentUser.firstname).toEqual('Gabri');
		  expect(currentUser.lastname).toEqual('Gabriele');
		  
		  
		  $log.debug("customerService Test login(username, password) currentUser id: "+currentUser.id);

	  }));

	
	  it('customerService logout() with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var logoutResponse = {};
		  
		  customerService.logout().then(function(response){
			  logoutResponse = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(logoutResponse).not.toBeNull();
	  
		  $log.debug("customerService Test logout() logoutResponse : "+JSON.stringify(logoutResponse));

	  }));
	  

	  it('customerService register(userData) with mocked getSessionId function', inject(function(customerService,$httpBackend) {

		  
		  var userData =  {"firstname":"Gabriele","lastname":"Gabriele","email":"gabriele@gabriele.it","logonid":"gabriele","password"
				  :"gabriele"};
		  var customer = {"status":"OK"};
		  
		  customerService.register(userData).then(function(response){
			  customer = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(customer).not.toBeNull();
		  
		  expect(customer.id).toEqual(17);
		  
		  expect(customer.firstname).toEqual('Gabriele');
		  expect(customer.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test register(userData) Customer id: "+customer.id);

	  }));

	  
	 it('customerService getCurrentUser() with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var customer =  {};
		  
		  customerService.getCurrentUser().then(function(response){
			  customer = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(customer).not.toBeNull();
		  
		  expect(customer.id).toEqual(17);
		  
		  expect(customer.firstname).toEqual('Gabriele');
		  expect(customer.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test getCurrentUser() Customer id: "+customer.id);

	  }));
	  
	  it('customerService updateUserData(userData) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var customer =  {};
		  var userData =  {};
		  
		  customerService.updateUserData(userData).then(function(response){
			  customer = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(customer).not.toBeNull();
		  
		  expect(customer.firstname).toEqual('Gabriele');
		  expect(customer.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test updateUserData(userData) Customer id: "+customer.id);

	  }));
	  
	  it('customerService  updateContactAddress(contactAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var contactAddress =  {};
		  var customer =  {};

		  
		  customerService.updateContactAddress(contactAddress).then(function(response){
			  customer = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(customer).not.toBeNull();
		  
		  expect(customer.firstname).toEqual('Gabriele');
		  expect(customer.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test updateContactAddress(contactAddress) Customer id: "+customer.id);

	  }));

	  
	  it('customerService  updateBillingAddress(billingAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var billingAddress =  {};
		  var customer =  {};

		  
		  customerService.updateBillingAddress(billingAddress).then(function(response){
			  billingAddress = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(billingAddress).not.toBeNull();
		  
		  expect(billingAddress.firstname).toEqual('Gabriele');
		  expect(billingAddress.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test updateBillingAddress(billingAddress) Customer id: "+billingAddress.id);

	  }));
	  
	  it('customerService  updateDestinationAddress(destinationAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var destinationAddress =  {};
		  var customer =  {};

		  
		  customerService.updateDestinationAddress(destinationAddress).then(function(response){
			  destinationAddress = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(destinationAddress).not.toBeNull();
		  
		  expect(destinationAddress.firstname).toEqual('Gabriele');
		  expect(destinationAddress.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test updateDestinationAddress(destinationAddress) Customer id: "+destinationAddress.id);

	  }));
	  
	  it('customerService  addDestinationAddress(destinationAddress) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  
		  var destinationAddress =  {};
		  var customer =  {};

		  
		  customerService.addDestinationAddress(destinationAddress).then(function(response){
			  destinationAddress = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(destinationAddress).not.toBeNull();
		  
		  expect(destinationAddress.firstname).toEqual('Gabriele');
		  expect(destinationAddress.lastname).toEqual('Gabrieles');
		  
		  $log.debug("customerService Test addDestinationAddress(destinationAddress) Customer id: "+destinationAddress.id);

	  }));
	  
	  it('customerService  deleteDestinationAddress(addressId) with mocked getSessionId function', inject(function(customerService,$httpBackend) {
		  var deleteResponse =  {};
		  var addressId =  '123456';

		  
		  customerService.deleteDestinationAddress(addressId).then(function(response){
			  deleteResponse = response;
		  })
		  
		  $httpBackend.flush();
		  
		  expect(deleteResponse).not.toBeNull();
		  
		  $log.debug("customerService Test deleteDestinationAddress(addressId) status: "+JSON.stringify(deleteResponse));
	  }));
		  
	  
	  
});