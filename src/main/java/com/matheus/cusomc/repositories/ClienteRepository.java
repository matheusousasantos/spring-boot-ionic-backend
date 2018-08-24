package com.matheus.cusomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.cusomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
