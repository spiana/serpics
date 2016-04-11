var httpProductMocks = angular.module('http.product.mocks', ['ngMock']);

httpProductMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var product={"responseObject":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT",
		"uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,
		"price":{"currentPrice":30.99,"minQty":0,"precedence":0},
		"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},
		"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":10}]}}
	
	//Paginato content
	var productCategoryPage0Size3={
			   "responseObject":    {
			      "content": [      {
			         "updated": "2015-11-18T14:19:11 GMT",
			         "created": "2015-11-16T16:18:02 GMT",
			         "uuid": "70ff79f3-a52b-4312-8fb7-ef7acf4cc948",
			         "id": 8,
			         "code": "MONITOR",
			         "url": "/default-catalog/product/Monitor",
			         "published": 0,
			         "buyable": 1,
			         "dowloadable": 0,
			         "price":          {
			            "currentPrice": 1500,
			            "minQty": 0,
			            "precedence": 0
			         },
			         "brand":          {
			            "updated": "2015-11-16T16:01:58 GMT",
			            "created": "2015-11-16T16:01:58 GMT",
			            "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
			            "id": 7,
			            "logo": "Logo",
			            "name": "TOSHIBA",
			            "brandProductNumber": 6,
			            "published": 1
			         },
			         "medias": [],
			         "categories": [         {
			            "updated": "2015-11-16T16:11:58 GMT",
			            "created": "2015-11-16T16:11:58 GMT",
			            "uuid": "183e775c-9675-43ce-a1d8-cf5a83228c0e",
			            "id": 2,
			            "code": "CAVO HDMI",
			            "url": "/default-catalog/provacategoria1",
			            "published": 1,
			            "catalogId": "default-catalog",
			            "childCategoryNumber": 0,
			            "childProductNumber": 1
			         }]
			      }],
			      "last": true,
			      "totalElements": 1,
			      "totalPages": 1,
			      "first": true,
			      "numberOfElements": 1,
			      "size": 3,
			      "number": 0
			   }
			};
	
	var productCategory={
			   "responseObject":    {
			      "content": [      {
			         "updated": "2015-11-18T14:19:11 GMT",
			         "created": "2015-11-16T16:18:02 GMT",
			         "uuid": "70ff79f3-a52b-4312-8fb7-ef7acf4cc948",
			         "id": 8,
			         "code": "MONITOR",
			         "url": "/default-catalog/product/Monitor",
			         "published": 0,
			         "buyable": 1,
			         "dowloadable": 0,
			         "price":          {
			            "currentPrice": 1500,
			            "minQty": 0,
			            "precedence": 0
			         },
			         "brand":          {
			            "updated": "2015-11-16T16:01:58 GMT",
			            "created": "2015-11-16T16:01:58 GMT",
			            "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
			            "id": 7,
			            "logo": "Logo",
			            "name": "TOSHIBA",
			            "brandProductNumber": 6,
			            "published": 1
			         },
			         "medias": [],
			         "categories": [         {
			            "updated": "2015-11-16T16:11:58 GMT",
			            "created": "2015-11-16T16:11:58 GMT",
			            "uuid": "183e775c-9675-43ce-a1d8-cf5a83228c0e",
			            "id": 2,
			            "code": "CAVO HDMI",
			            "url": "/default-catalog/provacategoria1",
			            "published": 1,
			            "catalogId": "default-catalog",
			            "childCategoryNumber": 0,
			            "childProductNumber": 1
			         }]
			      },{
				         "updated": "2015-11-18T14:19:11 GMT",
				         "created": "2015-11-16T16:18:02 GMT",
				         "uuid": "70ff79f3-a52b-4312-8fb7-ef7acf4cc948",
				         "id": 8,
				         "code": "MONITOR",
				         "url": "/default-catalog/product/Monitor",
				         "published": 0,
				         "buyable": 1,
				         "dowloadable": 0,
				         "price":          {
				            "currentPrice": 1500,
				            "minQty": 0,
				            "precedence": 0
				         },
				         "brand":          {
				            "updated": "2015-11-16T16:01:58 GMT",
				            "created": "2015-11-16T16:01:58 GMT",
				            "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
				            "id": 7,
				            "logo": "Logo",
				            "name": "TOSHIBA",
				            "brandProductNumber": 6,
				            "published": 1
				         },
				         "medias": [],
				         "categories": [         {
				            "updated": "2015-11-16T16:11:58 GMT",
				            "created": "2015-11-16T16:11:58 GMT",
				            "uuid": "183e775c-9675-43ce-a1d8-cf5a83228c0e",
				            "id": 2,
				            "code": "CAVO HDMI",
				            "url": "/default-catalog/provacategoria1",
				            "published": 1,
				            "catalogId": "default-catalog",
				            "childCategoryNumber": 0,
				            "childProductNumber": 1
				         }]
				      }],
			      "last": true,
			      "totalElements": 2,
			      "totalPages": 1,
			      "first": true,
			      "numberOfElements": 2,
			      "size": 3,
			      "number": 0
			   }
			};
	
	//Paginato content
	var productBrandPage0Size3={
		   "responseObject":    {
		      "content":       [
		                  {
		            "updated": "2015-11-19T10:39:20 GMT",
		            "created": "2015-11-19T10:39:20 GMT",
		            "uuid": "a5348dd5-4b4e-43e2-8764-ec4bad25736d",
		            "id": 776,
		            "code": "USB HARD DISK",
		            "url": "/default-catalog/product/USB HARD DISK",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "price":             {
		               "currentPrice": 89.99,
		               "minQty": 0,
		               "precedence": 0
		            },
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories": [            {
		               "updated": "2015-11-16T17:01:46 GMT",
		               "created": "2015-11-16T17:01:46 GMT",
		               "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		               "id": 19,
		               "code": "COMPUTER",
		               "url": "/default-catalog/COMPUTER",
		               "published": 1,
		               "catalogId": "default-catalog",
		               "childCategoryNumber": 5,
		               "childProductNumber": 10
		            }]
		         },
		                  {
		            "updated": "2015-11-19T10:39:42 GMT",
		            "created": "2015-11-19T10:39:42 GMT",
		            "uuid": "ef3a86a1-63a9-40ab-a043-9fe907f44d19",
		            "id": 777,
		            "code": "NOKIA 662",
		            "url": "/default-catalog/product/NOKIA 662",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "price":             {
		               "currentPrice": 256.67,
		               "minQty": 0,
		               "precedence": 0
		            },
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories":             [
		                              {
		                  "updated": "2015-11-20T10:19:32 GMT",
		                  "created": "2015-11-20T10:19:32 GMT",
		                  "uuid": "554b81a5-7f05-484d-b245-d9e536861c98",
		                  "id": 780,
		                  "code": "MOBILE",
		                  "url": "/default-catalog/MOBILE",
		                  "published": 0,
		                  "catalogId": "default-catalog",
		                  "childCategoryNumber": 0,
		                  "childProductNumber": 3
		               },
		                              {
		                  "updated": "2015-11-16T17:01:46 GMT",
		                  "created": "2015-11-16T17:01:46 GMT",
		                  "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		                  "id": 19,
		                  "code": "COMPUTER",
		                  "url": "/default-catalog/COMPUTER",
		                  "published": 1,
		                  "catalogId": "default-catalog",
		                  "childCategoryNumber": 5,
		                  "childProductNumber": 10
		               }
		            ]
		         },
		                  {
		            "updated": "2015-11-19T10:40:02 GMT",
		            "created": "2015-11-19T10:40:02 GMT",
		            "uuid": "11f8d1e7-1b37-4fda-b02f-05ad0dd57e6b",
		            "id": 778,
		            "code": "AURICOLARI NOKIA",
		            "url": "/default-catalog/product/AURICOLARI NOKIA",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories": [            {
		               "updated": "2015-11-16T17:01:46 GMT",
		               "created": "2015-11-16T17:01:46 GMT",
		               "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		               "id": 19,
		               "code": "COMPUTER",
		               "url": "/default-catalog/COMPUTER",
		               "published": 1,
		               "catalogId": "default-catalog",
		               "childCategoryNumber": 5,
		               "childProductNumber": 10
		            }]
		         }
		      ],
		      "last": true,
		      "totalElements": 3,
		      "totalPages": 1,
		      "first": true,
		      "numberOfElements": 3,
		      "size": 3,
		      "number": 0
		   }
		};
	
	var productBrand={
		   "status": "OK",
		   "responseObject":    {
		      "content":       [
		                  {
		            "updated": "2015-11-19T10:39:20 GMT",
		            "created": "2015-11-19T10:39:20 GMT",
		            "uuid": "a5348dd5-4b4e-43e2-8764-ec4bad25736d",
		            "id": 776,
		            "code": "USB HARD DISK",
		            "url": "/default-catalog/product/USB HARD DISK",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "price":             {
		               "currentPrice": 89.99,
		               "minQty": 0,
		               "precedence": 0
		            },
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories": [            {
		               "updated": "2015-11-16T17:01:46 GMT",
		               "created": "2015-11-16T17:01:46 GMT",
		               "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		               "id": 19,
		               "code": "COMPUTER",
		               "url": "/default-catalog/COMPUTER",
		               "published": 1,
		               "catalogId": "default-catalog",
		               "childCategoryNumber": 5,
		               "childProductNumber": 10
		            }]
		         },
		                  {
		            "updated": "2015-11-19T10:39:42 GMT",
		            "created": "2015-11-19T10:39:42 GMT",
		            "uuid": "ef3a86a1-63a9-40ab-a043-9fe907f44d19",
		            "id": 777,
		            "code": "NOKIA 662",
		            "url": "/default-catalog/product/NOKIA 662",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "price":             {
		               "currentPrice": 256.67,
		               "minQty": 0,
		               "precedence": 0
		            },
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories":             [
		                              {
		                  "updated": "2015-11-16T17:01:46 GMT",
		                  "created": "2015-11-16T17:01:46 GMT",
		                  "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		                  "id": 19,
		                  "code": "COMPUTER",
		                  "url": "/default-catalog/COMPUTER",
		                  "published": 1,
		                  "catalogId": "default-catalog",
		                  "childCategoryNumber": 5,
		                  "childProductNumber": 10
		               },
		                              {
		                  "updated": "2015-11-20T10:19:32 GMT",
		                  "created": "2015-11-20T10:19:32 GMT",
		                  "uuid": "554b81a5-7f05-484d-b245-d9e536861c98",
		                  "id": 780,
		                  "code": "MOBILE",
		                  "url": "/default-catalog/MOBILE",
		                  "published": 0,
		                  "catalogId": "default-catalog",
		                  "childCategoryNumber": 0,
		                  "childProductNumber": 3
		               }
		            ]
		         },
		                  {
		            "updated": "2015-11-19T10:40:02 GMT",
		            "created": "2015-11-19T10:40:02 GMT",
		            "uuid": "11f8d1e7-1b37-4fda-b02f-05ad0dd57e6b",
		            "id": 778,
		            "code": "AURICOLARI NOKIA",
		            "url": "/default-catalog/product/AURICOLARI NOKIA",
		            "published": 0,
		            "buyable": 1,
		            "dowloadable": 0,
		            "brand":             {
		               "updated": "2015-11-16T16:01:58 GMT",
		               "created": "2015-11-16T16:01:58 GMT",
		               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
		               "id": 6,
		               "logo": "Logo",
		               "name": "NOKIA",
		               "brandProductNumber": 3,
		               "published": 1
		            },
		            "medias": [],
		            "categories": [            {
		               "updated": "2015-11-16T17:01:46 GMT",
		               "created": "2015-11-16T17:01:46 GMT",
		               "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
		               "id": 19,
		               "code": "COMPUTER",
		               "url": "/default-catalog/COMPUTER",
		               "published": 1,
		               "catalogId": "default-catalog",
		               "childCategoryNumber": 5,
		               "childProductNumber": 10
		            }]
		         },
                 {
			            "updated": "2015-11-19T10:40:02 GMT",
			            "created": "2015-11-19T10:40:02 GMT",
			            "uuid": "11f8d1e7-1b37-4fda-b02f-05ad0dd57e6b",
			            "id": 778,
			            "code": "AURICOLARI NOKIA",
			            "url": "/default-catalog/product/AURICOLARI NOKIA",
			            "published": 0,
			            "buyable": 1,
			            "dowloadable": 0,
			            "brand":             {
			               "updated": "2015-11-16T16:01:58 GMT",
			               "created": "2015-11-16T16:01:58 GMT",
			               "uuid": "668a270b-d951-45ee-8d4e-9b4c8f716f0a",
			               "id": 6,
			               "logo": "Logo",
			               "name": "NOKIA",
			               "brandProductNumber": 3,
			               "published": 1
			            },
			            "medias": [],
			            "categories": [            {
			               "updated": "2015-11-16T17:01:46 GMT",
			               "created": "2015-11-16T17:01:46 GMT",
			               "uuid": "3fd6597a-edac-4825-bed0-5245b9d81e42",
			               "id": 19,
			               "code": "COMPUTER",
			               "url": "/default-catalog/COMPUTER",
			               "published": 1,
			               "catalogId": "default-catalog",
			               "childCategoryNumber": 5,
			               "childProductNumber": 10
			            }]
			         }
		      ],
		      "last": true,
		      "totalElements": 4,
		      "totalPages": 1,
		      "first": true,
		      "numberOfElements": 4,
		      "size": 10,
		      "number": 0
		   }
		}
	
	var cartModification= {"responseObject":{"modificationStatus":"OK","cart":{"updated":"2015-12-30T15:49:40 GMT","created":"2015-12-30T15:28:41 GMT","uuid":"94313ab9-3e5a-4205-8e0f-900e293d6ac8","id":1183,"billingAddress":{"updated":"2015-12-30T15:49:40 GMT","created":"2015-12-30T15:49:40 GMT","uuid":"f097f572-6c2c-484a-b180-be0d45d88742","id":135,"firstname":"Fede","lastname":"Pic","company":"stepfour","email":"fpicinelli@stepfour.it","address1":"VIA LUIGI PIRANDELLO","streetNumber":"8","address2":"6","zipcode":"26025","city":"PANDINO","phone":"333333333","mobile":"333333333","fax":"333333333"},"orderItems":[{"updated":"2015-12-30T15:28:41 GMT","created":"2015-12-30T15:28:41 GMT","uuid":"943e8d8d-2016-4021-9dd8-d5ee6c88c2df","id":256,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":30.99,"totalShipping":0.0,"totalTax":0.0}}};

	var category={"responseObject":{"updated":"2015-11-16T16:12:17 GMT","created":"2015-11-16T16:12:17 GMT","uuid":"c4d5a364-4a07-4e9a-aa68-4fe5cd31c538","id":4,"code":"GUESS","url":"/default-catalog/provacategoria3","published":1,"catalogId":"default-catalog","childCategoryNumber":0,"childProductNumber":0}};
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });
	
	//***ProductService rest-api endpoint***//
	
	//getCategoryById
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/1').respond(function(method, url, data) {
	    return [status,product];
	  });
	
	//getProductByName
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/byCode/MONITOR').respond(function(method, url, data) {
	    return [status,product];
	  });
	
	//getCategoryProduct
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/getCategory/1').respond(function(method, url, data) {
	    return [status,category];
	  });
	
	//findByCategory(categoryId, page, size)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/pageCategory/1?page=0&size=10').respond(function(method, url, data) {
	    return [status,productCategoryPage0Size3];
	  });
	
	//findByCategory(categoryId)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/pageCategory/1').respond(function(method, url, data) {
	    return [status,productCategory];
	  });
	
	//findByBrand(brandId, page, size)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/pageBrand/1?page=0&size=10').respond(function(method, url, data) {
	    return [status,productBrandPage0Size3];
	  });
	
	//findByBrand(brandId)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/pageBrand/1').respond(function(method, url, data) {
	    return [status,productBrand];
	  });
	
	//findAll(page, size)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/?page=0&size=10').respond(function(method, url, data) {
	    return [status,productBrandPage0Size3];
	  });
	
	//findAll()
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/').respond(function(method, url, data) {
	    return [status,productBrand];
	  });
	
	//findAll(page, size 9)
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/?page=0&size=9').respond(function(method, url, data) {
	    return [status,productBrandPage0Size3];
	  });
	
	//$scope.range
	$httpBackend.whenGET('http://localhost:8080/api/v1/productService/?page=0&size=3').respond(function(method, url, data) {
	    return [status,productBrandPage0Size3];
	  });
	
	//$scope.addToCart(sku,quantity)
	$httpBackend.whenPOST('http://localhost:8080/api/v1/cartService/').respond(function(method, url, data) {
	    return [400,cartModification];
	  });
	
		
	//***Html***//
	
	
	//html/template/shop.html
	$httpBackend.whenGET('html/template/shop.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/home-central.html
	$httpBackend.whenGET('html/template/home-central.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/home-central.html
	$httpBackend.whenGET('html/template/cart.html').respond(function(method, url, data) {
	    return [status];
	  });

}]);




