package com.ecommerce.webmain.products;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductServices ps;
	
	@GetMapping
	public List<Product> getAllProduct() {
		return ps.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductByID(@PathVariable Long id) {
		Product t = ps.getProductByID(id);
		return ResponseEntity.ok(t);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		ps.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@PathVariable @RequestParam("name") String name,
            @RequestParam("quantity") int quantity,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile imageFile) {
		Product p = new Product();
		p.setName(name);
        p.setQuantity(quantity);
        p.setDescription(description);
        p.setPrice(price);

        try {
        	ps.addProduct(p, imageFile);
			return ResponseEntity.ok(p);
		} catch (IOException e) {
			return ResponseEntity.status(500).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateProduct(@RequestBody Product p, @PathVariable Long id) {
		ps.updateProduct(p, id);
		return ResponseEntity.noContent().build();
	}
}
