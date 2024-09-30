package com.ecommerce.webmain.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
	public List<CustomerOrder> getOrderByCustomer(Long customerId);
}

