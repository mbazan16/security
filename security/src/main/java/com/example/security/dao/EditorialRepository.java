package com.example.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
	Editorial findByNombre(String nombre);

}
