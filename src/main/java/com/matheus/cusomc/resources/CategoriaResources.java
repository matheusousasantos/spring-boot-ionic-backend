package com.matheus.cusomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {
	
	@RequestMapping(method = RequestMethod.GET) //Verbos do HTTP
	public String listar() {
		return "REST funcionando";
	}

}
