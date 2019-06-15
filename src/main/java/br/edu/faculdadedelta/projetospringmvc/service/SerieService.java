package br.edu.faculdadedelta.projetospringmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.projetospringmvc.modelo.Serie;
import br.edu.faculdadedelta.projetospringmvc.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	public SerieRepository serieRepository;
	
	public Serie incluir(Serie serie) {
		serie.setId(null);
		return serieRepository.save(serie);
	}
	
	public Serie editar(Serie serie) {
		pesquisarPorId(serie.getId());
		return serieRepository.save(serie);
	}
	
	public void excluir(Long id) {
		serieRepository.deleteById(id);
	}
	
	public List<Serie> listar(){
		return serieRepository.findAll();
	}
	
	public Serie pesquisarPorId(Long id) {
		return serieRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)); 
	}
	
}
