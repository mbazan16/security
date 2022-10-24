package com.example.security.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	DataSource datasource;
	
	@Autowired
	MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;
	
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Bean
	public AuthenticationManager authMg() throws Exception {
		return super.authenticationManagerBean();
	}

	// definición roles y usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		logger.info("[SecurityConfig-configure]");
				
		
		auth
		.jdbcAuthentication()
		.dataSource(datasource)
		.passwordEncoder(passwordEncoder());

		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**").permitAll()  
		.antMatchers("/h2-console/**").hasRole("ADMIN")//.permitAll()  //
		.anyRequest().authenticated()
		.and()
        .formLogin()
        .permitAll()
         .defaultSuccessUrl("/libro",true)
        .failureHandler(customAuthenticationFailureHandler)
        .and()
		.logout()
		.deleteCookies("JSESSIONID")
		.logoutSuccessHandler(customLogoutSuccessHandler)
		.and()
		.headers().frameOptions().disable()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/accessDenied")
		.accessDeniedHandler(customAccessDeniedHandler)
		.and()
		.httpBasic()
		.authenticationEntryPoint(myBasicAuthenticationEntryPoint);

	}
	
	
	
	    

	 @Bean

	    public BCryptPasswordEncoder passwordEncoder() {

		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

	//El numero 4 representa que tan fuerte quieres la encriptacion.

	//Se puede en un rango entre 4 y 31. 

	//Si no pones un numero el programa utilizara uno aleatoriamente cada vez

	//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien

	        return bCryptPasswordEncoder;

	    }

}
