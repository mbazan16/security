package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@Profile("!https")
public class SecSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("user1")).roles("USER")
				.build();
		UserDetails user2 = User.withUsername("user2").password(passwordEncoder().encode("user2")).roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user1, user2, admin);
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

		/*
		 * http .csrf().disable() .authorizeRequests()
		 * .antMatchers("/css/**").permitAll()
		 * .antMatchers("/h2-console/**").hasRole("ADMIN") //
		 * .antMatchers(HttpMethod.POST,"/libros").hasRole("ADMIN")
		 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login")
		 * .permitAll() .defaultSuccessUrl("/libros",true)
		 * .failureUrl("/login?error=true") .usernameParameter("user")
		 * .passwordParameter("pass") .and() .logout() .deleteCookies("JSESSIONID")
		 * .and() .headers().frameOptions().disable() .and() .exceptionHandling()
		 * .accessDeniedPage("/accessDenied")
		 * .accessDeniedHandler(customAccessDeniedHandler);
		 */
	}

	/*@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
