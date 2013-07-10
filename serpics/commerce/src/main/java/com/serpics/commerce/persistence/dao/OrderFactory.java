package com.serpics.commerce.persistence.dao;


import java.util.List;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.persistence.Suborder;
import com.serpics.core.persistence.dao.BaseFactory;



public interface OrderFactory extends BaseFactory {

	
	public List<Suborder> fetchSubOrderByuser(Long user_id , Long customer_id, Long store_id ,Long address_id, String status);
	public List<AbstractOrder> fetchOrderByuser(Long user_id , Long customer_id, Long store_id , String status);
	public List<Orderitem> fetchOrderItemByuser(Long user_id ,Long customer_id, Long store_id , String status);
	public AbstractOrder fetchCurrentOrder(Long user_id,Long customer_id , Long store_id);
	public List<Suborder> fetchCurrentSubOrder(Long user_id,Long customer_id , Long store_id);
	public List<Orderitem> fetchCurrentOrderItem(Long user_id, Long customer_id , Long store_id);

}
