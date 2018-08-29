package com.matheus.cusomc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.cusomc.domain.Pedido;

@Repository // Anotação para acesso ao BD transformando a classe em uma interface
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}
