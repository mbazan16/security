package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public String miMetodo() {
		log.info("[miMetodo]");
		return "index";
	}
	
	
	@PostMapping
	public String miMetodoPost() {
		log.info("[miMetodoPost]");
		return "index";
	}
}
