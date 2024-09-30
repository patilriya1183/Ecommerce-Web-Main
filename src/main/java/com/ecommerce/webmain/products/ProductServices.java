package com.ecommerce.webmain.products;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.webmain.ExceptionClass;


@Service
public class ProductServices {
	@Autowired
	private ProductRepository pr;
	
	public List<Product> getAllProducts() {
		return pr.findAll();
	}
	
	public Product getProductByID(Long id) {
		return pr.findById(id).orElseThrow(() -> new ExceptionClass("Product not found by id = " + id));
	}

	public void updateProduct(Product p, Long id) {
		Product pi = getProductByID(id);
		pi.setName(p.getName());
		pi.setQuantity(p.getQuantity());
		pi.setDescription(p.getDescription());
		pi.setPrice(p.getPrice());
		pi.setImage(p.getImage());
		pr.save(pi);
	}
	
	public Product addProduct(Product p, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
            p.setImage(file.getBytes());
        }
        return pr.save(p);
	}
	
	public void deleteProduct(Long id) {
		Product p = getProductByID(id);
		pr.delete(p);
	}
}
