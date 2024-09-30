package com.ecommerce.webmain.customer;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("customerSecurity")
public class Security {
	private String SECRET_KEY = "keepsmile";
	
	public String generateToken(Customers customer) {
		return Jwts.builder()
				.setSubject(customer.getEmail())
				.claim("customerId", customer.getId())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
	}
	
	public String extractEmail(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean isTokenExpired(String token) {
		try {
	        Jwts.parser()
	            .setSigningKey(SECRET_KEY)
	            .parseClaimsJws(token);
	        return true;
	    } catch (JwtException e) {
	        return false;
	    }
	}
}
