package com.example.mvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.mvc.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
	
	List<Libro> findByTituloContaining(String titulo);
	
	List<Libro> findByAutor(String autor);	
		
	@Modifying @Transactional
	@Query("update Libro l set l.autor = ?1 where l.id = ?2")
	void setAutorById(String autor, int id);
	
	@Modifying @Transactional
	@Query("update Libro l set l.titulo = ?1 where l.id = ?2")
	void setTituloById(String titulo, int id);
	
	
}
