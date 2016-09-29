(function(){
	'use strict';
	angular.module('cart.controller', [])
	/** cartController **/
	.controller('CartController',cartController);
	
	cartController.$inject = ['$state','customerService', 'cartService','logger',
	                          '$stateParams','geographicService','$window'];
                                  

	/** @ngInject */
	function cartController($state,customerService,cartService,logger,$stateParams,geographicService,$window) {
		
		/* jshint validthis: true */
		var vm = this;
		vm.cart = getCurrentCart();
		vm.currentUser = customerService.currentUser;
		vm.shipmodeList = {};
		vm.paymethodList = {};
		vm.countries = {};
		vm.regions = {};
		vm.districts = {};
		vm.payment = {};
		vm.stateParams = $state.current.name;
		
	  	
	    function getCurrentCart() {
	    	logger.debug('CartController getCurrentCart()'+angular.toJson(vm.stateParams));
  			cartService.getCurrentCart().then(function(response){
    			  logger.debug('CartController getCurrentCart(): ramo then');
    			  vm.cart = response;
    		  });
  	    }

  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,
  	     */
  		vm.getCurrentCart = function() {
  	    	getCurrentCart();
  	    };
  	    
  	    /**
  	     * @param sku 					sku to send
  	     * @param quantity 		        quantity to send
  	     * @return 						a new cartItemModification
  	     * @use 						cartService,
  	     */
  	    vm.cartAdd = function(sku ,quantity) {
  	    	logger.debug('CartController cartAdd(sku ,quantity)');
  			cartService.cartAdd(sku ,quantity).then(function(response){
    			  logger.debug('CartController cartAdd(sku ,quantity): ramo then');
    			  vm.cart = response.cart;
    		  });
  	    };
  	    
  	   /**
  	     * @param cartItem    			cartItem to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,
  	     */
  	    vm.cartUpdate = function( cartItem, quantity ) {
  	    	cartItem.quantity = quantity;
  	    	logger.debug('CartController cartUpdate(cartItem)');
  			cartService.cartUpdate(cartItem).then(function(){
    			  logger.debug('CartController cartUpdate(cartItem): ramo then');
    			  getCurrentCart();
    		  });
  	    };
  	    
  	    /**
  	     * @param itemId 	    	itemId to delete
  	     * @return 					cartItemModification
  	     * @use 					cartService,
  	     */
  	    vm.deleteItem = function(itemId) {		
  	    	logger.debug('CartController deleteItem(itemId)');
  			cartService.deleteItem(itemId).then(function(response){
    			  logger.debug('CartController deleteItem(itemId): ramo then');
    			  vm.cart = response.cart;
    		  });
  	    };
  	    
  	    /**
  	     * @param billingAddress 	billingAddress to add
  	     * @return 					a cart update with @param data
  	     * @use 					cartService,
  	     */
	  	 vm.submitBillingForm = function (billingAddress,shippingToBill){
				if (billingAddress.country != null){
					billingAddress.countryIso3Code = billingAddress.country.iso3Code;
  				}
  				if (billingAddress.region != null){
  					billingAddress.regionName = billingAddress.region.name;
  				}
  				if (billingAddress.district != null){
  					billingAddress.districtIsoCode = billingAddress.district.isoCode;
  				}
	  		cartService.addBillingAddress(billingAddress).then(function(response){
				  logger.debug('cartController addBillingAddress(billingAddress): ramo then1');
				  //var complete = $stateParams.complete;
				  if (shippingToBill) {
					  cartService.addShippingAddress(billingAddress).then(function(response){
		    			  logger.debug('cartController billingAddress(billingAddress): ramo then');
		    			  vm.cart = response;
		    			  $state.go($stateParams.shipmode);
					  });
				  } else {
					  vm.cart = response;
					  logger.debug('cartController billingAddress(billingAddress): ramo else'+$stateParams.toJson());
	    			  $state.go($stateParams.shipping);
				  }
			 });
	  	 };
		
	  	/**
	  	 * @param shippingAddress 	shippingAddress to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		vm.submitShippingForm = function (shippingAddress){
			if (shippingAddress.country != null){
				shippingAddress.countryIso3Code = shippingAddress.country.iso3Code;
				}
				if (shippingAddress.region != null){
					shippingAddress.regionName = shippingAddress.region.name;
				}
				if (shippingAddress.district != null){
					shippingAddress.districtIsoCode = shippingAddress.district.isoCode;
				}
	  			cartService.addShippingAddress(shippingAddress).then(function(response){
	  			  logger.debug('cartController shippingAddress(shippingAddress): ramo then');
	  			  vm.cart = response;
	  			  $state.go($stateParams.shipmode);
	  		  });
	  	};
	  	
	  	/**
	  	 * @param shipMode		 	shipMode to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		vm.addShipmode = function (shipmode){
	  			cartService.addShipmode(shipmode).then(function(response){
	  			  logger.debug('cartController addShipmode(shipMode): ramo then');
	  			  vm.cart = response;
	  			  $state.go($stateParams.payment)
	  		  });
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					a list of paymethod available for current store
	  	 * @use 					cartService,
	  	 */	  	 
		vm.getPaymethodList = function (){
	  			cartService.getPaymethodList().then(function(response){
	  			  logger.debug('cartController getPaymethodList(): ramo then');
	  			  vm.paymethodList = response;
	  		  });
	  	};
	  	
	  	/**
	  	 * @param shipMode		 	shipMode to add
	  	 * @return 					a cart update with @param data
	  	 * @use 					cartService,
	  	 */	  	 
		vm.addPaymethod = function (paymethod){
	  			cartService.addPaymethod(paymethod).then(function(response){
	  			  logger.debug('cartController addPaymethod(shipMode): ramo then');
	  			  vm.cart = response;
	  			  $state.go($stateParams.payment);
	  		  });
	  	};
	  	
	  	/**
	  	 * @return 					a cart update with 
	  	 * @use 					cartService,
	  	 */	  	 
		vm.createPayment = function (){
	  			cartService.createPayment().then(function(response){
	  			  logger.debug('cartController createPayment(): ramo then');
	  			  vm.payment = response;
	  			  if (response.authorizedURL != null){
	  				$window.location.href = response.authorizedURL;
	  			  }else{
	  				$state.go($stateParams.complete);
	  			  }
	  			  
	  		  });
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					a list of shipmode available for currentCart
	  	 * @use 					cartService,
	  	 */	  	 
		vm.getShipmodeList = function (){
	  			cartService.getShipmodeList().then(function(response){
	  			  logger.debug('cartController getShipmodeList(): ramo then');
	  			  vm.shipmodeList = response;
	  		  });
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					new sessionCart
	  	 * @use 					cartService,
	  	 */	  	 
		vm.deleteCart = function (){
	  			cartService.deleteCart().then(function(response){
	  			  logger.debug('cartController deleteCart(): ramo then');
	  			  vm.cart = response;
	  		  });
	  	};
	  	
	  	/**
	  	 * @param		 	
	  	 * @return 					country list
	  	 * @use 					geographicService,
	  	 */	  	 
		vm.getCountryList = function (){
			geographicService.getCountryList().then(function(response){
	  			  logger.debug('cartController getCountryList(): ramo then');
	  			  vm.countries = response;
	  		  });
	  	};
  		
	  	/**
	  	 * @param		 			countryId
	  	 * @return 					region list
	  	 * @use 					geographicService,
	  	 */	  	 
		vm.getRegionByCountry = function (countryId){
			if ( !angular.isUndefined(countryId)){
				geographicService.getRegionByCountry(countryId).then(function(response){
		  			  logger.debug('cartController getRegionByCountry(countryId): ramo then');
		  			  vm.regions = response;
		  		  });
			} else {
				vm.regions = {};
			}
	  	};
	  	
	  	/**
	  	 * @param		 			countryId
	  	 * @return 					district list
	  	 * @use 					geographicService,
	  	 */	  	 
		vm.getDistrictByCountry = function (countryId){
			if ( !angular.isUndefined(countryId)){
				geographicService.getDistrictByCountry(countryId).then(function(response){
		  			  logger.debug('cartController getDistrictByCountry(countryId): ramo then');
		  			  vm.districts = response;
		  		  });
			} else {
				vm.districts = {};
			}
	  	};
  		
}
})();
