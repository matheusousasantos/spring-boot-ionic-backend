package com.matheus.cusomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.cusomc.domain.Pedido;
import com.matheus.cusomc.repositories.PedidoRepository;
import com.matheus.cusomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;	
	
	public Pedido buscar(Integer id) { /* Uma operação que busca uma categoria no repository*/
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo " + Pedido.class.getName()));
	}

}
