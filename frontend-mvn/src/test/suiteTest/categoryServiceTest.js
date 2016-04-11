describe("Testing category.service module:", function() {
	
	var mockedLogOff = {debug:function(){}}
	var mockedLog = {debug:function(log){console.log(log)}}; 
	
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('category.service','http.category.mocks','serpics.services', function($provide) {
			
	
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
		
	  }));

	  it('categoryService getTopQ() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categories =  [];
		  categoryService.getTopQ().then(function(response){
				  categories = response;
		  });		
		  
		  $httpBackend.flush();

		  //$log.debug('categoryService getTopQ() Test category code:'+JSON.stringify(categories));
		  
		  expect(categories).not.toBeNull();
		  
		  expect(categories[0].code).toEqual('GUESS');
		  expect(categories[0].id).toEqual(4);

		  
		  $log.debug("categoryService getTopQ() Test category Code: "+categories[0].code);

	  }));

	
	  it('categoryService getCategoryById with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var category =  {};
		  var categoryId= '4';
		  
		  categoryService.getCategoryById(categoryId).then(function(response){
				  category = response;
				  
		  });		
		  
		  $httpBackend.flush();
		  
	  
		  expect(category).not.toBeNull();
		  
		  expect(category.code).toEqual('GUESS');

		  
		  $log.debug("categoryService getCategoryById() Test category code: "+category.code);

	  }));
	  
	  it('categoryService getCategoryByCode() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var category =  [];
		  var categoryName= 'GUESS';
		  
		  categoryService.getCategoryByCode(categoryName).then(function(response){
				  category = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(category).not.toBeNull();
		  
		  expect(category.code).toEqual('GUESS');
		  expect(category.id).toEqual(4);
		  
		  $log.debug("categoryService getCategoryByCode() Test category code: "+category.code);

	  }));

	  
	  it('categoryService findAll() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categories =  [];

		  
		  categoryService.findAll().then(function(response){
				  categories = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categories).not.toBeNull();
		  
		  expect(categories.content[0].code).toEqual('GUESS');
		  expect(categories.content[0].id).toEqual(4);
		  
		  $log.debug("categoryService findAll() Test category code: "+categories.content[0].code);

	  }));
	  
	  it('categoryService findAll(page, size) with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categories =  [];
		  var page=0;
		  var size=3;
		  
		  categoryService.findAll(page,size).then(function(response){
				  categories = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categories).not.toBeNull();
		  
		  expect(categories.content.length).toEqual(3);
		  expect(categories.content[0].code).toEqual('GUESS');
		  expect(categories.content[0].id).toEqual(4);
		  
		  $log.debug("categorieservice findAll(page, size) category code: "+categories.content[0].code);

	  }));
	  
	  it('categoryService getChild(parent) with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categories =  [];
		  var parent=0;

		  
		  categoryService.getChild(parent).then(function(response){
				  categories = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categories).not.toBeNull();
		  
		  expect(categories.length).toEqual(3);
		  expect(categories[0].code).toEqual('GUESS');
		  expect(categories[0].id).toEqual(4);
		  
		  $log.debug("categorieservice getChild(parent) category code: "+categories[0].code);

	  }));
	  
	  it('categoryService getTop() with mocked getSessionId function', inject(function(categoryService,$httpBackend) {
		  
		  var categories =  [];
		  var parent=0;

		  
		  categoryService.getTop().then(function(response){
				  categories = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(categories).not.toBeNull();
		  
		  expect(categories.length).toEqual(9);
		  expect(categories[0].code).toEqual('GUESS');
		  expect(categories[0].id).toEqual(4);
		  
		  $log.debug("categorieservice getTop() category code: "+categories[0].code);

	  }));

});