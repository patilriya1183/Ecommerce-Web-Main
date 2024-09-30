package com.ecommerce.webmain;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ecommerce.webmain.customer.Customers;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("webSecurity")
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
            Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
            return expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
	}
}

