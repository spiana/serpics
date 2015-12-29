var serpicsMocks = angular.module('serpics.mocks', ['ngMock']);

serpicsMocks.run(function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var brandList ={ "responseObject":{"content":[{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logo","name":"PHILIPHS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:02 GMT","created":"2015-11-16T16:14:02 GMT","uuid":"bde83c7b-5503-4f4c-8c63-8b56540db517"
		,"id":2,"logo":"Logos","name":"MICROMICRO","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:12 GMT","created":"2015-11-16T16:14:12 GMT","uuid":"bf05b880-5645-4821-9877-5148b6d808cc","id":3
		,"logo":"Logos","name":"MULTITOUCH","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":4,"logo"
		:"Logo","name":"SAMSUNG","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":5,"logo":"Logo","name"
		:"APPLE","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":6,"logo":"Logo","name":"NOKIA","brandProductNumber"
		:3,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":7,"logo":"Logo","name":"TOSHIBA","brandProductNumber":6,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":8,"logo":"Logo","name":"LG","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":9,"logo":"Logo"
		,"name":"ASUS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber"
		:3,"published":1}]}};
	
	var brandPageSize ={ "responseObject":{"content":[{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logo","name":"PHILIPHS","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:02 GMT","created":"2015-11-16T16:14:02 GMT","uuid":"bde83c7b-5503-4f4c-8c63-8b56540db517"
		,"id":2,"logo":"Logos","name":"MICROMICRO","brandProductNumber":0,"published":1},{"updated":"2015-11-16T16:14:12 GMT","created":"2015-11-16T16:14:12 GMT","uuid":"bf05b880-5645-4821-9877-5148b6d808cc","id":3
		,"logo":"Logos","name":"MULTITOUCH","brandProductNumber":0,"published":1}]}};
	
	var brand={ "responseObject":{"content":[{"updated":"2015-11-16T16:13:41 GMT","created":"2015-11-16T16:13:41 GMT","uuid":"3b2e4404-6ab2-43cb-97e0-148074abb9a5","id":1,"logo":"Logos","name":"PHILIPHS","brandProductNumber":0,"published":1}]}};
	

	var category={};
	
	var categoryTop={"responseObject":[{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:25 GMT","created":"2015-11-16T17:01:25 GMT","uuid":"96ea6e5a-8c10-4e29-9f02-e7e74c887d15","id":17,"code":"MENS","url":"/default-catalog/MENS","published":1,"catalogId":"default-catalog","childCategoryNumber":4,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:36 GMT","created":"2015-11-16T17:01:36 GMT","uuid":"12358ab9-0549-4d66-9f9f-fe5ba309aef3","id":18,"code":"WOMENS","url":"/default-catalog/WOMENS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11},
		{"updated":"2015-11-16T17:01:58 GMT","created":"2015-11-16T17:01:58 GMT","uuid":"86d6f1a7-e22c-4994-9916-124639bb31e1","id":20,"code":"PARFUM","url":"/default-catalog/PARFUM","published":1,"catalogId":"default-catalog","childCategoryNumber":1,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:19 GMT","created":"2015-11-16T17:02:19 GMT","uuid":"9fefe1cf-3cbb-4565-a294-02d8ad25779c","id":22,"code":"LEGEA","url":"/default-catalog/LEGEA","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:30 GMT","created":"2015-11-16T17:02:30 GMT","uuid":"5451fd28-4787-4d81-815e-972bbc76ab60","id":23,"code":"KIDS","url":"/default-catalog/KIDS","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-16T17:02:47 GMT","created":"2015-11-16T17:02:47 GMT","uuid":"d15d64ea-e0dd-412c-9e36-17359886ce47","id":24,"code":"SURF","url":"/default-catalog/SURF","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0},
		{"updated":"2015-11-20T10:19:32 GMT","created":"2015-11-20T10:19:32 GMT","uuid":"554b81a5-7f05-484d-b245-d9e536861c98","id":780,"code":"MOBILE","url":"/default-catalog/MOBILE","published":0,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":3}]};
	
	var categoryList={};
	
	var categoryPageSize={};
	
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });
	
	
	//***Brand rest-api endpoint***
	$httpBackend.whenGET('http://localhost:8080/jax-rs/brandService/?page=0&size=10').respond(function(method, url, data) {
	    return [status,brandList];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/brandService/code/1').respond(function(method, url, data) {
	    return [status,brand];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/brandService/PHILIPHS').respond(function(method, url, data) {
	    return [status,brand];
	  });
	
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/brandService/').respond(function(method, url, data) {
	    return [status,brandList];
	  });
	
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/brandService/?page=0&size=3').respond(function(method, url, data) {
	    return [status,brandPageSize];
	  });
	
	
	//***Category rest-api endpoint
	$httpBackend.whenGET('http://localhost:8080/jax-rs/categoryService/top').respond(function(method, url, data) {
	    return [status,categoryTop];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/categoryService/1').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	$httpBackend.whenGET('http://localhost:8080/jax-rs/categoryService/code/PHILIPHS').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	
	
	
	
});




