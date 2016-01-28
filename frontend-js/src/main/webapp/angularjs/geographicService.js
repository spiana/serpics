var app = angular.module('geographic.service',['serpics.config','serpics.services'])

app.service("geographicService", function( $http, $q,serpicsServices,URL,$log ) {
	
	
	var endpoint = '/jax-rs/geographicService/';
	
    var service =({
    	getCountryList		:	getCountryList,
    	getRegionByCountry	:	getRegionByCountry
    });
    return service;
	
	/**
	 *@return CountryList
	 *
	 */
	function getRegionByCountry(countryId) {
		var serviceSSID = serpicsServices;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug("session Id nel promise"+sessionId) ;
				$http({
					method: 	'GET',
					url: URL + endpoint +  'region/' + countryId,
					headers: {
						'ssid': sessionId
						}
				}).then(handleSuccess, handleError).then(resolve, reject);
			});
		});
	}
	
	/**
	 *@return CountryList
	 *
	 */
	function getCountryList() {
		var serviceSSID = serpicsServices;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug("session Id nel promise"+sessionId) ;
				$http({
					method: 	'GET',
					url: URL + endpoint +  'country',
					headers: {
						'ssid': sessionId
						}
				}).then(handleSuccess, handleError).then(resolve, reject);
			});
		});
	}
	
	
    
    
    /**
     * private method.
     * I transform the error response, unwrapping the application dta from
     * the API response payload.
     */                
    function handleError( response ) {
        /**
         * The API response from the server should be returned in a
         * nomralized format. However, if the request was not handled by the
         * server (or what not handles properly - ex. server error), then we
         * may have to normalize it on our end, as best we can.
         */ 
        if (! angular.isObject( response.data ) || ! response.data.message ) {
            return( $q.reject( "GeographicService: An unknown error occurred." ) );
        }
        /** Otherwise, use expected error message.**/
        return( $q.reject( response.data.message ) );
    }
    
    /** 
     * I transform the successful response, unwrapping the application data
     *from the API response payload.                
     */
    function handleSuccess( response ) {
    	var serviceSSID = serpicsServices;
    	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES)  /** expire 20 minut **/ 
        return(response.data.responseObject);
    }
	
})