package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

public interface InventoryRestService {

	Response getInventoryForProduct(String ssid, Long productId);

}
