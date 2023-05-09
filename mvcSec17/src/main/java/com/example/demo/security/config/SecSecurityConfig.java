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
         .password(passwordEncoder().encode("user1"))
         .roles("USER")
         .build();
     UserDetails user2 = User.withUsername("user2")
         .password(passwordEncoder().encode("user2"))
         .roles("USER")
         .build();
     UserDetails admin = User.withUsername("admin")
         .password(passwordEncoder().encode("admin"))
         .roles("ADMIN")
         .build();
     return new InMemoryUserDetailsManager(user1, user2, admin);
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
         .authorizeHttpRequests((authz) -> authz
             .requestMatchers("/css/**").permitAll()
             .requestMatchers("/js/**").permitAll()
             .requestMatchers("/img/**").permitAll()
             .requestMatchers("/h2-console/**").hasRole("ADMIN")
             .anyRequest().authenticated()
         )
         .formLogin((form) -> form
        		 .loginPage("/login")
 				//.loginProcessingUrl("/login")
        	     .failureUrl("/login?error=true")
 				.successForwardUrl("/user")
 				.permitAll()
 			)
        .exceptionHandling()
        .accessDeniedPage("/accessDenied")
        .and()
 		.logout((logout) -> logout.permitAll());
     return http.build();
     
   /*  http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**").permitAll()  
		.antMatchers("/h2-console/**").hasRole("ADMIN")
	//	.antMatchers(HttpMethod.POST,"/libros").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
     .formLogin()
     .loginPage("/login")
     .permitAll()
     .defaultSuccessUrl("/libros",true)
     .failureUrl("/login?error=true")
     .usernameParameter("user")
     .passwordParameter("pass")
     .and()
     .logout()
     .deleteCookies("JSESSIONID")        
		.and()
		.headers().frameOptions().disable()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/accessDenied")
		.accessDeniedHandler(customAccessDeniedHandler);*/
 }

     
     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 }
