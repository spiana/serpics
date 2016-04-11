var httpBrandMocks = angular.module('http.brand.mocks', ['ngMock']);

httpBrandMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
//	Paginato content
	var brandList ={ "responseObject":{"content":[{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logo","name":"PHILIPHS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:02 GMT","created":"2015-11-16T16:14:02 GMT","uuid":"bde83c7b-5503-4f4c-8c63-8b56540db517"
		,"id":2,"logo":"Logos","name":"MICROMICRO","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:12 GMT","created":"2015-11-16T16:14:12 GMT","uuid":"bf05b880-5645-4821-9877-5148b6d808cc","id":3
		,"logo":"Logos","name":"MULTITOUCH","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":4,"logo"
		:"Logo","name":"SAMSUNG","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":5,"logo":"Logo","name"
		:"APPLE","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":6,"logo":"Logo","name":"NOKIA","brandProductNumber"
		:3,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":7,"logo":"Logo","name":"TOSHIBA","brandProductNumber":6,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":8,"logo":"Logo","name":"LG","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":9,"logo":"Logo"
		,"name":"ASUS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber"
		:3,"published":1}]}};
	
	//Paginato content
	var brandPageSize ={ "responseObject":{"content":[{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logo","name":"PHILIPHS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:02 GMT","created":"2015-11-16T16:14:02 GMT","uuid":"bde83c7b-5503-4f4c-8c63-8b56540db517"
		,"id":2,"logo":"Logos","name":"MICROMICRO","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:12 GMT","created":"2015-11-16T16:14:12 GMT","uuid":"bf05b880-5645-4821-9877-5148b6d808cc","id":3
		,"logo":"Logos","name":"MULTITOUCH","brandProductNumber":0,"published":1}]}};
	
	
	
	var brand={ "responseObject":{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logos","name":"PHILIPHS","brandProductNumber":0,"published":1}};
	

	var category={"responseObject":{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0}};
	
	var categoryTop={"responseObject":[{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:25 GMT","created":"2015-11-16T17:01:25 GMT","uuid":"96ea6e5a-8c10-4e29-9f02-e7e74c887d15","id":17,"code":"MENS","url":"/default-catalog/MENS","published":1,"catalogId":"default-catalog","childCategoryNumber":4,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:36 GMT","created":"2015-11-16T17:01:36 GMT","uuid":"12358ab9-0549-4d66-9f9f-fe5ba309aef3","id":18,"code":"WOMENS","url":"/default-catalog/WOMENS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11},
		{"updated":"2015-11-16T17:01:58 GMT","created":"2015-11-16T17:01:58 GMT","uuid":"86d6f1a7-e22c-4994-9916-124639bb31e1","id":20,"code":"PARFUM","url":"/default-catalog/PARFUM","published":1,"catalogId":"default-catalog","childCategoryNumber":1,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:19 GMT","created":"2015-11-16T17:02:19 GMT","uuid":"9fefe1cf-3cbb-4565-a294-02d8ad25779c","id":22,"code":"LEGEA","url":"/default-catalog/LEGEA","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:30 GMT","created":"2015-11-16T17:02:30 GMT","uuid":"5451fd28-4787-4d81-815e-972bbc76ab60","id":23,"code":"KIDS","url":"/default-catalog/KIDS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:47 GMT","created":"2015-11-16T17:02:47 GMT","uuid":"d15d64ea-e0dd-412c-9e36-17359886ce47","id":24,"code":"SURF","url":"/default-catalog/SURF","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-20T10:19:32 GMT","created":"2015-11-20T10:19:32 GMT","uuid":"554b81a5-7f05-484d-b245-d9e536861c98","id":780,"code":"MOBILE","url":"/default-catalog/MOBILE","published":0,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":3}]};
	
	var categoryList={"responseObject":[{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                            		{"updated":"2015-11-16T17:01:25 GMT","created":"2015-11-16T17:01:25 GMT","uuid":"96ea6e5a-8c10-4e29-9f02-e7e74c887d15","id":17,"code":"MENS","url":"/default-catalog/MENS","published":1,"catalogId":"default-catalog","childCategoryNumber":4,"childProductNumber":0},
	                            		{"updated":"2015-11-16T17:01:36 GMT","created":"2015-11-16T17:01:36 GMT","uuid":"12358ab9-0549-4d66-9f9f-fe5ba309aef3","id":18,"code":"WOMENS","url":"/default-catalog/WOMENS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0}]};
	
	//Paginato content
	var categoryPageSize={"responseObject":{"content":[{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                                           		{"updated":"2015-11-16T17:01:25 GMT","created":"2015-11-16T17:01:25 GMT","uuid":"96ea6e5a-8c10-4e29-9f02-e7e74c887d15","id":17,"code":"MENS","url":"/default-catalog/MENS","published":1,"catalogId":"default-catalog","childCategoryNumber":4,"childProductNumber":0},
	                                        		{"updated":"2015-11-16T17:01:36 GMT","created":"2015-11-16T17:01:36 GMT","uuid":"12358ab9-0549-4d66-9f9f-fe5ba309aef3","id":18,"code":"WOMENS","url":"/default-catalog/WOMENS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                                        		{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11},
	                                        		{"updated":"2015-11-16T17:01:58 GMT","created":"2015-11-16T17:01:58 GMT","uuid":"86d6f1a7-e22c-4994-9916-124639bb31e1","id":20,"code":"PARFUM","url":"/default-catalog/PARFUM","published":1,"catalogId":"default-catalog","childCategoryNumber":1,"childProductNumber":0},
	                                        		{"updated":"2015-11-16T17:02:19 GMT","created":"2015-11-16T17:02:19 GMT","uuid":"9fefe1cf-3cbb-4565-a294-02d8ad25779c","id":22,"code":"LEGEA","url":"/default-catalog/LEGEA","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                                        		{"updated":"2015-11-16T17:02:30 GMT","created":"2015-11-16T17:02:30 GMT","uuid":"5451fd28-4787-4d81-815e-972bbc76ab60","id":23,"code":"KIDS","url":"/default-catalog/KIDS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                                        		{"updated":"2015-11-16T17:02:47 GMT","created":"2015-11-16T17:02:47 GMT","uuid":"d15d64ea-e0dd-412c-9e36-17359886ce47","id":24,"code":"SURF","url":"/default-catalog/SURF","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
	                                        		{"updated":"2015-11-20T10:19:32 GMT","created":"2015-11-20T10:19:32 GMT","uuid":"554b81a5-7f05-484d-b245-d9e536861c98","id":780,"code":"MOBILE","url":"/default-catalog/MOBILE","published":0,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":3},
	                                        		{"updated":"2015-11-20T10:19:32 GMT","created":"2015-11-20T10:19:32 GMT","uuid":"554b81a5-7f05-484d-b245-d9e536861c98","id":780,"code":"MOBILE","url":"/default-catalog/MOBILE","published":0,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":3}]}};
	//Paginato content
	var categoryPage0Size3={"responseObject":{"content":[{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		                                           		{"updated":"2015-11-16T17:01:25 GMT","created":"2015-11-16T17:01:25 GMT","uuid":"96ea6e5a-8c10-4e29-9f02-e7e74c887d15","id":17,"code":"MENS","url":"/default-catalog/MENS","published":1,"catalogId":"default-catalog","childCategoryNumber":4,"childProductNumber":0},
		                                        		{"updated":"2015-11-16T17:01:36 GMT","created":"2015-11-16T17:01:36 GMT","uuid":"12358ab9-0549-4d66-9f9f-fe5ba309aef3","id":18,"code":"WOMENS","url":"/default-catalog/WOMENS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0}]}};
			
	
	
	var currentCart={"responseObject":{"updated":"2015-12-30T11:22:21 GMT","created":"2015-12-30T11:21:39 GMT","uuid":"436d39ce-a827-47dd-b84f-85b09538cf7a","id":1180,"orderItems":[{"updated":"2015-12-30T11:22:20 GMT","created":"2015-12-30T11:22:20 GMT","uuid":"b8290c20-821c-4c01-90bf-8057da6624ed","id":252,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":30.99,"totalShipping":0.0,"totalTax":0.0}}
	
	var cartModification= {};
	
	var cartModificationUpdate ={"responseObject":{"modificationStatus":"OK","cart":{"updated":"2015-12-30T12:05:43 GMT","created":"2015-12-30T11:52:20 GMT","uuid":"b9dd3fa1-f6a9-409b-bc10-741c0dacf6d2","id":1181,"orderItems":[{"updated":"2015-12-30T12:05:43 GMT","created":"2015-12-30T12:05:37 GMT","uuid":"dce20718-a56a-438d-9efc-1a8419662fc2","id":254,"discountAmount":0.0,"discountPerc":0.0,"quantity":2.0,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0.0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0.0,"shippingAddressId":0},{"updated":"2015-12-30T12:05:30 GMT","created":"2015-12-30T12:05:30 GMT","uuid":"437a8169-50b9-4502-8ab4-1e1ecfca0481","id":253,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":75.99,"totalShipping":0.0,"totalTax":0.0}}};

	var customer = {};
	
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });
	
	
	//***Brand rest-api endpoint***
	$httpBackend.whenGET('http://localhost:8080/api/v1/brandService/?page=0&size=10').respond(function(method, url, data) {
	    return [status,brandList];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/brandService/code/1').respond(function(method, url, data) {
	    return [status,brand];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/brandService/PHILIPHS').respond(function(method, url, data) {
	    return [status,brand];
	  });
	
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/brandService/').respond(function(method, url, data) {
	    return [status,brandList];
	  });
	
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/brandService/?page=0&size=3').respond(function(method, url, data) {
	    return [status,brandPageSize];
	  });
	
	
	//***Category rest-api endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/top').respond(function(method, url, data) {
	    return [status,categoryTop];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/4').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/code/GUESS').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/').respond(function(method, url, data) {
	    return [status,categoryPageSize];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/getChild/0').respond(function(method, url, data) {
	    return [status,categoryList];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/?page=0&size=3').respond(function(method, url, data) {
	    return [status,categoryPage0Size3];
	  });
	
	
	//Cart endpoint getCurrentCart
	$httpBackend.whenGET('http://localhost:8080/api/v1/cartService/').respond(function(method, url, data) {
	    return [status,currentCart];
	  });
	//cartDelete
	$httpBackend.whenDELETE('http://localhost:8080/api/v1/cartService/?itemId=1').respond(function(method, url, data) {
	    return [status,currentCart];
	  });
	
	//cartAdd
	$httpBackend.whenPOST('http://localhost:8080/api/v1/cartService/?sku=TASTIERA&qty=1').respond(function(method, url, data) {
	    return [status,cartModification];
	  });
	
	//cartUpdate
	$httpBackend.whenPUT('http://localhost:8080/api/v1/cartService/').respond(function(method, url, data) {
	    return [status,cartModificationUpdate];
	  });
	
	//addBillingAddress
	$httpBackend.whenPOST('http://localhost:8080/api/v1/cartService/address/billing').respond(function(method, url, data) {
	    return [status,cartModificationUpdate];
	  });

	//addShippingAddress
	$httpBackend.whenPOST('http://localhost:8080/api/v1/cartService/address/shipping').respond(function(method, url, data) {
	    return [status,cartModificationUpdate];
	  });	

}]);




