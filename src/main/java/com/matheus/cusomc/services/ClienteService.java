package com.matheus.cusomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheus.cusomc.domain.Cidade;
import com.matheus.cusomc.domain.Cliente;
import com.matheus.cusomc.domain.Endereco;
import com.matheus.cusomc.domain.enums.TipoCliente;
import com.matheus.cusomc.dto.ClienteDTO;
import com.matheus.cusomc.dto.ClienteNewDTO;
import com.matheus.cusomc.repositories.ClienteRepository;
import com.matheus.cusomc.repositories.EnderecoRepository;
import com.matheus.cusomc.services.exceptions.ConstraintViolationException;
import com.matheus.cusomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;	
	
	@Autowired
	private EnderecoRepository enderecoRepository;	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				+ id + ", Tipo " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new ConstraintViolationException("Não é possivel excluir porque há entidades relacionada!!");	
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) { /* Os outros campos serão nulos porque o DTO não tem esses dados */
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null); 
	}
	
	private void updateData(Cliente newObj, Cliente obj) { //Interno da classe.
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.
				getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		
		Cidade cid = new Cidade();
		cid.setId(objDTO.getCidadeId());
		
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), 
				objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end); // Adicionamos a uma lista de endereços referente ao cliente um endereço.
		cli.getTelefones().add(objDTO.getTelefone1()); /*Esse Telefone foi dito como obrigatótio
		agora precisamos sabre o cliente informou outros telefones */
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}

}
