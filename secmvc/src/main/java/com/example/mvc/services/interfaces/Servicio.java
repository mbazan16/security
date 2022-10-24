package com.example.mvc.services.interfaces;

import java.util.List;

public interface Servicio<A,K> {
	
	public List<A> getElements();
	public A getElement(K id);
	public void save(A element);
	public void delete(K id);
	

}
