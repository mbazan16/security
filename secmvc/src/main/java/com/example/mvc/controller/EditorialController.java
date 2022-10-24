package com.example.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mvc.model.Editorial;
import com.example.mvc.services.interfaces.IServEditorial;


@Controller
public class EditorialController {
	
	final static Logger logger = LoggerFactory.getLogger(EditorialController.class);
	
	@Autowired
	private IServEditorial servicio;
	
	@RequestMapping("/nuevaEditorial")
	public String insertar(String nombre, String telefono, String email,
						   Model model) {
		logger.info("[EditorialController-insertar] nombre:"+ nombre+" telefono:"+ telefono+" email:"+email);
		
		Editorial editorial = new Editorial(nombre, telefono, email);
		servicio.save(editorial);

		return "nuevaEditorial";
	}
	
	
}
