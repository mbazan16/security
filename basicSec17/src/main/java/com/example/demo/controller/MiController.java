package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pagina")
public class MiController {

	@ResponseBody
	@GetMapping
	public String miMetodo() {
		return "Hola";
	}
}
