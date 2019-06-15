package br.edu.faculdadedelta.projetospringmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.projetospringmvc.modelo.Genero;
import br.edu.faculdadedelta.projetospringmvc.repository.GeneroRepository;	

@Service
public class GeneroService {

	@Autowired
	public GeneroRepository generoRepository;
	
	public Genero incluir(Genero genero) {
		genero.setId(null);
		return generoRepository.save(genero);
	}
	
	public Genero editar(Genero genero) {
		pesquisarPorId(genero.getId());
		return generoRepository.save(genero);
	}
	
	public void excluir(Long id) {
		generoRepository.deleteById(id);
	}
	
	public List<Genero> listar(){
		return generoRepository.findAll();
	}
	
	public Genero pesquisarPorId(Long id) {
		return generoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)); 
	}
}
