package com.serpics.commerce.repositories;

import com.serpics.commerce.persistence.Order;
import com.serpics.core.data.Repository;

public interface OrderRepository extends Repository<Order, Long>, OrderRepositoryCustom {

}
