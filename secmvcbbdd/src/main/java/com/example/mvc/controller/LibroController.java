package com.example.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc.model.Editorial;
import com.example.mvc.model.Libro;
import com.example.mvc.services.interfaces.IServEditorial;
import com.example.mvc.services.interfaces.IServLibro;


@Controller
public class LibroController {
	
	final static Logger logger = LoggerFactory.getLogger(LibroController.class);
	
	@Autowired
	private IServEditorial servEditorial;
	
	@Autowired
	private IServLibro servicio;
	
	@RequestMapping("/libros")
	public String index(Model model) {
		
		logger.info("[LibroController-index]");
		
		model.addAttribute("libros", servicio.getElements());

		return "libros";
	}
	
	@RequestMapping("/mostrarPorTitulo")
	public String mostrarPorTitulo(@RequestParam String titulo, Model model) {
		
		logger.info("[LibroController-mostrarPorTitulo] titulo:"+titulo);

		List<Libro> libros = servicio.getElementsByTitulo(titulo);

	    model.addAttribute("libros", libros);
	    
	    logger.info("[LibroController-mostrarPorTitulo] libros:"+libros);

		return "mostrar";
	}
	
	@RequestMapping("/mostrarPorAutor")
	public String mostrarPorAutor(@RequestParam String autor, Model model) {
		
		logger.info("[LibroController-mostrarPorAutor] autor:"+autor);

		List<Libro> libros = servicio.getElementsByAutor(autor);

	    model.addAttribute("libros", libros);

		return "mostrar";
	}		
	
	
	@RequestMapping("/nuevoLibro")
	public String nuevoLibro(Model model) {
		
		logger.info("[LibroController-nuevoLibro]");
		
		List<Editorial> editoriales =servEditorial.getElementsOrder();
		
		model.addAttribute("editoriales", editoriales);
		
		return("nuevoLibro");
	}
	
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam String name, Libro libro, Model model) {
		
		logger.info("[LibroController-insertar] name:"+name+" libro:"+ libro);

		Editorial e = servEditorial.getByName(name);
		libro.setEditorial(e);

		servicio.save(libro);

		return "insertar";
	}
	
	
	@RequestMapping("/modificarLibro/{id}")
	public String modificarLibro(@PathVariable Integer id, Model model){
		
		logger.info("[LibroController-modificarLibro] id:"+id);
		
		Libro libro = servicio.getElement(id);
		
		List<Editorial> editoriales =servEditorial.getElementsOrder();
		
		
		model.addAttribute("libro", libro);
		
		model.addAttribute("editoriales", editoriales);
		
		return "modificar";
	}
	
	@RequestMapping("/modificar")
	public String modificar( Libro libro, Editorial editorial,Model model){
		
		logger.info("[LibroController-modificar] libro:"+ libro +" editorial:"+ editorial);
		
		servicio.modificarTituloById(libro.getTitulo(), libro.getId());
		servicio.modificarAutorById(libro.getAutor(), libro.getId());
		
		libro.setEditorial(editorial);			
		
		servicio.save(libro);
		
		return "modificado";
	}
	
	
}
