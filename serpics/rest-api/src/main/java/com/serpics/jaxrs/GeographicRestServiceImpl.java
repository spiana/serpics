package com.serpics.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

@Path("/geographicService")
@Transactional(readOnly = true)
public class GeographicRestServiceImpl implements GeographicRestService{
	
	@Autowired
	CountryFacade countryFacade;
	
	@Autowired
	RegionFacade regionFacade;
	
    /**
     * This method gets all countries
     * @summary  Method: getCountryList()
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/country")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CountryData>")
	public Response getCountryList(){
		ApiRestResponse<List<CountryData>> apiRestResponse = new ApiRestResponse<List<CountryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(countryFacade.findAllList());
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets all region by country
     * @summary  Method: getRegionByCountry(Long countryId)
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/region/{country}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.RegionData>")
	public Response getRegionByCountry(@PathParam("country") Long countryId){
		ApiRestResponse<List<RegionData>> apiRestResponse = new ApiRestResponse<List<RegionData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(regionFacade.findRegionByCountry(countryId));
		return Response.ok(apiRestResponse).build();
	}

}
