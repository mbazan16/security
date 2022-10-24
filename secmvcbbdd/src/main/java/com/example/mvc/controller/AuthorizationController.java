package com.example.mvc.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {
	
	final static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	
	

	@GetMapping({"/","/login"})
	public String index(Model model) {
		
		logger.info("[AuthorizationController-login]");
		
		return "index";
	}
	
	
	
	@GetMapping({"/accesDenied"})
	public String accesDenied(Model model) {
		
		logger.info("[AuthorizationController-accesDenied]");

		return "accesDenied";
	}
	
	@GetMapping({"/user"})
	public String user(HttpServletRequest request,Authentication authentication, Principal principal) {
		
		logger.info("[AuthorizationController-user]");
		
		logger.info("request:name:"+request.getUserPrincipal().getName());
		
		logger.info("authentication:name:"+authentication.getName());
		
		logger.info("authentication:authorities:"+authentication.getAuthorities());
		
		logger.info("authentication:credenciales:"+authentication.getCredentials());
		
		logger.info("authentication:details:"+authentication.getDetails());
		
		logger.info("authentication:principal:"+authentication.getPrincipal());
		
		logger.info("principal:principal:"+principal);
		
		logger.info("principal:name:"+principal.getName());
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return "user";
	}
	
	
}
