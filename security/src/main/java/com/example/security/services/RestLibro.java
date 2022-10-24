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
import com.example.security.model.Libro;
import com.example.security.model.Libro;

@RestController
@RequestMapping("libro")
public class RestLibro {
	
final static Logger logger = LoggerFactory.getLogger(RestLibro.class);
	
	@Autowired
	@Qualifier("ServLibro")
	private Servicio<Libro,Integer> servicio;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Libro> findAll(){
	      logger.info("RestLibro[findAll]");
		return servicio.getElements();
	}
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Libro findOne(@PathVariable int id){
	      logger.info("RestLibro[findOne]");
	      logger.info("id"+id);
		return servicio.getElement(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@Validated @RequestBody Libro libro){
	      logger.info("RestLibro[save])");
	      logger.info("libro"+libro);
	      
		servicio.save(libro);
	}
	@PutMapping(value = "/{id}/{autor}")
	public void save(@PathVariable int id,@PathVariable String autor){
	      logger.info("RestLibro[save]");
	      logger.info("id"+id);
	      logger.info("autor"+autor);
		Libro libro = servicio.getElement(id);
		libro.setAutor(autor);
		servicio.save(libro);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable int id){
	      logger.info("RestLibro[delete]");
	      logger.info("id"+id);
		servicio.delete(id);
	}

}
