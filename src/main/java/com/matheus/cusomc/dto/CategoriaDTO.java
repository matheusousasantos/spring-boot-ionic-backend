package com.matheus.cusomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheus.cusomc.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório") /*Caso o campo seja vazio aparecerá a mensagem.*/
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres") 
	//Mínimo de 5 e máximo de 80 caracteres e caso não ocorra aparecerá a menssagem.
	private String nome;
	
	public CategoriaDTO() {	
	}
	
	//Cria um DTO a partir de dados vindo de uma categoria.
	public CategoriaDTO(Categoria obj) {		
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
