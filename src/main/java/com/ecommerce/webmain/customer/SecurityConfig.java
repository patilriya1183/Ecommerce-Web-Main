package com.ecommerce.webmain.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration("customerSecurityConfig")
public class SecurityConfig {
	
	@Bean("customerPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean("customerSecurityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for testing (enable in production)
            .authorizeRequests()
            .requestMatchers("/**").permitAll()  // Allow access to register and login endpoints
            .anyRequest().authenticated();  // All other requests require authentication

        return http.build();
    }
}

