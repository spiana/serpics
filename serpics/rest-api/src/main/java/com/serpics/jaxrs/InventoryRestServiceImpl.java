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
import com.serpics.catalog.facade.data.InventoryData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.warehouse.facade.InventoryFacade;

@Path("/inventory")
@Transactional(readOnly = true)
public class InventoryRestServiceImpl implements InventoryRestService {

	@Autowired
	InventoryFacade inventoryFacade;
	
    /**
     * This method gets inventoryStatus and stockLevel for a product
     * @param ssid The sessionId for the store authentication
     * @summary  Method: getInventoryForProduct
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{productId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.InventoryData>")
	public Response getInventoryForProduct(@HeaderParam(value = "ssid") String ssid, @PathParam("productId") Long productId){
		ApiRestResponse<InventoryData> apiRestResponse = new ApiRestResponse<InventoryData>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(inventoryFacade.getInventoryForProductId(productId));
		return Response.ok(apiRestResponse).build();
	}
}
