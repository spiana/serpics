package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

public interface GeographicRestService {

	public Response getCountryList(String ssid);

	public Response getRegionByCountry(Long countryId, String ssid);

}
