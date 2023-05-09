package com.example.demo.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@Profile("!https")
public class SecSecurityConfig {

@Bean
public UserDetailsManager users(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
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
     
     @Bean
     DataSource dataSource() {
     	return new EmbeddedDatabaseBuilder()
     		.setType(EmbeddedDatabaseType.H2)
     		.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
     		.build();
     }
 }
