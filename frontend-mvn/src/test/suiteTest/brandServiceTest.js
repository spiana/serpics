describe("Testing brand.service module:", function() {
	
	var mockedLogOff = {debug:function(){}};
//	var mockedLog = {debug:console.log};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('brand.service','http.brand.mocks','serpics.services', function($provide) {
			
	
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

	  it('BrandService getBrandQ() with mocked getSessionId function', inject(function(brandService,$httpBackend) {
		  
		  var brands =  [];
		  brandService.getBrandQ().then(function(response){
				  brands = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brands).not.toBeNull();
		  
		  expect(brands.content[0].name).toEqual('PHILIPHS');
		  expect(brands.content[0].logo).toEqual('Logo');
		  
		  $log.debug("BrandService getBrandQ() Brand Name: "+brands.content[0].name);

	  }));

	
	  it('BrandService findBrandById() with mocked getSessionId function', inject(function(brandService,$httpBackend) {
		  
		  var brand =  {};
		  var brandId= '1';
		  
		  brandService.findBrandById(brandId).then(function(response){
				  brand = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brand).not.toBeNull();
		  
		  expect(brand.name).toEqual('PHILIPHS');
		  expect(brand.logo).toEqual('Logos');
		  
		  $log.debug("BrandService findBrandById() Brand Name: "+brand.name);

	  }));
	  
	  it('BrandService findBrandByName() with mocked getSessionId function', inject(function(brandService,$httpBackend) {
		  
		  var brand =  {};
		  var brandName= 'PHILIPHS';
		  
		  brandService.findBrandByName(brandName).then(function(response){
				  brand = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brand).not.toBeNull();
		  
		  expect(brand.name).toEqual('PHILIPHS');
		  expect(brand.logo).toEqual('Logos');
		  expect(brand.id).toEqual(1);
		  
		  $log.debug("BrandService findBrandByName() Brand Name: "+brand.name);

	  }));

	  
	  it('BrandService findAll() with mocked getSessionId function', inject(function(brandService,$httpBackend) {
		  
		  var brands =  [];

		  
		  brandService.findAll().then(function(response){
				  brands = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brands).not.toBeNull();
		  
		  expect(brands.content[0].name).toEqual('PHILIPHS');
		  expect(brands.content[0].logo).toEqual('Logo');
		  expect(brands.content[0].id).toEqual(1);
		  
		  $log.debug("BrandService findAll() Brand Name: "+brands.content[0].name);

	  }));
	  
	  it('BrandService findAll(page, size) with mocked getSessionId function', inject(function(brandService,$httpBackend) {
		  
		  var brands =  [];
		  var page=0;
		  var size=3;
		  
		  brandService.findAll(page,size).then(function(response){
				  brands = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(brands).not.toBeNull();
		  
		  expect(brands.content.length).toEqual(3);
		  expect(brands.content[0].name).toEqual('PHILIPHS');
		  expect(brands.content[0].logo).toEqual('Logo');
		  expect(brands.content[0].id).toEqual(1);
		  
		  $log.debug("BrandService findAll(page, size) Brand Name: "+brands.content[0].name);

	  }));
	
	  
	  
	
	
});