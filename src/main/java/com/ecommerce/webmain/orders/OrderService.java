package com.ecommerce.webmain.orders;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.webmain.ExceptionClass;
import com.ecommerce.webmain.cart.Carts;
import com.ecommerce.webmain.customer.CustomerRepository;
import com.ecommerce.webmain.customer.Customers;
import com.ecommerce.webmain.products.Product;
import com.ecommerce.webmain.products.ProductRepository;


@Service
public class OrderService {
    @Autowired
    private OrderRepository or;
    
    @Autowired
    private CustomerRepository cr;
    
    @Autowired
    private ProductRepository pr;
    
    public CustomerOrder addOrders(Long customerId, String name, String mobile, String address, String payment) {
        
        Customers c = cr.findById(customerId).orElseThrow(() -> new ExceptionClass("Customer not found"));
        c.setName(name);
        c.setMobile(mobile);
        c.setAddress(address);
        cr.save(c);
        
        List<Carts> cartItems = c.getCartItems();
        CustomerOrder o = new CustomerOrder();
        o.setAddress(address);
        o.setMobile(mobile);
        o.setPayment(payment);
        o.setCustomer(customerId);
        o.setName(name);
        o.setEmail(c.getEmail());
        
        List<Long> products = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        List<Double> price = new ArrayList<>();
        double total = 0;
        for(Carts cart: cartItems) {
            Product p = pr.findById(cart.getProduct()).orElseThrow(() -> new ExceptionClass("Product not found"));
            products.add(p.getId());
            quantity.add(cart.getQuantity());
            price.add(p.getPrice());
            p.setQuantity(p.getQuantity() - cart.getQuantity());
            pr.save(p);
            total += cart.getQuantity() * p.getPrice();
        }
        o.setTotalprice(total);
        o.setPrice(price);
        o.setQuantity(quantity);
        o.setProducts(products);
        return or.save(o);
    }
 
    public CustomerOrder getOrderById(Long id) {
    	return or.findById(id).orElseThrow(() -> new ExceptionClass("Order not found"));
    }
 
    public List<UserDTO> getOrderByCustomerId(Long id) {
    	List<CustomerOrder> cos = or.getOrderByCustomer(id);
    	List<UserDTO> orlist = new ArrayList<UserDTO>();
    	for(CustomerOrder co:cos) {
    		List<Prod> plist = new ArrayList<Prod>();
    		for(int i=0; i<co.getProducts().size(); i++) {
    			Product p = pr.findById(co.getProducts().get(i)).orElseThrow(() -> new ExceptionClass("Product not found"));
    			plist.add(new Prod(Base64.getEncoder().encodeToString(p.getImage()), p.getName(), p.getPrice(), co.getQuantity().get(i)));
    		}
    		orlist.add(new UserDTO(co.getOrder_id(), plist));
    	}
        return orlist;
    }
}
