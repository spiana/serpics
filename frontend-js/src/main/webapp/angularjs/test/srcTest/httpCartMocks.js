var httpCartMocks = angular.module('http.cart.mocks', ['ngMock','serpics.config']);

httpCartMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	var customer= {"responseObject":{"updated":"2016-01-04T13:04:51 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"a0f4e309-83ca-4a80-964c-6fd682357310","id":17,
		"firstname":"Gabriele","lastname":"Gabrieles","email":"gabri@gabri.it","userType":"REGISTERED","logonid":"gabri",
		"contactAddress":{"updated":"2015-12-16T09:25:15 GMT","created":"2015-12-16T09:25:15 GMT","uuid":"432a17ce-dcb4-493b-a8be-eda9572befd3"},
		"destinationAddress":[]}};
	
	var currentCart={"responseObject":{"updated":"2015-12-30T11:22:21 GMT","created":"2015-12-30T11:21:39 GMT","uuid":"436d39ce-a827-47dd-b84f-85b09538cf7a","id":1180,"orderItems":[{"updated":"2015-12-30T11:22:20 GMT","created":"2015-12-30T11:22:20 GMT","uuid":"b8290c20-821c-4c01-90bf-8057da6624ed","id":252,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":30.99,"totalShipping":0.0,"totalTax":0.0}}
	
	var cartModification= {"responseObject":{"modificationStatus":"OK","cart":{"updated":"2015-12-30T15:49:40 GMT","created":"2015-12-30T15:28:41 GMT","uuid":"94313ab9-3e5a-4205-8e0f-900e293d6ac8","id":1183,"billingAddress":{"updated":"2015-12-30T15:49:40 GMT","created":"2015-12-30T15:49:40 GMT","uuid":"f097f572-6c2c-484a-b180-be0d45d88742","id":135,"firstname":"Fede","lastname":"Pic","company":"stepfour","email":"fpicinelli@stepfour.it","address1":"VIA LUIGI PIRANDELLO","streetNumber":"8","address2":"6","zipcode":"26025","city":"PANDINO","phone":"333333333","mobile":"333333333","fax":"333333333"},"orderItems":[{"updated":"2015-12-30T15:28:41 GMT","created":"2015-12-30T15:28:41 GMT","uuid":"943e8d8d-2016-4021-9dd8-d5ee6c88c2df","id":256,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":30.99,"totalShipping":0.0,"totalTax":0.0}}};
	
	var cartModificationUpdate ={"responseObject":{"modificationStatus":"OK","cart":{"updated":"2015-12-30T12:05:43 GMT","created":"2015-12-30T11:52:20 GMT","uuid":"b9dd3fa1-f6a9-409b-bc10-741c0dacf6d2","id":1181,"orderItems":[{"updated":"2015-12-30T12:05:43 GMT","created":"2015-12-30T12:05:37 GMT","uuid":"dce20718-a56a-438d-9efc-1a8419662fc2","id":254,"discountAmount":0.0,"discountPerc":0.0,"quantity":2.0,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0.0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0.0,"shippingAddressId":0},{"updated":"2015-12-30T12:05:30 GMT","created":"2015-12-30T12:05:30 GMT","uuid":"437a8169-50b9-4502-8ab4-1e1ecfca0481","id":253,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":75.99,"totalShipping":0.0,"totalTax":0.0}}};
	
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });
	
	//***CartService rest-api endpoint***//
	
	//getCurrent endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/customerService/getCurrent').respond(function(method, url, data) {
	    return [status,customer];
	  });
	
	//CartService endpoint getCurrentCart
	$httpBackend.whenGET('http://localhost:8080/api/v1/cartService/').respond(function(method, url, data) {
	    return [status,currentCart];
	  });
	//cartDelete
	$httpBackend.whenDELETE('http://localhost:8080/api/v1/cartService/?itemId=1').respond(function(method, url, data) {
	    return [status,cartModification];
	  });
	
	//cartAdd
	$httpBackend.whenPOST('http://localhost:8080/api/v1/cartService/').respond(function(method, url, data) {
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
	
	
	//***Html***//
	
	
	//html/template/shop.html
	$httpBackend.whenGET('html/template/shop.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/home-central.html
	$httpBackend.whenGET('html/template/home-central.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/orderComplete.html
	$httpBackend.whenGET('html/template/orderComplete.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/checkout.html
	$httpBackend.whenGET('html/template/checkout.html').respond(function(method, url, data) {
	    return [status];
	  });
	
	//html/template/checkoutShippingAddress.html
	$httpBackend.whenGET('html/template/checkoutShippingAddress.html').respond(function(method, url, data) {
	    return [status];
	  });
	
 
}]);




