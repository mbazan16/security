package com.example.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Editorial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String telefono;
	private String email;
	
	
	
	
	
	public Editorial()
	{
		
	}
	
	public Editorial(String name, String tel, String correo)
	{
		this.name = name;
		this.telefono = tel;
		this.email = correo;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString() {
		return "Editorial [id=" + id + ", name=" + name + ", telefono=" + telefono + ", email=" + email + "]";
	}

	
	
	
}
