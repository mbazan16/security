package com.example.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	
	/*
	 * @Bean public AuthenticationManager authMg() throws Exception { return
	 * super.authenticationManagerBean(); }
	 */

	// definición roles y usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
		.withUser("user1")
		.password(passwordEncoder().encode("user1"))// lo de {noop} se pone para no obligar a usar mecanismo de encriptación
		.roles("COMUN")
		.and()
		.withUser("admin")
		.password(passwordEncoder().encode("admin"))
		.roles("ADMIN");

		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
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
