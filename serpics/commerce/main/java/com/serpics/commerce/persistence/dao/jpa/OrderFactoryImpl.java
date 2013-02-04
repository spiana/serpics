package com.serpics.commerce.persistence.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.persistence.Suborder;
import com.serpics.commerce.persistence.dao.OrderFactory;
import com.serpics.core.persistence.dao.jpa.AbstractFactory;

public class OrderFactoryImpl extends AbstractFactory implements OrderFactory {

	@Override
	public List<Suborder> fetchSubOrderByuser(Long user_id, Long customer_id,
			Long store_id, Long address_id , String status) {
		StringBuffer q = new StringBuffer();
		q.append("from Suborder o where storeId = :storeId");
		
		if  (customer_id != null)
			q.append(" and customerId= :customer_id");
		if (user_id != null)
			q.append(" and userId = :user_id");
		if (status !=  null)
			q.append(" and status = :status");
		if  (address_id != null)
			q.append(" and shippingAddressId= :address_id");
		
		Query query = getEntityManager().createQuery(q.toString());
		query.setParameter("storeId",store_id);
		if (user_id != null)
			query.setParameter("user_id",user_id);
		if (customer_id != null)
			query.setParameter("customer_id",customer_id);
		if (status != null)
			query.setParameter("status",status);
		if  (address_id != null)
			query.setParameter("address_id",user_id);
		
		@SuppressWarnings("unchecked")
		List<Suborder> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<AbstractOrder> fetchOrderByuser(Long user_id, Long customer_id,
			Long store_id, String status) {
		StringBuffer q = new StringBuffer();
		q.append("from Order o where storeId = :storeId");
		
		if  (customer_id != null)
			q.append(" and customerId= :customer_id");
		if (user_id != null)
			q.append(" and userId = :user_id");
		if (status !=  null)
			q.append(" and status = :status");
		
		Query query = getEntityManager().createQuery(q.toString());
		query.setParameter("storeId",store_id);
		if (user_id != null)
			query.setParameter("user_id",user_id);
		if (customer_id != null)
			query.setParameter("customer_id",customer_id);
		if (status != null)
			query.setParameter("status",status);
		
		@SuppressWarnings("unchecked")
		List<AbstractOrder> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Orderitem> fetchOrderItemByuser(Long user_id, Long customer_id,
			Long store_id, String status) {
		StringBuffer q = new StringBuffer();
		q.append("from Orderitem o where order is null and storeId = :storeId");
		
		if  (customer_id != null)
			q.append(" and customerId= :customer_id");
		if (user_id != null)
			q.append(" and userId = :user_id");
		if (status !=  null)
			q.append(" and status = :status");
		
		Query query = getEntityManager().createQuery(q.toString());
		query.setParameter("storeId",store_id);
		if (user_id != null)
			query.setParameter("user_id",user_id);
		if (customer_id != null)
			query.setParameter("customer_id",customer_id);
		if (status != null)
			query.setParameter("status",status);
		
		@SuppressWarnings("unchecked")
		List<Orderitem> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public AbstractOrder fetchCurrentOrder(Long user_id ,Long customer_id, Long store_id) {
		List<AbstractOrder> orders = this.fetchOrderByuser(user_id, customer_id, store_id, AbstractOrder.PENDING);
		if (orders.size() > 0)
			; //FIXME  throw TooManyPendigOrder
		return  (orders.size() == 1 ? orders.get(0): null);
	}
	@Override
	public List<Suborder> fetchCurrentSubOrder(Long user_id ,Long customer_id, Long store_id) {
		return this.fetchSubOrderByuser(user_id, customer_id, store_id,null, AbstractOrder.PENDING);
		
	}


	@Override
	public List<Orderitem> fetchCurrentOrderItem(Long user_id ,Long customer_id, Long store_id) {
		return this.fetchOrderItemByuser(user_id, customer_id, store_id, AbstractOrder.PENDING);
	}

}
