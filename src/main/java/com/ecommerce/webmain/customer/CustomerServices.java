package com.ecommerce.webmain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.webmain.ExceptionClass;


@Service
public class CustomerServices {
	@Autowired
	private CustomerRepository cr;
	
	@Autowired
    @Qualifier("customerPasswordEncoder")
	private PasswordEncoder pe;
	
	public Customers registerCustomer(Customers customer) {
		customer.setPassword(pe.encode(customer.getPassword()));
		return cr.save(customer);
	}
	
	public void updateCustomer(Customers customer, Long id) {
		Customers c = cr.findById(id).orElseThrow(() -> new ExceptionClass("Customer not found"));
		c.setAddress(customer.getAddress());
		c.setCartItems(customer.getCartItems());
		c.setMobile(customer.getMobile());
		c.setName(customer.getName());
		cr.save(c);
	}
	
	public Customers getCustomerByEmail(String email) {
		return cr.findByEmail(email);
	}
	
	public CustomerDTO getCustomerById(Long id) {
		Customers c = cr.findById(id).orElseThrow(() -> new ExceptionClass("Customer not found"));
		return new CustomerDTO(c.getId(), c.getName(), c.getEmail(), c.getAddress(), c.getMobile());
	}
}
