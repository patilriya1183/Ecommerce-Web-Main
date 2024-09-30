package com.ecommerce.webmain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerServices cs;
	
	@Autowired
    private Security security;

	@Autowired
    @Qualifier("customerPasswordEncoder") 
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
          
            boolean isValid = security.isTokenExpired(token);
            if (isValid) {
                return ResponseEntity.ok().body("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
    	return ResponseEntity.ok(cs.getCustomerById(id));
    }

	@PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody Customers customer) {
		if(cs.getCustomerByEmail(customer.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
		}
        return ResponseEntity.ok(cs.registerCustomer(customer));
    }
	
	@PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customers customer) {
		Customers custom = cs.getCustomerByEmail(customer.getEmail());
		if(custom == null || !passwordEncoder.matches(customer.getPassword(), custom.getPassword())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid email and password");
		}
		return ResponseEntity.ok(security.generateToken(custom));
    }
}