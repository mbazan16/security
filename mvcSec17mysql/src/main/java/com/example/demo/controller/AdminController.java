package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping
	public String miMetodo() {
		log.info("[miMetodo]");
		return "admin";
	}
	
	
	@PostMapping
	public String miMetodoPost() {
		log.info("[miMetodoPost]");
		return "admin";
	}
}
