package com.ecommerce.webmain.cart;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.webmain.ExceptionClass;
import com.ecommerce.webmain.customer.CustomerRepository;
import com.ecommerce.webmain.customer.Customers;
import com.ecommerce.webmain.products.Product;
import com.ecommerce.webmain.products.ProductRepository;


@Service
public class CartService {
	@Autowired
	private CartRepository cr;
	
	@Autowired
	private ProductRepository pr;
	
	@Autowired
	private CustomerRepository ctr;
	
	public Carts getCartById(Long cartId) {
		return cr.findById(cartId).orElseThrow(() -> new ExceptionClass("Cart is not found."));
	}
	
	public void addProductToCart(Long customerId, Long productId) {
		Customers customer = ctr.findById(customerId).orElseThrow(() -> new ExceptionClass("Customer not found"));

		Carts cartItem = cr.findByCustomerAndProduct(customer, productId);

		if (cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		} else {
			cartItem = new Carts();
			cartItem.setCustomer(customer);
			cartItem.setProduct(productId);
			cartItem.setQuantity(1);
		}
		
		cr.save(cartItem);
	}
	
	public void clearCarts(Long customerId) {
		List<Carts> carts = cr.findByCustomerId(customerId);
		cr.deleteAll(carts);
	}
	
	public void removeProductFromCart(Long customerId, Long productId) {
		Customers customer = ctr.findById(customerId).orElseThrow(() -> new ExceptionClass("Customer not found"));
		Carts cartItem = cr.findByCustomerAndProduct(customer, productId);

		if (cartItem != null) {
			if (cartItem.getQuantity() > 1) {
				cartItem.setQuantity(cartItem.getQuantity() - 1);
				cr.save(cartItem);
			} else {
				cr.delete(cartItem);
			}
		} else {
			throw new RuntimeException("Product is not in the cart");
		}
	}
	
	public int getCartProductCount(Long customerId) {
		List<Carts> cartItems = cr.findByCustomerId(customerId);

		int totalQuantity = cartItems.stream()
				.mapToInt(Carts::getQuantity)
				.sum();

		return totalQuantity;
	}

	public List<CartDTO> getAllCartProducts(Long customerId) {
		List<CartDTO> carts = new ArrayList<CartDTO>();
		for(Carts cart: cr.findByCustomerId(customerId)) {
			Product p = pr.findById(cart.getProduct()).orElseThrow(() -> new ExceptionClass("Product not found"));
			CartDTO c = new CartDTO(cart.getId(), p.getId(), Base64.getEncoder().encodeToString(p.getImage()), p.getName(), cart.getQuantity(), p.getPrice());
			carts.add(c);
		}
		return carts;
	}
	
}
