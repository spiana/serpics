var httpCategoryMocks = angular.module('http.category.mocks', ['ngMock']);

httpCategoryMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
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
	
	//getTopQ()
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
			
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });
	
	//***CategoryService rest-api endpoint***//
	
	//getTopQ getTop
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/top').respond(function(method, url, data) {
	    return [status,categoryTop];
	  });
	
	//getCategoryById
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/4').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	//getCategoryByCode
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/code/GUESS').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	//findAll
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/').respond(function(method, url, data) {
	    return [status,categoryPageSize];
	  });
	
	//getChild
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/getChild/0').respond(function(method, url, data) {
	    return [status,categoryList];
	  });
	
	//findAll
	$httpBackend.whenGET('http://localhost:8080/api/v1/categoryService/?page=0&size=3').respond(function(method, url, data) {
	    return [status,categoryPage0Size3];
	  });
	
}]);




