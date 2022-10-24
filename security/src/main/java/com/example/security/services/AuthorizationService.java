package com.example.security.services;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationService {
	
	final static Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
	
	

	@GetMapping(value ={"/","/login"})
	public void index() {
		
		logger.info("[AuthorizationController-login]");
	
	}
		
	
	
	@GetMapping({"/user"})
	public String user(Authentication authentication) {
		
		logger.info("[AuthorizationController-user]");
		
		logger.info("nombre:"+authentication.getName());
		
		logger.info("authorities:"+authentication.getAuthorities());
		
		logger.info("credenciales:"+authentication.getCredentials());
		
		logger.info("details:"+authentication.getDetails());
		
		logger.info("principal:"+authentication.getPrincipal());
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return "user";
	}
	
	@GetMapping({"/username"})
	public String username(HttpServletRequest request) {
		
		logger.info("[AuthorizationController-username]");
		
		logger.info("nombre:"+request.getUserPrincipal().getName());
		return "user";
	}
	
	
}
