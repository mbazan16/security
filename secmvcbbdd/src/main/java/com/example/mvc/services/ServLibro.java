package com.example.mvc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.mvc.dao.LibroRepository;
import com.example.mvc.model.Libro;
import com.example.mvc.services.interfaces.IServLibro;

@Service("ServLibro")
public class ServLibro implements IServLibro{
	
	final static Logger logger = LoggerFactory.getLogger(ServLibro.class);
	@Autowired
	LibroRepository repositorio;
	
	

	@Override
	public List<Libro> getElements() {
		return repositorio.findAll();
	}

	@Override
	public Libro getElement(Integer id) {
		
		return repositorio.findById(id).get();
	}

	//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@Override
	public void save(Libro element) {
		logger.info("element"+element);
		repositorio.save(element);
		
	}

	@Override
	public void delete(Integer id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public List<Libro> getElementsByTitulo(String titulo) {
		
		return repositorio.findByTituloContaining(titulo);
	}

	@Override
	public List<Libro> getElementsByAutor(String autor) {
		return repositorio.findByAutor(autor);
	}

	@Override
	public void modificarAutorById(String autor, int id) {
		repositorio.setAutorById(autor, id);
		
	}
	@Override
	public void modificarTituloById(String titulo, int id) {
		repositorio.setTituloById(titulo, id);
		
	}

}
