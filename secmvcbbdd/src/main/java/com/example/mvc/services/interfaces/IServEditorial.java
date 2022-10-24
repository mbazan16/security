package com.example.mvc.services.interfaces;

import java.util.List;

import com.example.mvc.model.Editorial;

public interface IServEditorial extends Servicio<Editorial,Integer>{
	

	Editorial getByName(String name);
	List<Editorial> getElementsOrder();
}
