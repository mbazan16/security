package com.example.demo.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
	.csrf().disable()
    .authorizeHttpRequests((authz) -> authz
			.requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/img/**").permitAll()
			.requestMatchers("/admin/**").hasAnyRole("ADMIN")
			.requestMatchers("/h2-console/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated())
    		.headers(headers -> headers.frameOptions().sameOrigin())
			.formLogin((form) -> form.loginPage("/login")
					// .loginProcessingUrl("/login")
					.failureUrl("/loginError")
					.successForwardUrl("/user").permitAll())
			.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()
			.logout((logout) -> logout.permitAll());
	return http.build();
     
}
     
     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
     
    
 }
