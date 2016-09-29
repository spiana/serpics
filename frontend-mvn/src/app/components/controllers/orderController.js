(function(){
	angular.module('order.controller', [ ])

/** orderController **/
.controller('OrderController',orderController);
	
	orderController.$inject = ['$scope','orderService','logger','$stateParams','cartService'];
                    
	/** @ngInject */
	function orderController($scope,orderService,logger,$stateParams,cartService) {	
   	
		var vm = this;
//	    vm.order = {};
	    placeOrder();

  	    

  	    
  	    function placeOrder() {
  	    	
  	    	logger.debug('$stateParams:   '+$stateParams);
//  	    {'paymentId':'PAY-99K50069UF479600WK3HP111','token':'EC-8EL93027C49180111','PayerID':'SHB26LER8P111'}
  	    	if ($stateParams.paymentId != null  && $stateParams.PayerID != null){
  	    		var paidData = {
  	    				paymentIdentifier: $stateParams.paymentId,
  	    				token: $stateParams.token,
  	    				payerId: $stateParams.PayerID
  	    		};
  	    		logger.debug('OrderController: paidData:'+paidData);
  	    		//removed response in function()
  	    		cartService.addPaymentInfo(paidData).then( function(  ) {
  	  	    		logger.debug('OrderController: addPaymentInfo(paidData): paidData:'+paidData);
  	  	    		orderService.placeOrder().then( function( response){
  	  	    			logger.debug('orderController after addPaymentInfo: placeOrder()');
  	  	    			vm.order = response;
  	  	    			});
  	    		});
  	    	}else{
  	  	    	orderService.placeOrder().then( function( response) {
  	  	    		logger.debug('orderController: placeOrder() without stateParams');
  	  	    		vm.order = response;
  	  	    	});
  	    	}
  	    }
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param order 				add payment for @param order
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						orderService
  	     */
  	    vm.addPayment = function(orderId, paymentData) {		
  	    	orderService.addPayment(orderId, paymentData).then( function( response ) {
  	    		logger.debug('OrderController: addPayment(orderId, paymentData): ramo then');
  	    		vm.order = response;
  	    	});
  	    };
  	    
  	    
  	    
}
})();