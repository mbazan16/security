package com.example.mvc.config;

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

import com.example.mvc.controller.EditorialController;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	DataSource datasource;
	
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	
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
		
		
		//String userQuery = "SELECT email,password,enabled FROM users where email = ?";
		//String authoritiesQuery="SELECT email,authority FROM authorities where email = ?";
		auth
		.jdbcAuthentication()
		.dataSource(datasource)
		//.usersByUsernameQuery(userQuery)
	    //.authoritiesByUsernameQuery(authoritiesQuery)
		.passwordEncoder(passwordEncoder());

		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**").permitAll()  
		.antMatchers("/h2-console/**").hasAuthority("ROLE_ADMIN")  //.hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
        .formLogin()
        .permitAll()
        .loginPage("/login")
        .defaultSuccessUrl("/libros",true)
        //.failureHandler(customAuthenticationFailureHandler)
        .failureUrl("/login?error=true")
        .usernameParameter("username")
        //.usernameParameter("email")
        .passwordParameter("password") 
        .and()
        .logout()
        .deleteCookies("JSESSIONID")
		.and()
		.headers().frameOptions().disable()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/accessDenied")
		.accessDeniedHandler(customAccessDeniedHandler);

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
