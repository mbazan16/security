package com.example.mvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.mvc.model.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
	
	Editorial findByNombre(String nombre);
	List<Editorial> findAllByOrderByNombreAsc();
		

}
