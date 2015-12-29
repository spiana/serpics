describe("Testing category.service module:", function() {
	
	var mockedLogOff = {debug:function(){}}
	var mockedLog = {debug:function(log){console.log(log)}}; 
	
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('category.service','serpics.mocks','serpics.services', function($provide) {
			
	
	    	// Do some provider configuration here
//			$provide.value('$log', mockedLogOff);
	  
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
		
	  }));

	  it('categoryService getTopQ() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categorys =  [];
		  categoryService.getTopQ().then(function(response){
				  categorys = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categorys).not.toBeNull();
		  
		  expect(categorys.content[0].code).toEqual('PHILIPHS');

		  
		  $log.debug("categoryService getTopQ() Test category Name: "+categorys.content[0].name);

	  }));

	
	  it('categoryService getCategoryById with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var category =  {};
		  var categoryId= '1';
		  
		  categoryService.getCategoryById(categoryId).then(function(response){
				  category = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(category).not.toBeNull();
		  
		  expect(category.content[0].code).toEqual('PHILIPHS');

		  
		  $log.debug("categoryService getCategoryById() Test category Name: "+category.content[0].name);

	  }));
	  
	  it('categoryService getCategoryByCode() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var category =  [];
		  var categoryName= 'PHILIPHS';
		  
		  categoryService.getCategoryByCode(categoryName).then(function(response){
				  category = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(category).not.toBeNull();
		  
		  expect(category.content[0].code).toEqual('PHILIPHS');
		  expect(category.content[0].id).toEqual(1);
		  
		  $log.debug("categoryService getCategoryByCode() Test category Name: "+category.content[0].name);

	  }));

	  
	  it('categoryService findAll() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categorys =  [];

		  
		  categoryService.findAll().then(function(response){
				  categorys = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categorys).not.toBeNull();
		  
		  expect(categorys.content[0].name).toEqual('PHILIPHS');
		  expect(categorys.content[0].logo).toEqual('Logo');
		  expect(categorys.content[0].id).toEqual(1);
		  
		  $log.debug("categoryService findAll() Test category Name: "+categorys.content[0].name);

	  }));
	  
	  it('categoryService findAll(page, size) with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categorys =  [];
		  var page=0;
		  var size=3;
		  
		  categoryService.findAll(page,size).then(function(response){
				  categorys = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categorys).not.toBeNull();
		  
		  expect(categorys.content.length).toEqual(3);
		  expect(categorys.content[0].name).toEqual('PHILIPHS');
		  expect(categorys.content[0].logo).toEqual('Logo');
		  expect(categorys.content[0].id).toEqual(1);
		  
		  $log.debug("categoryService findAll(page, size) category Name: "+categorys.content[0].name);

	  }));
	
	  
	  
	
	
});