package com.matheus.cusomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.cusomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {
	
	@RequestMapping(method = RequestMethod.GET) //Verbos do HTTP
	public List<Categoria> listar() {
		Categoria cad1 = new Categoria(1,"Informática");
		Categoria cad2 = new Categoria(2,"Escritório");
		
		List<Categoria> lista = new ArrayList<>();// Vou criar uma lista de categorias
		lista.add(cad1);
		lista.add(cad2);
		
		return lista;
	}

}
