package com.dicsys.tfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();
			
		return new InMemoryUserDetailsManager(user, admin);
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable())
		        .authorizeHttpRequests(auth -> auth
		        	.requestMatchers( HttpMethod.POST, "/apiV1/**").permitAll()
		        	.requestMatchers( HttpMethod.GET, "/apiV1/**").permitAll()
		        	.requestMatchers( HttpMethod.PUT, "/apiV1/**").permitAll()
		        	.requestMatchers( HttpMethod.DELETE, "/apiV1/**").permitAll()
		            .anyRequest().authenticated());
		
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}

}
