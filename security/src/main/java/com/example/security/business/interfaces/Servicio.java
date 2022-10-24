package com.example.security.business.interfaces;

import java.util.List;

public interface Servicio<T,K> {
	
	public List<T> getElements();
	public T getElement(K id);
	public void save(T element);
	public void delete(K id);
	

}
