package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

public interface GeographicRestService {

	public Response getCountryList();

	public Response getRegionByCountry(Long countryId);

}
