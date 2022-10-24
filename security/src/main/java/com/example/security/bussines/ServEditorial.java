package com.example.security.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.business.interfaces.Servicio;
import com.example.security.dao.EditorialRepository;
import com.example.security.model.Editorial;

@Service("ServEditorial")
public class ServEditorial implements Servicio<Editorial,Integer>{
	
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

}
