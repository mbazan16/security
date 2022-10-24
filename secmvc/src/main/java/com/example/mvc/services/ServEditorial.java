package com.example.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc.dao.EditorialRepository;
import com.example.mvc.model.Editorial;
import com.example.mvc.services.interfaces.IServEditorial;

@Service("ServEditorial")
public class ServEditorial implements IServEditorial{
	
	@Autowired
	EditorialRepository repositorio;
	
	

	@Override
	public List<Editorial> getElements() {
		return repositorio.findAll();
	}

	@Override
	public Editorial getElement(Integer id) {
		
		return repositorio.findById(id).get();
	}

	@Override
	public void save(Editorial element) {
		repositorio.save(element);
		
	}

	@Override
	public void delete(Integer id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public Editorial getByNombre(String nombre) {
		
		return repositorio.findByNombre(nombre);
	}

	@Override
	public List<Editorial> getElementsOrder() {
		
		return repositorio.findAllByOrderByNombreAsc();
	}

	

}
