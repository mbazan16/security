package com.example.mvc.services.interfaces;

import java.util.List;

import com.example.mvc.model.Libro;

public interface IServLibro extends Servicio<Libro,Integer>{
	
	List<Libro> getElementsByTitulo(String titulo);
	List<Libro> getElementsByAutor(String autor);	
		
	void modificarAutorById(String autor, int id);
	
	void modificarTituloById(String titulo, int id);
	
}
