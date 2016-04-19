(function(){
	'use strict';
	angular.module('geographic.service',[])
	
	.service('geographicService', geographicService);
	
	geographicService.$inject = ['$http', '$q','sessionService','COOKIE_EXPIRES','$cookies','URL','$log'];
			
	/** @ngInject */
	function geographicService( $http, $q,sessionService,COOKIE_EXPIRES,$cookies,URL,$log ) {
	
	
	var endpoint = '/api/v1/geographic/';
	
    var service =({
    	getCountryList		:	getCountryList,
    	getRegionByCountry	:	getRegionByCountry,
    	getDistrictByCountry:	getDistrictByCountry,
    	getDistrictByRegion :	getDistrictByRegion
    });
    return service;
	
	/**
	 *@return RegionList
	 *
	 */
	function getRegionByCountry(countryId) {
		var serviceSSID = sessionService;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug('session Id nel promise'+sessionId) ;
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
		var serviceSSID = sessionService;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug('session Id nel promise'+sessionId) ;
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
	 *@return DistrictList
	 *
	 */
	function getDistrictByCountry(countryId) {
		var serviceSSID = sessionService;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug('session Id nel promise'+sessionId) ;
				$http({
					method: 	'GET',
					url: URL + endpoint +  'district/country/' + countryId,
					headers: {
						'ssid': sessionId
						}
				}).then(handleSuccess, handleError).then(resolve, reject);
			});
		});
	}
	
	/**
	 *@return DistrictList
	 *
	 */
	function getDistrictByRegion(regionId) {
		var serviceSSID = sessionService;
		return $q(function(resolve, reject) {
			serviceSSID.getSessionId().then(function(sessionId){
				$log.debug('session Id nel promise'+sessionId) ;
				$http({
					method: 	'GET',
					url: URL + endpoint +  'district/region/' + regionId,
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
            return( $q.reject( 'GeographicService: An unknown error occurred.' ) );
        }
        /** Otherwise, use expected error message.**/
        return( $q.reject( response.data.message ) );
    }
    
    /** 
     * I transform the successful response, unwrapping the application data
     *from the API response payload.                
     */
    function handleSuccess( response ) {
    	var serviceSSID = sessionService;
    	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES);  /** expire 20 minut **/ 
        return(response.data.responseObject);
    }
	
}
})();