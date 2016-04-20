(function() {
	'use strict';
	angular.module('serpics.services',[
	                                 'service.config',//config with url, cocckies, and default store
	                                 'session.service',// service for retrive session id
	                                 'brand.service',
	                                 'category.service',
	                                 'product.service',
	                                 'order.service',
	                                 'cart.service',
	                                 'customer.service',
	                                 'geographic.service'
	                                   ]);
})();