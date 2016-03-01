package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

public interface GeographicRestService {

	public Response getCountryList(String ssid);

	public Response getRegionByCountry(Long countryId, String ssid);
	
	public Response getDistrictByCountry(Long countryId, String ssid);
	
	public Response getDistrictByRegion(Long regionId, String ssid);

}
