package com.sushmita.binge_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

/**
 * Configuration class for Spring Security.
 * Sets up security configurations for the application.
 *
 * @author Sushmita Budhathoki on 2024-07-30
 */

@Configuration
public class SecurityConfig {

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .csrf(csrf -> csrf.disable())
              .authorizeRequests(authorizeRequests ->
                      authorizeRequests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/content/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user", "/user/authenticate").permitAll()
                        .anyRequest().authenticated()
              );

      return http.build();
   }


   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
