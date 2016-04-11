var httpOrderMocks = angular.module('http.order.mocks', ['ngMock']);

httpOrderMocks.run(['$httpBackend',function($httpBackend) {
	
	var status = 200;
	var ssid = "12345";
	
	
	var order = {"responseObject":{"updated":"2015-12-28T14:41:08 GMT","created":"2015-12-28T14:21:39 GMT","uuid":"f8321898-a8ff-48c9-b13a-73fa6eaacd58","id":1146,
		"billingAddress":{"updated":"2015-12-28T14:41:07 GMT","created":"2015-12-28T14:41:07 GMT","uuid":"d3e56258-8201-4776-a534-609632d78d0d","id":96,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"shippingAddress":{"updated":"2015-12-28T14:41:07 GMT","created":"2015-12-28T14:41:07 GMT","uuid":"492ef26d-8ee2-4101-a257-80dd6aeeff70","id":97,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"orderItems":[],"totalProduct":0.0,"totalShipping":0.0,"totalTax":0.0}};
	
	var orders = {"responseObject":[{"updated":"2015-12-28T13:00:29 GMT","created":"2015-12-28T12:51:08 GMT","uuid":"d1d533fa-6028-4dc3-8edb-01243c119311","id":01,
		"billingAddress":{"updated":"2015-12-28T13:00:28 GMT","created":"2015-12-28T13:00:28 GMT","uuid":"db7556b0-915e-4607-96e7-7ca5c1eb4fb9","id":94,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"shippingAddress":{"updated":"2015-12-28T13:00:29 GMT","created":"2015-12-28T13:00:29 GMT","uuid":"0e048db7-8587-4c20-a319-0ba117d50809","id":95,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"orderItems":[{"updated":"2015-12-28T13:00:12 GMT","created":"2015-12-28T13:00:12 GMT","uuid":"e5811046-97ec-449e-9252-8040f87ebf45","id":237,"discountAmount":0.0,"discountPerc":0.0,"quantity":4.0,
			"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,
				"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],
				"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":123.96,"totalShipping":0.0,"totalTax":3.0},
				{"updated":"2015-12-28T14:41:08 GMT","created":"2015-12-28T14:21:39 GMT","uuid":"f8321898-a8ff-48c9-b13a-73fa6eaacd58","id":1146,"billingAddress":{"updated":"2015-12-28T14:41:07 GMT","created":"2015-12-28T14:41:07 GMT","uuid":"d3e56258-8201-4776-a534-609632d78d0d","id":96,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},"shippingAddress":{"updated":"2015-12-28T14:41:07 GMT","created":"2015-12-28T14:41:07 GMT","uuid":"492ef26d-8ee2-4101-a257-80dd6aeeff70","id":97,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},"orderItems":[],"totalProduct":0.0,"totalShipping":0.0,"totalTax":0.0},{"updated":"2015-12-28T14:56:38 GMT","created":"2015-12-28T14:55:44 GMT","uuid":"d5b28347-727c-4467-8431-a91e1787f113","id":1148,"billingAddress":{"updated":"2015-12-28T14:56:37 GMT","created":"2015-12-28T14:56:37 GMT","uuid":"8169c83c-3dd8-45a7-8cfe-6eb6301c9265","id":101,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"8","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},"shippingAddress":{"updated":"2015-12-28T14:56:37 GMT","created":"2015-12-28T14:56:37 GMT","uuid":"7a609302-462c-40df-848a-ebf52d29e9f7","id":102,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"8","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},"orderItems":[{"updated":"2015-12-28T14:55:48 GMT","created":"2015-12-28T14:55:48 GMT","uuid":"81cb4abb-cbb9-42cd-90e2-a9ecfca6b4b1","id":238,"discountAmount":0.0,"discountPerc":0.0,"quantity":1.0,"product":{"updated":"2015-11-18T14:31:07 GMT","created":"2015-11-16T16:12:59 GMT","uuid":"80513282-94b5-4708-988d-8ae5c1919d3d","id":6,"code":"MOUSE","url":"/default-catalog/product/Mouse","published":0,"buyable":1,"dowloadable":0,"price":{"currentPrice":22.5,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"MOUSE","skuCost":0.0,"skuNetPrice":22.5,"skuPrice":22.5,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":22.5,"totalShipping":0.0,"totalTax":0.0},{"updated":"2015-12-29T11:43:23 GMT","created":"2015-12-29T11:42:53 GMT","uuid":"a8852aae-83b7-45ea-8f68-eae5cad2ca70","id":1163,"billingAddress":{"updated":"2015-12-29T11:43:12 GMT","created":"2015-12-29T11:43:12 GMT","uuid":"ca40b93a-ec5e-43c8-9aa2-9084449d0a72","id":125,"firstname":"Fede","lastname":"Pic","email":"fpicinelli@stepfour.it","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","address2":"6","zipcode":"26025","city":"PANDINO","phone":"333333333","mobile":"333333333","flag":"TEMPORARY"},"shippingAddress":{"updated":"2015-12-29T11:43:22 GMT","created":"2015-12-29T11:43:22 GMT","uuid":"0a74641d-01b8-4a27-abec-a1819443e774","id":126,"firstname":"Fede","lastname":"Pic","email":"fpicinelli@stepfour.it","address1":"VIA LUIGI PIRANDELLO","streetNumber":"8","address2":"6","zipcode":"26025","city":"PANDINO","phone":"333333333","mobile":"333333333","flag":"TEMPORARY"},"orderItems":[],"totalProduct":0.0,"totalShipping":0.0,"totalTax":0.0}]};
	
	
	var orderModification ={"responseObject":[{"updated":"2015-12-28T13:00:29 GMT","created":"2015-12-28T12:51:08 GMT","uuid":"d1d533fa-6028-4dc3-8edb-01243c119311","id":02,
		"billingAddress":{"updated":"2015-12-28T13:00:28 GMT","created":"2015-12-28T13:00:28 GMT","uuid":"db7556b0-915e-4607-96e7-7ca5c1eb4fb9","id":94,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"shippingAddress":{"updated":"2015-12-28T13:00:29 GMT","created":"2015-12-28T13:00:29 GMT","uuid":"0e048db7-8587-4c20-a319-0ba117d50809","id":95,"firstname":"Fede","lastname":"Pic","address1":"VIA LUIGI PIRANDELLO","streetNumber":"6","zipcode":"26025","city":"PANDINO","flag":"TEMPORARY"},
		"orderItems":[{"updated":"2015-12-28T13:00:12 GMT","created":"2015-12-28T13:00:12 GMT","uuid":"e5811046-97ec-449e-9252-8040f87ebf45","id":237,"discountAmount":0.0,"discountPerc":0.0,"quantity":4.0,
			"product":{"updated":"2015-11-20T10:46:59 GMT","created":"2015-11-16T16:12:45 GMT","uuid":"a0441cae-08ae-43b3-96a3-e7ce10cf13a5","id":5,"code":"TASTIERA","url":"/default-catalog/product/Tastiera","published":0,"buyable":1,"dowloadable":0,
				"price":{"currentPrice":30.99,"minQty":0,"precedence":0},"brand":{"updated":"2015-11-16T16:01:58 GMT","created":"2015-11-16T16:01:58 GMT","uuid":"668a270b-d951-45ee-8d4e-9b4c8f716f0a","id":10,"logo":"Logo","name":"LOGITECH","brandProductNumber":3,"published":1},"medias":[],
				"categories":[{"updated":"2015-11-16T17:01:46 GMT","created":"2015-11-16T17:01:46 GMT","uuid":"3fd6597a-edac-4825-bed0-5245b9d81e42","id":19,"code":"COMPUTER","url":"/default-catalog/COMPUTER","published":1,"catalogId":"default-catalog","childCategoryNumber":5,"childProductNumber":11}]},"sku":"TASTIERA","skuCost":0.0,"skuNetPrice":30.99,"skuPrice":30.99,"shippingCost":0.0,"shippingAddressId":0}],"totalProduct":123.96,"totalShipping":0.0,"totalTax":0.0}]};
	
	
	//Authentication endpoint
	$httpBackend.whenGET('http://localhost:8080/api/v1/auth/connect/default-store').respond(function(method, url, data) {
	    return [status,ssid];
	  });

	//***OrderService rest-api endpoint***//
	
	//OrderService endpoint getOrders
	$httpBackend.whenGET('http://localhost:8080/api/v1/orderService/').respond(function(method, url, data) {
	    return [status,orders];
	  });
	//addPayment
	$httpBackend.whenPOST('http://localhost:8080/api/v1/orderService/addPayment/1').respond(function(method, url, data) {
	    return [status,order];
	  });
	
	//placeOrder
	$httpBackend.whenGET('http://localhost:8080/api/v1/orderService/placeOrder').respond(function(method, url, data) {
	    return [status,order];
	  });

}]);

