package com.matheus.cusomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.cusomc.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
