package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/admin/pagina")
public class UserController {

	@ResponseBody
	@GetMapping
	public String miMetodo() {
		return "Hola soy la pagina del administrador";
	}
}
