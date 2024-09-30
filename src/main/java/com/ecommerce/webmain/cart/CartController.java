package com.ecommerce.webmain.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartController {
	@Autowired
	private CartService cs;

	@PostMapping("/add")
    public ResponseEntity<String> addProductToCart(
            @RequestParam Long customerId,
            @RequestParam Long productId) {
        cs.addProductToCart(customerId, productId);
        return ResponseEntity.ok("Product added to cart");
    }
	
	@PostMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(
            @RequestParam Long customerId,
            @RequestParam Long productId) {

        cs.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok("Product removed from cart or quantity decreased");
    }

	@PostMapping("/clear")
    public ResponseEntity<String> clearCarts(@RequestParam Long customerId) {
        cs.clearCarts(customerId);
        return ResponseEntity.ok("Product removed from cart or quantity decreased");
    }
	
	@GetMapping("/count/{customerId}")
    public ResponseEntity<Integer> getCartProductCount(@PathVariable Long customerId) {
        int totalQuantity = cs.getCartProductCount(customerId);
        return ResponseEntity.ok(totalQuantity);
    }
	
	@GetMapping("/products/{customerId}")
    public List<CartDTO> getAllCartProduct(@PathVariable Long customerId) {
        List<CartDTO> carts = cs.getAllCartProducts(customerId);
        return carts;
    }
}
