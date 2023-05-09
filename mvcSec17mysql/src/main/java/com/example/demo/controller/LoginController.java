package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String miMetodo() {
		return "login";
	}
	
	// Login form with error
	  @RequestMapping("/loginError")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }
	  
	  @RequestMapping("/accessDenied")
		public String accessDenied() {
			return "accessDenied";
	  }
	  
	  @RequestMapping("/logout")
		public String logout() {
			return "login";
	  }
}
