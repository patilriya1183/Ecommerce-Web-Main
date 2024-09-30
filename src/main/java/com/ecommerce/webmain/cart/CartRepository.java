package com.ecommerce.webmain.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.webmain.customer.Customers;


@Repository
public interface CartRepository extends JpaRepository<Carts, Long> {
	List<Carts> findByCustomerId(Long customerId);
	Carts findByCustomerAndProduct(Customers customer, Long productId);
}
