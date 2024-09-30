package com.ecommerce.webmain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration("webSecurityConfig")
public class SecurityConfig {
	
	@Bean("webPasswordEncoder")
	@Primary
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean("webSecurityFilterChain") 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for testing (enable in production)
            .authorizeRequests()
            .requestMatchers("/**").permitAll()  // Allow access to register and login endpoints
            .anyRequest().authenticated();  // All other requests require authentication

        return http.build();
    }
}

