package com.matheus.cusomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.cusomc.domain.Categoria;
import com.matheus.cusomc.repositories.CategoriaRepository;
import com.matheus.cusomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;	
	
	public Categoria find(Integer id) { /* Uma operação que busca uma categoria no repository*/
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); /*Se o objeto não existir gera um erro!*/
		return repo.save(obj);
	}

}
