package com.serpics.commerce.data.repositories;

import com.serpics.commerce.data.model.Order;
import com.serpics.core.data.Repository;

public interface OrderRepository extends Repository<Order, Long>, OrderRepositoryCustom {

}
