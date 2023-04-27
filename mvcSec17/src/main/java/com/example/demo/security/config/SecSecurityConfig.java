package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@Profile("!https")
public class SecSecurityConfig {

 @Bean
 public InMemoryUserDetailsManager userDetailsService() {
     UserDetails user1 = User.withUsername("user1")
         .password(passwordEncoder().encode("user1Pass"))
         .roles("USER")
         .build();
     UserDetails user2 = User.withUsername("user2")
         .password(passwordEncoder().encode("user2Pass"))
         .roles("USER")
         .build();
     UserDetails admin = User.withUsername("admin")
         .password(passwordEncoder().encode("adminPass"))
         .roles("ADMIN")
         .build();
     return new InMemoryUserDetailsManager(user1, user2, admin);
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
         .authorizeHttpRequests((authz) -> authz
             .requestMatchers("/api/admin/**").hasRole("ADMIN")
             .requestMatchers("/api/user/**").hasRole("USER")
             .anyRequest().authenticated()
         )
         .formLogin((form) -> form
 				.loginPage("/login")
 				.permitAll()
 			)
 		.logout((logout) -> logout.permitAll());
     return http.build();
 }

     
     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 }
