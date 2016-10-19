/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.DistrictFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

@Path("/geographic")
@Transactional(readOnly = true)
public class GeographicRestServiceImpl implements GeographicRestService{
	
	@Autowired
	CountryFacade countryFacade;
	
	@Autowired
	RegionFacade regionFacade;
	
	@Autowired
	DistrictFacade districtFacade;
	
    /**
     * This method gets all countries
     * @param ssid The sessionId for the store authentication
     * @summary  Method: getCountryList()
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/country")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.base.facade.data.CountryData>>")
	public Response getCountryList(@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<List<CountryData>> apiRestResponse = new ApiRestResponse<List<CountryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(countryFacade.findAllList());
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets all regions by countryId
     * @summary  Method: getRegionByCountry(Long countryId)
     * @param ssid The sessionId for the store authentication
     * @param countryId The country id to search for regions
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/region/{countryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.base.facade.data.RegionData>>")
	public Response getRegionByCountry(@PathParam("countryId") Long countryId,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<List<RegionData>> apiRestResponse = new ApiRestResponse<List<RegionData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		List<RegionData> regions = new ArrayList<RegionData>();
		if (countryId != null)
			regions = regionFacade.findRegionByCountry(countryId);
		
		apiRestResponse.setResponseObject(regions);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets all districts by countryId
     * @summary  Method: getDistrictByCountry(Long countryId)
     * @param ssid The sessionId for the store authentication
     * @param countryId The country id to search for districts
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/district/country/{countryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.base.facade.data.DistrictData>>")
	public Response getDistrictByCountry(@PathParam("countryId") Long countryId,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<List<DistrictData>> apiRestResponse = new ApiRestResponse<List<DistrictData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(districtFacade.findDistrictByCountry(countryId));
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets all regions by countryId
     * @summary  Method: getDistrictByRegion(Long countryId)
     * @param ssid The sessionId for the store authentication
     * @param regionId The region id to search for districts
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/district/region/{regionId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.base.facade.data.DistrictData>>")
	public Response getDistrictByRegion(@PathParam("regionId") Long regionId,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<List<DistrictData>> apiRestResponse = new ApiRestResponse<List<DistrictData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(districtFacade.findDistrictByRegion(regionId));
		return Response.ok(apiRestResponse).build();
	}

}
