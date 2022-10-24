package com.example.security.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.security.business.interfaces.Servicio;
import com.example.security.dao.LibroRepository;
import com.example.security.model.Libro;

@Service("ServLibro")
public class ServLibro implements Servicio<Libro,Integer>{
	
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

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void save(Libro element) {
		repositorio.save(element);
		
	}

	@Override
	public void delete(Integer id) {
		repositorio.deleteById(id);
		
	}

}
