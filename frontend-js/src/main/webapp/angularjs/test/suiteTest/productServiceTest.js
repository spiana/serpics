describe("Testing product.service module:", function() {
	
	var mockedLogOff = {debug:function(){}};
//	var mockedLog = {debug:console.log};
	var mockedLog = {debug:function(log){console.log(log)}};
	
	//1 Dichiaro i moduli che mi servono per i test
		beforeEach(module('product.service','http.product.mocks','serpics.services', function($provide) {
			
	
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
	
	  it('productService getProduct(productId) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var product =  {};
		  var productId = 1;
		  
		  productService.getProduct(productId).then(function(response){
				  product = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(product).not.toBeNull();
		  
		  expect(product.code).toEqual('TASTIERA');
		  expect(product.dowloadable).toEqual(0);
		  expect(product.price.currentPrice).toEqual(30.99);
		  
		  $log.debug("productService Test getProduct(productId) Product Code: "+product.code);

	  }));

	
	  it('productService getCategoryProduct(productId) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var category =  {};
		  var productId = 1;
		  
		  productService.getCategoryProduct(productId).then(function(response){
				  category = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(category).not.toBeNull();
		  
		  expect(category.code).toEqual('GUESS');
		  expect(category.id).toEqual(4);
		  expect(category.childProductNumber).toEqual(0);
		  
		  $log.debug("productService Test getCategoryProduct(productId) Category Code: "+category.code);

	  }));
	  
	  it('productService Test getProductByName(productName) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var product =  {};
		  var productName = 'MONITOR';
		  
		  productService.getProductByName(productName).then(function(response){
				  product = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(product).not.toBeNull();
		  
		  expect(product.code).toEqual('TASTIERA');
		  expect(product.dowloadable).toEqual(0);
		  expect(product.price.currentPrice).toEqual(30.99);
		  
		  $log.debug("productService Test getProductByName(productName) Product Code: "+product.code);

	  }));

	  
	  it('productService findByCategory(categoryId, page, size) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  var categoryId = '1';
		  var page = 0;
		  var size = 10;
		  
		  productService.findByCategory(categoryId, page, size).then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(1);
		  expect(products.content[0].code).toEqual('MONITOR');
		  expect(products.content[0].id).toEqual(8);
		  expect(products.content[0].price.currentPrice).toEqual(1500);
		  
		  $log.debug("productService Test findByCategory(categoryId, page, size) Product code: "+products.content[0].code);

	  }));
	  
	  it('productService findByCategory(categoryId) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  var categoryId = '1';
		  
		  productService.findByCategory(categoryId).then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(2);
		  expect(products.content[0].code).toEqual('MONITOR');
		  expect(products.content[0].id).toEqual(8);
		  expect(products.content[0].price.currentPrice).toEqual(1500);
		  
		  $log.debug("productService Test findByCategory(categoryId, page, size) Product code: "+products.content[0].code);

	  }));
	  
	  it('productService findByBrand(brandId, page, size) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  var brandId = '1';
		  var page = 0;
		  var size = 10;
		  
		  productService.findByBrand(brandId, page, size).then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(3);
		  expect(products.content[0].code).toEqual('USB HARD DISK');
		  expect(products.content[0].id).toEqual(776);
		  expect(products.content[0].price.currentPrice).toEqual(89.99);
		  
		  $log.debug("productService Test findByBrand(brandId, page, size) Product code: "+products.content[0].code);

	  }));
	  
	  it('productService findByBrand(brandId) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  var brandId = '1';

		  
		  productService.findByBrand(brandId).then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(4);
		  expect(products.content[0].code).toEqual('USB HARD DISK');
		  expect(products.content[0].id).toEqual(776);
		  expect(products.content[0].price.currentPrice).toEqual(89.99);
		  
		  $log.debug("productService Test findByBrand(brandId) Product code: "+products.content[0].code);

	  }));
	  
	  it('productService findAll(page, size) with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  var page = 0;
		  var size = 10;
		  
		  productService.findAll(page, size).then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(3);
		  expect(products.content[0].code).toEqual('USB HARD DISK');
		  expect(products.content[0].id).toEqual(776);
		  expect(products.content[0].price.currentPrice).toEqual(89.99);
		  
		  $log.debug("productService Test findAll(page, size) Product code: "+products.content[0].code);

	  }));
	  
	  it('productService findAll() with mocked getSessionId function', inject(function(productService,$httpBackend) {
		  
		  var products =  [];
		  
		  productService.findAll().then(function(response){
			  products = response;
		  });		
		  
		  $httpBackend.flush();
		  
		  expect(products).not.toBeNull();
		  
		  expect(products.content.length).toEqual(4);
		  expect(products.content[0].code).toEqual('USB HARD DISK');
		  expect(products.content[0].id).toEqual(776);
		  expect(products.content[0].price.currentPrice).toEqual(89.99);
		  
		  $log.debug("productService Test findAll() Product code: "+products.content[0].code);

	  }));	
	
});