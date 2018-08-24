package com.matheus.cusomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.cusomc.domain.Cliente;
import com.matheus.cusomc.repositories.ClienteRepository;
import com.matheus.cusomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;	
	
	public Cliente buscar(Integer id) { /* Uma operação que busca uma categoria no repository*/
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo " + Cliente.class.getName()));
	}

}
