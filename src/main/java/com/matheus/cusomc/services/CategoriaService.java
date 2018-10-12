package com.matheus.cusomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheus.cusomc.domain.Categoria;
import com.matheus.cusomc.dto.CategoriaDTO;
import com.matheus.cusomc.repositories.CategoriaRepository;
import com.matheus.cusomc.services.exceptions.ConstraintViolationException;
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
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new ConstraintViolationException("Não é possivel excluir uma Categoria que possue produtos!!");	
		}
	}
	
	public List<Categoria> findAll() { /* Uma operação que busca uma lista de categorias no repository*/
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	/* A partir de um DTO eu vou construir uma categoria passando seu id e nome.
	 * Será o inverso feito no construtor da CategoriaDTO mais será necessário criar um método pois será usando 
	   tanto no PUT com no POST.
	 */
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

}
