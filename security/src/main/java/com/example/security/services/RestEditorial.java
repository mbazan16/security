package com.example.security.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.business.interfaces.Servicio;
import com.example.security.model.Editorial;

@RestController
@RequestMapping("editorial")
public class RestEditorial {
	
	final static Logger logger = LoggerFactory.getLogger(RestEditorial.class);
	
	@Autowired
	@Qualifier("ServEditorial")
	private Servicio<Editorial,Integer> servicio;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Editorial> findAll(){
	      logger.info("RestEditorial[findAll]");
		return servicio.getElements();
	}
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Editorial findOne(@PathVariable int id){
	      logger.info("RestEditorial[findOne]");
	      logger.info("id"+id);
		return servicio.getElement(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@Validated @RequestBody Editorial editorial){
	      logger.info("RestEditorial[save])");
	      logger.info("editorial"+editorial);
	      
		servicio.save(editorial);
	}
	@PutMapping(value = "/{id}/{nuevoNombre}")
	public void save(@PathVariable int id,@PathVariable("nuevoNombre") String nombre){
	      logger.info("RestEditorial[save]");
	      logger.info("id"+id);
	      logger.info("nombre"+nombre);
		Editorial editorial = servicio.getElement(id);
		editorial.setNombre(nombre);
		servicio.save(editorial);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable int id){
	      logger.info("RestEditorial[delete]");
	      logger.info("id"+id);
		servicio.delete(id);
	}

}
