app.controller("authController", function($scope,authManagerService) {
	
	 	
    $scope.getSessionId = function(  ) {
        // Rather than doing anything clever on the client-side, I'm just
        // going to reload the remote data.
    	authManagerService.getSessionId()
            .then( function( response ) {
            	console.log("received ssid from the service: " + response)
            })
        ;
    };
       
})
 